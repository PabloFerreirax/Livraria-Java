/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.dao;

import com.bookstore.project.dao.exceptions.IllegalOrphanException;
import com.bookstore.project.dao.exceptions.NonexistentEntityException;
import com.bookstore.project.model.Buy;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bookstore.project.model.PaymentMethod;
import com.bookstore.project.model.PurchaseItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pablo
 */
public class BuyJpaController implements Serializable {

    public BuyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Buy buy) {
        if (buy.getPurchaseItemCollection() == null) {
            buy.setPurchaseItemCollection(new ArrayList<PurchaseItem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaymentMethod idFormaPagamento = buy.getIdFormaPagamento();
            if (idFormaPagamento != null) {
                idFormaPagamento = em.getReference(idFormaPagamento.getClass(), idFormaPagamento.getIdFormaPagamento());
                buy.setIdFormaPagamento(idFormaPagamento);
            }
            Collection<PurchaseItem> attachedPurchaseItemCollection = new ArrayList<PurchaseItem>();
            for (PurchaseItem purchaseItemCollectionPurchaseItemToAttach : buy.getPurchaseItemCollection()) {
                purchaseItemCollectionPurchaseItemToAttach = em.getReference(purchaseItemCollectionPurchaseItemToAttach.getClass(), purchaseItemCollectionPurchaseItemToAttach.getIdItemCompra());
                attachedPurchaseItemCollection.add(purchaseItemCollectionPurchaseItemToAttach);
            }
            buy.setPurchaseItemCollection(attachedPurchaseItemCollection);
            em.persist(buy);
            if (idFormaPagamento != null) {
                idFormaPagamento.getBuyCollection().add(buy);
                idFormaPagamento = em.merge(idFormaPagamento);
            }
            for (PurchaseItem purchaseItemCollectionPurchaseItem : buy.getPurchaseItemCollection()) {
                Buy oldIdCompraOfPurchaseItemCollectionPurchaseItem = purchaseItemCollectionPurchaseItem.getIdCompra();
                purchaseItemCollectionPurchaseItem.setIdCompra(buy);
                purchaseItemCollectionPurchaseItem = em.merge(purchaseItemCollectionPurchaseItem);
                if (oldIdCompraOfPurchaseItemCollectionPurchaseItem != null) {
                    oldIdCompraOfPurchaseItemCollectionPurchaseItem.getPurchaseItemCollection().remove(purchaseItemCollectionPurchaseItem);
                    oldIdCompraOfPurchaseItemCollectionPurchaseItem = em.merge(oldIdCompraOfPurchaseItemCollectionPurchaseItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Buy buy) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Buy persistentBuy = em.find(Buy.class, buy.getIdCompra());
            PaymentMethod idFormaPagamentoOld = persistentBuy.getIdFormaPagamento();
            PaymentMethod idFormaPagamentoNew = buy.getIdFormaPagamento();
            Collection<PurchaseItem> purchaseItemCollectionOld = persistentBuy.getPurchaseItemCollection();
            Collection<PurchaseItem> purchaseItemCollectionNew = buy.getPurchaseItemCollection();
            List<String> illegalOrphanMessages = null;
            for (PurchaseItem purchaseItemCollectionOldPurchaseItem : purchaseItemCollectionOld) {
                if (!purchaseItemCollectionNew.contains(purchaseItemCollectionOldPurchaseItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PurchaseItem " + purchaseItemCollectionOldPurchaseItem + " since its idCompra field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idFormaPagamentoNew != null) {
                idFormaPagamentoNew = em.getReference(idFormaPagamentoNew.getClass(), idFormaPagamentoNew.getIdFormaPagamento());
                buy.setIdFormaPagamento(idFormaPagamentoNew);
            }
            Collection<PurchaseItem> attachedPurchaseItemCollectionNew = new ArrayList<PurchaseItem>();
            for (PurchaseItem purchaseItemCollectionNewPurchaseItemToAttach : purchaseItemCollectionNew) {
                purchaseItemCollectionNewPurchaseItemToAttach = em.getReference(purchaseItemCollectionNewPurchaseItemToAttach.getClass(), purchaseItemCollectionNewPurchaseItemToAttach.getIdItemCompra());
                attachedPurchaseItemCollectionNew.add(purchaseItemCollectionNewPurchaseItemToAttach);
            }
            purchaseItemCollectionNew = attachedPurchaseItemCollectionNew;
            buy.setPurchaseItemCollection(purchaseItemCollectionNew);
            buy = em.merge(buy);
            if (idFormaPagamentoOld != null && !idFormaPagamentoOld.equals(idFormaPagamentoNew)) {
                idFormaPagamentoOld.getBuyCollection().remove(buy);
                idFormaPagamentoOld = em.merge(idFormaPagamentoOld);
            }
            if (idFormaPagamentoNew != null && !idFormaPagamentoNew.equals(idFormaPagamentoOld)) {
                idFormaPagamentoNew.getBuyCollection().add(buy);
                idFormaPagamentoNew = em.merge(idFormaPagamentoNew);
            }
            for (PurchaseItem purchaseItemCollectionNewPurchaseItem : purchaseItemCollectionNew) {
                if (!purchaseItemCollectionOld.contains(purchaseItemCollectionNewPurchaseItem)) {
                    Buy oldIdCompraOfPurchaseItemCollectionNewPurchaseItem = purchaseItemCollectionNewPurchaseItem.getIdCompra();
                    purchaseItemCollectionNewPurchaseItem.setIdCompra(buy);
                    purchaseItemCollectionNewPurchaseItem = em.merge(purchaseItemCollectionNewPurchaseItem);
                    if (oldIdCompraOfPurchaseItemCollectionNewPurchaseItem != null && !oldIdCompraOfPurchaseItemCollectionNewPurchaseItem.equals(buy)) {
                        oldIdCompraOfPurchaseItemCollectionNewPurchaseItem.getPurchaseItemCollection().remove(purchaseItemCollectionNewPurchaseItem);
                        oldIdCompraOfPurchaseItemCollectionNewPurchaseItem = em.merge(oldIdCompraOfPurchaseItemCollectionNewPurchaseItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buy.getIdCompra();
                if (findBuy(id) == null) {
                    throw new NonexistentEntityException("The buy with id " + id + " no longer exists.");
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
            Buy buy;
            try {
                buy = em.getReference(Buy.class, id);
                buy.getIdCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buy with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PurchaseItem> purchaseItemCollectionOrphanCheck = buy.getPurchaseItemCollection();
            for (PurchaseItem purchaseItemCollectionOrphanCheckPurchaseItem : purchaseItemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Buy (" + buy + ") cannot be destroyed since the PurchaseItem " + purchaseItemCollectionOrphanCheckPurchaseItem + " in its purchaseItemCollection field has a non-nullable idCompra field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            PaymentMethod idFormaPagamento = buy.getIdFormaPagamento();
            if (idFormaPagamento != null) {
                idFormaPagamento.getBuyCollection().remove(buy);
                idFormaPagamento = em.merge(idFormaPagamento);
            }
            em.remove(buy);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Buy> findBuyEntities() {
        return findBuyEntities(true, -1, -1);
    }

    public List<Buy> findBuyEntities(int maxResults, int firstResult) {
        return findBuyEntities(false, maxResults, firstResult);
    }

    private List<Buy> findBuyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Buy.class));
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

    public Buy findBuy(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Buy.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Buy> rt = cq.from(Buy.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
