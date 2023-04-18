package com.cedalanavi.project_ijva500_soa_propositions.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.cedalanavi.project_ijva500_soa_propositions.Entities.PropositionVote;
import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PropositionResource {
	
	public Long id;

	@JsonInclude(value  = Include.NON_EMPTY)
	public Long idParentProposition;

	public Long idUser;
    
	public String name;
    
	public String description;

	public String propositionType;

	public LocalDateTime submitDate;

	public Long delay;

	public Long evaluateDelay;
    
	public PropositionStatus status;

	@JsonInclude(value  = Include.NON_EMPTY)
	public List<PropositionResource> amendments;

	@JsonInclude(value  = Include.NON_EMPTY)
	public List<PropositionVote> propositionVotes;
	
}
