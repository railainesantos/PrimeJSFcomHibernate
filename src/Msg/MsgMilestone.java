package Msg;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MsgMilestone {

	public static String msgSalva() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Milestone Criado com sucesso", ""));
		return "";
	}

	public static String msgAltera() {
	FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Milestone Alterado com sucesso", ""));
		return "";
	}
	
	public static String msgExclui() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Milestone Excluido com sucesso", ""));
		return "";
	}
	public static String msgMilestoneNaoPodeExcluir() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"O milestone nao pode ser excluido", ""));
		return "";
	}

	public static String msgMilestoneVerificaDataMilestone() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data Final do Milestone Superior a Data Final do Projeto", ""));
		return "";
	}
	
	public static String msgMilestoneVerificaDataMilestoneProprio() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data Invalida", ""));
		return "";
	}
	public static String msgNaoPossuiDireito() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Você não possui direito para esta ação", ""));
		return "";
	}


}
