package rebat.shoura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wassanalluhaidan on 2/15/17.
 */

public class ViewChats extends AppCompatActivity {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_chats);

        // to receive the consultant from previous page
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        if(username != null) {

        }
    }
}
