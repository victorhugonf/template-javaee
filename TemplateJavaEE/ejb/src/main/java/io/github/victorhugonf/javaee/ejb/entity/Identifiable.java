package io.github.victorhugonf.javaee.ejb.entity;

public interface Identifiable  {
	
	long getId();

	void setId(long id);
	
	void validate() throws Exception;

}
