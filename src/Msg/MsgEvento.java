package Msg;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MsgEvento {


	public static String msgSalva() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Evento Criado com sucesso", ""));
		return "";
	}

	public static String msgAltera() {
	FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Evento Alterado com sucesso", ""));
		return "";
	}
	
	public static String msgExclui() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Evento Excluido com sucesso", ""));
		return "";
	}
	
	public static String msgEventoExiste() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ja existe um evento com este nome", ""));
		return "";
	}

	public static String msgEventoNaoPodeExcluir() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"O evento nao pode ser excluido", ""));
		return "";
	}

	public static String msgEventoVerificaDataEventoProprio() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data Invalida", ""));
		return "";
	}

	public static String msgEventoVerificaDataEvento() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data do Evento nao Pode ser superior a do Projeto", ""));
		return "";
	}
	public static String msgNaoPossuiDireito() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Você não possui direito para esta ação", ""));
		return "";
	}

}
