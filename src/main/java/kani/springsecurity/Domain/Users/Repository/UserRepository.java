package kani.springsecurity.Domain.Users.Repository;

import kani.springsecurity.Domain.Users.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
