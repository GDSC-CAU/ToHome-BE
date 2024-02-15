package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.domain.entity.Like;
import com.tobehome.tobehomeserver.domain.entity.Post;
import com.tobehome.tobehomeserver.repository.LikeJpaRepository;
import com.tobehome.tobehomeserver.repository.PostJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LikeService {

    @Autowired
    private LikeJpaRepository likeRepository;

    @Autowired
    private PostJpaRepository postRepository;

    // 좋아요 추가
    // 좋아요 추가
    @Transactional
    public void likePost(Long postId, Long userId) {
//        Principal principal = request.getUserPrincipal();
//        if (principal == null) {
//            throw new IllegalStateException("사용자가 로그인되어 있지 않습니다.");
//        }
//        Long userId = Long.parseLong(principal.getName());
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("좋아요를 추가할 수 없습니다."));

        if(likeRepository.findByPostIdAndUserId(postId, userId).isPresent()) {
            // 이미 값이 존재하면 좋아요 취소
            Like like = likeRepository.findByPostIdAndUserId(postId, userId).get();
            likeRepository.deleteByPostIdAndUserId(postId, userId);
        } else {
            // 좋아요 처리 한 적이 없다면 좋아요 처리
            Like like = Like.builder()

                    .user_id(userId)
                    .postId(postId)
                    .build();

            likeRepository.save(like);
        }

    }

    // 좋아요 삭제
//    public void deleteLike(Long postId, HttpServletRequest request) {
//        Long userId = Long.parseLong(request.getUserPrincipal().getName());
//        try {
//            likeRepository.deleteByPostIdAndUserId(postId, userId);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("좋아요를 삭제할 수 없습니다.");
//        }
//    }

    // 사용자가 누른 게시물 조회
    public List<Post> getPostsLikedByUser(Long userId) {
        try {
            List<Long> postIds = likeRepository.findPostIdsByUserId(userId);
            return postRepository.findAllById(postIds);
        } catch (Exception e) {
            throw new IllegalArgumentException("사용자의 좋아요를 조회할 수 없습니다.");
        }
    }

    // 한 게시물 당 좋아요 수 조회
    public int getLikesCount(Long postId) {
        try {
            return likeRepository.countByPostId(postId);
        } catch (Exception e) {
            throw new IllegalArgumentException("게시물의 좋아요 수를 조회할 수 없습니다.");
        }
    }

}