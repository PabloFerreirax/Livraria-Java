/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "tb_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buy.findAll", query = "SELECT b FROM Buy b"),
    @NamedQuery(name = "Buy.findByIdCompra", query = "SELECT b FROM Buy b WHERE b.idCompra = :idCompra"),
    @NamedQuery(name = "Buy.findByIdCliente", query = "SELECT b FROM Buy b WHERE b.idCliente = :idCliente"),
    @NamedQuery(name = "Buy.findByDataCompra", query = "SELECT b FROM Buy b WHERE b.dataCompra = :dataCompra"),
    @NamedQuery(name = "Buy.findByValorTotal", query = "SELECT b FROM Buy b WHERE b.valorTotal = :valorTotal")})
public class Buy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_compra")
    private Integer idCompra;
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @Column(name = "data_compra")
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_total")
    private Double valorTotal;
    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id_forma_pagamento")
    @ManyToOne(optional = false)
    private PaymentMethod idFormaPagamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompra")
    private Collection<PurchaseItem> purchaseItemCollection;

    public Buy() {
    }

    public Buy(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Buy(Integer idCompra, int idCliente, Date dataCompra, Double valorTotal) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public PaymentMethod getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(PaymentMethod idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    @XmlTransient
    public Collection<PurchaseItem> getPurchaseItemCollection() {
        return purchaseItemCollection;
    }

    public void setPurchaseItemCollection(Collection<PurchaseItem> purchaseItemCollection) {
        this.purchaseItemCollection = purchaseItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buy)) {
            return false;
        }
        Buy other = (Buy) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookstore.project.model.Buy[ idCompra=" + idCompra + " ]";
    }
    
}
