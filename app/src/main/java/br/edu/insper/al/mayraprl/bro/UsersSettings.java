package br.edu.insper.al.mayraprl.bro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsersSettings extends AppCompatActivity {

    private TextView textEmail, textID;
    private Button btnLogOut;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_settings);
        initializeComponents();
        clickEvents();

    }

    private void clickEvents(){
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conection.logOut();
                finish();
            }
        });
    }

    private void initializeComponents(){
        textEmail = (TextView) findViewById(R.id.userEmail);
        textID = (TextView) findViewById(R.id.userID);
        btnLogOut = (Button) findViewById(R.id.buttonLogOut);

    }

    @Override
    protected void onStart(){
        super.onStart();
        auth = Conection.getFirebaseAuth();
        user = Conection.getFirebaseUser();
        verifyUser();
    }

    private void verifyUser(){
        if (user == null){
            finish();
        }else{
            textEmail.setText("Email:"+ user.getEmail());
            textID.setText("ID:"+ user.getUid());
        }
    }
}
