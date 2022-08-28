package hello.proxy.postprocessor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

public class BasicTest {
	@Test
	void basicConfig() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			BasicConfg.class);

		A a = applicationContext.getBean("beanA", A.class);
		a.helloA();

		assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean("beanB"));
	}

	@Slf4j
	@Configuration
	static class BasicConfg {
		@Bean(name = "beanA")
		public A a() {
			return new A();
		}
	}

	@Slf4j
	static class A {
		public void helloA() {
			log.info("hello A");
		}
	}

	@Slf4j
	static class B {
		public void helloB() {
			log.info("hello B");
		}
	}

}
