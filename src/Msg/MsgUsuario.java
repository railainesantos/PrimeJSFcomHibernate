package Msg;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import Model.Usuario;

public class MsgUsuario {
	public static String msgSalva() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario Criado com sucesso", ""));
		return "";
	}

	public static String msgAltera() {
	FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario Alterado com sucesso", ""));
		return "";
	}
	
	public static String msgExclui() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario Excluido com sucesso", ""));
		return "";
	}

	public static String msgUsuarioInvalido() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario ou Senha Invalido", ""));
		return "";
	}
	public static String msgNaoPossuiDireito() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Você não possui direito para esta ação", ""));
		return "";
	}
	public static String msgUsuarioExiste() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ja existe um usuario com este login", ""));
		return "";
	}

	public static String msgNaoFoiPossivelRecuperarUsuario() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Dados Invalidos", ""));
		return "";
	}
	public static String msgMostraSenha(Usuario usuario) {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Sua senha :"+usuario.getSenha(), ""));
		return "";
	}
	public static String msgUsuarioBloqueado() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario bloqueado", ""));
		return "";
	}
	public static String msgDesbloqueado() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario desbloqueado com sucesso", ""));
		return "";
	}
	public static String msgUsuarioJaExisteProjeto() {
		FacesContext.getCurrentInstance().addMessage(	null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario já está incluido no Projeto", ""));
		return "";
	}
}
