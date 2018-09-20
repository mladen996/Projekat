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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "film")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f")
    , @NamedQuery(name = "Film.findByFilmID", query = "SELECT f FROM Film f WHERE f.filmID = :filmID")
    , @NamedQuery(name = "Film.findByNaziv", query = "SELECT f FROM Film f WHERE f.naziv = :naziv")
    , @NamedQuery(name = "Film.findByGodina", query = "SELECT f FROM Film f WHERE f.godina = :godina")
    , @NamedQuery(name = "Film.findByDuzinaFilma", query = "SELECT f FROM Film f WHERE f.duzinaFilma = :duzinaFilma")
    , @NamedQuery(name = "Film.findByOcena", query = "SELECT f FROM Film f WHERE f.ocena = :ocena")})
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "filmID")
    private Integer filmID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "godina")
    private int godina;
    @Size(max = 10)
    @Column(name = "duzinaFilma")
    private String duzinaFilma;
    @Column(name = "ocena")
    private Integer ocena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filmID")
    private Collection<GlumacFilm> glumacFilmCollection;
    @JoinColumn(name = "reziserID", referencedColumnName = "reziserID")
    @ManyToOne(optional = false)
    private Reziser reziserID;
    @JoinColumn(name = "jezikID", referencedColumnName = "jezikID")
    @ManyToOne(optional = false)
    private Jezik jezikID;
    @OneToMany(mappedBy = "filmID")
    private Collection<Prikazivanje> prikazivanjeCollection;

    public Film() {
    }

    public Film(Integer filmID) {
        this.filmID = filmID;
    }

    public Film(Integer filmID, String naziv, int godina) {
        this.filmID = filmID;
        this.naziv = naziv;
        this.godina = godina;
    }

    public Integer getFilmID() {
        return filmID;
    }

    public void setFilmID(Integer filmID) {
        this.filmID = filmID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public String getDuzinaFilma() {
        return duzinaFilma;
    }

    public void setDuzinaFilma(String duzinaFilma) {
        this.duzinaFilma = duzinaFilma;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    @XmlTransient
    public Collection<GlumacFilm> getGlumacFilmCollection() {
        return glumacFilmCollection;
    }

    public void setGlumacFilmCollection(Collection<GlumacFilm> glumacFilmCollection) {
        this.glumacFilmCollection = glumacFilmCollection;
    }

    public Reziser getReziserID() {
        return reziserID;
    }

    public void setReziserID(Reziser reziserID) {
        this.reziserID = reziserID;
    }

    public Jezik getJezikID() {
        return jezikID;
    }

    public void setJezikID(Jezik jezikID) {
        this.jezikID = jezikID;
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
        hash += (filmID != null ? filmID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Film)) {
            return false;
        }
        Film other = (Film) object;
        if ((this.filmID == null && other.filmID != null) || (this.filmID != null && !this.filmID.equals(other.filmID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Film[ filmID=" + filmID + " ]";
    }
    
}
