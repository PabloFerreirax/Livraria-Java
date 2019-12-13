/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.model;

import java.io.Serializable;
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
@Table(name = "tb_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByIdCliente", query = "SELECT c FROM Client c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Client.findByCpf", query = "SELECT c FROM Client c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "Client.findBySobrenomeCliente", query = "SELECT c FROM Client c WHERE c.sobrenomeCliente = :sobrenomeCliente"),
    @NamedQuery(name = "Client.findByNmCliente", query = "SELECT c FROM Client c WHERE c.nmCliente = :nmCliente"),
    @NamedQuery(name = "Client.findBySexo", query = "SELECT c FROM Client c WHERE c.sexo = :sexo")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "sobrenome_cliente")
    private String sobrenomeCliente;
    @Basic(optional = false)
    @Column(name = "nm_cliente")
    private String nmCliente;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @JoinColumn(name = "id_contato", referencedColumnName = "id_contato")
    @ManyToOne(optional = false)
    private Contact idContato;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    @ManyToOne(optional = false)
    private Address idEndereco;

    public Client() {
    }

    public Client(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Client(Integer idCliente, String cpf, String sobrenomeCliente, String nmCliente, String sexo) {
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.sobrenomeCliente = sobrenomeCliente;
        this.nmCliente = nmCliente;
        this.sexo = sexo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Contact getIdContato() {
        return idContato;
    }

    public void setIdContato(Contact idContato) {
        this.idContato = idContato;
    }

    public Address getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Address idEndereco) {
        this.idEndereco = idEndereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNmCliente() + " " + getSobrenomeCliente();
    }
    
}
