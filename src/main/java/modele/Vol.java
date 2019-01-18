package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "Vol")
@SequenceGenerator(name = "vol_seq", allocationSize = 100)
public class Vol {
	
	private final static Logger logger = LoggerFactory.getLogger(Vol.class);

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vol_seq")
	@Column
	private Integer id;

	@Column(unique = true)
	@NotBlank(message = " Le type ne de doit pas etre vide")
	private String numVol;

	@Column
	@NotBlank(message = " Le type ne de doit pas etre vide")
	private String type;

	@Column
	@NotNull(message = " Le nb de places ne de doit pas etre vide")
	private Integer nbPlaces;

	@Column
	@NotBlank(message = " La ville de départ ne de doit pas etre vide")
	private String villeD;

	@Column
	@NotBlank(message = " La ville d'arrivée ne de doit pas etre vide")
	private String villeA;

	@Column
	@NotBlank(message = " La date de départ ne de doit pas etre vide")
	private Date dateD;

	@OneToMany(mappedBy = "vol",fetch = FetchType.EAGER)
	private List<Passager> passager;
	
	public String getNumVol() {
		return numVol;
	}

	public void setNumVol(String numVol) {
		this.numVol = numVol;
	}
	
	
	@SuppressWarnings("unused")
	private Vol() {

	  }

	public Vol(String nV,String t, Integer i, String vD, String vA, Date d) {
		numVol=nV;
		type = t;
		nbPlaces = i;
		villeD = vD;
		villeA = vA;
		dateD = d;
		passager = new ArrayList<Passager>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getVilleD() {
		return villeD;
	}

	public void setVilleD(String villeD) {
		this.villeD = villeD;
	}

	public String getVilleA() {
		return villeA;
	}

	public void setVilleA(String villeA) {
		this.villeA = villeA;
	}

	public Date getDateD() {
		return dateD;
	}

	public void setDateD(Date dateD) {
		this.dateD = dateD;
	}

	public List<Passager> getPassager() {
		return passager;
	}
	
	public void ajoutPassager(Passager p) {
		passager.add(p);
	}
	
	public void affiche() {
		logger.debug("{} | {} | {} | {} | {} | {}",numVol,type,nbPlaces,villeD,villeA,dateD);
	}

}
