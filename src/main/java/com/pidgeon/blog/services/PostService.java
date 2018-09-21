package com.pidgeon.blog.services;

import com.pidgeon.blog.models.Post;
import com.pidgeon.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class PostService {

  @Resource
  private PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public Optional<Post> findById(Long id) {
    Optional<Post> p = postRepository.findById(id);

    return p;
  }

  public Iterable<Post> findAll() {
    return postRepository.findAll();
  }

  public Post save(Post p) {
    return postRepository.save(p);
  }

  public void deleteById(Long id) {
    postRepository.deleteById(id);
  }
}
