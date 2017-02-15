package rebat.shoura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lobna on 2/15/17.
 */

public class ConsultantDashboard extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultant_dashboard);

        // to receive the consultant from previous page
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        if(username != null) {

        }
    }
}
