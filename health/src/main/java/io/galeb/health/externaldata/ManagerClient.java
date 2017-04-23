package io.galeb.health.externaldata;

import com.google.gson.Gson;
import io.galeb.health.SystemEnvs;
import io.galeb.health.services.HttpClientService;
import io.galeb.manager.entity.Environment;
import io.galeb.manager.entity.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

import static io.galeb.health.utils.ErrorLogger.logError;
import static io.galeb.health.externaldata.ManagerSpringRestResponse.*;

@Component
public class ManagerClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Gson gson = new Gson();

    private final String managerUrl = SystemEnvs.MANAGER_URL.getValue();
    private final String manageruser = SystemEnvs.MANAGER_USER.getValue();
    private final String managerPass = SystemEnvs.MANAGER_PASS.getValue();
    private final String tokenUrl = managerUrl + "/token";
    private final HttpClientService httpClientService;
    private String token = null;

    @Autowired
    public ManagerClient(final HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    public void resetToken() {
        this.token = null;
    }

    public Stream<Target> targetsByEnvName(String environmentName) {
        renewToken();
        if (token != null) {
            long environmentId = getEnvironmentId(environmentName);
            if (environmentId == -1) {
                logger.error("Environment \"" +  environmentName + "\" NOT FOUND");
                return Stream.empty();
            }
            String poolsUrl = managerUrl + "/environment/" + Math.toIntExact(environmentId) + "/pools";
            String body = httpClientService.getResponseBodyWithToken(poolsUrl, token);
            if (!"".equals(body)) {
                PoolList poolList = gson.fromJson(body, PoolList.class);
                return targetsByPoolList(poolList);
            } else {
                logger.error("httpClientService.getResponseBodyWithToken has return body empty");
                resetToken();
                return Stream.empty();
            }
        }
        logger.error("Token is NULL (request problem?)");
        return Stream.empty();
    }

    public Stream<Target> targetsByPoolList(ManagerSpringRestResponse.PoolList poolList) {
        try {
            return Arrays.stream(poolList._embedded.pool).parallel().map(pool -> {
                String targetsUrl = pool._links.targets.href + "?size=99999999";
                String bodyTargets = httpClientService.getResponseBodyWithToken(targetsUrl, token);
                if (!"".equals(bodyTargets)) {
                    final ManagerSpringRestResponse.TargetList targetList = gson.fromJson(bodyTargets, ManagerSpringRestResponse.TargetList.class);
                    return Arrays.stream(targetList._embedded.target).map(target -> {
                        target.setParent(pool);
                        return target;
                    });
                } else {
                    return Stream.empty();
                }
            }).flatMap(s -> s.map(o -> (Target) o));
        } catch (Exception e) {
            logError(e, this.getClass());
            return Stream.empty();
        }
    }

    public long getEnvironmentId(String envName) {
        renewToken();
        if (token != null) {
            String envFindByNameUrl = managerUrl + "/environment/search/findByName?name=" + envName;
            String body = httpClientService.getResponseBodyWithToken(envFindByNameUrl, token);
            EnvironmentFindByName environmentFindByName = gson.fromJson(body, EnvironmentFindByName.class);
            try {
                Environment environment = Arrays.stream(environmentFindByName._embedded.environment).findAny().orElse(null);
                if (environment != null) {
                    return environment.getId();
                }
            } catch (NullPointerException e) {
                logError(e, this.getClass());
                resetToken();
            }
        } else {
            logger.error("Token is NULL (request problem?)");
        }
        return -1;
    }

    public void patch(String targetUrl, String body) {
        if (!httpClientService.patchResponse(targetUrl, body, token)) {
            logger.error("Request FAIL");
            resetToken();
        }
    }

    public void renewToken() {
        if (token == null) {
            String bodyToken = httpClientService.getResponseBodyWithAuth(manageruser, managerPass, tokenUrl);
            if (!"".equals(bodyToken)) {
                Token tokenObj = gson.fromJson(bodyToken, Token.class);
                token = tokenObj.token;
            }
        }
    }

}