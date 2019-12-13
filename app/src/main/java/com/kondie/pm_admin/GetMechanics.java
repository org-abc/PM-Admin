package com.kondie.pm_admin;

import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kondie on 2018/07/05.
 */

public class GetMechanics extends AsyncTask<String, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainActivity.loadingMechanics.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(Constants.PM_HOSTING_WEBSITE + "/getMechanics.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder().appendQueryParameter("lastUpdated", params[0])
                    .appendQueryParameter("all", "all");
            String query = builder.build().getEncodedQuery();

            OutputStream outStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));

            writer.write(query);
            writer.flush();
            writer.close();
            outStream.close();
            conn.connect();

            int resCode = conn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK)
            {
                InputStream inStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                inStream.close();
                return (result.toString());
            }
            else
            {
                return ("Connection Failed " + String.valueOf(resCode));
            }
        }
        catch(Exception e)
        {
            return ("Conn, " + e.toString());
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        MainActivity.loadingMechanics.setVisibility(View.GONE);
        try{
            JSONArray mechanicsArr = new JSONArray(s);
            for (int c=0; c < mechanicsArr.length(); c++){
                JSONObject mechanicOb = mechanicsArr.getJSONObject(c);
                MechanicItem item = new MechanicItem();
                item.setMechanicDpPath(mechanicOb.getString("image_path"));
                item.setMechanicName(mechanicOb.getString("lname") + " " + mechanicOb.getString("fname"));
                item.setMechanicPhone(mechanicOb.getString("phone"));
                item.setMechanicEmail(mechanicOb.getString("email"));
                item.setMechanicStatus(mechanicOb.getString("status"));

                int existRV = exist(item.getMechanicEmail());
                if (existRV == -1) {
                    MainActivity.mechanicItems.add(item);
                }
            }
            MainActivity.mechanicListAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Toast.makeText(MainActivity.activity, e.toString() + s, Toast.LENGTH_SHORT).show();
        }
    }

    private int exist(String email){

        for (int c=0; c<MainActivity.mechanicItems.size(); c++){
            MechanicItem item = MainActivity.mechanicItems.get(c);
            if (item.getMechanicEmail().equals(email)){
                return (c);
            }
        }
        return (-1);
    }
}
