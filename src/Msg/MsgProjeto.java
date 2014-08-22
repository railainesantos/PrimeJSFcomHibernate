package Msg;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MsgProjeto {


	public static String msgSalva() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Projeto Criado com sucesso", ""));
		return "";
	}

	public static String msgAltera() {
	FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Projeto Alterado com sucesso", ""));
		return "";
	}
	
	public static String msgExclui() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Projeto Excluido com sucesso", ""));
		return "";
	}
	
	public static String msgProjetoExiste() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ja existe um projeto com este nome", ""));
		return "";
	}

	public static String msgProjetoNaoPodeExcluir() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"O projeto nao pode ser excluido", ""));
		return "";
	}

	public static String msgProjetoDataInvalida() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data Invalida", ""));
		return "";
	}
	public static String msgNaoPossuiDireito() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Você não possui direito para esta ação", ""));
		return "";
	}
	public static String msgMembrosIncluidoComSucesso() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Membro incluido no projeto com sucesso", ""));
		return "";
	}
	public static String msgStatus() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Status não pode ser alterado", ""));
		return "";
}


}
