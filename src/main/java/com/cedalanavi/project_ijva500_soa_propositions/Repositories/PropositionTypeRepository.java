package com.cedalanavi.project_ijva500_soa_propositions.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cedalanavi.project_ijva500_soa_propositions.Entities.PropositionType;

public interface PropositionTypeRepository extends JpaRepository<PropositionType, Long> {
	
	Optional<PropositionType> findByName(String name);

}
