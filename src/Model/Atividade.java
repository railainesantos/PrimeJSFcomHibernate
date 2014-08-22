package Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import Controller.AtividadeController;



@Entity
public class Atividade implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idAtividade=1000;
	private String nome;
	private Date dataInicial;
	private Date dataFinal;
	private String status;
	
	@ManyToOne (cascade=CascadeType.ALL)
	private Milestone milestone;
	
	@ManyToOne (cascade=CascadeType.ALL)
	private Usuario usuario;
	
	public Integer getIdAtividade() {
		return idAtividade;
	}
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome ;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status= status;
	}
	
	public Milestone getMilestone() {
		return milestone;
	}
	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static void main (String []args){
		Atividade a = new Atividade();
		a.setNome("Domingo");
		a.setDataInicial(new Date());
		a.setDataFinal(new Date());
		a.setStatus("Ativo");
		a.setMilestone(new Milestone());
		a.setMilestone(null);
		
		Usuario u = new Usuario();
		u.setIdUsuario(1);
		
		a.setUsuario(u);

		AtividadeController ac = new AtividadeController();
		ac.salvarAtividade(a);
	}
}
