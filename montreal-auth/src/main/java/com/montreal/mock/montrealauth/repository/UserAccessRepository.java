package com.montreal.mock.montrealauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.montreal.mock.montrealauth.model.UserAccess;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {

	Boolean existsByToken(String token);

}
