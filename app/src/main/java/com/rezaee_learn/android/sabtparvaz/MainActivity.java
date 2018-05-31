package com.rezaee_learn.android.sabtparvaz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.*;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements SimpleListAdapter.Listener {

    private List<TravelModel> list = new ArrayList<>();
    private ProgressDialog pd;
    private ArrayAdapter<TravelModel> arrayAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////////////////////////////////////////////////////////////////////////////
        recyclerView = (RecyclerView) findViewById(R.id.rv_activityMain);

        SimpleListAdapter adapter = new SimpleListAdapter(this, getTravelData(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
//////////////////////////////////////////////////////////////////////
    private List<TravelModel> getTravelData() {
        String url = "http://192.168.1.103:8080//index.php";
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, "اطلاعات دریافت شد", Toast.LENGTH_SHORT).show();

                        try {

                            JSONArray jsonArray = response.getJSONArray("travel");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject TravelData = jsonArray.getJSONObject(i);

                                TravelModel model = new TravelModel();
                                model.cod = TravelData.getString("cod");
                                model.CompanyName = TravelData.getString("companyname");
                                model.begn = TravelData.getString("begn");
                                model.stop = TravelData.getString("stop");
                                model.date_f = TravelData.getString("date_f");
                                model.time_f = TravelData.getString("time_f");
                                model.price = TravelData.getString("price");

                                list.add(model);
                            }

                            //   arrayAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "مشکل در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                        Log.e("error" , error.toString());
                        // hide the progress dialog
                        pDialog.dismiss();
                    }
                }


        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        return list;
    }


    @Override
    public void onPresser(String cod) {
        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra("code",cod);
        startActivity(intent);
    }

    @Override
    public void onHold() {

    }

    @Override
    public void onLoadMore() {

    }

//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()) {
//            case R.id.btn:
//                Toast.makeText(getApplicationContext(), "btn", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btn2:
//                Toast.makeText(getApplicationContext(), "btn2", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btn3:
//                Toast.makeText(getApplicationContext(), "btn3", Toast.LENGTH_SHORT).show();
//                break;
//
//        }


//    }
}

