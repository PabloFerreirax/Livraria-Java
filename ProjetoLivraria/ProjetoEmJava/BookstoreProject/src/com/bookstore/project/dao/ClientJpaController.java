/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.dao;

import com.bookstore.project.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bookstore.project.model.Contact;
import com.bookstore.project.model.Address;
import com.bookstore.project.model.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pablo
 */
public class ClientJpaController implements Serializable {

    public ClientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contact idContato = client.getIdContato();
            if (idContato != null) {
                idContato = em.getReference(idContato.getClass(), idContato.getIdContato());
                client.setIdContato(idContato);
            }
            Address idEndereco = client.getIdEndereco();
            if (idEndereco != null) {
                idEndereco = em.getReference(idEndereco.getClass(), idEndereco.getIdEndereco());
                client.setIdEndereco(idEndereco);
            }
            em.persist(client);
            if (idContato != null) {
                idContato.getClientCollection().add(client);
                idContato = em.merge(idContato);
            }
            if (idEndereco != null) {
                idEndereco.getClientCollection().add(client);
                idEndereco = em.merge(idEndereco);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client persistentClient = em.find(Client.class, client.getIdCliente());
            Contact idContatoOld = persistentClient.getIdContato();
            Contact idContatoNew = client.getIdContato();
            Address idEnderecoOld = persistentClient.getIdEndereco();
            Address idEnderecoNew = client.getIdEndereco();
            if (idContatoNew != null) {
                idContatoNew = em.getReference(idContatoNew.getClass(), idContatoNew.getIdContato());
                client.setIdContato(idContatoNew);
            }
            if (idEnderecoNew != null) {
                idEnderecoNew = em.getReference(idEnderecoNew.getClass(), idEnderecoNew.getIdEndereco());
                client.setIdEndereco(idEnderecoNew);
            }
            client = em.merge(client);
            if (idContatoOld != null && !idContatoOld.equals(idContatoNew)) {
                idContatoOld.getClientCollection().remove(client);
                idContatoOld = em.merge(idContatoOld);
            }
            if (idContatoNew != null && !idContatoNew.equals(idContatoOld)) {
                idContatoNew.getClientCollection().add(client);
                idContatoNew = em.merge(idContatoNew);
            }
            if (idEnderecoOld != null && !idEnderecoOld.equals(idEnderecoNew)) {
                idEnderecoOld.getClientCollection().remove(client);
                idEnderecoOld = em.merge(idEnderecoOld);
            }
            if (idEnderecoNew != null && !idEnderecoNew.equals(idEnderecoOld)) {
                idEnderecoNew.getClientCollection().add(client);
                idEnderecoNew = em.merge(idEnderecoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = client.getIdCliente();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            Contact idContato = client.getIdContato();
            if (idContato != null) {
                idContato.getClientCollection().remove(client);
                idContato = em.merge(idContato);
            }
            Address idEndereco = client.getIdEndereco();
            if (idEndereco != null) {
                idEndereco.getClientCollection().remove(client);
                idEndereco = em.merge(idEndereco);
            }
            em.remove(client);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Client findClient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
