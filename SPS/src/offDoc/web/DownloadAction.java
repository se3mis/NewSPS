package offDoc.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;
 
public class DownloadAction extends ActionSupport{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream fileInputStream;
	private String hid_FileName;
 
	public String getHid_FileName() {
		return hid_FileName;
	}

	public void setHid_FileName(String hid_FileName) {
		this.hid_FileName = hid_FileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}
 
	public String execute() throws Exception {
	      return SUCCESS;
	}
	
	public String generate()throws Exception {
		hid_FileName="D:\\downloadfile.txt";
		fileInputStream = new FileInputStream(hid_FileName);
		return SUCCESS;
	}
	
	public String close() throws Exception {
	      return "closed";
	}
}
