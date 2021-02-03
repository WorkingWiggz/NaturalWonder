package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;

public class Equipment extends BasicItemActivity {

    TextView TitleName, EqCat, Cost, Weight, Desc;

    TextView ArmorCategory, ArmorAC, ArmorCost, ArmorStealth, ArmorStrMini, ArmorWeight;
    TextView WeaponStrMini, WeaponRange, WeaponDamage, WeaponRangeType, WeaponCost, WeaponCategory, PropertiesLBL, WeaponThrownRange, WeaponWeight;
    TextView AmmoWeight, AmmoGearCat, AmmoCost, AmmoQuantity;
    TextView AGCost, AGDesc, AGGearCat, AGWeight;
    TextView ToolsCost, ToolsDesc, ToolCategory, ToolWeight;
    TextView MAVCapacity, MAVCost, MAVSpeed, MAVvehcat;
    ListView PropertiesList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        TitleName = findViewById(R.id.TitleName);
        EqCat = findViewById(R.id.EqCat);
        Cost = findViewById(R.id.Cost);
        Weight = findViewById(R.id.Weight);
        Desc = findViewById(R.id.Desc);
        BackButt = findViewById(R.id.BackButt);

        ArmorCategory = findViewById(R.id.ArmorCategory);
        ArmorAC = findViewById(R.id.ArmorAC);
        ArmorCost = findViewById(R.id.ArmorCost);
        ArmorStealth = findViewById(R.id.ArmorStealth);
        ArmorStrMini = findViewById(R.id.ArmorStrMinimum);
        ArmorWeight = findViewById(R.id.ArmorWeight);

        WeaponStrMini = findViewById(R.id.WeaponStrMinimum);
        WeaponRange = findViewById(R.id.WeaponRange);
        WeaponDamage = findViewById(R.id.WeaponDamage);
        WeaponRangeType = findViewById(R.id.WeaponRangeType);
        WeaponCost = findViewById(R.id.WeaponCost);
        WeaponCategory = findViewById(R.id.WeaponCategory);
        PropertiesLBL = findViewById(R.id.PropertiesLbl);
        WeaponThrownRange = findViewById(R.id.WeaponThrownRange);
        PropertiesList = findViewById(R.id.PropertiesList);
        WeaponWeight = findViewById(R.id.WeaponWeight);

        AmmoWeight = findViewById(R.id.AmmoWeight);
        AmmoGearCat = findViewById(R.id.AmmoGearCat);
        AmmoCost = findViewById(R.id.AmmoCost);
        AmmoQuantity = findViewById(R.id.AmmoQuanitity);

        AGCost = findViewById(R.id.AGCost);
        AGDesc = findViewById(R.id.AGDesc);
        AGGearCat = findViewById(R.id.AGGearCat);
        AGWeight = findViewById(R.id.AGWeight);

        ToolsCost = findViewById(R.id.ToolsCost);
        ToolsDesc = findViewById(R.id.ToolsDesc);
        ToolCategory = findViewById(R.id.ToolCategory);
        ToolWeight = findViewById(R.id.ToolWeight);

        MAVCapacity = findViewById(R.id.MAVCapacity);
        MAVCost = findViewById(R.id.MAVCost);
        MAVSpeed = findViewById(R.id.MAVSpeed);
        MAVvehcat = findViewById(R.id.MAVvehcat);


        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        String a = ItemData.getString("name");
        TitleName.setText(a);
        String b = ItemData.getJSONObject("equipment_category").getString("name");
        EqCat.setText(b);

        SelectEquipType(ItemData.getJSONObject("equipment_category").getString("name"));
    }

    private void SelectEquipType(String Type) throws JSONException {
        switch (Type) {
            case "Armor":
                LoadArmor();
                break;
            case "Weapon":
                LoadWeapon();
                break;
            case "Adventuring Gear":
                try {
                    if (ItemData.getJSONObject("gear_category").getString("name").equals("Ammunition")) {
                        LoadAmmo();
                    } else {
                        LoadAdGear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorHandle(e, this);
                }
                break;
            case "Tools":
                LoadTools();
                break;
            case "Mounts and Vehicles":
                LoadMnV();
                break;
            default:
                LoadDefault();
                break;
        }

    }

    private void LoadArmor() throws JSONException {
        String a = "Armor Category: " + ItemData.getString("armor_category");
        ArmorCategory.setText(a);

        String b = "";
        if (ItemData.getJSONObject("armor_class").getBoolean("dex_bonus")) {
            if (!ItemData.getJSONObject("armor_class").isNull("max_bonus")) {
                String MaxBonus = "(Upto " + ItemData.getJSONObject("armor_class").getInt("max_bonus") + " Dex Bonus)";
                b = ItemData.getJSONObject("armor_class").getString("base") + " " + MaxBonus;
            }
        } else {
            b = ItemData.getJSONObject("armor_class").getString("base");
        }
        ArmorAC.setText(b);

        String c = "Cost: " + ItemData.getJSONObject("cost").getString("quantity") + " " + ItemData.getJSONObject("cost").getString("unit");
        ArmorCost.setText(c);

        String isDisAdvantaged = "";
        if (ItemData.getBoolean("stealth_disadvantage")) {
            isDisAdvantaged = "Yes";
        } else {
            isDisAdvantaged = "No";
        }

        String d = "Does it make you disadvantaged in stealth: " + isDisAdvantaged;
        ArmorStealth.setText(d);

        String e = "Minimum strength: " + ItemData.getString("str_minimum");
        ArmorStrMini.setText(e);

        String f = "Weight: " + ItemData.getInt("weight");
        ArmorWeight.setText(f);
    }

    private void LoadWeapon() throws JSONException {

        if (ItemData.getString("throw_range") == null) {
            String a = "Thrown Range: " + ItemData.getString("throw_range");
            WeaponThrownRange.setText(a);
        }

        String b = "Weapon Category: " + ItemData.getString("weapon_category");
        WeaponCategory.setText(b);

        String c = "Cost: " + ItemData.getJSONObject("cost").getInt("quantity") + " " + ItemData.getJSONObject("cost").getString("unit");
        WeaponCost.setText(c);

        String d = "Weapon Range: " + ItemData.getString("weapon_range");
        WeaponRangeType.setText(d);

        PropertiesLBL.setText("Properties");
        String e = ItemData.getJSONObject("damage").getString("damage_dice") + " " + ItemData.getJSONObject("damage").getJSONObject("damage_type").getString("name") + " Damage";
        WeaponDamage.setText(e);
        PopulateLists(PropertiesList, ItemData.getJSONArray("properties"));


        String LongRange = "";
        if (ItemData.getJSONObject("range").getString("long").equals("null")) {
            LongRange = "N/a";
        } else {
            LongRange = ItemData.getJSONObject("range").getString("long");
        }

        String f = "Range: " + ItemData.getJSONObject("range").getInt("normal") + "ft / " + LongRange;
        WeaponRange.setText(f);

        String g = "Weight: " + ItemData.getInt("weight");
        WeaponWeight.setText(g);

    }

    private void LoadAmmo() throws JSONException {

        String a = "Weight: " + ItemData.getString("weight");
        AmmoWeight.setText(a);
        String b = "Cost: " + ItemData.getJSONObject("cost").getInt("quantity") + " " + ItemData.getJSONObject("cost").getString("quantity");
        AmmoCost.setText(b);
        String c = "Ammo Quantity: " + ItemData.getString("quantity");
        AmmoQuantity.setText(c);
        String d = "Gear Category: " + ItemData.getJSONObject("gear_category").getString("name");
        AmmoGearCat.setText(d);
    }

    private void LoadAdGear() throws JSONException {

        String a = "Cost: " + ItemData.getJSONObject("cost").getInt("quantity") + ItemData.getJSONObject("cost").getString("unit");
        AGCost.setText(a);

        String b = ItemData.getString("desc");
        AGDesc.setText(b);

        String c = "Weight: " + ItemData.getInt("weight");
        AGWeight.setText(c);

        String d = "Gear Category: " + ItemData.getJSONObject("gear_category").getString("name");
        AGGearCat.setText(d);


    }

    private void LoadTools() throws JSONException {
        String a = "Cost: " + ItemData.getJSONObject("cost").getInt("quantity") + " " + ItemData.getJSONObject("cost").getInt("unit");
        ToolsCost.setText(a);

        String b = ItemData.getString("desc");
        ToolsDesc.setText(b);

        String c = "Tool Category: " + ItemData.getString("tool_category");
        ToolCategory.setText(c);

        String d = "Weight: " + ItemData.getInt("weight");
        ToolWeight.setText(d);


    }

    private void LoadMnV() throws JSONException {

        String a = "";
        MAVCost.setText(a);

        String b = "";

        String c = "";

        String d = "";


    }

    private void LoadDefault() {
        try {
            String a = ItemData.getString("name");
            TitleName.setText(a);
            String b = "Type: " + ItemData.getJSONObject("equipment_category").getString("name");
            EqCat.setText(b);
            ArrayList<String> keys = new ArrayList<>();
            Iterator<String> ItemKeys = ItemData.keys();
            while (ItemKeys.hasNext()) {
                keys.add(ItemKeys.next());
            }
            String d = "Cost: " + ItemData.getJSONObject("cost").getString("quantity").concat(" " + ItemData.getJSONObject("cost").getString("unit"));
            Cost.setText(d);


            if (ItemData.has("weight")) {
                String e = "Weight: " + ItemData.getString("weight");
                Weight.setText(e);
            } else {
                String e = "N/a";
                Weight.setText(e);
            }


            if ((ItemData.getJSONArray("desc").length() > 0)) {
                String f = ItemData.getJSONArray("desc").getString(0);
                Desc.setText(f);
            } else {
                String f = "This item has no recorded description for this item. Try to google for this perhaps?";
                Desc.setText(f);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }
    }

    @Override
    protected void LoadFetchedDatatoViews() {

    }

    @Override
    protected void StartAssocaiedFunction(int pos) {

    }

    @Override
    protected void PreloadData() {

    }

    @Override
    protected void AssignDataThreadFactory() {
        AssignDataThread = new Thread() {
            @Override
            public void run() {
                synchronized (LoadDataThread) {
                    while (!LoadedData) {
                        if (ItemData != null) {
                            try {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            SortDataToItems();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            ErrorHandle(e, getApplicationContext());
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                ErrorHandle(e, getApplicationContext());
                            }
                            break;
                        }

                    }
                }
            }
        };
        AssignDataThread.start();
    }
}
