package assignment.griffith.hari.currencyconvertor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Spinner currencyFrom;
    private TextView aud,cad,eur,gbp,jpy,usd;
    private EditText inputValue;
    private float userEnteredValue = 1.0f;
    private int currencySelected =0;


    public static ArrayList<TypedArray> ratesArray = new ArrayList<TypedArray>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources resources = getResources();

        currencyFrom = (Spinner) findViewById(R.id.currency_spinner);
        aud = (TextView) findViewById(R.id.aud_value);
        cad = (TextView) findViewById(R.id.cad_value);
        eur = (TextView) findViewById(R.id.eur_value);
        gbp = (TextView) findViewById(R.id.gbp_value);
        jpy = (TextView) findViewById(R.id.jpy_value);
        usd = (TextView) findViewById(R.id.usd_value);




        ratesArray.add(0, resources.obtainTypedArray(R.array.AUD));
        ratesArray.add(1, resources.obtainTypedArray(R.array.CAD));
        ratesArray.add(2, resources.obtainTypedArray(R.array.EUR));
        ratesArray.add(3, resources.obtainTypedArray(R.array.GBP));
        ratesArray.add(4, resources.obtainTypedArray(R.array.JPY));
        ratesArray.add(5, resources.obtainTypedArray(R.array.USD));



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
        //intent.putExtra("rates",ratesArray);
        startActivity(intent);

    }

    private void populateValues(){

        TypedArray localArray = ratesArray.get(currencySelected);

        aud.setText(Float.toString(userEnteredValue*localArray.getFloat(0,0)));
        cad.setText(Float.toString(userEnteredValue*localArray.getFloat(1,0)));
        eur.setText(Float.toString(userEnteredValue*localArray.getFloat(2,0)));
        gbp.setText(Float.toString(userEnteredValue*localArray.getFloat(3,0)));
        jpy.setText(Float.toString(userEnteredValue*localArray.getFloat(4,0)));
        usd.setText(Float.toString(userEnteredValue*localArray.getFloat(5,0)));
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
