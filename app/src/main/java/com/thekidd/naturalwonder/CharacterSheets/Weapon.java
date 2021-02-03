package com.thekidd.naturalwonder.CharacterSheets;

import java.util.ArrayList;

public class Weapon extends Item {
    private String categoryRange;
    private String costUnit;
    private int costQuantity;
    private String damageDice;
    private String damageType;
    private ArrayList<String> desc;
    private ArrayList<String> properties;
    private int rangeShort;
    private int rangeLong;
    private ArrayList<String> special;
    private String speedUnit;
    private int speedQuantity;
    private boolean stealthDisadvantage;
    private int strMinimum;
    private int thrownShort;
    private int thrownLong;
    private String twoHandedDamageDice;
    private String twoHandedDamageType;
    private String weaponCategory;
    private String weaponRange;
    private int weight;

    Weapon(String Title, String Type, String categoryRange, String costUnit, int costQuantity, String damageDice,
           String damageType, ArrayList<String> desc, ArrayList<String> properties, int rangeShort,
           int rangeLong, ArrayList<String> special, String speedUnit, int speedQuantity, boolean stealthDisadvantage,
           int strMinimum, int thrownShort, int thrownLong, String twoHandedDamageDice, String twoHandedDamageType,
           String weaponCategory, String weaponRange, int weight) {
        super(Title, Type);
        this.categoryRange = categoryRange;
        this.costUnit = costUnit;
        this.costQuantity = costQuantity;
        this.damageDice = damageDice;
        this.damageType = damageType;
        this.desc = desc;
        this.properties = properties;
        this.rangeShort = rangeShort;
        this.rangeLong = rangeLong;
        this.special = special;
        this.speedUnit = speedUnit;
        this.speedQuantity = speedQuantity;
        this.stealthDisadvantage = stealthDisadvantage;
        this.strMinimum = strMinimum;
        this.thrownShort = thrownShort;
        this.thrownLong = thrownLong;
        this.twoHandedDamageDice = twoHandedDamageDice;
        this.twoHandedDamageType = twoHandedDamageType;
        this.weaponCategory = weaponCategory;
        this.weaponRange = weaponRange;
        this.weight = weight;

    }

    public String getCategoryRange() {
        return categoryRange;
    }

    public void setCategoryRange(String categoryRange) {
        this.categoryRange = categoryRange;
    }

    public String getCostUnit() {
        return costUnit;
    }

    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }

    public int getCostQuantity() {
        return costQuantity;
    }

    public void setCostQuantity(int costQuantity) {
        this.costQuantity = costQuantity;
    }

    public String getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public ArrayList<String> getDesc() {
        return desc;
    }

    public void setDesc(ArrayList<String> desc) {
        this.desc = desc;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }

    public int getRangeShort() {
        return rangeShort;
    }

    public void setRangeShort(int rangeShort) {
        this.rangeShort = rangeShort;
    }

    public int getRangeLong() {
        return rangeLong;
    }

    public void setRangeLong(int rangeLong) {
        this.rangeLong = rangeLong;
    }

    public ArrayList<String> getSpecial() {
        return special;
    }

    public void setSpecial(ArrayList<String> special) {
        this.special = special;
    }

    public String getSpeedUnit() {
        return speedUnit;
    }

    public void setSpeedUnit(String speedUnit) {
        this.speedUnit = speedUnit;
    }

    public int getSpeedQuantity() {
        return speedQuantity;
    }

    public void setSpeedQuantity(int speedQuantity) {
        this.speedQuantity = speedQuantity;
    }

    public boolean isStealthDisadvantage() {
        return stealthDisadvantage;
    }

    public void setStealthDisadvantage(boolean stealthDisadvantage) {
        this.stealthDisadvantage = stealthDisadvantage;
    }

    public int getStrMinimum() {
        return strMinimum;
    }

    public void setStrMinimum(int strMinimum) {
        this.strMinimum = strMinimum;
    }

    public int getThrownShort() {
        return thrownShort;
    }

    public void setThrownShort(int thrownShort) {
        this.thrownShort = thrownShort;
    }

    public int getThrownLong() {
        return thrownLong;
    }

    public void setThrownLong(int thrownLong) {
        this.thrownLong = thrownLong;
    }

    public String getTwoHandedDamageDice() {
        return twoHandedDamageDice;
    }

    public void setTwoHandedDamageDice(String twoHandedDamageDice) {
        this.twoHandedDamageDice = twoHandedDamageDice;
    }

    public String getTwoHandedDamageType() {
        return twoHandedDamageType;
    }

    public void setTwoHandedDamageType(String twoHandedDamageType) {
        this.twoHandedDamageType = twoHandedDamageType;
    }

    public String getWeaponCategory() {
        return weaponCategory;
    }

    public void setWeaponCategory(String weaponCategory) {
        this.weaponCategory = weaponCategory;
    }

    public String getWeaponRange() {
        return weaponRange;
    }

    public void setWeaponRange(String weaponRange) {
        this.weaponRange = weaponRange;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}