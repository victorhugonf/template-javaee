package io.github.victorhugonf.javaee.ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.github.victorhugonf.javaee.ejb.utils.CONSTANTS;
import io.github.victorhugonf.javaee.ejb.utils.exceptions.UtilException;

@Entity
@Table(name = CONSTANTS.DATA_BASE.TABLES.LOG_ERRORS.NAME)
@SequenceGenerator(name = CONSTANTS.DATA_BASE.TABLES.LOG_ERRORS.SEQUENCE,
					sequenceName = CONSTANTS.DATA_BASE.TABLES.LOG_ERRORS.SEQUENCE,
					allocationSize = 1)
public class LogError implements Serializable, ValueObject{
    
	private static final long serialVersionUID = -1253994792523393811L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = CONSTANTS.DATA_BASE.TABLES.LOG_ERRORS.SEQUENCE)
    private long id;
    
    @Column(nullable = false, columnDefinition="text")
    private String message;
    
    @Column(name = CONSTANTS.DATA_BASE.TABLES.LOG_ERRORS.COLUMNS.STACK_TRACE, nullable = false, columnDefinition="text")
    private String stackTrace;
    
    @Column(nullable = false)
    private Date date;
    
    public LogError(Exception e){
    	message = getMessage(e);
		stackTrace = getStackTrace(e);
		date = new Date();
    }
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}
	
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public void validate() throws Exception {
    	
	}
    
    @Override
    public String toString(){
        return getId() + ";" + getMessage() + ";" + getStackTrace() + ";" + getDate() + ";";
    }

    private String getMessage(Exception e) {
		if(e.getMessage() != null){
			return e.getMessage();
		}else{
			return new String();
		}
	}

	private String getStackTrace(Exception e) {
		String stackTrace = new String();
		if(e instanceof UtilException){
			if(e.getStackTrace().length > 0){
				stackTrace = e.getStackTrace()[0].toString();
			}
		}else{
			for (StackTraceElement item: e.getStackTrace()) {
				stackTrace += item.toString() + "\n";
			}
		}
		return stackTrace;
	}
}
