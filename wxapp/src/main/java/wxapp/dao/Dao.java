package wxapp.dao;

public interface Dao {
	<T> T merge(T obj);

	<T> T find(Class<T> clazz, Object key);

	void remove(Object obj);

}
