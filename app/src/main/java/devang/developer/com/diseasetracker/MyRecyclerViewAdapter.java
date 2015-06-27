package devang.developer.com.diseasetracker;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView disease_label;
        TextView risk_label;
        RoundCornerProgressBar progress_label;
        TextView progress;

        public DataObjectHolder(View itemView) {
            super(itemView);
            disease_label = (TextView) itemView.findViewById(R.id.textView24);
            risk_label = (TextView) itemView.findViewById(R.id.textView25);
            progress_label=(RoundCornerProgressBar)itemView.findViewById(R.id.progressBar6);
            progress = (TextView)itemView.findViewById(R.id.textView28);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.disease_label.setText(mDataset.get(position).getmText1());
        holder.risk_label.setText(mDataset.get(position).getmText2());
        holder.progress_label.setProgress(mDataset.get(position).getmProgress());
        holder.progress.setText("(" + String.valueOf(mDataset.get(position).getmProgress()) + "%)");
        if(mDataset.get(position).getmProgress() >= 80)
        {
            holder.progress_label.setProgressColor(Color.parseColor("#B71C1C"));
            holder.risk_label.setTextColor(Color.parseColor("#B71C1C"));
            holder.progress.setTextColor(Color.parseColor("#B71C1C"));
        }
        else if(mDataset.get(position).getmProgress() >= 50)
        {
            holder.progress_label.setProgressColor(Color.parseColor("#ff9800"));
            holder.risk_label.setTextColor(Color.parseColor("#ff9800"));
            holder.progress.setTextColor(Color.parseColor("#ff9800"));
        }
        else
        {
            holder.progress_label.setProgressColor(Color.parseColor("#8bc34a"));
            holder.risk_label.setTextColor(Color.parseColor("#8bc34a"));
            holder.progress.setTextColor(Color.parseColor("#8bc34a"));
        }
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}