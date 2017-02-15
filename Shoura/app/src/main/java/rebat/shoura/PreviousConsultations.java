package rebat.shoura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lobna on 2/15/17.
 */

public class PreviousConsultations extends AppCompatActivity {

    int ConsultationId;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_consultations);

        // to receive the consultant from previous page
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        if(username != null) {
            // Get Consultant Consultations
        }
    }
    private void GoToViewConsultation(){
        Intent intent = new Intent(PreviousConsultations.this, ViewConsultation.class);
        intent.putExtra("ConsultationId", ConsultationId);
        startActivity(intent);
    }
}
