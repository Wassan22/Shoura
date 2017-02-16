package rebat.shoura;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Maram on 2/15/17.
 */

public class UserProfile extends AppCompatActivity {

    EditText USER_NAME ,USER_EMAIL, USER_AGE, USER_GENDER, USER_STATUS, USER_JOB;
    String user_name, user_email, user_age, user_gender, user_status, user_job;
    Button REG;
    Context ctx= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        // Get layout elements
        USER_NAME =(EditText)findViewById(R.id.editusername);
        USER_EMAIL =(EditText)findViewById(R.id.editemail);
        USER_AGE =(EditText) findViewById(R.id.editage);
        USER_GENDER =(EditText)findViewById(R.id.editgender);
        USER_STATUS =(EditText) findViewById(R.id.editstatus);
        USER_JOB =(EditText) findViewById(R.id.editjob);
        REG = (Button)findViewById(R.id.saveButton);


        // If data is in db, view data


        // If save button clicked
        REG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get filled data
                user_name= USER_NAME.getText().toString();
                user_email= USER_EMAIL.getText().toString();
                user_age= USER_AGE.getText().toString();
                user_gender= USER_GENDER.getText().toString();
                user_status= USER_STATUS.getText().toString();
                user_job= USER_JOB.getText().toString();

                // insert into db
                DatabaseOperations DB = new DatabaseOperations(ctx);
                DB.InsertInfo(DB, user_name, user_email, user_age, user_gender, user_status, user_job);

                // notify user of success
                Toast.makeText(getBaseContext(), R.string.DataSavedSuccessfully, Toast.LENGTH_LONG).show();
            }
        });
    }
}
