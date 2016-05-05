package util.common;

import java.math.BigDecimal;
import java.util.List;

import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;

import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pcesthmt;
import job.model.PcesthmtPK;

public class Transfer {
	public void done(Pcesthtt pcesthtt, List<Pcestdtt> pcestdttList){
		PcesthmtPK id=new PcesthmtPK();
		id.setEstimateNo(pcesthtt.getId().getEstimateNo());
		id.setDeptId(pcesthtt.getId().getDeptId());
		id.setRevNo(pcesthtt.getId().getRevNo());
		
		Pcesthmt pcesthmt=new Pcesthmt(id, pcesthtt.getActualUnits(), 
				pcesthtt.getAllocPaid(), pcesthtt.getAllocSettle(), pcesthtt.getAprDt1(), 
				pcesthtt.getAprDt2(), pcesthtt.getAprDt3(), pcesthtt.getAprDt4(), pcesthtt.getAprDt5(), pcesthtt.getAprUid1(), 
				pcesthtt.getAprUid2(), pcesthtt.getAprUid3(), pcesthtt.getAprUid4(), pcesthtt.getAprUid5(), 
				pcesthtt.getCatCd(), pcesthtt.getClientNm(), pcesthtt.getConfBy(), pcesthtt.getConfDt(), 
				pcesthtt.getContNo(), pcesthtt.getControlled(), pcesthtt.getCustContrib(), 
				pcesthtt.getDescr(), pcesthtt.getEntBy(), pcesthtt.getEntDt(), new BigDecimal(pcesthtt.getEstType()), 
				pcesthtt.getEtimateDt(), pcesthtt.getFundContrib(), pcesthtt.getFundId(), 
				pcesthtt.getFundSource(), pcesthtt.getLabel1(), pcesthtt.getLabel10(), pcesthtt.getLabel2(), 
				pcesthtt.getLabel3(), pcesthtt.getLabel4(), pcesthtt.getLabel5(), pcesthtt.getLabel6(), 
				pcesthtt.getLabel7(), pcesthtt.getLabel8(), pcesthtt.getLabel9(), pcesthtt.getLogId(), 
				pcesthtt.getNormDefault(), pcesthtt.getPaidAmt(), pcesthtt.getPartPcnt(), 
				pcesthtt.getPartialAmt(), pcesthtt.getPartialPmt(), pcesthtt.getPriority(), 
				pcesthtt.getPrjAssDt(), pcesthtt.getProjectNo(), pcesthtt.getRejctDt(), pcesthtt.getRejctUid(), 
				pcesthtt.getRejectReason(), pcesthtt.getRevReason(), pcesthtt.getReviseDt(), 
				pcesthtt.getReviseEst(), pcesthtt.getReviseUid(), pcesthtt.getSettledAmt(), 
				pcesthtt.getStatus(), pcesthtt.getStdCost(), pcesthtt.getSubCont(), 
				pcesthtt.getSupCd(), pcesthtt.getTaxAmt(), pcesthtt.getTaxPcnt(), pcesthtt.getTmplId());
		
		if (pcestdttList!=null){
			for(int i=0; i<=pcestdttList.size()-1; i++){
				PcestdmtPK id2=new PcestdmtPK();
				id2.setEstimateNo(pcestdttList.get(i).getId().getEstimateNo());
				id2.setDeptId(pcestdttList.get(i).getId().getDeptId());
				id2.setResCd(pcestdttList.get(i).getId().getResCd());
				id2.setRevNo(pcestdttList.get(i).getId().getRevNo());
				Pcestdmt pcestdmt =new Pcestdmt(id2, pcestdttList.get(i).getApprovedCost(), pcestdttList.get(i).getApprovedQty(), pcestdttList.get(i).getCommitedCost(), pcestdttList.get(i).getCommitedQty(), pcestdttList.get(i).getCustomerQty(), pcestdttList.get(i).getDamageQty(), pcestdttList.get(i).getEstimateCost(), pcestdttList.get(i).getEstimateQty(), pcestdttList.get(i).getGenRes(), pcestdttList.get(i).getIssuedCost(), pcestdttList.get(i).getIssuedQty(), pcestdttList.get(i).getNormDefault(), pcestdttList.get(i).getRequestedCost(), pcestdttList.get(i).getRequestedQty(), pcestdttList.get(i).getResCat(), pcestdttList.get(i).getResType(), pcestdttList.get(i).getReturnedCost(), pcestdttList.get(i).getReturnedQty(), pcestdttList.get(i).getTolerance(), pcestdttList.get(i).getUnitPrice(), pcestdttList.get(i).getUom(),pcestdttList.get(i).getMntQty());
			}}
		
		
	
	}

}
