package br.edu.insper.al.mayraprl.bro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.internal.NonNull;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth auth;

    private void createUser (String email, String password){
        this.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            alert("Usuário cadastrado com sucesso!");
                            Intent goToUsersStatus = new Intent(SignIn.this, Permission.class);
                            startActivity(goToUsersStatus);
                            finish();
                        }else{
                            alert("Erro de cadastro");
                        }
                    }
                });
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);

        String customToken = FirebaseAuth.getInstance().createCustomTokenAsync(UID).get();
        System.out.println(customToken);
    }

}

/*
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
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class SignIn extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnBack, btnSignIn;
    private com.google.firebase.auth.FirebaseAuth auth;



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
        this.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            alert("Usuário cadastrado com sucesso!");
                            Intent goToUsersStatus = new Intent(SignIn.this, Permission.class);
                            startActivity(goToUsersStatus);
                            finish();
                        }else{
                            alert("Erro de cadastro");
                        }
                    }
                });
    }

    private static final String UID = "some_uid";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);

        String customToken = FirebaseAuth.getInstance().createCustomTokenAsync(UID).get();
        System.out.println(customToken);
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
*/