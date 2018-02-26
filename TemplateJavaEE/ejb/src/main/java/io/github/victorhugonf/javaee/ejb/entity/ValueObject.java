package io.github.victorhugonf.javaee.ejb.entity;

public interface ValueObject  {
	
	long getId();

	void setId(long id);
	
	void validate() throws Exception;

}
