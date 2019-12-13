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
@Table(name = "tb_editora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublishingCompany.findAll", query = "SELECT p FROM PublishingCompany p"),
    @NamedQuery(name = "PublishingCompany.findByIdEditora", query = "SELECT p FROM PublishingCompany p WHERE p.idEditora = :idEditora"),
    @NamedQuery(name = "PublishingCompany.findByNmEditora", query = "SELECT p FROM PublishingCompany p WHERE p.nmEditora = :nmEditora"),
    @NamedQuery(name = "PublishingCompany.findByCnpj", query = "SELECT p FROM PublishingCompany p WHERE p.cnpj = :cnpj")})
public class PublishingCompany implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_editora")
    private Integer idEditora;
    @Basic(optional = false)
    @Column(name = "nm_editora")
    private String nmEditora;
    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEditora")
    private Collection<Book> bookCollection;

    public PublishingCompany() {
    }

    public PublishingCompany(Integer idEditora) {
        this.idEditora = idEditora;
    }

    public PublishingCompany(Integer idEditora, String nmEditora, String cnpj) {
        this.idEditora = idEditora;
        this.nmEditora = nmEditora;
        this.cnpj = cnpj;
    }

    public Integer getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(Integer idEditora) {
        this.idEditora = idEditora;
    }

    public String getNmEditora() {
        return nmEditora;
    }

    public void setNmEditora(String nmEditora) {
        this.nmEditora = nmEditora;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @XmlTransient
    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEditora != null ? idEditora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublishingCompany)) {
            return false;
        }
        PublishingCompany other = (PublishingCompany) object;
        if ((this.idEditora == null && other.idEditora != null) || (this.idEditora != null && !this.idEditora.equals(other.idEditora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNmEditora();
    }
    
}
