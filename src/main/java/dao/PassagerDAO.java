package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modele.Passager;
import modele.Vol;

public class PassagerDAO {
	

	private PassagerDAO() {

	  }

	public static void createPassager(Passager p) {
		EntityManager entity = DBHelper.createEntityManager();
		entity.getTransaction().begin();
		entity.persist(p);
		entity.getTransaction().commit();
		entity.close();
		update(p);
	}

	public static void update(Passager p) {
		EntityManager entity = DBHelper.createEntityManager();
		entity.getTransaction().begin();
		entity.merge(p);
		entity.getTransaction().commit();
		entity.close();
	}

	public static void delete(Passager p) {
		EntityManager entity = DBHelper.createEntityManager();
		entity.getTransaction().begin();
		entity.remove(entity.find(Passager.class, p.getId()));
		entity.getTransaction().commit();
		entity.close();
	}

	public static List<Passager> reservationVols(Vol v) {
		EntityManager em = DBHelper.createEntityManager();
		TypedQuery<Passager> query = em.createQuery(
				"select reservation from Vol v join v.passager reservation where v.numVol=:numVol", Passager.class);
		query.setParameter("numVol", v.getNumVol());
		List<Passager> res = query.getResultList();
		em.close();
		return res;
	}

	public static void annulerVol(String s) {
		EntityManager em = DBHelper.createEntityManager();
		TypedQuery<Passager> query = em.createQuery(
				"select reservation from Vol v join v.passager reservation where reservation.idReservation=:idReservation",
				Passager.class);
		query.setParameter("idReservation", s);
		Passager res = query.getSingleResult();
		em.close();
		delete(res);
	}

	public static boolean verifReservation(String s) {
		EntityManager em = DBHelper.createEntityManager();
		TypedQuery<Passager> query = em.createQuery(
				"select reservation from Vol v join v.passager reservation where reservation.idReservation=:idReservation",
				Passager.class);
		query.setParameter("idReservation", s);
		List<Passager> res = query.getResultList();
		em.close();
		return res.size() != 0;
	}

	public static List<Passager> listeReservations(String n, String p) {
		EntityManager em = DBHelper.createEntityManager();
		TypedQuery<Passager> query = em.createQuery(
				"select reservation from Vol v join v.passager reservation "
						+ "where reservation.nom=:nomReservation and reservation.prenom=:prenomReservation",
				Passager.class);
		query.setParameter("nomReservation", n);
		query.setParameter("prenomReservation", p);
		List<Passager> res = query.getResultList();
		em.close();
		return res;
	}
}
