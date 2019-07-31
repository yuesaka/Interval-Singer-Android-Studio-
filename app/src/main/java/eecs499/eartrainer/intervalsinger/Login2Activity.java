package eecs499.eartrainer.intervalsinger;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;

public class Login2Activity extends Activity {
    private static final String TAG = Login2Activity.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private Button submit, guestLogin;
    private AutoCompleteTextView email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        submit = findViewById(R.id.email_sign_in_button);
        guestLogin = findViewById(R.id.login_as_guest);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
            }
        });
        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    private void createNewUser() {
        //firebaseAuth.u
        if (email.getText().length() == 0 || password.getText().length() == 0) {
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(Login2Activity.this, "New User Created",
                                    Toast.LENGTH_SHORT).show();
                            startMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            signIn();
                           // updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void signIn() {
        if (email.getText().length() == 0 || password.getText().length() == 0) {
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            startMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login2Activity.this, "Email Already Exists and Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(this, "currentUser: " + currentUser.getEmail(), Toast.LENGTH_LONG).show();
            startMainActivity();
        }
        //updateUI(currentUser)
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
