package com.core.cafe.service.repository;

import com.core.cafe.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
}
