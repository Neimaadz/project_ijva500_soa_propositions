package com.cedalanavi.project_ijva500_soa_propositions.Utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionCreateRequest;
import com.cedalanavi.project_ijva500_soa_propositions.Data.PropositionResource;
import com.cedalanavi.project_ijva500_soa_propositions.Entities.Proposition;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PropositionMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "propositionType", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "submitDate", ignore = true)
	@Mapping(target = "amendments", ignore = true)
	@Mapping(target = "propositionVotes", ignore = true)
	@Mapping(target = "parentProposition", ignore = true)
	public Proposition toProposition(PropositionCreateRequest propositionCreateRequest);

	@Mapping(target = "submitDate", ignore = true)
	public Proposition update(Proposition proposition);
	
	@Mapping(target = "propositionType", source = "propositionType.name")
	@Mapping(target = "idParentProposition", source = "parentProposition.id")
	public PropositionResource toPropositionResource(Proposition proposition);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "propositionType", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "submitDate", ignore = true)
	@Mapping(target = "amendments", ignore = true)
	@Mapping(target = "propositionVotes", ignore = true)
	@Mapping(target = "parentProposition", ignore = true)
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePropositionFromDto(PropositionCreateRequest req, @MappingTarget Proposition proposition);
}
