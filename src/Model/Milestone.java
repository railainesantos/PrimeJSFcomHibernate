package Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import Controller.MilestoneController;



@Entity
public class Milestone implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idMilestone=1;
	private String nome;
	private Date dataFinal;

	@ManyToOne () 
	private Projeto projeto;
	

	
	public Integer getIdMilestone() {
		return idMilestone;
	}
	public void setIdMilestone(Integer idMilestone) {
		this.idMilestone = idMilestone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome ;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public boolean equals(Milestone milestoneAux) {
			if (this.idMilestone== milestoneAux.getIdMilestone()) {
				return true;
		}
		return false; 
	}


}
