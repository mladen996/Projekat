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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaaaa
 */@ManagedBean
@RequestScoped
@Entity
@Table(name = "glumac_film")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlumacFilm.findAll", query = "SELECT g FROM GlumacFilm g")
    , @NamedQuery(name = "GlumacFilm.findByGlumacfilmID", query = "SELECT g FROM GlumacFilm g WHERE g.glumacfilmID = :glumacfilmID")
    , @NamedQuery(name = "GlumacFilm.findByDuzinaFilma", query = "SELECT g FROM GlumacFilm g WHERE g.duzinaFilma = :duzinaFilma")})
public class GlumacFilm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "glumac_filmID")
    private Integer glumacfilmID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duzinaFilma")
    private int duzinaFilma;
    @JoinColumn(name = "glumacID", referencedColumnName = "glumacID")
    @ManyToOne(optional = false)
    private Glumac glumacID;
    @JoinColumn(name = "filmID", referencedColumnName = "filmID")
    @ManyToOne(optional = false)
    private Film filmID;

    public GlumacFilm() {
    }

    public GlumacFilm(Integer glumacfilmID) {
        this.glumacfilmID = glumacfilmID;
    }

    public GlumacFilm(Integer glumacfilmID, int duzinaFilma) {
        this.glumacfilmID = glumacfilmID;
        this.duzinaFilma = duzinaFilma;
    }

    public Integer getGlumacfilmID() {
        return glumacfilmID;
    }

    public void setGlumacfilmID(Integer glumacfilmID) {
        this.glumacfilmID = glumacfilmID;
    }

    public int getDuzinaFilma() {
        return duzinaFilma;
    }

    public void setDuzinaFilma(int duzinaFilma) {
        this.duzinaFilma = duzinaFilma;
    }

    public Glumac getGlumacID() {
        return glumacID;
    }

    public void setGlumacID(Glumac glumacID) {
        this.glumacID = glumacID;
    }

    public Film getFilmID() {
        return filmID;
    }

    public void setFilmID(Film filmID) {
        this.filmID = filmID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (glumacfilmID != null ? glumacfilmID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlumacFilm)) {
            return false;
        }
        GlumacFilm other = (GlumacFilm) object;
        if ((this.glumacfilmID == null && other.glumacfilmID != null) || (this.glumacfilmID != null && !this.glumacfilmID.equals(other.glumacfilmID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.GlumacFilm[ glumacfilmID=" + glumacfilmID + " ]";
    }
    
}
