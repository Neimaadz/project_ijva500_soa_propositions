package com.cedalanavi.project_ijva500_soa_propositions.Data;

import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PropositionUpdateRequest {
	
	public String name;
	
	public String description;

	@JsonInclude(value = Include.NON_EMPTY)
	public PropositionStatus status;

	public Long delay;

	public Long evaluateDelay;
}
