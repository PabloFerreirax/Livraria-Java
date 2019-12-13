/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.JLabel;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "tb_livro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByIdLivro", query = "SELECT b FROM Book b WHERE b.idLivro = :idLivro"),
    @NamedQuery(name = "Book.findByDescLivro", query = "SELECT b FROM Book b WHERE b.descLivro = :descLivro"),
    @NamedQuery(name = "Book.findByNmLivro", query = "SELECT b FROM Book b WHERE b.nmLivro = :nmLivro"),
    @NamedQuery(name = "Book.findByIdioma", query = "SELECT b FROM Book b WHERE b.idioma = :idioma"),
    @NamedQuery(name = "Book.findByQtdPagina", query = "SELECT b FROM Book b WHERE b.qtdPagina = :qtdPagina"),
    @NamedQuery(name = "Book.findByQtdEstoque", query = "SELECT b FROM Book b WHERE b.qtdEstoque = :qtdEstoque"),
    @NamedQuery(name = "Book.findByValorLivro", query = "SELECT b FROM Book b WHERE b.valorLivro = :valorLivro"),
    @NamedQuery(name = "Book.findByAnoLivro", query = "SELECT b FROM Book b WHERE b.anoLivro = :anoLivro")})
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_livro")
    private Integer idLivro;
    @Basic(optional = false)
    @Column(name = "desc_livro")
    private String descLivro;
    @Basic(optional = false)
    @Column(name = "nm_livro")
    private String nmLivro;
    @Basic(optional = false)
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "qtd_pagina")
    private Integer qtdPagina;
    @Basic(optional = false)
    @Column(name = "qtd_estoque")
    private int qtdEstoque;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_livro")
    private Double valorLivro;
    @Basic(optional = false)
    @Column(name = "ano_livro")
    private String anoLivro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLivro")
    private Collection<PurchaseItem> purchaseItemCollection;
    @JoinColumn(name = "id_autor", referencedColumnName = "id_autor")
    @ManyToOne(optional = false)
    private Author idAutor;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = false)
    private Category idCategoria;
    @JoinColumn(name = "id_editora", referencedColumnName = "id_editora")
    @ManyToOne(optional = false)
    private PublishingCompany idEditora;

    public Book() {
    }

    public Book(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Book(Integer idLivro, String descLivro, String nmLivro, String idioma, int qtdEstoque, Double valorLivro, String anoLivro) {
        this.idLivro = idLivro;
        this.descLivro = descLivro;
        this.nmLivro = nmLivro;
        this.idioma = idioma;
        this.qtdEstoque = qtdEstoque;
        this.valorLivro = valorLivro;
        this.anoLivro = anoLivro;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getDescLivro() {
        return descLivro;
    }

    public void setDescLivro(String descLivro) {
        this.descLivro = descLivro;
    }

    public String getNmLivro() {
        return nmLivro;
    }

    public void setNmLivro(String nmLivro) {
        this.nmLivro = nmLivro;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getQtdPagina() {
        return qtdPagina;
    }

    public void setQtdPagina(Integer qtdPagina) {
        this.qtdPagina = qtdPagina;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getValorLivro() {
        return valorLivro;
    }

    public void setValorLivro(Double valorLivro) {
        this.valorLivro = valorLivro;
    }

    public String getAnoLivro() {
        return anoLivro;
    }

    public void setAnoLivro(String anoLivro) {
        this.anoLivro = anoLivro;
    }

    @XmlTransient
    public Collection<PurchaseItem> getPurchaseItemCollection() {
        return purchaseItemCollection;
    }

    public void setPurchaseItemCollection(Collection<PurchaseItem> purchaseItemCollection) {
        this.purchaseItemCollection = purchaseItemCollection;
    }

    public Author getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Author idAutor) {
        this.idAutor = idAutor;
    }

    public Category getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Category idCategoria) {
        this.idCategoria = idCategoria;
    }

    public PublishingCompany getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(PublishingCompany idEditora) {
        this.idEditora = idEditora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivro != null ? idLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.idLivro == null && other.idLivro != null) || (this.idLivro != null && !this.idLivro.equals(other.idLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookstore.project.model.Book[ idLivro=" + idLivro + " ]";
    }

    
}
