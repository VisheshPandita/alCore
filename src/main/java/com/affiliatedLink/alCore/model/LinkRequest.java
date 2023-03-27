package com.affiliatedLink.alCore.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkRequest {
    @NotNull
    private UUID product;
    @NotNull
    private UUID influencer;
}
