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

import io.galeb.api.services.GenericDaoService;
import io.galeb.api.services.StatusService;
import io.galeb.core.entity.Account;
import io.galeb.core.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"unused", "SpringJavaAutowiredMembersInspection"})
public class TeamRepositoryImpl extends AbstractRepositoryImplementation<Team> implements TeamRepositoryCustom, WithRoles {

    @Autowired
    private GenericDaoService genericDaoService;

    @Autowired
    private StatusService statusService;

    @PostConstruct
    private void init() {
        setSimpleJpaRepository(Team.class, genericDaoService);
        setStatusService(statusService);
    }

    @Override
    public Set<String> roles(Object criteria) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (criteria instanceof Team) {
            if (isAccountLinkedWithTeam(account.getId(), ((Team)criteria).getId())) {
                return mergeAllRolesOf(account);
            }
        }
        Team team = null;
        if (criteria instanceof Long) {
            team = (Team) genericDaoService.findOne(Team.class, (Long) criteria);
        }
        if (criteria instanceof String) {
            team = (Team) genericDaoService.findByName(Team.class, (String) criteria);
        }
        if (team != null) {
            return roles(team);
        }
        return Collections.emptySet();
    }

    private boolean isAccountLinkedWithTeam(long accountId, long teamId) {
        List<Team> teams = genericDaoService.teamLinkedToAccount(accountId, teamId);
        return teams != null && !teams.isEmpty();
    }


}
