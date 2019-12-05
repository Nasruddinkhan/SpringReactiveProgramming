/**
 * nasru
 * CustumException.java
 * Dec 5, 2019
 */
package com.mypractice.springreactive.SpringReactiveProgramming;

/**
 * @author nasru
 *
 */
public class CustumException extends Throwable{
private String errMsg;
	/**
	 * @param e
	 */
	public CustumException(Throwable e) {
		// TODO Auto-generated constructor stub
		this.errMsg = e.getMessage();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	

}
