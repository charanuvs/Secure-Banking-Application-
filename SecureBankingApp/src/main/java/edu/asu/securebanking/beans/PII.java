package edu.asu.securebanking.beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vikranth on 10/25/2015.
 */
@Entity
@Table(
        name = "pii",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID", "FROM_USER_ID"})}
)
public class PII implements Serializable {

    @Id
    @Column(name = "PII_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer piiId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "FROM_USER_ID")
    private AppUser fromUser;

    @Column(name = "TYPE",
            columnDefinition = "enum ('REQUEST', 'AUTHORIZE')")
    private String type;

    public Integer getPiiId() {
        return piiId;
    }

    public void setPiiId(Integer piiId) {
        this.piiId = piiId;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public AppUser getFromUser() {
        return fromUser;
    }

    public void setFromUser(AppUser fromUser) {
        this.fromUser = fromUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PII{" +
                "piiId=" + piiId +
                ", user=" + user +
                ", fromUser=" + fromUser +
                ", type='" + type + '\'' +
                '}';
    }
}
