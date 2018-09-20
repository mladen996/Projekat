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
public class SalaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "bioskopID")
    private int bioskopID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salaID")
    private int salaID;

    public SalaPK() {
    }

    public SalaPK(int bioskopID, int salaID) {
        this.bioskopID = bioskopID;
        this.salaID = salaID;
    }

    public int getBioskopID() {
        return bioskopID;
    }

    public void setBioskopID(int bioskopID) {
        this.bioskopID = bioskopID;
    }

    public int getSalaID() {
        return salaID;
    }

    public void setSalaID(int salaID) {
        this.salaID = salaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) bioskopID;
        hash += (int) salaID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalaPK)) {
            return false;
        }
        SalaPK other = (SalaPK) object;
        if (this.bioskopID != other.bioskopID) {
            return false;
        }
        if (this.salaID != other.salaID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.SalaPK[ bioskopID=" + bioskopID + ", salaID=" + salaID + " ]";
    }
    
}
