package com.furkankaraketir.necatdernegi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class KullaniciTanimlamaActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici_tanimlama)

        auth = Firebase.auth

        val db = Firebase.firestore

        val emailEditText = findViewById<TextInputLayout>(R.id.newUserEmailTextField)
        val passwordEditText = findViewById<TextInputLayout>(R.id.newUserPasswordTextField)

        val newUserNameTextField = findViewById<TextInputLayout>(R.id.newUserNameTextField)

        val newUserLastnameTextField = findViewById<TextInputLayout>(R.id.newUserLastnameTextField)

        val newUserMissionTextField = findViewById<TextInputLayout>(R.id.newUserMissionTextField)

        val newUserAddressTextField = findViewById<TextInputLayout>(R.id.newUserAddressTextField)

        val newUserPhoneNumberTextField =
            findViewById<TextInputLayout>(R.id.newUserPhoneNumberTextField)

        val newUserTypeTextField = findViewById<TextInputLayout>(R.id.newUserTypeTextField)

        val newUserSaveButton = findViewById<Button>(R.id.newUserSaveButton)

        newUserSaveButton.setOnClickListener {
            val email = emailEditText.editText?.text.toString()
            val password = passwordEditText.editText?.text.toString()
            val ad = newUserNameTextField.editText?.text.toString()
            val soyad = newUserLastnameTextField.editText?.text.toString()
            val gorev = newUserMissionTextField.editText?.text.toString()
            val adres = newUserAddressTextField.editText?.text.toString()
            val telefon = newUserPhoneNumberTextField.editText?.text.toString()
            val tip = newUserTypeTextField.editText?.text.toString()
            val tarih = System.currentTimeMillis()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = hashMapOf(
                            "ad" to ad,
                            "adres" to adres,
                            "email" to email,
                            "gorev" to gorev,
                            "irtibatTelNo" to telefon,
                            "kayitTarihi" to tarih,
                            "soyad" to soyad,
                            "tip" to tip
                        )

                        db.collection("Kullanicilar")
                            .add(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Başarılı...", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, KullaniciIslemleriActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Hata...", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Hata...", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}