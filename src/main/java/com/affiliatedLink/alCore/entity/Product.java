package com.affiliatedLink.alCore.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "product"
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "productId")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "product_id",
            updatable = false
    )
    private UUID productId;
    @Column(
            name = "product_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String productName;
    @Column(
            name = "product_url",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String productUrl;
    @Column(
            name = "product_created_on",
            nullable = false
    )
    private Timestamp productCreatedOn;
    @Column(
            name = "product_updated_on",
            nullable = false
    )
    private Timestamp productUpdatedOn;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "product_owner",
            referencedColumnName = "user_id",
            nullable = false
    )
    private User productOwner;
    @OneToMany(
            targetEntity = Link.class,
            cascade = CascadeType.ALL,
            mappedBy = "product",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Link> generatedLinks;

    public Product(String productName, String productUrl, Timestamp productCreatedOn, Timestamp productUpdatedOn, User productOwner) {
        this.productName = productName;
        this.productUrl = productUrl;
        this.productCreatedOn = productCreatedOn;
        this.productUpdatedOn = productUpdatedOn;
        this.productOwner = productOwner;
    }
}
