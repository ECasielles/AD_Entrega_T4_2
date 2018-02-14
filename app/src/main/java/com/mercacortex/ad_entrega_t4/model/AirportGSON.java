package com.mercacortex.ad_entrega_t4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AirportGSON {

    @SerializedName("")
    @Expose
    private ArrayList<Airport> airports;

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }

    public class Airport {

        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("iata")
        @Expose
        private String iata;
        @SerializedName("icao")
        @Expose
        private String icao;
        @SerializedName("major")
        @Expose
        private Boolean major;
        @SerializedName("scenery_fs9_link")
        @Expose
        private String sceneryFs9Link;
        @SerializedName("scenery_fsx_link")
        @Expose
        private String sceneryFsxLink;
        @SerializedName("scenery_xp_link")
        @Expose
        private String sceneryXpLink;
        @SerializedName("country")
        @Expose
        private Country country;
        @SerializedName("data")
        @Expose
        private Data data;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIata() {
            return iata;
        }

        public void setIata(String iata) {
            this.iata = iata;
        }

        public String getIcao() {
            return icao;
        }

        public void setIcao(String icao) {
            this.icao = icao;
        }

        public Boolean getMajor() {
            return major;
        }

        public void setMajor(Boolean major) {
            this.major = major;
        }

        public String getSceneryFs9Link() {
            return sceneryFs9Link;
        }

        public void setSceneryFs9Link(String sceneryFs9Link) {
            this.sceneryFs9Link = sceneryFs9Link;
        }

        public String getSceneryFsxLink() {
            return sceneryFsxLink;
        }

        public void setSceneryFsxLink(String sceneryFsxLink) {
            this.sceneryFsxLink = sceneryFsxLink;
        }

        public String getSceneryXpLink() {
            return sceneryXpLink;
        }

        public void setSceneryXpLink(String sceneryXpLink) {
            this.sceneryXpLink = sceneryXpLink;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        @Override
        public String toString() {
            String code = "code";
            String iata = "iata";
            String name = "name";
            if (getCountry() != null)
                code = getCountry().getCode();
            if (getIata() != null)
                iata = getIata();
            if (getData() != null)
                name = getData().getName();

            return String.format(Locale.getDefault(),
                    "%s:%s: %s",
                    code, iata, name
            );
        }
    }

    class Country {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("name")
        @Expose
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    class Data {

        @SerializedName("elevation")
        @Expose
        private Integer elevation;
        @SerializedName("icao")
        @Expose
        private String icao;
        @SerializedName("lat")
        @Expose
        private Float lat;
        @SerializedName("lon")
        @Expose
        private Float lon;
        @SerializedName("msa")
        @Expose
        private Integer msa;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("ta")
        @Expose
        private Integer ta;
        @SerializedName("runways")
        @Expose
        private List<Runway> runways = null;

        public Integer getElevation() {
            return elevation;
        }

        public void setElevation(Integer elevation) {
            this.elevation = elevation;
        }

        public String getIcao() {
            return icao;
        }

        public void setIcao(String icao) {
            this.icao = icao;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLon() {
            return lon;
        }

        public void setLon(Float lon) {
            this.lon = lon;
        }

        public Integer getMsa() {
            return msa;
        }

        public void setMsa(Integer msa) {
            this.msa = msa;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTa() {
            return ta;
        }

        public void setTa(Integer ta) {
            this.ta = ta;
        }

        public List<Runway> getRunways() {
            return runways;
        }

        public void setRunways(List<Runway> runways) {
            this.runways = runways;
        }

    }

    class Runway {

        @SerializedName("course")
        @Expose
        private Integer course;
        @SerializedName("elevation")
        @Expose
        private Integer elevation;
        @SerializedName("glidepath")
        @Expose
        private Float glidepath;
        @SerializedName("ils")
        @Expose
        private Boolean ils;
        @SerializedName("ils_fac")
        @Expose
        private Integer ilsFac;
        @SerializedName("ils_freq")
        @Expose
        private Float ilsFreq;
        @SerializedName("lat")
        @Expose
        private Float lat;
        @SerializedName("length")
        @Expose
        private Integer length;
        @SerializedName("lon")
        @Expose
        private Float lon;
        @SerializedName("number")
        @Expose
        private String number;

        public Integer getCourse() {
            return course;
        }

        public void setCourse(Integer course) {
            this.course = course;
        }

        public Integer getElevation() {
            return elevation;
        }

        public void setElevation(Integer elevation) {
            this.elevation = elevation;
        }

        public Float getGlidepath() {
            return glidepath;
        }

        public void setGlidepath(Float glidepath) {
            this.glidepath = glidepath;
        }

        public Boolean getIls() {
            return ils;
        }

        public void setIls(Boolean ils) {
            this.ils = ils;
        }

        public Integer getIlsFac() {
            return ilsFac;
        }

        public void setIlsFac(Integer ilsFac) {
            this.ilsFac = ilsFac;
        }

        public Float getIlsFreq() {
            return ilsFreq;
        }

        public void setIlsFreq(Float ilsFreq) {
            this.ilsFreq = ilsFreq;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Float getLon() {
            return lon;
        }

        public void setLon(Float lon) {
            this.lon = lon;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

    }
}

