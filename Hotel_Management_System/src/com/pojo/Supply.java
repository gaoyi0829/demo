package com.pojo;


public class Supply {
    private int supply_id;
    private String supply_name,supply_inventory,supply_note;

    public Supply(int supply_id, String supply_name, String supply_inventory, String supply_note) {
        super();
        this.supply_id = supply_id;
        this.supply_name = supply_name;
        this.supply_inventory = supply_inventory;
        this.supply_note = supply_note;
    }
    public Supply() {
        super();
    }
    @Override
    public String toString() {
        return "Supply [supply_id=" + supply_id + ", supply_name=" + supply_name + ", supply_inventory="
                + supply_inventory + ", supply_note=" + supply_note + "]";
    }
    public int getSupply_id() {
        return supply_id;
    }
    public void setSupply_id(int supply_id) {
        this.supply_id = supply_id;
    }
    public String getSupply_name() {
        return supply_name;
    }
    public void setSupply_name(String supply_name) {
        this.supply_name = supply_name;
    }
    public String getSupply_inventory() {
        return supply_inventory;
    }
    public void setSupply_inventory(String supply_inventory) {
        this.supply_inventory = supply_inventory;
    }
    public String getSupply_note() {
        return supply_note;
    }
    public void setSupply_note(String supply_note) {
        this.supply_note = supply_note;
    }


}