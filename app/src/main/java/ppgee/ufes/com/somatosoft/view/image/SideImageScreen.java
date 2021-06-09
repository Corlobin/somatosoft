package ppgee.ufes.com.somatosoft.view.image;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import ppgee.ufes.com.somatosoft.R;
import ppgee.ufes.com.somatosoft.util.ImageResolutionProvider;
import ppgee.ufes.com.somatosoft.view.form.PageOneUserData;

public class SideImageScreen extends AppCompatActivity {
    Button nextButton;
    ImageView imageView;
    boolean selectedImage;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_image_screen);

        selectedImage = false;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        findViewById(R.id.btPicture).setOnClickListener($ -> {
            takePicture();
        });

        nextButton = findViewById(R.id.btNext);
        nextButton.setOnClickListener($ -> {
            if (!selectedImage) {
                nextButton.setError("Por favor, tire a foto!");
                return;
            }

            Intent intent = new Intent(SideImageScreen.this, PageOneUserData.class);
            Bundle bundle = getIntent().getExtras();
            intent.putExtra("user", bundle.getString("user"));
//            intent.putExtra("front", bundle.getByteArray("front"));
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            intent.putExtra("side", byteArray);

            SideImageScreen.this.startActivity(intent);
        });

        imageView = findViewById(R.id.imageViewFront);
        imageView.setImageResource(R.drawable.lado);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void takePicture() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE}, 0);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = createImageFile();
        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(this, "ppgee.ufes.com.somatosoft.fileprovider", photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(intent, 1);
        }

    }

    private File createImageFile() {
        try {
            return ImageResolutionProvider.createSideImage();
        } catch (Exception e) {
            Log.e("error", "SideImage error", e);
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap sideBitmap = BitmapFactory.decodeFile(ImageResolutionProvider.sideImage);
            imageView.setImageBitmap(sideBitmap);
            selectedImage = true;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}