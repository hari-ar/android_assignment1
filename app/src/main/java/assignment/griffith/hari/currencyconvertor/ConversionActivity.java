package assignment.griffith.hari.currencyconvertor;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by aahuyarakshakaharil on 22/10/17.
 */

public class ConversionActivity extends Activity {

    ArrayList<TypedArray> ratesArray = MainActivity.ratesArray;

    EditText[] editTexts = new EditText[6];
    Spinner conversionSpinner;
    private int currencySelected =0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversionlayout);
        //ratesArray = (ArrayList<TypedArray>) getIntent().getExtras().get("rates");
        Resources resources = getResources();
        conversionSpinner = (Spinner) findViewById(R.id.currency_spinner);
        populateEditTextArrayList();
        TypedArray localArray = ratesArray.get(0);

        for (int i = 0; i<6; i++){
            editTexts[i].setHint(Float.toString(localArray.getFloat(i,0)));
        }

        conversionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 currencySelected = position;
                populateEditTextArrayList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void populateEditTextArrayList() {

        editTexts[0] = (EditText) findViewById(R.id.conv_aud);
        editTexts[1] = (EditText) findViewById(R.id.conv_cad);
        editTexts[2] = (EditText) findViewById(R.id.conv_eur);
        editTexts[3] = (EditText) findViewById(R.id.conv_gbp);
        editTexts[4] = (EditText) findViewById(R.id.conv_jpy);
        editTexts[5] = (EditText) findViewById(R.id.conv_usd);

    }



    public void editRates(View args0) {

        populateEditTextArrayList();




    }


}
