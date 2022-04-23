package com.example.steps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent ;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// test 2
    EditText email , password , confirmpassword ;
    Button signup , signin ;
    DBHelper DB;
    Boolean insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.signupbtn) ;
        signin = (Button) findViewById(R.id.signinbtn) ;
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email1 = email.getText().toString();
                String pass = password.getText().toString();
                String confirmpass = confirmpassword.getText().toString();

                if (email1.equals("")|| pass.equals("") || confirmpass.equals("") )
                    Toast.makeText( MainActivity.this ," please enter all fields" , Toast.LENGTH_LONG).show();
                else { // if user exist
                    if (pass.equals(confirmpass)) {
                        Boolean checkemail = DB.checkemail(email1);
                        if (checkemail == false) {
                            insert = DB.insertData(email1, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Registered Successfully ", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), Homepage.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(MainActivity.this, " Registration failed", Toast.LENGTH_LONG).show();

                            }
                        } else {
                            Toast.makeText(MainActivity.this, " user already exist , please log in ", Toast.LENGTH_LONG).show();

                        }
                    }else {
                        Toast.makeText(MainActivity.this, " Passwords not matching", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent ( getApplicationContext() , LoginActivit.class );
                startActivity(intent);

            }
        });
    }
}