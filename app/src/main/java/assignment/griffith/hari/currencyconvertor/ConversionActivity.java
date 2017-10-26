package assignment.griffith.hari.currencyconvertor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by aahuyarakshakaharil on 22/10/17.
 */

public class ConversionActivity extends Activity {

    //Similar rates array to store rates.
    private ArrayList<Float> ratesList = new ArrayList<Float>();

    //Edit Texts array to store input rates entered by user.
    ArrayList<EditText> editTexts = new ArrayList<EditText>();

    private boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_rate_view_layout);

        Bundle bundle = getIntent().getExtras();
        ratesList = (ArrayList<Float>) bundle.get("rates");

        //Method to set edit text into an array list
        populateEditTextArrayList();


        //Loop to populate hints in edit text
        for (int index = 0; index<6; index++){
            if(index!=2)//Skip Eur value as it is always 1. and index 2 is Eur value which we are no displaying..
            editTexts.get(index).setHint(Float.toString(ratesList.get(index)));
        }


    }
    //Method to set edit text into an array list
    private void populateEditTextArrayList() {

        editTexts.add(0,(EditText) findViewById(R.id.conv_aud));
        editTexts.add(1,(EditText) findViewById(R.id.conv_cad));
        editTexts.add(2,(EditText) findViewById(R.id.conv_cad)); // Dummy value to continue the sequence. Hacky way but this makes it simpler..
        editTexts.add(3,(EditText) findViewById(R.id.conv_gbp));
        editTexts.add(4,(EditText) findViewById(R.id.conv_jpy));
        editTexts.add(5,(EditText) findViewById(R.id.conv_usd));

    }

    // Method called on submit of submitbutton.
    public void editRates(View args0) {
        flag = true;
        ArrayList<Float> floatlist = new ArrayList<Float>();
        //Read all values again
        populateEditTextArrayList();
        for (int index=0;index<6;index++)
        {
            if(index==2)
                floatlist.add(index,1f); //Setting default value to Eur
            else
            floatlist.add(index,getRateFromInput(index));
        }

        if(flag){
            Toast.makeText(this,"Values are persisted..!!",Toast.LENGTH_SHORT).show();
            Intent result = new Intent(Intent.ACTION_VIEW);
            result.putExtra("rates", floatlist);
            setResult(RESULT_OK, result);
            finish();
        }
    }



    //Take index and return a valid rate based on user input
    //If user entered value is not valid or didn't enter any value, default rates are returned.
    float getRateFromInput(int index){
        float currentRate = ratesList.get(index); //Set current rate in currentRate
        String userGivenValue = editTexts.get(index).getText().toString(); //Read user input in to a string
        if(isValidNumber(userGivenValue)){ //validate user input
           return Float.parseFloat(userGivenValue);
        }
        return currentRate;
    }

    //Method to validate input
    private boolean isValidNumber(String userGivenInput) {
        try{
            //Trying to parse float value
            float userEnteredValue = Float.parseFloat(userGivenInput);
            //Validating for 0 input
            if (userEnteredValue == 0){
                flag = false;
                Toast.makeText(this,"Invalid conversion rate, 0 is not valid",Toast.LENGTH_SHORT).show();
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;

    }



}
