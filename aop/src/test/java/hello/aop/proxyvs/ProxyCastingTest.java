package hello.aop.proxyvs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyCastingTest {

	@Test
	void jdkProxy() {
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(false); // JDS 동적 프록시

		MemberService memberServiceproxy = (MemberService)proxyFactory.getProxy();

		Assertions.assertThrows(ClassCastException.class, () -> {
			MemberServiceImpl castingMemberService = (MemberServiceImpl)memberServiceproxy;
		});
	}

	@Test
	void cglibProxy() {
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(true); // CGLIB 프록시

		MemberService memberServiceproxy = (MemberService)proxyFactory.getProxy();
		log.info("proxy class={}", memberServiceproxy.getClass());

		MemberServiceImpl castingMemberService = (MemberServiceImpl)memberServiceproxy;
	}
}
