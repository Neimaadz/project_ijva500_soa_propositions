package com.cedalanavi.project_ijva500_soa_propositions.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cedalanavi.project_ijva500_soa_propositions.Entities.Proposition;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.PropositionType;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {

	Optional<Proposition> findByIdAndPropositionType(Long id, PropositionType propositionType);
	
	List<Proposition> findAllByIdUserAndPropositionType(Long idUser, PropositionType propositionType);
	
	List<Proposition> findAllByIdUser(Long idUser);
	
	@Query("SELECT p FROM Proposition p WHERE (:id is null or p.id = :id) "
			+ "AND (:typeName is null  or p.propositionType.name = :typeName) "
			+ "AND (:idUser is null  or p.idUser = :idUser)")
	List<Proposition> findAllByCustomParams(
			@Param("id") Long id,
			@Param("idUser") Long idUser,
			@Param("typeName") String type);
}
