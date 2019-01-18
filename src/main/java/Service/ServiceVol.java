package Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.VolDAO;
import modele.Vol;

public class ServiceVol {
	
	private static String affichage = "Numero | Type | Place | Départ | Arrivé | Date";
	private final static Logger logger = LoggerFactory.getLogger(ServiceVol.class);

	private ServiceVol() {

	  }
	
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
		logger.debug(affichage);
		for (Vol v : vols) {
			v.affiche();
		}
	}

	public static void afficherAvionNumVol(String s) {
		Vol v = VolDAO.rechercheVol(s);
		logger.debug(affichage);
		v.affiche();
	}

	public static void afficherAvionVille(String villeD, String villeA) {
		List<Vol> vols = VolDAO.listeVolVille(villeD,villeA);
		logger.debug(affichage);
		for (Vol v : vols) {
			v.affiche();
		}
	}

}
