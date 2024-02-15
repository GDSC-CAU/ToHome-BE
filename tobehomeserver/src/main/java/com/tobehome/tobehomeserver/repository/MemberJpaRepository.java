package com.tobehome.tobehomeserver.repository;

import com.tobehome.tobehomeserver.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    boolean existsByNickname(String nickname);
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Member> findByNickname(String nickname);

}
