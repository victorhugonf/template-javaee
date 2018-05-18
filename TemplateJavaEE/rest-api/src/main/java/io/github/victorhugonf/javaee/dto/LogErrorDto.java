package io.github.victorhugonf.javaee.dto;

import java.io.Serializable;

public class LogErrorDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String message;

    public LogErrorDto(){
    	super();
    }

    public LogErrorDto(Exception e){
    	this();

    	message = getMessage(e);
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    @Override
    public String toString(){
        return getMessage() + ";";
    }

    private String getMessage(Exception e) {
		if(e.getMessage() != null){
			return e.getMessage();
		}else{
			return new String();
		}
	}

}
