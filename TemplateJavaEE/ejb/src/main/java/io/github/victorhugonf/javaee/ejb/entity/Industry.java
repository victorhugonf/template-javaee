package io.github.victorhugonf.javaee.ejb.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import io.github.victorhugonf.javaee.ejb.utils.CONSTANTS;

@Entity
@Table(name = CONSTANTS.DATA_BASE.TABLES.INDUSTRIES.NAME)
@SequenceGenerator(name = CONSTANTS.DATA_BASE.TABLES.INDUSTRIES.SEQUENCE,
					sequenceName = CONSTANTS.DATA_BASE.TABLES.INDUSTRIES.SEQUENCE,
					allocationSize = 1)
public class Industry implements EntityIdentifiable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = CONSTANTS.DATA_BASE.TABLES.INDUSTRIES.SEQUENCE)
    private long id = 0;

	@Column(name = CONSTANTS.DATA_BASE.TABLES.INDUSTRIES.COLUMNS.NAME, nullable = false, columnDefinition="text")
	private String name;

	@OneToMany(mappedBy = CONSTANTS.DATA_BASE.TABLES.AIRCRAFTS.REFERENCES.INDUSTRY)
	private List<Aircraft> aircrafts;

	@Override
	public long getId() {
        return id;
    }

	@Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

    public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString(){
        return getId() + ";" + getName() + ";";
    }

    @Override
	public void validate() throws Exception {
		//TODO: tratar BusinessException
		if(StringUtils.isBlank(getName())){
			throw new Exception("Please, informe the name.");
        }
	}

}
