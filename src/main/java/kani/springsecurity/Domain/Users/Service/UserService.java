package kani.springsecurity.Domain.Users.Service;

import kani.springsecurity.Domain.Users.Model.Users;
import kani.springsecurity.Domain.Users.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public List<Users> findall(){
        return repo.findAll();
    }
}
