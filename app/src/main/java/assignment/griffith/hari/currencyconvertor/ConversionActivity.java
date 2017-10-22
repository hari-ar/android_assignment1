package assignment.griffith.hari.currencyconvertor;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aahuyarakshakaharil on 22/10/17.
 */

public class ConversionActivity extends Activity {

    ArrayList<TypedArray> ratesArray = MainActivity.ratesArray;

    EditText aud,cad,eur,gbp,jpy,usd;
    Spinner conversionSpinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversionlayout);
        //ratesArray = (ArrayList<TypedArray>) getIntent().getExtras().get("rates");
        Resources resources = getResources();
        conversionSpinner = (Spinner) findViewById(R.id.currency_spinner);
        aud = (EditText) findViewById(R.id.conv_aud);
        cad = (EditText) findViewById(R.id.conv_cad);
        eur = (EditText) findViewById(R.id.conv_eur);
        gbp = (EditText) findViewById(R.id.conv_gbp);
        jpy = (EditText) findViewById(R.id.conv_jpy);
        usd = (EditText) findViewById(R.id.conv_usd);

        TypedArray localArray = ratesArray.get(0);

        aud.setHint(Float.toString(localArray.getFloat(0,0)));

        //aud.setHint("Test");
        cad.setHint(Float.toString(localArray.getFloat(1,0)));
        eur.setHint(Float.toString(localArray.getFloat(2,0)));
        gbp.setHint(Float.toString(localArray.getFloat(3,0)));
        jpy.setHint(Float.toString(localArray.getFloat(4,0)));
        usd.setHint(Float.toString(localArray.getFloat(5,0)));
        
    }


}
