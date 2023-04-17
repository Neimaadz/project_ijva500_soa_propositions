package com.cedalanavi.project_ijva500_soa_propositions.Entities;

import java.util.Date;
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

import org.springframework.lang.NonNull;

import com.cedalanavi.project_ijva500_soa_propositions.Utils.Status;

@Entity
@Table(name = "proposition")
public class Proposition {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NonNull
    private String name;
    
    @NonNull
    private String description;

    @NonNull
    @ManyToOne
    private Type type;

    @NonNull
    private Date submitDate;

    private Long delay;

    private Long delayEvaluate;
    
    // Proposition state or advancement statut
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Enum<Status> status;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "amendmentId")
    private List<Proposition> amendments;
    
    private Long nbVoteSupported;
    
    private Long nbVoteAccepted;
    
    private Long nbVoteRejected;

    private Long nbVoteAbstention;
    
    private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Long getDelay() {
		return delay;
	}

	public void setDelay(Long delay) {
		this.delay = delay;
	}

	public Long getDelayEvaluate() {
		return delayEvaluate;
	}

	public void setDelayEvaluate(Long delayEvaluate) {
		this.delayEvaluate = delayEvaluate;
	}

	public Enum<Status> getStatus() {
		return status;
	}

	public void setStatus(Enum<Status> status) {
		this.status = status;
	}

	public List<Proposition> getAmendments() {
		return amendments;
	}

	public void setAmendments(List<Proposition> amendments) {
		this.amendments = amendments;
	}

	public Long getNbVoteSupported() {
		return nbVoteSupported;
	}

	public void setNbVoteSupported(Long nbVoteSupported) {
		this.nbVoteSupported = nbVoteSupported;
	}

	public Long getNbVoteAccepted() {
		return nbVoteAccepted;
	}

	public void setNbVoteAccepted(Long nbVoteAccepted) {
		this.nbVoteAccepted = nbVoteAccepted;
	}

	public Long getNbVoteRejected() {
		return nbVoteRejected;
	}

	public void setNbVoteRejected(Long nbVoteRejected) {
		this.nbVoteRejected = nbVoteRejected;
	}

	public Long getNbVoteAbstention() {
		return nbVoteAbstention;
	}

	public void setNbVoteAbstention(Long nbVoteAbstention) {
		this.nbVoteAbstention = nbVoteAbstention;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
}
