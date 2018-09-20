/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.entities;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaaaa
 */
@ManagedBean
@RequestScoped
@Entity
@Table(name = "bioskop")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bioskop.findAll", query = "SELECT b FROM Bioskop b")
    , @NamedQuery(name = "Bioskop.findByBioskopID", query = "SELECT b FROM Bioskop b WHERE b.bioskopID = :bioskopID")
    , @NamedQuery(name = "Bioskop.findByIme", query = "SELECT b FROM Bioskop b WHERE b.ime = :ime")
    , @NamedQuery(name = "Bioskop.findByGrad", query = "SELECT b FROM Bioskop b WHERE b.grad = :grad")})
public class Bioskop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bioskopID")
    private Integer bioskopID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "grad")
    private String grad;

    public Bioskop() {
    }

    public Bioskop(Integer bioskopID) {
        this.bioskopID = bioskopID;
    }

    public Bioskop(Integer bioskopID, String ime, String grad) {
        this.bioskopID = bioskopID;
        this.ime = ime;
        this.grad = grad;
    }

    public Integer getBioskopID() {
        return bioskopID;
    }

    public void setBioskopID(Integer bioskopID) {
        this.bioskopID = bioskopID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bioskopID != null ? bioskopID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bioskop)) {
            return false;
        }
        Bioskop other = (Bioskop) object;
        if ((this.bioskopID == null && other.bioskopID != null) || (this.bioskopID != null && !this.bioskopID.equals(other.bioskopID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Bioskop[ bioskopID=" + bioskopID + " ]";
    }
    
}
