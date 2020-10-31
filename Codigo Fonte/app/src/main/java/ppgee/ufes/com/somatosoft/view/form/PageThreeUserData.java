package ppgee.ufes.com.somatosoft.view.form;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import javax.xml.transform.Result;

import androidx.appcompat.app.AppCompatActivity;
import ppgee.ufes.com.somatosoft.R;

public class PageThreeUserData extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_screen_page3);
        nextButton = findViewById(R.id.btn_next_3);
        nextButton.setOnClickListener(this::sendToNextPage);
    }

    private void sendToNextPage(View view) {
        System.out.println("next page");
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(PageThreeUserData.this, ResultCalculusUserData.class);
            PageThreeUserData.this.startActivity(intent);
        }, 2000);
    }
}