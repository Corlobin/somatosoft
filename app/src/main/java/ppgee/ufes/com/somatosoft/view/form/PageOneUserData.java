package ppgee.ufes.com.somatosoft.view.form;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskara.Mask;
import com.santalu.maskara.MaskChangedListener;
import com.santalu.maskara.MaskStyle;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import ppgee.ufes.com.somatosoft.R;
import ppgee.ufes.com.somatosoft.util.HeathCarter;
import ppgee.ufes.com.somatosoft.util.Pair;

public class PageOneUserData extends AppCompatActivity {

    static final String REQUIRED = "Preencha o campo!";

    Button nextButton;
    TextInputLayout estatura;
    TextInputLayout massa;
    TextInputLayout dataNascimento;
    MaskChangedListener listener;
    private RadioButton masculino;
    private RadioButton covid;
    private RadioButton cirurgia;

    private CheckBox diabetes;
    private CheckBox obesidade;
    private CheckBox hipertensao;
    private CheckBox cardiopatia;
    private CheckBox colesterolemia;
    private CheckBox dislipidemia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_screen_page1);
        nextButton = findViewById(R.id.btn_next_1);
        nextButton.setOnClickListener(this::sendToNextPage);
        estatura = findViewById(R.id.tlEstatura);
        massa = findViewById(R.id.tlMassaCoporal);
        dataNascimento = (TextInputLayout) findViewById(R.id.tlDataNascimento);
        masculino = findViewById(R.id.radio_button_1);
        covid = findViewById(R.id.covidRadioButton_1);
        cirurgia = findViewById(R.id.cirurgiaButton_1);
        diabetes = findViewById(R.id.diabetes);
        obesidade = findViewById(R.id.obesidade);
        hipertensao = findViewById(R.id.hipertensao);
        cardiopatia = findViewById(R.id.cardiopatia);
        colesterolemia = findViewById(R.id.colesterolemia);
        dislipidemia = findViewById(R.id.dislipidemia);


        Mask mask = new Mask("__/__/____", '_', MaskStyle.COMPLETABLE);
        listener = new MaskChangedListener(mask);

        dataNascimento.getEditText().addTextChangedListener(listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sendToNextPage(View view) {
        System.out.println("next page");
        if (!validate()) {
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(PageOneUserData.this, PageTwoUserData.class);
            Bundle bundle = getIntent().getExtras();

            HeathCarter heathCarter = new HeathCarter();
            heathCarter.setEstaturaH(Double.valueOf(estatura.getEditText().getText().toString()));
            heathCarter.setMassaM(Double.valueOf(massa.getEditText().getText().toString()));
            heathCarter.setDataNascimento(listener.getMasked());
            if (masculino.isChecked()) {
                heathCarter.setSexo("m");
            } else {
                heathCarter.setSexo("f");
            }
            heathCarter.setCovid(covid.isChecked());
            heathCarter.setCirurgia(cirurgia.isChecked());
            heathCarter.setDiabetes(diabetes.isChecked());
            heathCarter.setObesidade(obesidade.isChecked());
            heathCarter.setHipertensao(hipertensao.isChecked());
            heathCarter.setCardiopatia(cardiopatia.isChecked());
            heathCarter.setColesterolemia(colesterolemia.isChecked());
            heathCarter.setDislipidemia(dislipidemia.isChecked());

            heathCarter.setName(bundle.getString("user"));
//            heathCarter.setFront(bundle.getByteArray("front"));
//            heathCarter.setSide(bundle.getByteArray("side"));

            intent.putExtra("data", heathCarter);

            PageOneUserData.this.startActivity(intent);
        }, 2000);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Boolean validate() {
        return Stream.of(estatura, massa)
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