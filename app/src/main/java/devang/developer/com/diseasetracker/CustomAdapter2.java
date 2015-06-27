package devang.developer.com.diseasetracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class CustomAdapter2 extends BaseAdapter{
    String [] result;
    Context context;
    int problemindex;
    private static LayoutInflater inflater=null;
    public CustomAdapter2(SymptomsDetail symptomsDetail, String[] prgmNameList, int i) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=symptomsDetail;
        problemindex=i;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public int find_index_of_symptom(String s)
    {
        for (int i = 1; i < values.symptoms.length; i++)
        {
            if (values.symptoms[i].equalsIgnoreCase(s))
            {
                return i;
            }
        }

        return 0;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public class Holder
    {
        TextView tv;
        ImageButton cb;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View rowView;
        final Holder holder = new Holder();
        rowView = inflater.inflate(R.layout.list_item2, null);
        holder.tv =(TextView) rowView.findViewById(R.id.textView1);
        holder.cb =(ImageButton) rowView.findViewById(R.id.checkBox1);
        holder.tv.setText(result[position]);
        int symptom_index = getSymptomIndex(holder.tv.getText().toString());
        if(values.selectedsymptoms[symptom_index] == 1)
        {
            holder.cb.setImageResource(R.drawable.tick);
        }
        rowView.setOnClickListener(new OnClickListener() {
            String symptom = holder.tv.getText().toString();

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (holder.cb.getDrawable()==null) {
                    int j = find_index_of_symptom(symptom);
                    values.selectedsymptoms[j] = 1;
                    holder.cb.setImageResource(R.drawable.tick);
                } else {
                    int k = find_index_of_symptom(symptom);
                    values.selectedsymptoms[k] = 0;
                    holder.cb.setImageDrawable(null);
                }
            }
        });
        holder.cb.setOnClickListener(new OnClickListener() {
            String symptom = holder.tv.getText().toString();

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (holder.cb.getDrawable()==null) {
                    int j = find_index_of_symptom(symptom);
                    values.selectedsymptoms[j] = 1;
                    holder.cb.setImageResource(R.drawable.tick);
                } else {
                    int k = find_index_of_symptom(symptom);
                    values.selectedsymptoms[k] = 0;
                    holder.cb.setImageDrawable(null);
                }
            }
        });
        return rowView;
    }

    private int getSymptomIndex(String s) {
        for(int i=0;i<values.symptoms.length;i++)
        {
            if(values.symptoms[i].equalsIgnoreCase(s))
                return i;
        }
        return 0;
    }

}