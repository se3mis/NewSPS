package security.web;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RedirectAction extends ActionSupport{
	public String execute(){
		return SUCCESS;
	}

}
