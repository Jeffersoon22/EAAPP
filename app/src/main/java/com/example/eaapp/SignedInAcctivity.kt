package com.example.eaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SignedInAcctivity : AppCompatActivity() {
    private lateinit var user: FirebaseUser
    private lateinit var userId : String
    private lateinit var reference: DatabaseReference
    private lateinit var signOut: Button
    lateinit var nameView: TextView
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signedin)
        mAuth =  FirebaseAuth.getInstance()
        user = mAuth.currentUser!!;
        reference = Firebase.database.getReference("Users")
        userId = user.uid
        reference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo = snapshot.getValue(User::class.java)
                if (userInfo != null){
                    nameView.text = userInfo.firstName
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignedInAcctivity, "aaaaauuuuuuuuuuuuuuuu!", Toast.LENGTH_LONG).show()
            }
        })
        signOut = findViewById(R.id.signOut)
        signOut.setOnClickListener{
            mAuth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }

    }
}