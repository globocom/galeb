/*
 * Copyright (c) 2014-2017 Globo.com - ATeam
 * All rights reserved.
 *
 * This source is subject to the Apache License, Version 2.0.
 * Please see the LICENSE file for more information.
 *
 * Authors: See AUTHORS file
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.galeb.health.configurations;

import io.galeb.core.entity.dto.TargetDTO;
import io.galeb.core.enums.SystemEnv;
import io.galeb.core.log.JsonEventToLogger;
import io.galeb.health.services.HealthCheckerService;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

@SuppressWarnings("Duplicates")
@Configuration
@EnableJms
public class JMSConfiguration implements JmsListenerConfigurer {

    // @formatter:off
    private static final String QUEUE_NAME = SystemEnv.QUEUE_NAME.getValue()     + SystemEnv.QUEUE_NAME_SEPARATOR.getValue() +
                                             SystemEnv.ENVIRONMENT_ID.getValue() + SystemEnv.QUEUE_NAME_SEPARATOR.getValue() +
                                             SystemEnv.ZONE_ID.getValue().toLowerCase();
    // @formatter:on

    private final HealthCheckerService healthCheckerService;

    @Autowired
    public JMSConfiguration(HealthCheckerService healthCheckerService) {
        this.healthCheckerService = healthCheckerService;
    }

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar endpointRegistrar) {
        SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
        endpoint.setId(QUEUE_NAME);
        endpoint.setDestination(QUEUE_NAME);
        endpoint.setConcurrency("5-5");
        endpoint.setMessageListener(message -> {
            try {
                if (message.isBodyAssignableTo(TargetDTO.class)) {
                    healthCheckerService.check(message.getBody(TargetDTO.class));
                }
            } catch (JMSException e) {
                JsonEventToLogger eventToLogger = new JsonEventToLogger(this.getClass());
                eventToLogger.put("short_message", "Error processing message from queue");
                eventToLogger.sendError(e);
            }
        });
        endpointRegistrar.registerEndpoint(endpoint);
    }
}
