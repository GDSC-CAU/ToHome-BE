package com.tobehome.tobehomeserver.repository;

import com.tobehome.tobehomeserver.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}
