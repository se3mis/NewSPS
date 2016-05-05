package job.ejb;

import javax.ejb.Stateless;

import util.emSelect.EmSelector;

import job.model.Pcprjbal;

/**
 * Session Bean implementation class PcprjbalDao
 */
@Stateless
public class PcprjbalDao extends EmSelector implements PcprjbalDaoRemote, PcprjbalDaoLocal {
	
    /**
     * Default constructor. 
     */
    public PcprjbalDao() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void createPcprjbal(Pcprjbal pcprjbal, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(pcprjbal);
		
	}

}
