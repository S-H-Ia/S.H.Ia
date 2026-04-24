package kani.springsecurity.Domain.Embeding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbedingRepository extends JpaRepository<Embeding,Long> { }
