package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.domain.entity.Post;
import com.tobehome.tobehomeserver.domain.entity.PostRelation;
import com.tobehome.tobehomeserver.dto.request.PostRelationDTO;
import com.tobehome.tobehomeserver.repository.PostJpaRepository;
import com.tobehome.tobehomeserver.repository.PostRelationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostRelationService {

    private final PostRelationJpaRepository postRelationRepository;
    private final PostJpaRepository postRepository;

    @Transactional
    public void addPostRelation(Long interiorPostId, Long productPostId) {
        Post interiorPost = postRepository.findById(interiorPostId)
                .orElseThrow(() -> new IllegalArgumentException("인테리어 게시글을 찾을 수 없습니다."));

        Post productPost = postRepository.findById(productPostId)
                .orElseThrow(() -> new IllegalArgumentException("상품 게시글을 찾을 수 없습니다."));

        PostRelation postRelation = PostRelation.builder()
                .interiorPost(interiorPost)
                .productPost(productPost)
                .build();

        postRelationRepository.save(postRelation);
    }

    @Transactional(readOnly = true)
    public List<PostRelationDTO> getRelatedPosts(Long interiorPostId) {
        List<PostRelation> postRelations = postRelationRepository.findByInteriorPostId(interiorPostId);
        return postRelations.stream()
                .map(PostRelationDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removePostRelation(Long interiorPostId, Long productPostId) {
        postRelationRepository.deleteByInteriorPostIdAndProductPostId(interiorPostId, productPostId);
    }
}
