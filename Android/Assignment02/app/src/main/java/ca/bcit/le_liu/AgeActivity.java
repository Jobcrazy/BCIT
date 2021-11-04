package ca.bcit.le_liu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class AgeActivity extends AppCompatActivity {
    private RecyclerView counterList;
    private ArrayList<Counter> counters = new ArrayList<>();
    private TreeMap<String, Integer> mapCounter = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        ImmersionBar.with(this).init();

        counterList = findViewById(R.id.recList);
        counterList.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        loadData();
    }

    private void loadData() {
        counters.clear();
        mapCounter.clear();

        for (Person p : DataBase.getInstance().getPersons()) {
            if (mapCounter.get(p.Age_Group) != null) {
                Integer currentNumber = mapCounter.get(p.Age_Group);
                mapCounter.put(p.Age_Group, currentNumber + 1);
            } else {
                mapCounter.put(p.Age_Group, 1);
            }
        }

        for (String key: mapCounter.keySet()){
            Counter counter = new Counter(key, mapCounter.get(key).toString());
            counters.add(counter);
        }

        counterList.setAdapter(new CounterAdapter(counters));
    }
}