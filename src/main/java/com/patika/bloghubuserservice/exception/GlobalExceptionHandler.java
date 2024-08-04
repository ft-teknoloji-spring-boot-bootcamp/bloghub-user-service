package com.patika.bloghubuserservice.exception;

import com.patika.bloghubuserservice.dto.response.GenericResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlogHubException.class)
    public GenericResponse handleBlogHubException(BlogHubException blogHubException){
        return GenericResponse.failed(blogHubException.getMessage());
    }

}
