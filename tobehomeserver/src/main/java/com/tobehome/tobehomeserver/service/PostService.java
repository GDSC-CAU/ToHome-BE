package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.domain.entity.Post;
import com.tobehome.tobehomeserver.dto.request.post.PostCreateRequest;
import com.tobehome.tobehomeserver.dto.request.post.PostDTO;
import com.tobehome.tobehomeserver.dto.request.post.PostUpdateRequest;
import com.tobehome.tobehomeserver.repository.PostJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final HttpServletRequest request;
    private final PostJpaRepository postJpaRepository;

    public Long createPost(PostCreateRequest request, Long userId) {
        if (userId != null) {
            Post post = Post.builder()
                    .userId(userId)
                    .title(request.getTitle())
                    .shortDescription(request.getShortDescription())
                    .content(request.getContent())
                    .type(request.getType())
                    .materialCategory(request.getMaterialCategory())
                    .furnitureCategory(request.getFurnitureCategory())
                    .imageUrl(request.getImageUrl())
                    .imageUrl2(request.getImageUrl2())
                    .imageUrl3(request.getImageUrl3())
                    .x(request.getX())
                    .y(request.getY())
                    .build();
            return postJpaRepository.save(post).getId();
        } else {
            throw new IllegalArgumentException("사용자 ID를 가져올 수 없습니다.");
        }
    }



//    private Long getCurrentUserId(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            return (Long) session.getAttribute("user_id");
//        } else {
//            // 세션이 없는 경우 예외처리 또는 기본값 반환 등을 수행할 수 있습니다.
//            return null; // 또는 다른 값 또는 예외 처리 등을 수행할 수 있습니다.
//        }
//    }
//    public List<Post> getAllPosts() {    //게시글 목록 조회
//        return postJpaRepository.findAll();
//    }

    public Page<Post> getPostsByPage(int page, int size) {       //게시글 페이지 당 목록 조회
        return postJpaRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Post getPostById(Long postId) {
        return postJpaRepository.findById(postId).orElse(null);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postJpaRepository.findByUserId(userId);
    }

    public void updatePost(Long postId, PostUpdateRequest request) {
        Post post = postJpaRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setTitle(request.getTitle());
            post.setShortDescription(request.getShortDescription());
            post.setContent(request.getContent());
            post.setMaterialCategory(request.getMaterialCategory());
            post.setFurnitureCategory(request.getFurnitureCategory());
            post.setImageUrl(request.getImageUrl());
            post.setImageUrl2(request.getImageUrl2());
            post.setImageUrl3(request.getImageUrl3());
        }
    }

    public void deletePost(Long postId) {
        postJpaRepository.deleteById(postId);
    }

    public List<PostDTO> getPostsByMaterialCategory(Long materialCategoryId) {
        // 구현: 특정 재료 카테고리에 속하는 게시글을 가져와서 DTO로 변환
        List<Post> posts = postJpaRepository.findByMaterialCategory(materialCategoryId);
        return posts.stream().map(PostDTO::fromEntity).collect(Collectors.toList());
    }

    public List<PostDTO> getPostsByFurnitureCategory(Long furnitureCategoryId) {
        // 구현: 특정 가구 카테고리에 속하는 게시글을 가져와서 DTO로 변환
        List<Post> posts = postJpaRepository.findByFurnitureCategory(furnitureCategoryId);
        return posts.stream().map(PostDTO::fromEntity).collect(Collectors.toList());
    }
}