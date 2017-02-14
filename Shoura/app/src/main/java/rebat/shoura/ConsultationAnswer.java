package rebat.shoura;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


/**
 * Created by salha Altamimi on 2/14/17.
 */

public class ConsultationAnswer extends AppCompatActivity {

    String ID;
    TextView ConsultationID,ConsultationName;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultation_answer);

        ConsultationName=(TextView)findViewById(R.id.Consultation) ;
        ConsultationID= (TextView) findViewById(R.id.ConsultationID);
        ConsultationID.setText(ID);


        ID = getIntent().getExtras().getString("consultationId");

        if(ID!= null) {
            getJSON();
        }
    }
    public void getJSON (){
        new BackgroundTasks().execute();
    }

    class BackgroundTasks extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute(){
            json_url="http://10.0.3.2/showra/Retrieving.php";
        }

        @Override
        protected String doInBackground(Void... voids){
            try{

                URL url = new URL(json_url);
                String Display = "Display";
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Display", "UTF-8") + "=" + URLEncoder.encode(Display, "UTF-8") + "&" +
                        URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null ){
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e){
                e.printStackTrace();

            }
            return null;
        }
        @Override
        protected void onProgressUpdate (Void... values){
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result){
            json_string = result;
            try {
                String Name;
                jsonObject=new JSONObject(json_string);
                jsonArray=jsonObject.getJSONArray("result");
                JSONObject JO=jsonArray.getJSONObject(0);
                Name=JO.getString("Name");
                ConsultationName.setText(Name);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
