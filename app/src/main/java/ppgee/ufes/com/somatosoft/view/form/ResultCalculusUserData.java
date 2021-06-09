package ppgee.ufes.com.somatosoft.view.form;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.transform.Result;

import androidx.appcompat.app.AppCompatActivity;
import ppgee.ufes.com.somatosoft.R;
import ppgee.ufes.com.somatosoft.util.HeathCarter;
import ppgee.ufes.com.somatosoft.util.SheetsUtil;
import ppgee.ufes.com.somatosoft.view.HomeScreen;
import ppgee.ufes.com.somatosoft.view.image.FrontImageScreen;
import ppgee.ufes.com.somatosoft.view.image.SideImageScreen;

public class ResultCalculusUserData extends AppCompatActivity {

    static final int REQUEST_DIRECTORY = 13;

    GraphView graphView;
    EditText endo;
    EditText ecto;
    EditText meso;
    Button export;
    Button sair;
    HeathCarter heathCarter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_calculate);
        // graphView = (GraphView) findViewById(R.id.graphView);
        endo = findViewById(R.id.endomorfo);
        ecto = findViewById(R.id.ectomorfo);
        meso = findViewById(R.id.mesomorfo);
        sair = findViewById(R.id.sairbutton);
        sair.setOnClickListener(sairClickListener());
        export = findViewById(R.id.button);
        export.setOnClickListener(onExportClickListener());

        Bundle bundle = getIntent().getExtras();
        heathCarter = (HeathCarter) bundle.getSerializable("data");
        heathCarter.calculate();

        endo.setText(String.format("%.04f", heathCarter.getEndo()));
        ecto.setText(String.format("%.04f", heathCarter.getEcto()));
        meso.setText(String.format("%.04f", heathCarter.getMeso()));

        SheetsUtil sheetsUtil = new SheetsUtil(ResultCalculusUserData.this);
        sheetsUtil.addItem(heathCarter);

        /*LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(heathCarter.getX(), heathCarter.getY())
        });
        graphView.addSeries(series);*/
    }

    private View.OnClickListener sairClickListener() {
        return (view) -> {
            Intent intent = new Intent(ResultCalculusUserData.this, FrontImageScreen.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            ResultCalculusUserData.this.startActivity(intent);
        };
    }

    private View.OnClickListener onExportClickListener() {
        return (view) -> {
            File direct = new File(Environment.getExternalStorageDirectory() + "/Download/somatosoft");

            if (!direct.exists()) {
                File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + "/Download/somatosoft");
                wallpaperDirectory.mkdirs();
            }

            String fileName = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                fileName = String.format("%s.txt",LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss")));
            } else {
                fileName = String.format("%s.txt", new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date()));

            }
            File file = new File(Environment.getExternalStorageDirectory() + "/Download/somatosoft/", fileName);
            if (file.exists()) {
                file.delete();
            }
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileOutputStream out = new FileOutputStream(file);
                out.write(heathCarter.toCSV().getBytes());
                out.flush();
                out.close();

                Toast.makeText(this, String.format("Salvo em Downloads/somatosoft/%s", fileName), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}