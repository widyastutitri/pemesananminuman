package com.example.prawoto.pemesananminuman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.string.no;
import static android.os.Build.VERSION_CODES.N;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view){//perintah tombol tambah
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama:"+name);

        CheckBox JUSALPOKATChekBox= (CheckBox) findViewById(R.id.JUSALPOKAT_checkbox);
        boolean hasJUSALPOKAT=JUSALPOKATChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has JUSALPOKAT:"+hasJUSALPOKAT);

        CheckBox JUSJAMBUChekBox= (CheckBox) findViewById(R.id.JUSJAMBU_checkbox);
        boolean hasJUSJAMBU=JUSJAMBUChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has JUALPOKAT:"+hasJUSJAMBU);

        CheckBox JUSWORTELChekBox= (CheckBox) findViewById(R.id.JUSWORTEL_checkbox);
        boolean hasJUSWORTEL=JUSWORTELChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has JUALPOKAT:"+hasJUSWORTEL);


        int price=calculateprice(hasJUSALPOKAT,hasJUSJAMBU,hasJUSWORTEL);//memanggil method jumlah harga
        String pricemessage=createOrderSummary(price,name,hasJUSALPOKAT,hasJUSJAMBU,hasJUSWORTEL);


        displayMessage(pricemessage);

    }
    private int calculateprice(boolean addALPOKAT,boolean addJAMBU,boolean addWORTEL){//jumlah pesanan * harga
        int harga=5000;

        if(addJAMBU){
            harga=harga+1000;//harga tambahan toping
        }

        if (addALPOKAT) {
            harga = harga + 2000;
        }
        if (addWORTEL){
                harga=harga+2000;
        }

        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean addJAMBU, boolean addALPOKAT, boolean addWORTEL) {//hasil pemesanan
        String pricemessage=" Nama ="+name;
        pricemessage+="\n add JUS ALPOKAT?"+addALPOKAT;
        pricemessage+="\n add JUS JAMBU?"+addJAMBU;
        pricemessage+="\n add JUS WORTEL?"+addWORTEL;
        pricemessage+="\n quantity"+quantity;
        pricemessage+="\n Total Rp"+price;
        pricemessage+="\n Thankyou";
        return  pricemessage;
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}
