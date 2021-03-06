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

package io.galeb.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.*;

import static com.google.common.hash.Hashing.sha256;

@NamedQueries({
        @NamedQuery(
                name = "AccountDefault",
                query = "SELECT DISTINCT entity From Account entity WHERE entity.username = :username")
})

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UK_account_username", columnNames = { "username" }) })
public class Account extends AbstractEntity implements UserDetails {

    @JsonProperty(required = true)
    @Column(nullable = false)
    private String email;

    @JsonProperty(required = true)
    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @Transient
    private String password;

    @JsonIgnore
    @Transient
    private Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ADMIN");

    @JsonIgnore
    @Transient
    private boolean accountNonExpired = true;

    @JsonIgnore
    @Transient
    private boolean accountNonLocked = true;

    @JsonIgnore
    @Transient
    private boolean credentialsNonExpired = true;

    @JsonIgnore
    @Transient
    private boolean enabled = true;

    private String apitoken = sha256().hashBytes(UUID.randomUUID().toString().getBytes()).toString();

    private Boolean resettoken = false;


    @ManyToMany(mappedBy = "accounts")
    private Set<Team> teams = new HashSet<>();


    @ManyToMany(mappedBy = "accounts", fetch = FetchType.EAGER)
    private Set<RoleGroup> rolegroups = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> details = new HashMap<>();

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        if (teams != null) {
            this.teams.clear();
            this.teams.addAll(teams);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Assert.hasText(email, "email is not valid");
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        Assert.hasText(username, "name is not valid");
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<RoleGroup> getRolegroups() {
        return rolegroups;
    }

    public void setRolegroups(Set<RoleGroup> rolegroups) {
        if (rolegroups != null) {
            this.rolegroups.clear();
            this.rolegroups.addAll(rolegroups);
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String seed) {
        this.apitoken = sha256().hashBytes((seed + UUID.randomUUID().toString()).getBytes()).toString();
    }

    public Boolean getResettoken() {
        return resettoken;
    }

    public void setResettoken(Boolean resettoken) {
        this.resettoken = resettoken;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        if (details != null) {
            this.details.clear();
            this.details.putAll(details);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getUsername(), account.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return username;
    }

    @JsonIgnore
    public String extractApiTokenFromDetails(boolean usePassword) {
        if (usePassword) return getPassword();
        return getDetails().get("token");
    }
}
