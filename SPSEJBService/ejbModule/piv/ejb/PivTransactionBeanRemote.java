package piv.ejb;
import javax.ejb.Remote;

import estimate.model.Spstdesthmt;

import application.model.Application;

import piv.model.PivDetail;

@Remote
public interface PivTransactionBeanRemote {
	void confirm(PivDetail pivDetail, Application application, String webAppName);
	public void confirm(PivDetail pivDetail, Spstdesthmt spstdesthmt, String webAppName);

}
