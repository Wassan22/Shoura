package rebat.shoura;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by wassanalluhaidan on 2/9/17.
 */

public class RequestConsultation extends Activity {

    EditText nameEditText,emailEditText,bodyEditText, ageEditText;
    Button send;
    RadioGroup genderRadioGroup, privacyRadioGroup;
    RadioButton selectedGender, selectedPrivacy;
    String Consultantusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Leyout
        setContentView(R.layout.request_consultation);
        setTitle(R.string.title_requestConsultation);

        // Get Consultant Id
        Intent previous = getIntent();
        Consultantusername = previous.getStringExtra("username");

        if(Consultantusername != null) {
            // Get Layout Elements
            send = (Button) findViewById(R.id.send);
            nameEditText = (EditText) findViewById(R.id.personName);
            emailEditText = (EditText) findViewById(R.id.personِEmail);
            bodyEditText = (EditText) findViewById(R.id.body);
            ageEditText = (EditText) findViewById(R.id.Age);
            genderRadioGroup = (RadioGroup) findViewById(R.id.Gender);
            privacyRadioGroup = (RadioGroup) findViewById(R.id.Privacy);

            selectedGender = (RadioButton) findViewById(genderRadioGroup.getCheckedRadioButtonId());
            selectedPrivacy = (RadioButton) findViewById(privacyRadioGroup.getCheckedRadioButtonId());

            // Set On Click for the Send Button
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Get Values
                    final String Name = nameEditText.getText().toString();
                    final String Email = emailEditText.getText().toString();
                    final String Body = bodyEditText.getText().toString();
                    final String Age = ageEditText.getText().toString();
                    final int Gender = selectedGender.getId();
                    final int Privacy = selectedPrivacy.getId();

                    // Check Layout Validation
                    if (ValidLayout(Name, Email, Body, Age)) {
                        // Save Consultation
                        float consultationNumber = SaveConsulation(Name, Email, Body, Age, Gender, Privacy);

                        if (consultationNumber != 0) {
                            // Email Applicant
                            String applicantEmailBody = String.format(getString(R.string.Email_ConsultationRecieved), Name, String.valueOf(consultationNumber));
                            //new SendMail(RequestConsultation.this,Email ,getString(R.string.Subject_ConsultationRecieved),applicantEmailBody).Send();

                            //Email Consultant
                            //Entities.Consultant consultant = GetConsultant(Consultantusername);
                            //String consultantEmailBody = String.format(getString(R.string.Email_NewConsultation), consultant.Name, String.valueOf(consultationNumber));
                            //new SendMail(RequestConsultation.this,consultant.Email ,getString(R.string.Subject_ConsultationRecieved),applicantEmailBody).Send();

                            // Confirm Save By Toast
                            Toast.makeText(RequestConsultation.this, getString(R.string.ConsultationSaved), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RequestConsultation.this, getString(R.string.ErrorOccured), Toast.LENGTH_LONG).show();
                        }
                    }
                }

            /*private Entities.Consultant GetConsultant(String username) {
                // Get Consultant By Id
                // Get Name
                Entities.Consultant consultant = new Entities().new Consultant();
                return consultant;
            }*/

                private float SaveConsulation(String name, String email, String body, String age, int gender, int privacy) {
                    // Save Consultation to Web Service or Database, return consultation number
                    return 0;
                }
            });
        }
    }

    private boolean ValidLayout(String user_name, String user_email, String user_body, String user_age) {
        boolean valid = true;

        // Check Required Fields
        if(user_name.length()==0)
        {
            valid = false;
            nameEditText.requestFocus();
            nameEditText.setError(getString(R.string.FieldRequired));
        }
        if(user_email.length()==0)
        {
            valid = false;
            emailEditText.requestFocus();
            emailEditText.setError(getString(R.string.FieldRequired));
        }
        else if (!user_email.matches("([\\w.-]+@([\\w-]+)\\.+\\w{2,})")) // Invalid Email Pattern
        {
            valid = false;
            emailEditText.requestFocus();
            emailEditText.setError(getString(R.string.InvalidEmail));
        }
        if(user_body.length()==0)
        {
            valid = false;
            bodyEditText.requestFocus();
            bodyEditText.setError(getString(R.string.FieldRequired));
        }
        if(user_age.length()==0)
        {
            valid = false;
            ageEditText.requestFocus();
            ageEditText.setError(getString(R.string.FieldRequired));
        }

        return valid;
    }
}
