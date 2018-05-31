package com.rezaee_learn.android.sabtparvaz;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SecondActivity extends AppCompatActivity {
   // private SimpleListAdapter.Listener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //listener.onPresser( 2);
        TextView txt_code= (TextView) findViewById(R.id.txt_activitySecond_codeParvaz);
        Bundle input=getIntent().getExtras();
        txt_code.setText(input.getString("code"));

    }

    public void btn_sabtBleit_onClick(View view) {

        String url = "http://192.168.1.103:8080//post.php";
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(SecondActivity.this,"ارسال اطلاعات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();


                        pDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SecondActivity.this, "مشکل در ارسال اطلاعات", Toast.LENGTH_SHORT).show();
                        // hide the progress dialog
                        pDialog.dismiss();
                    }

                }


        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                TextView txt_name= (TextView) findViewById(R.id.etx_activitySecond_name);
                TextView txt_codemelli= (TextView) findViewById(R.id.etxt_activitySecond_codeMelli);
                TextView txt_phone= (TextView) findViewById(R.id.etx_activitySecond_telephone);
                TextView txt_code= (TextView) findViewById(R.id.txt_activitySecond_codeParvaz);

                params.put("name" , txt_name.getText().toString());
                params.put("codemelli" , txt_codemelli.getText().toString());
                params.put("phone" , txt_phone.getText().toString());
                params.put("code" , txt_code.getText().toString());
                return params;
            }
        };
    }
    public class Test{
        private SimpleListAdapter.Listener listener;
        public Test(SimpleListAdapter.Listener listener){
            this.listener=listener;
        }
        private void get(){
            listener.onPresser("1");
        }
    }
}
