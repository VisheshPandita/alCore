package com.affiliatedLink.alCore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Consumer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
