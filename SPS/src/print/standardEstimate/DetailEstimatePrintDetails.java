/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package print.standardEstimate;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement

public class DetailEstimatePrintDetails {

    private String estimateDate;
    private String appRefNo;
    private String estCat;
    private String fundSource;
    private String description;
    private String estimateName;
//    private double pageTotal = 0;
//    private double subTotal;
    private double total;
    private Integer pageNo;
    private Integer totalPages = 0;
    private List<String> arrResName = new ArrayList<String>();
    private List<String> arrResCode = new ArrayList<String>();
    private List<String> arrUom = new ArrayList<String>();
    private List<String> arrEst_qty = new ArrayList<String>();
    private List<String> arrPrice = new ArrayList<String>();
    private List<String> arrEst_cost = new ArrayList<String>();
    private int totResCount;
    private int pageCount;
    private String printDate;
    
   
    
    public String getEstimateName() {
		return estimateName;
	}

	public void setEstimateName(String estimateName) {
		this.estimateName = estimateName;
	}

	
    
    public String getEstCat() {
        return estCat;
    }

    public void setEstCat(String estCatt) {
        if (estCatt != null) {
        this.estCat = estCatt;
        }
    }


    public String getAppRefNo() {
        return appRefNo;
    }

    public void setAppRefNo(String appRefNo) {
        if (appRefNo != null) {
        this.appRefNo = appRefNo;
        }
    }

//    public double getPageTotal() {
//        return pageTotal;
//    }
//
//    public void setPageTotal(double pageTotal) {
//        if (pageTotal != 0) {
//        this.pageTotal = pageTotal;
//        }
//    }
//
//    public double getSubTotal() {
//        return subTotal;
//    }
//
//    public void setSubTotal(double subTotal) {
//        if (subTotal != 0) {
//        this.subTotal = subTotal;
//        }
//    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        if (total != 0) {
        this.total = total;
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo != null) {
        this.pageNo = pageNo;
        }
    }

//    public Integer getTotalPages() {
//        return totalPages;
//    }
//
//    public void setTotalPages(Integer totalPages) {
//        if (totalPages != null) {
//        this.totalPages = totalPages;
//        }
//    }

  
    

   
    public int getTotResCount() {
        return totResCount;
    }

    public List<String> getArrResName() {
		return arrResName;
	}

	public void setArrResName(List<String> arrResName) {
		this.arrResName = arrResName;
	}

	public List<String> getArrResCode() {
		return arrResCode;
	}

	public void setArrResCode(List<String> arrResCode) {
		this.arrResCode = arrResCode;
	}

	public List<String> getArrUom() {
		return arrUom;
	}

	public void setArrUom(List<String> arrUom) {
		this.arrUom = arrUom;
	}

	public List<String> getArrEst_qty() {
		return arrEst_qty;
	}

	public void setArrEst_qty(List<String> arrEst_qty) {
		this.arrEst_qty = arrEst_qty;
	}

	public List<String> getArrPrice() {
		return arrPrice;
	}

	public void setArrPrice(List<String> arrPrice) {
		this.arrPrice = arrPrice;
	}

	public List<String> getArrEst_cost() {
		return arrEst_cost;
	}

	public void setArrEst_cost(List<String> arrEst_cost) {
		this.arrEst_cost = arrEst_cost;
	}

	public void setTotResCount(int totResCount) {
        this.totResCount = totResCount;

    }

	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
    
}
