/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "tb_item_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseItem.findAll", query = "SELECT p FROM PurchaseItem p"),
    @NamedQuery(name = "PurchaseItem.findByIdItemCompra", query = "SELECT p FROM PurchaseItem p WHERE p.idItemCompra = :idItemCompra"),
    @NamedQuery(name = "PurchaseItem.findByValorCompra", query = "SELECT p FROM PurchaseItem p WHERE p.valorCompra = :valorCompra"),
    @NamedQuery(name = "PurchaseItem.findByValorUni", query = "SELECT p FROM PurchaseItem p WHERE p.valorUni = :valorUni"),
    @NamedQuery(name = "PurchaseItem.findByQtdCompra", query = "SELECT p FROM PurchaseItem p WHERE p.qtdCompra = :qtdCompra")})
public class PurchaseItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_item_compra")
    private Integer idItemCompra;
    @Basic(optional = false)
    @Column(name = "valor_compra")
    private Double valorCompra;
    @Basic(optional = false)
    @Column(name = "valor_uni")
    private Double valorUni;
    @Basic(optional = false)
    @Column(name = "qtd_compra")
    private int qtdCompra;
    @JoinColumn(name = "id_compra", referencedColumnName = "id_compra")
    @ManyToOne(optional = false)
    private Buy idCompra;
    @JoinColumn(name = "id_livro", referencedColumnName = "id_livro")
    @ManyToOne(optional = false)
    private Book idLivro;

    public PurchaseItem() {
    }

    public PurchaseItem(Integer idItemCompra) {
        this.idItemCompra = idItemCompra;
    }

    public PurchaseItem(Integer idItemCompra, Double valorCompra, Double valorUni, int qtdCompra) {
        this.idItemCompra = idItemCompra;
        this.valorCompra = valorCompra;
        this.valorUni = valorUni;
        this.qtdCompra = qtdCompra;
    }

    public Integer getIdItemCompra() {
        return idItemCompra;
    }

    public void setIdItemCompra(Integer idItemCompra) {
        this.idItemCompra = idItemCompra;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Double getValorUni() {
        return valorUni;
    }

    public void setValorUni(Double valorUni) {
        this.valorUni = valorUni;
    }

    public int getQtdCompra() {
        return qtdCompra;
    }

    public void setQtdCompra(int qtdCompra) {
        this.qtdCompra = qtdCompra;
    }

    public Buy getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Buy idCompra) {
        this.idCompra = idCompra;
    }

    public Book getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Book idLivro) {
        this.idLivro = idLivro;
    }
    
    public void setIdItemCompra(Buy buy) {
        this.idCompra = buy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemCompra != null ? idItemCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseItem)) {
            return false;
        }
        PurchaseItem other = (PurchaseItem) object;
        if ((this.idItemCompra == null && other.idItemCompra != null) || (this.idItemCompra != null && !this.idItemCompra.equals(other.idItemCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookstore.project.model.PurchaseItem[ idItemCompra=" + idItemCompra + " ]";
    }

    
    
}
