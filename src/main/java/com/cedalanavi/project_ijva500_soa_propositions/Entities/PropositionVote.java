package com.cedalanavi.project_ijva500_soa_propositions.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "propositionVote")
public class PropositionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private Long idUser;
    
    @ManyToOne
    @JoinColumn(name="idProposition")
    private Proposition proposition;
    
    private Boolean supported;
    
    private Boolean accepted;
    
    private Boolean rejected;

    private Boolean abstention;

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

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}

	public Boolean getSupported() {
		return supported;
	}

	public void setSupported(Boolean supported) {
		this.supported = supported;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Boolean getRejected() {
		return rejected;
	}

	public void setRejected(Boolean rejected) {
		this.rejected = rejected;
	}

	public Boolean getAbstention() {
		return abstention;
	}

	public void setAbstention(Boolean abstention) {
		this.abstention = abstention;
	}
    
}
