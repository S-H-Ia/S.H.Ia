package kani.springsecurity.Application.Controller.Response;

import kani.springsecurity.Domain.Users.Model.Profile;
import lombok.Builder;

@Builder
public record UserResponse(
        String username,
        Long id,
        Profile profile
        ){
}
