package com.affiliatedLink.alCore.repository;

import com.affiliatedLink.alCore.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Integer> {
    @Query(value = """
            select t from JwtToken t inner join Consumer u\s
            on t.user.id = u.id\s
            where u.id = :id and (t.expired = false and t.revoked = false)\s
            """)
    List<JwtToken> findAllValidTokenByUser(UUID id);

    Optional<JwtToken> findByToken(String token);
}
