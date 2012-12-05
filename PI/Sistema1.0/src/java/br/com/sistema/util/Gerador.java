/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Aluno
 */
public class Gerador {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory ("Sistema1.0PU");
        EntityManager manager = factory . createEntityManager ();
    }
}
