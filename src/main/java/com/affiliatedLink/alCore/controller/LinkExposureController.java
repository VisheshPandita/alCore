package com.affiliatedLink.alCore.controller;

import com.affiliatedLink.alCore.entity.Link;
import com.affiliatedLink.alCore.exception.LinkNotFoundException;
import com.affiliatedLink.alCore.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class LinkExposureController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/{linkId}")
    public RedirectView redirectToOriginalLink(@PathVariable UUID linkId) throws LinkNotFoundException {
        Link link = linkService.linkVisited(linkId);
        return new RedirectView(link.getProduct().getProductUrl());
    }
}
