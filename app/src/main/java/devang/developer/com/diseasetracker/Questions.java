package devang.developer.com.diseasetracker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import is.arontibo.library.ElasticDownloadView;





public class Questions extends ActionBarActivity {


    Button yes,no,dontknow;
    TextView question;
    ElasticDownloadView progressBar;
    ProgressBar spinner;
    private static int SPLASH_TIME_OUT_1 = 1500;
    private static int SPLASH_TIME_OUT_2 = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        setSupportActionBar((Toolbar) findViewById(R.id.tool_bar2));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar=(ElasticDownloadView)findViewById(R.id.progressBar3);
        progressBar.startIntro();
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        float x = (float)values.counter / (float)values.toBeAskedSymptoms.length;
        int y = (int)(50*x);
        progressBar.setProgress(50 + y);
        question = (TextView)findViewById(R.id.textView23);
        question.setText(values.questions[values.toBeAskedSymptoms[values.counter]]);
        yes=(Button)findViewById(R.id.button5);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = values.toBeAskedSymptoms[values.counter];
                values.selectedsymptoms[i] = 1;
                values.counter++;
                if (values.counter < values.toBeAskedSymptoms.length)
                {
                    spinner.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Questions.this, Questions.class));
                            spinner.setVisibility(View.GONE);
                        }
                    }, SPLASH_TIME_OUT_1);
                } else
                {
                    update_disease_scores();
                    make_disease_list();
                    spinner.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.success();
                        }
                    }, SPLASH_TIME_OUT_1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Questions.this, Results.class));
                            finish();
                            spinner.setVisibility(View.GONE);
                        }
                    }, SPLASH_TIME_OUT_2);
                }
            }
        });
        dontknow=(Button)findViewById(R.id.button6);
        dontknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.counter++;
                if (values.counter < values.toBeAskedSymptoms.length)
                {
                    spinner.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Questions.this, Questions.class));
                            spinner.setVisibility(View.GONE);
                        }
                    }, SPLASH_TIME_OUT_1);
                } else
                {
                    update_disease_scores();
                    make_disease_list();
                    spinner.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.success();
                        }
                    }, SPLASH_TIME_OUT_1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Questions.this, Results.class));
                            spinner.setVisibility(View.GONE);
                            finish();
                        }
                    }, SPLASH_TIME_OUT_2);
                }
            }
        });
        no=(Button)findViewById(R.id.button7);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.counter++;
                if (values.counter < values.toBeAskedSymptoms.length)
                {
                    spinner.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Questions.this, Questions.class));
                            spinner.setVisibility(View.GONE);
                        }
                    }, SPLASH_TIME_OUT_2);
                } else
                {
                    update_disease_scores();
                    make_disease_list();
                    spinner.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.success();
                        }
                    }, SPLASH_TIME_OUT_1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Questions.this, Results.class));
                            spinner.setVisibility(View.GONE);
                            finish();
                        }
                    }, SPLASH_TIME_OUT_2);
                }
            }
        });
    }
    private void make_disease_list()
    {
        int k = 0;
        boolean flag = false;
        for (int i = 0; i < values.disease_score.length;)
        {
            int i1 = k;
            if (values.disease_score[i] >= 0.2)
            {
                i1 = k + 1;
            }
            i++;
            k = i1;
        }

        String as[] = new String[k];
        int ai[] = new int[k];
        int j = 0;
        int j1;
        for (int l = ((flag) ? 1 : 0); j < values.disease_score.length; l = j1)
        {
            j1 = l;
            if (values.disease_score[j] >= 0.2)
            {
                as[l] = values.diseases[j];
                ai[l] = (int)(values.disease_score[j] * 100);
                j1 = l + 1;
            }
            j++;
        }

        values.finalDiseases = as;
        values.finalScores = ai;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(values.counter != 0)
            values.counter -= 1;
    }

    private void update_disease_scores()
    {
        for (int i = 0; i < values.disease_score.length; i++)
        {
            values.disease_score[i] = 0.0;
        }

        for (int j = 0; j < values.disease_score.length; j++)
        {
            for (int k = 0; k < values.disease_info[j].length; k++)
            {
                if (values.selectedsymptoms[values.disease_info[j][k]] == 1)
                {
                    double ad[] = values.disease_score;
                    ad[j] = ad[j] + values.symptom_score[j][k];
                }
            }

        }

    }
}
