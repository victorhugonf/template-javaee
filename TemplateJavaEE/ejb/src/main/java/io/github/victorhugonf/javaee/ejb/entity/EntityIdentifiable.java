package io.github.victorhugonf.javaee.ejb.entity;

public interface EntityIdentifiable  {
	
	long getId();

	void setId(long id);
	
	void validate() throws Exception;

}
