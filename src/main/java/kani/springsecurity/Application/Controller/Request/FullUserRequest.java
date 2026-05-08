package kani.springsecurity.Application.Controller.Request;

import lombok.Builder;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record FullUserRequest (
        String username,
        String password,

        String bio,
        String location,
        String occupation,
        String interests,
        Set<String> tags

        ) {

    public static FullUserRequest EmptyExemple (){
        return FullUserRequest.builder().username("").password("").bio("").location("").occupation("").interests("").tags(Set.of()).build();
    }

    public static Map Build(FullUserRequest request){

        UserRequest userReq = UserRequest.builder().username(request.username()).password(request.password()).build();

        Set<TagRequest> tagReq = request.tags.stream()
                .map(tag -> TagRequest.builder()
                        .tag(tag)
                        .build())
                .collect(Collectors.toSet());

        ProfileRequest profileReq = ProfileRequest.builder()
                .bio(request.bio())
                .location(request.location())
                .occupation(request.occupation())
                .interests(request.interests())
                .tags(tagReq)
                .build();

        return  Map.of(
                "user", UserRequest.ToEntity(userReq),

                "profile",  ProfileRequest.ToEntity(profileReq)
                );
    }

}
