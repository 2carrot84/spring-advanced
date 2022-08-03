package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {
	private final Component component;

	public MessageDecorator(Component component) {
		this.component = component;
	}

	@Override
	public String operateion() {
		log.info("MessageDecorator 실행");

		String result = component.operateion();
		return "*****" + result + "*****";
	}
}
