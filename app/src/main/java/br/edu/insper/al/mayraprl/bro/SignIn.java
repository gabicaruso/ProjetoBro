package br.edu.insper.al.mayraprl.bro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignIn extends AppCompatActivity {
    private EditText editEmail, editPassword, editFirstName, editLastName, editBirthday, editCPF, editState, editCity, editStreet;
    private DatabaseReference databaseCadastro;
    private FirebaseAuth firebaseAuth;
  

    @Override
    protected void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_sign_in);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editBirthday = findViewById(R.id.editBirthday);
        editCPF = findViewById(R.id.editCPF);
        editState = findViewById(R.id.editState);
        editCity = findViewById(R.id.editCity);
        editStreet = findViewById(R.id.editStreet);

        Button btnBack = findViewById(R.id.buttonBack);
        Button btnSignIn = findViewById(R.id.buttonSignin);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseCadastro = FirebaseDatabase.getInstance().getReference();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String email = editEmail.getText().toString().trim();
               String password = editPassword.getText().toString().trim();
               final String firstName = editFirstName.getText().toString().trim();
               final String lastName = editLastName.getText().toString().trim();
               final String birthdate = editBirthday.getText().toString().trim();
               final String cpf = editCPF.getText().toString().trim();
               final String estado = editState.getText().toString().trim();
               final String city = editCity.getText().toString().trim();
               final String street = editStreet.getText().toString().trim();

               if (TextUtils.isEmpty(email)) {
                   Toast.makeText(SignIn.this, "Email inválido", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(password)) {
                   Toast.makeText(SignIn.this, "Senha inválida", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(firstName)) {
                   Toast.makeText(SignIn.this, "Nome inválido", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(lastName)) {
                   Toast.makeText(SignIn.this, "Nome inválido", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(birthdate)) {
                   Toast.makeText(SignIn.this, "Data inválida", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(cpf)) {
                   Toast.makeText(SignIn.this, "CPF inválido", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(estado)) {
                   Toast.makeText(SignIn.this, "Estado inválido", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(city)) {
                   Toast.makeText(SignIn.this, "Cidade inválida", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(street)) {
                   Toast.makeText(SignIn.this, "Rua inválida", Toast.LENGTH_SHORT).show();
                   return;
               }

               firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {

                           FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                           String id = user.getUid();

                           newUser(firstName, email, id, lastName, birthdate, cpf, estado, city, street);

                           startActivity(new Intent(getApplicationContext(), Permission.class));
                           Toast.makeText(SignIn.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                       } else {
                           Toast.makeText(SignIn.this, "Erro de cadastro!", Toast.LENGTH_SHORT).show();
                       }

                   }

                   });
               }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(SignIn.this, firstPage.class);
                startActivityForResult(returnIntent, 1);
            }
        });

    }

    private void newUser(String name, String email, String id, String last, String date, String cpf, String estado, String city, String street) {
        Cadastro user = new Cadastro(name, email, id, last, date, cpf, estado, city, street);
        databaseCadastro.child("cadastro").child(id).setValue(user);
    }


}
