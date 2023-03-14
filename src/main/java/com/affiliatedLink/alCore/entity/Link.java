package com.affiliatedLink.alCore.entity;

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
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID linkId;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "linked_product",
            referencedColumnName = "product_id"
    )
    private Product product;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "linked_influencer",
            referencedColumnName = "consumer_id"
    )
    private Consumer influencer;
    @Column(
            name = "link_click_count",
            nullable = false
    )
    private Long clickCount;

    public Link(Product product, Consumer influencer, Long clickCount) {
        this.product = product;
        this.influencer = influencer;
        this.clickCount = clickCount;
    }
}
