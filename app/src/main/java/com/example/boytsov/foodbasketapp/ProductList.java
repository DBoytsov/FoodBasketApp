package com.example.boytsov.foodbasketapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boytsov on 23.07.2015.
 */
public class ProductList extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,TextView.OnEditorActionListener {
    EditText myText;
    TextView myTextView;
    ListView lvMain;
    int mm=0;
    ArrayList<String> catnames;
    ArrayAdapter<String> adapter;
    Button button;
    DataBase db;
    Product product;
    final String LOG_TAG = "myLogs";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlist);
        db = new DataBase(this);
        myText = (EditText)findViewById(R.id.editText);
        lvMain = (ListView) findViewById(R.id.lvMain);

        button=(Button)findViewById(R.id.button);
        lvMain.setOnItemClickListener(this);
        lvMain.setOnItemLongClickListener(this);
        // Прослушиваем нажатия клавиш
        button.setOnClickListener(this);
        //слушаем edittext
        myText.setOnEditorActionListener(this);

        catnames= new ArrayList<String>();
        // создаем адаптер
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, catnames);
        ;
        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);




    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button : {
                //TextView myText = (TextView) view;

                //myText.setBackgroundColor(Color.TRANSPARENT);
                //myText.setPaintFlags(0);
                Log.d(LOG_TAG,"Insert: ");
                db.addProduct(product=new Product(mm,myText.getText().toString()));
                catnames.add(0, product.getName_product());

                adapter.notifyDataSetChanged();
                mm=+1;
                myText.setText("");

                Log.d("Reading: ", "Reading all contacts after button..");
                List<Product> products = db.getAllProducts();

                for (Product cn : products) {
                    String log = "Id: "+cn.getID_product()+" ,Name: " + cn.getName_product()+"isStrike " + cn.IsStrikeout();
                    // Writing Contacts to log
                    Log.d(LOG_TAG,"Name: " + log);
                }
            }

        break;

            }

    }
/**
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = (LinearLayout) inflater.inflate(R.layout.simple_list_item_1, parent, false);
        }

        TextView textview = (TextView) view.findViewById(R.id.text1);

        if (catnames.get(position).IsStrikeout()) {
            textview.setPaintFlags(16);
            textview.setBackgroundColor(Color.parseColor("#77dd77"));
            Toast.makeText(this, "Куплено", Toast.LENGTH_SHORT).show();
            Log.d(LOG_TAG, "itemClick: position = " + i + ", id = "
                    + l);
        } else {
            textview.setPaintFlags(0);
            textview.setBackgroundColor(Color.TRANSPARENT);
            Toast.makeText(this, "Не куплено", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
**/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textview = (TextView) view;
        Log.d(LOG_TAG, "catnames = " + catnames.get(i));
        Log.d(LOG_TAG, "itemClick: position = " + i + ", id = "
                + l);
        Product pr = db.getStrikeStatusByName(catnames.get(i));
        if (!pr.IsStrikeout()) {

            textview.setPaintFlags(16);
            textview.setBackgroundColor(Color.parseColor("#77dd77"));
            Toast.makeText(this, "Куплено", Toast.LENGTH_SHORT).show();
            pr.setIsstrikeout(true);
            db.updateProduct(pr);
            Log.d(LOG_TAG, "product after onItemClick if true = " +pr.getID_product()+" "+ pr.getName_product() +" " + pr.IsStrikeout());
        } else {
            textview.setPaintFlags(0);
            textview.setBackgroundColor(Color.TRANSPARENT);
            Toast.makeText(this, "Не куплено", Toast.LENGTH_SHORT).show();
            pr.setIsstrikeout(false);
            db.updateProduct(pr);
            Log.d(LOG_TAG, "product after onItemClick if false = " + pr.getName_product() +" " + pr.IsStrikeout());
        }
        Log.d("Reading: ", "Reading all contacts after onItemClick..");
        List<Product> products = db.getAllProducts();

        for (Product cn : products) {
            String log = "Id: " + cn.getID_product() + " ,Name: " + cn.getName_product() + "isStrike " + cn.IsStrikeout();
            // Writing Contacts to log
            Log.d(LOG_TAG, "Name: " + log);
        }
    }
        /**TextView textview= (TextView) view;
        mm = textview.getId();

        if (textview.getPaintFlags() != 16){
            textview.setPaintFlags(16);
            textview.setBackgroundColor(Color.parseColor("#77dd77"));
            Toast.makeText(this, "Куплено", Toast.LENGTH_SHORT).show();

            Log.d(LOG_TAG, "itemClick: position = " + i + ", id = "
                    + l +"mm"+mm);
         } else {
            textview.setPaintFlags(0);
            textview.setBackgroundColor(Color.TRANSPARENT);
            Toast.makeText(this, "Не куплено", Toast.LENGTH_SHORT).show();
                }**/


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        catnames.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show();

        Log.d(LOG_TAG, "onItemClick: position = " + position + ", id = "
                + id);
        return true;
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            Log.d(LOG_TAG, "onItemClick: position = " + actionId + ", id = "
                    + event);
            handled = true;
        }
        return handled;
    }


}


