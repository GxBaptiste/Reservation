package Service;

import java.util.List;

import dao.PassagerDAO;
import dao.VolDAO;
import modele.Passager;
import modele.Vol;

public class ServicePassager {
	
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
		System.out.println("IdRéservation | Nom | Prénom | Age");
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
		System.out.println("IdReservation | Nom | Prénom | numVol | Date de départ");
		for(Passager p : passagers) {
			System.out.println(p.getIdReservation()+" | "+p.getNom()+" | "+p.getPrenom()+" | "+p.getVol().getNumVol()+" | "+p.getVol().getDateD());
		}
	}

}
