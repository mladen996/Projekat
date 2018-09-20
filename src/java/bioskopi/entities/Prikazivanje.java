/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaaaa
 */@ManagedBean
@RequestScoped
@Entity
@Table(name = "prikazivanje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prikazivanje.findAll", query = "SELECT p FROM Prikazivanje p")
    , @NamedQuery(name = "Prikazivanje.findByPrikazivanjeID", query = "SELECT p FROM Prikazivanje p WHERE p.prikazivanjeID = :prikazivanjeID")
    , @NamedQuery(name = "Prikazivanje.findByDatumPrikazivanja", query = "SELECT p FROM Prikazivanje p WHERE p.datumPrikazivanja = :datumPrikazivanja")
    , @NamedQuery(name = "Prikazivanje.findByCena", query = "SELECT p FROM Prikazivanje p WHERE p.cena = :cena")})
public class Prikazivanje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prikazivanjeID")
    private Integer prikazivanjeID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumPrikazivanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPrikazivanja;
    @Column(name = "cena")
    private Integer cena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prikazivanje")
    private Collection<Sediste> sedisteCollection;
    @JoinColumn(name = "filmID", referencedColumnName = "filmID")
    @ManyToOne
    private Film filmID;
    @JoinColumns({
        @JoinColumn(name = "bioskopID", referencedColumnName = "bioskopID")
        , @JoinColumn(name = "salaID", referencedColumnName = "salaID")})
    @ManyToOne
    private Sala sala;

    public Prikazivanje() {
    }

    public Prikazivanje(Integer prikazivanjeID) {
        this.prikazivanjeID = prikazivanjeID;
    }

    public Prikazivanje(Integer prikazivanjeID, Date datumPrikazivanja) {
        this.prikazivanjeID = prikazivanjeID;
        this.datumPrikazivanja = datumPrikazivanja;
    }

    public Integer getPrikazivanjeID() {
        return prikazivanjeID;
    }

    public void setPrikazivanjeID(Integer prikazivanjeID) {
        this.prikazivanjeID = prikazivanjeID;
    }

    public Date getDatumPrikazivanja() {
        return datumPrikazivanja;
    }

    public void setDatumPrikazivanja(Date datumPrikazivanja) {
        this.datumPrikazivanja = datumPrikazivanja;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    @XmlTransient
    public Collection<Sediste> getSedisteCollection() {
        return sedisteCollection;
    }

    public void setSedisteCollection(Collection<Sediste> sedisteCollection) {
        this.sedisteCollection = sedisteCollection;
    }

    public Film getFilmID() {
        return filmID;
    }

    public void setFilmID(Film filmID) {
        this.filmID = filmID;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prikazivanjeID != null ? prikazivanjeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prikazivanje)) {
            return false;
        }
        Prikazivanje other = (Prikazivanje) object;
        if ((this.prikazivanjeID == null && other.prikazivanjeID != null) || (this.prikazivanjeID != null && !this.prikazivanjeID.equals(other.prikazivanjeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Prikazivanje[ prikazivanjeID=" + prikazivanjeID + " ]";
    }
    
}
