package com.cedalanavi.project_ijva500_soa_propositions.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.cedalanavi.project_ijva500_soa_propositions.Utils.VoteType;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "propositionVote")
public class PropositionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String idUser;
    
    @ManyToOne
    @JoinColumn(name="idProposition")
    @JsonBackReference
    private Proposition proposition;

    @Enumerated(EnumType.STRING)
    @Column(name="voteType")
    private VoteType voteType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}
	
}
