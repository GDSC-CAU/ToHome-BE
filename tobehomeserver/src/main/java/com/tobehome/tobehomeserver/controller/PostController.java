package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.domain.entity.Comment;
import com.tobehome.tobehomeserver.domain.entity.Post;
import com.tobehome.tobehomeserver.domain.entity.Like;
import com.tobehome.tobehomeserver.dto.request.comment.CommentCreateRequest;
import com.tobehome.tobehomeserver.dto.request.post.PostCreateRequest;
import com.tobehome.tobehomeserver.dto.request.post.PostUpdateRequest;
import com.tobehome.tobehomeserver.service.CommentService;
import com.tobehome.tobehomeserver.service.LikeService;
import com.tobehome.tobehomeserver.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Service
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;

//    @Autowired
//    public PostController(PostService postService, LikeService likeService) {
//        this.postService = postService;
//        this.likeService = likeService;
//    }

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody PostCreateRequest request, @RequestHeader(name = "user_id") Long user_id) {
        try {
            Long postId = postService.createPost(request, user_id);
            return ResponseEntity.ok(postId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //    @GetMapping
//    public List<Post> getAllPosts() {
//        return postService.getAllPosts();
//    }
    @GetMapping
    public Page<Post> getPostsByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        return postService.getPostsByPage(page, size);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }

    @PatchMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        postService.updatePost(postId, request);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    // 좋아요 추가
    @PostMapping("/{postId}/likes")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestHeader(name = "user_id") Long userId) {
        try {
            likeService.likePost(postId, userId);
            return ResponseEntity.ok("게시물 좋아요 처리가 완료되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 한 게시물의 좋아요 수 조회
    @GetMapping("/{postId}/likeCount")
    public int getLikesCount(@PathVariable Long postId) {
        return likeService.getLikesCount(postId);
    }

    // 한 사람이 좋아요 누른 게시물들 조회
    @GetMapping("/likedByUser/{userId}")
    public List<Post> getPostsLikedByUser(@PathVariable Long userId) {
        return likeService.getPostsLikedByUser(userId);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<String> addComment(@PathVariable int postId, @RequestBody CommentCreateRequest request, @RequestHeader(name = "user_id") int userId) {
        request.setUserId(userId);
        request.setPostId(postId);

        try {
            commentService.addComment(request);
            return ResponseEntity.ok("댓글이 추가되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 추가에 실패했습니다.");
        }
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable int postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}