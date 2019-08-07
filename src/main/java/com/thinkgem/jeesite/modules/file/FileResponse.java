package com.thinkgem.jeesite.modules.file;

public class FileResponse {
	  private String name;
	    private String message;
	    private String url;
	    private String status;
	    public void setName(String name) {
	         this.name = name;
	     }
	     public String getName() {
	         return name;
	     }

	    public void setMessage(String message) {
	         this.message = message;
	     }
	     public String getMessage() {
	         return message;
	     }

	    public void setUrl(String url) {
	         this.url = url;
	     }
	     public String getUrl() {
	         return url;
	     }

	    public void setStatus(String status) {
	         this.status = status;
	     }
	     public String getStatus() {
	         return status;
	     }
}
