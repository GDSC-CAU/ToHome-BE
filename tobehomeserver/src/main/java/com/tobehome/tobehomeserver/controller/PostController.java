package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.domain.entity.Comment;
import com.tobehome.tobehomeserver.domain.entity.Post;
import com.tobehome.tobehomeserver.dto.request.comment.CommentCreateRequest;
import com.tobehome.tobehomeserver.dto.request.post.PostCreateRequest;
import com.tobehome.tobehomeserver.dto.request.post.PostUpdateRequest;
import com.tobehome.tobehomeserver.service.CommentService;
import com.tobehome.tobehomeserver.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
//@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


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

    @PatchMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        postService.updatePost(postId, request);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
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