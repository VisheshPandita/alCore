package com.affiliatedLink.alCore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "Link")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "link")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "linkId")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "link_id",
            updatable = false
    )
    private UUID linkId;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "linked_product",
            referencedColumnName = "product_id",
            nullable = false
    )
    private Product product;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "linked_influencer",
            referencedColumnName = "user_id",
            nullable = false
    )
    private User influencer;
    @Column(
            name = "link_click_count",
            nullable = false
    )
    private Long clickCount;

    public Link(Product product, User influencer, Long clickCount) {
        this.product = product;
        this.influencer = influencer;
        this.clickCount = clickCount;
    }
}
