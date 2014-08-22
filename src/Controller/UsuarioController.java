package Controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import Dao.UsuarioDao;
import Dao.UsuarioDaoImp;
import Model.Usuario;
import Model.Usuario;


@ManagedBean
@SessionScoped
public class UsuarioController {
	
	ProjetoController pc;

    private Usuario usuario;
    private DataModel listaUsuarios;

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
        usuario = new Usuario();
        return "cadastrarUsuario";
    }

    public String prepararAlterarUsuario(){
        usuario = (Usuario)(listaUsuarios.getRowData());
        return "gerenciarUsuario";
    }

    public String excluirUsuario(){
        Usuario usuarioTemp = (Usuario)(listaUsuarios.getRowData());
        UsuarioDao dao = new UsuarioDaoImp();
        dao.remove(usuarioTemp);
        return "index";

    }

    public String adicionarUsuario(){
        UsuarioDao dao = new UsuarioDaoImp();
        dao.save(usuario);
        return "consultarUsuario";
        
    }

    public String alterarUsuario(){
        UsuarioDao dao = new UsuarioDaoImp();
        dao.update(usuario);
        return "consultarUsuario";
    }

//    //metodo para salvar na unha
//    public String salvarUsuario(Usuario usuario){
//        UsuarioDao dao = new UsuarioDaoImp();
//        dao.saveOrUpdate(usuario);
//        return "consultarUsuario";
//        
//    }

    
}
