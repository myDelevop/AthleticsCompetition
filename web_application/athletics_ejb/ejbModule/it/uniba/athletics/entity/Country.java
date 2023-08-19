package it.uniba.athletics.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import it.uniba.athletics.common.dto.AuthUserDTO;
import it.uniba.athletics.common.dto.CountryDTO;
import it.uniba.athletics.common.exception.BackEndException;
import it.uniba.athletics.util.Constants;

/**
 * The persistent class for the COUNTRY database table.
 * 
 */
@Entity
@Table(name="COUNTRY")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	static final Logger LOGGER = Logger.getLogger(Country.class);

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idCountry;

	
	private Blob flag;
	
	private String name;

	private String review;

	private Integer numGoldMedals;

	private Integer numSilverMedals;
	
	private Integer numBronzeMedals;

	

	public Country() {}

	
	public CountryDTO convertToDTO() throws BackEndException {
		LOGGER.info("converting Country entity in DTO");

		return new ModelMapper().map(this, CountryDTO.class);
	}


	public long getIdCountry() {
		return idCountry;
	}


	public void setIdCountry(long idCountry) {
		this.idCountry = idCountry;
	}


	public Blob getFlag() {
		return flag;
	}


	public void setFlag(Blob flag) {
		this.flag = flag;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public Integer getNumGoldMedals() {
		return numGoldMedals;
	}


	public void setNumGoldMedals(Integer numGoldMedals) {
		this.numGoldMedals = numGoldMedals;
	}


	public Integer getNumSilverMedals() {
		return numSilverMedals;
	}


	public void setNumSilverMedals(Integer numSilverMedals) {
		this.numSilverMedals = numSilverMedals;
	}


	public Integer getNumBronzeMedals() {
		return numBronzeMedals;
	}


	public void setNumBronzeMedals(Integer numBronzeMedals) {
		this.numBronzeMedals = numBronzeMedals;
	}


	@Override
	public String toString() {
		return "\nENTITY\nidCountry -> " + this.idCountry + "\n"
			/*.concat("flag -> " + this.flag + "\n")*/
			.concat("name -> " + this.name + "\n")
			.concat("review -> " + this.review + "\n")
			.concat("numGoldMedals -> " + this.numGoldMedals + "\n")
			.concat("numSilverMedals -> " + this.numSilverMedals + "\n")
			.concat("numBronzeMedals -> " + this.numBronzeMedals);
	}
	
	
	
	

}