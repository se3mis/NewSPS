package util.common;
import javax.ejb.Remote;

@Remote
public interface ManagerRemote {

	EntityManager getEntityManager(String webAppName);

}
