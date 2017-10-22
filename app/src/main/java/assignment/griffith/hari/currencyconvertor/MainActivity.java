package assignment.griffith.hari.currencyconvertor;

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

public class MainActivity extends AppCompatActivity {

    private Spinner currencyFrom;
    private TextView aud,cad,eur,gbp,jpy,usd;
    private EditText inputValue;
    private float userEnteredValue = 1.0f;
    private int currencySelected =0;

    private TypedArray[] ratesArray = new TypedArray[6];

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

        ratesArray[0] = resources.obtainTypedArray(R.array.AUD);
        ratesArray[1] = resources.obtainTypedArray(R.array.CAD);
        ratesArray[2] = resources.obtainTypedArray(R.array.EUR);
        ratesArray[3] = resources.obtainTypedArray(R.array.GBP);
        ratesArray[4] = resources.obtainTypedArray(R.array.JPY);
        ratesArray[5] = resources.obtainTypedArray(R.array.USD);



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

        TypedArray localArray = ratesArray[currencySelected];

        aud.setText(Float.toString(localArray.getFloat(0,0)));
        cad.setText(Float.toString(localArray.getFloat(1,0)));
        eur.setText(Float.toString(localArray.getFloat(2,0)));
        gbp.setText(Float.toString(localArray.getFloat(3,0)));
        jpy.setText(Float.toString(localArray.getFloat(4,0)));
        usd.setText(Float.toString(localArray.getFloat(5,0)));


       // finalValue = (TextView) findViewById(R.id.output);
    }

    public void submit(View args0){

        inputValue = (EditText) findViewById(R.id.input);
        //Call to validate input text
        if(isValidNumber(inputValue.getText().toString())){

            TypedArray localArray = ratesArray[currencySelected];

            aud.setText(Float.toString(userEnteredValue*localArray.getFloat(0,0)));
            cad.setText(Float.toString(userEnteredValue*localArray.getFloat(1,0)));
            eur.setText(Float.toString(userEnteredValue*localArray.getFloat(2,0)));
            gbp.setText(Float.toString(userEnteredValue*localArray.getFloat(3,0)));
            jpy.setText(Float.toString(userEnteredValue*localArray.getFloat(4,0)));
            usd.setText(Float.toString(userEnteredValue*localArray.getFloat(5,0)));
        }

        else
        {
            Toast.makeText(this,"Please enter valid number..!!",Toast.LENGTH_LONG);
        }

    }

    private boolean isValidNumber(String userGivenInput) {

        try{
             userEnteredValue = Float.parseFloat(userGivenInput);
        }
        catch (NumberFormatException e){
            Toast.makeText(this,"Please enter valid number..!!",Toast.LENGTH_LONG);
            return false;
        }
        return true;

    }
}
