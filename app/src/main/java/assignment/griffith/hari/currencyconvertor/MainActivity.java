package assignment.griffith.hari.currencyconvertor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    //Variable to read input spinner values
    private Spinner currencyFrom;

    //Array List to store name, code and rates wrt Euro in alphabetical order
    private ArrayList<Float> ratesList = new ArrayList<>();
    private ArrayList<String> codeList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();



    ArrayList<CurrencyViewItem> currencyViewItems = new ArrayList<CurrencyViewItem>();

    ListView listView;

    // Edit text which reads user input
    private EditText inputValue;
    // Float value of user entered input,defaulted to 1.0f
    private float userEnteredValue = 1.0f;

    //Pointer to identify spinner position / currency selected.
    private int currencySelected =0;

    CurrecyArrayAdapter currecyArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.rates_display_list_view); //Initialize our currency list View

        currencyFrom = (Spinner) findViewById(R.id.currency_spinner); // Initialize spinner


        // initializeTextViewArray(); // Method to initialize text View Array
        populateListFromTypedArray(getResources().obtainTypedArray(R.array.currency_name_list),nameList);
        populateListFromTypedArray(getResources().obtainTypedArray(R.array.currency_code_list),codeList);
        populateListFromTypedArrayFloat(getResources().obtainTypedArray(R.array.EUR),ratesList); // Method to read conversion rates from xml and store in to array list for easy navigation and mapping.



        inputValue = (EditText) findViewById(R.id.input); //Initialize edit text to read user input value

        currencyFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener to spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                currencySelected = position; // Updates Spinner position every time it changes.

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        populateValues(); // Method used to populate values in text views post conversion.

    }


    //This method is used to populate the conversion rates array list in alphabetically increasing order
    private void populateListFromTypedArrayFloat(TypedArray typedArray, ArrayList list){
        list.clear();

        for(int index =0;index<6;index++){
            list.add(index,typedArray.getFloat(index,0)); // Conversion from typed array to array list.
        }
    }

    private void populateListFromTypedArray(TypedArray typedArray, ArrayList list){
        list.clear();

        for(int index =0;index<6;index++){
            list.add(index,typedArray.getString(index)); // Conversion from typed array to array list.
        }
    }





    //Method invoked on click of submit button

    public void submit(View args0){

        inputValue = (EditText) findViewById(R.id.input); //Reading input value


        if(isValidNumber(inputValue.getText().toString())){ //Input Validation
            populateValues();
        }
        else //Ideally shouldn't be executed but writing it anyway to be fail safe..!!
        {
            Toast.makeText(this,"Please enter valid number..!! Showing values of defalut input 1.0",Toast.LENGTH_SHORT).show();
            populateValues();

        }

    }


    //Method invoked on click of edit currency rates button

    public void editCurrencyRates(View args0){

        Intent intent = new Intent(MainActivity.this, ConversionActivity.class);
        intent.putExtra("rates",ratesList); //Adding serializable ratesList Array to intent
        startActivityForResult(intent,0); //Starting activity with id = 0

    }

    public void populateValues(){
        currencyViewItems.clear();
        for(int index=0;index<6;index++)
        {
            if(index!=currencySelected)
            currencyViewItems.add(new CurrencyViewItem(nameList.get(index),codeList.get(index),userEnteredValue*getMultiplier(index)));
        }
        currecyArrayAdapter = new CurrecyArrayAdapter(this, currencyViewItems);
        listView.setAdapter(currecyArrayAdapter);
    }


    //Method Used to set final converted values in textbox to display to usr
    //private void populateValues(){

       /* for (int i =0; i<6;i++){
            textViews.get(i).setText(Float.toString(userEnteredValue*getMultiplier(i))); // Multiplies input with multiplier
        }*/




    //}

    //Method to get multiplier for output currency position.
    // Selects input currency based on spinner position
    private float getMultiplier(int output) {
        float to_rate = ratesList.get(output); //Get output currency rate.
        float inverse_from_rate = 1/(ratesList.get(currencySelected)); //Gets rate from ratesList ArrayList and setting inverse of it, since we need inverse of multiplier.
        return to_rate*inverse_from_rate;
    }



    //Input Validation Test Method is isValidNumberMethodTest
     boolean isValidNumber(String userGivenInput) {

        try{
             userEnteredValue = Float.parseFloat(userGivenInput);
        }
        catch (NumberFormatException e){
            //Setting default value in case of invalid input.
            userEnteredValue = 1.0f;
            //Toast.makeText(this,"Please enter valid number..!! Converting to default value 1.0",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    //This method will called on returning from second activity.
    protected void onActivityResult(int request, int result, Intent data) {
        if(request == 0 && result == RESULT_OK) {
            Bundle bundle = data.getExtras(); // Getting rates array from bundle.
            ratesList = (ArrayList<Float>) bundle.get("rates"); // Replace old rates with user edited rates.
            populateValues();
        }
    }


}
