package com.example.steps;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent ;


public class LoginActivit extends AppCompatActivity {

    EditText email , password ;
    Button loginbtn ;
    DBHelper DB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        loginbtn = (Button) findViewById(R.id.loginbtn) ;
        DB= new DBHelper(this);

        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email1 = email.getText().toString();
                String pass = password.getText().toString();
                if(email1.equals("")|| pass.equals("") )
                    Toast.makeText( LoginActivit.this ," please enter all fields" , Toast.LENGTH_LONG).show();
                else{
                    Boolean checkuserpassword = DB.checkpassword(email1 ,pass );
                    if(checkuserpassword== true){
                        Toast.makeText( LoginActivit.this ," log in successfully" , Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext() , Homepage.class );
                        startActivity(intent);

                    }else{
                        Toast.makeText( LoginActivit.this ," invalid email or password" , Toast.LENGTH_LONG).show();

                    }
                }



            }
        });

    }
}