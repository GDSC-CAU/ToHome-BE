package com.tobehome.tobehomeserver.repository;

import com.tobehome.tobehomeserver.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    Member findByNickname(String nickname);
}
