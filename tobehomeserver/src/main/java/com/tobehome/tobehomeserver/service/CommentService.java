package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.domain.entity.Comment;
import com.tobehome.tobehomeserver.dto.request.comment.CommentCreateRequest;
import com.tobehome.tobehomeserver.repository.CommentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentJpaRepository commentRepository;

    @Autowired
    public CommentService(CommentJpaRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(CommentCreateRequest request) {
        Comment comment = new Comment();
        comment.setUserId(request.getUserId());
        comment.setPostId(request.getPostId());
        comment.setContent(request.getContent());
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(int postId) {
        return commentRepository.findByPostId(postId);
    }
}