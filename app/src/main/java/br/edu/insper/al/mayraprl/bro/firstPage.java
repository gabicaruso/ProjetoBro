package br.edu.insper.al.mayraprl.bro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class firstPage extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnLogin, btnSignIn;
    private TextView txtResetPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        initializeComponents();
        clickEvents();
    }

    private void initializeComponents(){
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnSignIn = (Button) findViewById(R.id.buttonSignin);
        txtResetPassword = (TextView) findViewById(R.id.resetPassword);
    }

    private void clickEvents(){
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signingIn = new Intent(getApplicationContext(), SignIn.class);
                startActivity(signingIn);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                login(email, password);
            }
        });

        txtResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(firstPage.this, ResetPassword.class);
                startActivity(forgotPassword);
                finish();
            }
        });
    }

    private void login(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(firstPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent logginin = new Intent(firstPage.this, UsersSettings.class);
                            startActivity(logginin);
                        }else{
                            alert("Email ou senha incorretos.");
                        }
                    }
                });

    }

    @Override
    protected void onStart(){
        super.onStart();
        auth = Conection.getFirebaseAuth();
    }

    private void alert (String msg){
        Toast.makeText(firstPage.this, msg,Toast.LENGTH_LONG).show();
    }
}
