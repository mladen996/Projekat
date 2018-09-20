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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaaaa
 */@ManagedBean
@RequestScoped
@Entity
@Table(name = "karta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Karta.findAll", query = "SELECT k FROM Karta k")
    , @NamedQuery(name = "Karta.findByKartaID", query = "SELECT k FROM Karta k WHERE k.kartaID = :kartaID")})
public class Karta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kartaID")
    private Integer kartaID;
    @JoinColumn(name = "korisnikID", referencedColumnName = "korisnikID")
    @ManyToOne
    private Korisnik korisnikID;
    @JoinColumns({
        @JoinColumn(name = "bioskopID", referencedColumnName = "bioskopID")
        , @JoinColumn(name = "salaID", referencedColumnName = "salaID")
        , @JoinColumn(name = "sedisteID", referencedColumnName = "sedisteID")
        , @JoinColumn(name = "prikazivanjeID", referencedColumnName = "prikazivanjeID")})
    @ManyToOne
    private Sediste sediste;

    public Karta() {
    }

    public Karta(Integer kartaID) {
        this.kartaID = kartaID;
    }

    public Integer getKartaID() {
        return kartaID;
    }

    public void setKartaID(Integer kartaID) {
        this.kartaID = kartaID;
    }

    public Korisnik getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Korisnik korisnikID) {
        this.korisnikID = korisnikID;
    }

    public Sediste getSediste() {
        return sediste;
    }

    public void setSediste(Sediste sediste) {
        this.sediste = sediste;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kartaID != null ? kartaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Karta)) {
            return false;
        }
        Karta other = (Karta) object;
        if ((this.kartaID == null && other.kartaID != null) || (this.kartaID != null && !this.kartaID.equals(other.kartaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Karta[ kartaID=" + kartaID + " ]";
    }
    
}
