package com.gdg.gdgminna;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DISCLAIMER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView gdglink= (TextView)findViewById(R.id.gdgminna);

        gdglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://docs.google.com/forms/d/1ZoR9T6LTxERRjTL-TUrmMOW-SJjVN_76lquinFhcn-8/viewform";
                Intent i= new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
