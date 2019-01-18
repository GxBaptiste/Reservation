package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modele.Vol;

public class VolDAO {
	

	private VolDAO() {

	  }

	public static void createVol(Vol v) {
		EntityManager entity = DBHelper.createEntityManager();
		entity.getTransaction().begin();
		entity.persist(v);
		entity.getTransaction().commit();
		entity.close();
	}

	public static void update(Vol v) {
		EntityManager entity = DBHelper.createEntityManager();
		entity.getTransaction().begin();
		entity.merge(v);
		entity.getTransaction().commit();
		entity.close();
	}

	public static void delete(Vol v) {
		EntityManager entity = DBHelper.createEntityManager();
		entity.getTransaction().begin();
		entity.remove(entity.find(Vol.class, v.getId()));
		entity.getTransaction().commit();
		entity.close();
	}

	public static Vol rechercheVol(String s) {
		EntityManager em = DBHelper.createEntityManager();
		TypedQuery<Vol> query = em.createQuery("select distinct v from Vol v where v.numVol=:numVol ", Vol.class);
		query.setParameter("numVol", s);
		Vol res = query.getSingleResult();
		em.close();
		return res;
	}

	public static List<Vol> listeVols() {
		EntityManager em = DBHelper.createEntityManager();
		TypedQuery<Vol> query = em.createQuery("select distinct v from Vol v", Vol.class);
		List<Vol> res = query.getResultList();
		em.close();
		return res;
	}

	public static List<Vol> listeVolVille(String depart, String arrive) {
		EntityManager em = DBHelper.createEntityManager();
		TypedQuery<Vol> query = em
				.createQuery("select distinct v from Vol v where v.villeD=:villeD and v.villeA=:villeA", Vol.class);
		query.setParameter("villeD", depart);
		query.setParameter("villeA", arrive);
		List<Vol> res = query.getResultList();
		em.close();
		return res;
	}
}
