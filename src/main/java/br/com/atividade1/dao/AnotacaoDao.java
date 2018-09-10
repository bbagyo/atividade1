package br.com.atividade1.dao;

import br.com.atividade1.model.Anotacao;

import javax.persistence.EntityManager;
import java.util.List;

public class AnotacaoDao {

    public List<Anotacao> listarTodos() throws Exception {
        EntityManager em = Conexao.getEntityManagerFactory().createEntityManager();
        List<Anotacao> anotacaos = null;

        try {
            anotacaos = em.createQuery("from Anotacao").getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            em.close();
        }

        return anotacaos;
    }

    public void inserir(Anotacao anotacao) throws Exception {
        EntityManager em = Conexao.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(anotacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        } finally {
            em.close();
        }
    }

    public void atualizar(Anotacao anotacao) throws Exception {
        EntityManager em = Conexao.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(anotacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        } finally {
            em.close();
        }
    }

    public void excluir(long id) throws Exception {
        EntityManager em = Conexao.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Anotacao anotacao = em.find(Anotacao.class, id);
            em.remove(anotacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        } finally {
            em.close();
        }
    }

    public Anotacao selecionar(long id) throws Exception {
        Anotacao anotacao;

        EntityManager em = Conexao.getEntityManagerFactory().createEntityManager();

        try {
            anotacao = em.find(Anotacao.class, id);
        } finally {
            em.close();
        }

        return anotacao;
    }
}
