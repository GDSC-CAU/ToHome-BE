package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.dto.request.PostRelationDTO;
import com.tobehome.tobehomeserver.service.PostRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{interiorPostId}/related-posts")
@RequiredArgsConstructor
public class PostRelationController {

    private final PostRelationService postRelationService;

    @PostMapping("/{productPostId}")
    public ResponseEntity<String> addPostRelation(@PathVariable Long interiorPostId, @PathVariable Long productPostId) {
        try {
            postRelationService.addPostRelation(interiorPostId, productPostId);
            return ResponseEntity.ok("게시물 연관 관계가 추가되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시물 연관 관계 추가에 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<List<PostRelationDTO>> getRelatedPosts(@PathVariable Long interiorPostId) {
        List<PostRelationDTO> relatedPosts = postRelationService.getRelatedPosts(interiorPostId);
        return ResponseEntity.ok(relatedPosts);
    }

    @DeleteMapping("/{productPostId}")
    public ResponseEntity<String> removePostRelation(@PathVariable Long interiorPostId, @PathVariable Long productPostId) {
        try {
            postRelationService.removePostRelation(interiorPostId, productPostId);
            return ResponseEntity.ok("게시물 연관 관계가 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시물 연관 관계 삭제에 실패했습니다.");
        }
    }
}
