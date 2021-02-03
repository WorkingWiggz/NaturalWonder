package com.thekidd.naturalwonder.CharacterSheets;

import java.util.ArrayList;

public class LevelUps {
    private ArrayList<Level> Barbarian = new ArrayList<>();
    private ArrayList<Level> Bard = new ArrayList<>();
    private ArrayList<Level> Cleric = new ArrayList<>();
    private ArrayList<Level> Druid = new ArrayList<>();
    private ArrayList<Level> Fighter = new ArrayList<>();
    private ArrayList<Level> Monk = new ArrayList<>();
    private ArrayList<Level> Paladin = new ArrayList<>();
    private ArrayList<Level> Ranger = new ArrayList<>();
    private ArrayList<Level> Rogue = new ArrayList<>();
    private ArrayList<Level> Sorcerer = new ArrayList<>();
    private final ArrayList<Level> Warlock = new ArrayList<>();
    private ArrayList<Level> Wizard = new ArrayList<>();
    private ArrayList<ArrayList<Level>> Classes = new ArrayList<>();

    LevelUps() {
        LoadLevels();
    }

    public ArrayList<ArrayList<Level>> getClasses() {
        return Classes;
    }

    public void setClasses(ArrayList<ArrayList<Level>> classes) {
        Classes = classes;
    }

    private void LoadLevels() {
        //Barb Levels
        Barbarian.add(new Level("Barbarian", new String[]{"Rage", "Unarmored Defense"}, "Strength", 13, 2, 2, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Reckless Attack", "Danger Sense"}, "Strength", 13, 2, 2, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Primal Path"}, "Strength", 13, 3, 2, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Ability Score Improvement"}, "Strength", 13, 3, 2, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Extra Attack", "Fast Movement"}, "Strength", 13, 3, 2, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Path Feature"}, "Strength", 13, 4, 2, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Fearal Instinct"}, "Strength", 13, 4, 2, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Ability Score Improvement"}, "Strength", 13, 4, 2, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Brutal Critical (1 die)"}, "Strength", 13, 4, 3, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Path feature"}, "Strength", 13, 4, 3, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Relentless Rage"}, "Strength", 13, 4, 3, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Ability Score Improvement"}, "Strength", 13, 5, 3, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Brutal Critical (2 dice)"}, "Strength", 13, 5, 3, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Path feature"}, "Strength", 13, 5, 3, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Persistent Rage"}, "Strength", 13, 5, 3, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Ability Score Improvement"}, "Strength", 13, 5, 4, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Brutal Critical (3 dice)"}, "Strength", 13, 6, 4, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Indomitable Might"}, "Strength", 13, 6, 4, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Ability Score Improvement"}, "Strength", 13, 6, 4, false));
        Barbarian.add(new Level("Barbarian", new String[]{"Primal Champion"}, "Strength", 13, 0, 4, true));

        Bard.add(new Level("Bard", new String[]{"Spellcasting", "Bardic Inspiration (d6)"}, "Charisma", 13, 4, 2, new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Jack of All Trades", "Song of Rest (d6)"}, "Charisma", 13, 5, 2, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Bard College", "Expertise"}, "Charisma", 13, 6, 2, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Ability Score Improvement"}, "Charisma", 13, 7, 3, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Bardic Inspiration (d8)", "Font of Inspiration"}, "Charisma", 13, 8, 3, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Countercharm", "Bard College feature"}, "Charisma", 13, 9, 3, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{""}, "Charisma", 13, 10, 3, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Ability Score Improvement"}, "Charisma", 13, 11, 3, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Song of Rest (d8)"}, "Charisma", 13, 12, 3, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Bardic Inspiration (d10)", "Expertise", "Magical Secrets"}, "Charisma", 13, 14, 4, new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{""}, "Charisma", 13, 15, 4, new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Ability Score Improvement"}, "Charisma", 13, 15, 4, new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Song of Rest (d10)"}, "Charisma", 13, 16, 4, new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Magical Secrets", "Bard College feature"}, "Charisma", 13, 18, 4, new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0}));
        Bard.add(new Level("Bard", new String[]{"Bardic Inspiration (d12)"}, "Charisma", 13, 19, 4, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0}));
        Bard.add(new Level("Bard", new String[]{"Ability Score Improvement"}, "Charisma", 13, 19, 4, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0}));
        Bard.add(new Level("Bard", new String[]{"Song of Rest (d12)"}, "Charisma", 13, 20, 4, new int[]{4, 3, 3, 3, 3, 1, 1, 1, 1}));
        Bard.add(new Level("Bard", new String[]{"Magical Secrets"}, "Charisma", 13, 22, 4, new int[]{4, 3, 3, 3, 3, 2, 1, 1, 1}));
        Bard.add(new Level("Bard", new String[]{"Ability Score Improvement"}, "Charisma", 13, 22, 4, new int[]{4, 3, 3, 3, 3, 2, 1, 1, 1}));
        Bard.add(new Level("Bard", new String[]{"Superior Inspiration"}, "Charisma", 13, 22, 4, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}));

        Cleric.add(new Level("Cleric", new String[]{"Spellcasting", "Divine Domain"}, "Wisdom", 13, new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0}, 3));
        Cleric.add(new Level("Cleric", new String[]{"Channel Divinity (1/rest)", "Divine Domain feature"}, "Wisdom", 13, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}, 3));
        Cleric.add(new Level("Cleric", new String[]{""}, "Wisdom", 13, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}, 3));
        Cleric.add(new Level("Cleric", new String[]{"Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}, 4));
        Cleric.add(new Level("Cleric", new String[]{"Destroy Undead (CR 1/2)"}, "Wisdom", 13, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}, 4));
        Cleric.add(new Level("Cleric", new String[]{"Channel Divinity (2/rest)", "Divine Domain feature"}, "Wisdom", 13, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}, 4));
        Cleric.add(new Level("Cleric", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}, 4));
        Cleric.add(new Level("Cleric", new String[]{"Ability Score Improvement", "Destroy Undead (CR 1)", "Divine Domain feature"}, "Wisdom", 13, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}, 4));
        Cleric.add(new Level("Cleric", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}, 4));
        Cleric.add(new Level("Cleric", new String[]{"Divine Intervention"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0}, 5));
        Cleric.add(new Level("Cleric", new String[]{"Destroy Undead (CR 2)"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0}, 5));
        Cleric.add(new Level("Cleric", new String[]{"Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0}, 5));
        Cleric.add(new Level("Cleric", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0}, 5));
        Cleric.add(new Level("Cleric", new String[]{"Destroy Undead (CR 3)"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0}, 5));
        Cleric.add(new Level("Cleric", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0}, 5));
        Cleric.add(new Level("Cleric", new String[]{"Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0}, 5));
        Cleric.add(new Level("Cleric", new String[]{"Destroy Undead (CR 4)", "Divine Domain feature"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 1}, 5));
        Cleric.add(new Level("Cleric", new String[]{"Channel Divinity (3/rest)"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 1, 1, 1}, 5));
        Cleric.add(new Level("Cleric", new String[]{"Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 1, 1, 1}, 5));
        Cleric.add(new Level("Cleric", new String[]{"Divine Intervention improvement"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 5));

        Druid.add(new Level("Druid", new String[]{"Druidic", "Spellcasting"}, "Wisdom", 13, new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0}, 2));
        Druid.add(new Level("Druid", new String[]{"Wild Shape", "Druid Circle"}, "Wisdom", 13, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}, 2));
        Druid.add(new Level("Druid", new String[]{""}, "Wisdom", 13, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}, 2));
        Druid.add(new Level("Druid", new String[]{"Wild Shape improvement", "Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}, 3));
        Druid.add(new Level("Druid", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}, 3));
        Druid.add(new Level("Druid", new String[]{"Druid Circle feature"}, "Wisdom", 13, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}, 3));
        Druid.add(new Level("Druid", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}, 3));
        Druid.add(new Level("Druid", new String[]{"Wild Shape improvement", "Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}, 3));
        Druid.add(new Level("Druid", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}, 3));
        Druid.add(new Level("Druid", new String[]{"Druid Circle feature"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{"Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{"Druid Circle feature"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{"Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{""}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{"Timeless Body", "Beast Spells"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{"Ability Score Improvement"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));
        Druid.add(new Level("Druid", new String[]{"Archdruid"}, "Wisdom", 13, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 4));

        Fighter.add(new Level("Fighter", new String[]{"Fighting Style", "Second Wind"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Action Surge (one use)"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Martial Archetype"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Ability Score Improvement"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Extra Attack"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Ability Score Improvement"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Martial Archetype feature"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Ability Score Improvement"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Indomitable (one use)"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Martial Archetype feature"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Extra Attack (2)"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Ability Score Improvementd"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Indomitable (two uses)"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Ability Score Improvement"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Martial Archetype feature"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Ability Score Improvement"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Action Surge (two uses)", "Indomitable (three uses)"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Martial Archetype feature"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Ability Score Improvement"}, new String[]{"Strength", "Dexterity"}, 13, false));
        Fighter.add(new Level("Fighter", new String[]{"Extra Attack (3)"}, new String[]{"Strength", "Dexterity"}, 13, false));


        Monk.add(new Level("Monk", 4, 0, new String[]{"Unarmored Defense", "Martial Arts"}, new String[]{"Dexterity", "Wisdom"}, 0));
        Monk.add(new Level("Monk", 4, 10, new String[]{"Ki", "Unarmored Movement"}, new String[]{"Dexterity", "Wisdom"}, 2));
        Monk.add(new Level("Monk", 4, 10, new String[]{"Monastic Tradition", "Deflect Missiles"}, new String[]{"Dexterity", "Wisdom"}, 3));
        Monk.add(new Level("Monk", 4, 10, new String[]{"Ability Score Improvement", "Slow Fall"}, new String[]{"Dexterity", "Wisdom"}, 4));
        Monk.add(new Level("Monk", 6, 10, new String[]{"Extra Attack", "Stunning Strike"}, new String[]{"Dexterity", "Wisdom"}, 5));
        Monk.add(new Level("Monk", 6, 15, new String[]{"Ki-Empowered Strikes", "Monastic Tradition feature"}, new String[]{"Dexterity", "Wisdom"}, 6));
        Monk.add(new Level("Monk", 6, 15, new String[]{"Evasion", "Stillness of Mind"}, new String[]{"Dexterity", "Wisdom"}, 7));
        Monk.add(new Level("Monk", 6, 15, new String[]{"Ability Score Improvement"}, new String[]{"Dexterity", "Wisdom"}, 8));
        Monk.add(new Level("Monk", 6, 15, new String[]{"Unarmored Movement improvement"}, new String[]{"Dexterity", "Wisdom"}, 9));
        Monk.add(new Level("Monk", 6, 20, new String[]{"Purity of Body"}, new String[]{"Dexterity", "Wisdom"}, 10));
        Monk.add(new Level("Monk", 8, 20, new String[]{"Monastic Tradition feature"}, new String[]{"Dexterity", "Wisdom"}, 11));
        Monk.add(new Level("Monk", 8, 20, new String[]{"Ability Score Improvement"}, new String[]{"Dexterity", "Wisdom"}, 12));
        Monk.add(new Level("Monk", 8, 20, new String[]{"Tongue of the Sun and Moon"}, new String[]{"Dexterity", "Wisdom"}, 13));
        Monk.add(new Level("Monk", 8, 25, new String[]{"Diamond Soul"}, new String[]{"Dexterity", "Wisdom"}, 14));
        Monk.add(new Level("Monk", 8, 25, new String[]{"Timeless Body"}, new String[]{"Dexterity", "Wisdom"}, 15));
        Monk.add(new Level("Monk", 8, 25, new String[]{"Ability Score Improvement"}, new String[]{"Dexterity", "Wisdom"}, 16));
        Monk.add(new Level("Monk", 10, 25, new String[]{"Monastic Tradition feature"}, new String[]{"Dexterity", "Wisdom"}, 17));
        Monk.add(new Level("Monk", 10, 30, new String[]{"Empty Body"}, new String[]{"Dexterity", "Wisdom"}, 18));
        Monk.add(new Level("Monk", 10, 30, new String[]{"Ability Score Improvement"}, new String[]{"Dexterity", "Wisdom"}, 19));
        Monk.add(new Level("Monk", 10, 30, new String[]{"Perfect Self"}, new String[]{"Dexterity", "Wisdom"}, 20));

        Paladin.add(new Level("Paladin", new String[]{"Divine Sense", "Lay on Hands"}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Fighting Style", "Spellcasting", "Divine Smite"}, new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Divine Health", "Sacred Oath"}, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Ability Score Improvement"}, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Extra Attack"}, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Aura of Protection"}, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Sacred Oath feature"}, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Ability Score Improvement"}, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{""}, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Aura of Courage"}, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Improved Divine Smite"}, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Ability Score Improvement"}, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{""}, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Cleansing Touch"}, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Sacred Oath feature"}, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Ability Score Improvement"}, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{""}, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Aura improvements"}, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Ability Score Improvement"}, new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));
        Paladin.add(new Level("Paladin", new String[]{"Sacred Oath feature"}, new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0}, new String[]{"Strength", "Charisma"}, true));


        Ranger.add(new Level("Ranger", new String[]{"Favored Enemy", "Natural Explorer"}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 0));
        Ranger.add(new Level("Ranger", new String[]{"Fighting Style", "Spellcasting"}, new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 2));
        Ranger.add(new Level("Ranger", new String[]{"Ranger Archetype", "Primeval Awareness"}, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 3));
        Ranger.add(new Level("Ranger", new String[]{"Ability Score Improvement"}, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 3));
        Ranger.add(new Level("Ranger", new String[]{"Extra Attack"}, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 4));
        Ranger.add(new Level("Ranger", new String[]{"Favored Enemy and Natural Explorer improvements"}, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 4));
        Ranger.add(new Level("Ranger", new String[]{"Ranger Archetype feature"}, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 5));
        Ranger.add(new Level("Ranger", new String[]{"Ability Score Improvement", "Land’s Stride"}, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 5));
        Ranger.add(new Level("Ranger", new String[]{""}, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 6));
        Ranger.add(new Level("Ranger", new String[]{"Natural Explorer improvement", "Hide in Plain Sight"}, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 6));
        Ranger.add(new Level("Ranger", new String[]{"Ranger Archetype feature"}, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 7));
        Ranger.add(new Level("Ranger", new String[]{"Ability Score Improvement"}, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 7));
        Ranger.add(new Level("Ranger", new String[]{""}, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 8));
        Ranger.add(new Level("Ranger", new String[]{"Favored Enemy improvement", "Vanish"}, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 8));
        Ranger.add(new Level("Ranger", new String[]{"Ranger Archetype feature"}, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 9));
        Ranger.add(new Level("Ranger", new String[]{"Ability Score Improvement"}, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 9));
        Ranger.add(new Level("Ranger", new String[]{""}, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 10));
        Ranger.add(new Level("Ranger", new String[]{"Feral Senses"}, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 10));
        Ranger.add(new Level("Ranger", new String[]{"Ability Score Improvement"}, new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 11));
        Ranger.add(new Level("Ranger", new String[]{"Foe Slayer"}, new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0}, new String[]{"Dexterity", "Wisdom"}, true, 11));

        Rogue.add(new Level("Rogue", 1, new String[]{"Expertise", "Sneak Attack", "Thieves’ Cant"}));
        Rogue.add(new Level("Rogue", 1, new String[]{"Cunning Action"}));
        Rogue.add(new Level("Rogue", 2, new String[]{"Roguish Archetype"}));
        Rogue.add(new Level("Rogue", 2, new String[]{"Ability Score Improvement"}));
        Rogue.add(new Level("Rogue", 3, new String[]{"Uncanny Dodge"}));
        Rogue.add(new Level("Rogue", 3, new String[]{"Expertise"}));
        Rogue.add(new Level("Rogue", 4, new String[]{"Evasion"}));
        Rogue.add(new Level("Rogue", 4, new String[]{"Ability Score Improvement"}));
        Rogue.add(new Level("Rogue", 5, new String[]{"Roguish Archetype feature"}));
        Rogue.add(new Level("Rogue", 5, new String[]{"Ability Score Improvement"}));
        Rogue.add(new Level("Rogue", 6, new String[]{"Reliable Talent"}));
        Rogue.add(new Level("Rogue", 6, new String[]{"Ability Score Improvement"}));
        Rogue.add(new Level("Rogue", 7, new String[]{"Roguish Archetype feature"}));
        Rogue.add(new Level("Rogue", 7, new String[]{"Blindsense"}));
        Rogue.add(new Level("Rogue", 8, new String[]{"Slippery Mind"}));
        Rogue.add(new Level("Rogue", 8, new String[]{"Ability Score Improvement"}));
        Rogue.add(new Level("Rogue", 9, new String[]{"Roguish Archetype feature"}));
        Rogue.add(new Level("Rogue", 9, new String[]{"Elusive"}));
        Rogue.add(new Level("Rogue", 10, new String[]{"Ability Score Improvement"}));
        Rogue.add(new Level("Rogue", 10, new String[]{"Stroke of Luck"}));

        Sorcerer.add(new Level("Sorcerer", new String[]{"Spellcasting", "Sorcerous Origin"}, "Charisma", 13, 2, 4, new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0}, 0));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Font of Magic"}, "Charisma", 13, 3, 4, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}, 2));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Metamagic"}, "Charisma", 13, 4, 4, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}, 3));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Ability Score Improvement"}, "Charisma", 13, 5, 5, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}, 4));
        Sorcerer.add(new Level("Sorcerer", new String[]{""}, "Charisma", 13, 6, 5, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}, 5));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Sorcerous Origin feature"}, "Charisma", 13, 7, 5, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}, 6));
        Sorcerer.add(new Level("Sorcerer", new String[]{""}, "Charisma", 13, 8, 5, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}, 7));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Ability Score Improvement"}, "Charisma", 13, 9, 5, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}, 8));
        Sorcerer.add(new Level("Sorcerer", new String[]{""}, "Charisma", 13, 10, 5, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}, 9));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Metamagic"}, "Charisma", 13, 11, 6, new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0}, 10));
        Sorcerer.add(new Level("Sorcerer", new String[]{""}, "Charisma", 13, 12, 6, new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0}, 11));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Ability Score Improvement"}, "Charisma", 13, 12, 6, new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0}, 12));
        Sorcerer.add(new Level("Sorcerer", new String[]{""}, "Charisma", 13, 13, 6, new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0}, 13));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Sorcerous Origin feature"}, "Charisma", 13, 13, 6, new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0}, 14));
        Sorcerer.add(new Level("Sorcerer", new String[]{""}, "Charisma", 13, 14, 6, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0}, 15));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Ability Score Improvement"}, "Charisma", 13, 14, 6, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0}, 16));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Metamagic"}, "Charisma", 13, 15, 6, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 1}, 17));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Sorcerous Origin feature"}, "Charisma", 13, 15, 6, new int[]{4, 3, 3, 3, 3, 1, 1, 1, 1}, 18));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Ability Score Improvement"}, "Charisma", 13, 15, 6, new int[]{4, 3, 3, 3, 3, 2, 1, 1, 1}, 19));
        Sorcerer.add(new Level("Sorcerer", new String[]{"Sorcerous Restoration"}, "Charisma", 13, 15, 6, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}, 20));

        Warlock.add(new Level("Warlock", new String[]{"Otherworldly Patron", "Pact Magic"}, 2, 2, 1, 0, 1));
        Warlock.add(new Level("Warlock", new String[]{"Eldritch Invocations"}, 2, 3, 1, 2, 2));
        Warlock.add(new Level("Warlock", new String[]{"Pact Boon"}, 2, 4, 2, 2, 2));
        Warlock.add(new Level("Warlock", new String[]{"Ability Score Improvement"}, 3, 5, 2, 2, 2));
        Warlock.add(new Level("Warlock", new String[]{""}, 3, 6, 3, 3, 2));
        Warlock.add(new Level("Warlock", new String[]{"Otherworldly Patron feature"}, 3, 7, 3, 3, 2));
        Warlock.add(new Level("Warlock", new String[]{""}, 3, 8, 4, 4, 2));
        Warlock.add(new Level("Warlock", new String[]{"Ability Score Improvement"}, 3, 9, 4, 4, 2));
        Warlock.add(new Level("Warlock", new String[]{""}, 3, 10, 5, 5, 2));
        Warlock.add(new Level("Warlock", new String[]{"Otherworldly Patron feature"}, 4, 10, 5, 5, 2));
        Warlock.add(new Level("Warlock", new String[]{"Mystic Arcanum (6th level)"}, 4, 11, 5, 5, 3));
        Warlock.add(new Level("Warlock", new String[]{"Ability Score Improvement"}, 4, 11, 5, 6, 3));
        Warlock.add(new Level("Warlock", new String[]{"Mystic Arcanum (7th level)"}, 4, 12, 5, 6, 3));
        Warlock.add(new Level("Warlock", new String[]{"Otherworldly Patron feature"}, 4, 12, 5, 6, 3));
        Warlock.add(new Level("Warlock", new String[]{"Mystic Arcanum (8th level)"}, 4, 13, 5, 7, 3));
        Warlock.add(new Level("Warlock", new String[]{"Ability Score Improvement"}, 4, 13, 5, 7, 3));
        Warlock.add(new Level("Warlock", new String[]{"Mystic Arcanum (9th level)"}, 4, 14, 5, 7, 4));
        Warlock.add(new Level("Warlock", new String[]{""}, 4, 14, 5, 8, 4));
        Warlock.add(new Level("Warlock", new String[]{"Ability Score Improvement"}, 4, 15, 5, 8, 4));
        Warlock.add(new Level("Warlock", new String[]{"Eldritch Master"}, 4, 15, 5, 8, 4));

        Wizard.add(new Level("Wizard", new String[]{"Spellcasting", "Arcane Recovery"}, 3, new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{"Arcane Tradition"}, 3, new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{""}, 3, new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{"Ability Score Improvement"}, 4, new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{""}, 4, new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{"Arcane Tradition feature"}, 4, new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{""}, 4, new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{"Ability Score Improvement"}, 4, new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{""}, 4, new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{"Arcane Tradition feature"}, 5, new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{""}, 5, new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{"Ability Score Improvement"}, 5, new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{""}, 5, new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{"Arcane Tradition feature"}, 5, new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0}));
        Wizard.add(new Level("Wizard", new String[]{""}, 5, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0}));
        Wizard.add(new Level("Wizard", new String[]{"Ability Score Improvement"}, 5, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0}));
        Wizard.add(new Level("Wizard", new String[]{""}, 5, new int[]{4, 3, 3, 3, 2, 1, 1, 1, 1}));
        Wizard.add(new Level("Wizard", new String[]{"Spell Mastery"}, 5, new int[]{4, 3, 3, 3, 3, 1, 1, 1, 1}));
        Wizard.add(new Level("Wizard", new String[]{"Ability Score Improvement"}, 5, new int[]{4, 3, 3, 3, 3, 2, 1, 1, 1}));
        Wizard.add(new Level("Wizard", new String[]{"Signature Spell"}, 5, new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1}));

        Classes.add(Barbarian);
        Classes.add(Bard);
        Classes.add(Cleric);
        Classes.add(Druid);
        Classes.add(Fighter);
        Classes.add(Monk);
        Classes.add(Paladin);
        Classes.add(Ranger);
        Classes.add(Rogue);
        Classes.add(Sorcerer);
        Classes.add(Warlock);
        Classes.add(Wizard);
    }

    public ArrayList<Level> getBarbarian() {
        return Barbarian;
    }

    public void setBarbarian(ArrayList<Level> barbarian) {
        Barbarian = barbarian;
    }

    public ArrayList<Level> getBard() {
        return Bard;
    }

    public void setBard(ArrayList<Level> bard) {
        Bard = bard;
    }

    public ArrayList<Level> getCleric() {
        return Cleric;
    }

    public void setCleric(ArrayList<Level> cleric) {
        Cleric = cleric;
    }

    public ArrayList<Level> getDruid() {
        return Druid;
    }

    public void setDruid(ArrayList<Level> druid) {
        Druid = druid;
    }

    public ArrayList<Level> getFighter() {
        return Fighter;
    }

    public void setFighter(ArrayList<Level> fighter) {
        Fighter = fighter;
    }

    public ArrayList<Level> getMonk() {
        return Monk;
    }

    public void setMonk(ArrayList<Level> monk) {
        Monk = monk;
    }

    public ArrayList<Level> getPaladin() {
        return Paladin;
    }

    public void setPaladin(ArrayList<Level> paladin) {
        Paladin = paladin;
    }

    public ArrayList<Level> getRanger() {
        return Ranger;
    }

    public void setRanger(ArrayList<Level> ranger) {
        Ranger = ranger;
    }

    public ArrayList<Level> getRogue() {
        return Rogue;
    }

    public void setRogue(ArrayList<Level> rogue) {
        Rogue = rogue;
    }

    public ArrayList<Level> getSorcerer() {
        return Sorcerer;
    }

    public void setSorcerer(ArrayList<Level> sorcerer) {
        Sorcerer = sorcerer;
    }

    public ArrayList<Level> getWarlock() {
        return Warlock;
    }

    public void setWarlock(ArrayList<Level> Warlock) {
        Warlock = Warlock;
    }

    public ArrayList<Level> getWizard() {
        return Wizard;
    }

    public void setWizard(ArrayList<Level> wizard) {
        Wizard = wizard;
    }


}
