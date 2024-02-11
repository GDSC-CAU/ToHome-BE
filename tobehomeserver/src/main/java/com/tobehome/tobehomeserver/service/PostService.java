package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.domain.entity.Post;
import com.tobehome.tobehomeserver.dto.request.post.PostCreateRequest;
import com.tobehome.tobehomeserver.dto.request.post.PostUpdateRequest;
import com.tobehome.tobehomeserver.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostJpaRepository postJpaRepository;

    public Long createPost(PostCreateRequest request) {
        Post post = Post.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .shortDescription(request.getShortDescription())
                .content(request.getContent())
                .type(request.getType())
                .materialCategory(request.getMaterialCategoryId())
                .furnitureCategory(request.getFurnitureCategoryId())
                .imageUrl(request.getImageUrl())
                .build();
        return postJpaRepository.save(post).getId();
    }

    public List<Post> getAllPosts() {    //게시글 목록 조회
        return postJpaRepository.findAll();
    }

    public Page<Post> getPostsByPage(int page, int size) {       //게시글 페이지 당 목록 조회
        return postJpaRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Post getPostById(Long postId) {
        return postJpaRepository.findById(postId).orElse(null);
    }

    public void updatePost(Long postId, PostUpdateRequest request) {
        Post post = postJpaRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setTitle(request.getTitle());
            post.setShortDescription(request.getShortDescription());
            post.setContent(request.getContent());
            post.setMaterialCategory(request.getMaterialCategoryId());
            post.setFurnitureCategory(request.getFurnitureCategoryId());
            post.setImageUrl(request.getImageUrl());
        }
    }

    public void deletePost(Long postId) {
        postJpaRepository.deleteById(postId);
    }
}