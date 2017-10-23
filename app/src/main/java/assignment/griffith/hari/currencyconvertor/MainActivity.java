package assignment.griffith.hari.currencyconvertor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    private Spinner currencyFrom;

    public static HashMap<Integer,ArrayList<Float>> ratesMap = new HashMap<Integer,ArrayList<Float>>();

    ArrayList<TextView> textViews = new ArrayList<TextView>();
    private EditText inputValue;
    private float userEnteredValue = 1.0f;
    private int currencySelected =0;


    //public static ArrayList<TypedArray> ratesArray = new ArrayList<TypedArray>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Resources resources = getResources();

        currencyFrom = (Spinner) findViewById(R.id.currency_spinner);

        initializeTextViewArray();
        populateRatesMap();
        inputValue = (EditText) findViewById(R.id.input);

        currencyFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencySelected = position;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        populateValues();

    }

    private ArrayList<Float> getFloatArrayFromTypedArray(TypedArray typedArray) {
        ArrayList<Float> localRateFloatArray = new ArrayList<Float>();
        for(int i =0;i<6;i++){
            localRateFloatArray.add(i,typedArray.getFloat(i,0));
        }
        return localRateFloatArray;

    }




    private void populateRatesMap(){
        Resources resources = getResources();
        ratesMap.put(0,getFloatArrayFromTypedArray(resources.obtainTypedArray(R.array.AUD)));
        ratesMap.put(1,getFloatArrayFromTypedArray(resources.obtainTypedArray(R.array.CAD)));
        ratesMap.put(2,getFloatArrayFromTypedArray(resources.obtainTypedArray(R.array.EUR)));
        ratesMap.put(3,getFloatArrayFromTypedArray(resources.obtainTypedArray(R.array.GBP)));
        ratesMap.put(4,getFloatArrayFromTypedArray(resources.obtainTypedArray(R.array.JPY)));
        ratesMap.put(5,getFloatArrayFromTypedArray(resources.obtainTypedArray(R.array.USD)));
    }

    public void submit(View args0){

        inputValue = (EditText) findViewById(R.id.input);
        if(isValidNumber(inputValue.getText().toString())){
            populateValues();
        }
        else
        {
            Toast.makeText(this,"Please enter valid number..!! Showing values of defalut",Toast.LENGTH_LONG);
            populateValues();

        }

    }

    public void editCurrencyRates(View args0){

        Intent intent = new Intent(MainActivity.this, ConversionActivity.class);
        //intent.putExtra("rates",ratesMap);
        startActivity(intent);

    }

    private void populateValues(){

        for (int i =0; i<6;i++){
            textViews.get(i).setText(Float.toString(userEnteredValue*ratesMap.get(currencySelected).get(i)));
        }

    }

    private void initializeTextViewArray(){
        textViews.add(0,(TextView) findViewById(R.id.aud_value));
        textViews.add(1,(TextView) findViewById(R.id.cad_value));
        textViews.add(2,(TextView) findViewById(R.id.eur_value));
        textViews.add(3,(TextView) findViewById(R.id.gbp_value));
        textViews.add(4,(TextView) findViewById(R.id.jpy_value));
        textViews.add(5,(TextView) findViewById(R.id.usd_value));
    }

    private boolean isValidNumber(String userGivenInput) {

        try{
             userEnteredValue = Float.parseFloat(userGivenInput);
        }
        catch (NumberFormatException e){
            userEnteredValue = 1.0f;
            Toast.makeText(this,"Please enter valid number..!!",Toast.LENGTH_LONG);
            return false;
        }
        return true;

    }


}
