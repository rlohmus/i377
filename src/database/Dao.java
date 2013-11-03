package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import database.models.Unit;

public class Dao extends AbstractDao {

	private EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("projekt");

	public boolean deleteAll() throws SQLException {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Query q = em.createQuery("DELETE FROM Unit");
			q.executeUpdate();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;
	}

	public boolean deleteUnit(long id) throws SQLException {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Unit unit = em.find(Unit.class, id);

			if (unit != null) {
				em.remove(unit);

				em.getTransaction().commit();
			}
		} finally {
			em.close();
		}
		return true;
	}

	public boolean addUnit(String name, String code, String superId)
			throws SQLException {
		EntityManager em = emf.createEntityManager();

		try {
			Unit u = new Unit();
			u.setName(name);
			u.setCode(code);

			if (superId != null && !superId.isEmpty()) {
				u.setUnit(this.findUnitByCode(superId));
			}

			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();

		} finally {
			em.close();
		}
		return true;
	}

	public Unit findUnitByCode(String superId) {
		Unit units = null;
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Unit> q = em.createNamedQuery("Unit.UnitByCode", Unit.class);
			q.setParameter("code", superId.toLowerCase());
			units = q.getSingleResult();
		}catch(Exception e) {
			return null;
		} finally {
			em.close();
		}
		return units;
	}

	public List<Unit> search(String keyword) throws SQLException {
		List<Unit> units = new ArrayList<Unit>();
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Unit> q = em.createNamedQuery("Unit.search", Unit.class);
			q.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
			units = q.getResultList();
		}catch(Exception e) {
			return null;
		} finally {
			em.close();
		}
		return units;
	}

	public List<Unit> findAllUnits() throws SQLException {
		List<Unit> units = new ArrayList<Unit>();
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Unit> q = em
					.createNamedQuery("Unit.findAll", Unit.class);
			units = q.getResultList();
		}catch(Exception e) {
			return null;
		} finally {
			em.close();
		}
		return units;
	}

	public List<Unit> findAllChildUnits(long id) throws SQLException {
		List<Unit> units = new ArrayList<Unit>();
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Unit> q = em.createNamedQuery("Unit.findChildUnits",
					Unit.class);
			q.setParameter("id", id);
			units = q.getResultList();
		}catch(Exception e) {
			return null;
		} finally {
			em.close();
		}
		return units;
	}
	
	public Unit findSuperUnit(long id) throws SQLException {
		Unit unit = null;
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Unit> q = em.createNamedQuery("Unit.findSuperUnit",
					Unit.class);
			q.setParameter("id", id);
				unit = q.getSingleResult();
		}catch(Exception e) {
			return null;
		} finally {
			em.close();
		}
		return unit;
	}

	public Unit findUnit(long id) {
		EntityManager em = emf.createEntityManager();
		Unit unit = null;
		try {
			TypedQuery<Unit> q = em.createNamedQuery("Unit.findUnit",
					Unit.class);
			q.setParameter("id", id);
			unit = q.getSingleResult();
		}catch(Exception e) {
			return null;
		} finally {
			em.close();
		}
		return unit;
	}
}