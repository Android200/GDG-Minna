package com.gdg.gdgminna;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FEEDBACK extends AppCompatActivity {
    EditText Name, Phone, Email,Location, Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Name =(EditText)findViewById(R.id.editTextName);
        Phone =(EditText) findViewById(R.id.editTextPhone);
        Email =(EditText) findViewById(R.id.editTextEmail);
        Location =(EditText) findViewById(R.id.editTextLocation);
        Comment =(EditText) findViewById(R.id.editTextComment);


    }

    public void SendFeedback(View v){
        if(Name.getText().toString().length()==0){
            Name.setError("Please Enter Your Name");
        }else if(Phone.getText().toString().length()==0){
            Phone.setError("Please Enter Your Phone Number");
        }else if(Email.getText().toString().length()==0){
            Email.setError("Please Enter Your Email Address");
        }else if(Location.getText().toString().length()==0){
            Location.setError("Please Enter Your Location");
        }else if (Comment.getText().toString().length()==0){
            Comment.setError("Please Give Your Comment");
        }else{
            String body=
                    "Name : "+Name.getText().toString()+"<br>Mobile : "+Phone.getText().toString()+
                            "<br>Email : "+Email.getText().toString()+"<br>Location : "+Location.getText().toString()+"<br>Comment : "+Comment.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"gdgminna@gmail.com"});
            email.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body));
            email.setType("message/rfc822");
            startActivityForResult(Intent.createChooser(email, "Choose an Email Client:"),1);
        }
    }




    public boolean onOptionsItemSelected(MenuItem item){
        int id;
        id = item.getItemId();

        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
