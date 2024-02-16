package com.tobehome.tobehomeserver.repository;

import com.tobehome.tobehomeserver.domain.entity.PostRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRelationJpaRepository extends JpaRepository<PostRelation, Long> {
    List<PostRelation> findByInteriorPostId(Long interiorPostId);
    List<PostRelation> findByProductPostId(Long productPostId);
    Optional<PostRelation> findByInteriorPostIdAndProductPostId(Long interiorPostId, Long productPostId);
    void deleteByInteriorPostIdAndProductPostId(Long interiorPostId, Long productPostId);
}

