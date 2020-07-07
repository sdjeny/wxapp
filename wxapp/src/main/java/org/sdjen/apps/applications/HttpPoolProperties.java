package org.sdjen.apps.applications;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by qxr4383 on 2018/12/25.
 */
@Component
@PropertySource(value = { "classpath:properties/httpConfig.properties" })
@ConfigurationProperties(prefix = "http.pool.conn")
@Data
public class HttpPoolProperties {
	private Integer maxTotal;
	private Integer defaultMaxPerRoute;
	private Integer connectTimeout;
	private Integer connectionRequestTimeout;
	private Integer socketTimeout;
	private Integer validateAfterInactivity;

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}

	public void setDefaultMaxPerRoute(Integer defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Integer getValidateAfterInactivity() {
		return validateAfterInactivity;
	}

	public void setValidateAfterInactivity(Integer validateAfterInactivity) {
		this.validateAfterInactivity = validateAfterInactivity;
	}

}