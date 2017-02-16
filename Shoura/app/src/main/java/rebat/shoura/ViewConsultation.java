package rebat.shoura;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONObject;

/**
 * Created by wassanalluhaidan on 2/11/17.
 */

public class ViewConsultation extends Activity {
    TextView Consultation, Answer;
    int ConsultationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Set Leyout
            setContentView(R.layout.consultation);
            setTitle(R.string.ViewConsultation);

            // Get Consultation Id
            Intent previous = getIntent();
            ConsultationId = previous.getIntExtra("ConsultationId", 0);

            if (ConsultationId != 0) {
                // Get Layout Elements
                Consultation = (TextView) findViewById(R.id.Consultation);
                Answer = (TextView) findViewById(R.id.Answer);

                // Get Consultation
                JSONObject consultation = GetConsultation(ConsultationId);

                // Set Retrieved Values
                Consultation.setText(consultation.getString("Consultation"));
                Answer.setText(consultation.getString("Answer"));
            }
        }catch (Exception ex){
            // Log Error
        }
    }

    private JSONObject GetConsultation(int consultationId) {
        try{
            // Get Consultation From Web Service or JSONObject
            String jsonString = "{\"ConsultationId\":\"1\",\"Consultation\":\"Consultation\",\"Answer\":\"Answer\"}";
            JSONObject Consultation = new JSONObject(jsonString);
            return Consultation;
        }catch (Exception ex){
            // Log Error
            return null;
        }
    }
}
