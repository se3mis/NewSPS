package progressMonitoring.service;

import java.util.List;

import javax.persistence.PersistenceException;

import job.model.Pcesthmt;

import estimate.model.Spstdestdmt;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilesumary;



public interface ProgressMonitoringEjbI {
	
	public int updateProgress(Pcmiledates pcmiledate,Pcmilesumary pcmilesumary,String webAppName );
}
