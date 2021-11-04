package ca.bcit.le_liu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.gyf.immersionbar.ImmersionBar;

public class MainActivity extends AppCompatActivity implements DataBase.DatabaseLoadListener {
    private LinearLayout progressBar;
    private LinearLayout linearLayoutMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
        } else {
            setContentView(R.layout.activity_main);
        }

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            toLogin();
        });

        Button btnMonth = findViewById(R.id.btnMonth);
        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                startActivity(intent);
            }
        });

        Button btnHealth = findViewById(R.id.btnHealth);
        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HealthActivity.class);
                startActivity(intent);
            }
        });

        Button btnGender = findViewById(R.id.btnGender);
        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GenderActivity.class);
                startActivity(intent);
            }
        });

        Button btnAge = findViewById(R.id.btnAge);
        btnAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgeActivity.class);
                startActivity(intent);
            }
        });

        // Load data
        progressBar = findViewById(R.id.progressBar);
        linearLayoutMenu = findViewById(R.id.menu);
        DataBase.getInstance().loadData(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
        } else {
            setContentView(R.layout.activity_main);
        }
        super.onConfigurationChanged(newConfig);
    }

    private void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onLoadFinish() {
        progressBar.setVisibility(View.GONE);
        linearLayoutMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFail() {
        progressBar.setVisibility(View.GONE);
        ((TextView)findViewById(R.id.info_one)).setText("Failed to load data");
        ((TextView)findViewById(R.id.info_two)).setText("Check the connection and restart the app");
    }
}