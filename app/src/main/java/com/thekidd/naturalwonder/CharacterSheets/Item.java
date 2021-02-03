package com.thekidd.naturalwonder.CharacterSheets;

public class Item {

    private String Type;
    private String Title;

    Item(String Title, String Type) {
        this.Title = Title;
        this.Type = Type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
