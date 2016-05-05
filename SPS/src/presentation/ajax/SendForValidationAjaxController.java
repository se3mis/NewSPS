package presentation.ajax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import job.model.Pcrstypm;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.common.ApplicationStatus;
import util.common.Constants;


import application.model.Applicant;
import application.model.Application;
import application.model.WiringLandDetail;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;

import costcenter.model.Gldeptm;
import costcenter.service.GldeptmEjb;

import estimate.dto.EstimateDetails;
import estimate.dto.Norm;
import estimate.model.FundSource;
import estimate.model.Pcfunddm;
import estimate.model.Pcrsgrpm;
import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.Spnorms;
import estimate.model.Spstdestdmt;
import estimate.model.SpstdestdmtPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.EstimateEjb;
import estimate.service.SpestedyEjb;

public class SendForValidationAjaxController {
	


public JSONObject  loadEstmationRefNumbersForValidation(HttpServletRequest request) {
		
		JSONArray applicationRefNumbers = new JSONArray();

		JSONObject objectlist = new JSONObject();

		List<String> applicationNos  = (List<String>) request.getSession().getAttribute("estimationRefNos");
		try {
			
		  for (String applicationNo : applicationNos) {
			  	JSONObject appliNoJson = new JSONObject();
				
				appliNoJson.put("id", applicationNo);
				appliNoJson.put("name", applicationNo);
				
	        	applicationRefNumbers.put(appliNoJson);
	       }
		  	
	       objectlist.put("jsonarrayapplicationrefs", applicationRefNumbers);

		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		return objectlist;

	}

	
}