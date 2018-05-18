package io.github.victorhugonf.javaee.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.github.victorhugonf.javaee.ejb.utils.CONSTANTS;

@Entity
@Table(name = CONSTANTS.DATA_BASE.TABLES.AIRCRAFTS.NAME)
@SequenceGenerator(name = CONSTANTS.DATA_BASE.TABLES.AIRCRAFTS.SEQUENCE,
					sequenceName = CONSTANTS.DATA_BASE.TABLES.AIRCRAFTS.SEQUENCE,
					allocationSize = 1)
public class Aircraft implements EntityIdentifiable{

	private static final long serialVersionUID = 2124815335333460885L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = CONSTANTS.DATA_BASE.TABLES.AIRCRAFTS.SEQUENCE)
    private long id = 0;

	@Version
	private long version;

	@Column(name = CONSTANTS.DATA_BASE.TABLES.AIRCRAFTS.COLUMNS.MODEL, nullable = false, columnDefinition="text")
	@NotEmpty(message = "The aircraft's model must be informed.")
	private String model;

	@ManyToOne
	@JoinColumn(name = CONSTANTS.DATA_BASE.TABLES.AIRCRAFTS.COLUMNS.ID_INDUSTRY)
	@NotNull(message = "The aircraft's industry must be informed.")
	private Industry industry;

	@Override
	public long getId() {
        return id;
    }

	@Override
    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
		return model;
	}

    public void setModel(String model) {
		this.model = model;
	}

    public Industry getIndustry() {
		return industry;
	}

    public void setIndustry(Industry industry) {
		this.industry = industry;
	}

    @Override
    public String toString(){
        return getId() + ";" + getModel() + ";";
    }

}
