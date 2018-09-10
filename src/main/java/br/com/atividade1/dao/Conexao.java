package br.com.atividade1.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("anotacao");
        }

        return entityManagerFactory;
    }
}
