package com.montreal.mock.montrealauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.montreal.mock.montrealauth.model.PaperPO;

@Repository
public interface PaperRepository extends JpaRepository<PaperPO, Long> {

	public Optional<PaperPO> findByName(String nome);

}
