package com.example.alec.phase_05.Client.Model;

/**
 * Created by Andrew on 2/8/2017.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 2/3/2017.
 */

public class DerpData {
    public static final String[] titles = {"Nothingness cannot be defined", "The softest thing cannot be snapped",
            "Be like water, my friend."};
    public static final int[] icons = {android.R.drawable.ic_popup_reminder, android.R.drawable.ic_menu_add,
            android.R.drawable.ic_menu_delete};

    public static List<ListItem> getListData()
    {
        List<ListItem> data = new ArrayList<>();

        for(int x = 0; x < 4; x++)
        {
            for(int i = 0; i < titles.length && i < icons.length; i++)
            {
                ListItem item = new ListItem();
                item.setImageResId(icons[i]);
                item.setTitle(titles[i]);
                data.add(item);
            }
        }

        return data;
    }

}
