package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Dao.MembrosProjetoDaoImp;



@Entity
public class Projeto implements Serializable {
	
	
	@Id
	@GeneratedValue
	private Integer idProjeto;
	private String nome;
	private String descricao;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private Set<MembrosProjeto> membrosProjetoList = new HashSet<MembrosProjeto>();

    private Date dataInicial;
	private Date dataFinal;
	private String status;
	
	@ManyToOne (cascade=CascadeType.ALL)
	private Usuario usuario;

	
	public Projeto() {
	
	}
	
	public Integer getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome= nome;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Set<MembrosProjeto> getMembrosProjetoList() {
		return membrosProjetoList;
	}

	public void setMembrosProjetoList(Set<MembrosProjeto> membrosProjetoList) {
		this.membrosProjetoList = membrosProjetoList;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public static void main (String []args){
		Projeto p = new Projeto();
		p.setIdProjeto(10);
    	List<MembrosProjeto> listaMembrosProjetoAux = new MembrosProjetoDaoImp().listByProjeto(p);
        List<Projeto> listaNovaAux = new ArrayList<Projeto>() ;
        Projeto e = new Projeto();

    	for (MembrosProjeto membrosProjetosAux : listaMembrosProjetoAux){
    		if  (membrosProjetosAux.getProjeto().getIdProjeto().equals(10)){
    			System.out.println("MembroProjetoAux Id: "+membrosProjetosAux.getIdMembrosProjetos());
    			e=membrosProjetosAux.getProjeto();
    			listaNovaAux.add(e);
    		}
    	}

    	for (Projeto listaNovaAuxAux : listaNovaAux){
    		if  (listaNovaAuxAux.getIdProjeto().equals(10)){
    			System.out.println("listaNovaAuxAux Id: "+listaNovaAuxAux.getIdProjeto());
    		}
    	}

//        
//        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
//    				.getExternalContext().getSessionMap().get("usuarioLogado");


//        for (MembrosProjeto membrosProjetoFor : listaMembrosAux) { 
//            	if ( membrosProjetoFor.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario()) ) {
//            		 
//            		System.out.println("Entrou no IF");
//            		listaNovaAux.add(membrosProjetoFor.getProjeto()); 
//            		System.out.println("Entrou no IF");
//            }
//       	}
//        listaProjetos = new ListDataModel(listaNovaAux);
//
//        return listaProjetos;
	}



}
