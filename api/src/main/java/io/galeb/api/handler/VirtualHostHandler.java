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

package io.galeb.api.handler;

import io.galeb.api.repository.VirtualhostGroupRepository;
import io.galeb.core.entity.Environment;
import io.galeb.core.entity.VirtualHost;
import io.galeb.core.entity.VirtualhostGroup;
import io.galeb.core.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class VirtualHostHandler extends AbstractHandler<VirtualHost> {

    @Autowired
    VirtualhostGroupRepository virtualhostGroupRepository;

    @Override
    protected void onBeforeCreate(VirtualHost virtualHost) {
        super.onBeforeCreate(virtualHost);
        if (virtualHost.getEnvironments() == null || virtualHost.getEnvironments().isEmpty()) {
            throw new BadRequestException("Environment(s) undefined");
        }
        checkVirtualHostGroup(virtualHost);
    }

    @Override
    protected void onBeforeSave(VirtualHost virtualHost) {
        super.onBeforeSave(virtualHost);
        checkVirtualHostGroup(virtualHost);
    }

    @Override
    protected Set<Environment> getAllEnvironments(VirtualHost entity) {
        return entity.getEnvironments();
    }

    private void checkVirtualHostGroup(VirtualHost virtualHost) {
        if (virtualHost.getVirtualhostgroup() == null) {
            VirtualhostGroup virtualhostGroup = new VirtualhostGroup();
            virtualhostGroupRepository.save(virtualhostGroup);
            virtualHost.setVirtualhostgroup(virtualhostGroup);
        }
    }

}
