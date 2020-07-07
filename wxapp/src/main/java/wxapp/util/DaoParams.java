package wxapp.util;

import java.util.HashMap;
import java.util.Map;

public class DaoParams {
	boolean isNative;
	String jpql;
	int firstResult;
	int maxResult;
	Map<Object, Object> params;

	public static DaoParams get() {
		return new DaoParams();
	}

	public boolean isNative() {
		return isNative;
	}

	public DaoParams setNative(boolean isNative) {
		this.isNative = isNative;
		return this;
	}

	public String getJpql() {
		return jpql;
	}

	public DaoParams setJpql(String jpql) {
		this.jpql = jpql;
		return this;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public DaoParams setFirstResult(int firstResult) {
		this.firstResult = firstResult;
		return this;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public DaoParams setMaxResult(int maxResult) {
		this.maxResult = maxResult;
		return this;
	}

	public Map<Object, Object> getParams() {
		if (null == params)
			params = new HashMap<>();
		return params;
	}

	public DaoParams addParams(String key, Object value) {
		getParams().put(key, value);
		return this;
	}

	public DaoParams setParams(Object... params) {
		for (int i = 0; i < params.length; i++) {
			getParams().put(i + 1, params[i]);
		}
		return this;
	}
}
