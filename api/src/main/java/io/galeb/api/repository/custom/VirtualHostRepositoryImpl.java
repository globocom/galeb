/*
 * Copyright (c) 2014-2018 Globo.com - ATeam
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

package io.galeb.api.repository.custom;

import com.google.common.collect.Sets;
import io.galeb.api.services.GenericDaoService;
import io.galeb.api.repository.EnvironmentRepository;
import io.galeb.api.services.StatusService;
import io.galeb.core.entity.AbstractEntity;
import io.galeb.core.entity.Environment;
import io.galeb.core.entity.Project;
import io.galeb.core.entity.VirtualHost;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Set;

@SuppressWarnings({"unused", "SpringJavaAutowiredMembersInspection"})
public class VirtualHostRepositoryImpl extends AbstractRepositoryImplementation<VirtualHost> implements VirtualHostRepositoryCustom, WithRoles {

    @Autowired
    private GenericDaoService genericDaoService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @PostConstruct
    private void init() {
        setSimpleJpaRepository(VirtualHost.class, genericDaoService);
        setStatusService(statusService);
    }

    @Override
    protected Set<Environment> getAllEnvironments(AbstractEntity entity) {
        return Sets.newHashSet(((VirtualHost)entity).getEnvironments());
    }

    @Override
    protected long getProjectId(Object criteria) {
        try {
            long id = -1L;
            if (criteria instanceof Project) {
                return ((Project) criteria).getId();
            }
            if (criteria instanceof VirtualHost) {
                return ((VirtualHost) criteria).getProject().getId();
            }
            VirtualHost virtualHost = null;
            if (criteria instanceof String) {
                virtualHost = (VirtualHost) genericDaoService.findByName(VirtualHost.class, (String) criteria);
            }
            if (criteria instanceof Long) {
                virtualHost = (VirtualHost) genericDaoService.findOne(VirtualHost.class, (Long) criteria);
            }
            if (virtualHost != null) {
                return virtualHost.getProject().getId();
            } else {
                return NOT_FOUND;
            }
        } catch (Exception ignored) {}
        return -1L;
    }
}
