package com.cedalanavi.project_ijva500_soa_propositions.Services;

import org.springframework.stereotype.Service;

import com.cedalanavi.project_ijva500_soa_propositions.Repositories.PropositionRepository;

@Service
public class PropositionService {

	PropositionRepository propositionRepository;

	public PropositionService(PropositionRepository propositionRepository) {
		this.propositionRepository = propositionRepository;
	}
	
	
	public getUserProposition() {
		
	}
	
}
