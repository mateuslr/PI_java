/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.entidade.Perfil;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class PerfilDaoImp extends BaseDaoImp<Perfil, Long> implements PerfilDao{

    @Override
    public Perfil pesquisaPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Perfil> getTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
