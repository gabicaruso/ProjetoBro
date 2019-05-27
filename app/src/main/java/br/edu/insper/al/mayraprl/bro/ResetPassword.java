package br.edu.insper.al.mayraprl.bro;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity {

    private EditText editEmail;
    private Button btnResetPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initializeComponents();
        clickEvents();
    }

    private void initializeComponents(){
        editEmail = (EditText) findViewById(R.id.editResetEmail);
        btnResetPassword = (Button) findViewById(R.id.buttonResetPassword);
    }

    @Override
    protected void onStart(){
        super.onStart();
        auth = Conection.getFirebaseAuth();

    }

    private void clickEvents(){
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                resetPassword(email);
            }
        });
    }

    private void resetPassword(String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(ResetPassword.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            alert("Um email foi encaminhado para alteração de senha");
                            finish();
                        }else{
                            alert("O email fornecido não foi encontrado");
                        }
                    }
                });
    }
    private void alert (String msg){
        Toast.makeText(ResetPassword.this, msg,Toast.LENGTH_LONG).show();
    }
}

