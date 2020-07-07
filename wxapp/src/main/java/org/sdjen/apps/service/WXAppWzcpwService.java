package org.sdjen.apps.service;

public interface WXAppWzcpwService {
	String getDateInitData() throws Throwable;

	String ship_search(int from, int to, String date);
}
