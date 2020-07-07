package org.sdjen.apps.configuration;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.sdjen.apps.applications.HttpPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	// https://www.cnblogs.com/javazhiyin/p/9851775.html
	// http://www.mkeeper.club/2018/09/17/SpringBoot%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B2-1-11%20RestTemplate%E6%95%B4%E5%90%88HttpClient/

	@Autowired
	private HttpPoolProperties httpPoolProperties;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(httpRequestFactory());
	}

	@Bean
	public ClientHttpRequestFactory httpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory(httpClient());
	}

	@Bean
	public HttpClient httpClient() {
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		connectionManager.setMaxTotal(httpPoolProperties.getMaxTotal());
		connectionManager.setDefaultMaxPerRoute(httpPoolProperties.getDefaultMaxPerRoute());
		connectionManager.setValidateAfterInactivity(httpPoolProperties.getValidateAfterInactivity());
		RequestConfig requestConfig = RequestConfig.custom()
				// 服务器返回数据(response)的时间，超过抛出read timeout
				.setSocketTimeout(httpPoolProperties.getSocketTimeout())
				// 连接上服务器(握手成功)的时间，超出抛出connect timeout
				.setConnectTimeout(httpPoolProperties.getConnectTimeout())
				// 从连接池中获取连接的超时时间//超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException:
				// Timeout waiting for connection from pool
				.setConnectionRequestTimeout(httpPoolProperties.getConnectionRequestTimeout()).build();
		return HttpClientBuilder//
				.create()//
				.setDefaultRequestConfig(requestConfig)//
				.setConnectionManager(connectionManager)//
				.build();
	}
}