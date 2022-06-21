package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = ("users"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role")
//    private Role role;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Order> orders;

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String email,
                /*Role role,*/ String password, List<Order> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
//        this.role = role;
        this.password = password;
        this.orders = orders;
    }

    public User(String firstName, String lastName, String email, String encryptedPassword) {
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
