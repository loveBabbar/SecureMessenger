package com.example.loveb.securemessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aes(View view)
    {
        Intent intent = new Intent( MainActivity.this, AES.class  );
        startActivity(intent);
    }
    public void des(View view)
    {
        Intent intent = new Intent( MainActivity.this, DES.class  );
        startActivity(intent);
    }
    public void rsa(View view)
    {
        Intent intent = new Intent( MainActivity.this, RSA.class  );
        startActivity(intent);
    }
    public void md5(View view)
    {
        Intent intent = new Intent( MainActivity.this, MD5.class  );
        startActivity(intent);
    }
}
