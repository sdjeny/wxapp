package org.sdjen.apps.service;

import java.util.Map;

public interface HttpService {

	String get(String url);

	String get(String url, Map<String, ?> variables);

}
