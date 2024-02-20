package donnees;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * POJO Discipline
 * @author Eric
 */

@Entity
@Table(name = "discipline")
@NamedQueries({
        @NamedQuery(name = "Discipline.findAll", query = "SELECT d FROM Discipline d"),
        @NamedQuery(name = "Discipline.findByCodeDiscipline", query = "SELECT d FROM Discipline d WHERE d.codeDiscipline = :codeDiscipline"),
        @NamedQuery(name = "Discipline.findByIntitule", query = "SELECT d FROM Discipline d WHERE d.intitule = :intitule")})
public class Discipline implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_discipline")
    private Integer codeDiscipline;
    @Column(name = "intitule")
    private String intitule;
    @ManyToMany(mappedBy = "disciplines")
    private Set<Sportif> sportifs;
    @JoinColumn(name = "code_sport", referencedColumnName = "code_sport")
    @ManyToOne
    private Sport sport;

    public int getCodeDiscipline() {
        return codeDiscipline;
    }

    public void setCodeDiscipline(int codeDiscipline) {
        this.codeDiscipline = codeDiscipline;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Discipline(int codeDiscipline, String intitule, Sport sport) {
        this.codeDiscipline = codeDiscipline;
        this.intitule = intitule;
        this.sport = sport;
    }

    public Discipline() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codeDiscipline;
        hash = 97 * hash + Objects.hashCode(this.intitule);
        hash = 97 * hash + Objects.hashCode(this.sport);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Discipline other = (Discipline) obj;
        if (this.codeDiscipline != other.codeDiscipline) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        if (!Objects.equals(this.sport, other.sport)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Discipline{" + "codeDiscipline=" + codeDiscipline + ", intitule=" + intitule + ", sport=" + sport + '}';
    }

}


