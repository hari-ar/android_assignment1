package assignment.griffith.hari.currencyconvertor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aahuyarakshakaharil on 25/10/17.
 */
public class MainActivityTest {


    @Test
    public void isValidNumberMethodTest(){
        MainActivity mainActivity = new MainActivity();
        boolean zeroInput = mainActivity.isValidNumber("0");
        assertEquals(zeroInput,true);
        boolean emptyInput = mainActivity.isValidNumber("");
        assertEquals(emptyInput,false);
        boolean stringInput = mainActivity.isValidNumber("A");
        assertEquals(emptyInput,false);
        boolean validCase = mainActivity.isValidNumber("1.0");
        assertEquals(validCase,true);
    }



}