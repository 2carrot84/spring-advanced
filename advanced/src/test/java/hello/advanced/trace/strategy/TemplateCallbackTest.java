package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.template.CallBack;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateCallbackTest {
	@Test
	void callbackV1() {
		TimeLogTemplate template = new TimeLogTemplate();
		template.excute(new CallBack() {
			@Override
			public void call() {
				log.info("비지니스 로직1 실행");
			}
		});

		template.excute(new CallBack() {
			@Override
			public void call() {
				log.info("비지니스 로직2 실행");
			}
		});
	}

	@Test
	void callbackV2() {
		TimeLogTemplate template = new TimeLogTemplate();
		template.excute(() -> log.info("비지니스 로직1 실행"));
		template.excute(() -> log.info("비지니스 로직2 실행"));
	}
}
