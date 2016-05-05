package estimate.ejb;

import inventory.ejb.InmatmDaoRemote;
import inventory.ejb.InwrhmapDaoRemote;
import inventory.ejb.InwrhmtmDaoRemote;
import inventory.model.Inwrhmtm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import job.ejb.PcestdmtDaoRemote;
import job.ejb.PcesthmtDaoRemote;
import job.ejb.PcprjbalDaoRemote;
import job.ejb.PcrstypmDaoRemote;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pcesthmt;
import job.model.PcesthmtPK;
import job.model.Pcprjbal;
import job.model.PcprjbalPK;
import job.model.Pcrstypm;

import masters.ejb.ProvinceDetailsMasterLocal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import piv.ejb.TempTbDaoRemote;
import piv.model.TempTb;
import progressMonitoring.ejb.PcinitialDaoRemote;
import progressMonitoring.ejb.PcmiledatesDaoRemote;
import progressMonitoring.ejb.PcmilesumaryDaoRemote;
import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilesumary;
import security.ejb.SecurityBeanRemote;
import util.common.AppStatus;
import util.common.AppointmentStatus;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.PIVStatus;
import util.common.StandardEstimateStatus;
import util.emSelect.EmSelector;
import application.ejb.ApplicationDaoRemote;
import application.ejb.ApplicationRefBSDaoRemote;
import application.ejb.ApplicationReferenceDaoRemote;
import application.ejb.WiringLandDetailDaoRemote;
import application.model.ApplicationReference;
import application.model.CityMap;
import costcenter.ejb.GldeptmDaoRemote;
import estimate.dto.DetailEstimateDTO;
import estimate.model.AllocationSummaryDisplay;
import estimate.model.Appestlim;
import estimate.model.Approval;
import estimate.model.CostCalculationMaster;
import estimate.model.DefaultMatGrid;
import estimate.model.EstimateDisplay;
import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.model.FundSource;
import estimate.model.LabourGrid;
import estimate.model.MaterialGrid;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.Pcfunddm;
import estimate.model.Pcrsgrpm;
import estimate.model.Pegschdmt;
import estimate.model.SpNormsGroup;
import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.SpestedyCons;
import estimate.model.SpestedyConsPK;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Spestmtm;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Splpsvcm;
import estimate.model.SplpsvcmPK;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;
import estimate.model.Spratesm;
import estimate.model.SpratesmPK;
import estimate.model.Spsecdep;
import estimate.model.SpsecdepPK;
import estimate.model.Spserest;
import estimate.model.Spstdestdmt;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.SpstdesthmtEjb;

/**
 * Session Bean implementation class JobBean
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class ProgressMonitoringBean extends EmSelector implements ProgressMonitoringBeanRemote,
		ProgressMonitoringBeanLocal {
	private Log LOGGER = LogFactory.getLog(ProgressMonitoringBean.class);
	@Resource
	private SessionContext context;
	
	
	@EJB
	PcinitialDaoRemote pcinitialDaoRemote;
	
	@EJB
	PcmiledatesDaoRemote pcmiledatesDaoRemote;
	
	@EJB
	PcmilesumaryDaoRemote pcmilesumaryDaoRemote;
	
	private Format format;
	/*@EJB
	EstimateTypeMasterDao estimateTypeMasterDao;*/
	
    /**
     * Default constructor. 
     */
    public ProgressMonitoringBean() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public int updateProgress(Pcmiledates pcmiledate, Pcmilesumary pcmilesumary,String webAppName) {
		try{
			Pcmilesumary summary= pcmilesumaryDaoRemote.getMilestoneList(pcmiledate.getId().getDeptId(), pcmiledate.getId().getProjectNumber(), webAppName);
			if(pcmilesumary == null){
				pcmilesumaryDaoRemote.createPcmilesumary(pcmilesumary, webAppName);
			}else{
				pcmilesumaryDaoRemote.updatePcmilesumary(pcmilesumary, webAppName);
			}
			Pcmiledates pcmiledates= pcmiledatesDaoRemote.getMilestoneById(pcmiledate.getId().getDeptId(), pcmiledate.getId().getProjectNumber(),pcmiledate.getId().getMileId(), webAppName);
			if(pcmiledates == null){
				pcmiledatesDaoRemote.createpcmiledates(pcmiledate, webAppName);
			}else{
				pcmiledatesDaoRemote.updatepcmiledates(pcmiledate, webAppName);
			}
		
		
		return 1;
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
		
	}
	
    
	
	
	

}
