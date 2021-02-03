package com.thekidd.naturalwonder.CharacterSheets;

import java.util.ArrayList;
import java.util.HashMap;

public class Magic extends Item {

    private ArrayList<String> desc;
    private String castTime;
    private ArrayList<String> spellClass;
    private ArrayList<String> components;
    private boolean concentration;
    private String range;
    private boolean ritual;
    private String school;
    private String duration;
    private int level;
    private String material;
    private HashMap<Integer, String> healAtSlotLevel;
    private String attackType;
    private String aoeType;
    private int aoEsize;
    private String damageType;
    private String dcDesc;
    private String dcType;
    private String dcSucces;
    private ArrayList<String> higherLevels;


    public Magic(String name, String Type, ArrayList<String> desc, String castTime, ArrayList<String> spellClass, ArrayList<String> components, boolean concentration, String range, boolean ritual, String school, String duration, int level, String material, String attackType, String aoeType, int aoEsize, String damageType, String dcDesc, String dcType, String dcSucces, ArrayList<String> higherLevels, HashMap<Integer, String> healAtSlotLevel) {
        super(name, Type);
        this.desc = desc;
        this.castTime = castTime;
        this.spellClass = spellClass;
        this.components = components;
        this.concentration = concentration;
        this.range = range;
        this.ritual = ritual;
        this.school = school;
        this.duration = duration;
        this.level = level;
        this.material = material;
        this.healAtSlotLevel = healAtSlotLevel;
        this.attackType = attackType;
        this.aoEsize = aoEsize;
        this.aoeType = aoeType;
        this.damageType = damageType;
        this.dcDesc = dcDesc;
        this.dcType = dcType;
        this.dcSucces = dcSucces;
        this.higherLevels = higherLevels;
    }

    public ArrayList<String> getDesc() {
        return desc;
    }

    public void setDesc(ArrayList<String> desc) {
        this.desc = desc;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public ArrayList<String> getSpellClass() {
        return spellClass;
    }

    public void setSpellClass(ArrayList<String> spellClass) {
        this.spellClass = spellClass;
    }

    public ArrayList<String> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<String> components) {
        this.components = components;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public boolean isRitual() {
        return ritual;
    }

    public void setRitual(boolean ritual) {
        this.ritual = ritual;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public HashMap<Integer, String> getHealAtSlotLevel() {
        return healAtSlotLevel;
    }

    public void setHealAtSlotLevel(HashMap<Integer, String> healAtSlotLevel) {
        this.healAtSlotLevel = healAtSlotLevel;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getAoeType() {
        return aoeType;
    }

    public void setAoeType(String aoeType) {
        this.aoeType = aoeType;
    }

    public int getAoEsize() {
        return aoEsize;
    }

    public void setAoEsize(int aoEsize) {
        this.aoEsize = aoEsize;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDcDesc() {
        return dcDesc;
    }

    public void setDcDesc(String dcDesc) {
        this.dcDesc = dcDesc;
    }

    public String getDcType() {
        return dcType;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    public String getDcSucces() {
        return dcSucces;
    }

    public void setDcSucces(String dcSucces) {
        this.dcSucces = dcSucces;
    }

    public ArrayList<String> getHigherLevels() {
        return higherLevels;
    }

    public void setHigherLevels(ArrayList<String> higherLevels) {
        this.higherLevels = higherLevels;
    }
}
