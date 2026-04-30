package kani.springsecurity.Application.Controller;

import kani.springsecurity.Application.Controller.Request.FullUserRequest;
import kani.springsecurity.Application.Controller.Request.ProfileRequest;
import kani.springsecurity.Application.Controller.Request.UserRequest;
import kani.springsecurity.Application.Controller.Response.ProfileResponse;
import kani.springsecurity.Application.Controller.Response.UserResponse;
import kani.springsecurity.Domain.Profile.Profile;
import kani.springsecurity.Domain.Tags.TagService;
import kani.springsecurity.Domain.Users.Users;
import kani.springsecurity.Domain.Profile.ProfileService;
import kani.springsecurity.Domain.Users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private  final ProfileService PfService;
    private final TagService TAGservice;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getall(){
        List<UserResponse> findall = service.findall().stream().map(UserResponse::ToResponse).toList();
        return ResponseEntity.ok(findall);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getbyid(@PathVariable Long id) throws Exception {
        try{
            UserResponse reponse = UserResponse.ToResponse(service.findById(id));
            return ResponseEntity.ok(reponse);
        }catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Void> postuser(@RequestBody  UserRequest users) throws Exception {
        service.saveuser(UserRequest.ToEntity(users));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<UserResponse> edituser(@RequestBody UserRequest request,@PathVariable Long id){
        try{
            Users user = service.alterUser(id, request);
            return ResponseEntity.ok(UserResponse.ToResponse(user));
        }  catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id, Authentication auth) throws Exception {
        service.deleteUser(id);
        PfService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }

    // User Profile operations -------------------------------------
    // the profile is iniciated at the moment that a user is created.



    @PutMapping("/profile/{id}")
    public ResponseEntity<ProfileResponse> PutUserPRofile(@PathVariable Long id, @RequestBody ProfileRequest request) throws Exception {
        try{
            Profile profile = PfService.alterProfile(id, request);
            return ResponseEntity.ok(ProfileResponse.ToResponse(profile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResponse> getProfileByid(@PathVariable Long id){
        try{
            Profile byId = PfService.findById(id);
            ProfileResponse profileResponse = ProfileResponse.ToResponse(byId);

            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @PostMapping("full-user/")
    public ResponseEntity CreateFullUser(@RequestBody FullUserRequest request) throws Exception {
       try{
        Users savedUser = service.saveuser(
                    UserRequest.ToEntity(request.user())
                ) ;
        PfService.saveProfile(
                    ProfileRequest.ToEntity(request.profile())
                );
        return ResponseEntity.ok(
                UserResponse.ToResponse(savedUser)
        );
       }catch (Exception e ){
           System.out.println(e);
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
       }
    }
}
