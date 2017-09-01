package com.ls.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(nullable = false)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @Transient
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "*Please provide your name")
    private String firstName;

    @Column(nullable = true)
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(nullable = true)
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Resource> resources = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions = new ArrayList<>();

    @Column(nullable = true)
    @Lob
    private byte[] image;

    @Transient
    String fullName;

    @Transient
    String confirmPassword;

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getActive() {
        return active;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public byte[] getImage() {
        return image;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}