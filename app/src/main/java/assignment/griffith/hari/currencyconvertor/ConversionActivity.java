package assignment.griffith.hari.currencyconvertor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aahuyarakshakaharil on 22/10/17.
 */

public class ConversionActivity extends Activity {

    HashMap<Integer,ArrayList<Float>> ratesMap = new HashMap<Integer,ArrayList<Float>>();

    ArrayList<EditText> editTexts = new ArrayList<EditText>();
    //EditText[] editTexts = new EditText[6];
    Spinner conversionSpinner;
    private int currencySelected =0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversionlayout);


        ratesMap = MainActivity.ratesMap;

       // Resources resources = getResources();
        conversionSpinner = (Spinner) findViewById(R.id.conversion_currency_spinner);

        populateEditTextArrayList();


        for (int i = 0; i<6; i++){
            editTexts.get(i).setHint(Float.toString(ratesMap.get(currencySelected).get(i)));
        }

        conversionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencySelected = position;
                populateEditTextArrayList();
                for (int i = 0; i<6; i++){
                    editTexts.get(i).setHint(Float.toString(ratesMap.get(currencySelected).get(i)));
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    private void populateEditTextArrayList() {

        editTexts.add(0,(EditText) findViewById(R.id.conv_aud));
        editTexts.add(1,(EditText) findViewById(R.id.conv_cad));
        editTexts.add(2,(EditText) findViewById(R.id.conv_eur));
        editTexts.add(3,(EditText) findViewById(R.id.conv_gbp));
        editTexts.add(4,(EditText) findViewById(R.id.conv_jpy));
        editTexts.add(5,(EditText) findViewById(R.id.conv_usd));

    }

    public void editRates(View args0) {
        populateEditTextArrayList();
        ratesMap.put(currencySelected,getFloatArrayFromEditTextList(editTexts));
        populateEditTextArrayList();
        Toast.makeText(this,"Values are persisted..!!",Toast.LENGTH_SHORT);
        finish();
    }

    private ArrayList<Float> getFloatArrayFromEditTextList(ArrayList<EditText> editTexts) {
        ArrayList<Float> floatlist = new ArrayList<Float>();
        for (int i=0;i<6;i++)
        {

            floatlist.add(i,getRateFromInput(i));
        }
        return floatlist;
    }

    float getRateFromInput(int index){
        float currentRate = ratesMap.get(currencySelected).get(index);
        String userGivenValue = editTexts.get(index).getText().toString();
        if(isValidNumber(userGivenValue)){
           return Float.parseFloat(userGivenValue);
        }
        return currentRate;
    }

    private boolean isValidNumber(String userGivenInput) {
        try{
            float userEnteredValue = Float.parseFloat(userGivenInput);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;

    }



}
