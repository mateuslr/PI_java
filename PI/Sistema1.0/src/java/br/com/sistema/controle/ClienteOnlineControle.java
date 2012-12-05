/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.controle;

import br.com.sistema.dao.ClienteOnlineDao;
import br.com.sistema.dao.ClienteOnlineDaoImp;
import br.com.sistema.dao.EnderecoDao;
import br.com.sistema.dao.EnderecoDaoImp;
import br.com.sistema.entidade.ClienteOnline;
import br.com.sistema.entidade.Endereco;
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
public class ClienteOnlineControle {
    private ClienteOnline cliente;
    private Endereco endereco;
    private Usuario usuario;
    private List enderecos;
    private ClienteOnlineDao codao;

    public ClienteOnline getCliente() {
        if (cliente == null) {
            cliente = new ClienteOnline();
        }
        return cliente;
    }

    public void setCliente(ClienteOnline cliente) {
        this.cliente = cliente;
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
    
     private void limpar(){
        cliente = null;
        endereco = null;
        usuario = null;
        enderecos = null;
    }
     
    public String salvar(){
        codao = new ClienteOnlineDaoImp();
        EnderecoDao edao = new EnderecoDaoImp();
        FacesContext context = FacesContext.getCurrentInstance();
        enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);
        cliente.setEnderecos(enderecos);
        cliente.setUsuario(usuario);
        cliente.setInicio(new Date());
        if (cliente.getId() == null) {
            cliente.setAtivo(true);
            cliente = codao.salva(cliente);
            for (Endereco end : cliente.getEnderecos()) {
                end.setPessoa(cliente);
                edao.salva(end);
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario Salvo Com Sucesso!", ""));
        }else{
            cliente = codao.altera(cliente);
            for (Endereco end : cliente.getEnderecos()) {
                if (end.getId() == null) {
                    end.setPessoa(cliente);
                    edao.salva(end);
                }else{
                    end.setPessoa(cliente);
                    edao.altera(end);
                }
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario Alterado Com Sucesso!", ""));
        }
        limpar();
        return "";
    }
    public String novo(){
        cliente = new ClienteOnline();
        cliente.setUsuario(new Usuario());
        endereco = new Endereco();
        return "cadClienteOnline.faces";
    }
    
}
