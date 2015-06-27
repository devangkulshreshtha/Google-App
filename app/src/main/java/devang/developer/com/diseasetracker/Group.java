package devang.developer.com.diseasetracker;

import java.util.ArrayList;

/**
 * Created by Dejucoder on 6/19/2015.
 */
public class Group {
    private ArrayList<Child> Items;
    private String Name;
    private int records;
    public ArrayList<Child> getItems()
    {
        return Items;
    }

    public String getName()
    {
        return Name;
    }

    public void setItems(ArrayList<Child> arraylist)
    {
        Items = arraylist;
    }

    public void setName(String s)
    {
        Name = s;
    }

    public void setRecords(int records1) { records = records1; }

    public  int getRecords() { return records; }
}
