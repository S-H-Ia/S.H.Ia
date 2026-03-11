package kani.springsecurity.Domain.Users.Service;

import kani.springsecurity.Application.Controller.Request.UserRequest;
import kani.springsecurity.Domain.Users.Model.Users;
import kani.springsecurity.Domain.Users.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public List<Users> findall(){
        return repo.findAll();
    }

    public Users findById(Long id)throws Exception {
        Optional<Users> thisUsers = repo.findById(id);
        if (thisUsers.isPresent()){
            return thisUsers.get();
        }
        throw new Exception();
    }

    public void saveuser(Users users) {
       repo.save(users);
    }
}
