package wxapp.dao;

import java.util.List;

import wxapp.util.DaoParams;

public interface Dao {
	<T> T merge(T obj);

	<T> T find(Class<T> clazz, Object key);

	void remove(Object obj);

	<E> List<E> getList(DaoParams params);

	<E> E getObject(DaoParams params);

	int update(DaoParams params);

}
