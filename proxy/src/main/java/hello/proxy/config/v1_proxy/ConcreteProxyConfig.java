package hello.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concreteProxy.OrderContollerConcreteProxy;
import hello.proxy.config.v1_proxy.concreteProxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concreteProxy.OrderServiceCocreteProxy;
import hello.proxy.trace.logtrace.LogTrace;

@Configuration
public class ConcreteProxyConfig {

	@Bean
	public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
		OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderServiceV2(logTrace));
		return new OrderContollerConcreteProxy(orderControllerV2, logTrace);
	}

	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace logTrace){
		OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2(logTrace));
		return new OrderServiceCocreteProxy(orderServiceV2, logTrace);
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
		OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();
		return new OrderRepositoryConcreteProxy(orderRepositoryV2, logTrace);
	}
}
