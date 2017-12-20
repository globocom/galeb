package io.galeb.core.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "virtualhostgroup")
public class VirtualhostGroup extends AbstractEntity implements WithStatus {

    @OneToMany(mappedBy = "virtualhostgroup", cascade = CascadeType.REMOVE)
    public Set<VirtualHost> virtualhosts = new HashSet<>();

    @OneToMany(mappedBy = "virtualhostgroup", cascade = CascadeType.REMOVE)
    public Set<RuleOrdered> rulesordered = new HashSet<>();

    @Transient
    private Status status = Status.UNKNOWN;

    public Set<VirtualHost> getVirtualhosts() {
        return virtualhosts;
    }

    public void setVirtualhosts(Set<VirtualHost> virtualhosts) {
        if (virtualhosts != null) {
            this.virtualhosts.clear();
            this.virtualhosts.addAll(virtualhosts);
        }
    }

    public Set<RuleOrdered> getRulesordered() {
        return rulesordered;
    }

    public void setRulesordered(Set<RuleOrdered> rulesordered) {
        if (rulesordered != null) {
            this.rulesordered.clear();
            this.rulesordered.addAll(rulesordered);
        }
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
}