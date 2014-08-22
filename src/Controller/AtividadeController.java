package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

import org.primefaces.context.RequestContext;

import Dao.AtividadeDao;
import Dao.AtividadeDaoImp;
import Dao.ProjetoDao;
import Dao.ProjetoDaoImp;
import Model.Atividade;
import Model.Projeto;


@ManagedBean
@SessionScoped
public class AtividadeController {

    private Atividade atividade;
    private DataModel listaAtividades;

//    public DataModel getListarAtividades() {
//        List<Atividade> lista = new AtividadeDaoImp().list();
//        
//        listaAtividades = new ListDataModel(lista);
//     
//        return listaAtividades;
//    }
    
    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    
    public void prepararAdicionarAtividade(){
        atividade = new Atividade();
    }

    public void prepararAlterarAtividade(){
        atividade = (Atividade)(listaAtividades.getRowData());
    }

//    public void excluirAtividade(){
//        Atividade atividadeTemp = (Atividade)(listaAtividades.getRowData());
//    	AtividadeDao dao = new AtividadeDaoImp();
//    	dao.remove(currentAtividade);
//    	RequestContext.getCurrentInstance().execute("dialogConfirmRemove.hide()");
//    }

    public void imprimeAtividade(Atividade atividade){
        AtividadeDao dao = new AtividadeDaoImp();
        dao.imprime(atividade);
    }
    
//    public void adicionarAtividade(){
//        AtividadeDao dao = new AtividadeDaoImp();
//        dao.saveOrUpdate(atividade);
//        atividade = new Atividade();
//    }

//    public void alterarAtividade(){
//
//        AtividadeDao dao = new AtividadeDaoImp();
//        dao.saveOrUpdate(atividade);
//        atividade = new Atividade();
//        List<Atividade> lista = new AtividadeDaoImp().list();
//        listaAtividades = new ListDataModel(lista);
//
//    }
    
    //metodo para salvar na unha
    public void salvarAtividade(Atividade atividade){
        AtividadeDao dao = new AtividadeDaoImp();
        dao.save(atividade);
    }
}
