package com.in28minutes.rest.webservice.restfulwebservices.controller;

import com.in28minutes.rest.webservice.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservice.restfulwebservices.model.Post;
import com.in28minutes.rest.webservice.restfulwebservices.model.User;
import com.in28minutes.rest.webservice.restfulwebservices.repository.PostRepository;
import com.in28minutes.rest.webservice.restfulwebservices.repository.UserRepository;
import com.in28minutes.rest.webservice.restfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
        //return userService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> find(@PathVariable("id") final Integer id) throws UserNotFoundException {
        User user = userRepository.findById(id).get();
        //User user = userService.find(id);

        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).findAll());

        resource.add(linkTo.withRel("all-users"));

        //HATEOAS

        return resource;
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody final User user) {
        Integer id = userRepository.save(user).getId();
        //Integer id = userService.create(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<User> update(@Valid @RequestBody final User user) throws UserNotFoundException {
        Integer id = userRepository.save(user).getId();
        //Integer id = userService.update(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id){
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
        //userService.delete(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> findPosts(@PathVariable("id") final Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new UserNotFoundException("id=" + id);
        }

        return user.get().getPosts();
    }
    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable("id") final Integer id,
                                             @RequestBody Post post) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException("id=" + id);
        }

        post.setUser(user.get());
        Post saved = postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}

