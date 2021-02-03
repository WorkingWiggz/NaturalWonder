package com.thekidd.naturalwonder.CharacterSheets;

import java.util.ArrayList;
import java.util.Arrays;

public class Level {
    private ArrayList<String> Features = new ArrayList<>();
    private ArrayList<String> ABSMinmum = new ArrayList<>();
    private int ClassSpecificCount = 0;
    private int spellsKnown = 0;
    private int CantripsKnown = 0;
    private ArrayList<Integer> SpellSlots = new ArrayList<>(9);
    private boolean barbmax = false;
    private boolean isBoth = false;
    private int MonkAdditonalMovement = 0;
    private int MonkDice = 4;
    private int SneakAttackDiceNum = 1;
    private int WarlockSlotLevel = 1;
    private int WarlockSlots = 1;
    private int invocationsKnown = 0;
    private int rageDamage = 2;
    private int ABSMin = 0;
    private String Name = "";

    Level(String Name, String[] features, String ABSType, int ABSMin, int ClassSpecific, int RageDamage, boolean barbmax) {
        Features.addAll(Arrays.asList(features));
        ABSMinmum.add(ABSType);
        this.ClassSpecificCount = ClassSpecific;
        this.rageDamage = RageDamage;
        this.barbmax = barbmax;
        this.ABSMin = ABSMin;
        this.Name = Name;
    }

    Level(String Name, String[] features, String ABSType, int ABSMin, int spellsKnown, int CantripKnown, int[] slots) {
        Features.addAll(Arrays.asList(features));
        ABSMinmum.add(ABSType);
        this.spellsKnown = spellsKnown;
        this.CantripsKnown = CantripKnown;
        for (int i = 0; i < slots.length; i++) {
            SpellSlots.add(slots[i]);
        }
        this.ABSMin = ABSMin;
        this.Name = Name;
    }

    Level(String Name, String[] features, String ABSType, int ABSMin, int spellsKnown, int CantripKnown, int[] slots, int ClassSpecificCount) {
        Features.addAll(Arrays.asList(features));
        ABSMinmum.add(ABSType);
        this.spellsKnown = spellsKnown;
        this.CantripsKnown = CantripKnown;
        this.ClassSpecificCount = ClassSpecificCount;
        for (int i = 0; i < slots.length; i++) {
            SpellSlots.add(slots[i]);
        }
        this.ABSMin = ABSMin;
        this.Name = Name;
    }

    Level(String Name, String[] features, String ABSType, int ABSMin, int[] slots, int CantripKnown) {
        Features.addAll(Arrays.asList(features));
        ABSMinmum.add(ABSType);
        this.CantripsKnown = CantripKnown;
        for (int i = 0; i < slots.length; i++) {
            SpellSlots.add(slots[i]);
        }
        this.ABSMin = ABSMin;
        this.Name = Name;
    }

    Level(String Name, String[] features, String ABSType, int ABSMin) {
        Features.addAll(Arrays.asList(features));
        ABSMinmum.add(ABSType);
        this.ABSMin = ABSMin;
        this.Name = Name;
    }

    Level(String Name, String[] features, int[] slots, String[] ABSTypes, boolean isBoth) {
        Features.addAll(Arrays.asList(features));
        ABSMinmum.addAll(Arrays.asList(ABSTypes));
        for (int i = 0; i < slots.length; i++) {
            SpellSlots.add(slots[i]);
        }
        this.isBoth = isBoth;
        this.Name = Name;
    }

    Level(String Name, String[] features, int[] slots, String[] ABSTypes, boolean isBoth, int spellsKnown) {
        Features.addAll(Arrays.asList(features));
        this.spellsKnown = spellsKnown;
        ABSMinmum.addAll(Arrays.asList(ABSTypes));
        for (int i = 0; i < slots.length; i++) {
            SpellSlots.add(slots[i]);
        }
        this.isBoth = isBoth;
        this.Name = Name;
    }

    Level(String Name, int SneakAttackDiceNum, String[] features) {
        Features.addAll(Arrays.asList(features));
        this.SneakAttackDiceNum = SneakAttackDiceNum;
        ABSMinmum.add("Dexterity");
        this.Name = Name;
    }

    Level(String Name, String[] features, int cantrips, int spells, int warlockSlotLevel, int invocationsKnown, int Slots) {
        Features.addAll(Arrays.asList(features));
        this.CantripsKnown = cantrips;
        this.spellsKnown = spells;
        this.WarlockSlotLevel = warlockSlotLevel;
        this.invocationsKnown = invocationsKnown;
        this.WarlockSlots = Slots;
        ABSMinmum.add("Charisma");
        this.Name = Name;
    }

    Level(String Name, String[] features, int cantripsKnown, int[] slots) {
        Features.addAll(Arrays.asList(features));
        this.CantripsKnown = cantripsKnown;
        for (int i = 0; i < slots.length; i++) {
            SpellSlots.add(slots[i]);
        }
        this.Name = Name;
    }

    Level(String Name, int MartialDice, int MonkMove, String[] features, String[] ABSTypes, int classSpecificCount) {
        Features.addAll(Arrays.asList(features));
        ABSMinmum.addAll(Arrays.asList(ABSTypes));
        this.MonkAdditonalMovement = MonkMove;
        this.MonkDice = MartialDice;
        this.ClassSpecificCount = classSpecificCount;
        this.isBoth = true;
        this.Name = Name;
    }

    Level(String Name, String[] features, String[] ABSTypes, int ABSMin, boolean isBoth) {
        Features.addAll(Arrays.asList(features));
        ABSMinmum.addAll(Arrays.asList(ABSTypes));
        this.isBoth = isBoth;
        this.ABSMin = ABSMin;
        this.Name = Name;
    }

    public ArrayList<String> getFeatures() {
        return Features;
    }

    public void setFeatures(ArrayList<String> features) {
        Features = features;
    }

    public ArrayList<String> getABSMinmum() {
        return ABSMinmum;
    }

    public void setABSMinmum(ArrayList<String> ABSMinmum) {
        this.ABSMinmum = ABSMinmum;
    }

    public int getClassSpecificCount() {
        return ClassSpecificCount;
    }

    public void setClassSpecificCount(int classSpecificCount) {
        ClassSpecificCount = classSpecificCount;
    }

    public int getSpellsKnown() {
        return spellsKnown;
    }

    public void setSpellsKnown(int spellsKnown) {
        this.spellsKnown = spellsKnown;
    }

    public int getCantripsKnown() {
        return CantripsKnown;
    }

    public void setCantripsKnown(int cantripsKnown) {
        CantripsKnown = cantripsKnown;
    }

    public ArrayList<Integer> getSpellSlots() {
        return SpellSlots;
    }

    public void setSpellSlots(ArrayList<Integer> spellSlots) {
        SpellSlots = spellSlots;
    }

    public boolean isBarbmax() {
        return barbmax;
    }

    public void setBarbmax(boolean barbmax) {
        this.barbmax = barbmax;
    }

    public boolean isBoth() {
        return isBoth;
    }

    public void setBoth(boolean both) {
        isBoth = both;
    }

    public int getMonkAdditonalMovement() {
        return MonkAdditonalMovement;
    }

    public void setMonkAdditonalMovement(int monkAdditonalMovement) {
        MonkAdditonalMovement = monkAdditonalMovement;
    }

    public int getMonkDice() {
        return MonkDice;
    }

    public void setMonkDice(int monkDice) {
        MonkDice = monkDice;
    }

    public int getSneakAttackDiceNum() {
        return SneakAttackDiceNum;
    }

    public void setSneakAttackDiceNum(int sneakAttackDiceNum) {
        SneakAttackDiceNum = sneakAttackDiceNum;
    }

    public int getWarlockSlotLevel() {
        return WarlockSlotLevel;
    }

    public void setWarlockSlotLevel(int warlockSlotLevel) {
        WarlockSlotLevel = warlockSlotLevel;
    }

    public int getWarlockSlots() {
        return WarlockSlots;
    }

    public void setWarlockSlots(int warlockSlots) {
        WarlockSlots = warlockSlots;
    }

    public int getInvocationsKnown() {
        return invocationsKnown;
    }

    public void setInvocationsKnown(int invocationsKnown) {
        this.invocationsKnown = invocationsKnown;
    }

    public int getRageDamage() {
        return rageDamage;
    }

    public void setRageDamage(int rageDamage) {
        this.rageDamage = rageDamage;
    }

    public int getABSMin() {
        return ABSMin;
    }

    public void setABSMin(int ABSMin) {
        this.ABSMin = ABSMin;
    }

    public String getMinimumString() {
        String s = "";

        for (int i = 0; i < ABSMinmum.size(); i++) {
            if (i == 0) {
                s = "You need " + ABSMinmum.get(i) + " 13";
            } else {
                if (isBoth) {
                    s = s + " and " + ABSMinmum.get(i) + " 13";
                } else {
                    s = s + " or " + ABSMinmum.get(i) + " 13";
                }
            }
        }
        s = s + " to multiclass with this class";

        return s;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}


