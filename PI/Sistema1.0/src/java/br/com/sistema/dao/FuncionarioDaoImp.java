/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.entidade.Funcionario;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class FuncionarioDaoImp extends BaseDaoImp<Funcionario, Long> implements FuncionarioDao{

    @Override
    public Funcionario pesquisaPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Funcionario> getTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
