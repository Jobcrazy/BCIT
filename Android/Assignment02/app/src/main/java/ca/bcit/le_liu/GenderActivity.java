package ca.bcit.le_liu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.gyf.immersionbar.ImmersionBar;

import java.util.HashMap;
import java.util.Map;

public class GenderActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    Integer maleCounter = 0;
    Integer femaleCounter = 0;
    Integer otherCounter = 0;
    TextView male;
    TextView female;
    TextView other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        ImmersionBar.with(this).init();

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);

        for (Person p: DataBase.getInstance().getPersons()){
            switch (p.Sex){
                case "M": maleCounter++;continue;
                case "F": femaleCounter++;continue;
                default: otherCounter++;
            }
        }

        male.setText("Male: " + maleCounter);
        female.setText("Female: " + femaleCounter);
        other.setText("Other: " + otherCounter);
    }
}