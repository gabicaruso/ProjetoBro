package br.edu.insper.al.mayraprl.bro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnBack, btnSignIn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initializeComponents();
        clickEvents();
    }

    private void initializeComponents(){
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnBack = (Button) findViewById(R.id.buttonBack);
        btnSignIn = (Button) findViewById(R.id.buttonSignin);

    }

    private void clickEvents(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                createUser(email, password);
            }
        });
    }

    private void createUser (String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            alert("Usuário cadastrado com sucesso!");
                            Intent goToUsersStatus = new Intent(SignIn.this, UsersSettings.class);
                            startActivity(goToUsersStatus);
                            finish();
                        }else{
                            alert("Erro de cadastro");
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
        Toast.makeText(SignIn.this, msg,Toast.LENGTH_LONG).show();
    }
}
