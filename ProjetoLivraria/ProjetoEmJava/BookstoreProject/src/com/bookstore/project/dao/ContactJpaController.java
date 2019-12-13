/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.dao;

import com.bookstore.project.dao.exceptions.IllegalOrphanException;
import com.bookstore.project.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bookstore.project.model.Client;
import com.bookstore.project.model.Contact;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pablo
 */
public class ContactJpaController implements Serializable {

    public ContactJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contact contact) {
        if (contact.getClientCollection() == null) {
            contact.setClientCollection(new ArrayList<Client>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Client> attachedClientCollection = new ArrayList<Client>();
            for (Client clientCollectionClientToAttach : contact.getClientCollection()) {
                clientCollectionClientToAttach = em.getReference(clientCollectionClientToAttach.getClass(), clientCollectionClientToAttach.getIdCliente());
                attachedClientCollection.add(clientCollectionClientToAttach);
            }
            contact.setClientCollection(attachedClientCollection);
            em.persist(contact);
            for (Client clientCollectionClient : contact.getClientCollection()) {
                Contact oldIdContatoOfClientCollectionClient = clientCollectionClient.getIdContato();
                clientCollectionClient.setIdContato(contact);
                clientCollectionClient = em.merge(clientCollectionClient);
                if (oldIdContatoOfClientCollectionClient != null) {
                    oldIdContatoOfClientCollectionClient.getClientCollection().remove(clientCollectionClient);
                    oldIdContatoOfClientCollectionClient = em.merge(oldIdContatoOfClientCollectionClient);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contact contact) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contact persistentContact = em.find(Contact.class, contact.getIdContato());
            Collection<Client> clientCollectionOld = persistentContact.getClientCollection();
            Collection<Client> clientCollectionNew = contact.getClientCollection();
            List<String> illegalOrphanMessages = null;
            for (Client clientCollectionOldClient : clientCollectionOld) {
                if (!clientCollectionNew.contains(clientCollectionOldClient)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Client " + clientCollectionOldClient + " since its idContato field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Client> attachedClientCollectionNew = new ArrayList<Client>();
            for (Client clientCollectionNewClientToAttach : clientCollectionNew) {
                clientCollectionNewClientToAttach = em.getReference(clientCollectionNewClientToAttach.getClass(), clientCollectionNewClientToAttach.getIdCliente());
                attachedClientCollectionNew.add(clientCollectionNewClientToAttach);
            }
            clientCollectionNew = attachedClientCollectionNew;
            contact.setClientCollection(clientCollectionNew);
            contact = em.merge(contact);
            for (Client clientCollectionNewClient : clientCollectionNew) {
                if (!clientCollectionOld.contains(clientCollectionNewClient)) {
                    Contact oldIdContatoOfClientCollectionNewClient = clientCollectionNewClient.getIdContato();
                    clientCollectionNewClient.setIdContato(contact);
                    clientCollectionNewClient = em.merge(clientCollectionNewClient);
                    if (oldIdContatoOfClientCollectionNewClient != null && !oldIdContatoOfClientCollectionNewClient.equals(contact)) {
                        oldIdContatoOfClientCollectionNewClient.getClientCollection().remove(clientCollectionNewClient);
                        oldIdContatoOfClientCollectionNewClient = em.merge(oldIdContatoOfClientCollectionNewClient);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contact.getIdContato();
                if (findContact(id) == null) {
                    throw new NonexistentEntityException("The contact with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contact contact;
            try {
                contact = em.getReference(Contact.class, id);
                contact.getIdContato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contact with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Client> clientCollectionOrphanCheck = contact.getClientCollection();
            for (Client clientCollectionOrphanCheckClient : clientCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contact (" + contact + ") cannot be destroyed since the Client " + clientCollectionOrphanCheckClient + " in its clientCollection field has a non-nullable idContato field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(contact);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contact> findContactEntities() {
        return findContactEntities(true, -1, -1);
    }

    public List<Contact> findContactEntities(int maxResults, int firstResult) {
        return findContactEntities(false, maxResults, firstResult);
    }

    private List<Contact> findContactEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contact.class));
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

    public Contact findContact(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contact.class, id);
        } finally {
            em.close();
        }
    }

    public int getContactCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contact> rt = cq.from(Contact.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
