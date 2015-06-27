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

public class ExpandListAdapter2 extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Group2> groups;

    public ExpandListAdapter2(Context context, ArrayList<Group2> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child2> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Child2 child = (Child2) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item2, null);
        }
        TextView tvDisease = (TextView) convertView.findViewById(R.id.tvDisease);
        TextView tvPercent = (TextView) convertView.findViewById(R.id.tvPercent);
        TextView tvSymptoms = (TextView) convertView.findViewById(R.id.tvSymptoms);
        ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar7);
        tvDisease.setText(child.getName().toString());
        tvPercent.setText(child.getPercent().toString());
        tvSymptoms.setText(child.getSymptomsmatched().toString());
        progressBar.setProgress(child.getProgress());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child2> chList = groups.get(groupPosition).getItems();
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
        Group2 group = (Group2) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item2, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.name);
        tv.setText(group.getName());
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