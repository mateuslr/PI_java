/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.entidade.ClienteOnline;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class ClienteOnlineDaoImp extends BaseDaoImp<ClienteOnline, Long> implements ClienteOnlineDao{

    @Override
    public ClienteOnline pesquisaPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ClienteOnline> getTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
