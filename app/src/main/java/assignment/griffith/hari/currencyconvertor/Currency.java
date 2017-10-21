package assignment.griffith.hari.currencyconvertor;

/**
 * Created by aahuyarakshakaharil on 21/10/17.
 */

public class Currency {
    private String currencyName;
    private String currencyCode;
    private float currencyValue;

    public float getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(float currencyRate) {
        this.currencyRate = currencyRate;
    }

    private float currencyRate;

    public Currency(String currencyName, String currencyCode, float currencyValue) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.currencyValue = currencyValue;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public float getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(float currencyValue) {
        this.currencyValue = currencyValue;
    }
}
