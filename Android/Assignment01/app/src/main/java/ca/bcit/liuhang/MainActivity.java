package ca.bcit.liuhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spnProject = findViewById(R.id.spn_projects);
        Button btnEnter = findViewById(R.id.btn_enter);

        if (null != btnEnter && null != spnProject) {
            btnEnter.setOnClickListener(view -> {
                String strProject = spnProject.getSelectedItem().toString();
                Intent intent = new Intent(this, ContributorActivity.class);
                intent.putExtra("project", strProject);
                startActivity(intent);
            });
        }
    }
}