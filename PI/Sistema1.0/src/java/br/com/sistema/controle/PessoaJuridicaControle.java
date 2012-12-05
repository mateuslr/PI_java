/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.controle;

import br.com.sistema.dao.EnderecoDao;
import br.com.sistema.dao.EnderecoDaoImp;
import br.com.sistema.dao.PessoaJuridicaDao;
import br.com.sistema.dao.PessoaJuridicaDaoImp;
import br.com.sistema.entidade.Endereco;
import br.com.sistema.entidade.PessoaJuridica;

import java.util.ArrayList;
import java.util.List;
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
public class PessoaJuridicaControle {
    private PessoaJuridica pessoa;
    private Endereco endereco;
    private List<Endereco> enderecos;
    private PessoaJuridicaDao dao;

    public PessoaJuridica getPessoa() {
        if (pessoa == null) {
            pessoa = new PessoaJuridica();
        }
        return pessoa;
    }

    public void setPessoa(PessoaJuridica pessoa) {
        this.pessoa = pessoa;
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
        if (endereco == null) {
            enderecos = new ArrayList<Endereco>();
        }
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    private void limpar(){
        pessoa = null;
        endereco = null;
        enderecos = null;
    }
    
    public String salvar(){
        dao = new PessoaJuridicaDaoImp();
        EnderecoDao enderecoDao = new EnderecoDaoImp();
        FacesContext context = FacesContext.getCurrentInstance();
        enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);
        pessoa.setEnderecos(enderecos);
        if (pessoa.getId() == null) {
            pessoa.setAtivo(true);
            pessoa = dao.salva(pessoa);
            for (Endereco end : pessoa.getEnderecos()) {
                end.setPessoa(pessoa);
                enderecoDao.salva(end);
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa Salvo Com Sucesso!", ""));
        }else{
            pessoa = dao.altera(pessoa);
            for (Endereco end : pessoa.getEnderecos()) {
                if (end.getId() == null) {
                    end.setPessoa(pessoa);
                    enderecoDao.salva(end);
                }else{
                    end.setPessoa(pessoa);
                    enderecoDao.altera(end);
                }
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa Alterado Com Sucesso!", ""));
        }
        limpar();
        return "";
    }
    
    
    public String novo(){
        pessoa = new PessoaJuridica();
        endereco = new Endereco();
        return "cadPessoaJuridica.faces";
    }
}
