package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import Dao.AtividadeDao;
import Dao.AtividadeDaoImp;
import Dao.EventoDao;
import Dao.EventoDaoImp;
import Dao.LogDao;
import Dao.LogDaoImp;
import Dao.MembrosProjetoDao;
import Dao.MembrosProjetoDaoImp;
import Dao.MilestoneDao;
import Dao.MilestoneDaoImp;
import Dao.ProjetoDao;
import Dao.ProjetoDaoImp;
import Dao.UsuarioDao;
import Dao.UsuarioDaoImp;
import Model.Atividade;
import Model.Evento;
import Model.Log;
import Model.MembrosProjeto;
import Model.Milestone;
import Model.Projeto;
import Model.Usuario;
import Msg.MsgAtividade;
import Msg.MsgEvento;
import Msg.MsgMilestone;
import Msg.MsgProjeto;
import Msg.MsgUsuario;



@ManagedBean
@SessionScoped
public class ProjetoController {

	
	private Log log= new Log();
    private DataModel listaLogs;
	
    private Projeto projeto;
    private DataModel listaProjetos;
    private MsgProjeto msgProjeto;
    
    private Milestone milestone = new Milestone();
    private DataModel listaMilestones;
    private MsgMilestone msgMilestone;
  
    private Atividade atividade;
    private DataModel listaAtividades;
    private Usuario responsavelAtividade;
    private MsgAtividade msgAtividade;

    private Usuario usuario = new Usuario();
    private Usuario usuarioView = new Usuario();

    private Usuario usuarioLogado= (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");

    private MsgUsuario msgUsuario;
    
    private DataModel listaUsuarios;
    private DataModel listaMembros;
    
    private MembrosProjeto membroProjetos;

    private Evento evento;
    private DataModel listaEventos;
    private MsgEvento msgEvento;
    
    /*
     * Action Projeto
     */
        
    public DataModel getListarProjetos() {
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ){
	    	List<Projeto> lista = new ProjetoDaoImp().listTodos();
	        
	        listaProjetos = new ListDataModel(lista);
	        return listaProjetos;
	    	
	    }else {
	    	List<Projeto> lista = new ProjetoDaoImp().list();	        
	        listaProjetos = new ListDataModel(lista);
	        return listaProjetos;
	    	
	    }
     
    }
    
    
    
    
    private List<Projeto> filteredProjetos;  
    
  
  
    public List<Projeto> getFilteredProjetos() {  
        return filteredProjetos;  
    }  
  
    public void setFilteredProjetos(List<Projeto> filteredProjetos) {  
        this.filteredProjetos = filteredProjetos;  
    }  
    
    
    
    
	
	//Lista os projetos do Usuario Logado
	public DataModel getListarProjetosUsuario() {
        usuario = (Usuario) FacesContext.getCurrentInstance()
    				.getExternalContext().getSessionMap().get("usuarioLogado");
//pega os itensMembros do usuario logado;


        List<MembrosProjeto> listaMembrosProjetoAux = new MembrosProjetoDaoImp().listByUsuario(usuario);
        List<Projeto> listaNovaAux = new ArrayList<Projeto>() ;
        Projeto e = new Projeto();

    	for (MembrosProjeto membrosProjetosAux : listaMembrosProjetoAux){
    		if  (membrosProjetosAux.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())){
    			System.out.println();
    			e=membrosProjetosAux.getProjeto();
    			System.out.println("membrosProjetosAux.getProjeto"+membrosProjetosAux.getProjeto().getIdProjeto());

    			listaNovaAux.add(e);
    		}
    	}
		
		listaProjetos = new ListDataModel(listaNovaAux);

        return listaProjetos;
    }



	
    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    public String prepararAdicionarProjeto(){
        this.projeto = new Projeto();
        return "cadastrarProjeto";
    }

    public String prepararAlterarProjeto(){
        projeto = (Projeto)(listaProjetos.getRowData());
        System.out.println("Nome do Projeto"+projeto.getNome());
        return "gerenciarProjeto";
}

    public String prepararExcluirProjeto(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
    				.getExternalContext().getSessionMap().get("usuarioLogado");

        if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
        		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){

	    	System.out.println("Entrou preparaaarrrrrrrr");
	        projeto = (Projeto)(listaProjetos.getRowData());
	        System.out.println("Nome do Projeto"+projeto.getNome());
	        return "";
	     }else {
	    	 MsgProjeto.msgNaoPossuiDireito();
	    	 return "";
	     }
        
    }
	

    public void adicionarProjeto(){
    	String strAux = verificaNomeProjeto();
		System.out.println("StrAyx:"+strAux);
	    	if (strAux.equals("sim")) {
	    		MsgProjeto.msgProjetoExiste();
	    	}else if (strAux.equals("nao")){
	            ProjetoDao dao = new ProjetoDaoImp();
	            projeto.setUsuario(new Usuario());
	            Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
	    				.getExternalContext().getSessionMap().get("usuarioLogado");
	            projeto.setUsuario(usuarioAux );
	            dao.save(projeto);
				salvaGerenteComoMembro(projeto,usuarioAux);
	            adicionarLog("O Usuario "+usuarioAux.getNome(), "adicionou" ," o registro "+projeto.getNome(),"na tabela Projeto",  new Date());
	            projeto = new Projeto();
	            MsgProjeto.msgSalva();
	    	}
    }    	
    

    public void alterarProjeto(){

        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){
					String strAux = verificaDataFinalProjeto();
					System.out.println("StrAyx:"+strAux);
				    	if (strAux.equals("sim")) {
				    		MsgProjeto.msgProjetoDataInvalida();
				    	}else if (verificaStatusProjeto().equals("sim")) {
				    		MsgProjeto.msgStatus();
				    	}else if (strAux.equals("nao")){
				        	ProjetoDao dao = new ProjetoDaoImp();
				            adicionarLog("O Usuario "+usuarioAux.getNome(), "alterou" ," o registro "+projeto.getNome(),"na tabela Projeto",  new Date());
							projeto.setUsuario(new Usuario());
				            projeto.setUsuario(usuario);
							salvaGerenteComoMembro(projeto,usuario);
							dao.update(projeto);
				            milestone = new Milestone();
				            atividade = new Atividade();
				            MsgProjeto.msgAltera();
	    	}

    }else {
    		MsgProjeto.msgNaoPossuiDireito();
    	}    	

    }

    public void excluirProjeto(){
    	if (verificaExisteMilestoneProjeto().equals("sim")) {
    		MsgProjeto.msgProjetoNaoPodeExcluir();
    	}else {
	    	ProjetoDao dao = new ProjetoDaoImp();
            Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
    				.getExternalContext().getSessionMap().get("usuarioLogado");
            adicionarLog("O Usuario "+usuarioAux.getNome(), "excluiu" ," o registro "+projeto.getNome(),"na tabela Projeto",  new Date());
			eliminaMilestonesProjeto();
			eliminaMembrosProjeto();
	        dao.remove(projeto);
	        MsgProjeto.msgExclui();
	        projeto = new Projeto();
    	}
    }
    
    
  //Verifica se a data final do milestone nao eh superior a do proprio milestone salvo anteriormente    
  	public String verificaStatusProjeto() {
          List<Projeto> lista = new ProjetoDaoImp().listTodos();
          for (Projeto projetoFor : lista) {      
  	        	if ( (projeto.getIdProjeto().equals(projetoFor.getIdProjeto())) && (
  	        			(projetoFor.getStatus().equals("Finalizado") ) ||
  	        			(projetoFor.getStatus().equals("Cancelado")) )){
          				return "sim";
  					}	
  	        	}
  		return "nao";
      }

  	public String verificaStatusAtividade() {
        List<Atividade> lista = new AtividadeDaoImp().listTodos();
        for (Atividade atividadeFor : lista) {      
	        	if ( (atividade.getIdAtividade().equals(atividadeFor.getIdAtividade())) && (
	        			(atividadeFor.getStatus().equals("Finalizada") ) )){
        				return "sim";
					}	
	        	}
		return "nao";
    }
  	
//Verifica se nao existe ja um nome cadastrado para tal projeto
    public String verificaNomeProjeto() {
        List<Projeto> lista = new ProjetoDaoImp().list();
        for (Projeto projetoFor : lista) {      
	        	if ( (projeto.getNome().equals(projetoFor.getNome()))  )  {
	        				System.out.println("Usuario 1 if : " + projetoFor.getNome());
        				return "sim";
					}	
	        	}
		return "nao";
    }

    
//  Faz a verificacao no view Alterar,se a data Nova nao eh inferior a atual salva
    public String verificaDataFinalProjeto() {
        List<Projeto> lista = new ProjetoDaoImp().list();
        for (Projeto projetoFor : lista) {      
	        	if ( (projeto.getIdProjeto().equals (projetoFor.getIdProjeto())) && 
	        			(projeto.getDataFinal().before(projetoFor.getDataFinal())))  {
	        				
	        				System.out.println("Projeto 1 if : " + projetoFor.getNome());
        				return "sim";
					}	
	        	}
		return "nao";
    }
    
// Verifica se existe algumm milestone num determinado projeto se verdadeiro retorna sim   
    public String verificaExisteMilestoneProjeto() {
        List<Milestone> lista = new MilestoneDaoImp().listTodos();
        for (Milestone milestoneFor : lista) {      
	        	if ( (projeto.getIdProjeto().equals(milestoneFor.getProjeto().getIdProjeto()))  )  {
	        				System.out.println("Milestone 1 if : " + milestoneFor.getIdMilestone());
        				return "sim";
					}	
	        	}
		return "nao";
    }
    
    public String retornar(){
        return "consultarProjeto";
    }

    //metodo para salvar na unha
    public String salvarProjeto(Projeto projeto){
        ProjetoDao dao = new ProjetoDaoImp();
        dao.save(projeto);
        return "consultarProjeto";
    }
    
    /*
     * Actions Milestone
     */
    
    public DataModel getListarMilestones() {
        List<Milestone> lista = new MilestoneDaoImp().list(projeto);
        listaMilestones = new ListDataModel(lista);
        return listaMilestones;
    }
    
    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
    	this.milestone = milestone;
    }

    
    public void prepararAdicionarMilestone(){
        milestone = new Milestone();
    }

    public void prepararAlterarMilestone(){
        milestone = (Milestone)(listaMilestones.getRowData());
    }

    public void prepararExcluirMilestone(){
        milestone = (Milestone)(listaMilestones.getRowData());
    }


    
    
    public void adicionarMilestone(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){
    		
			if (verificaDataFinaMilestoneProjeto().equals("sim")){
				MsgMilestone.msgMilestoneVerificaDataMilestone();
			}else {
		        MilestoneDao dao = new MilestoneDaoImp();
		        milestone.setProjeto(projeto);
		        dao.save(milestone);
		        adicionarLog("O Usuario "+usuarioAux.getNome(), "adicionou" ," o registro "+milestone.getNome(),"na tabela Milestone",  new Date());
		        milestone = new Milestone();
		        MsgMilestone.msgSalva();
		        }
		 }else {
			   MsgMilestone.msgNaoPossuiDireito();
		 }
}

    public void alterarMilestone(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){

		    	if (verificaDataFinaMilestoneProjeto().equals("sim")){
		    		MsgMilestone.msgMilestoneVerificaDataMilestone();
		    	}else if (verificaDataFinaMilestoneProprio().equals("sim")) {
		    		MsgMilestone.msgMilestoneVerificaDataMilestoneProprio();
		    	}else       {
		        MilestoneDao dao = new MilestoneDaoImp();
		        dao.update(milestone);
		        adicionarLog("O Usuario "+usuarioAux.getNome(), "alterou" ," o registro "+milestone.getNome(),"na tabela Milestone",  new Date());
		        milestone = new Milestone();
		        List<Milestone> lista = new MilestoneDaoImp().list(projeto);
		        listaMilestones = new ListDataModel(lista);
		        MsgMilestone.msgAltera();
		    	}
	    }else {
	    		MsgMilestone.msgNaoPossuiDireito();
	    	}
	    
}
    public void excluirMilestone(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){

		    	if (verificaExisteAtividadeMilestone().equals("sim")) {
		    		MsgMilestone.msgMilestoneNaoPodeExcluir();
		    		System.out.println("IF SIM");
		    	}else {
		    		System.out.println("Milestonenenen:"+milestone.getNome());
		    		MilestoneDao dao = new MilestoneDaoImp();
					eliminaAtividadesMilestone(milestone);
			        dao.remove(milestone);
		            adicionarLog("O Usuario "+usuarioAux.getNome(), "excluiu" ," o registro "+milestone.getNome(),"na tabela Milestone",  new Date());
			        MsgMilestone.msgExclui();
			        milestone = new Milestone();
		    		System.out.println("IF NAO");
		    		}
    	}else {
    		MsgMilestone.msgNaoPossuiDireito();
    	}
    }
//exclui TODOS os milestones de um determinado projeto
 	public void eliminaMilestonesProjeto() {
        List<Milestone> lista = new MilestoneDaoImp().list(projeto);
        for (Milestone milestoneFor : lista) {   
	    		MilestoneDao dao = new MilestoneDaoImp();
				eliminaAtividadesMilestone(milestoneFor);
	    		milestoneFor.setProjeto(null);
	    		dao.update(milestoneFor);
		        dao.remove(milestoneFor);		
    	    }
    }
//Verifica se a data final do milestone nao eh superior a do projeto    
    public String verificaDataFinaMilestoneProjeto() {
    	if (    milestone.getDataFinal().after(projeto.getDataFinal()) ||
    			milestone.getDataFinal().before(projeto.getDataInicial())
    			){
    				System.out.println("Milestoneif : " + milestone.getNome());
				return "sim";
			}	
		return "nao";
    }

//Verifica se a data final do milestone nao eh superior a do proprio milestone salvo anteriormente    
	public String verificaDataFinaMilestoneProprio() {
        List<Milestone> lista = new MilestoneDaoImp().listTodos();
        for (Milestone milestoneFor : lista) {      
	        	if ( (milestone.getIdMilestone().equals(milestoneFor.getIdMilestone())) &&  
	        			(milestone.getDataFinal().before(milestoneFor.getDataFinal()))  
	        			)  {
	        				System.out.println("Milestone 1 if : " + milestoneFor.getNome());
        				return "sim";
					}	
	        	}
		return "nao";
    }

 
    
    /*
     * Actions Activity 
     */
    
    public DataModel getListarAtividades() {
        List<Atividade> lista = new AtividadeDaoImp().list(milestone);
        listaAtividades = new ListDataModel(lista);
        return listaAtividades;
	}
	
	public DataModel getListarAtividadesTab() {
	    List<Atividade> lista = new AtividadeDaoImp().listTab(milestone);
	    listaAtividades = new ListDataModel(lista);
	    return listaAtividades;
	}
	
	public Atividade getAtividade() {
	    return atividade;
	}
	
	public void setAtividade(Atividade atividade) {
	    this.atividade = atividade;
	}

	public void finalizarAtividade(){
		atividade = (Atividade)(listaAtividades.getRowData());
	     Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ||
	    		(atividade.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ) {
				
				AtividadeDao dao = new AtividadeDaoImp();
				atividade.setStatus("Finalizado");
			    dao.update(atividade);
		
		         adicionarLog("O Usuario "+usuarioAux.getNome(), "alterou" ," o registro "+atividade.getNome(),"na tabela Atividade",  new Date());
		
			    atividade = new Atividade();
			    List<Atividade> lista = new AtividadeDaoImp().listTab(milestone);
			    listaAtividades = new ListDataModel(lista);
			    MsgAtividade.msgAltera();	

		}else {
				 MsgAtividade.msgNaoPossuiDireito();
		}
	
	}

	public void prepararAdicionarAtividade(){
	    atividade = new Atividade();
	    atividade.setUsuario(new Usuario()) ;
	    usuario = new Usuario();
	}
	public void prepararAlterarAtividade(){
	    atividade = (Atividade)(listaAtividades.getRowData());
	}

	public void prepararExcluirAtividade(){
		atividade = (Atividade)(listaAtividades.getRowData());
    }

 //inclui uma atividade Ja Cadastrado num Milestone
	public void prepararIncluirAtividade(){
	     Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ||
	    		(atividade.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){
						atividade = (Atividade)(listaAtividades.getRowData());
						if (verificaDataFinalAtividadeMilestonePrepararIncluir(atividade).equals("sim")){
							MsgAtividade.msgDataInvalidaAtividadeMilestone();
						}else {
						 atividade.setMilestone(milestone);
						 AtividadeDao dao = new AtividadeDaoImp();
						 dao.update(atividade);
				   
						 adicionarLog("O Usuario "+usuarioAux.getNome(), "incluiu" ," o registro "+atividade.getNome(),"na tabela Milestone",  new Date());

						 List<Atividade> lista = new AtividadeDaoImp().listTab(milestone);
						 listaAtividades = new ListDataModel(lista);
						 MsgAtividade.msgAtividadeIncluida();
						 }
		 }else{
		 MsgAtividade.msgNaoPossuiDireito();	
	 }
}
	
	
	//CRUD DA TAB VIEW	
	
	public void adicionarAtividadeTab(){
		if (verificaNomeAtividade().equals("sim") ) {
    		MsgAtividade.msgAtividadeExiste();
		}else if (verificaDataInicialAtividadeProjeto().equals("sim")){
			MsgAtividade.msgDataInvalidaAtividadeProjeto();
    	}else {
    		AtividadeDao dao = new AtividadeDaoImp();
    	    atividade.setMilestone(new Milestone());
    	    atividade.setMilestone(null);
    	    atividade.setUsuario(new Usuario());
    	    atividade.setUsuario(usuario);
            Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
     				.getExternalContext().getSessionMap().get("usuarioLogado");
             adicionarLog("O Usuario "+usuarioAux.getNome(), "adicionou" ," o registro "+atividade.getNome(),"na tabela Atividade",  new Date());
    	    dao.save(atividade);
    	    atividade = new Atividade();
            MsgAtividade.msgSalva();
    	}
	}
	
	

//CRUD DA DIALOG MILESTONE	
    public void adicionarAtividade(){ 
	     	if (verificaNomeAtividade().equals("sim") ) {
	    		MsgAtividade.msgAtividadeExiste();
	    	}else if (verificaDataFinalAtividadeMilestone().equals("sim")){
    			MsgAtividade.msgDataInvalidaAtividadeMilestone();
    		}else if (verificaDataInicialAtividadeProjeto().equals("sim")) {
    			MsgAtividade.msgDataInvalidaAtividadeMilestone();
    		} else {
	    	    AtividadeDao dao = new AtividadeDaoImp();
	    	    atividade.setMilestone(new Milestone());
	    	    atividade.setUsuario(new Usuario());
	     	    atividade.setUsuario(usuario);
				atividade.setMilestone(milestone);
	            Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
	     				.getExternalContext().getSessionMap().get("usuarioLogado");
	             adicionarLog("O Usuario "+usuarioAux.getNome(), "adicionou" ," o registro "+atividade.getNome(),"na tabela Atividade",  new Date());

	    	    dao.save(atividade);
	    	    atividade = new Atividade();
	            MsgAtividade.msgSalva();
	    	}
    }    

//CRUD DA TAB VIEW	
	public void alterarAtividadeTab(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ||
	    		(atividade.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){

			    if (verificaDataInicialAtividadeProjeto().equals("sim")) {
					MsgAtividade.msgDataInvalidaAtividadeMilestone();
		    	}else if (verificaStatusAtividade().equals("sim")) {
		    		MsgAtividade.msgStatus();
		    	}else {
			    AtividadeDao dao = new AtividadeDaoImp();
			    atividade.setUsuario(usuario);
			    dao.update(atividade);
		
		         adicionarLog("O Usuario "+usuarioAux.getNome(), "alterou" ," o registro "+atividade.getNome(),"na tabela Atividade",  new Date());
		
			    atividade = new Atividade();
			    List<Atividade> lista = new AtividadeDaoImp().listTab(milestone);
			    listaAtividades = new ListDataModel(lista);
			    MsgAtividade.msgAltera();	
			    }
		}else {
		       MsgAtividade.msgNaoPossuiDireito();	  
		}
}	
	//CRUD DA DIALOG MILESTONE	
	public void alterarAtividade(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ||
	    		(atividade.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){
				if (verificaDataInicialAtividadeProjeto().equals("sim")) {
					MsgAtividade.msgDataInvalidaAtividadeMilestone();
		    	}else if (verificaStatusAtividade().equals("sim")) {
		    		MsgAtividade.msgStatus();
			 	}else if (verificaDataFinalAtividadeMilestone().equals("sim")){
					MsgAtividade.msgDataInvalidaAtividadeMilestone();
				}else {
			    AtividadeDao dao = new AtividadeDaoImp();
			    dao.update(atividade);
		         adicionarLog("O Usuario "+usuarioAux.getNome(), "alterou" ," o registro "+atividade.getNome(),"na tabela Atividade",  new Date());
		
			    atividade = new Atividade();
			    List<Atividade> lista = new AtividadeDaoImp().list(milestone);
			    listaAtividades = new ListDataModel(lista);
			    MsgAtividade.msgAltera();
		    	}
	    }else {
	    	   MsgAtividade.msgNaoPossuiDireito();
	    }
}	
	
   public void excluirAtividade(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ||
	    		(atividade.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){

	    		AtividadeDao dao = new AtividadeDaoImp();
	    		atividade.setUsuario(null);
	    		atividade.setMilestone(null);
	    		dao.update(atividade);
		        dao.remove(atividade);
		        MsgAtividade.msgExclui();
		        atividade = new Atividade();
    	}else {
    			MsgAtividade.msgNaoPossuiDireito();
    	}
    }		

// Verifica se existe alguma atividade com o nome especiicado
	public String verificaNomeAtividade() {
        List<Atividade> lista = new AtividadeDaoImp().listTodos();
        for (Atividade atividadeFor : lista) {      
	        	if (  (atividade.getNome().equals(atividadeFor.getNome()))  )  {
	        				System.out.println("Usuario 1 if : " + atividadeFor.getNome());
        				return "sim";
				} 	
	        	}
		return "nao";
    }
// Elimina TODAS as atividades de um determinado milestone
	public void eliminaAtividadesMilestone(Milestone milestone) {
        List<Atividade> lista = new AtividadeDaoImp().list(milestone);
        for (Atividade atividadeFor : lista) {   
	    		AtividadeDao dao = new AtividadeDaoImp();
	    		atividadeFor.setUsuario(null);
	    		atividadeFor.setMilestone(null);
	    		dao.update(atividadeFor);
		        dao.remove(atividadeFor);		
    	    }
    }

// Verifica se existe a data inicial da atividade nao eh maior que a do milestone PrepararIncluir
	public String verificaDataFinalAtividadeMilestonePrepararIncluir(Atividade atividadeAux) {
	        List<Milestone> lista = new MilestoneDaoImp().list(projeto);
	        for (Milestone milestoneFor : lista) {      
				System.out.println("Entrou For: " + milestoneFor.getNome());
		        	if (   (atividadeAux.getDataFinal().after(milestoneFor.getDataFinal()))  )  {
	        				return "sim";
					} 	
		     }
			return "nao";
	    }
	
// Verifica se existe a data inicial da atividade nao eh maior que a do milestone
    public String verificaDataFinalAtividadeMilestone() {
	        	if (  (atividade.getDataFinal().after(milestone.getDataFinal()))){
        				return "sim";
					}	
	        	
		return "nao";
    }
    
 // Verifica se existe a data inicial da atividade nao eh maior que a do projeto
    public String verificaDataInicialAtividadeProjeto() {
	        	if ( (
	        			 (atividade.getDataInicial().before(projeto.getDataInicial())) || 
	        			 (atividade.getDataInicial().after(projeto.getDataFinal()))  || 
	        			 (atividade.getDataFinal().after(projeto.getDataFinal())) || 
	        			 (atividade.getDataFinal().before(projeto.getDataInicial()))
	        			)
	        			)  {
	        				System.out.println("Atividade if : " + atividade.getNome());
        				return "sim";
	        	}
		return "nao";
    }

    // Verifica se existe algumm atividade num determinado milestone se verdadeiro retorna sim   
    public String verificaExisteAtividadeMilestone() {
        List<Atividade> lista = new AtividadeDaoImp().listTodos();
        for (Atividade atividadeFor : lista) {
        	
	        if (atividadeFor.getMilestone()!=null) {
	        	if ( 
	        			(milestone.getIdMilestone().equals(atividadeFor.getMilestone().getIdMilestone()))  
	        			)  {
	        				System.out.println("IF SIMMM : ") ;
        				return "sim";
					}	
	        	}

	        	
	        }
        System.out.println("IF NAOO : ") ;
		return "nao";
    }
	
    /*
     * Actions Membros
     */
	 //lista TODOS os Membros do Projeto   
    
    
	public DataModel getListarMembrosProjeto() {
        usuario = (Usuario) FacesContext.getCurrentInstance()
    				.getExternalContext().getSessionMap().get("usuarioLogado");

//pego todo os ItemMembros do projeto de um determinado projeto
        List<MembrosProjeto> listaMembrosProjetoAux = new MembrosProjetoDaoImp().listByProjeto(projeto);
        List<Usuario> listaNovaAux = new ArrayList<Usuario>() ;
        Usuario e = new Usuario();
        

    	for (MembrosProjeto membrosProjetosAux : listaMembrosProjetoAux){
    		if  (membrosProjetosAux.getProjeto().getIdProjeto().equals(projeto.getIdProjeto())){
    			System.out.println();
    			e=membrosProjetosAux.getUsuario();
    			System.out.println("membrosProjetosAux.getUsuario"+membrosProjetosAux.getUsuario().getIdUsuario());
    			listaNovaAux.add(e);
    		}
    	}
		
		listaMembros= new ListDataModel(listaNovaAux);

        return listaMembros;
}

        
	
//lista os Usuarios Fora do Projeto
	public DataModel getListarUsuariosForaProjeto() {

        List<Usuario> listaUsuariosAux = new UsuarioDaoImp().list();

        List<Usuario> listaNovaAux = new ArrayList<Usuario>() ;
    	
    	for (Usuario usuarioForListaNovaAux : listaUsuariosAux){
    		
    		if (existeUsuarioMembros(usuarioForListaNovaAux)==false){
    			listaNovaAux.add(usuarioForListaNovaAux);
    		}else {
    			System.out.println("Entrou no ELSE IF !!");
    		}
    	}
   	
    	listaMembros= new ListDataModel(listaNovaAux);

        return listaMembros;
}
	
public Boolean existeUsuarioMembros(Usuario usuarioAux){
	List<MembrosProjeto> membrosProjeto = new MembrosProjetoDaoImp().listByProjeto(projeto);
	Boolean bAux=false;
	Boolean b=false;
	
	for (MembrosProjeto membroProjetoFor: membrosProjeto){
		if ( (membroProjetoFor.getUsuario().equals(usuarioAux)) && (b==false)){
			b=true;
			bAux=false;
		}
	}
	
	return b;
}


	//inclui um gerente num projeto jah cadastrado
		 public void prepararIncluirGerente(){
	     usuario = (Usuario)(listaUsuarios.getRowData());
 }
		 
		 
 //inclui um responsavel numa atividade 
	   public void prepararIncluirResponsavel(){
//	     usuario = (Usuario)(listaUsuarios.getRowData());
//	     atividade.setUsuario(usuario);
		   System.out.println("Entrou incluir responsavel");
		   
	   }
	   
 //faz a preparação e inclui um membro no projeto especificado
    public void prepararIncluirMembroProjeto(){
	    Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){
    		
   	 
				usuario = (Usuario)(listaUsuarios.getRowData());

				
				membroProjetos = new MembrosProjeto();
				 
				membroProjetos.setProjeto(new Projeto());
				membroProjetos.setUsuario(new Usuario());
				
				
				 membroProjetos.setProjeto(projeto);
				 membroProjetos.setUsuario(usuario);

				 MembrosProjetoDao dao = new MembrosProjetoDaoImp();
				 MsgProjeto.msgMembrosIncluidoComSucesso();
				 dao.save(membroProjetos);
		}else{
			MsgProjeto.msgNaoPossuiDireito();
		}
    	
    }
	
public void salvaGerenteComoMembro(Projeto projetoAux,Usuario usuarioAux){
 
				membroProjetos = new MembrosProjeto();
				 
				membroProjetos.setProjeto(new Projeto());
				membroProjetos.setUsuario(new Usuario());
				
				
				 membroProjetos.setProjeto(projetoAux);
				 membroProjetos.setUsuario(usuarioAux);

				 MembrosProjetoDao dao = new MembrosProjetoDaoImp();
				 dao.save(membroProjetos);
	
 }
 
 public void alteraGerenteComoMembro(Projeto projetoAux,Usuario usuarioAux){
 
				membroProjetos = new MembrosProjeto();
				 
				membroProjetos.setProjeto(new Projeto());
				membroProjetos.setUsuario(new Usuario());
				
				
				 membroProjetos.setProjeto(projetoAux);
				 membroProjetos.setUsuario(usuarioAux);

				 MembrosProjetoDao dao = new MembrosProjetoDaoImp();
				 MsgProjeto.msgMembrosIncluidoComSucesso();
				 dao.update(membroProjetos);
	
 }
	 
	 //exclui TODOS os membros de um determinado projeto
 	public void eliminaMembrosProjeto() {
        List<MembrosProjeto> lista = new MembrosProjetoDaoImp().listByProjeto(projeto);
        for (MembrosProjeto membrosProjetoFor : lista) {   
	    		MembrosProjetoDao dao = new MembrosProjetoDaoImp();
				membrosProjetoFor.setProjeto(null);
				membrosProjetoFor.setProjeto(null);
	    		dao.update(membrosProjetoFor);
		        dao.remove(membrosProjetoFor);		
    	    }
    }
	 /*
     * Actions Users
     */
    
    public DataModel getListarUsuarios() {
        List<Usuario> lista = new UsuarioDaoImp().list();
        
        listaUsuarios = new ListDataModel(lista);
        return listaUsuarios;
    }
 

      
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    public String prepararAdicionarUsuario(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador")){
	        usuario = new Usuario();
	        return "cadastrarUsuario"; 
	    }else {
	    	MsgUsuario.msgNaoPossuiDireito();
	    	return "";
	    }
    }

    public String prepararAlterarUsuario(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador")){
	         usuario = (Usuario)(listaUsuarios.getRowData());
		     return "gerenciarUsuario";
	    }else {
	    	 MsgUsuario.msgNaoPossuiDireito();
	    	 return "";
	    }
}    

    public void prepararExcluirUsuario(){
        usuario = (Usuario)(listaUsuarios.getRowData());
    }

 
    
    public void adicionarUsuario(){
    	if (verificaLoginUsuario(usuario).equals("sim")){
    			MsgUsuario.msgUsuarioExiste();
    	}else {
            UsuarioDao dao = new UsuarioDaoImp();
            usuario.setTentativas(0);
            dao.save(usuario);

            Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
     				.getExternalContext().getSessionMap().get("usuarioLogado");
             adicionarLog("O Usuario "+usuarioAux.getNome(), "adicionou" ," o registro "+usuario.getNome(),"na tabela Usuario",  new Date());

            usuario = new Usuario();
            
            MsgUsuario.msgSalva();
    		
    	}
    }

    public void  alterarUsuario(){
    	if (verificaLoginUsuario(usuario).equals("sim")){
			MsgUsuario.msgUsuarioExiste();
    	}else {
        UsuarioDao dao = new UsuarioDaoImp();
        dao.update(usuario);

        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
 				.getExternalContext().getSessionMap().get("usuarioLogado");
         adicionarLog("O Usuario "+usuarioAux.getNome(), "alterou" ," o registro "+usuario.getNome(),"na tabela Usuario",  new Date());

        usuario = new Usuario();
        MsgUsuario.msgAltera();
    	}
    }
	
	   public void excluirUsuario(){
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador")){
	    	UsuarioDao dao = new UsuarioDaoImp();
	        dao.remove(usuario);
	        MsgUsuario.msgExclui();
	        usuario = new Usuario();
	    }else {
	    	MsgUsuario.msgNaoPossuiDireito();
	    }
}

  //Verifica se nao existe ja um usuario cadastrado com o login solicitado
    public String verificaLoginUsuario(Usuario usuarioAux) {
        List<Usuario> lista = new UsuarioDaoImp().list();
		Boolean blAux=false;

        for (Usuario usuarioFor : lista) {      
	        	if ( (usuarioAux.getLogin().equals(usuarioFor.getLogin())) &&
	        			(blAux=false)  )  {
	        				blAux=true;
	        				System.out.println("Usuario 1 if : " + usuarioFor.getNome());
        				return "sim";
					}	
	        	}
		return "nao";
    }



/////////////******//////////////////////
///////////// Action Autenticação //////////////////////
/////////////******//////////////////////


    public String doEfetuarLogin() {
      usuarioView = usuario;	

	  if (verificaUsuario(usuarioView)!=null){

		  usuario = verificaUsuario(usuarioView);
		   System.out.println("Usuario "+usuario.getTipoUsuario());
		   System.out.println("UsuarioView "+usuarioView.getTipoUsuario());

				  if (usuario.getTentativas()<2){		
								  FacesContext.getCurrentInstance().getExternalContext()
									.getSessionMap().put("usuarioLogado", usuario);
								  			usuario = new Usuario();
											  return "/private/pages/index";
					}else {
							       	    MsgUsuario.msgUsuarioBloqueado();
										usuario = new Usuario();
		                                return null;
					}

        } else {
        	Usuario usuarioViewAux=verificaSohLoginUsuario(usuarioView);
        	if ( (verificaSohLoginUsuario(usuarioViewAux)!=null) &&
        				(usuarioViewAux.getTentativas()<2)){	
		      		   /* Cria uma mensagem. */
					somaNTentativaLogin(usuarioView);
		       	    MsgUsuario.msgUsuarioInvalido();
		       	   /* Obtém a instancia atual do FacesContext e adiciona a mensagem de erro nele. */
		            usuario = new Usuario();
		            return null;
		  		  }	else{
		  			  MsgUsuario.msgUsuarioBloqueado();
		              usuario = new Usuario();
		              return null;
		  		  }
          
        }
}
	  
    public void somaNTentativaLogin(Usuario usuarioViewAux ) {
		Boolean blAux=false;

    	List<Usuario> lista = new UsuarioDaoImp().list();
    	
        for (Usuario usuarioFor : lista) {      
        	if ( (usuarioViewAux.getLogin().equals(usuarioFor.getLogin())) &&
						blAux.equals(false) )  {
        			blAux=true;
					usuarioFor.setTentativas(usuarioFor.getTentativas() +1 );
					UsuarioDao dao = new UsuarioDaoImp();
				    dao.update(usuarioFor);
					}	
        	  
        	}
    }	  
	  
	public void desbloquearUsuario(){
		usuario = (Usuario)(listaUsuarios.getRowData());
		
	     Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	    if ( usuarioAux.getTipoUsuario().equals("Administrador") ) {
				
				UsuarioDao dao = new UsuarioDaoImp();
				usuario.setTentativas(0);
			    dao.update(usuario);
		
		         adicionarLog("O Usuario "+usuarioAux.getNome(), "desbloqueou" ," o usuario "+usuario.getNome(),"na tabela Usuario",  new Date());
		
			    usuario = new Usuario();
			    List<Usuario> lista = new UsuarioDaoImp().list();
			    listaUsuarios = new ListDataModel(lista);
			    MsgUsuario.msgDesbloqueado();	

		}else {
				 MsgAtividade.msgNaoPossuiDireito();
		}
	
}

	public String doEfetuarLogof() {
    	usuario =new Usuario();
    	
    	FacesContext.getCurrentInstance().getExternalContext()
		.getSessionMap().put("usuario", new Object());
    	return "/login/login";
    }
	//verifica se os dados digitados na tela existem mesmo ,comparando login e senha
	    public Usuario verificaUsuario(Usuario usuarioViewAux ) {
		Boolean blAux=false;

    	List<Usuario> lista = new UsuarioDaoImp().list();
    	
        for (Usuario usuarioFor : lista) {      
        	if ( (usuarioViewAux.getLogin().equals(usuarioFor.getLogin()))  && 
					( usuarioViewAux.getSenha().equals(usuarioFor.getSenha()))  &&
						blAux.equals(false) )  {
        			blAux=true;
        			System.out.println("usuario for :" +usuarioFor.getTipoUsuario());
        			return usuarioFor;
					}	
        	  
        	}
		return null;
    }
	    public Usuario verificaSohLoginUsuario(Usuario usuarioViewAux ) {
		Boolean blAux=false;

    	List<Usuario> lista = new UsuarioDaoImp().list();
    	
        for (Usuario usuarioFor : lista) {      
        	if ( (usuarioViewAux.getLogin().equals(usuarioFor.getLogin()))    &&
						blAux.equals(false) )  {
        			blAux=true;
        			System.out.println("usuario for :" +usuarioFor.getTipoUsuario());
        			return usuarioFor;
					}	
        	  
        	}
		return null;
    }
	    

//prepara a recuperação de senha ...manda o usuario para o tela de recuperação de senha
	public String preparaRecuperarSenha(){
    	usuario= new Usuario();
    	return "recuperarSenha";
 }
 //recupera a senha do usuario ,mostrando ou a SENHA ou NAO FOI POSSIVEL
        public void recuperaSenha() {
        usuarioView = doVerificaRecuperacaoSenha(usuario);
        System.out.println("Usuario View"+ usuarioView.getLogin());
        System.out.println("Usuario "+ usuario.getLogin());
  	   	 if (doVerificaRecuperacaoSenha(usuarioView)!=null){
  		   	 usuario = doVerificaRecuperacaoSenha(usuarioView);
  		   	 MsgUsuario.msgMostraSenha(usuario);
  		   	 System.out.println("Usuario "+usuario.getTipoUsuario());
          } else {
          	MsgUsuario.msgNaoFoiPossivelRecuperarUsuario();
            usuario = new Usuario();
          }
    }
	
//verifica se os campos login,perguntaSecreta e RespostaSecreta estao corretos
	public Usuario  doVerificaRecuperacaoSenha(Usuario usuarioViewAux ) {
		Boolean blAux=false;
		List<Usuario> lista = new UsuarioDaoImp().list();
		
	    for (Usuario usuarioFor : lista) {      
	    	if ( (usuarioViewAux.getLogin().equals(usuarioFor.getLogin()))  && 
					( usuarioViewAux.getPerguntaSecreta().equals(usuarioFor.getPerguntaSecreta()))  &&
					( usuarioViewAux.getRespostaSecreta().equals(usuarioFor.getRespostaSecreta()))  &&
						blAux.equals(false) )  {
	    			blAux=true;
	    			System.out.println("usuario for :" +usuarioFor.getTipoUsuario());
	    			return usuarioFor;
					}	
	    	  
	    	}
		return null;
	}




    
    //********************************//
    // METODOS EVENTO//
    //********************************//
    
    public DataModel getListarEventos() {
        List<Evento> lista = new EventoDaoImp().listTab(projeto);
        listaEventos = new ListDataModel(lista);
        return listaEventos;
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
    	this.evento = evento;
    }

    
    public void prepararAdicionarEvento(){
        evento = new Evento();
    }

    public void prepararAlterarEvento(){
        evento = (Evento)(listaEventos.getRowData());
    }

    public void prepararExcluirEvento(){
        evento = (Evento)(listaEventos.getRowData());
    }
 
 
    
    public void adicionarEvento(){
	if (verificaDataFinaEventoProjeto().equals("sim")){
		MsgEvento.msgEventoVerificaDataEvento();
	}else {
        EventoDao dao = new EventoDaoImp();
        evento.setProjeto(projeto);
        dao.save(evento);

        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
 				.getExternalContext().getSessionMap().get("usuarioLogado");
         adicionarLog("O Usuario "+usuarioAux.getNome(), "adicionou" ," o registro "+evento.getNome(),"na tabela Evento",  new Date());

        evento = new Evento();
        MsgEvento.msgSalva();
	}
}

    public void alterarEvento(){
    	if (verificaDataFinaEventoProjeto().equals("sim")){
    		MsgEvento.msgEventoVerificaDataEvento();
    	}else     {
        EventoDao dao = new EventoDaoImp();
        dao.update(evento);
        
        Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
 				.getExternalContext().getSessionMap().get("usuarioLogado");
         adicionarLog("O Usuario "+usuarioAux.getNome(), "alterou" ," o registro "+evento.getNome(),"na tabela Evento",  new Date());

        evento = new Evento();
        List<Evento> lista = new EventoDaoImp().listTab(projeto);
        listaEventos = new ListDataModel(lista);
        MsgEvento.msgAltera();
    	}
  }
   public void excluirEvento(){
   	       Usuario usuarioAux = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");

	       if ( usuarioAux.getTipoUsuario().equals("Administrador") ||
	    		(projeto.getUsuario().getIdUsuario().equals(usuarioAux.getIdUsuario())) ){
    		System.out.println("Eventonenen:"+evento.getNome());
    		EventoDao dao = new EventoDaoImp();
	        dao.remove(evento);

	         adicionarLog("O Usuario "+usuarioAux.getNome(), "excluiu" ," o registro "+evento.getNome(),"na tabela Evento",  new Date());

	        MsgEvento.msgExclui();
	        evento = new Evento();
			} else{
			MsgEvento.msgNaoPossuiDireito();
			}
    	
    }
    
//Verifica se a data final do evento nao eh superior a do projeto    
    public String verificaDataFinaEventoProjeto() {
    	if (    evento.getDataEvento().after(projeto.getDataFinal()) ||
    			evento.getDataEvento().before(projeto.getDataInicial())
    			){
    				System.out.println("Eventoif : " + evento.getNome());
				return "sim";
			}	
		return "nao";
    }
  
    ////////*******************///////////////////
    		//Actions Log
    ///////*******************//////////////////

    public DataModel getListarLogsTodos() {
        List<Log> lista = new LogDaoImp().listTodos();
        listaLogs = new ListDataModel(lista);
        return listaLogs;
    }

    public DataModel getListarLogs() {
        List<Log> lista = new LogDaoImp().list(projeto);
        listaLogs = new ListDataModel(lista);
        return listaLogs;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log ;
    }
    
	public void adicionarLog(String usuarioAux,String acaoAux,String dadoTabelaAux,String tabelaAux,
			Date dataRegistroAux){
	        LogDao dao = new LogDaoImp();
	        log.setProjeto(projeto);
	        log.setUsuario(usuarioAux);
	        log.setAcao(acaoAux);
	        log.setDadoTabela(dadoTabelaAux);
	        log.setTabela(tabelaAux);
	        log.setDataRegistro(dataRegistroAux);
	        dao.save(log);
	        log = new Log();
	}

}
