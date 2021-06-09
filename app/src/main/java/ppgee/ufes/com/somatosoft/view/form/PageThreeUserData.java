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

import javax.xml.transform.Result;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import ppgee.ufes.com.somatosoft.R;
import ppgee.ufes.com.somatosoft.util.HeathCarter;
import ppgee.ufes.com.somatosoft.util.Pair;

public class PageThreeUserData extends AppCompatActivity {

    static final String REQUIRED = "Preencha o campo!";

    Button nextButton;
    TextInputLayout tlDiaOssUmer;
    TextInputLayout tlDiaOssFemur;
    TextInputLayout tlPerBraFlex;
    TextInputLayout tlPeriPantu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_screen_page3);
        nextButton = findViewById(R.id.btn_next_3);
        nextButton.setOnClickListener(this::sendToNextPage);
        tlDiaOssUmer = findViewById(R.id.tlDiaOssUmer);
        tlDiaOssFemur = findViewById(R.id.tlDiaOssFemur);
        tlPerBraFlex = findViewById(R.id.tlPerBraFlex);
        tlPeriPantu = findViewById(R.id.tlPeriPantu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sendToNextPage(View view) {
        System.out.println("next page");
        if (!validate()) {
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(PageThreeUserData.this, ResultCalculusUserData.class);

            Bundle bundle = getIntent().getExtras();
            HeathCarter heathCarter = (HeathCarter) bundle.getSerializable("data");

            heathCarter.setDiametroDU(Double.valueOf(tlDiaOssUmer.getEditText().getText().toString()));
            heathCarter.setDiametroDF(Double.valueOf(tlDiaOssFemur.getEditText().getText().toString()));
            heathCarter.setPerimetroPB(Double.valueOf(tlPerBraFlex.getEditText().getText().toString()));
            heathCarter.setPerimetroPP(Double.valueOf(tlPeriPantu.getEditText().getText().toString()));

            intent.putExtra("data", heathCarter);

            PageThreeUserData.this.startActivity(intent);
        }, 2000);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Boolean validate() {
        return Stream.of(tlDiaOssUmer, tlDiaOssFemur, tlPerBraFlex, tlPeriPantu)
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