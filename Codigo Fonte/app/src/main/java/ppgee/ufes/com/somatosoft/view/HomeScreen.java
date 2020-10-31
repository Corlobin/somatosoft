package ppgee.ufes.com.somatosoft.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ppgee.ufes.com.somatosoft.R;
import ppgee.ufes.com.somatosoft.database.AppDatabase;
import ppgee.ufes.com.somatosoft.database.DatabaseSingleton;
import ppgee.ufes.com.somatosoft.database.entity.User;
import ppgee.ufes.com.somatosoft.view.dialog.AlertDialogFactory;
import ppgee.ufes.com.somatosoft.view.image.FrontImageScreen;

public class HomeScreen extends AppCompatActivity {
    private Button buttonEntrar;
    private Button buttonCadastrar;

    private TextInputLayout userLogin;
    private TextInputLayout userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        buttonEntrar = findViewById(R.id.buttonEntrarLogin);
        buttonEntrar.setOnClickListener(onButtonEntrarClickListener());

        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        buttonCadastrar.setOnClickListener(onButtonCadastrarClickListener());

        userLogin = findViewById(R.id.textFieldUsuario);
        userPassword = findViewById(R.id.textFieldSenha);

    }

    private void loginUser(AppDatabase db) {
        if (validateFields()) {
            return;
        }
        User user = db.userDao().findByLoginAndPassword(userLogin.getEditText().getText().toString(),
                userPassword.getEditText().getText().toString());

        if (user == null) {
            runOnUiThread(() -> AlertDialogFactory.createUserNotExistsDialog(HomeScreen.this));
            return;
        }

        displaySuccessMessage();
    }

    private void displaySuccessMessage() {
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast toast = Toast.makeText(HomeScreen.this, "Login efetuado!", Toast.LENGTH_SHORT);
            toast.show();
        });

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(HomeScreen.this, FrontImageScreen.class);
            HomeScreen.this.startActivity(intent);
        }, 2000);
    }

    private boolean validateFields() {
        boolean invalid = false;
        if (userLogin.getEditText().getText() == null || userLogin.getEditText().getText().length() == 0) {
            runOnUiThread(() -> userLogin.getEditText().setError("Campo deve ser preenchido"));
            invalid = true;
        }
        if (userPassword.getEditText().getText() == null || userPassword.getEditText().getText().length() == 0) {
            runOnUiThread(() -> userPassword.getEditText().setError("Campo deve ser preenchido"));
            invalid = true;
        }
        return invalid;
    }

    private View.OnClickListener onButtonEntrarClickListener() {
        return view -> Observable.just(DatabaseSingleton.getInstance(getApplicationContext()))
                .subscribeOn(Schedulers.io())
                .subscribe(db -> loginUser(db));
    }

    private View.OnClickListener onButtonCadastrarClickListener() {
        return view -> {
            Intent intent = new Intent(HomeScreen.this, RegisterScreen.class);
            HomeScreen.this.startActivity(intent);
        };
    }
}