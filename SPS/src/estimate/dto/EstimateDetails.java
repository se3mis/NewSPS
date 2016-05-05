package estimate.dto;

import java.util.List;

import job.model.Pcestdmt;
import job.model.Pcesthmt;

import piv.model.TempTb;

import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.SpPointdmt;

public class EstimateDetails {
	private List<Pcestdtt> dtts;
	private List<Pcestdmt> dmts;
	private Pcesthtt htt;
	private Pcesthmt hmt;
	private TempTb tb;
	public List<Pcestdtt> getDtts() {
		return dtts;
	}
	public void setDtts(List<Pcestdtt> dtts) {
		this.dtts = dtts;
	}
	public Pcesthtt getHtt() {
		return htt;
	}
	public void setHtt(Pcesthtt htt) {
		this.htt = htt;
	}
	public TempTb getTb() {
		return tb;
	}
	public void setTb(TempTb tb) {
		this.tb = tb;
	}
	public List<Pcestdmt> getDmts() {
		return dmts;
	}
	public void setDmts(List<Pcestdmt> dmts) {
		this.dmts = dmts;
	}
	public Pcesthmt getHmt() {
		return hmt;
	}
	public void setHmt(Pcesthmt hmt) {
		this.hmt = hmt;
	}
	
	
}
