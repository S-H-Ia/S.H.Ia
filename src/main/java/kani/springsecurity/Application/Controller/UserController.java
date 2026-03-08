package kani.springsecurity.Application.Controller;

import kani.springsecurity.Application.Controller.Response.UserResponse;
import kani.springsecurity.Application.Mapper.UserMapper;
import kani.springsecurity.Domain.Users.Model.Users;
import kani.springsecurity.Domain.Users.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getall(){
        List<UserResponse> findall = service.findall().stream().map(mapper::ToResponse).toList();
        return ResponseEntity.ok(findall);
    }
}
