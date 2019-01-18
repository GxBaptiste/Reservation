package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.PassagerDAO;
import dao.VolDAO;
import modele.Passager;
import modele.Vol;

public class ServicePassager {
	
	private final static Logger logger = LoggerFactory.getLogger(ServicePassager.class);
	
	private ServicePassager() {

	  }
	
	public static Vol rechercheVol(String s) {
		return VolDAO.rechercheVol(s);	
	}
	
	public static void creationPassager(Passager p) {
		PassagerDAO.createPassager(p);
		p.updateIdReservation();
		PassagerDAO.update(p);
	}
	
	public static void afficheReservationsVol(Vol v) {
		List<Passager> passagers = PassagerDAO.reservationVols(v);
		logger.debug("IdRéservation | Nom | Prénom | Age");
		for (Passager p  : passagers) {
			p.affiche();
		}
	}
	
	public static void afficheAnnulerVol(String s) {
		PassagerDAO.annulerVol(s);
	}
	
	public static boolean verfiReservation(String s) {
		return PassagerDAO.verifReservation(s);
	}
	
	public static void afficheListeReservation(String n,String pre) {
		List<Passager> passagers = PassagerDAO.listeReservations(n,pre);
		logger.debug("IdReservation | Nom | Prénom | numVol | Date de départ");
		for(Passager p : passagers) {
			logger.debug(p.getIdReservation()+" | "+p.getNom()+" | "+p.getPrenom()+" | "+p.getVol().getNumVol()+" | "+p.getVol().getDateD());
		}
	}

}
