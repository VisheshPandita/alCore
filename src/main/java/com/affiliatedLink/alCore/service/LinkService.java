package com.affiliatedLink.alCore.service;

import com.affiliatedLink.alCore.model.LinkRequest;
import com.affiliatedLink.alCore.entity.Link;
import com.affiliatedLink.alCore.entity.Product;
import com.affiliatedLink.alCore.entity.User;
import com.affiliatedLink.alCore.exception.UserNotFoundException;
import com.affiliatedLink.alCore.exception.LinkNotFoundException;
import com.affiliatedLink.alCore.exception.ProductNotFoundException;
import com.affiliatedLink.alCore.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<Link> getLink() {
        return linkRepository.findAll();
    }

    public Link registerLink(LinkRequest LinkRequest) throws UserNotFoundException, ProductNotFoundException {
        User influencer = userService.getUserById(LinkRequest.getInfluencer());
        Product product = productService.getProductById(LinkRequest.getProduct());

        Link link = new Link(
                product,
                influencer,
                0L
        );

        return linkRepository.save(link);
    }

    public Link getLinkById(UUID linkId) throws LinkNotFoundException {
        Optional<Link> link = linkRepository.findById(linkId);
        if(link.isPresent()) return link.get();
        else throw new LinkNotFoundException("Link not found with id " + linkId);
    }

    public Link linkVisited(UUID linkId) throws LinkNotFoundException {
        Optional<Link> link = linkRepository.findById(linkId);
        if(link.isPresent()) {
            Link updatedLink = link.get();
            updatedLink.setClickCount(updatedLink.getClickCount()+1);
            return linkRepository.save(updatedLink);
        }
        else throw new LinkNotFoundException("Link not found with id " + linkId);
    }
}
