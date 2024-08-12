package com.jjikmukpa.project.config;

import org.springframework.security.core.AuthenticationException;

public class UserDeletedException extends AuthenticationException {
    public UserDeletedException(String msg) {
        super(msg);
    }
}