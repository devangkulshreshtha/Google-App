package devang.developer.com.diseasetracker;

import java.util.ArrayList;

/**
 * Created by Dejucoder on 6/19/2015.
 */
public class Group2 {
    private ArrayList<Child2> Items;
    private String Name;

    public ArrayList<Child2> getItems()
    {
        return Items;
    }

    public String getName()
    {
        return Name;
    }

    public void setItems(ArrayList<Child2> arraylist)
    {
        Items = arraylist;
    }

    public void setName(String s)
    {
        Name = s;
    }
}