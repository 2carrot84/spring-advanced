package hello.advanced.trace.hellotrace;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.TraceStatus;

class HelloTraceV2Test {
	@Test
	void begin_end() {
		HelloTraceV2 trace = new HelloTraceV2();
		TraceStatus status = trace.begin("hello");
		TraceStatus status1 = trace.beginSync(status.getTraceId(), "hello2");
		trace.end(status1);
		trace.end(status);
	}
	
	@Test
	void begin_exception() {
		HelloTraceV2 trace = new HelloTraceV2();
		TraceStatus status = trace.begin("hello");
		TraceStatus status1 = trace.beginSync(status.getTraceId(), "hello2");
		trace.exception(status1, new IllegalStateException());
		trace.exception(status, new IllegalStateException());
	}
}