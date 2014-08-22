package Msg;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MsgAtividade {

	
	public static String msgSalva() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Atividade Criada com sucesso", ""));
		return "";
	}

	public static String msgAltera() {
	FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Atividade Alterada com sucesso", ""));
		return "";
	}
	
	public static String msgExclui() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Atividade Excluida com sucesso", ""));
		return "";
	}
	

	public static String msgAtividadeIncluida() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Atividade Incluida no Milestone com Sucesso", ""));
		return "";
	}
	public static String msgAtividadeExiste() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ja existe uma atividade com este nome", ""));
		return "";
	}
	
	public static String msgDataInvalidaAtividadeMilestone() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data Inicial Atividade maior que a Final do Milestone", ""));
		return "";
	}
	
	public static String msgDataInvalidaAtividadeProjeto() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data Invalida", ""));
		return "";
	}
	public static String msgNaoPossuiDireito() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Você não possui direito para esta ação", ""));
		return "";
	}

	public static String msgStatus() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Status não pode ser alterado", ""));
		return "";
	}
	
}
