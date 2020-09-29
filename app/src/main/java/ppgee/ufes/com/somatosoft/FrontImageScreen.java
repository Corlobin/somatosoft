package ppgee.ufes.com.somatosoft;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class FrontImageScreen extends AppCompatActivity {
    ImageView imageView;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_image_screen);
        System.out.println("aaaaaaaa1");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        imageView = findViewById(R.id.imageViewFront);
        imageView.setImageResource(R.drawable.exemplo_de_frente);
        findViewById(R.id.btPicture).setOnClickListener($ -> {
            takePicture();
            System.out.println("aaaaaaaa2");
        });

        nextButton = findViewById(R.id.btNext);
        nextButton.setOnClickListener($ -> {
            Intent intent = new Intent(FrontImageScreen.this, SideImageScreen.class);
            FrontImageScreen.this.startActivity(intent);
        });
    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap image = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
