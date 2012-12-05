/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.entidade.Endereco;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class EnderecoDaoImp extends BaseDaoImp<Endereco, Long> implements EnderecoDao{

    @Override
    public Endereco pesquisaPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Endereco> getTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
