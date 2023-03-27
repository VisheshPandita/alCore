package com.affiliatedLink.alCore.controller;

import com.affiliatedLink.alCore.model.LinkRequest;
import com.affiliatedLink.alCore.entity.Link;
import com.affiliatedLink.alCore.exception.UserNotFoundException;
import com.affiliatedLink.alCore.exception.LinkNotFoundException;
import com.affiliatedLink.alCore.exception.ProductNotFoundException;
import com.affiliatedLink.alCore.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping
    public ResponseEntity<List<Link>> getLink() { return ResponseEntity.ok(linkService.getLink()); }

    @PostMapping
    public ResponseEntity<Link> registerLink(@RequestBody @Valid LinkRequest linkRequest) throws UserNotFoundException, ProductNotFoundException {
        Link link = linkService.registerLink(linkRequest);
        return new ResponseEntity<>(link, HttpStatus.CREATED);
    }

    @GetMapping("/{linkId}")
    public ResponseEntity<Link> getLinkById(@PathVariable UUID linkId) throws LinkNotFoundException {
        return ResponseEntity.ok(linkService.getLinkById(linkId));
    }
}
