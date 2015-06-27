package devang.developer.com.diseasetracker;

/**
 * Created by Dejucoder on 6/24/2015.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyViewPagerAdapter extends PagerAdapter {

    private Activity _activity;
    private String[] _diseases;
    private int[] _scores;
    private LayoutInflater inflater;
    public static View viewLayout;

    // constructor
    public MyViewPagerAdapter(Activity activity,
                                  String[] diseases, int[] scores) {
        this._activity = activity;
        this._diseases = diseases;
        this._scores = scores;
    }

    @Override
    public int getCount() {
        return this._diseases.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView name;
        CustomProgress customProgress;

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewLayout = inflater.inflate(R.layout.swipe_layout, container,
                false);

        name = (TextView) viewLayout.findViewById(R.id.textView4);
        customProgress = (CustomProgress) viewLayout.findViewById(R.id.customProgress);
        name.setText(_diseases[position]);
        float x = (float)(((float)_scores[position])/100.0);
        customProgress.setMaximumPercentage(x);
        customProgress.setShowingPercentage(true);
        ((ViewPager) container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
