package com.example.ssgmce;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;


public class PaymentGateway extends AppCompatActivity implements PaymentResultListener {
    EditText amount;
    Button make_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_gateway);

        amount = findViewById(R.id.amount);
        make_payment = findViewById(R.id.make_payment);

        make_payment.setOnClickListener(view -> {

            double payment_value = Math.round(Double.parseDouble(String.valueOf(amount.getText())));


            Log.d("From onclick id make_payment", "onClick: amount = " + payment_value);


            Checkout checkout=new Checkout();

            checkout.setKeyID("rzp_test_GWNcjMx4YJOFy7");

            checkout.setImage(R.drawable.ic_launcher_foreground);

            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("name","MR.Pranajay Kandekar(RSS)");

                jsonObject.put("description" , "Private Room Payment");

                jsonObject.put("theme.color","black");

                jsonObject.put("currency","INR");

                jsonObject.put("amount", payment_value*100);

                jsonObject.put("prefill.contact","6969696969");

                jsonObject.put("prefill.email","pantya.is1.5@gmail.com");

                if (payment_value>=1 && payment_value <=1000000) {
                    checkout.open(PaymentGateway.this, jsonObject);
                }
                else{
                    Toast.makeText(this,"Invalid Amount ",Toast.LENGTH_LONG).show();
                }


            }catch (JSONException e){
                e.printStackTrace();
            }





        });
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"Your payment is successful : "+s,Toast.LENGTH_LONG).show();
        Log.d("From", "onPaymentSuccess: "+s);
    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(this,"Your payment is unsuccessful : "+s,Toast.LENGTH_LONG).show();
        Log.d("From", "onPaymentError: "+s);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}