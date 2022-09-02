package hello.aop.pointcut;

import java.lang.reflect.Method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutionTest {
	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	@Test
	void printMethod() {
		log.info("helloMethod={}", helloMethod);
	}

	@Test
	void exactMethod() {
		// public java.lang.String hello.aop.order.member.MemberServiceImpl.hello(java.lang.String)
		pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void allMatch() {
		pointcut.setExpression("execution(* *(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void nameMatch() {
		pointcut.setExpression("execution(* hello(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void nameMatchStar1() {
		pointcut.setExpression("execution(* hel*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void nameMatchStar2() {
		pointcut.setExpression("execution(* *el*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void nameMatchFalse() {
		pointcut.setExpression("execution(* nono(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	void pacakageExactMatch1() {
		pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void pacakageExactMatch2() {
		pointcut.setExpression("execution(* hello.aop.member.*.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void pacakageExactFalse() {
		pointcut.setExpression("execution(* hello.aop.*.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	void pacakageMatchSubPackage1() {
		pointcut.setExpression("execution(* hello.aop.member..*.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void pacakageMatchSubPackage2() {
		pointcut.setExpression("execution(* hello.aop..*.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void typeExactMatch() {
		pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void typeMatchSuperType() {
		pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
	}

	@Test
	void typeMatchInternal() throws NoSuchMethodException {
		pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");

		Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
		Assertions.assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
		pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");

		Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
		Assertions.assertThat(pointcut.matches(internalMethod, MemberService.class)).isFalse();
	}

	@Test
	void argsMatch() {
		pointcut.setExpression("execution(* *(String))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void argsMatchNoArgs() {
		pointcut.setExpression("execution(* *())");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	void argsMatchStar() {
		pointcut.setExpression("execution(* *(*))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void argsMatchAll() {
		pointcut.setExpression("execution(* *(..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void argsMatchComplex() {
		pointcut.setExpression("execution(* *(String, ..))");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
}
