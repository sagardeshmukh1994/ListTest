package com.example.padcc.listtest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btngetdata;
    ProgressDialog pd;
    ListView listView;
    String TAG = "MainActivity";

    List<jsondata> jsondataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btngetdata = (Button) findViewById(R.id.btnHit);
        listView = (ListView) findViewById(R.id.listview1);

        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsynchTaskToGetData().execute("http://api.androidhive.info/contacts/");
            }
        });

    }


    private class AsynchTaskToGetData extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String jsonStr = null;
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(params[0]));
                HttpResponse response = client.execute(request);
                jsonStr = EntityUtils.toString(response.getEntity());
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }

            return jsonStr;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = new JSONArray();
                jsonArray = jsonObject.getJSONArray("contacts");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject data = jsonArray.getJSONObject(i);

                    jsondata jsondata = new jsondata();
                    jsondata.setUserid(data.getString("id"));
                    jsondata.setUsername(data.getString("name"));
                    jsondata.setUseremail(data.getString("email"));
                    jsondata.setUseraddress(data.getString("address"));
                    jsondata.setUsergender(data.getString("gender"));

                    JSONObject numbers = data.getJSONObject("phone");

                    jsondata.setUserphonemobile(numbers.getString("mobile"));
                    jsondata.setUserphonehome(numbers.getString("home"));
                    jsondata.setUserphoneoffice(numbers.getString("office"));
                    jsondataList.add(jsondata);
                }
                CustomAdapter customAdaptor = new CustomAdapter();
                listView.setAdapter(customAdaptor);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        class CustomAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return jsondataList.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {

                view = getLayoutInflater().inflate(R.layout.jsondataelements, null);

                TextView textViewid = (TextView) view.findViewById(R.id.textid);
                TextView textViewname = (TextView) view.findViewById(R.id.textname);
                TextView textViewemail = (TextView) view.findViewById(R.id.textemail);
                TextView textViewaddress = (TextView) view.findViewById(R.id.textaddress);
                TextView textViewgender = (TextView) view.findViewById(R.id.textgender);
                TextView textViewmobile = (TextView) view.findViewById(R.id.textmobile);
                TextView textViewhome = (TextView) view.findViewById(R.id.texthome);
                TextView textViewoffice = (TextView) view.findViewById(R.id.textoffice);

                textViewid.setText(jsondataList.get(position).getUserid());
                textViewname.setText(jsondataList.get(position).getUsername());
                textViewemail.setText(jsondataList.get(position).getUseremail());
                textViewaddress.setText(jsondataList.get(position).getUseraddress());
                textViewgender.setText(jsondataList.get(position).getUsergender());
                textViewmobile.setText(jsondataList.get(position).getUserphonemobile());
                textViewhome.setText(jsondataList.get(position).getUserphonehome());
                textViewoffice.setText(jsondataList.get(position).getUserphoneoffice());

                return view;
            }
        }


    }
}