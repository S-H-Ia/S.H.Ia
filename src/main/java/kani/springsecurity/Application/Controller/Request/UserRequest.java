package kani.springsecurity.Application.Controller.Request;

import lombok.Builder;

@Builder
public record UserRequest(
        String username,
        String password) {
}
