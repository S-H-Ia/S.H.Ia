package kani.springsecurity.Application.Controller.Request;

import kani.springsecurity.Application.Controller.Response.UserResponse;
import kani.springsecurity.Domain.Users.Users;
import lombok.Builder;

@Builder
public record FullUserRequest(
        UserRequest user,
        ProfileRequest profile
        ) {
    public UserResponse UserAndProfileWrapperToResponse(UserRequest userRequest, ProfileRequest profileRequest){
        return UserResponse.ToResponse(
                    Users.builder()
                        .username(userRequest.username())
                            .password(userRequest.password())

                        .thisuserprofile(
                                ProfileRequest.ToEntity(profileRequest)
                        )
                    .build())
                ;
    }
}
