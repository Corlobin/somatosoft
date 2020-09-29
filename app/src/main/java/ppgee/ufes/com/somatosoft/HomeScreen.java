package ppgee.ufes.com.somatosoft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {
    private Button buttonEntrar;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        buttonEntrar = findViewById(R.id.buttonEntrar);
        buttonEntrar.setOnClickListener(onButtonEntrarClickListener());

        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        buttonCadastrar.setOnClickListener(onButtonCadastrarClickListener());
    }

    private View.OnClickListener onButtonEntrarClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, FrontImageScreen.class);
                HomeScreen.this.startActivity(intent);
            }
        };
    }

    private View.OnClickListener onButtonCadastrarClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, RegisterScreen.class);
                HomeScreen.this.startActivity(intent);
            }
        };
    }
}