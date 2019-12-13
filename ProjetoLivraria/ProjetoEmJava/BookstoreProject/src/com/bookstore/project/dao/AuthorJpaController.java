/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.dao;

import com.bookstore.project.dao.exceptions.IllegalOrphanException;
import com.bookstore.project.dao.exceptions.NonexistentEntityException;
import com.bookstore.project.model.Author;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bookstore.project.model.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pablo
 */
public class AuthorJpaController implements Serializable {

    public AuthorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Author author) {
        if (author.getBookCollection() == null) {
            author.setBookCollection(new ArrayList<Book>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Book> attachedBookCollection = new ArrayList<Book>();
            for (Book bookCollectionBookToAttach : author.getBookCollection()) {
                bookCollectionBookToAttach = em.getReference(bookCollectionBookToAttach.getClass(), bookCollectionBookToAttach.getIdLivro());
                attachedBookCollection.add(bookCollectionBookToAttach);
            }
            author.setBookCollection(attachedBookCollection);
            em.persist(author);
            for (Book bookCollectionBook : author.getBookCollection()) {
                Author oldIdAutorOfBookCollectionBook = bookCollectionBook.getIdAutor();
                bookCollectionBook.setIdAutor(author);
                bookCollectionBook = em.merge(bookCollectionBook);
                if (oldIdAutorOfBookCollectionBook != null) {
                    oldIdAutorOfBookCollectionBook.getBookCollection().remove(bookCollectionBook);
                    oldIdAutorOfBookCollectionBook = em.merge(oldIdAutorOfBookCollectionBook);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Author author) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Author persistentAuthor = em.find(Author.class, author.getIdAutor());
            Collection<Book> bookCollectionOld = persistentAuthor.getBookCollection();
            Collection<Book> bookCollectionNew = author.getBookCollection();
            List<String> illegalOrphanMessages = null;
            for (Book bookCollectionOldBook : bookCollectionOld) {
                if (!bookCollectionNew.contains(bookCollectionOldBook)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Book " + bookCollectionOldBook + " since its idAutor field is not nullable.");
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
            author.setBookCollection(bookCollectionNew);
            author = em.merge(author);
            for (Book bookCollectionNewBook : bookCollectionNew) {
                if (!bookCollectionOld.contains(bookCollectionNewBook)) {
                    Author oldIdAutorOfBookCollectionNewBook = bookCollectionNewBook.getIdAutor();
                    bookCollectionNewBook.setIdAutor(author);
                    bookCollectionNewBook = em.merge(bookCollectionNewBook);
                    if (oldIdAutorOfBookCollectionNewBook != null && !oldIdAutorOfBookCollectionNewBook.equals(author)) {
                        oldIdAutorOfBookCollectionNewBook.getBookCollection().remove(bookCollectionNewBook);
                        oldIdAutorOfBookCollectionNewBook = em.merge(oldIdAutorOfBookCollectionNewBook);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = author.getIdAutor();
                if (findAuthor(id) == null) {
                    throw new NonexistentEntityException("The author with id " + id + " no longer exists.");
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
            Author author;
            try {
                author = em.getReference(Author.class, id);
                author.getIdAutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The author with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Book> bookCollectionOrphanCheck = author.getBookCollection();
            for (Book bookCollectionOrphanCheckBook : bookCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Author (" + author + ") cannot be destroyed since the Book " + bookCollectionOrphanCheckBook + " in its bookCollection field has a non-nullable idAutor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(author);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Author> findAuthorEntities() {
        return findAuthorEntities(true, -1, -1);
    }

    public List<Author> findAuthorEntities(int maxResults, int firstResult) {
        return findAuthorEntities(false, maxResults, firstResult);
    }

    private List<Author> findAuthorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Author.class));
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

    public Author findAuthor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Author.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuthorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Author> rt = cq.from(Author.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
