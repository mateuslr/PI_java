/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.controle;

import br.com.sistema.dao.MesaDao;
import br.com.sistema.dao.MesaDaoImp;
import br.com.sistema.entidade.Mesa;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author Mateus
 */
@ManagedBean
@SessionScoped
public class MesaControle {
    private Mesa mesa;
    private MesaDao dao;
    

    public Mesa getMesa() {
        if (mesa == null) {
            mesa = new Mesa();
        }
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

      
    
    public String salvar() {
        dao = new MesaDaoImp();
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (mesa.getId() == null) {
            dao.salva(mesa);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mesa Salva Com Sucesso!", ""));
        } else {
            dao.altera(mesa);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mesa Alterado Com Sucesso!", ""));
        }
        limpar();
        return "";
    }

    private void limpar() {
        mesa = null;
        
    }
    
    public String novo(){
        limpar();
        return "cadMesa.faces";
    }
    
}
