package hello.advanced.app.V2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
	private final OrderServiceV2 orderService;
	private final HelloTraceV2 trace;

	@GetMapping("/v2/request")
	public String requset(String itemId) {
		TraceStatus status = null;
		try {
			status = trace.begin("OrderController.requset()");
			orderService.orderItem(status.getTraceId(), itemId);
			trace.end(status);
			return "ok";
		} catch (Exception e) {
			trace.exeception(status, e);
			throw e;
		}
	}
}
