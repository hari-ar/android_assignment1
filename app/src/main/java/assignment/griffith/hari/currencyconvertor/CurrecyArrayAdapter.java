package assignment.griffith.hari.currencyconvertor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aahuyarakshakaharil on 26/10/17.
 */



public class CurrecyArrayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CurrencyViewItem> currencyViewItems;

    static class ViewHolder {
        public TextView currencyName;
        public TextView currencyCode;
        public TextView currencyValue;
    }

    public CurrecyArrayAdapter(Context context, ArrayList<CurrencyViewItem> currencyViewItems) {
        this.context = context;
        this.currencyViewItems = currencyViewItems;
    }

    @Override
    public int getCount() {
        return currencyViewItems.size();

    }

    @Override
    public Object getItem(int position) {
        return currencyViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.currency_display_layout, parent, false);
            viewHolder.currencyName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.currencyCode = (TextView) convertView.findViewById(R.id.code);
            viewHolder.currencyValue = (TextView) convertView.findViewById(R.id.value);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();


        }
        viewHolder.currencyName.setText(currencyViewItems.get(position).getCurrencyName());
        viewHolder.currencyCode.setText(currencyViewItems.get(position).getCurrencyCode());
        viewHolder.currencyValue.setText(Float.toString(currencyViewItems.get(position).getCurrencyValue()));

        return convertView;
    }
}
