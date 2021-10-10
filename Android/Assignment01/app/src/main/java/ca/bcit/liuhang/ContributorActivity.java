package ca.bcit.liuhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class ContributorActivity extends AppCompatActivity {
    private final HashMap<String, String> urlMap = new HashMap<>();
    private ArrayList<Contributor> contributors;
    private ContributorAdapter contributorAdapter;
    private RecyclerView rvContributorList;
    private RequestQueue requestQueue;

    public ContributorActivity() {
        urlMap.put("Vue", "https://api.github.com/repos/vuejs/vue/contributors");
        urlMap.put("Tensorflow", "https://api.github.com/repos/tensorflow/tensorflow/contributors");
        urlMap.put("Bootstrap", "https://api.github.com/repos/twbs/bootstrap/contributors");
        urlMap.put("React", "https://api.github.com/repos/facebook/react/contributors");
        urlMap.put("Flutter", "https://api.github.com/repos/flutter/flutter/contributors");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);

        rvContributorList = findViewById(R.id.contributors);
        if (null == rvContributorList) {
            return;
        }

        rvContributorList.setHasFixedSize(true);
        //LinearLayoutManager lm = new LinearLayoutManager(this);
        GridLayoutManager lm = new GridLayoutManager(this, 2);
        rvContributorList.setLayoutManager(lm);

        requestQueue = Volley.newRequestQueue(this);
        queueParseJSON();
    }

    private void queueParseJSON() {
        String project = getIntent().getStringExtra("project");
        String url = urlMap.get(project);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    String jsonStr = "{\"contributors\":" + response.toString() + "}";
                    Gson gson = new Gson();
                    BaseContributor baseContributor = gson.fromJson(jsonStr, BaseContributor.class);
                    contributors = baseContributor.getContributors();
                    contributorAdapter = new ContributorAdapter(ContributorActivity.this, contributors);
                    rvContributorList.setAdapter(contributorAdapter);
                }, Throwable::printStackTrace
        );

        requestQueue.add(request);
    }
}