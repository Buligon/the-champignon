/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.connector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Giuvane Conti
 */
public class ConexaoJPQL {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    
    public static EntityManager getInstance() {
        if (manager == null)
        {
            synchronized (ConexaoJPQL.class) {
                if (manager == null)
                {
                    factory = Persistence.createEntityManagerFactory("thechampignon");
                    manager = factory.createEntityManager();
                }
            }
        }
        
        return manager;
    }
    
    public static void close() {
        manager.close();
        factory.close();
    }
}
