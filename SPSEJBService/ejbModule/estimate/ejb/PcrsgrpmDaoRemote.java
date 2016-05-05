package estimate.ejb;
import javax.ejb.Local;

import estimate.model.Pcrsgrpm;

@Local
public interface PcrsgrpmDaoRemote {
	public Pcrsgrpm getResourceDetails(String resourType,String code,String webAppName);
}
