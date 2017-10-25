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

    private ArrayList<Float> ratesList = new ArrayList<Float>();

    ArrayList<EditText> editTexts = new ArrayList<EditText>();
    //EditText[] editTexts = new EditText[6];
    Spinner conversionSpinner;
    private int currencySelected =0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversionlayout);

        Bundle bundle = getIntent().getExtras();
        ratesList = (ArrayList<Float>) bundle.get("rates");

        //ratesMap = MainActivity.ratesMap;

       // Resources resources = getResources();
        //conversionSpinner = (Spinner) findViewById(R.id.conversion_currency_spinner);

        populateEditTextArrayList();


        for (int i = 0; i<6; i++){
            if(i!=2)
            editTexts.get(i).setHint(Float.toString(ratesList.get(i)));
        }


    }

    private void populateEditTextArrayList() {

        editTexts.add(0,(EditText) findViewById(R.id.conv_aud));
        editTexts.add(1,(EditText) findViewById(R.id.conv_cad));
        editTexts.add(2,(EditText) findViewById(R.id.conv_cad));
        editTexts.add(3,(EditText) findViewById(R.id.conv_gbp));
        editTexts.add(4,(EditText) findViewById(R.id.conv_jpy));
        editTexts.add(5,(EditText) findViewById(R.id.conv_usd));

    }

    public void editRates(View args0) {
        ArrayList<Float> floatlist = new ArrayList<Float>();
        populateEditTextArrayList();
        for (int i=0;i<6;i++)
        {
            if(i==2)
                floatlist.add(i,1f);
            else
            floatlist.add(i,getRateFromInput(i));
        }

        Toast.makeText(this,"Values are persisted..!!",Toast.LENGTH_SHORT);
        Intent result = new Intent(Intent.ACTION_VIEW);
        result.putExtra("rates", floatlist);
        setResult(RESULT_OK, result);
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
        float currentRate = ratesList.get(index);
        String userGivenValue = editTexts.get(index).getText().toString();
        if(isValidNumber(userGivenValue)){
           return Float.parseFloat(userGivenValue);
        }
        return currentRate;
    }

    private boolean isValidNumber(String userGivenInput) {
        try{
            float userEnteredValue = Float.parseFloat(userGivenInput);

            if (userEnteredValue ==0)
                    throw new NumberFormatException();
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;

    }



}
