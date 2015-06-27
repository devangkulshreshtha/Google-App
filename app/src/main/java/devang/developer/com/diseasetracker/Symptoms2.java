package devang.developer.com.diseasetracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class Symptoms2 extends ActionBarActivity implements View.OnClickListener{
    ListView lv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms2);
        button=(Button)findViewById(R.id.button3);
        button.setOnClickListener(this);
        setSupportActionBar((Toolbar) findViewById(R.id.tool_bar2));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        lv = (ListView)findViewById(R.id.listView);
        String[] symptoms = getselectedSymptoms();
        lv.setAdapter(new CustomAdapter4(this, symptoms));

    }
    private String[] getselectedSymptoms()
    {
        int k = 0;
        for (int i = 1; i < values.selectedsymptoms.length;)
        {
            int l = k;
            if (values.selectedsymptoms[i] == 1)
            {
                l = k + 1;
            }
            i++;
            k = l;
        }

        String as[] = new String[k];
        k = 0;
        for (int j = 1; j < values.selectedsymptoms.length;)
        {
            int i1 = k;
            if (values.selectedsymptoms[j] == 1)
            {
                as[k] = values.symptoms[j];
                i1 = k + 1;
            }
            j++;
            k = i1;
        }

        return as;
    }

    @Override
    public void onClick(View v) {
        int i;
        for(i=0;i<values.selectedsymptoms.length;i++)
        {
            if(values.selectedsymptoms[i] == 1)
                break;;
        }
        if(i == values.selectedsymptoms.length)
            Toast.makeText(this, "No symptoms yet", Toast.LENGTH_SHORT).show();
        else
            startActivity(new Intent(Symptoms2.this, RiskFactors.class));
    }
}
