package com.demo.sumit.prac4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demo.sumit.prac4.classes.ServiceApi;
import com.demo.sumit.prac4.model.LoginDemoData;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRetrofitData();
        getStringReq();
    }

    private void getStringReq() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ServiceApi.Url + ServiceApi.Key, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("unique_id","1");
                params.put("app_version","1");
                params.put("email","sumitkumarsah589@gmail.com");
                params.put("password","4178");
                params.put("type","1");
                return params;
            }
        };
        addReqQue(stringRequest,MainActivity.this);
    }

    private void addReqQue(StringRequest stringRequest, Context context) {
        int stockTimeOut = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(stockTimeOut,-1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        stringRequest.setShouldCache(true);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void getRetrofitData() {

        Call<LoginDemoData> demo_data = ServiceApi.getPostService().getLoginData("1","1","sumitkumarsah589@gmail.com","4178","1");
        demo_data.enqueue(new Callback<LoginDemoData>() {
            @Override
            public void onResponse(Call<LoginDemoData> call, Response<LoginDemoData> response) {
                LoginDemoData data = response.body();
                Toast.makeText(MainActivity.this, ""+data.getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginDemoData> call, Throwable t) {

            }
        });
    }
}
