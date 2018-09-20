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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")
    , @NamedQuery(name = "Korisnik.findByKorisnikID", query = "SELECT k FROM Korisnik k WHERE k.korisnikID = :korisnikID")
    , @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime")
    , @NamedQuery(name = "Korisnik.findByIsAdmin", query = "SELECT k FROM Korisnik k WHERE k.isAdmin = :isAdmin")
    , @NamedQuery(name = "Korisnik.findByPrezime", query = "SELECT k FROM Korisnik k WHERE k.prezime = :prezime")
    , @NamedQuery(name = "Korisnik.findByEmail", query = "SELECT k FROM Korisnik k WHERE k.email = :email")
    , @NamedQuery(name = "Korisnik.findByUsername", query = "SELECT k FROM Korisnik k WHERE k.username = :username")
    , @NamedQuery(name = "Korisnik.findByPassword", query = "SELECT k FROM Korisnik k WHERE k.password = :password")})
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "korisnikID")
    private Integer korisnikID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ime")
    private String ime;
    @Column(name = "isAdmin")
    private Boolean isAdmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prezime")
    private String prezime;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "korisnikID")
    private Collection<Karta> kartaCollection;

    public Korisnik() {
    }

    public Korisnik(Integer korisnikID) {
        this.korisnikID = korisnikID;
    }

    public Korisnik(Integer korisnikID, String ime, String prezime, String email, String username, String password) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Integer getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Integer korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        hash += (korisnikID != null ? korisnikID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.korisnikID == null && other.korisnikID != null) || (this.korisnikID != null && !this.korisnikID.equals(other.korisnikID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bioskopi.entities.Korisnik[ korisnikID=" + korisnikID + " ]";
    }
    
}
