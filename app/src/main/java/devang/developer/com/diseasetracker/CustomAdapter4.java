package devang.developer.com.diseasetracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class CustomAdapter4 extends BaseAdapter{
    String [] result;
    Context context;
    private static LayoutInflater inflater=null;
    public CustomAdapter4(Symptoms2 symptoms2, String[] prgmNameList) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=symptoms2;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        ImageButton img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_item3, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageButton) rowView.findViewById(R.id.img1);
        holder.tv.setText(result[position]);
        holder.img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String symptomToBeCancelled = holder.tv.getText().toString();
                int i = find_index_of_symptom(symptomToBeCancelled);
                values.selectedsymptoms[i] = 0;
                String[] ans = new String[result.length - 1];
                int k = 0;
                for (int j = 0; j < result.length;)
                {
                    int l = k;
                    if (j != position)
                    {
                        ans[k] = result[j];
                        l = k + 1;
                    }
                    j++;
                    k = l;
                }

                result = ans;
                notifyDataSetChanged();
            }
        });
        return rowView;
    }

    private int find_index_of_symptom(String s)
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

}