/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.entidade.Usuario;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class UsuarioDaoImp extends BaseDaoImp<Usuario, Long> implements UsuarioDao{

    @Override
    public Usuario pesquisaPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Usuario> getTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
