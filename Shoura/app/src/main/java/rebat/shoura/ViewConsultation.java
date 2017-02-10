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

        // Set Leyout
        setContentView(R.layout.consultation);

        // Get Consultation Id
        Intent previous = getIntent();
        if(previous.getStringExtra("ConsultationId") != null) {
            ConsultationId = Integer.parseInt(previous.getStringExtra("ConsultationId"));

            // Get Layout Elements
            Consultation = (TextView) findViewById(R.id.Consultation);
            Answer = (TextView) findViewById(R.id.Answer);

            // Get Consultation
            JSONObject consultation = GetConsultation(ConsultationId);

            // Set Retrieved Values
            Consultation.setText("");
            Answer.setText("");
        }
    }

    private JSONObject GetConsultation(int consultationId) {
        // Get Consultation From Web Service or JSONObject
        return new JSONObject();
    }
}
