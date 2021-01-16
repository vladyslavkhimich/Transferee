package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerOverviewPOJO {

    @SerializedName("Height")
    @Expose
    private Integer height;
    @SerializedName("Age")
    @Expose
    private Integer age;
    @SerializedName("Birth_Date")
    @Expose
    private String birthDate;
    @SerializedName("Shirt_Number")
    @Expose
    private Integer shirtNumber;
    @SerializedName("Preferred_Foot")
    @Expose
    private Boolean preferredFoot;
    @SerializedName("Country_URL")
    @Expose
    private String countryURL;
    @SerializedName("Pseudonym")
    @Expose
    private String pseudonym;
    @SerializedName("Primary")
    @Expose
    private String primary;
    @SerializedName("Others")
    @Expose
    private List<String> others = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public PlayerOverviewPOJO() {
    }

    /**
     *
     * @param countryURL
     * @param shirtNumber
     * @param preferredFoot
     * @param birthDate
     * @param pseudonym
     * @param age
     * @param others
     * @param height
     * @param primary
     */
    public PlayerOverviewPOJO(Integer height, Integer age, String birthDate, Integer shirtNumber, Boolean preferredFoot, String countryURL, String pseudonym, String primary, List<String> others) {
        super();
        this.height = height;
        this.age = age;
        this.birthDate = birthDate;
        this.shirtNumber = shirtNumber;
        this.preferredFoot = preferredFoot;
        this.countryURL = countryURL;
        this.pseudonym = pseudonym;
        this.primary = primary;
        this.others = others;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(Integer shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public Boolean getPreferredFoot() {
        return preferredFoot;
    }

    public void setPreferredFoot(Boolean preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public String getCountryURL() {
        return countryURL;
    }

    public void setCountryURL(String countryURL) {
        this.countryURL = countryURL;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

}
