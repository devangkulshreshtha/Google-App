package devang.developer.com.diseasetracker;

import android.content.Context;
import android.content.Intent;
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
public class CustomAdapterRisk extends BaseAdapter{
    String [] result;
    Context context;
    private static LayoutInflater inflater=null;
    public CustomAdapterRisk(RiskFactors riskFactors, String[] prgmNameList) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=riskFactors;
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
        ImageButton cb;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_item2, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.cb=(ImageButton) rowView.findViewById(R.id.checkBox1);
        holder.tv.setText(result[position]);
        if(values.selected_risk_factors[position] == 1)
            holder.cb.setImageResource(R.drawable.tick);
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cb.getDrawable() == null) {
                    values.selected_risk_factors[position] = 1;
                    holder.cb.setImageResource(R.drawable.tick);
                } else {
                    values.selected_risk_factors[position] = 0;
                    holder.cb.setImageDrawable(null);
                }
            }
        });
        return rowView;
    }

}