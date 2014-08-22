package Utils;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
@SessionScoped
public class Validator {
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private String senha;
	
	private String confirmaSenha;
	
	public void validateBeginDate(FacesContext context, UIComponent component, Object value) {
	
		dataInicial = (Date) value;
	}

	
	public void validateEndDate(FacesContext context, UIComponent component, Object value) {
	
		dataFinal = (Date) value;
	
		if (dataInicial.after(dataFinal)) {
	
			throw new ValidatorException(new FacesMessage("Data inicial maior que data final"));
	
		} 
	}
	
	public void validateSetSenha(FacesContext context, UIComponent component, Object value) {
		
		confirmaSenha = (String) value;
	}

	
	public void validaSenhaConfirmaSenha(FacesContext context, UIComponent component, Object value) {
	
		senha = (String) value;
	
		if (!confirmaSenha.equals(senha)) {
	
			throw new ValidatorException(new FacesMessage("Senhas não conferem"));
	
		} 
	}
	}