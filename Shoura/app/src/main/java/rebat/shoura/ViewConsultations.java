package rebat.shoura;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salha Altamimi on 2/14/17.
 */

public class ViewConsultations extends AppCompatActivity {

    String json_string, username;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Adapter Adapter;
    View myView;

    ListView ListView;
    String ID="1";
    String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_consultations);

        // to receive the consultant from previous page
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        if(username != null) {
            getJSON();
        }
    }

    public void getJSON() {

        new BackgroundTasks().execute();
    }


    class BackgroundTasks extends AsyncTask<Void, Void, String>
    {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = "http://10.0.3.2/showra/ShowListViews.php";
            // super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                String Show = "Consultation";
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Show", "UTF-8") + "=" + URLEncoder.encode(Show, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);

            json_string = result;
            sendInfo();
        }


    }
    private void sendInfo() {
        SharedPreferences share =getSharedPreferences("Consultation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        ListView = (ListView)findViewById(R.id.listView);
        Adapter = new Adapter(ViewConsultations.this, R.layout.row_consultation);
        ListView.setAdapter(Adapter);
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SharedPreferences sharedPref = getSharedPreferences("Consultation", Context.MODE_PRIVATE);
                String sendID = sharedPref.getString("CID" + position, "");
                Intent in = new Intent(ViewConsultations.this, ConsultationAnswer.class);
                in.putExtra("consultationId", sendID);
                startActivity(in);

                // Toast.makeText(ViewConsultations.this,"Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
            }
        });

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("result");
            int count = 0;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                Name = JO.getString("Name");
                ID = JO.getString("ID");
                editor.putString("CID" + count, ID);
                editor.apply();
                ManageMutator ManageClub = new ManageMutator(Name);
                Adapter.add(ManageClub);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public class Adapter extends ArrayAdapter {
        List list = new ArrayList();

        public Adapter(Context context, int resource) {

            super(context, resource);
        }

        public void add(ManageMutator object) {
            super.add(object);
            list.add(object);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View row;
            row = convertView;
            Holder Holder;
            Holder MainHolder = null;


            if (row == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                row = layoutInflater.inflate(R.layout.row_consultation, parent, false);
                Holder = new Holder();
                Holder.Consultation_tx = (TextView) row.findViewById(R.id.Consultation_tx);

                row.setTag(Holder);
            }
            MainHolder = (Holder) row.getTag();

            ManageMutator manage_Consultation = (ManageMutator) this.getItem(position);
            MainHolder.Consultation_tx.setText(manage_Consultation.getname());


            return row;
        }

        class Holder {
            TextView Consultation_tx ;
        }

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        getJSON();
    }
}
