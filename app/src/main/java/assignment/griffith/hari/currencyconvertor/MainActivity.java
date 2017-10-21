package assignment.griffith.hari.currencyconvertor;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner currencyFrom;
    //private TextView aud,cad,eur,gbp,jpy,usd;
    private EditText inputValue;
    private ListView listView;

    private float userEnteredValue = -1.0f;
    private int currencySelected =0;

    private ArrayList<Currency> currencyArrayList;
    private CurrencyArrayAdaptor currencyArrayAdaptor;

    private TypedArray[] ratesArray = new TypedArray[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();

        currencyFrom = (Spinner) findViewById(R.id.currency_spinner);

        listView = (ListView) findViewById(R.id.currency_list);

        ratesArray[0] = resources.obtainTypedArray(R.array.AUD);
        ratesArray[1] = resources.obtainTypedArray(R.array.CAD);
        ratesArray[2] = resources.obtainTypedArray(R.array.EUR);
        ratesArray[3] = resources.obtainTypedArray(R.array.GBP);
        ratesArray[4] = resources.obtainTypedArray(R.array.JPY);
        ratesArray[5] = resources.obtainTypedArray(R.array.USD);

        currencyArrayList = new ArrayList<Currency>();

        TypedArray localArray = ratesArray[currencySelected];

        currencyArrayList.add(new Currency("Australian Dollar","AUD",localArray.getFloat(0,0)));
        currencyArrayList.add(new Currency("Canadian Dollar","CAD",localArray.getFloat(1,0)));
        currencyArrayList.add(new Currency("Great Britan Pound","GBP",localArray.getFloat(2,0)));
        currencyArrayList.add(new Currency("Euro","Eur",localArray.getFloat(3,0)));
        currencyArrayList.add(new Currency("Japanese Yen","JPY",localArray.getFloat(4,0)));
        currencyArrayList.add(new Currency("US Dollar","USD",localArray.getFloat(5,0)));




        currencyArrayAdaptor = new CurrencyArrayAdaptor(currencyArrayList,this);

        listView.setAdapter(currencyArrayAdaptor);

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

        //TypedArray localArray = ratesArray[currencySelected];




       // finalValue = (TextView) findViewById(R.id.output);
    }

    public void submit(View args0){

        userEnteredValue = 1;
        inputValue = (EditText) findViewById(R.id.input);
        //Call to validate input text
        if(isValidNumber(inputValue.getText().toString())){

            TypedArray localArray = ratesArray[currencySelected];

            currencyArrayList = new ArrayList<Currency>();
            currencyArrayList.add(new Currency("Australian Dollar","AUD",userEnteredValue*localArray.getFloat(0,0)));
            currencyArrayList.add(new Currency("Canadian Dollar","CAD",userEnteredValue*localArray.getFloat(1,0)));
            currencyArrayList.add(new Currency("Great Britan Pound","GBP",userEnteredValue*localArray.getFloat(2,0)));
            currencyArrayList.add(new Currency("Euro","Eur",userEnteredValue*localArray.getFloat(3,0)));
            currencyArrayList.add(new Currency("Japanese Yen","JPY",userEnteredValue*localArray.getFloat(4,0)));
            currencyArrayList.add(new Currency("US Dollar","USD",userEnteredValue*localArray.getFloat(5,0)));

            currencyArrayAdaptor = new CurrencyArrayAdaptor(currencyArrayList,this);
            listView.setAdapter(currencyArrayAdaptor);



            /*aud.setText(Float.toString(userEnteredValue*localArray.getFloat(0,0)));
            cad.setText(Float.toString(userEnteredValue*localArray.getFloat(1,0)));
            eur.setText(Float.toString(userEnteredValue*localArray.getFloat(2,0)));
            gbp.setText(Float.toString(userEnteredValue*localArray.getFloat(3,0)));
            jpy.setText(Float.toString(userEnteredValue*localArray.getFloat(4,0)));
            usd.setText(Float.toString(userEnteredValue*localArray.getFloat(5,0)));*/
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
