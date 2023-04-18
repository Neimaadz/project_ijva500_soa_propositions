package com.cedalanavi.project_ijva500_soa_propositions.Entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import com.cedalanavi.project_ijva500_soa_propositions.Utils.PropositionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "proposition")
public class Proposition {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private Long idUser;
    
    @NonNull
    private String name;
    
    @NonNull
    private String description;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "idPropositionType")
    @JsonManagedReference
    private PropositionType propositionType;

    @NonNull
    @CreationTimestamp
    private LocalDateTime submitDate;

    private Long delay;

    private Long evaluateDelay;
    
    // Proposition state or advancement statut
    @Enumerated(EnumType.STRING)
    @Column(name="propositionStatus")
    private PropositionStatus status;
    
    @OneToMany(mappedBy = "parentProposition", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Proposition> amendments;
    
    @ManyToOne
    @JoinColumn(name = "idParentProposition")
    @JsonBackReference
    private Proposition parentProposition;

    @OneToMany(mappedBy = "proposition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropositionVote> propositionVotes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PropositionType getPropositionType() {
		return propositionType;
	}

	public void setPropositionType(PropositionType propositionType) {
		this.propositionType = propositionType;
	}

	public LocalDateTime getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDateTime submitDate) {
		this.submitDate = submitDate;
	}

	public Long getDelay() {
		return delay;
	}

	public void setDelay(Long delay) {
		this.delay = delay;
	}

	public Long getEvaluateDelay() {
		return evaluateDelay;
	}

	public void setEvaluateDelay(Long evaluateDelay) {
		this.evaluateDelay = evaluateDelay;
	}

	public PropositionStatus getStatus() {
		return status;
	}

	public void setStatus(PropositionStatus status) {
		this.status = status;
	}

	public List<Proposition> getAmendments() {
		return amendments;
	}

	public void setAmendments(List<Proposition> amendments) {
		this.amendments = amendments;
	}

	public Proposition getParentProposition() {
		return parentProposition;
	}

	public void setParentProposition(Proposition parentProposition) {
		this.parentProposition = parentProposition;
	}

	public List<PropositionVote> getPropositionVotes() {
		return propositionVotes;
	}

	public void setPropositionVotes(List<PropositionVote> propositionVotes) {
		this.propositionVotes = propositionVotes;
	}
    
}
