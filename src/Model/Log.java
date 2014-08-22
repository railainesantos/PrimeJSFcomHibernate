package Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Log implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idLog=1;
	private String usuario;
	private String acao;
	private String tabela;
	private String dadoTabela;
	private Date dataRegistro;

	@ManyToOne(cascade=CascadeType.ALL) 
	private Projeto projeto;
	
	public Integer getIdLog() {
		return idLog;
	}
	public void setIdLog(Integer idLog) {
		this.idLog = idLog;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public String getDadoTabela() {
		return dadoTabela;
	}
	public void setDadoTabela(String dadoTabela) {
		this.dadoTabela = dadoTabela;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	
	

}
