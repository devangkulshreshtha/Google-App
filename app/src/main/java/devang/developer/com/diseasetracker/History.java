package devang.developer.com.diseasetracker;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lt.lemonlabs.android.expandablebuttonmenu.ExpandableButtonMenu;
import lt.lemonlabs.android.expandablebuttonmenu.ExpandableMenuOverlay;


public class History extends ActionBarActivity {
    TextView  tvDate,tvTime;
    MyViewPagerAdapter adapter;
    ViewPager pager;
    TextView tvrating,tvnote;
    ImageView ivrate,ivnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history) ;
        setSupportActionBar((Toolbar) findViewById(R.id.tool_bar2));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvDate = (TextView)findViewById(R.id.textView5);
        tvTime = (TextView)findViewById(R.id.textView6);
        tvrating = (TextView)findViewById(R.id.tvrating);
        tvnote = (TextView)findViewById(R.id.tvnote);
        Intent intent = getIntent();
        String date="";
        String time="";
        ivnote=(ImageView)findViewById(R.id.ivnote);
        ivrate=(ImageView)findViewById(R.id.ivrate);
        int rating = intent.getIntExtra("rating",0);
        String note = intent.getStringExtra("note");
        if(rating!=0 && !note.equals(""))
        {
            String[] ratings = {"Poor", "Soso", "Good", "Excellent", "Outstanding"};
            tvrating.setText(ratings[rating - 1]);
            tvnote.setText(note);
        }
        else if(rating == 0 && !note.equals(""))
        {
            ivnote.setVisibility(View.INVISIBLE);
            tvnote.setVisibility(View.INVISIBLE);
            ivrate.setImageResource(R.drawable.note);
            tvrating.setText(note);
        }
        else if(rating!=0 && note.equals(""))
        {
            String[] ratings = {"Poor", "Soso", "Good", "Excellent", "Outstanding"};
            ivnote.setVisibility(View.INVISIBLE);
            tvnote.setVisibility(View.INVISIBLE);
            tvrating.setText(ratings[rating - 1]);
        }
        else
        {
            ivnote.setVisibility(View.INVISIBLE);
            tvnote.setVisibility(View.INVISIBLE);
            ivrate.setVisibility(View.INVISIBLE);
            tvrating.setVisibility(View.INVISIBLE);
        }

        String both = intent.getStringExtra("date");
        for(int i=0;i<10;i++)
        {
            date += both.charAt(i);
        }
        for(int i=11;i<both.length();i++)
        {
            time += both.charAt(i);
        }
        tvDate.setText(date);
        tvTime.setText(time);
        String[] diseases = intent.getStringArrayExtra("diseases");
        int[] scores = intent.getIntArrayExtra("scores");
        adapter = new MyViewPagerAdapter(History.this, diseases, scores);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearLayout3);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)linearLayout.getLayoutParams();
        //values.height = MyViewPagerAdapter.viewLayout.getLayoutParams().height;
        params.setMargins(0,230, 0,0);
        Toast.makeText(this, String.valueOf(values.height), Toast.LENGTH_SHORT).show();
        linearLayout.setLayoutParams(params);
    }

}
