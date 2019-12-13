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
@Table(name = "tb_endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findByIdEndereco", query = "SELECT a FROM Address a WHERE a.idEndereco = :idEndereco"),
    @NamedQuery(name = "Address.findByBairro", query = "SELECT a FROM Address a WHERE a.bairro = :bairro"),
    @NamedQuery(name = "Address.findByEstado", query = "SELECT a FROM Address a WHERE a.estado = :estado"),
    @NamedQuery(name = "Address.findByCep", query = "SELECT a FROM Address a WHERE a.cep = :cep"),
    @NamedQuery(name = "Address.findByCidade", query = "SELECT a FROM Address a WHERE a.cidade = :cidade"),
    @NamedQuery(name = "Address.findByLogradouro", query = "SELECT a FROM Address a WHERE a.logradouro = :logradouro")})
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_endereco")
    private Integer idEndereco;
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @Column(name = "logradouro")
    private String logradouro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEndereco")
    private Collection<Client> clientCollection;

    public Address() {
    }

    public Address(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Address(Integer idEndereco, String bairro, String estado, String cep, String cidade, String logradouro) {
        this.idEndereco = idEndereco;
        this.bairro = bairro;
        this.estado = estado;
        this.cep = cep;
        this.cidade = cidade;
        this.logradouro = logradouro;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEndereco != null ? idEndereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.idEndereco == null && other.idEndereco != null) || (this.idEndereco != null && !this.idEndereco.equals(other.idEndereco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookstore.project.model.Address[ idEndereco=" + idEndereco + " ]";
    }
    
}
