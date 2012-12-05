/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.controle;

import br.com.sistema.dao.EnderecoDao;
import br.com.sistema.dao.EnderecoDaoImp;
import br.com.sistema.dao.FornecedorDao;
import br.com.sistema.dao.FornecedorDaoImp;
import br.com.sistema.entidade.Endereco;
import br.com.sistema.entidade.Fornecedor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author usuario
 */
@ManagedBean
@SessionScoped
public class FornecedorControle{
    private Fornecedor fornecedor;
    private Endereco endereco;
    private List<Endereco> enderecos;
    private FornecedorDao fdao;

    public Fornecedor getFornecedor() {
        if (fornecedor == null) {
            fornecedor = new Fornecedor();
        }
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos() {
        if (enderecos == null) {
            enderecos = new ArrayList<Endereco>();
        }
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public String salvar(){
        fdao = new FornecedorDaoImp();
        EnderecoDao edao = new EnderecoDaoImp();
        FacesContext context = FacesContext.getCurrentInstance();
        enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);
        fornecedor.setDataCriacao(new Date());
        fornecedor.setEnderecos(enderecos);
        if (fornecedor.getId() == null) {
            fornecedor.setAtivo(true);
            fornecedor = fdao.salva(fornecedor);
            for (Endereco end : fornecedor.getEnderecos()) {
                end.setPessoa(fornecedor);
                edao.salva(end);
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor Salvo Com Sucesso!", ""));
        }else{
            fornecedor = fdao.altera(fornecedor);
            for (Endereco end : fornecedor.getEnderecos()) {
                if (end.getId() == null) {
                    end.setPessoa(fornecedor);
                    edao.salva(end);
                }else{
                    end.setPessoa(fornecedor);
                    edao.altera(end);
                }
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Forncedor Alterado Com Sucesso!", ""));
        }
        limpar();
        return "";
    }
    
    private void limpar(){
        fornecedor = null;
        endereco = null;
        enderecos = null;
        
    }
    public String novo(){
        fornecedor = new Fornecedor();
        endereco = new Endereco();
        return "cadFornecedor.faces";
    }
}
