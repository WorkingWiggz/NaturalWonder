package com.thekidd.naturalwonder.CharacterSheets;

import java.util.ArrayList;

public class EquimentPiece extends Item {
    private String armorCat;
    private String capacity;
    private String categoryRange;
    private String costUnit;
    private String damageDice;
    private String damageType;
    private String speedUnit;
    private String toolCat;
    private String twoHandedDamageDice;
    private String getTwoHandedDamageType;
    private String vehCat;
    private String weaponCategory;
    private String weaponRange;
    private int acBase, acMaxBonus, costQuantity, quantity, rangeShort, rangeLong, speedQuantity, strMinimum, thrownShort, thrownLong, weight;
    private boolean acDexBonus, stealthDisadvantage;
    private ArrayList<String> desc, properties, special;

    public EquimentPiece(String Title, String Type, String armorCat, int acBase, boolean acDexBonus,
                         int acMaxBonus, String capacity, String categoryRange, String costUnit,
                         int costQuantity, String damageDice, String damageType, ArrayList<String> desc, ArrayList<String> properties,
                         int quantity, int rangeShort, int rangeLong, ArrayList<String> special, String speedUnit, int speedQuantity,
                         boolean stealthDisadvantage, int strMinimum, int thrownShort, int thrownLong, String toolCat,
                         String twoHandedDamageDice, String twoHandedDamageType, String vehCat, String weaponCategory,
                         String weaponRange, int weight) {
        super(Title, Type);
        this.armorCat = armorCat;
        this.acBase = acBase;
        this.acDexBonus = acDexBonus;
        this.capacity = capacity;
        this.categoryRange = categoryRange;
        this.costUnit = costUnit;
        this.damageDice = damageDice;
        this.damageType = damageType;
        this.speedUnit = speedUnit;
        this.toolCat = toolCat;
        this.twoHandedDamageDice = twoHandedDamageDice;
        this.getTwoHandedDamageType = twoHandedDamageType;
        this.vehCat = vehCat;
        this.weaponCategory = weaponCategory;
        this.weaponRange = weaponRange;
        this.weight = weight;
        this.quantity = quantity;
        this.acMaxBonus = acMaxBonus;
        this.costQuantity = costQuantity;
        this.desc = desc;
        this.thrownLong = thrownLong;
        this.properties = properties;
        this.strMinimum = strMinimum;
        this.thrownShort = thrownShort;
        this.speedQuantity = speedQuantity;
        this.stealthDisadvantage = stealthDisadvantage;
        this.rangeShort = rangeShort;
        this.rangeLong = rangeLong;
        this.special = special;

    }

    public String getArmorCat() {
        return armorCat;
    }

    public void setArmorCat(String armorCat) {
        this.armorCat = armorCat;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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

    public String getSpeedUnit() {
        return speedUnit;
    }

    public void setSpeedUnit(String speedUnit) {
        this.speedUnit = speedUnit;
    }

    public String getToolCat() {
        return toolCat;
    }

    public void setToolCat(String toolCat) {
        this.toolCat = toolCat;
    }

    public String getTwoHandedDamageDice() {
        return twoHandedDamageDice;
    }

    public void setTwoHandedDamageDice(String twoHandedDamageDice) {
        this.twoHandedDamageDice = twoHandedDamageDice;
    }

    public String getGetTwoHandedDamageType() {
        return getTwoHandedDamageType;
    }

    public void setGetTwoHandedDamageType(String getTwoHandedDamageType) {
        this.getTwoHandedDamageType = getTwoHandedDamageType;
    }

    public String getVehCat() {
        return vehCat;
    }

    public void setVehCat(String vehCat) {
        this.vehCat = vehCat;
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

    public int getAcBase() {
        return acBase;
    }

    public void setAcBase(int acBase) {
        this.acBase = acBase;
    }

    public int getAcMaxBonus() {
        return acMaxBonus;
    }

    public void setAcMaxBonus(int acMaxBonus) {
        this.acMaxBonus = acMaxBonus;
    }

    public int getCostQuantity() {
        return costQuantity;
    }

    public void setCostQuantity(int costQuantity) {
        this.costQuantity = costQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getSpeedQuantity() {
        return speedQuantity;
    }

    public void setSpeedQuantity(int speedQuantity) {
        this.speedQuantity = speedQuantity;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isAcDexBonus() {
        return acDexBonus;
    }

    public void setAcDexBonus(boolean acDexBonus) {
        this.acDexBonus = acDexBonus;
    }

    public boolean isStealthDisadvantage() {
        return stealthDisadvantage;
    }

    public void setStealthDisadvantage(boolean stealthDisadvantage) {
        this.stealthDisadvantage = stealthDisadvantage;
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

    public ArrayList<String> getSpecial() {
        return special;
    }

    public void setSpecial(ArrayList<String> special) {
        this.special = special;
    }
}