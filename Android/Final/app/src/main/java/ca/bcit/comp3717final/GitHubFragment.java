package ca.bcit.comp3717final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class GitHubFragment extends Fragment {
    static final String SERVICE_URL = "https://api.github.com/users/jobcrazy";
    private RequestQueue _requestQueue;
    TextView tvId, tvLogin, tvName, tvPublicRepos;
    ImageView ivPicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_git_hub, container, false);

        tvId = v.findViewById(R.id.tvId);
        tvLogin = v.findViewById(R.id.tvLogin);
        tvName = v.findViewById(R.id.tvName);
        ivPicture = v.findViewById(R.id.ivPicture);
        tvPublicRepos = v.findViewById(R.id.tvPublicRepos);

        StringRequest stringRequest = new StringRequest(SERVICE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int id = jsonObject.getInt("id");
                    String login = jsonObject.getString("login");
                    String name = jsonObject.getString("name");
                    String avatarUrl = jsonObject.getString("avatar_url");
                    String publicRepos = jsonObject.getString("public_repos");

                    tvLogin.setText(login);
                    tvId.setText(String.valueOf(id));
                    tvName.setText(name);
                    tvPublicRepos.setText(publicRepos);

                    Picasso.with(GitHubFragment.this.getContext())
                            .load(avatarUrl)
                            .fit()
                            .centerInside()
                            .into(ivPicture);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Anything you want
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        return v;
    }
}