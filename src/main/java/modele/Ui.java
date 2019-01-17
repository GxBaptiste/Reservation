package modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Service.ServicePassager;
import Service.ServiceVol;

public class Ui {
	
	private static String affichage_retour = "Veuillez choisir l'une des options suivantes \n";
	private static String afficheNumVol = "Numéro du vol : ";

	@SuppressWarnings("resource")
	public static void afficheMenuGeneral() throws ParseException {

		System.out.println("\n\n\n\n");

		Scanner sc = new Scanner(System.in);
		System.out.println("1 : Gestion des vols");
		System.out.println("2 : Gestion des réservations");
		System.out.println("3 : Quitter");
		String str = sc.nextLine();
		System.out.println("\n");
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
			System.out.println(affichage_retour);
			afficheMenuGeneralChoix(s);
		}
	}

	@SuppressWarnings("resource")
	public static void afficheGestionVol() throws ParseException {
		System.out.println("1 : Création d'un vol");
		System.out.println("2 : Suppresion d'un vol");
		System.out.println("3 : Liste des vols");
		System.out.println("4 : Rechercher un avion");
		System.out.println("5 : Rechercher un avion par ville de départ/arrivé");
		System.out.println("6 : Retour en arrière");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println("\n");
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
			System.out.println(affichage_retour);
			afficheGestionVol();
		}
	}

	@SuppressWarnings("resource")
	public static void afficheGestionReservation() throws ParseException {
		System.out.println("1 : Création d'une réservation");
		System.out.println("2 : Afficher les réservations d'un vol");
		System.out.println("3 : Annuler une réservation");
		System.out.println("4 : Afficher toutes les réservations d'une personne");
		System.out.println("5 : Retour en arrière");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println("\n");
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
			System.out.println(affichage_retour);
			afficheGestionReservation();
		}
	}

	@SuppressWarnings("resource")
	public static void afficheCreationVol() throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println(afficheNumVol);
		String numVol = sc.nextLine();
		System.out.println("Type de l'avion : ");
		String typeVol = sc.nextLine();
		System.out.println("Nombre de places : ");
		Integer nbPlace = Integer.parseInt(sc.nextLine());
		System.out.println("Ville de départ : ");
		String villeD = sc.nextLine();
		System.out.println("Ville d'arrivée : ");
		String villeA = sc.nextLine();
		System.out.println("Date de départ (JJ/MM/AAAA) : ");
		Date dateVol = formatter.parse(sc.nextLine());
		ServiceVol.creationVol(new Vol(numVol, typeVol, nbPlace, villeD, villeA, dateVol));
	}

	@SuppressWarnings("resource")
	public static void afficheSupprimerVol() {

		Scanner sc = new Scanner(System.in);
		System.out.println(afficheNumVol);
		String numVol = sc.nextLine();
		ServiceVol.supprimerVol(numVol);
	}

	public static void afficheListVol() {
		ServiceVol.afficherAllVol();
	}

	@SuppressWarnings("resource")
	public static void afficheAvionNumero() {
		Scanner sc = new Scanner(System.in);
		System.out.println(afficheNumVol);
		String numVol = sc.nextLine();
		ServiceVol.afficherAvionNumVol(numVol);
	}

	@SuppressWarnings("resource")
	public static void afficheAvionVille() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ville de départ : ");
		String villeD = sc.nextLine();
		sc = new Scanner(System.in);
		System.out.println("Ville d'arrivée : ");
		String villeA = sc.nextLine();
		ServiceVol.afficherAvionVille(villeD, villeA);
	}

	@SuppressWarnings("resource")
	public static void afficheCreationReservation() {
		Scanner sc = new Scanner(System.in);
		System.out.println(afficheNumVol);
		String numVol = sc.nextLine();
		Vol vol = ServicePassager.rechercheVol(numVol);

		System.out.println("Nom : ");
		String nom = sc.nextLine();
		System.out.println("Prenom : ");
		String prenom = sc.nextLine();
		System.out.println("Age : ");
		Integer age = Integer.parseInt(sc.nextLine());
		Passager p = new Passager(nom, prenom, age, vol);
		ServicePassager.creationPassager(p);
		vol.ajoutPassager(p);
		ServiceVol.updateVol(vol);
	}

	@SuppressWarnings("resource")
	public static void afficheReservationVol() {
		Scanner sc = new Scanner(System.in);
		System.out.println(afficheNumVol);
		String numVol = sc.nextLine();
		Vol vol = ServicePassager.rechercheVol(numVol);
		ServicePassager.afficheReservationsVol(vol);
	}

	@SuppressWarnings("resource")
	public static void afficheAnnulationVol() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Numéro de réservation : ");
		String numRes = sc.nextLine();
		if (ServicePassager.verfiReservation(numRes))
			ServicePassager.afficheAnnulerVol(numRes);
		else {
			System.out.println("le numéro de vol n'existe pas");
			afficheAnnulationVol();
		}
	}

	@SuppressWarnings("resource")
	public static void afficheReservationPersonne() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la personne : ");
		String nom = sc.nextLine();
		System.out.println("Prenom de la personne : ");
		String prenom = sc.nextLine();
		ServicePassager.afficheListeReservation(nom, prenom);

	}

}
