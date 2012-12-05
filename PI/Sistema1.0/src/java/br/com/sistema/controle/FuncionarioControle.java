/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.controle;

import br.com.sistema.dao.EnderecoDao;
import br.com.sistema.dao.EnderecoDaoImp;
import br.com.sistema.dao.FuncionarioDao;
import br.com.sistema.dao.FuncionarioDaoImp;
import br.com.sistema.entidade.Endereco;
import br.com.sistema.entidade.Funcionario;
import br.com.sistema.entidade.Usuario;
import java.util.ArrayList;
import java.util.Date;
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
public class FuncionarioControle {
    private Funcionario funcionario;
    private Endereco endereco;
    private Usuario usuario;
    private List enderecos;
    private FuncionarioDao fdao;

    public Funcionario getFuncionario() {
        if (funcionario == null) {
            funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List enderecos) {
        this.enderecos = enderecos;
    }
    
    public String salvar(){
        fdao = new FuncionarioDaoImp();
        EnderecoDao edao = new EnderecoDaoImp();
        FacesContext context = FacesContext.getCurrentInstance();
        enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);
        funcionario.setEnderecos(enderecos);
        funcionario.setUsuario(usuario);
        funcionario.setInicio(new Date());
        if (funcionario.getId() == null) {
            funcionario.setAtivo(true);
            funcionario = fdao.salva(funcionario);
            for (Endereco end : funcionario.getEnderecos()) {
                end.setPessoa(funcionario);
                edao.salva(end);
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario Salvo Com Sucesso!", ""));
        }else{
            funcionario = fdao.altera(funcionario);
            for (Endereco end : funcionario.getEnderecos()) {
                if (end.getId() == null) {
                    end.setPessoa(funcionario);
                    edao.salva(end);
                }else{
                    end.setPessoa(funcionario);
                    edao.altera(end);
                }
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario Alterado Com Sucesso!", ""));
        }
        limpar();
        return "";
    }
    
    private void limpar(){
        funcionario = null;
        endereco = null;
        usuario = null;
        enderecos = null;
    }
    
    public String novo(){
        funcionario = new Funcionario();
        funcionario.setUsuario(new Usuario());
        endereco = new Endereco();
        return "cadFuncionario.faces";
    }
}
