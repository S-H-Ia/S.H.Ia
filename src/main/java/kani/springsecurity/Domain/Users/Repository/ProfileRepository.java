package kani.springsecurity.Domain.Users.Repository;

import kani.springsecurity.Domain.Users.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile , Long> {
}
