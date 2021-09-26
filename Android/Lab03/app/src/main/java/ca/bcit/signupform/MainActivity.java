package ca.bcit.signupform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onConstraintClick(View v) {
        Intent i = new Intent(this, ConstraintActivity.class);
        startActivity(i);
    }

    public void onLinearClick(View view) {
        Intent i = new Intent(this, LinearActivity.class);
        startActivity(i);
    }

    public void onRelativeClick(View view) {
        Intent i = new Intent(this, RelativeActivity.class);
        startActivity(i);
    }

    public void onGridClick(View view) {
        Intent i = new Intent(this, GridActivity.class);
        startActivity(i);
    }

    public void onTableClick(View view) {
        Intent i = new Intent(this, TableActivity.class);
        startActivity(i);
    }

}