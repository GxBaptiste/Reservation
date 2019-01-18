package modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.ServicePassager;
import service.ServiceVol;

public class Ui {
	
	private static final  Logger logger = LoggerFactory.getLogger(Ui.class);
	
	private static String affichage_retour = "Veuillez choisir l'une des options suivantes \n";
	private static String afficheNumVol = "Numéro du vol : ";


	private Ui() {

	  }
	
	@SuppressWarnings("resource")
	public static void afficheMenuGeneral() throws ParseException {

		logger.info("\n\n\n\n");

		Scanner sc = new Scanner(System.in);
		logger.info("1 : Gestion des vols");
		logger.info("2 : Gestion des réservations");
		logger.info("3 : Quitter");
		String str = sc.nextLine();
		logger.info("\n");
		afficheMenuGeneralChoix(str);
	}

	public static void afficheMenuGeneralChoix(String s) throws ParseException {
		if (s.equals("1")) {
			afficheGestionVol();
		} else if (s.equals("2")) {
			afficheGestionReservation();
		} else if (s.equals("3")) {
			Main.ui = true;
		} else {
			logger.info(affichage_retour);
			afficheMenuGeneralChoix(s);
		}
	}

	@SuppressWarnings("resource")
	public static void afficheGestionVol() throws ParseException {
		logger.info("1 : Création d'un vol");
		logger.info("2 : Suppresion d'un vol");
		logger.info("3 : Liste des vols");
		logger.info("4 : Rechercher un avion");
		logger.info("5 : Rechercher un avion par ville de départ/arrivé");
		logger.info("6 : Retour en arrière");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		logger.info("\n");
		if (str.equals("1")) {
			afficheCreationVol();
		} else if (str.equals("2")) {
			afficheSupprimerVol();
		} else if (str.equals("3")) {
			afficheListVol();
		} else if (str.equals("4")) {
			afficheAvionNumero();
		} else if (str.equals("5")) {
			afficheAvionVille();
		} else if (str.equals("6")) {
			afficheMenuGeneral();
		} else {
			logger.info(affichage_retour);
			afficheGestionVol();
		}
	}

	@SuppressWarnings("resource")
	public static void afficheGestionReservation() throws ParseException {
		logger.info("1 : Création d'une réservation");
		logger.info("2 : Afficher les réservations d'un vol");
		logger.info("3 : Annuler une réservation");
		logger.info("4 : Afficher toutes les réservations d'une personne");
		logger.info("5 : Retour en arrière");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		logger.info("\n");
		if (str.equals("1")) {
			afficheCreationReservation();
		} else if (str.equals("2")) {
			afficheReservationVol();
		} else if (str.equals("3")) {
			afficheAnnulationVol();
		} else if (str.equals("4")) {
			afficheReservationPersonne();
		} else if (str.equals("5")) {
			afficheMenuGeneral();
		} else {
			logger.info(affichage_retour);
			afficheGestionReservation();
		}
	}

	@SuppressWarnings("resource")
	public static void afficheCreationVol() throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		logger.info(afficheNumVol);
		String numVol = sc.nextLine();
		logger.info("Type de l'avion : ");
		String typeVol = sc.nextLine();
		logger.info("Nombre de places : ");
		Integer nbPlace = Integer.parseInt(sc.nextLine());
		logger.info("Ville de départ : ");
		String villeD = sc.nextLine();
		logger.info("Ville d'arrivée : ");
		String villeA = sc.nextLine();
		logger.info("Date de départ (JJ/MM/AAAA) : ");
		Date dateVol = formatter.parse(sc.nextLine());
		ServiceVol.creationVol(new Vol(numVol, typeVol, nbPlace, villeD, villeA, dateVol));
	}

	@SuppressWarnings("resource")
	public static void afficheSupprimerVol() {

		Scanner sc = new Scanner(System.in);
		logger.info(afficheNumVol);
		String numVol = sc.nextLine();
		ServiceVol.supprimerVol(numVol);
	}

	public static void afficheListVol() {
		ServiceVol.afficherAllVol();
	}

	@SuppressWarnings("resource")
	public static void afficheAvionNumero() {
		Scanner sc = new Scanner(System.in);
		logger.info(afficheNumVol);
		String numVol = sc.nextLine();
		ServiceVol.afficherAvionNumVol(numVol);
	}

	@SuppressWarnings("resource")
	public static void afficheAvionVille() {
		Scanner sc = new Scanner(System.in);
		logger.info("Ville de départ : ");
		String villeD = sc.nextLine();
		sc = new Scanner(System.in);
		logger.info("Ville d'arrivée : ");
		String villeA = sc.nextLine();
		ServiceVol.afficherAvionVille(villeD, villeA);
	}

	@SuppressWarnings("resource")
	public static void afficheCreationReservation() {
		Scanner sc = new Scanner(System.in);
		logger.info(afficheNumVol);
		String numVol = sc.nextLine();
		Vol vol = ServicePassager.rechercheVol(numVol);

		logger.info("Nom : ");
		String nom = sc.nextLine();
		logger.info("Prenom : ");
		String prenom = sc.nextLine();
		logger.info("Age : ");
		Integer age = Integer.parseInt(sc.nextLine());
		Passager p = new Passager(nom, prenom, age, vol);
		ServicePassager.creationPassager(p);
		vol.ajoutPassager(p);
		ServiceVol.updateVol(vol);
	}

	@SuppressWarnings("resource")
	public static void afficheReservationVol() {
		Scanner sc = new Scanner(System.in);
		logger.info(afficheNumVol);
		String numVol = sc.nextLine();
		Vol vol = ServicePassager.rechercheVol(numVol);
		ServicePassager.afficheReservationsVol(vol);
	}

	@SuppressWarnings("resource")
	public static void afficheAnnulationVol() {
		Scanner sc = new Scanner(System.in);
		logger.info("Numéro de réservation : ");
		String numRes = sc.nextLine();
		if (ServicePassager.verfiReservation(numRes))
			ServicePassager.afficheAnnulerVol(numRes);
		else {
			logger.info("le numéro de vol n'existe pas");
			afficheAnnulationVol();
		}
	}

	@SuppressWarnings("resource")
	public static void afficheReservationPersonne() {
		Scanner sc = new Scanner(System.in);
		logger.info("Nom de la personne : ");
		String nom = sc.nextLine();
		logger.info("Prenom de la personne : ");
		String prenom = sc.nextLine();
		ServicePassager.afficheListeReservation(nom, prenom);

	}

}
