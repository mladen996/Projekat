/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaaaa
 */@ManagedBean
@RequestScoped
@Entity
@Table(name = "reziser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reziser.findAll", query = "SELECT r FROM Reziser r")
    , @NamedQuery(name = "Reziser.findByReziserID", query = "SELECT r FROM Reziser r WHERE r.reziserID = :reziserID")
    , @NamedQuery(name = "Reziser.findByIme", query = "SELECT r FROM Reziser r WHERE r.ime = :ime")
    , @NamedQuery(name = "Reziser.findByPrezime", query = "SELECT r FROM Reziser r WHERE r.prezime = :prezime")})
public class Reziser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reziserID")
    private Integer reziserID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prezime")
    private String prezime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reziserID")
    private Collection<Film> filmCollection;

    public Reziser() {
    }

    public Reziser(Integer reziserID) {
        this.reziserID = reziserID;
    }

    public Reziser(Integer reziserID, String ime, String prezime) {
        this.reziserID = reziserID;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getReziserID() {
        return reziserID;
    }

    public void setReziserID(Integer reziserID) {
        this.reziserID = reziserID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @XmlTransient
    public Collection<Film> getFilmCollection() {
        return filmCollection;
    }

    public void setFilmCollection(Collection<Film> filmCollection) {
        this.filmCollection = filmCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reziserID != null ? reziserID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reziser)) {
            return false;
        }
        Reziser other = (Reziser) object;
        if ((this.reziserID == null && other.reziserID != null) || (this.reziserID != null && !this.reziserID.equals(other.reziserID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Reziser[ reziserID=" + reziserID + " ]";
    }
    
}
