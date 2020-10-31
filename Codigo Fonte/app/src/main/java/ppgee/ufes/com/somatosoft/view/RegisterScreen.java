package ppgee.ufes.com.somatosoft.view;

import android.annotation.SuppressLint;
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

public class RegisterScreen extends AppCompatActivity {

    private Button buttonCadastrar;
    private TextInputLayout userName;
    private TextInputLayout userLogin;
    private TextInputLayout userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        buttonCadastrar.setOnClickListener(onButtonCadastrarClickListener());

        userName = findViewById(R.id.textFieldNome);
        userLogin = findViewById(R.id.textFieldUsuario);
        userPassword = findViewById(R.id.textFieldSenha);

    }

    @SuppressLint("CheckResult")
    private View.OnClickListener onButtonCadastrarClickListener() {
        return view -> {
            if (validateFields()) {
                return;
            }
            Observable.just(DatabaseSingleton.getInstance(getApplicationContext()))
                    .subscribeOn(Schedulers.io())
                    .subscribe(db -> createNewUser(db));
        };
    }

    private void createNewUser(AppDatabase db) {
        User user = db.userDao().findByLogin(userLogin.getEditText().getText().toString());

        if (user != null) {
            AlertDialogFactory.createUserExistsDialog(RegisterScreen.this).show();
            return;
        }

        user = new User(userName.getEditText().getText().toString(), userLogin.getEditText().getText().toString(),
                userPassword.getEditText().getText().toString());
        db.userDao().insertAll(user);

        displaySuccessMessage();
    }

    private void displaySuccessMessage() {
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast toast = Toast.makeText(RegisterScreen.this, "Cadastro efetuado!", Toast.LENGTH_SHORT);
            toast.show();
        });

        new Handler(Looper.getMainLooper()).postDelayed(() -> finish(), 2000);
    }

    private boolean validateFields() {
        boolean invalid = false;
        if (userLogin.getEditText().getText() == null || userLogin.getEditText().getText().length() == 0) {
            userLogin.getEditText().setError("Campo deve ser preenchido");
            invalid = true;
        }
        if (userName.getEditText().getText() == null || userName.getEditText().getText().length() == 0) {
            userName.getEditText().setError("Campo deve ser preenchido");
            invalid = true;
        }
        if (userPassword.getEditText().getText() == null || userPassword.getEditText().getText().length() == 0) {
            userPassword.getEditText().setError("Campo deve ser preenchido");
            invalid = true;
        }
        return invalid;
    }
}