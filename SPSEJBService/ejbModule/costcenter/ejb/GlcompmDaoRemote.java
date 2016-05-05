package costcenter.ejb;
import java.util.List;


import javax.ejb.Remote;
import costcenter.model.Area;
import costcenter.model.Glcompm;
import costcenter.model.Province;

@Remote
public interface GlcompmDaoRemote {
	List<Area> getAllAreas(String webAppName);
	List<Province> getAllProvince(String webAppName);
	List<String> getAreasForProvince(String deptid, String webAppName);
	
	String getAreaCodesByName(String deptid,String compNm, String webAppName);
	List<String> getAreasByDeptId(String deptid, String webAppName);
}
