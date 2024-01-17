package com.example.flashcard_v3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.database.*

class FragmentLoginSignup : Fragment() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignup: Button
    private lateinit var buttonLogin: Button

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_signup, container, false)

        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        buttonSignup = view.findViewById(R.id.buttonSignup)
        buttonLogin = view.findViewById(R.id.buttonLogin)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        buttonSignup.setOnClickListener {
            val username = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty()) {
                signup(username, password)
            } else {
                Log.d("Signup", "Username or password is empty")
                Toast.makeText(requireActivity(), "Please enter both username and password", Toast.LENGTH_LONG).show()
            }
        }

        buttonLogin.setOnClickListener {
            val username = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Log.d("Login", "Username or password is empty")
                Toast.makeText(requireActivity(), "Please enter both username and password", Toast.LENGTH_LONG).show()
            }
        }

        //signup("mert4x4","mert2002")

        return view
    }

    private fun signup(username: String, password: String) {
        databaseReference.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("Signup", "Data snapshot exists: ${dataSnapshot.exists()}")
                    if (!dataSnapshot.exists()) {
                        val id = databaseReference.push().key
                        val userData = UserData(id, username, password)
                        databaseReference.child(id!!).setValue(userData)
                        Log.d("Signup", "Signup Successful")
                        Toast.makeText(requireActivity(), "Signup Successful", Toast.LENGTH_LONG).show()
                        view?.findNavController()?.navigate(R.id.action_fragmentLoginSignup_to_fragment_AllCards)
                    } else {
                        Log.d("Signup", "Username already exists")
                        Toast.makeText(requireActivity(), "Username already exists", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("Signup", "Error: ${databaseError.message}")
                    Toast.makeText(requireActivity(), "Error: ${databaseError.message}", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun login(username: String, password: String) {
        databaseReference.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("Login", "Data snapshot exists: ${dataSnapshot.exists()}")
                    if (dataSnapshot.exists()) {
                        for (userSnapshot in dataSnapshot.children) {
                            val userData = userSnapshot.getValue(UserData::class.java)
                            if (userData != null && userData.password == password) {
                                Log.d("Login", "Login Successful")
                                Toast.makeText(requireActivity(), "Login Successful", Toast.LENGTH_LONG).show()
                                view?.findNavController()?.navigate(R.id.action_fragmentLoginSignup_to_fragment_AllCards)
                            } else {
                                Log.d("Login", "incorrect password")
                                Toast.makeText(requireActivity(), "Incorrect Password", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        // User not found
                        Log.d("Login", "User not found")
                        Toast.makeText(requireActivity(), "User not found", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors, if any
                    Log.e("Login", "Error: ${databaseError.message}")
                    Toast.makeText(requireActivity(), "Error: ${databaseError.message}", Toast.LENGTH_LONG).show()
                }
            })
    }
}

