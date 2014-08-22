package Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class MembrosProjeto implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idMembrosProjetos;
	
	@ManyToOne (cascade=CascadeType.REFRESH)
	private Projeto projeto;

	@ManyToOne (cascade=CascadeType.REFRESH)
	private Usuario usuario;

	public Integer getIdMembrosProjetos() {
		return idMembrosProjetos;
	}

	public void setIdMembrosProjetos(Integer idMembrosProjetos) {
		this.idMembrosProjetos = idMembrosProjetos;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	

//	public static void main (String []args){
//
//		Atividade a = new Atividade();
//		a.setMilestone(null);
//		
//		AtividadeController ac = new AtividadeController();
//		ac.salvarAtividade(a);
//	}

}
