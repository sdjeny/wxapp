package wxapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import wxapp.util.DaoParams;

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

	private Query getQuery(@NotNull DaoParams params) {
		Query query = params.isNative() ? em.createNativeQuery(params.getJpql()) : em.createQuery(params.getJpql());
		if (params.getFirstResult() > -1) {
			query.setFirstResult(params.getFirstResult());
		}
		if (params.getMaxResult() > 0) {
			query.setMaxResults(params.getMaxResult());
		}
		params.getParams().forEach((k, v) -> {
			if (k instanceof Integer) {
				query.setParameter((Integer) k, v);
			} else if (k instanceof String) {
				query.setParameter((String) k, v);
			}
		});
		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> getList(DaoParams params) {
		return getQuery(params).getResultList();
	}

	@Override
	public <E> E getObject(DaoParams params) {
		List<E> ls = getList(params.setMaxResult(1));
		return ls.isEmpty() ? null : ls.iterator().next();
	}

	@Override
	public int update(DaoParams params) {
		return getQuery(params).executeUpdate();
	}
}
