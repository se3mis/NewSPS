package _179._6._235._119.cebwebservice;

public class MobileServiceSoapProxy implements _179._6._235._119.cebwebservice.MobileServiceSoap {
  private String _endpoint = null;
  private _179._6._235._119.cebwebservice.MobileServiceSoap mobileServiceSoap = null;
  
  public MobileServiceSoapProxy() {
    _initMobileServiceSoapProxy();
  }
  
  public MobileServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initMobileServiceSoapProxy();
  }
  
  private void _initMobileServiceSoapProxy() {
    try {
      mobileServiceSoap = (new _179._6._235._119.cebwebservice.MobileServiceLocator()).getMobileServiceSoap();
      if (mobileServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mobileServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mobileServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mobileServiceSoap != null)
      ((javax.xml.rpc.Stub)mobileServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public _179._6._235._119.cebwebservice.MobileServiceSoap getMobileServiceSoap() {
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap;
  }
  
  public java.lang.String getAccessToken(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.getAccessToken(userName, password);
  }
  
  public java.lang.String CC_GetLocationSinhala(java.lang.String accountNumber) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetLocationSinhala(accountNumber);
  }
  
  public java.lang.String CC_GetCallCenters(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetCallCenters(accessToken);
  }
  
  public java.lang.String CC_GetPendingJobs(java.lang.String accessToken, java.lang.String CCCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetPendingJobs(accessToken, CCCode);
  }
  
  public java.lang.String CC_GetServerTime(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetServerTime(accessToken);
  }
  
  public java.lang.String CC_GetAreaName(java.lang.String accessToken, java.lang.String areaCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetAreaName(accessToken, areaCode);
  }
  
  public java.lang.String CC_GetProvCodebyCallCentre(java.lang.String accessToken, java.lang.String CCCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetProvCodebyCallCentre(accessToken, CCCode);
  }
  
  public java.lang.String CC_GetProvinceInfo(java.lang.String accessToken, java.lang.String CCCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetProvinceInfo(accessToken, CCCode);
  }
  
  public java.lang.String CC_GetCompletedJobs(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar completedDate) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetCompletedJobs(accessToken, CCCode, completedDate);
  }
  
  public java.lang.String CC_GetCallCenterStaff(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar date) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetCallCenterStaff(accessToken, CCCode, date);
  }
  
  public java.lang.String CC_GetFieldStaff(java.lang.String accessToken, java.lang.String CCCode, java.lang.String provCode, java.util.Calendar date) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetFieldStaff(accessToken, CCCode, provCode, date);
  }
  
  public java.lang.String CC_GetCurrentJobsInRange(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar startDate, java.util.Calendar endDate) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetCurrentJobsInRange(accessToken, CCCode, startDate, endDate);
  }
  
  public java.lang.String CC_GetFailureTypes(java.lang.String accessToken, java.lang.String CCCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetFailureTypes(accessToken, CCCode);
  }
  
  public java.lang.String CC_GetFailureCount(java.lang.String accessToken, java.lang.String CCCode, java.lang.String failureCode, java.util.Calendar yearMonth) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetFailureCount(accessToken, CCCode, failureCode, yearMonth);
  }
  
  public java.lang.String CC_GetMastersFailureAnalysis(java.lang.String masterCode, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetMastersFailureAnalysis(masterCode, accessCode);
  }
  
  public java.lang.String CC_GetCompletedJobsInMonth(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar yearMonth) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetCompletedJobsInMonth(accessToken, CCCode, yearMonth);
  }
  
  public java.lang.String CC_GetAssignedJobs(java.lang.String accessToken, java.lang.String CCCode, java.lang.String areaCode, java.util.Calendar yearMonth) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetAssignedJobs(accessToken, CCCode, areaCode, yearMonth);
  }
  
  public java.lang.String CC_GetComplaints(java.lang.String accessToken, java.lang.String CCCode, java.lang.String areaCode, _179._6._235._119.cebwebservice.ComplaintRetrevalStatus status, java.util.Calendar yearMonth) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetComplaints(accessToken, CCCode, areaCode, status, yearMonth);
  }
  
  public java.lang.String CC_GetFieldStaffInfo(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar startDate, java.util.Calendar endDate, java.lang.String unit, java.lang.String provCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetFieldStaffInfo(accessToken, CCCode, startDate, endDate, unit, provCode);
  }
  
  public java.lang.String CC_GetAllBreakdownJobs(java.lang.String accessToken, java.lang.String CCCode, java.lang.String provCode, java.util.Calendar startYearMonth, java.util.Calendar endYearMonth) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetAllBreakdownJobs(accessToken, CCCode, provCode, startYearMonth, endYearMonth);
  }
  
  public java.lang.String CC_GetAllTemporaryCompletedJobs(java.lang.String accessToken, java.lang.String CCCode, java.lang.String provCode, java.util.Calendar startYearMonth, java.util.Calendar endYearMonth) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetAllTemporaryCompletedJobs(accessToken, CCCode, provCode, startYearMonth, endYearMonth);
  }
  
  public java.lang.String CC_GetNoOfConsumersPerArea(java.lang.String accessToken, java.lang.String areaCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetNoOfConsumersPerArea(accessToken, areaCode);
  }
  
  public java.lang.String billing_GetAreaCodes(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.billing_GetAreaCodes(accessToken);
  }
  
  public java.lang.String billing_GetDailyPack(java.lang.String accessToken, java.lang.String areaCode, java.lang.String readerCode, java.lang.String dailyPack) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.billing_GetDailyPack(accessToken, areaCode, readerCode, dailyPack);
  }
  
  public boolean CC_CheckMobileUser(java.lang.String accessToken, java.lang.String accessCode, java.lang.String password) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_CheckMobileUser(accessToken, accessCode, password);
  }
  
  public java.lang.String CC_GetJobTickets(java.lang.String accessToken, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetJobTickets(accessToken, accessCode);
  }
  
  public int CC_GetPrintCount(java.lang.String accessToken, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetPrintCount(accessToken, accessCode);
  }
  
  public java.lang.String CC_GetMobileUserInfo(java.lang.String accessToken, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetMobileUserInfo(accessToken, accessCode);
  }
  
  public java.lang.String CC_GetMapKey(java.lang.String accessToken, java.lang.String mapType) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetMapKey(accessToken, mapType);
  }
  
  public java.lang.String CC_GetLandMarks(java.lang.String accessToken, java.lang.String accountNo) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetLandMarks(accessToken, accountNo);
  }
  
  public java.lang.String CC_GetCustomerLocation(java.lang.String accessToken, java.lang.String accountNo) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetCustomerLocation(accessToken, accountNo);
  }
  
  public boolean CC_SetMobileLoginAccessState(java.lang.String accessToken, java.lang.String accessCode, java.lang.String deviceID, java.lang.String CCCode, java.lang.String activity) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_SetMobileLoginAccessState(accessToken, accessCode, deviceID, CCCode, activity);
  }
  
  public boolean CC_CompleteJobTicketPrintStatus(java.lang.String accessToken, java.lang.String jobNumber) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_CompleteJobTicketPrintStatus(accessToken, jobNumber);
  }
  
  public java.lang.String CC_GetBreakdownJobs(java.lang.String accessToken, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_GetBreakdownJobs(accessToken, accessCode);
  }
  
  public boolean CC_UpdateJobStatus(java.lang.String accessToken, java.lang.String jobNo, java.lang.String STCode, java.lang.String comment) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.CC_UpdateJobStatus(accessToken, jobNo, STCode, comment);
  }
  
  public void CC_JobComplete(java.lang.String accessToken, java.lang.String accessCode, java.lang.String jobNo, java.lang.String failureType, java.lang.String cause, java.lang.String details, java.lang.String action, java.lang.String comment, java.lang.String STCode, javax.xml.rpc.holders.BooleanHolder CC_JobCompleteResult, javax.xml.rpc.holders.StringHolder errMsg) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    mobileServiceSoap.CC_JobComplete(accessToken, accessCode, jobNo, failureType, cause, details, action, comment, STCode, CC_JobCompleteResult, errMsg);
  }
  
  public java.lang.String HR_GetEmployeeDetails(java.lang.String accessToken, java.lang.String EPFNo) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetEmployeeDetails(accessToken, EPFNo);
  }
  
  public java.lang.String HR_GetEmployeeHierarchy3(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetEmployeeHierarchy3(accessToken);
  }
  
  public java.lang.String HR_GetEmployeeHierarchy4(java.lang.String accessToken, java.lang.String hie3) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetEmployeeHierarchy4(accessToken, hie3);
  }
  
  public java.lang.String HR_GetEmployeeHierarchy5(java.lang.String accessToken, java.lang.String hie3, java.lang.String hie4) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetEmployeeHierarchy5(accessToken, hie3, hie4);
  }
  
  public java.lang.String HR_GetEmployeeHierarchy6(java.lang.String accessToken, java.lang.String hie3, java.lang.String hie4, java.lang.String hie5) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetEmployeeHierarchy6(accessToken, hie3, hie4, hie5);
  }
  
  public java.lang.String HR_GetEmployees(java.lang.String accessToken, java.lang.String hie1, java.lang.String hie2, java.lang.String hie3, java.lang.String hie4, java.lang.String hie5, java.lang.String hie6, java.lang.String dsg, boolean isActive) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetEmployees(accessToken, hie1, hie2, hie3, hie4, hie5, hie6, dsg, isActive);
  }
  
  public java.lang.String HR_GetEmployeesByServiceCategory(java.lang.String accessToken, java.lang.String accessName, java.lang.String serviceCategory, java.lang.String subServiceCategory, java.lang.String designation, java.lang.String grade, java.lang.String scale, boolean isActive) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetEmployeesByServiceCategory(accessToken, accessName, serviceCategory, subServiceCategory, designation, grade, scale, isActive);
  }
  
  public _179._6._235._119.cebwebservice.LEVEL HR_EmpBreakDownByDsgTree(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_EmpBreakDownByDsgTree(accessToken);
  }
  
  public _179._6._235._119.cebwebservice.LEVEL HR_EmpBreakDownByDsg(java.lang.String accessToken, java.lang.String hie1, java.lang.String hie2, java.lang.String hie3, java.lang.String hie4, java.lang.String hie5, java.lang.String hie6) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_EmpBreakDownByDsg(accessToken, hie1, hie2, hie3, hie4, hie5, hie6);
  }
  
  public _179._6._235._119.cebwebservice.LEVEL HR_EmpBreakDownByService(java.lang.String accessToken, java.lang.String serviceCategory, java.lang.String subserviceCategory) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_EmpBreakDownByService(accessToken, serviceCategory, subserviceCategory);
  }
  
  public java.lang.String HR_GetRetiringEmployees(java.lang.String accessToken, java.util.Calendar start, java.util.Calendar end, java.lang.String serviceCategory, java.lang.String subServiceCategory) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetRetiringEmployees(accessToken, start, end, serviceCategory, subServiceCategory);
  }
  
  public java.lang.String HR_GetServiceCategories(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetServiceCategories(accessToken);
  }
  
  public java.lang.String HR_GetSubServiceCategories(java.lang.String accessToken, java.lang.String serviceCategory) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GetSubServiceCategories(accessToken, serviceCategory);
  }
  
  public java.lang.String HR_DesignationByServiceCategories(java.lang.String accessToken, java.lang.String serviceCategory, java.lang.String subServiceCategory) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_DesignationByServiceCategories(accessToken, serviceCategory, subServiceCategory);
  }
  
  public java.lang.String HR_GradeByServiceCategories(java.lang.String accessToken, java.lang.String serviceCategory, java.lang.String subServiceCategory) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_GradeByServiceCategories(accessToken, serviceCategory, subServiceCategory);
  }
  
  public java.lang.String HR_ScaleByServiceCategories(java.lang.String accessToken, java.lang.String serviceCategory, java.lang.String subServiceCategory) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.HR_ScaleByServiceCategories(accessToken, serviceCategory, subServiceCategory);
  }
  
  public _179._6._235._119.cebwebservice.Bill billing_CalculateBill(java.lang.String accessToken, int tariff, int units, java.util.Calendar fromDate, java.util.Calendar toDate) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.billing_CalculateBill(accessToken, tariff, units, fromDate, toDate);
  }
  
  public _179._6._235._119.cebwebservice.Bill billing_CalculateDetailMonthlyBill(java.lang.String accessToken, int tariff, int kwh) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.billing_CalculateDetailMonthlyBill(accessToken, tariff, kwh);
  }
  
  public _179._6._235._119.cebwebservice.TariffCode[] billing_GetTariffCodes(java.lang.String accessToken) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.billing_GetTariffCodes(accessToken);
  }
  
  public java.lang.String billing_GetLastPrintedBill(java.lang.String accessToken, java.lang.String accountNumber) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.billing_GetLastPrintedBill(accessToken, accountNumber);
  }
  
  public java.lang.String billing_GetPaymentTransactions(java.lang.String accessToken, java.lang.String accountNumber) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.billing_GetPaymentTransactions(accessToken, accountNumber);
  }
  
  public java.lang.String billing_GetLatestPayment(java.lang.String accessToken, java.lang.String accountNumber, java.util.Calendar lastReadingDate) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.billing_GetLatestPayment(accessToken, accountNumber, lastReadingDate);
  }
  
  public java.lang.String circulars_GetCircularList(java.lang.String accessToken, java.lang.String keyWord) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.circulars_GetCircularList(accessToken, keyWord);
  }
  
  public java.lang.String circulars_GetKPIArea(java.lang.String accessToken, java.lang.String monthID) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.circulars_GetKPIArea(accessToken, monthID);
  }
  
  public java.lang.String circulars_GetKPIProvince(java.lang.String accessToken, java.lang.String monthID) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.circulars_GetKPIProvince(accessToken, monthID);
  }
  
  public java.lang.String circulars_GetKPIRegion(java.lang.String accessToken, java.lang.String monthID) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.circulars_GetKPIRegion(accessToken, monthID);
  }
  
  public java.lang.String circulars_GetKPIAll(java.lang.String accessToken, java.lang.String monthID) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.circulars_GetKPIAll(accessToken, monthID);
  }
  
  public java.lang.String getTelDirectory(java.lang.String accessCode, java.lang.String name, java.lang.String telNo, java.lang.String designation, java.lang.String branch) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.getTelDirectory(accessCode, name, telNo, designation, branch);
  }
  
  public java.lang.String sendMail(java.lang.String accessToken, java.lang.String toAddresses, java.lang.String subject, java.lang.String body, java.lang.String msgFrom) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.sendMail(accessToken, toAddresses, subject, body, msgFrom);
  }
  
  public java.lang.String sendSMS(java.lang.String accessCode, java.lang.String toNumber, java.lang.String body) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.sendSMS(accessCode, toNumber, body);
  }
  
  public java.lang.String genStat_GetPeakReadings(java.util.Calendar readDate) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.genStat_GetPeakReadings(readDate);
  }
  
  public java.lang.String genStat_GetEnergyGenerated(java.util.Calendar readDate) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.genStat_GetEnergyGenerated(readDate);
  }
  
  public java.lang.String genStat_GetReserviorStorage(java.util.Calendar readDate) throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.genStat_GetReserviorStorage(readDate);
  }
  
  public java.lang.String genStat_GetRealtimeDeamd() throws java.rmi.RemoteException{
    if (mobileServiceSoap == null)
      _initMobileServiceSoapProxy();
    return mobileServiceSoap.genStat_GetRealtimeDeamd();
  }
  
  
}