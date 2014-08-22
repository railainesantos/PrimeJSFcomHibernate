package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idUsuario;
	private Integer tentativas;
	private String nome;
	private String login;
	private String senha;
	private String tipoUsuario;
	private String perguntaSecreta;
	private String respostaSecreta;
	
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<MembrosProjeto> membrosProjetoList = new HashSet<MembrosProjeto>();

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getTentativas() {
		return tentativas;
	}

	public void setTentativas(Integer tentativas) {
		this.tentativas = tentativas;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}


	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	public Collection<MembrosProjeto> getMembrosProjetoList() {
		return membrosProjetoList;
	}


	public String getPerguntaSecreta() {
		return perguntaSecreta;
	}

	public void setPerguntaSecreta(String perguntaSecreta) {
		this.perguntaSecreta = perguntaSecreta;
	}

	public String getRespostaSecreta() {
		return respostaSecreta;
	}

	public void setRespostaSecreta(String respostaSecreta) {
		this.respostaSecreta = respostaSecreta;
	}

	public void setMembrosProjetoList(Collection<MembrosProjeto> membrosProjetoList) {
		this.membrosProjetoList = (Set<MembrosProjeto>) membrosProjetoList;
	}

   public boolean equals(Usuario u) {  
      boolean ret = false;  
		  
	      if (   (this.login.equals(u.getLogin())) && (this.senha.equals(u.getSenha()))   )  
	         ret = true;  
	  
	      return ret;  
		   }  

//	public static void main (String[] args){
//		Usuario u=  new Usuario();
//		u.setIdUsuario(3);
//		u.setDescricao("Consulta");
//		u.setTipoUsuario("Consulta");
//
//		UsuarioController uc = new UsuarioController();
//		uc.salvarUsuario(u);
//		
//	}
}
