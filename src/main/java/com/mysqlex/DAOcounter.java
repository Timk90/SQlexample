package com.mysqlex;

public class DAOcounter {
    int id;
    int count;
    String regionName;

    public DAOcounter(int id, String regionName, int count){
        this.count = count;
        this.id = id;
        this.regionName = regionName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(" ID: "+this.id);
        sb.append(" Регион : "+this.regionName);
        sb.append(" Количество городов: "+this.count);

        return sb.toString();
    }
}
