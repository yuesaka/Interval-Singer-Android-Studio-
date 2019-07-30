package eecs499.eartrainer.intervalsinger

import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : Activity() {
    private val TAG: String? = LoginActivity::class.simpleName;
    private lateinit var firebaseAuth: FirebaseAuth
    private var newUser: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
//        newUser = findViewById(R.id.newUserButton)
//        newUser?.setOnClickListener {
//            createNewUser()
//        }
    }

    private fun createNewUser() {
        firebaseAuth.createUserWithEmailAndPassword("yuuuuuuuma@gmail.com", "123456789")
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = firebaseAuth.currentUser
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }

                    // ...
                }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = firebaseAuth.currentUser
        //updateUI(currentUser)
    }


}
