package ppgee.ufes.com.somatosoft.view.image;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import ppgee.ufes.com.somatosoft.R;
import ppgee.ufes.com.somatosoft.view.form.PageOneUserData;

public class SideImageScreen extends AppCompatActivity {
    Button nextButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_image_screen);

        nextButton = findViewById(R.id.btNext);
        nextButton.setOnClickListener($ -> {
            Intent intent = new Intent(SideImageScreen.this, PageOneUserData.class);
            SideImageScreen.this.startActivity(intent);
        });

        imageView = findViewById(R.id.imageViewFront);
        imageView.setImageResource(R.drawable.exemplo);
    }
}