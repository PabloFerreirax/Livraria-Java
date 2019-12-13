/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "tb_forma_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p"),
    @NamedQuery(name = "PaymentMethod.findByIdFormaPagamento", query = "SELECT p FROM PaymentMethod p WHERE p.idFormaPagamento = :idFormaPagamento"),
    @NamedQuery(name = "PaymentMethod.findByNmFormaPagamento", query = "SELECT p FROM PaymentMethod p WHERE p.nmFormaPagamento = :nmFormaPagamento")})
public class PaymentMethod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_forma_pagamento")
    private Integer idFormaPagamento;
    @Column(name = "nm_forma_pagamento")
    private String nmFormaPagamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormaPagamento")
    private Collection<Buy> buyCollection;

    public PaymentMethod() {
    }

    public PaymentMethod(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getNmFormaPagamento() {
        return nmFormaPagamento;
    }

    public void setNmFormaPagamento(String nmFormaPagamento) {
        this.nmFormaPagamento = nmFormaPagamento;
    }

    @XmlTransient
    public Collection<Buy> getBuyCollection() {
        return buyCollection;
    }

    public void setBuyCollection(Collection<Buy> buyCollection) {
        this.buyCollection = buyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaPagamento != null ? idFormaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.idFormaPagamento == null && other.idFormaPagamento != null) || (this.idFormaPagamento != null && !this.idFormaPagamento.equals(other.idFormaPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNmFormaPagamento();
    }
    
}
