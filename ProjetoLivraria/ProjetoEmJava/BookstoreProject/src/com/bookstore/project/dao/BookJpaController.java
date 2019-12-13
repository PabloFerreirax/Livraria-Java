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
import com.bookstore.project.model.Author;
import com.bookstore.project.model.Book;
import com.bookstore.project.model.Category;
import com.bookstore.project.model.PublishingCompany;
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
public class BookJpaController implements Serializable {

    public BookJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Book book) {
        if (book.getPurchaseItemCollection() == null) {
            book.setPurchaseItemCollection(new ArrayList<PurchaseItem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Author idAutor = book.getIdAutor();
            if (idAutor != null) {
                idAutor = em.getReference(idAutor.getClass(), idAutor.getIdAutor());
                book.setIdAutor(idAutor);
            }
            Category idCategoria = book.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getIdCategoria());
                book.setIdCategoria(idCategoria);
            }
            PublishingCompany idEditora = book.getIdEditora();
            if (idEditora != null) {
                idEditora = em.getReference(idEditora.getClass(), idEditora.getIdEditora());
                book.setIdEditora(idEditora);
            }
            Collection<PurchaseItem> attachedPurchaseItemCollection = new ArrayList<PurchaseItem>();
            for (PurchaseItem purchaseItemCollectionPurchaseItemToAttach : book.getPurchaseItemCollection()) {
                purchaseItemCollectionPurchaseItemToAttach = em.getReference(purchaseItemCollectionPurchaseItemToAttach.getClass(), purchaseItemCollectionPurchaseItemToAttach.getIdItemCompra());
                attachedPurchaseItemCollection.add(purchaseItemCollectionPurchaseItemToAttach);
            }
            book.setPurchaseItemCollection(attachedPurchaseItemCollection);
            em.persist(book);
            if (idAutor != null) {
                idAutor.getBookCollection().add(book);
                idAutor = em.merge(idAutor);
            }
            if (idCategoria != null) {
                idCategoria.getBookCollection().add(book);
                idCategoria = em.merge(idCategoria);
            }
            if (idEditora != null) {
                idEditora.getBookCollection().add(book);
                idEditora = em.merge(idEditora);
            }
            for (PurchaseItem purchaseItemCollectionPurchaseItem : book.getPurchaseItemCollection()) {
                Book oldIdLivroOfPurchaseItemCollectionPurchaseItem = purchaseItemCollectionPurchaseItem.getIdLivro();
                purchaseItemCollectionPurchaseItem.setIdLivro(book);
                purchaseItemCollectionPurchaseItem = em.merge(purchaseItemCollectionPurchaseItem);
                if (oldIdLivroOfPurchaseItemCollectionPurchaseItem != null) {
                    oldIdLivroOfPurchaseItemCollectionPurchaseItem.getPurchaseItemCollection().remove(purchaseItemCollectionPurchaseItem);
                    oldIdLivroOfPurchaseItemCollectionPurchaseItem = em.merge(oldIdLivroOfPurchaseItemCollectionPurchaseItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Book book) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book persistentBook = em.find(Book.class, book.getIdLivro());
            Author idAutorOld = persistentBook.getIdAutor();
            Author idAutorNew = book.getIdAutor();
            Category idCategoriaOld = persistentBook.getIdCategoria();
            Category idCategoriaNew = book.getIdCategoria();
            PublishingCompany idEditoraOld = persistentBook.getIdEditora();
            PublishingCompany idEditoraNew = book.getIdEditora();
            Collection<PurchaseItem> purchaseItemCollectionOld = persistentBook.getPurchaseItemCollection();
            Collection<PurchaseItem> purchaseItemCollectionNew = book.getPurchaseItemCollection();
            List<String> illegalOrphanMessages = null;
            for (PurchaseItem purchaseItemCollectionOldPurchaseItem : purchaseItemCollectionOld) {
                if (!purchaseItemCollectionNew.contains(purchaseItemCollectionOldPurchaseItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PurchaseItem " + purchaseItemCollectionOldPurchaseItem + " since its idLivro field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAutorNew != null) {
                idAutorNew = em.getReference(idAutorNew.getClass(), idAutorNew.getIdAutor());
                book.setIdAutor(idAutorNew);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getIdCategoria());
                book.setIdCategoria(idCategoriaNew);
            }
            if (idEditoraNew != null) {
                idEditoraNew = em.getReference(idEditoraNew.getClass(), idEditoraNew.getIdEditora());
                book.setIdEditora(idEditoraNew);
            }
            Collection<PurchaseItem> attachedPurchaseItemCollectionNew = new ArrayList<PurchaseItem>();
            for (PurchaseItem purchaseItemCollectionNewPurchaseItemToAttach : purchaseItemCollectionNew) {
                purchaseItemCollectionNewPurchaseItemToAttach = em.getReference(purchaseItemCollectionNewPurchaseItemToAttach.getClass(), purchaseItemCollectionNewPurchaseItemToAttach.getIdItemCompra());
                attachedPurchaseItemCollectionNew.add(purchaseItemCollectionNewPurchaseItemToAttach);
            }
            purchaseItemCollectionNew = attachedPurchaseItemCollectionNew;
            book.setPurchaseItemCollection(purchaseItemCollectionNew);
            book = em.merge(book);
            if (idAutorOld != null && !idAutorOld.equals(idAutorNew)) {
                idAutorOld.getBookCollection().remove(book);
                idAutorOld = em.merge(idAutorOld);
            }
            if (idAutorNew != null && !idAutorNew.equals(idAutorOld)) {
                idAutorNew.getBookCollection().add(book);
                idAutorNew = em.merge(idAutorNew);
            }
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getBookCollection().remove(book);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getBookCollection().add(book);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            if (idEditoraOld != null && !idEditoraOld.equals(idEditoraNew)) {
                idEditoraOld.getBookCollection().remove(book);
                idEditoraOld = em.merge(idEditoraOld);
            }
            if (idEditoraNew != null && !idEditoraNew.equals(idEditoraOld)) {
                idEditoraNew.getBookCollection().add(book);
                idEditoraNew = em.merge(idEditoraNew);
            }
            for (PurchaseItem purchaseItemCollectionNewPurchaseItem : purchaseItemCollectionNew) {
                if (!purchaseItemCollectionOld.contains(purchaseItemCollectionNewPurchaseItem)) {
                    Book oldIdLivroOfPurchaseItemCollectionNewPurchaseItem = purchaseItemCollectionNewPurchaseItem.getIdLivro();
                    purchaseItemCollectionNewPurchaseItem.setIdLivro(book);
                    purchaseItemCollectionNewPurchaseItem = em.merge(purchaseItemCollectionNewPurchaseItem);
                    if (oldIdLivroOfPurchaseItemCollectionNewPurchaseItem != null && !oldIdLivroOfPurchaseItemCollectionNewPurchaseItem.equals(book)) {
                        oldIdLivroOfPurchaseItemCollectionNewPurchaseItem.getPurchaseItemCollection().remove(purchaseItemCollectionNewPurchaseItem);
                        oldIdLivroOfPurchaseItemCollectionNewPurchaseItem = em.merge(oldIdLivroOfPurchaseItemCollectionNewPurchaseItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = book.getIdLivro();
                if (findBook(id) == null) {
                    throw new NonexistentEntityException("The book with id " + id + " no longer exists.");
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
            Book book;
            try {
                book = em.getReference(Book.class, id);
                book.getIdLivro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The book with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PurchaseItem> purchaseItemCollectionOrphanCheck = book.getPurchaseItemCollection();
            for (PurchaseItem purchaseItemCollectionOrphanCheckPurchaseItem : purchaseItemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Book (" + book + ") cannot be destroyed since the PurchaseItem " + purchaseItemCollectionOrphanCheckPurchaseItem + " in its purchaseItemCollection field has a non-nullable idLivro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Author idAutor = book.getIdAutor();
            if (idAutor != null) {
                idAutor.getBookCollection().remove(book);
                idAutor = em.merge(idAutor);
            }
            Category idCategoria = book.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getBookCollection().remove(book);
                idCategoria = em.merge(idCategoria);
            }
            PublishingCompany idEditora = book.getIdEditora();
            if (idEditora != null) {
                idEditora.getBookCollection().remove(book);
                idEditora = em.merge(idEditora);
            }
            em.remove(book);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Book> findBookEntities() {
        return findBookEntities(true, -1, -1);
    }

    public List<Book> findBookEntities(int maxResults, int firstResult) {
        return findBookEntities(false, maxResults, firstResult);
    }

    private List<Book> findBookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Book.class));
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

    public Book findBook(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Book> rt = cq.from(Book.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
