package consumer.ejb;
import javax.ejb.Remote;

import consumer.model.Consumer;

@Remote
public interface ConsumerDaoRemote {
	void update(String ccc);
	Consumer findApplicationRefNo(String applicationRefNo, String deptId, String webAppName);

}
