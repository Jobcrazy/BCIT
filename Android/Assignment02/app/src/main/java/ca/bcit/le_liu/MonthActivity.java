package ca.bcit.le_liu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;


public class MonthActivity extends AppCompatActivity {
    private RecyclerView personList;
    private ArrayList<Person> persons = new ArrayList<>();
    private EditText editYear;
    private EditText editMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        ImmersionBar.with(this).init();

        personList = findViewById(R.id.recList);
        personList.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        editYear = findViewById(R.id.edtYear);
        editMonth = findViewById(R.id.edtMonth);
        editYear.setText("2020");
        editMonth.setText("11");

        Button btnSearch = findViewById(R.id.btnFind);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, 0);
                loadData();
            }
        });

        loadData();
    }

    private void loadData() {
        persons.clear();
        String month = editMonth.getText().toString();
        if (month.length() == 1) {
            month = "0" + month;
        }
        String date = editYear.getText().toString() + "-" + month;
        for (Person p : DataBase.getInstance().getPersons()) {
            String dataMonth = p.Reported_Date.substring(0, 7);
            if (dataMonth.equals(date)) {
                persons.add(p);
            }
        }
        personList.setAdapter(new PersonAdapter(persons));
    }
}