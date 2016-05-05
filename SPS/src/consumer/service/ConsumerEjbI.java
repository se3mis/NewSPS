package consumer.service;

import consumer.model.Consumer;

public interface ConsumerEjbI {
	void update(String ccc);
	Consumer findApplicationRefNo(String applicationRefNo, String deptId);
}
