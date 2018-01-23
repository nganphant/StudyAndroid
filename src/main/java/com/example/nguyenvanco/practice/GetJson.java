package com.example.nguyenvanco.practice;

/**
 * Created by NGUYEN VAN CO on 12/30/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetJson extends AppCompatActivity {
    int x = 1;
    public Thread a;
    URLConnection urlConn = null;
    BufferedReader bufferedReader = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


              //  DownloadAsyncTask task = new DownloadAsyncTask();
                //task.execute("https://jsonplaceholder.typicode.com/posts/");
                //x++;
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    URL url = new URL("https://jsonplaceholder.typicode.com/posts/");
                    urlConn = url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    while ((line = bufferedReader.readLine()) != null)
                    {
                        stringBuffer.append(line);
                    }
                    System.out.println("nvc------stringBuffer.toString()-----:" + stringBuffer.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally
                {
                    if(bufferedReader != null)
                    {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        thread.start();

    }


    public class DownloadAsyncTask extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog progressDialog  = new ProgressDialog(GetJson.this);;
        InputStream inputStream = null;
        String result = "";
        //    progressDialog = new ProgressDialog(MainActivity.this);
        protected void onPreExecute() {
            progressDialog.setMessage("Downloading your data...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    DownloadAsyncTask.this.cancel(true);
                }
            });
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            URLConnection urlConn = null;
            BufferedReader bufferedReader = null;
            try
            {
                URL url = new URL(params[0]);
                urlConn = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuffer.append(line);
                }
                System.out.println("nvc------stringBuffer.toString()-----:" + stringBuffer.toString());
                return new JSONObject(stringBuffer.toString());
            }
            catch(Exception ex)
            {
                return null;
            }
            finally
            {
                if(bufferedReader != null)
                {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } // protected Void doInBackground(String... params)

        @Override
        protected void onPostExecute(JSONObject response)
        {
            super.onPostExecute(response);
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            if(response != null)
            {
                try {
                    String s = response.getString("title");
                    System.out.println("nvc-----------:" + s);

//                Log.e("App", "Success: " + response.getString("yourJsonElement") );
                } catch (JSONException ex) {
//                Log.e("App", "Failure", ex);
                    System.out.print(ex);
                }
            }
        }
    }

}
