package com.cedalanavi.project_ijva500_soa_propositions.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cedalanavi.project_ijva500_soa_propositions.Data.CommentaryCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionResource;
import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionUpdateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.VoteCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.Proposition;
import com.cedalanavi.project_ijva500_soa_propositions.Services.PropositionService;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionMapper;

@RestController
@RequestMapping("proposition")
public class PropositionController {

	PropositionService propositionService;
	
	PropositionMapper propositionMapper;

	public PropositionController(PropositionService propositionService, PropositionMapper propositionMapper) {
		this.propositionService = propositionService;
		this.propositionMapper = propositionMapper;
	}
	
	@GetMapping("/{id}")
	public PropositionResource findById(@PathVariable Long id) {
		Proposition proposition = propositionService.findById(id);
		return propositionMapper.toPropositionResource(proposition);
	}

	@GetMapping("/search")
	public List<PropositionResource> searchPropositions(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long idProject,
			@RequestParam(required = false) String idUser,
			@RequestParam(required = false) String type) {
		List<Proposition> propositions = propositionService.searchPropositions(id, idProject, idUser, type);
		List<PropositionResource> propositionResources = new ArrayList<PropositionResource>();
		propositions.forEach(proposition -> {
			propositionResources.add(propositionMapper.toPropositionResource(proposition));
		});
		return propositionResources;
	}
	
	@PostMapping
	public PropositionResource createProposition(@RequestBody PropositionCreateRequest propositionCreateRequest) {
		Proposition propositionCreated = propositionService.createProposition(propositionCreateRequest);
		return propositionMapper.toPropositionResource(propositionCreated);
	}
	
	@PostMapping("/amendment")
	public PropositionResource createAmendment(@RequestBody PropositionCreateRequest amendmentCreateRequest) {
		Proposition propositionCreated = propositionService.createAmendment(amendmentCreateRequest);
		return propositionMapper.toPropositionResource(propositionCreated);
	}
	
	@PutMapping("/update/{id}")
	public PropositionResource updateProposition(@PathVariable Long id, @RequestBody PropositionUpdateRequest updateRequest) {
		Proposition propositionUpdated = propositionService.update(id, updateRequest);
		return propositionMapper.toPropositionResource(propositionUpdated);
	}

	@PostMapping("/vote/{id}")
	public PropositionResource vote(@PathVariable Long id, @RequestBody VoteCreateRequest voteCreateRequest) throws Exception {
		Proposition propositionVoted = propositionService.vote(id, voteCreateRequest);
		return propositionMapper.toPropositionResource(propositionVoted);
	}
	
	@PostMapping("/commentary/add/{id}")
	public PropositionResource addCommentary(@PathVariable Long id, @RequestBody CommentaryCreateRequest commentaryCreateRequest) throws Exception {
		Proposition propositionVoted = propositionService.addCommentary(id, commentaryCreateRequest);
		return propositionMapper.toPropositionResource(propositionVoted);
	}
}
