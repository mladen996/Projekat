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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jaaaa
 */@ManagedBean
@RequestScoped
@Embeddable
public class SedistePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "salaID")
    private int salaID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bioskopID")
    private int bioskopID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prikazivanjeID")
    private int prikazivanjeID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sedisteID")
    private int sedisteID;

    public SedistePK() {
    }

    public SedistePK(int salaID, int bioskopID, int prikazivanjeID, int sedisteID) {
        this.salaID = salaID;
        this.bioskopID = bioskopID;
        this.prikazivanjeID = prikazivanjeID;
        this.sedisteID = sedisteID;
    }

    public int getSalaID() {
        return salaID;
    }

    public void setSalaID(int salaID) {
        this.salaID = salaID;
    }

    public int getBioskopID() {
        return bioskopID;
    }

    public void setBioskopID(int bioskopID) {
        this.bioskopID = bioskopID;
    }

    public int getPrikazivanjeID() {
        return prikazivanjeID;
    }

    public void setPrikazivanjeID(int prikazivanjeID) {
        this.prikazivanjeID = prikazivanjeID;
    }

    public int getSedisteID() {
        return sedisteID;
    }

    public void setSedisteID(int sedisteID) {
        this.sedisteID = sedisteID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) salaID;
        hash += (int) bioskopID;
        hash += (int) prikazivanjeID;
        hash += (int) sedisteID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SedistePK)) {
            return false;
        }
        SedistePK other = (SedistePK) object;
        if (this.salaID != other.salaID) {
            return false;
        }
        if (this.bioskopID != other.bioskopID) {
            return false;
        }
        if (this.prikazivanjeID != other.prikazivanjeID) {
            return false;
        }
        if (this.sedisteID != other.sedisteID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.SedistePK[ salaID=" + salaID + ", bioskopID=" + bioskopID + ", prikazivanjeID=" + prikazivanjeID + ", sedisteID=" + sedisteID + " ]";
    }
    
}
