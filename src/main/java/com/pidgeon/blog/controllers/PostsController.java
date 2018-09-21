package com.pidgeon.blog.controllers;

import com.pidgeon.blog.models.Comment;
import com.pidgeon.blog.models.Post;
import com.pidgeon.blog.services.CommentService;
import com.pidgeon.blog.services.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostsController {
  @Resource
  private PostService postService;

  @Resource
  private CommentService commentService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<Post>> posts() {
    List<Post> posts = new ArrayList<>();
    postService.findAll().forEach(posts::add);

    return new ResponseEntity<>(posts, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Post> post(@PathVariable long id) {
    Optional<Post> t = postService.findById(id);

    if (t.isPresent()) {
      return new ResponseEntity<>(t.get(), new HttpHeaders(), HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public Post create(@RequestBody Post post) {
    Post p = postService.save(post);

    return p;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity delete(@PathVariable Long id) {
    postService.deleteById(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PostConstruct
  public void init() {
    insertPost("Learn Spring Boot");
    insertPost("Learn JPA");
    insertPost("Learn Maven");
  }

  private void insertPost(String title) {
    Post p = new Post();
    p.setTitle(title);
    p.setContent("<p>Hello there</p>");

    Post savedP = postService.save(p);

    Comment c = new Comment();
    c.setContent("Nice article.");
    c.setPost(savedP);

    commentService.save(c);
  }
}
