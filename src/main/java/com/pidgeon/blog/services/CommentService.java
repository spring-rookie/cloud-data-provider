package com.pidgeon.blog.services;

import com.pidgeon.blog.models.Comment;
import com.pidgeon.blog.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentService {
  @Resource
  private CommentRepository commentRepository;

  public Comment save(Comment c) {
    return commentRepository.save(c);
  }
}
