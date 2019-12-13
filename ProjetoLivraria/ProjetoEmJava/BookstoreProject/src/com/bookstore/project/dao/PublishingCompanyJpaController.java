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
import com.bookstore.project.model.Book;
import com.bookstore.project.model.PublishingCompany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pablo
 */
public class PublishingCompanyJpaController implements Serializable {

    public PublishingCompanyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PublishingCompany publishingCompany) {
        if (publishingCompany.getBookCollection() == null) {
            publishingCompany.setBookCollection(new ArrayList<Book>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Book> attachedBookCollection = new ArrayList<Book>();
            for (Book bookCollectionBookToAttach : publishingCompany.getBookCollection()) {
                bookCollectionBookToAttach = em.getReference(bookCollectionBookToAttach.getClass(), bookCollectionBookToAttach.getIdLivro());
                attachedBookCollection.add(bookCollectionBookToAttach);
            }
            publishingCompany.setBookCollection(attachedBookCollection);
            em.persist(publishingCompany);
            for (Book bookCollectionBook : publishingCompany.getBookCollection()) {
                PublishingCompany oldIdEditoraOfBookCollectionBook = bookCollectionBook.getIdEditora();
                bookCollectionBook.setIdEditora(publishingCompany);
                bookCollectionBook = em.merge(bookCollectionBook);
                if (oldIdEditoraOfBookCollectionBook != null) {
                    oldIdEditoraOfBookCollectionBook.getBookCollection().remove(bookCollectionBook);
                    oldIdEditoraOfBookCollectionBook = em.merge(oldIdEditoraOfBookCollectionBook);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PublishingCompany publishingCompany) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PublishingCompany persistentPublishingCompany = em.find(PublishingCompany.class, publishingCompany.getIdEditora());
            Collection<Book> bookCollectionOld = persistentPublishingCompany.getBookCollection();
            Collection<Book> bookCollectionNew = publishingCompany.getBookCollection();
            List<String> illegalOrphanMessages = null;
            for (Book bookCollectionOldBook : bookCollectionOld) {
                if (!bookCollectionNew.contains(bookCollectionOldBook)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Book " + bookCollectionOldBook + " since its idEditora field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Book> attachedBookCollectionNew = new ArrayList<Book>();
            for (Book bookCollectionNewBookToAttach : bookCollectionNew) {
                bookCollectionNewBookToAttach = em.getReference(bookCollectionNewBookToAttach.getClass(), bookCollectionNewBookToAttach.getIdLivro());
                attachedBookCollectionNew.add(bookCollectionNewBookToAttach);
            }
            bookCollectionNew = attachedBookCollectionNew;
            publishingCompany.setBookCollection(bookCollectionNew);
            publishingCompany = em.merge(publishingCompany);
            for (Book bookCollectionNewBook : bookCollectionNew) {
                if (!bookCollectionOld.contains(bookCollectionNewBook)) {
                    PublishingCompany oldIdEditoraOfBookCollectionNewBook = bookCollectionNewBook.getIdEditora();
                    bookCollectionNewBook.setIdEditora(publishingCompany);
                    bookCollectionNewBook = em.merge(bookCollectionNewBook);
                    if (oldIdEditoraOfBookCollectionNewBook != null && !oldIdEditoraOfBookCollectionNewBook.equals(publishingCompany)) {
                        oldIdEditoraOfBookCollectionNewBook.getBookCollection().remove(bookCollectionNewBook);
                        oldIdEditoraOfBookCollectionNewBook = em.merge(oldIdEditoraOfBookCollectionNewBook);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = publishingCompany.getIdEditora();
                if (findPublishingCompany(id) == null) {
                    throw new NonexistentEntityException("The publishingCompany with id " + id + " no longer exists.");
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
            PublishingCompany publishingCompany;
            try {
                publishingCompany = em.getReference(PublishingCompany.class, id);
                publishingCompany.getIdEditora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publishingCompany with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Book> bookCollectionOrphanCheck = publishingCompany.getBookCollection();
            for (Book bookCollectionOrphanCheckBook : bookCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PublishingCompany (" + publishingCompany + ") cannot be destroyed since the Book " + bookCollectionOrphanCheckBook + " in its bookCollection field has a non-nullable idEditora field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(publishingCompany);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PublishingCompany> findPublishingCompanyEntities() {
        return findPublishingCompanyEntities(true, -1, -1);
    }

    public List<PublishingCompany> findPublishingCompanyEntities(int maxResults, int firstResult) {
        return findPublishingCompanyEntities(false, maxResults, firstResult);
    }

    private List<PublishingCompany> findPublishingCompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PublishingCompany.class));
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

    public PublishingCompany findPublishingCompany(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PublishingCompany.class, id);
        } finally {
            em.close();
        }
    }

    public int getPublishingCompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PublishingCompany> rt = cq.from(PublishingCompany.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
