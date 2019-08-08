package com.montreal.mock.montrealapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.montreal.mock.montrealapi.model.UserPO;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {

	Optional<UserPO> findUsuarioById(Long id);

	Optional<UserPO> findByEmail(String email);

	Optional<UserPO> findByNameOrEmail(String nome, String email);

	List<UserPO> findByIdIn(List<Long> userIds);

	Optional<UserPO> findByName(String nome);

	Boolean existsByEmail(String email);

	Optional<Long> countBy();
}