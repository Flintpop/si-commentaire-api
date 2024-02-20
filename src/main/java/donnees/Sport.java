package donnees;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

import donnees.Discipline;

/**
 * POJO Sport
 * @author Eric
 */

@Entity
@Table(name = "sport")
@NamedQueries({
        @NamedQuery(name = "Sport.findAll", query = "SELECT s FROM Sport s"),
        @NamedQuery(name = "Sport.findByCodeSport", query = "SELECT s FROM Sport s WHERE s.codeSport = :codeSport"),
        @NamedQuery(name = "Sport.findByIntitule", query = "SELECT s FROM Sport s WHERE s.intitule = :intitule")})
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_sport")
    private Integer codeSport;
    @Column(name = "intitule")
    private String intitule;
    @OneToMany(mappedBy = "sport")
    private Set<Discipline> disciplines;

    public void setCodeSport(int codeSport) {
        this.codeSport = codeSport;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public int getCodeSport() {
        return codeSport;
    }

    public String getIntitule() {
        return intitule;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    public void addDiscipline(Discipline disc) {
        this.disciplines.add(disc);
    }

    public void removeDiscipline(Discipline disc) {
        this.disciplines.remove(disc);
    }

    public Sport(int codeSport, String intitule, Set<Discipline> disciplines) {
        this.codeSport = codeSport;
        this.intitule = intitule;
        this.disciplines = disciplines;
    }

    public Sport() {
        this.disciplines = new HashSet<Discipline>();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.codeSport;
        hash = 67 * hash + Objects.hashCode(this.intitule);
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
        final Sport other = (Sport) obj;
        if (this.codeSport != other.codeSport) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        if (!Objects.equals(this.disciplines, other.disciplines)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sport{" + "codeSport=" + codeSport + ", intitule=" + intitule + '}';
    }


}
