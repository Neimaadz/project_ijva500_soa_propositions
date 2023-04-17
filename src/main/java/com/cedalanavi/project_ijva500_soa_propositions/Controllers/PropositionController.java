package com.cedalanavi.project_ijva500_soa_propositions.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionResource;
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

	@GetMapping(value = "/search")
	public List<PropositionResource> searchPropositions(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long idProject,
			@RequestParam(required = false) Long idUser,
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
	
	@PostMapping(value = "/amendment")
	public PropositionResource createAmendment(@RequestBody PropositionCreateRequest amendmentCreateRequest) {
		Proposition propositionCreated = propositionService.createAmendment(amendmentCreateRequest);
		return propositionMapper.toPropositionResource(propositionCreated);
	}
}
