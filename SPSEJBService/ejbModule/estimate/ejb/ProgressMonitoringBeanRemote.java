package estimate.ejb;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import piv.model.TempTb;
import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilesumary;

import job.model.Pcestdmt;
import job.model.Pcesthmt;
import job.model.Pcrstypm;

import costcenter.model.Gldeptm;

import estimate.dto.DetailEstimateDTO;
import estimate.model.AllocationSummaryDisplay;
import estimate.model.Approval;
import estimate.model.DefaultMatGrid;
import estimate.model.EstimateDisplay;
import estimate.model.EstimateReference;
import estimate.model.FundSource;
import estimate.model.LabourGrid;
import estimate.model.MaterialGrid;
import estimate.model.Pcrsgrpm;
import estimate.model.Pegschdmt;
import estimate.model.SpNormsGroup;
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

@Remote
@SuppressWarnings("rawtypes")
public interface ProgressMonitoringBeanRemote {
	public int updateProgress(Pcmiledates pcmiledate,Pcmilesumary pcmilesumary,String webAppName );
}
