package io.github.victorhugonf.javaee.ejb.entity;

import java.io.Serializable;

public interface EntityIdentifiable extends Serializable {

	long getId();

	void setId(long id);

}
