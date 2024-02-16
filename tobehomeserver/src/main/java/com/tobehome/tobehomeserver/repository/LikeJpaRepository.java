package com.tobehome.tobehomeserver.repository;

import com.tobehome.tobehomeserver.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeJpaRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    void deleteByPostIdAndUserId(Long postId, Long userId);
//    List<Long> findPostIdsByUserId(Long userId);
    @Query("SELECT l.postId FROM Like l WHERE l.userId = :userId")
    List<Long> findPostIdsByUserId(@Param("userId") Long userId);
    int countByPostId(Long postId);

}