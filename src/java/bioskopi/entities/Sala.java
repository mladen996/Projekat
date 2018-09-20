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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaaaa
 */@ManagedBean
@RequestScoped
@Entity
@Table(name = "sala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")
    , @NamedQuery(name = "Sala.findByBioskopID", query = "SELECT s FROM Sala s WHERE s.salaPK.bioskopID = :bioskopID")
    , @NamedQuery(name = "Sala.findBySalaID", query = "SELECT s FROM Sala s WHERE s.salaPK.salaID = :salaID")
    , @NamedQuery(name = "Sala.findByBrojSedista", query = "SELECT s FROM Sala s WHERE s.brojSedista = :brojSedista")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SalaPK salaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojSedista")
    private int brojSedista;
    @OneToMany(mappedBy = "sala")
    private Collection<Prikazivanje> prikazivanjeCollection;

    public Sala() {
    }

    public Sala(SalaPK salaPK) {
        this.salaPK = salaPK;
    }

    public Sala(SalaPK salaPK, int brojSedista) {
        this.salaPK = salaPK;
        this.brojSedista = brojSedista;
    }

    public Sala(int bioskopID, int salaID) {
        this.salaPK = new SalaPK(bioskopID, salaID);
    }

    public SalaPK getSalaPK() {
        return salaPK;
    }

    public void setSalaPK(SalaPK salaPK) {
        this.salaPK = salaPK;
    }

    public int getBrojSedista() {
        return brojSedista;
    }

    public void setBrojSedista(int brojSedista) {
        this.brojSedista = brojSedista;
    }

    @XmlTransient
    public Collection<Prikazivanje> getPrikazivanjeCollection() {
        return prikazivanjeCollection;
    }

    public void setPrikazivanjeCollection(Collection<Prikazivanje> prikazivanjeCollection) {
        this.prikazivanjeCollection = prikazivanjeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salaPK != null ? salaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.salaPK == null && other.salaPK != null) || (this.salaPK != null && !this.salaPK.equals(other.salaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Sala[ salaPK=" + salaPK + " ]";
    }
    
}
