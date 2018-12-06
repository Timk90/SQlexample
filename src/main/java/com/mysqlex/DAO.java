package com.mysqlex;

public class DAO {

    String city_name;
    String region_name;
    int naselenie;
    long id;
    long region_id;

    public DAO(){}

    public DAO(long id, String city_name, String region_name, long region_id, int naselenie){
        this.city_name = city_name;
        this.region_name = region_name;
        this.naselenie = naselenie;
        this.id = id;
        this.region_id = region_id;
    };

    public DAO(long id, String city_name, String region_name, long region_id){
        this.city_name = city_name;
        this.region_name = region_name;
        this.id = id;
        this.region_id = region_id;
    };

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public int getNaselenie() {
        return naselenie;
    }

    public void setNaselenie(int naselenie) {
        this.naselenie = naselenie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRegion_id() {
        return region_id;
    }

    public void setRegion_id(long region_id) {
        this.region_id = region_id;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(" ID: "+this.id);
        sb.append(" City name: "+this.city_name);
        sb.append(" Region ID: "+this.region_id);
        sb.append(" Region name: "+this.region_name);
        sb.append(" Naselenie: "+this.naselenie);
        return sb.toString();
    }
}
