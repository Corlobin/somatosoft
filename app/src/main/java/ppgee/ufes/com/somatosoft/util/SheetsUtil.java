package ppgee.ufes.com.somatosoft.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class SheetsUtil {
    private Context mcontext;
    static final String ADD = "add";
    static final String URL = "https://script.google.com/macros/s/AKfycbyc57s9vLywwR9UCPI1Re3VEv2Fg2Dg74csIyjkWFM3uM9sxniFoS67WXxnfJRovOBMWg/exec";

    public SheetsUtil(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void addItem(HeathCarter heathCarter) {
        final ProgressDialog loading = ProgressDialog.show(mcontext, "Enviando ...", "Por favor, aguarde.");
        StringRequest request = new StringRequest(Request.Method.POST, URL, response(loading), error(loading)) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("action", ADD);
                params.put("name", heathCarter.getName());
                params.put("sex", heathCarter.getSexo());
                params.put("peso", addParam(heathCarter.getMassaM()));
                params.put("birthday", heathCarter.getDataNascimento());
                params.put("estatura", addParam(heathCarter.getEstaturaH()));
                params.put("tr", addParam(heathCarter.getDobraCutaneaTR()));
                params.put("sb", addParam(heathCarter.getDobraCutaneaSB()));
                params.put("se", addParam(heathCarter.getDobraCutaneaSE()));
                params.put("pm", addParam(heathCarter.getDobraCutaneaPA()));
                params.put("du", addParam(heathCarter.getDiametroDU()));
                params.put("df", addParam(heathCarter.getDiametroDF()));
                params.put("pbf", addParam(heathCarter.getPerimetroPB()));
                params.put("pp", addParam(heathCarter.getPerimetroPP()));
                params.put("xc", addParam(heathCarter.getXC()));
                params.put("ip", addParam(heathCarter.getIP()));
                params.put("pcb", addParam(heathCarter.getPCB()));
                params.put("pcp", addParam(heathCarter.getPCP()));
                params.put("ecto", addParam(heathCarter.getEcto()));
                params.put("meso", addParam(heathCarter.getMeso()));
                params.put("endo", addParam(heathCarter.getEndo()));
                params.put("x", addParam(heathCarter.getX()));
                params.put("y", addParam(heathCarter.getY()));

                params.put("covid", addParam(heathCarter.isCovid()));
                params.put("cirurgia", addParam(heathCarter.isCirurgia()));
                params.put("diabetes", addParam(heathCarter.isDiabetes()));
                params.put("obesidade", addParam(heathCarter.isObesidade()));
                params.put("hipertensao", addParam(heathCarter.isHipertensao()));
                params.put("cardiopatia", addParam(heathCarter.isCardiopatia()));
                params.put("dislipidemia", addParam(heathCarter.isDislipidemia()));

                String id = UUID.randomUUID().toString();
                params.put("user", id);
                params.put("fin", heathCarter.getName() + "_front_" + id);
                params.put("sin", heathCarter.getName() + "_side_" + id);

                Bitmap frontBitmap = BitmapFactory.decodeFile(ImageResolutionProvider.frontImage);
                byte[] frontByte = getBytes(frontBitmap);
                params.put("fi", Base64.getEncoder().encodeToString(frontByte));

                Bitmap sideBitmap = BitmapFactory.decodeFile(ImageResolutionProvider.sideImage);
                byte[] sideByte = getBytes(sideBitmap);
                params.put("si", Base64.getEncoder().encodeToString(sideByte));

                return params;
            }
        };
        int socketTimeout = 10000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        request.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(mcontext);
        queue.add(request);
    }

    private byte[] getBytes(Bitmap frontBitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        frontBitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    private Response.Listener<String> response(ProgressDialog loading) {
        return response -> {
            loading.dismiss();
            Toast.makeText(mcontext, "Sucesso!", Toast.LENGTH_LONG).show();
        };
    }

    private Response.ErrorListener error(ProgressDialog loading) {
        return (Response.ErrorListener) error -> {
            Toast.makeText(mcontext, "Erro " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            loading.dismiss();
        };
    }

    private String addParam(Double value) {
        return String.format("%.04f", value).replace(".", ",");
    }

    private String addParam(boolean value) {
        return value ? "S" : "N";
    }
}
