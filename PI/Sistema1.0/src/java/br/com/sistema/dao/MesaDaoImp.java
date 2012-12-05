/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.entidade.Mesa;
import br.com.sistema.util.FabricaSessao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mateus
 */
public class MesaDaoImp extends BaseDaoImp<Mesa, Long> implements MesaDao{

    

    @Override
    public List<Mesa> getTodos() {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Mesa");
        List<Mesa> mesas = query.list();
        session.close();
        return mesas;
    }

    @Override
    public Mesa pesquisaPorId(Long id) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Mesa mesa = (Mesa) session.get(Mesa.class, id);
        session.close();
        return mesa;
    }
    
}

