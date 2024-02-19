package com.tobehome.tobehomeserver.exception;

public class NicknameDuplicateException extends RuntimeException {
    public NicknameDuplicateException(String message) {
        super(message);
    }
}