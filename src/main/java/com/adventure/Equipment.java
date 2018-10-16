package com.adventure;

public class Equipment {
    private String name;
    private int equipmentCount;
    private int defectiveEquipmentCount;

    public Equipment(String name, int equipmentCount, int defectiveEquipmentCount) {
        this.name = name;
        this.equipmentCount = equipmentCount;
        this.defectiveEquipmentCount = defectiveEquipmentCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEquipmentCount() {
        return equipmentCount;
    }

    public void setEquipmentCount(int equipmentCount) {
        this.equipmentCount = equipmentCount;
    }

    public int getDefectiveEquipmentCount() {
        return defectiveEquipmentCount;
    }

    public void setDefectiveEquipmentCount(int defectiveEquipmentCount) {
        this.defectiveEquipmentCount = defectiveEquipmentCount;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", equipmentCount=" + equipmentCount +
                ", defectiveEquipmentCount=" + defectiveEquipmentCount +
                '}';
    }
}