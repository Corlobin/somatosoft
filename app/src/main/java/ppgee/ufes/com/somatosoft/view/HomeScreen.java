package ppgee.ufes.com.somatosoft.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.apache.commons.lang3.StringUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ppgee.ufes.com.somatosoft.R;
import ppgee.ufes.com.somatosoft.view.image.FrontImageScreen;

public class HomeScreen extends AppCompatActivity {
    private Button buttonEntrar;
    private TextInputLayout userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        buttonEntrar = findViewById(R.id.buttonEntrarLogin);
        buttonEntrar.setOnClickListener(onButtonEntrarClickListener());

        userLogin = findViewById(R.id.textFieldUsuario);

        if (ActivityCompat.checkSelfPermission(HomeScreen.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 0);
        }

        if (ActivityCompat.checkSelfPermission(HomeScreen.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 0);
        }
    }

    private void displaySuccessMessage() {
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast toast = Toast.makeText(HomeScreen.this, "Login efetuado!", Toast.LENGTH_SHORT);
            toast.show();
        });

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(HomeScreen.this, FrontImageScreen.class);
            intent.putExtra("user", userLogin.getEditText().getText().toString());
            HomeScreen.this.startActivity(intent);
        }, 2000);
    }

    private View.OnClickListener onButtonEntrarClickListener() {
        return view -> {
            if (userLogin == null || userLogin.getEditText().getText() == null || StringUtils.isEmpty(userLogin.getEditText().getText())) {
                userLogin.setError("Preencha o campo!");
                return;
            }
            displaySuccessMessage();
        };
    }
}