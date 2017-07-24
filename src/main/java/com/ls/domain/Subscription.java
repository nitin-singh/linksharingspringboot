package com.ls.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false)
    Date dateCreated;

    @Column(nullable = false)
    Date lastUpdated;

    public Subscription() {
    }

    public Subscription(Topic topic, User user, Date dateCreated, Date lastUpdated) {
        this.topic = topic;
        this.user = user;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public User getUser() {
        return user;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
