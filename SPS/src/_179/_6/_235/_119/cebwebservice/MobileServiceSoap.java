/**
 * MobileServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _179._6._235._119.cebwebservice;

public interface MobileServiceSoap extends java.rmi.Remote {

    /**
     * Create and return the time stamped token to access the web
     * service
     */
    public java.lang.String getAccessToken(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Get the customer location in Sinhala Language for the Account
     * Number
     */
    public java.lang.String CC_GetLocationSinhala(java.lang.String accountNumber) throws java.rmi.RemoteException;

    /**
     * Get all active call center details
     */
    public java.lang.String CC_GetCallCenters(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Get all pending jobs for the call center
     */
    public java.lang.String CC_GetPendingJobs(java.lang.String accessToken, java.lang.String CCCode) throws java.rmi.RemoteException;

    /**
     * Get server time
     */
    public java.lang.String CC_GetServerTime(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Get area name
     */
    public java.lang.String CC_GetAreaName(java.lang.String accessToken, java.lang.String areaCode) throws java.rmi.RemoteException;

    /**
     * Get prov code by Call Center
     */
    public java.lang.String CC_GetProvCodebyCallCentre(java.lang.String accessToken, java.lang.String CCCode) throws java.rmi.RemoteException;

    /**
     * Get prov information
     */
    public java.lang.String CC_GetProvinceInfo(java.lang.String accessToken, java.lang.String CCCode) throws java.rmi.RemoteException;

    /**
     * Get completed job information
     */
    public java.lang.String CC_GetCompletedJobs(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar completedDate) throws java.rmi.RemoteException;

    /**
     * Get call center staff
     */
    public java.lang.String CC_GetCallCenterStaff(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar date) throws java.rmi.RemoteException;

    /**
     * Get field staff
     */
    public java.lang.String CC_GetFieldStaff(java.lang.String accessToken, java.lang.String CCCode, java.lang.String provCode, java.util.Calendar date) throws java.rmi.RemoteException;

    /**
     * Get current jobs in a range
     */
    public java.lang.String CC_GetCurrentJobsInRange(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar startDate, java.util.Calendar endDate) throws java.rmi.RemoteException;

    /**
     * Get failure types
     */
    public java.lang.String CC_GetFailureTypes(java.lang.String accessToken, java.lang.String CCCode) throws java.rmi.RemoteException;

    /**
     * Get failure count
     */
    public java.lang.String CC_GetFailureCount(java.lang.String accessToken, java.lang.String CCCode, java.lang.String failureCode, java.util.Calendar yearMonth) throws java.rmi.RemoteException;

    /**
     * Get masters failure analysis
     */
    public java.lang.String CC_GetMastersFailureAnalysis(java.lang.String masterCode, java.lang.String accessCode) throws java.rmi.RemoteException;

    /**
     * Get completed jobs in month
     */
    public java.lang.String CC_GetCompletedJobsInMonth(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar yearMonth) throws java.rmi.RemoteException;

    /**
     * Get assigned jobs
     */
    public java.lang.String CC_GetAssignedJobs(java.lang.String accessToken, java.lang.String CCCode, java.lang.String areaCode, java.util.Calendar yearMonth) throws java.rmi.RemoteException;

    /**
     * Get complaints summary
     */
    public java.lang.String CC_GetComplaints(java.lang.String accessToken, java.lang.String CCCode, java.lang.String areaCode, _179._6._235._119.cebwebservice.ComplaintRetrevalStatus status, java.util.Calendar yearMonth) throws java.rmi.RemoteException;

    /**
     * Get field staff information
     */
    public java.lang.String CC_GetFieldStaffInfo(java.lang.String accessToken, java.lang.String CCCode, java.util.Calendar startDate, java.util.Calendar endDate, java.lang.String unit, java.lang.String provCode) throws java.rmi.RemoteException;

    /**
     * Get all breakdown jobs
     */
    public java.lang.String CC_GetAllBreakdownJobs(java.lang.String accessToken, java.lang.String CCCode, java.lang.String provCode, java.util.Calendar startYearMonth, java.util.Calendar endYearMonth) throws java.rmi.RemoteException;

    /**
     * Get all temp completed breakdown jobs
     */
    public java.lang.String CC_GetAllTemporaryCompletedJobs(java.lang.String accessToken, java.lang.String CCCode, java.lang.String provCode, java.util.Calendar startYearMonth, java.util.Calendar endYearMonth) throws java.rmi.RemoteException;

    /**
     * Get total count of consumers per area
     */
    public java.lang.String CC_GetNoOfConsumersPerArea(java.lang.String accessToken, java.lang.String areaCode) throws java.rmi.RemoteException;

    /**
     * Get all area information
     */
    public java.lang.String billing_GetAreaCodes(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Get daily pack for the reader
     */
    public java.lang.String billing_GetDailyPack(java.lang.String accessToken, java.lang.String areaCode, java.lang.String readerCode, java.lang.String dailyPack) throws java.rmi.RemoteException;

    /**
     * Check mobile user
     */
    public boolean CC_CheckMobileUser(java.lang.String accessToken, java.lang.String accessCode, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Load information for the Job Tickets
     */
    public java.lang.String CC_GetJobTickets(java.lang.String accessToken, java.lang.String accessCode) throws java.rmi.RemoteException;

    /**
     * Load number of jobs for print
     */
    public int CC_GetPrintCount(java.lang.String accessToken, java.lang.String accessCode) throws java.rmi.RemoteException;

    /**
     * Load information of the mobile user
     */
    public java.lang.String CC_GetMobileUserInfo(java.lang.String accessToken, java.lang.String accessCode) throws java.rmi.RemoteException;

    /**
     * Get map key
     */
    public java.lang.String CC_GetMapKey(java.lang.String accessToken, java.lang.String mapType) throws java.rmi.RemoteException;

    /**
     * Load land marks information
     */
    public java.lang.String CC_GetLandMarks(java.lang.String accessToken, java.lang.String accountNo) throws java.rmi.RemoteException;

    /**
     * Load location of the customer
     */
    public java.lang.String CC_GetCustomerLocation(java.lang.String accessToken, java.lang.String accountNo) throws java.rmi.RemoteException;

    /**
     * Set mobile login access
     */
    public boolean CC_SetMobileLoginAccessState(java.lang.String accessToken, java.lang.String accessCode, java.lang.String deviceID, java.lang.String CCCode, java.lang.String activity) throws java.rmi.RemoteException;

    /**
     * Complete Job Ticket Print Status
     */
    public boolean CC_CompleteJobTicketPrintStatus(java.lang.String accessToken, java.lang.String jobNumber) throws java.rmi.RemoteException;

    /**
     * Load breakdown job information
     */
    public java.lang.String CC_GetBreakdownJobs(java.lang.String accessToken, java.lang.String accessCode) throws java.rmi.RemoteException;

    /**
     * Update job status
     */
    public boolean CC_UpdateJobStatus(java.lang.String accessToken, java.lang.String jobNo, java.lang.String STCode, java.lang.String comment) throws java.rmi.RemoteException;

    /**
     * Completion of Jobs
     */
    public void CC_JobComplete(java.lang.String accessToken, java.lang.String accessCode, java.lang.String jobNo, java.lang.String failureType, java.lang.String cause, java.lang.String details, java.lang.String action, java.lang.String comment, java.lang.String STCode, javax.xml.rpc.holders.BooleanHolder CC_JobCompleteResult, javax.xml.rpc.holders.StringHolder errMsg) throws java.rmi.RemoteException;

    /**
     * Get Employee Details
     */
    public java.lang.String HR_GetEmployeeDetails(java.lang.String accessToken, java.lang.String EPFNo) throws java.rmi.RemoteException;

    /**
     * Get Employee Hierarchy 3
     */
    public java.lang.String HR_GetEmployeeHierarchy3(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Get Employee Hierarchy 4
     */
    public java.lang.String HR_GetEmployeeHierarchy4(java.lang.String accessToken, java.lang.String hie3) throws java.rmi.RemoteException;

    /**
     * Get Employee Hierarchy 5
     */
    public java.lang.String HR_GetEmployeeHierarchy5(java.lang.String accessToken, java.lang.String hie3, java.lang.String hie4) throws java.rmi.RemoteException;

    /**
     * Get Employee Hierarchy 6
     */
    public java.lang.String HR_GetEmployeeHierarchy6(java.lang.String accessToken, java.lang.String hie3, java.lang.String hie4, java.lang.String hie5) throws java.rmi.RemoteException;

    /**
     * Get Employees
     */
    public java.lang.String HR_GetEmployees(java.lang.String accessToken, java.lang.String hie1, java.lang.String hie2, java.lang.String hie3, java.lang.String hie4, java.lang.String hie5, java.lang.String hie6, java.lang.String dsg, boolean isActive) throws java.rmi.RemoteException;

    /**
     * Get Employees by service cat
     */
    public java.lang.String HR_GetEmployeesByServiceCategory(java.lang.String accessToken, java.lang.String accessName, java.lang.String serviceCategory, java.lang.String subServiceCategory, java.lang.String designation, java.lang.String grade, java.lang.String scale, boolean isActive) throws java.rmi.RemoteException;

    /**
     * EMP breakdown by Designation tree
     */
    public _179._6._235._119.cebwebservice.LEVEL HR_EmpBreakDownByDsgTree(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * EMP breakdown by Designation
     */
    public _179._6._235._119.cebwebservice.LEVEL HR_EmpBreakDownByDsg(java.lang.String accessToken, java.lang.String hie1, java.lang.String hie2, java.lang.String hie3, java.lang.String hie4, java.lang.String hie5, java.lang.String hie6) throws java.rmi.RemoteException;

    /**
     * EMP breakdown by Service
     */
    public _179._6._235._119.cebwebservice.LEVEL HR_EmpBreakDownByService(java.lang.String accessToken, java.lang.String serviceCategory, java.lang.String subserviceCategory) throws java.rmi.RemoteException;

    /**
     * Get Retiring Employees
     */
    public java.lang.String HR_GetRetiringEmployees(java.lang.String accessToken, java.util.Calendar start, java.util.Calendar end, java.lang.String serviceCategory, java.lang.String subServiceCategory) throws java.rmi.RemoteException;

    /**
     * Get Service Categories
     */
    public java.lang.String HR_GetServiceCategories(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Get sub Service Categories
     */
    public java.lang.String HR_GetSubServiceCategories(java.lang.String accessToken, java.lang.String serviceCategory) throws java.rmi.RemoteException;

    /**
     * Get Designation By Service Categories
     */
    public java.lang.String HR_DesignationByServiceCategories(java.lang.String accessToken, java.lang.String serviceCategory, java.lang.String subServiceCategory) throws java.rmi.RemoteException;

    /**
     * Get Grade By Service Categories
     */
    public java.lang.String HR_GradeByServiceCategories(java.lang.String accessToken, java.lang.String serviceCategory, java.lang.String subServiceCategory) throws java.rmi.RemoteException;

    /**
     * Get Scale By Service Categories
     */
    public java.lang.String HR_ScaleByServiceCategories(java.lang.String accessToken, java.lang.String serviceCategory, java.lang.String subServiceCategory) throws java.rmi.RemoteException;

    /**
     * Calculate Bill
     */
    public _179._6._235._119.cebwebservice.Bill billing_CalculateBill(java.lang.String accessToken, int tariff, int units, java.util.Calendar fromDate, java.util.Calendar toDate) throws java.rmi.RemoteException;

    /**
     * Calculate Detail Monthly Bill
     */
    public _179._6._235._119.cebwebservice.Bill billing_CalculateDetailMonthlyBill(java.lang.String accessToken, int tariff, int kwh) throws java.rmi.RemoteException;

    /**
     * Get Tariff Codes
     */
    public _179._6._235._119.cebwebservice.TariffCode[] billing_GetTariffCodes(java.lang.String accessToken) throws java.rmi.RemoteException;

    /**
     * Get Last Printed Bill
     */
    public java.lang.String billing_GetLastPrintedBill(java.lang.String accessToken, java.lang.String accountNumber) throws java.rmi.RemoteException;

    /**
     * Get Payment Transactions
     */
    public java.lang.String billing_GetPaymentTransactions(java.lang.String accessToken, java.lang.String accountNumber) throws java.rmi.RemoteException;

    /**
     * Get Lastest Payments
     */
    public java.lang.String billing_GetLatestPayment(java.lang.String accessToken, java.lang.String accountNumber, java.util.Calendar lastReadingDate) throws java.rmi.RemoteException;
    public java.lang.String circulars_GetCircularList(java.lang.String accessToken, java.lang.String keyWord) throws java.rmi.RemoteException;
    public java.lang.String circulars_GetKPIArea(java.lang.String accessToken, java.lang.String monthID) throws java.rmi.RemoteException;
    public java.lang.String circulars_GetKPIProvince(java.lang.String accessToken, java.lang.String monthID) throws java.rmi.RemoteException;
    public java.lang.String circulars_GetKPIRegion(java.lang.String accessToken, java.lang.String monthID) throws java.rmi.RemoteException;
    public java.lang.String circulars_GetKPIAll(java.lang.String accessToken, java.lang.String monthID) throws java.rmi.RemoteException;
    public java.lang.String getTelDirectory(java.lang.String accessCode, java.lang.String name, java.lang.String telNo, java.lang.String designation, java.lang.String branch) throws java.rmi.RemoteException;

    /**
     * Sends emails to the mail address specified
     */
    public java.lang.String sendMail(java.lang.String accessToken, java.lang.String toAddresses, java.lang.String subject, java.lang.String body, java.lang.String msgFrom) throws java.rmi.RemoteException;

    /**
     * Sends SMS messages to given number
     */
    public java.lang.String sendSMS(java.lang.String accessCode, java.lang.String toNumber, java.lang.String body) throws java.rmi.RemoteException;
    public java.lang.String genStat_GetPeakReadings(java.util.Calendar readDate) throws java.rmi.RemoteException;
    public java.lang.String genStat_GetEnergyGenerated(java.util.Calendar readDate) throws java.rmi.RemoteException;
    public java.lang.String genStat_GetReserviorStorage(java.util.Calendar readDate) throws java.rmi.RemoteException;
    public java.lang.String genStat_GetRealtimeDeamd() throws java.rmi.RemoteException;
}
