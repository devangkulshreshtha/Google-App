package devang.developer.com.diseasetracker;


public class DataObject
{

    public int mProgress;
    public String mText1;
    public String mText2;

    DataObject(String s, String s1, int i)
    {
        mText1 = s;
        mText2 = s1;
        mProgress = i;
    }

    public int getmProgress()
    {
        return mProgress;
    }

    public String getmText1()
    {
        return mText1;
    }

    public String getmText2()
    {
        return mText2;
    }
}