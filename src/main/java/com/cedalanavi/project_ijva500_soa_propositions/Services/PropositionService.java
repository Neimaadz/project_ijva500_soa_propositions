package com.cedalanavi.project_ijva500_soa_propositions.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cedalanavi.project_ijva500_soa_propositions.Data.CommentaryCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionUpdateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.VoteCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.Commentary;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.Proposition;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.PropositionType;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.PropositionVote;
import com.cedalanavi.project_ijva500_soa_propositions.Repositories.PropositionRepository;
import com.cedalanavi.project_ijva500_soa_propositions.Repositories.PropositionTypeRepository;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionMapper;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionStatus;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionTypes;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.VoteTypes;

@Service
public class PropositionService {

	PropositionRepository propositionRepository;

	PropositionTypeRepository propositionTypeRepository;
	
	PropositionMapper propositionMapper;

	public PropositionService(PropositionRepository propositionRepository, PropositionMapper propositionMapper, PropositionTypeRepository propositionTypeRepository) {
		this.propositionRepository = propositionRepository;
		this.propositionMapper = propositionMapper;
		this.propositionTypeRepository = propositionTypeRepository;
	}
	
	public Proposition findById(Long id) {
		return propositionRepository.getReferenceById(id);
	}

	public List<Proposition> searchPropositions(Long id, Long idProject, String idUser, String type) {
		return propositionRepository.findAllByCustomParams(id, idProject, idUser, type);
	}
	
	public Proposition createProposition(PropositionCreateRequest propositionCreateRequest) {
		Proposition proposition = propositionMapper.toProposition(propositionCreateRequest);
		proposition.setStatus(PropositionStatus.EVALUATION);
		PropositionType propositionType = propositionTypeRepository.findByName(PropositionTypes.PROPOSITION.toString()).get();
		proposition.setPropositionType(propositionType);
		
		return propositionRepository.save(proposition);
	}
	
	public Proposition createAmendment(PropositionCreateRequest amendmentCreateRequest) {
		Proposition proposition = propositionRepository.getReferenceById(amendmentCreateRequest.idProposition);
		Proposition amendment = propositionMapper.toProposition(amendmentCreateRequest);
		amendment.setStatus(PropositionStatus.EVALUATION);
		PropositionType propositionType = propositionTypeRepository.findByName(PropositionTypes.AMENDMENT.toString()).get();
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
	
	public Proposition vote(Long id, VoteCreateRequest voteCreateRequest) throws Exception {
		Proposition proposition = propositionRepository.getReferenceById(id);
		VoteTypes voteType = VoteTypes.valueOf(voteCreateRequest.voteType);
		
		PropositionVote propositionVote = new PropositionVote();
		propositionVote.setIdUser(voteCreateRequest.idUser);
		propositionVote.setProposition(proposition);
		propositionVote.setVoteType(voteType);
		proposition.getPropositionVotes().add(propositionVote);
		
		return propositionRepository.save(proposition);
	}
	
	public Proposition addCommentary(Long id, CommentaryCreateRequest commentaryCreateRequest) {
		Proposition proposition = propositionRepository.getReferenceById(id);
		
		Commentary commentary = new Commentary();
		commentary.setIdUser(commentaryCreateRequest.idUser);
		commentary.setMessage(commentaryCreateRequest.message);
		commentary.setProposition(proposition);
		proposition.getCommentaries().add(commentary);
		
		return propositionRepository.save(proposition);
	}
}
