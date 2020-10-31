package ppgee.ufes.com.somatosoft.view.form;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import ppgee.ufes.com.somatosoft.R;

public class PageTwoUserData extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_screen_page2);
        nextButton = findViewById(R.id.btn_next_2);
        nextButton.setOnClickListener(this::sendToNextPage);
    }

    private void sendToNextPage(View view) {
        System.out.println("next page");
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(PageTwoUserData.this, PageThreeUserData.class);
            PageTwoUserData.this.startActivity(intent);
        }, 2000);
    }
}