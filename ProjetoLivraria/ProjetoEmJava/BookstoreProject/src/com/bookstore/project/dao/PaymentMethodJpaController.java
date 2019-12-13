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
import com.bookstore.project.model.Buy;
import com.bookstore.project.model.PaymentMethod;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pablo
 */
public class PaymentMethodJpaController implements Serializable {

    public PaymentMethodJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaymentMethod paymentMethod) {
        if (paymentMethod.getBuyCollection() == null) {
            paymentMethod.setBuyCollection(new ArrayList<Buy>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Buy> attachedBuyCollection = new ArrayList<Buy>();
            for (Buy buyCollectionBuyToAttach : paymentMethod.getBuyCollection()) {
                buyCollectionBuyToAttach = em.getReference(buyCollectionBuyToAttach.getClass(), buyCollectionBuyToAttach.getIdCompra());
                attachedBuyCollection.add(buyCollectionBuyToAttach);
            }
            paymentMethod.setBuyCollection(attachedBuyCollection);
            em.persist(paymentMethod);
            for (Buy buyCollectionBuy : paymentMethod.getBuyCollection()) {
                PaymentMethod oldIdFormaPagamentoOfBuyCollectionBuy = buyCollectionBuy.getIdFormaPagamento();
                buyCollectionBuy.setIdFormaPagamento(paymentMethod);
                buyCollectionBuy = em.merge(buyCollectionBuy);
                if (oldIdFormaPagamentoOfBuyCollectionBuy != null) {
                    oldIdFormaPagamentoOfBuyCollectionBuy.getBuyCollection().remove(buyCollectionBuy);
                    oldIdFormaPagamentoOfBuyCollectionBuy = em.merge(oldIdFormaPagamentoOfBuyCollectionBuy);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaymentMethod paymentMethod) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaymentMethod persistentPaymentMethod = em.find(PaymentMethod.class, paymentMethod.getIdFormaPagamento());
            Collection<Buy> buyCollectionOld = persistentPaymentMethod.getBuyCollection();
            Collection<Buy> buyCollectionNew = paymentMethod.getBuyCollection();
            List<String> illegalOrphanMessages = null;
            for (Buy buyCollectionOldBuy : buyCollectionOld) {
                if (!buyCollectionNew.contains(buyCollectionOldBuy)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Buy " + buyCollectionOldBuy + " since its idFormaPagamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Buy> attachedBuyCollectionNew = new ArrayList<Buy>();
            for (Buy buyCollectionNewBuyToAttach : buyCollectionNew) {
                buyCollectionNewBuyToAttach = em.getReference(buyCollectionNewBuyToAttach.getClass(), buyCollectionNewBuyToAttach.getIdCompra());
                attachedBuyCollectionNew.add(buyCollectionNewBuyToAttach);
            }
            buyCollectionNew = attachedBuyCollectionNew;
            paymentMethod.setBuyCollection(buyCollectionNew);
            paymentMethod = em.merge(paymentMethod);
            for (Buy buyCollectionNewBuy : buyCollectionNew) {
                if (!buyCollectionOld.contains(buyCollectionNewBuy)) {
                    PaymentMethod oldIdFormaPagamentoOfBuyCollectionNewBuy = buyCollectionNewBuy.getIdFormaPagamento();
                    buyCollectionNewBuy.setIdFormaPagamento(paymentMethod);
                    buyCollectionNewBuy = em.merge(buyCollectionNewBuy);
                    if (oldIdFormaPagamentoOfBuyCollectionNewBuy != null && !oldIdFormaPagamentoOfBuyCollectionNewBuy.equals(paymentMethod)) {
                        oldIdFormaPagamentoOfBuyCollectionNewBuy.getBuyCollection().remove(buyCollectionNewBuy);
                        oldIdFormaPagamentoOfBuyCollectionNewBuy = em.merge(oldIdFormaPagamentoOfBuyCollectionNewBuy);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paymentMethod.getIdFormaPagamento();
                if (findPaymentMethod(id) == null) {
                    throw new NonexistentEntityException("The paymentMethod with id " + id + " no longer exists.");
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
            PaymentMethod paymentMethod;
            try {
                paymentMethod = em.getReference(PaymentMethod.class, id);
                paymentMethod.getIdFormaPagamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paymentMethod with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Buy> buyCollectionOrphanCheck = paymentMethod.getBuyCollection();
            for (Buy buyCollectionOrphanCheckBuy : buyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PaymentMethod (" + paymentMethod + ") cannot be destroyed since the Buy " + buyCollectionOrphanCheckBuy + " in its buyCollection field has a non-nullable idFormaPagamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(paymentMethod);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaymentMethod> findPaymentMethodEntities() {
        return findPaymentMethodEntities(true, -1, -1);
    }

    public List<PaymentMethod> findPaymentMethodEntities(int maxResults, int firstResult) {
        return findPaymentMethodEntities(false, maxResults, firstResult);
    }

    private List<PaymentMethod> findPaymentMethodEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaymentMethod.class));
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

    public PaymentMethod findPaymentMethod(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaymentMethod.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentMethodCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaymentMethod> rt = cq.from(PaymentMethod.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
