package Service;

import java.util.List;

import dao.VolDAO;
import modele.Vol;

public class ServiceVol {
	
	private static String affichage = "Numero | Type | Place | Départ | Arrivé | Date";

	public static void creationVol(Vol v) {
		VolDAO.createVol(v);
	}

	public static void supprimerVol(String s) {
		VolDAO.delete(VolDAO.rechercheVol(s));
	}
	
	public static void updateVol(Vol v) {
		VolDAO.update(v);
	}

	public static void afficherAllVol() {
		List<Vol> vols = VolDAO.listeVols();
		System.out.println(affichage);
		for (Vol v : vols) {
			v.affiche();
		}
	}

	public static void afficherAvionNumVol(String s) {
		Vol v = VolDAO.rechercheVol(s);
		System.out.println(affichage);
		v.affiche();
	}

	public static void afficherAvionVille(String villeD, String villeA) {
		List<Vol> vols = VolDAO.listeVolVille(villeD,villeA);
		System.out.println(affichage);
		for (Vol v : vols) {
			v.affiche();
		}
	}

}
