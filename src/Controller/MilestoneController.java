package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class MilestoneController {

//    private Milestone milestone;
//    private DataModel listaMilestones;
//    
////    public DataModel getListarMilestones() {
////        List<Milestone> lista = new MilestoneDaoImp().list();
////        
////        listaMilestones = new ListDataModel(lista);
////     
////        return listaMilestones;
////    }
//    
//    public Milestone getMilestone() {
//        return milestone;
//    }
//
//    public void setMilestone(Milestone milestone) {
//        this.milestone = milestone;
//    }
//
//    
//    public void prepararAdicionarMilestone(){
//        milestone = new Milestone();
//    }
//
//    public void prepararAlterarMilestone(){
//        milestone = (Milestone)(listaMilestones.getRowData());
//    }
//
//    public void excluirMilestone(){
//        Milestone milestoneTemp = (Milestone)(listaMilestones.getRowData());
//        MilestoneDao dao = new MilestoneDaoImp();
//        dao.remove(milestoneTemp);
//
//    }
//    public void imprimeMilestone(Milestone milestone){
//        MilestoneDao dao = new MilestoneDaoImp();
//        dao.imprime(milestone);
//    }
//
//    
//    public void adicionarMilestone(){
//        MilestoneDao dao = new MilestoneDaoImp();
//        dao.saveOrUpdate(milestone);
//        milestone = new Milestone();
//    }
//
//    public void alterarMilestone(){
//
//        MilestoneDao dao = new MilestoneDaoImp();
//        dao.saveOrUpdate(milestone);
//        milestone = new Milestone();
//        List<Milestone> lista = new MilestoneDaoImp().list();
//        listaMilestones = new ListDataModel(lista);

    }
    
    //metodo para salvar na unha
//    public String salvarMilestone(Milestone milestone){
//    	MilestoneDao dao = new MilestoneDaoImp();
//        dao.saveOrUpdate(milestone);
//        return "consultarMilestone";
//        
//    }
    
//}
