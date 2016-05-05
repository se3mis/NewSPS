package estimate.service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.PersistenceException;

import job.model.Pcrstypm;

import estimate.dto.DetailEstimateDTO;
import estimate.ejb.Spugcblm;
import estimate.ejb.SpugcblmPK;
import estimate.model.Approval;
import estimate.model.DefaultMatGrid;
import estimate.model.EstimateDisplay;
import estimate.model.FundSource;
import estimate.model.LabourGrid;
import estimate.model.MaterialGrid;
import estimate.model.Pcrsgrpm;
import estimate.model.Pegschdmt;
import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.Spnorms;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.Pcfunddm;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;
import estimate.model.Splpsvcm;
import estimate.model.SplpsvcmPK;
import estimate.model.SpnormsPK;
import estimate.model.Spratesm;
import estimate.model.SpratesmPK;
import estimate.model.Spsecdep;
import estimate.model.SpsecdepPK;
import estimate.model.Spstdestdmt;
import estimate.model.Spstdesthmt;
@SuppressWarnings("rawtypes")
public interface PegschdmtEjbI {
	
	public void insert(Pegschdmt pegschdmt, String webAppName) throws PersistenceException;
	public List<Pegschdmt> findByEstimationNo(String estNo, String deptId,String webAppName);
}
