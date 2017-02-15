package rebat.shoura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Areej on 2/14/17.
 */

public class ViewFields extends AppCompatActivity {

    int FieldId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_fields);

    }
    private void GoToViewConsultants(){
        Intent intent = new Intent(ViewFields.this, ViewConsultants.class);
        intent.putExtra("FieldId", FieldId);
        startActivity(intent);
    }
}
