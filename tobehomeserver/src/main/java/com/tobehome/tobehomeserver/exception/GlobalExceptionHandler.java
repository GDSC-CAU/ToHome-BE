package com.tobehome.tobehomeserver.exception;

import com.tobehome.tobehomeserver.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "이미 존재하는 닉네임입니다.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(message));
    }

//    @ExceptionHandler(NicknameDuplicateException.class)
//    public ResponseEntity<Object> handleNicknameDuplicateException(NicknameDuplicateException ex) {
//        return ResponseEntity.badRequest().body(new ErrorResponse("이미 존재하는 닉네임입니다."));
//    }
//
//    @ExceptionHandler(MemberNotFoundException.class)
//    public ResponseEntity<Object> handleMemberNotFoundException(MemberNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(new ErrorResponse("회원을 찾을 수 없습니다."));
//    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage()));
    }
}
