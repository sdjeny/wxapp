package wxapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements Dao {
	@PersistenceContext
	EntityManager em;

	@Override
	public <T> T merge(T obj) {
		return em.merge(obj);
	}

	@Override
	public <T> T find(Class<T> clazz, Object key) {
		return em.find(clazz, key);
	}

	@Override
	public void remove(Object obj) {
		em.remove(obj);
	}

}
