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
@Table(name = "jezik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jezik.findAll", query = "SELECT j FROM Jezik j")
    , @NamedQuery(name = "Jezik.findByJezikID", query = "SELECT j FROM Jezik j WHERE j.jezikID = :jezikID")
    , @NamedQuery(name = "Jezik.findByIme", query = "SELECT j FROM Jezik j WHERE j.ime = :ime")})
public class Jezik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "jezikID")
    private Integer jezikID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ime")
    private String ime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jezikID")
    private Collection<Film> filmCollection;

    public Jezik() {
    }

    public Jezik(Integer jezikID) {
        this.jezikID = jezikID;
    }

    public Jezik(Integer jezikID, String ime) {
        this.jezikID = jezikID;
        this.ime = ime;
    }

    public Integer getJezikID() {
        return jezikID;
    }

    public void setJezikID(Integer jezikID) {
        this.jezikID = jezikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
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
        hash += (jezikID != null ? jezikID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jezik)) {
            return false;
        }
        Jezik other = (Jezik) object;
        if ((this.jezikID == null && other.jezikID != null) || (this.jezikID != null && !this.jezikID.equals(other.jezikID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Jezik[ jezikID=" + jezikID + " ]";
    }
    
}
