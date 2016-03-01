package com.codepath.simpletodo.model;

/**
 * Created by Mazino on 3/1/2016.
 */
public class Item {
    private long itemId;

    public long getItemId() {
        return itemId;
    }

    private String itemName;

    public Item() {
    }

    public Item(String name) {
        itemName = name;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
