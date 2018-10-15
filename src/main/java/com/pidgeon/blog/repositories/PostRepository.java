package com.pidgeon.blog.repositories;

import com.pidgeon.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
