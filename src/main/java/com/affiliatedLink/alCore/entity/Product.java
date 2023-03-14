package com.affiliatedLink.alCore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "product"
)
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
            referencedColumnName = "consumer_id"
    )
    private Consumer productOwner;

    public Product(String productName, String productUrl, Timestamp productCreatedOn, Timestamp productUpdatedOn, Consumer productOwner) {
        this.productName = productName;
        this.productUrl = productUrl;
        this.productCreatedOn = productCreatedOn;
        this.productUpdatedOn = productUpdatedOn;
        this.productOwner = productOwner;
    }
}
