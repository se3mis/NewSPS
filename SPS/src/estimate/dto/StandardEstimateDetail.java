package estimate.dto;

import java.util.List;

import piv.model.TempTb;

import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.SpPointdmt;
import estimate.model.Spstdestdmt;
import estimate.model.Spstdesthmt;

public class StandardEstimateDetail {
	private List<Spstdestdmt> dmts;
	private Spstdesthmt hmt;
	public List<Spstdestdmt> getDmts() {
		return dmts;
	}
	public void setDmts(List<Spstdestdmt> dmts) {
		this.dmts = dmts;
	}
	public Spstdesthmt getHmt() {
		return hmt;
	}
	public void setHmt(Spstdesthmt hmt) {
		this.hmt = hmt;
	}
	
	
}
