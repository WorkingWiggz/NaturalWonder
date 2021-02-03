package com.thekidd.naturalwonder.CharacterSheets;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CharacterDataRepo {

    Context context;
    private ArrayList<EquimentPiece> EquipmentList = new ArrayList<>();
    private ArrayList<Magic> MagicList = new ArrayList<>();
    private ArrayList<Weapon> WeaponList = new ArrayList<>();
    private ArrayList<String> ClassList = new ArrayList<>();
    private LevelUps Levels = new LevelUps();

    CharacterDataRepo(Context context) {
        this.context = context;
        SetupData();
        ClassList.add("Barbarian");
        ClassList.add("Bard");
        ClassList.add("Cleric");
        ClassList.add("Druid");
        ClassList.add("Fighter");
        ClassList.add("Monk");
        ClassList.add("Paladin");
        ClassList.add("Ranger");
        ClassList.add("Rogue");
        ClassList.add("Sorcerer");
        ClassList.add("Warlock");
        ClassList.add("Wizard");
    }

    public ArrayList<String> getClassList() {
        return ClassList;
    }

    public void setClassList(ArrayList<String> classList) {
        ClassList = classList;
    }

    public LevelUps getLevels() {
        return Levels;
    }

    public void setLevels(LevelUps levels) {
        Levels = levels;
    }

    private void SetupData() {
        StringRequest Equips = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/graphql?query=%7Bequipments%28limit%3A10000%29%7Barmor_category%20armor_class%7Bbase%20dex_bonus%20max_bonus%7D%20capacity%20category_range%20contents%7Bitem%7Bname%20url%7D%20quantity%7D%20cost%7Bunit%20quantity%7D%20damage%7Bdamage_dice%20damage_type%7Bname%20url%7D%7D%20desc%20equipment_category%7Bname%20url%7D%20gear_category%7Bname%20url%7D%20index%20name%20properties%7Bname%20url%7D%20quantity%20range%7Bnormal%20long%7D%20special%20speed%7Bunit%20quantity%7D%20stealth_disadvantage%20str_minimum%20throw_range%7Bnormal%20long%7D%20tool_category%20two_handed_damage%7Bdamage_dice%20damage_type%7Bname%20url%7D%7D%20url%20vehicle_category%20weapon_category%20weapon_range%20weight%20%7D%7D", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    SplitEquipments(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        StringRequest Magic = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/graphql?query=%7Bspells%28limit%3A10000%29%7Barea_of_effect%7Bsize%20type%7D%20attack_type%20casting_time%20classes%7Bname%20url%7D%20components%20concentration%20damage%7Bdamage_type%7Bname%20url%7D%7D%20dc%7Bdesc%20dc_type%7Bname%20url%7D%20dc_success%7D%20desc%20duration%20heal_at_slot_level%20higher_level%20index%20level%20material%20name%20range%20ritual%20school%7Bname%20url%7D%20subclasses%7Bname%20url%7D%20url%20%7D%7D", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    GetMagics(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue q = Volley.newRequestQueue(context);
        q.add(Equips);
        q.add(Magic);
    }

    private void SplitEquipments(String response) throws JSONException {
        JSONArray Weapons = new JSONArray();
        JSONArray Equipments = new JSONArray();
        JSONObject a = new JSONObject(response);
        JSONArray b = a.getJSONObject("data").getJSONArray("equipments");
        for (int i = 0; i < b.length(); i++) {
            JSONObject tmp = b.getJSONObject(i);
            if (tmp.getJSONObject("equipment_category").getString("name").equals("Weapon")) {
                Weapons.put(tmp);
            } else {
                Equipments.put(tmp);
            }
        }
        GetEquipments(Equipments);
        GetWeapons(Weapons);
    }

    private void GetEquipments(JSONArray Data) throws JSONException {
        for (int i = 0; i < Data.length(); i++) {
            JSONObject a = Data.getJSONObject(i);

            String ArmorCat = "";
            if (!a.isNull("armor_category")) {

            }
            int AcBase = 0;
            boolean AcDexBonus = false;
            int AcMaxBonus = 0;
            if (!a.isNull("armor_class")) {
                if (!a.getJSONObject("armor_class").isNull("base")) {
                    AcBase = a.getJSONObject("armor_class").getInt("base");
                }
                if (!a.getJSONObject("armor_class").isNull("dex_bonus")) {
                    AcDexBonus = a.getJSONObject("armor_class").getBoolean("dex_bonus");
                }
                if (!a.getJSONObject("armor_class").isNull("max_bonus")) {
                    AcMaxBonus = a.getJSONObject("armor_class").getInt("max_bonus");
                }
            }
            String Capacity = "";
            if (!a.isNull("capacity")) {
                Capacity = a.getString("capacity");
            }
            String CategoryRange = "";
            if (!a.isNull("category_range")) {
                CategoryRange = a.getString("category_range");
            }

            String CostUnit = "";
            int CostQuantity = 0;
            if (!a.isNull("cost")) {
                CostUnit = a.getJSONObject("cost").getString("unit");
                CostQuantity = a.getJSONObject("cost").getInt("quantity");
            }

            String DamageDice = "";
            String DamageType = "";
            if (!a.isNull("damage")) {
                DamageDice = a.getJSONObject("damage").getString("damage_dice");
                DamageType = a.getJSONObject("damage").getJSONObject("damage_type").getString("name");
            }

            ArrayList<String> desc = new ArrayList<>();
            if (!a.isNull("desc")) {
                if (a.getJSONArray("desc").length() > 0) {
                    for (int j = 0; j < a.getJSONArray("desc").length(); j++) {
                        desc.add(a.getJSONArray("desc").getString(j));
                    }
                }
            }
            String EquipCat = a.getJSONObject("equipment_category").getString("name");
            String GearCat = "";
            if (!a.isNull("gear_category")) {
                GearCat = a.getJSONObject("gear_category").getString("name");
            }


            String Name = a.getString("name");
            ArrayList<String> Properties = new ArrayList<>();
            if (!a.isNull("properties")) {
                if (a.getJSONArray("properties").length() > 0) {
                    for (int j = 0; j < a.getJSONArray("properties").length(); j++) {
                        Properties.add(a.getJSONArray("properties").getJSONObject(j).getString("name"));
                    }
                }
            }

            int Quantity = 0;
            if (!a.isNull("quantity")) {
                Quantity = a.getInt("quantity");
            }

            int RangeShort = 0;
            int RangeLong = 0;
            if (!a.isNull("range")) {
                if (!a.getJSONObject("range").isNull("long")) {
                    RangeLong = a.getJSONObject("range").getInt("long");
                }
                if (!a.getJSONObject("range").isNull("normal")) {
                    RangeShort = a.getJSONObject("range").getInt("normal");
                }
            }
            ArrayList<String> Special = new ArrayList<>();
            for (int j = 0; j < a.getJSONArray("special").length(); j++) {
                Special.add(a.getJSONArray("special").getString(j));
            }

            String SpeedUnit = "";
            int SpeedQuantity = 0;
            if (!a.isNull("speed")) {
                SpeedUnit = a.getJSONObject("speed").getString("unit");
                SpeedQuantity = a.getJSONObject("speed").getInt("quantity");
            }

            boolean StealthDisadvantage = false;
            if (!a.isNull("stealth_disadvantage")) {
                StealthDisadvantage = a.getBoolean("stealth_disadvantage");
            }

            int StrMinimum = 0;
            if (!a.isNull("str_minimum")) {
                StrMinimum = a.getInt("str_minimum");
            }
            int ThrownLong = 0;
            int ThrownShort = 0;
            if (!a.isNull("throw_range")) {
                ThrownShort = a.getJSONObject("throw_range").getInt("normal");
                ThrownLong = a.getJSONObject("throw_range").getInt("long");
            }

            String toolCat = "";
            if (!a.isNull("tool_category")) {
                toolCat = a.getString("tool_category");
            }

            String TwoHandedDamageDice = "";
            String TwoHandedDamageType = "";
            if (!a.isNull("two_handed_damage")) {
                TwoHandedDamageDice = a.getJSONObject("two_handed_damage").getString("damage_dice");
                TwoHandedDamageType = a.getJSONObject("two_handed_damage").getJSONObject("damage_type").getString("name");
            }

            String VehCat = "";
            if (!a.isNull("vehicle_category")) {
                VehCat = a.getString("vehicle_category");
            }

            String WeaponCategory = "";
            if (!a.isNull("weapon_category")) {
                WeaponCategory = a.getString("");
            }
            String WeaponRange = "";
            if (!a.isNull("weapon_range")) {
                WeaponRange = a.getString("weapon_range");
            }
            int weight = 0;
            if (!a.isNull("weight")) {
                weight = a.getInt("weight");
            }

            EquipmentList.add(new EquimentPiece(Name, EquipCat, ArmorCat, AcBase, AcDexBonus, AcMaxBonus, Capacity, CategoryRange, CostUnit, CostQuantity, DamageDice, DamageType, desc, Properties, Quantity, RangeShort, RangeLong, Special, SpeedUnit, SpeedQuantity, StealthDisadvantage, StrMinimum, ThrownShort, ThrownLong, toolCat, TwoHandedDamageDice, TwoHandedDamageType, VehCat, WeaponCategory, WeaponRange, weight));
        }
    }

    private void GetMagics(String Data) throws JSONException {
        JSONObject a = new JSONObject(Data);
        JSONArray b = a.getJSONObject("data").getJSONArray("spells");
        for (int i = 0; i < b.length(); i++) {
            JSONObject c = b.getJSONObject(i);
            ArrayList<String> desc = new ArrayList<>();
            if (!c.isNull("desc")) {
                if (c.getJSONArray("desc").length() > 0) {
                    for (int j = 0; j < c.getJSONArray("desc").length(); j++) {
                        desc.add(c.getJSONArray("desc").getString(j));
                    }
                }
            }
            String castTime = c.getString("casting_time");
            ArrayList<String> SpellClass = new ArrayList<>();
            for (int j = 0; j < c.getJSONArray("classes").length(); j++) {
                SpellClass.add(c.getJSONArray("classes").getJSONObject(j).getString("name"));
            }

            ArrayList<String> components = new ArrayList<>();
            for (int j = 0; j < c.getJSONArray("components").length(); j++) {
                components.add(c.getJSONArray("components").getString(j));
            }
            boolean concentration = c.getBoolean("concentration");
            String Range = c.getString("range");
            boolean ritual = c.getBoolean("ritual");
            String School = c.getJSONObject("school").getString("name");
            String Duration = c.getString("duration");
            int level = c.getInt("level");
            String Material = c.getString("material");
            String Name = c.getString("name");
            String AttackType = "";
            if (!c.isNull("attack_type")) {
                AttackType = c.getString("attack_type");
            }
            String AOEType = "";
            int AOEsize = 0;
            if (!c.isNull("area_of_effect")) {
                AOEsize = c.getJSONObject("area_of_effect").getInt("size");
                AOEType = c.getJSONObject("area_of_effect").getString("type");
            }

            String DamageType = "";
            if (!c.isNull("damage")) {
                if (!c.getJSONObject("damage").isNull("damage_type")) {
                    DamageType = c.getJSONObject("damage").getJSONObject("damage_type").getString("name");
                }
            }

            String dcDesc = "";
            String dcType = "";
            String dcSucces = "";
            if (!c.isNull("dc")) {
                dcDesc = c.getJSONObject("dc").getString("desc");
                dcType = c.getJSONObject("dc").getJSONObject("dc_type").getString("name");
                dcSucces = c.getJSONObject("dc").getString("dc_success");
            }

            ArrayList<String> higherLevels = new ArrayList<>();
            if (c.getJSONArray("higher_level").length() > 0) {
                for (int j = 0; j < c.getJSONArray("higher_level").length(); j++) {
                    higherLevels.add(c.getJSONArray("higher_level").getString(j));
                }
            }

            HashMap<Integer, String> healAtSlotLevel = new HashMap<>();
            if (!c.isNull("heal_at_slot_level")) {
                JSONObject d = c.getJSONObject("heal_at_slot_level");
                JSONArray e = d.names();
                for (int j = 0; j < e.length(); j++) {
                    healAtSlotLevel.put(Integer.parseInt(e.getString(j)), d.getString(e.getString(j)));
                }
            }
            MagicList.add(new Magic(Name, "Spell", desc, castTime, SpellClass, components, concentration, Range, ritual, School, Duration, level, Material, AttackType, AOEType, AOEsize, DamageType, dcDesc, dcType, dcSucces, higherLevels, healAtSlotLevel));
        }
    }

    private void GetWeapons(JSONArray Data) throws JSONException {
        for (int i = 0; i < Data.length(); i++) {
            JSONObject a = Data.getJSONObject(i);

            String CategoryRange = "";
            if (!a.isNull("category_range")) {
                CategoryRange = a.getString("category_range");
            }

            String CostUnit = "";
            int CostQuantity = 0;
            if (!a.isNull("cost")) {
                CostUnit = a.getJSONObject("cost").getString("unit");
                CostQuantity = a.getJSONObject("cost").getInt("quantity");
            }

            String DamageDice = "";
            String DamageType = "";
            if (!a.isNull("damage")) {
                DamageDice = a.getJSONObject("damage").getString("damage_dice");
                DamageType = a.getJSONObject("damage").getJSONObject("damage_type").getString("name");
            }

            ArrayList<String> desc = new ArrayList<>();
            if (!a.isNull("desc")) {
                for (int j = 0; j < a.getJSONArray("desc").length(); j++) {
                    desc.add(a.getJSONArray("desc").getString(j));
                }
            }

            String Name = a.getString("name");
            ArrayList<String> Properties = new ArrayList<>();
            for (int j = 0; j < a.getJSONArray("properties").length(); j++) {
                Properties.add(a.getJSONArray("properties").getJSONObject(j).getString("name"));
            }

            int RangeShort = 0;
            int RangeLong = 0;
            if (!a.isNull("range")) {
                if (!a.getJSONObject("range").isNull("long")) {
                    RangeLong = a.getJSONObject("range").getInt("long");
                }
                if (!a.getJSONObject("range").isNull("normal")) {
                    RangeShort = a.getJSONObject("range").getInt("normal");
                }
            }
            ArrayList<String> Special = new ArrayList<>();
            for (int j = 0; j < a.getJSONArray("special").length(); j++) {
                Special.add(a.getJSONArray("special").getString(j));
            }

            String SpeedUnit = "";
            int SpeedQuantity = 0;
            if (!a.isNull("speed")) {
                SpeedUnit = a.getJSONObject("speed").getString("unit");
                SpeedQuantity = a.getJSONObject("speed").getInt("quantity");
            }

            boolean StealthDisadvantage = false;
            if (!a.isNull("stealth_disadvantage")) {
                StealthDisadvantage = a.getBoolean("stealth_disadvantage");
            }

            int StrMinimum = 0;
            if (!a.isNull("str_minimum")) {
                StrMinimum = a.getInt("str_minimum");
            }
            int ThrownLong = 0;
            int ThrownShort = 0;
            if (!a.isNull("throw_range")) {
                ThrownShort = a.getJSONObject("throw_range").getInt("normal");
                ThrownLong = a.getJSONObject("throw_range").getInt("long");
            }


            String TwoHandedDamageDice = "";
            String TwoHandedDamageType = "";
            if (!a.isNull("two_handed_damage")) {
                TwoHandedDamageDice = a.getJSONObject("two_handed_damage").getString("damage_dice");
                TwoHandedDamageType = a.getJSONObject("two_handed_damage").getJSONObject("damage_type").getString("name");
            }


            String WeaponCategory = "";
            if (!a.isNull("weapon_category")) {
                WeaponCategory = a.getString("weapon_category");
            }
            String WeaponRange = "";
            if (!a.isNull("weapon_range")) {
                WeaponRange = a.getString("weapon_range");
            }
            int weight = 0;
            if (!a.isNull("weight")) {
                weight = a.getInt("weight");
            }
            WeaponList.add(new Weapon(Name, "Weapon", CategoryRange, CostUnit, CostQuantity, DamageDice, DamageType, desc, Properties, RangeShort, RangeLong, Special, SpeedUnit, SpeedQuantity, StealthDisadvantage, StrMinimum, ThrownShort, ThrownLong, TwoHandedDamageDice, TwoHandedDamageType, WeaponCategory, WeaponRange, weight));
        }
    }

    void AddtoEquipment(EquimentPiece EP) {
        getEquipmentList().add(EP);
    }

    void AddtoMagic(Magic M) {
        getMagicList().add(M);
    }

    void AddtoWeapon(Weapon W) {
        getWeaponList().add(W);
    }

    public ArrayList<EquimentPiece> getEquipmentList() {
        return EquipmentList;
    }

    public void setEquipmentList(ArrayList<EquimentPiece> equipmentList) {
        EquipmentList = equipmentList;
    }

    public ArrayList<Magic> getMagicList() {
        return MagicList;
    }

    public void setMagicList(ArrayList<Magic> magicList) {
        MagicList = magicList;
    }

    public ArrayList<Weapon> getWeaponList() {
        return WeaponList;
    }

    public void setWeaponList(ArrayList<Weapon> weaponList) {
        WeaponList = weaponList;
    }


}
