package modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "Passager")
@SequenceGenerator(name = "Passager", allocationSize = 100)
public class Passager {

	private final static Logger logger = LoggerFactory.getLogger(Passager.class);

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passager_seq")
	@Column
	private Integer id;

	@Column
	@NotBlank(message = "l'id de la reservation de noit pas etre vide")
	private String idReservation;

	@Column
	@NotBlank(message = " Le nom ne de doit pas etre vide")
	private String nom;

	@Column
	@NotBlank(message = " Le prenom ne de doit pas etre vide")
	private String prenom;

	@Column
	@NotNull(message = " L'age ne de doit pas etre vide")
	private Integer age;

	@ManyToOne
	private Vol vol;

	@SuppressWarnings("unused")
	private Passager() {

	}

	public Passager(String n, String p, Integer a, Vol v) {
		nom = n;
		prenom = p;
		age = a;
		vol = v;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

	public void updateIdReservation() {
		idReservation = vol.getNumVol() + "-" + Integer.toString(id);
	}

	public String getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(String idReservation) {
		this.idReservation = idReservation;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public Vol getVol() {
		return vol;
	}

	public void affiche() {
		logger.debug("{} | {} | {} | {}", idReservation, nom, prenom, age);
	}

}
