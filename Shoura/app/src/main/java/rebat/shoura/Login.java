package rebat.shoura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Maram on 2/15/17.
 */

public class Login extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }
    private void GoToViewConsultants(){
        Intent intent = new Intent(Login.this, ConsultantDashboard.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
