package com.cedalanavi.project_ijva500_soa_propositions.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionUpdateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.VoteCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.Proposition;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.PropositionType;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.PropositionVote;
import com.cedalanavi.project_ijva500_soa_propositions.Repositories.PropositionRepository;
import com.cedalanavi.project_ijva500_soa_propositions.Repositories.PropositionTypeRepository;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionMapper;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionStatus;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.VoteType;

@Service
public class PropositionService {

	PropositionRepository propositionRepository;

	PropositionTypeRepository propositionTypeRepository;
	
	PropositionMapper propositionMapper;
	
	enum PROPOSITION_TYPE {
        PROPOSITION,
        AMENDMENT
    }

	public PropositionService(PropositionRepository propositionRepository, PropositionMapper propositionMapper, PropositionTypeRepository propositionTypeRepository) {
		this.propositionRepository = propositionRepository;
		this.propositionMapper = propositionMapper;
		this.propositionTypeRepository = propositionTypeRepository;
	}

	public List<Proposition> searchPropositions(Long id, Long idProject, String idUser, String type) {
		return propositionRepository.findAllByCustomParams(id, idProject, idUser, type);
	}
	
	public Proposition createProposition(PropositionCreateRequest propositionCreateRequest) {
		Proposition proposition = propositionMapper.toProposition(propositionCreateRequest);
		proposition.setStatus(PropositionStatus.EVALUATION);
		PropositionType propositionType = propositionTypeRepository.findByName(PROPOSITION_TYPE.PROPOSITION.toString()).get();
		proposition.setPropositionType(propositionType);
		
		return propositionRepository.save(proposition);
	}
	
	public Proposition createAmendment(PropositionCreateRequest amendmentCreateRequest) {
		Proposition proposition = propositionRepository.getReferenceById(amendmentCreateRequest.idProposition);
		Proposition amendment = propositionMapper.toProposition(amendmentCreateRequest);
		amendment.setStatus(PropositionStatus.EVALUATION);
		PropositionType propositionType = propositionTypeRepository.findByName(PROPOSITION_TYPE.AMENDMENT.toString()).get();
		amendment.setPropositionType(propositionType);
		amendment.setParentProposition(proposition);
		proposition.getAmendments().add(amendment);
		
		return propositionRepository.save(proposition);
	}
	
	public Proposition update(Long id, PropositionUpdateRequest updateRequest) {
		Proposition proposition = propositionRepository.getReferenceById(id);
		propositionMapper.updatePropositionFromDto(updateRequest, proposition);
		
		return propositionRepository.save(proposition);
	}
	
	public Proposition vote(VoteCreateRequest voteCreateRequest) throws Exception {
		Proposition proposition = propositionRepository.getReferenceById(voteCreateRequest.idProposition);
		VoteType voteType = VoteType.valueOf(voteCreateRequest.voteType);
		if (proposition.getStatus() == PropositionStatus.EVALUATION && voteType != VoteType.SUPPORTED
				|| proposition.getStatus() != PropositionStatus.EVALUATION && voteType == VoteType.SUPPORTED) {
			throw new Exception("Error proposition status and vote type");
		}
		PropositionVote propositionVote = new PropositionVote();
		propositionVote.setIdUser(voteCreateRequest.idUser);
		propositionVote.setProposition(proposition);
		propositionVote.setVoteType(voteType);
		proposition.getPropositionVotes().add(propositionVote);
		
		return propositionRepository.save(proposition);
	}
}
