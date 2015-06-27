package devang.developer.com.diseasetracker;

/**
 * Created by Dejucoder on 6/19/2015.
 */
        import java.util.ArrayList;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseExpandableListAdapter;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Group> groups;

    public ExpandListAdapter(Context context, ArrayList<Group> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Child child = (Child) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item_tab3, null);
        }
        TextView tvDisease = (TextView) convertView.findViewById(R.id.tvDisease);
        TextView tvPercent = (TextView) convertView.findViewById(R.id.tvPercent);
        TextView tv = (TextView) convertView.findViewById(R.id.textView8);
        if(Integer.parseInt(child.getPercent()) == 100)
            tv.setVisibility(View.GONE);
        final TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar7);
        tvDisease.setText(child.getName().toString());
        tvPercent.setText(child.getPercent().toString());
        tvDate.setText(child.getDate().toString());
        progressBar.setProgress(child.getProgress());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "ghj", Toast.LENGTH_SHORT).show();
                String x = tvDate.getText().toString();
                String mselectionClause = ReportListProvider.KEY_DATE + " = ?";
                String[] mselectionArgs = {""};
                mselectionArgs[0] = x;
                Cursor cursor = context.getContentResolver().query(ReportListProvider.CONTENT_URI, new String[]{
                        "_id", "disease1", "disease2", "disease3", "disease4", "disease5", "disease6", "disease7", "disease8", "disease9",
                        "disease10", "value1", "value2", "value3", "value4", "value5", "value6", "value7", "value8", "value9",
                        "value10", "rating", "note", "date", "year", "month"
                }, mselectionClause, mselectionArgs, null);
                while (cursor.moveToNext())
                {
                    Intent i = new Intent();
                    i.putExtra("date", cursor.getString(23));
                    i.putExtra("rating", cursor.getInt(21));
                    i.putExtra("note", cursor.getString(22));
                    int count=0;
                    for(int j=0;j<10;j++)
                    {
                        if(cursor.getInt(j+11) != 0)
                            count++;
                        else
                            break;
                    }
                    String[] diseases = new String[count];
                    int[] scores = new int[count];
                    for(int j=0;j<count;j++)
                    {
                        diseases[j] = cursor.getString(j+1);
                        scores[j] = cursor.getInt(j+11);
                    }
                    i.putExtra("diseases", diseases);
                    i.putExtra("scores", scores);
                    i.setClassName("devang.developer.com.diseasetracker", "devang.developer.com.diseasetracker.History");
                    context.startActivity(i);
                    Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvMonthYear);
        TextView tv1 = (TextView) convertView.findViewById(R.id.tvRecords);
        tv.setText(group.getName());
        tv1.setText(String.valueOf(group.getRecords()));
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}