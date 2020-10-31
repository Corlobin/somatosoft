package ppgee.ufes.com.somatosoft.view.form;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import ppgee.ufes.com.somatosoft.R;

public class PageOneUserData extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_screen_page1);
        nextButton = findViewById(R.id.btn_next_1);
        nextButton.setOnClickListener(this::sendToNextPage);
    }

    private void sendToNextPage(View view) {
        System.out.println("next page");
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(PageOneUserData.this, PageTwoUserData.class);
            PageOneUserData.this.startActivity(intent);
        }, 2000);
    }
}