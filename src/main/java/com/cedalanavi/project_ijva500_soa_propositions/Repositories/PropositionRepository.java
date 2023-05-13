package com.cedalanavi.project_ijva500_soa_propositions.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cedalanavi.project_ijva500_soa_propositions.Entities.Proposition;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
	
	@Query("SELECT p FROM Proposition p "
			+ "WHERE (:id is null or p.id = :id) "
			+ "AND (:idProject is null  or p.idProject = :idProject) "
			+ "AND (:idUser is null  or p.idUser = :idUser)"
			+ "AND (:typeName is null  or p.propositionType.name = :typeName)")
	List<Proposition> findAllByCustomParams(
			@Param("id") Long id,
			@Param("idProject") Long idProject,
			@Param("idUser") String idUser,
			@Param("typeName") String type);
}
