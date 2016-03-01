package com.codepath.simpletodo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mazino on 3/1/2016.
 */
public class ItemDAO {
    List<Item> mItemList = new ArrayList<>();

    public List<Item> getItemList() {
        return mItemList;
    }
}
