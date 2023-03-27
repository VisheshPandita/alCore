package com.affiliatedLink.alCore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity(name = "Consumer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "consumer",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "user_email")
        }
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "user_id",
            updatable = false
    )
    private UUID id;
    @Column(
            name = "user_first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "user_last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "user_email",
            nullable = false
    )
    @Email
    private String email;
    @OneToMany(
            targetEntity = Product.class,
            cascade = CascadeType.ALL,
            mappedBy = "productOwner",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Product> productList;
    @OneToMany(
            targetEntity = Link.class,
            cascade = CascadeType.ALL,
            mappedBy = "influencer",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Link> linkList;
    @NotBlank
    @Size(max = 120)
    private String password;
    private String role;
    private boolean enabled = false;

    public User(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
