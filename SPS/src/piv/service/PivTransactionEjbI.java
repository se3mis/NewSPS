package piv.service;

import estimate.model.Spstdesthmt;
import application.model.Application;
import piv.model.PivDetail;

public interface PivTransactionEjbI {
	void confirm(PivDetail pivDetail, Application application);
	public void confirmPIV2(PivDetail pivDetail, Spstdesthmt spstdesthmt);
}
