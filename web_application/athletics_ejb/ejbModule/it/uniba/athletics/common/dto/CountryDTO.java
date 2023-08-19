package it.uniba.athletics.common.dto;

import java.io.Serializable;
import java.sql.Blob;

public class CountryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// private Blob flag;

	private long idCountry;
	
	private String name;

	private String review;

	private Integer numGoldMedals;

	private Integer numSilverMedals;
	
	private Integer numBronzeMedals;
	
	public CountryDTO() {};

	public CountryDTO(long idCountry, Blob flag, String name, String review, Integer numGoldMedals, 
			Integer numSilverMedals, Integer numBronzeMedals) {
		super();
		// this.flag = flag;
		this.name = name;
		this.review = review;
		this.numGoldMedals = numGoldMedals;
		this.numSilverMedals = numSilverMedals;
		this.numBronzeMedals = numBronzeMedals;
	}

	/* public Blob getFlag() {
		return flag;
	}

	public void setFlag(Blob flag) {
		this.flag = flag;
	} */

	
	public String getName() {
		return name;
	}

	public long getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(long idCountry) {
		this.idCountry = idCountry;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public String toString() {
		return "\nDTO\nidCountry -> " + this.idCountry + "\n"
			/*.concat("flag -> " + this.flag + "\n")*/
			.concat("name -> " + this.name + "\n")
			.concat("review -> " + this.review + "\n")
			.concat("numGoldMedals -> " + this.numGoldMedals + "\n")
			.concat("numSilverMedals -> " + this.numSilverMedals + "\n")
			.concat("numBronzeMedals -> " + this.numBronzeMedals);
	}
}
