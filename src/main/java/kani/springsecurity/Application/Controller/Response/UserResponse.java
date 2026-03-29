package kani.springsecurity.Application.Controller.Response;

import lombok.Builder;

@Builder
public record UserResponse(
        String username,
        Long id){
}
