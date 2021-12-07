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

public class DinoFragment extends Fragment {
    static final String SERVICE_URL = "https://portal.stgeorge.ca/dino.json";
    private RequestQueue _requestQueue;

    TextView tvId, tvOccupation, tvName;
    ImageView ivPicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dino, container, false);

        tvId = v.findViewById(R.id.tvId);
        tvOccupation = v.findViewById(R.id.tvOccupation);
        tvName = v.findViewById(R.id.tvName);
        ivPicture = v.findViewById(R.id.ivPicture);

        StringRequest stringRequest = new StringRequest(SERVICE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int id = jsonObject.getInt("id");
                    String occupation = jsonObject.getString("occupation");
                    String name = jsonObject.getString("firstName") + " "
                            + jsonObject.getString("lastName");
                    String avatarUrl = jsonObject.getString("pictureUrl");

                    tvOccupation.setText(occupation);
                    tvId.setText(String.valueOf(id));
                    tvName.setText(name);

                    Picasso.with(DinoFragment.this.getContext())
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