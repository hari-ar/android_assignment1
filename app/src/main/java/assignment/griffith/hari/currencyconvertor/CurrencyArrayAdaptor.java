package assignment.griffith.hari.currencyconvertor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by aahuyarakshakaharil on 21/10/17.
 */

public class CurrencyArrayAdaptor extends BaseAdapter {

    private ArrayList<Currency> currencyArrayList;
    private Context context;

    static class CurrencyViewHolder{
        public TextView nameTextView;
        public TextView codeTextView;
        public TextView valueTextView;
    }

    public CurrencyArrayAdaptor(ArrayList<Currency> currencyArrayList, Context context) {
        this.currencyArrayList = currencyArrayList;
        this.context = context;
    }

    static class ViewHolder {
        public TextView tv_name;
        public TextView tv_date;
    }

    @Override
    public int getCount() {
        return currencyArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return currencyArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CurrencyViewHolder currencyViewHolder;

        if(convertView == null){
            currencyViewHolder = new CurrencyViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.currency_display_layout,parent,false);

            currencyViewHolder.codeTextView = (TextView) convertView.findViewById(R.id.currency_code);
            currencyViewHolder.nameTextView = (TextView) convertView.findViewById(R.id.currency_name);
            currencyViewHolder.valueTextView = (TextView) convertView.findViewById(R.id.currency_value);

            convertView.setTag(currencyViewHolder);
        }
        else
        {
            currencyViewHolder = (CurrencyViewHolder) convertView.getTag();
        }
//            currencyViewHolder.codeTextView.setText(currencyArrayList.get(position).getCurrencyCode());
            currencyViewHolder.nameTextView.setText(currencyArrayList.get(position).getCurrencyName());
            currencyViewHolder.valueTextView.setText(String.valueOf(currencyArrayList.get(position).getCurrencyValue()));

            return convertView;
    }
}
