package ppgee.ufes.com.somatosoft.view.form;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.stream.Stream;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import ppgee.ufes.com.somatosoft.R;
import ppgee.ufes.com.somatosoft.util.HeathCarter;
import ppgee.ufes.com.somatosoft.util.Pair;

public class PageTwoUserData extends AppCompatActivity {
    static final String REQUIRED = "Preencha o campo!";

    Button nextButton;
    TextInputLayout tlDobCutTri;
    TextInputLayout tlDobCutSup;
    TextInputLayout tlDobCutSub;
    TextInputLayout tlDobCutPantMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_screen_page2);
        nextButton = findViewById(R.id.btn_next_2);
        nextButton.setOnClickListener(this::sendToNextPage);
        tlDobCutTri = findViewById(R.id.tlDobCutTri);
        tlDobCutSup = findViewById(R.id.tlDobCutSup);;
        tlDobCutSub = findViewById(R.id.tlDobCutSub);;
        tlDobCutPantMed = findViewById(R.id.tlDobCutPantMed);;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sendToNextPage(View view) {
        System.out.println("next page");
        if (!validate()) {
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(PageTwoUserData.this, PageThreeUserData.class);

            Bundle bundle = getIntent().getExtras();
            HeathCarter heathCarter = (HeathCarter) bundle.getSerializable("data");

            heathCarter.setDobraCutaneaTR(Double.valueOf(tlDobCutTri.getEditText().getText().toString()));
            heathCarter.setDobraCutaneaSE(Double.valueOf(tlDobCutSup.getEditText().getText().toString()));
            heathCarter.setDobraCutaneaSB(Double.valueOf(tlDobCutSub.getEditText().getText().toString()));
            heathCarter.setDobraCutaneaPA(Double.valueOf(tlDobCutPantMed.getEditText().getText().toString()));

            intent.putExtra("data", heathCarter);
            PageTwoUserData.this.startActivity(intent);
        }, 2000);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Boolean validate() {
        return Stream.of(tlDobCutTri, tlDobCutSup, tlDobCutSub, tlDobCutPantMed)
                .map((field) -> isValid(field.getEditText()))
                .filter((field) -> Boolean.FALSE == field.getSecond())
                .peek(field ->  field.getFirst().setError(REQUIRED))
                .map(Pair::getSecond)
                .findAny()
                .orElse(true);
    }

    private Pair<EditText, Boolean> isValid(EditText editText) {
        String text = editText.getText().toString();
        return Pair.create(editText, StringUtils.isNotEmpty(text) && NumberUtils.isCreatable(text));
    }
}