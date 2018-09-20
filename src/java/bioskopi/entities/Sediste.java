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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaaaa
 */@ManagedBean
@RequestScoped
@Entity
@Table(name = "sediste")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sediste.findAll", query = "SELECT s FROM Sediste s")
    , @NamedQuery(name = "Sediste.findBySalaID", query = "SELECT s FROM Sediste s WHERE s.sedistePK.salaID = :salaID")
    , @NamedQuery(name = "Sediste.findByBioskopID", query = "SELECT s FROM Sediste s WHERE s.sedistePK.bioskopID = :bioskopID")
    , @NamedQuery(name = "Sediste.findByPrikazivanjeID", query = "SELECT s FROM Sediste s WHERE s.sedistePK.prikazivanjeID = :prikazivanjeID")
    , @NamedQuery(name = "Sediste.findBySedisteID", query = "SELECT s FROM Sediste s WHERE s.sedistePK.sedisteID = :sedisteID")
    , @NamedQuery(name = "Sediste.findByIsFree", query = "SELECT s FROM Sediste s WHERE s.isFree = :isFree")})
public class Sediste implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SedistePK sedistePK;
    @Column(name = "isFree")
    private Boolean isFree;
    @JoinColumn(name = "prikazivanjeID", referencedColumnName = "prikazivanjeID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prikazivanje prikazivanje;
    @OneToMany(mappedBy = "sediste")
    private Collection<Karta> kartaCollection;

    public Sediste() {
    }

    public Sediste(SedistePK sedistePK) {
        this.sedistePK = sedistePK;
    }

    public Sediste(int salaID, int bioskopID, int prikazivanjeID, int sedisteID) {
        this.sedistePK = new SedistePK(salaID, bioskopID, prikazivanjeID, sedisteID);
    }

    public SedistePK getSedistePK() {
        return sedistePK;
    }

    public void setSedistePK(SedistePK sedistePK) {
        this.sedistePK = sedistePK;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public Prikazivanje getPrikazivanje() {
        return prikazivanje;
    }

    public void setPrikazivanje(Prikazivanje prikazivanje) {
        this.prikazivanje = prikazivanje;
    }

    @XmlTransient
    public Collection<Karta> getKartaCollection() {
        return kartaCollection;
    }

    public void setKartaCollection(Collection<Karta> kartaCollection) {
        this.kartaCollection = kartaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sedistePK != null ? sedistePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sediste)) {
            return false;
        }
        Sediste other = (Sediste) object;
        if ((this.sedistePK == null && other.sedistePK != null) || (this.sedistePK != null && !this.sedistePK.equals(other.sedistePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Sediste[ sedistePK=" + sedistePK + " ]";
    }
    
}
