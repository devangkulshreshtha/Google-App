package devang.developer.com.diseasetracker;

/**
 * Created by Dejucoder on 6/19/2015.
 */
public class Child2 {
    private String number_of_symptoms_matched;
    private int id;
    private String name;
    private String percent;
    private int progress;

    public String getSymptomsmatched() {
        return number_of_symptoms_matched;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPercent() {
        return percent;
    }

    public int getProgress() {
        return progress;
    }

    public void setSymptomsmatched(String s) {
        number_of_symptoms_matched = s;
    }

    public void setId(int i) {
        id = i;
    }

    public void setName(String s) {
        name = s;
    }

    public void setPercent(String s) {
        percent = s;
    }

    public void setProgress(int i) {
        progress = i;
    }
}
