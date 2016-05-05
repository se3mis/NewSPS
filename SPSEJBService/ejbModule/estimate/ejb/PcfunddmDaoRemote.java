package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;

import estimate.model.FundSource;
import estimate.model.Pcfunddm;

@Remote
public interface PcfunddmDaoRemote {
	List<Pcfunddm> getFundSourceList(String deptId, String webAppName);
	List<String> getApplicationTypes(String deptId, String webAppName);
	public List<String> getFundSources(String deptId, String webAppName);
	public List<String> getFundIds(String deptId,String foundsource, String webAppName);
	
}
