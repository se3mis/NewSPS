<%@ taglib prefix="s" uri="/struts-tags"%>

<s:select name="chequeBranchCode" id="txfdChequeBranchCode" headerKey=""  headerValue="Select Bank Branch" list="bankBranchList"  listKey="id.branchCode" listValue="%{id.branchCode+' - '+branchName}" />

