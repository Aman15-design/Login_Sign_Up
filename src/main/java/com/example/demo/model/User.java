package com.example.demo.model;

import java.util.Collection;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//now we need to make this class as JPAentitity.
//JPA entity class is just a POJO class but it is marked as having the ability to represent objects in the database
import javax.persistence.UniqueConstraint;

import org.yaml.snakeyaml.emitter.Emitable;
@Entity
@Table(name = "user" , uniqueConstraints = @UniqueConstraint(columnNames = "email")) // here we just specified the name of our table that is it would be Users and unique constraint in our table would be email
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // here we are specifying the strategy for generating our Identity key i.e ID in this case
    private Long id;
    
    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private String email;
    private String password;
    //here we have used collection as one user can a multiple roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //roles are retrieved eagerly, if we want to generate it on demand that we are gonna specify lazy. Here we also need to retreive role along with user.
    // whenever we perform operations like persist, merge or detach etc on parent entity than it would also be applied to its child entity. Here our role is child
    // now we need to create a third table to maintain a mapping between these two tables
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(
            name="user_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                name="role_id", referencedColumnName = "id"
            )
        )
    private Collection<Role> roles;
    public User(String firstName,String lastName, String email, String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName= lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    // we have to generate getter setter methods to access these private fields.
    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
