package inventory.service;

import java.math.BigDecimal;
import java.util.List;

import inventory.model.Inwrhmtm;

public interface InwrhmtmEjbI {
	Inwrhmtm findByMatCd(String matCd, String warehouse);
	BigDecimal findUnitPriceByMatCd(String matCd, String webAppName);
	public List<String> findNPLMatCds(String deptId, String webAppName);
	public List<String> findMatCds(String deptId, String webAppName);
	public BigDecimal findUPByMatCd(String matCd, String warehouse);
}
