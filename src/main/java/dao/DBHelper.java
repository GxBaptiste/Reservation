package dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBHelper {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static EntityManager createEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("reservation");
		}
		entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	public static void commitTxAndClose(EntityManager entityManager) {
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void rollbackTxAndClose(EntityManager entityManager) {
		entityManager.getTransaction().rollback();
		entityManager.close();
	}

	public static void beginTx(EntityManager entityManager) {
		entityManager.getTransaction().begin();
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

}
