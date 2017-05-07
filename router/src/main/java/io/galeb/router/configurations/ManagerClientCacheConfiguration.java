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

package io.galeb.router.configurations;

import io.galeb.core.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ManagerClientCacheConfiguration {

    @Bean
    ManagerClientCache managerClientCache() {
        return new ManagerClientCache();
    }

    public static class ManagerClientCache {
        private final ConcurrentHashMap<String, VirtualHost> virtualHosts = new ConcurrentHashMap<>();

        public VirtualHost get(String hostName) {
            return virtualHosts.get(hostName);
        }

        public synchronized void put(String virtualhostName, final VirtualHost virtualHost) {
            virtualHosts.put(virtualhostName, virtualHost);
        }

        public boolean isEmpty() {
            return virtualHosts.isEmpty();
        }

        public boolean exist(String virtualhostName) {
            return virtualHosts.containsKey(virtualhostName);
        }

        public synchronized void remove(String virtualhostName) {
            virtualHosts.remove(virtualhostName);
        }

        public Set<String> getAll() {
            return virtualHosts.keySet();
        }
    }
}