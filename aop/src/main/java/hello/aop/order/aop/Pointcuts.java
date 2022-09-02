package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

	@Pointcut("execution(* hello.aop.order..*(..))")    // pointicut
	public void allOrder() {
	}    // point cut

	@Pointcut("execution(* *..*Service.*(..))")    // pointicut
	public void allService() {
	}    // point cut signature

	@Pointcut("allOrder() && allService()")
	public void orderAndService() {

	}
}
