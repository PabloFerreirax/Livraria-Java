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
import com.bookstore.project.model.Buy;
import com.bookstore.project.model.Book;
import com.bookstore.project.model.PurchaseItem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pablo
 */
public class PurchaseItemJpaController implements Serializable {

    public PurchaseItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PurchaseItem purchaseItem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Buy idCompra = purchaseItem.getIdCompra();
            if (idCompra != null) {
                idCompra = em.getReference(idCompra.getClass(), idCompra.getIdCompra());
                purchaseItem.setIdCompra(idCompra);
            }
            Book idLivro = purchaseItem.getIdLivro();
            if (idLivro != null) {
                idLivro = em.getReference(idLivro.getClass(), idLivro.getIdLivro());
                purchaseItem.setIdLivro(idLivro);
            }
            em.persist(purchaseItem);
            if (idCompra != null) {
                idCompra.getPurchaseItemCollection().add(purchaseItem);
                idCompra = em.merge(idCompra);
            }
            if (idLivro != null) {
                idLivro.getPurchaseItemCollection().add(purchaseItem);
                idLivro = em.merge(idLivro);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PurchaseItem purchaseItem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PurchaseItem persistentPurchaseItem = em.find(PurchaseItem.class, purchaseItem.getIdItemCompra());
            Buy idCompraOld = persistentPurchaseItem.getIdCompra();
            Buy idCompraNew = purchaseItem.getIdCompra();
            Book idLivroOld = persistentPurchaseItem.getIdLivro();
            Book idLivroNew = purchaseItem.getIdLivro();
            if (idCompraNew != null) {
                idCompraNew = em.getReference(idCompraNew.getClass(), idCompraNew.getIdCompra());
                purchaseItem.setIdCompra(idCompraNew);
            }
            if (idLivroNew != null) {
                idLivroNew = em.getReference(idLivroNew.getClass(), idLivroNew.getIdLivro());
                purchaseItem.setIdLivro(idLivroNew);
            }
            purchaseItem = em.merge(purchaseItem);
            if (idCompraOld != null && !idCompraOld.equals(idCompraNew)) {
                idCompraOld.getPurchaseItemCollection().remove(purchaseItem);
                idCompraOld = em.merge(idCompraOld);
            }
            if (idCompraNew != null && !idCompraNew.equals(idCompraOld)) {
                idCompraNew.getPurchaseItemCollection().add(purchaseItem);
                idCompraNew = em.merge(idCompraNew);
            }
            if (idLivroOld != null && !idLivroOld.equals(idLivroNew)) {
                idLivroOld.getPurchaseItemCollection().remove(purchaseItem);
                idLivroOld = em.merge(idLivroOld);
            }
            if (idLivroNew != null && !idLivroNew.equals(idLivroOld)) {
                idLivroNew.getPurchaseItemCollection().add(purchaseItem);
                idLivroNew = em.merge(idLivroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = purchaseItem.getIdItemCompra();
                if (findPurchaseItem(id) == null) {
                    throw new NonexistentEntityException("The purchaseItem with id " + id + " no longer exists.");
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
            PurchaseItem purchaseItem;
            try {
                purchaseItem = em.getReference(PurchaseItem.class, id);
                purchaseItem.getIdItemCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The purchaseItem with id " + id + " no longer exists.", enfe);
            }
            Buy idCompra = purchaseItem.getIdCompra();
            if (idCompra != null) {
                idCompra.getPurchaseItemCollection().remove(purchaseItem);
                idCompra = em.merge(idCompra);
            }
            Book idLivro = purchaseItem.getIdLivro();
            if (idLivro != null) {
                idLivro.getPurchaseItemCollection().remove(purchaseItem);
                idLivro = em.merge(idLivro);
            }
            em.remove(purchaseItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PurchaseItem> findPurchaseItemEntities() {
        return findPurchaseItemEntities(true, -1, -1);
    }

    public List<PurchaseItem> findPurchaseItemEntities(int maxResults, int firstResult) {
        return findPurchaseItemEntities(false, maxResults, firstResult);
    }

    private List<PurchaseItem> findPurchaseItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PurchaseItem.class));
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

    public PurchaseItem findPurchaseItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PurchaseItem.class, id);
        } finally {
            em.close();
        }
    }

    public int getPurchaseItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PurchaseItem> rt = cq.from(PurchaseItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
