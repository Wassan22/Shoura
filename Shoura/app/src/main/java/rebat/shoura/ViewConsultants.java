package rebat.shoura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Areej on 2/14/17.
 */

public class ViewConsultants extends AppCompatActivity {

    String username;
    int FieldId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_consultants);

        // to receive the field from previous page
        Intent intent = getIntent();
        FieldId = intent.getIntExtra("FieldId",0);

        if(FieldId!=0){

        }
    }

    private void GoToConsultantProfile(){
        Intent intent = new Intent(ViewConsultants.this, ConsultantProfile.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
