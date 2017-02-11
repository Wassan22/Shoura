package rebat.shoura;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ForgetPassword extends Activity {

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        setTitle(R.string.forgotPassword);

        // get layout elements
        final Button button = (Button) findViewById(R.id.send);
        final EditText username = (EditText) findViewById(R.id.name);
        final MyDB db = new MyDB(this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get Email from user name
                String email =  db.getUserEmail(username.getText().toString());

                //check if Exist ?
                if(email == null){
                    //if not Exist
                    Toast.makeText(getBaseContext()," لا يوجد اسم مستخدم ",Toast.LENGTH_LONG).show();
                }
                else {
                    //if Exist true
                    final String newPassword = GenerateRandomPassword();
                    Log.d("Password",newPassword);

                    //Getting content for email
                    String consultantName = "";
                    String subject = getString(R.string.Subject_PasswordReset);
                    String message = String.format(getString(R.string.Email_NewPassword), consultantName, newPassword);

                    // SendEmail
                    new SendMail(ForgetPassword.this, email,subject,message).Send();

                    // Transfer to Login Interface
                     /*Intent i = new Intent(AdviserProfile.this,MainActivity.class); back to login page
                      i.putExtra("username","");
                      startActivity(i);*/
                }
            }

        });
    }

    private String GenerateRandomPassword(){
        int passwordLength = 10;
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] allowedCharsArray = allowedChars.toCharArray();
        char[] chars = new char[passwordLength];
        Random random = new Random();
        for (int i = 0; i < passwordLength; i++) {
            chars[i] = allowedCharsArray[random.nextInt(allowedChars.length())];}
        final String newPassword = String.valueOf(chars).substring(0,passwordLength);
        return newPassword;
    }
}

