package rebat.shoura;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ACM on 2/10/17.
 */

public class ConsultantProfile extends AppCompatActivity {

    Button RequestConsultation,Chat,Consultations;
    TextView name, field, bio;
    ImageView img;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultant_profile);

        setTitle(R.string.viewConsultant);

        // to receive the number from previous page
        //Intent intent = getIntent();
        username = "Ghada"; // username = intent.getStringExtra("username");

        // Get Layout Elements
        name = (TextView) findViewById(R.id.name);
        field = (TextView) findViewById(R.id.field);
        bio = (TextView) findViewById(R.id.bio);
        img = (ImageView) findViewById(R.id.img);

        // Get & Set consultant profile
        Entities.Consultant consultant = GetConsultant(username);

        // Set Values from GetConsultant
        name.setText(consultant.Name);
        field.setText(consultant.Field);
        bio.setText(consultant.Bio);

        if (consultant.Photo != null){ // If img is available
            img.setImageBitmap(getPhoto(consultant.Photo));
        }else { // Default Img
            img.setImageDrawable(getResources().getDrawable(R.drawable.ad));
        }
        // When user click on Sending Consultation Button
        RequestConsultation = (Button) findViewById(R.id.RequestConsultationButton);
        RequestConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Transfer to the next interface
                Intent i = new Intent(ConsultantProfile.this, RequestConsultation.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        });

        // When user click on Chat with Consultant Button
        Chat = (Button) findViewById(R.id.ChatButton);
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Transfer to the next interface
                /*
                Intent i = new Intent(AdviserProfile.this,MainActivity.class);
                i.putExtra("username","");
                startActivity(i);*/
            }
        });

        // When user click on preview previous consultations Button
        Consultations =(Button) findViewById(R.id.ConsultationsButton);
        Consultations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                //change the next page
                Intent i = new Intent(AdviserProfile.this,MainActivity.class);
                i.putExtra("username","");
                startActivity(i); */
            }
        });
    }

    private Entities.Consultant GetConsultant(String username) {
        //call get information to get user profile information from database
        Entities.Consultant consultant = new MyDB(this).getProfile(username);
        return consultant;
    }

    //convert photo from byte array to bitmap to be shown in activity
    public Bitmap getPhoto(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
