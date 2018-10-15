package com.pidgeon.blog.repositories;

import com.pidgeon.blog.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
