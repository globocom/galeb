package io.galeb.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ruleordered")
public class RuleOrdered extends AbstractEntity implements WithStatus, Comparable<RuleOrdered> {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JsonProperty("virtualhostgroup")
    @JoinColumn(name = "virtualhostgroup_rule_ordered_id", nullable = false, foreignKey = @ForeignKey(name="FK_virtualhostgroup_rule_ordered"))
    private VirtualhostGroup virtualhostgroup;

    @JsonProperty("order")
    @Column(name = "rule_order", nullable = false)
    private Integer order = Integer.MAX_VALUE;

    @ManyToOne
    @JoinColumn(name = "rule_rule_ordered_id", nullable = false, foreignKey = @ForeignKey(name="FK_rule_rule_ordered"))
    private Rule rule;

    @Transient
    private Status status = Status.UNKNOWN;

    public VirtualhostGroup getVirtualhostgroup() {
        return virtualhostgroup;
    }

    public void setVirtualhostgroup(VirtualhostGroup virtualhostgroup) {
        this.virtualhostgroup = virtualhostgroup;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleOrdered that = (RuleOrdered) o;
        return Objects.equals(rule, that.rule);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rule);
    }

    @Override
    public int compareTo(RuleOrdered o) {
        return getRule().equals(o.getRule()) ? 0 : getOrder().compareTo(o.getOrder());
    }
}
