package assignment.griffith.hari.currencyconvertor;

/**
 * Created by aahuyarakshakaharil on 26/10/17.
 */



public class CurrencyViewItem {

    private String currencyName;
    private String currencyCode;
    private Float currencyValue;

    public CurrencyViewItem(String currencyName, String currencyCode, Float currencyValue) {
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

    public Float getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Float currencyValue) {
        this.currencyValue = currencyValue;
    }
}
