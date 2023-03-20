package com.affiliatedLink.alCore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Consumer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "consumer",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_email", columnNames = "consumer_email")
        }
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "consumer_id",
            updatable = false
    )
    private UUID id;
    @Column(
            name = "consumer_first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "consumer_last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "consumer_email",
            nullable = false,
            columnDefinition = "TEXT"
    )
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

    public Consumer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
