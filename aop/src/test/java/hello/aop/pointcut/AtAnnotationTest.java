package hello.aop.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Import(AtAnnotationTest.AtAnnotationAspect.class)
public class AtAnnotationTest {
	@Autowired
	MemberService memberService;

	@Autowired
	MemberServiceImpl memberService1;


	@Test
	void success() {
		log.info("memberService Proxy={}", memberService.getClass());
		memberService.hello("helloA");
		memberService1.hello("helloB");
		memberService1.internal("internalA");
	}

	@Slf4j
	@Aspect
	static class AtAnnotationAspect {
		@Around("@annotation(hello.aop.member.annotation.MethodAop)")
		public Object doAtAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[@annotation] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
	}
}
