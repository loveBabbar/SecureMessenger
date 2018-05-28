package com.example.loveb.securemessenger;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;

public class MD5 extends AppCompatActivity {

    ///             THIS TECHNIQUE IS USED FOR GETTING A SECURE PASSWORD
    ///this is hashing not ENCRYPTION
    ///we cannot decrypr this, the only thing we can do is just try to match a millions of inputs and wait for the correct one
    //generrally use for checking data loss and for making passsword

    String password="";
    Button hash_button,clear;
        EditText input_text;
        TextView output_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5);
        input_text=(EditText) findViewById(R.id.input_text);
        output_text=(TextView)findViewById(R.id.output_text);
        hash_button=(Button) findViewById(R.id.clear_button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=input_text.getText().toString();
                if(password.length()>0)
                output_text.setText(hashPassword(password));
                else
                    Toast.makeText(MD5.this,"Empty output",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String hashPassword(String password)
    {
        String passwordtohash=password;
        String generatedPassword=null;
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            //Returns a MessageDigest object that implements the specified digest algorithm.
            md.update(passwordtohash.getBytes());
            //Updates the digest using the specified array of bytes.
            byte[] bytes=md.digest();
            //Completes the hash computation by performing final operations such as padding.
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<bytes.length;i++)
            {
                sb.append(Integer.toString(( bytes[i]&0xff)+0x100,32).substring(1));
                //pehle widen krra h and baad me 0x100 add krra nd hexadecimal me convert krde
                // //and pehla character utha leta and builder me add krde
            }
            generatedPassword=sb.toString();
            Log.d("BABBAR","generated password"+generatedPassword);
            return generatedPassword;

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void getspeechinput(View view) {

        Intent intent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent,1001);
        }
        else
        {
            Toast.makeText(this,"your device does not support this feature",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case 1001:
                if(resultCode==RESULT_OK&&data!=null)
                {
                    ArrayList<String> res=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    input_text.setText(res.get(0));
                }
                break;
        }
    }
}
//This MessageDigest class provides applications the functionality of a message digest algorithm,
// such as SHA-1 or SHA-256. Message digests are secure one-way hash functions that take arbitrary-sized data
// and output a fixed-length hash value.
//A MessageDigest object starts out initialized. The data is processed through it using the update methods.
// At any point reset can be called to reset the digest. Once all the data to be updated has been updated,
// one of the digest methods should be called to complete the hash computation.