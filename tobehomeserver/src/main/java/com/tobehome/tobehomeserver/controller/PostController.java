package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.domain.entity.Post;
import com.tobehome.tobehomeserver.dto.request.post.PostCreateRequest;
import com.tobehome.tobehomeserver.dto.request.post.PostUpdateRequest;
import com.tobehome.tobehomeserver.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public Long createPost(@RequestBody PostCreateRequest request) {
        return postService.createPost(request);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
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
}