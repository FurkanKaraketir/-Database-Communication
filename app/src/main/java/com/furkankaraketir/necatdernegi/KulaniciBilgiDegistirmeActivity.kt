package com.furkankaraketir.necatdernegi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class KulaniciBilgiDegistirmeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kulanici_bilgi_degistirme)

        auth = Firebase.auth

        val db = Firebase.firestore
        val hata = "Bu Alan Boş Bırakılamaz"

        var ad = intent.getStringExtra("ad")
        var adres = intent.getStringExtra("adres")
        val email = intent.getStringExtra("email")
        var gorev = intent.getStringExtra("gorev")
        var irtibatTelNo = intent.getStringExtra("irtibatTelNo")
        val kayitTarihi = intent.getStringExtra("kayitTarihi")
        var soyad = intent.getStringExtra("soyad")
        var tip = intent.getStringExtra("tip")

        var docId = ""

        val oldUserNameTextField = findViewById<TextInputLayout>(R.id.oldUserNameTextField)

        val oldUserLastnameTextField = findViewById<TextInputLayout>(R.id.oldUserLastnameTextField)

        val oldUserMissionTextField = findViewById<TextInputLayout>(R.id.oldUserMissionTextField)

        val oldUserAddressTextField = findViewById<TextInputLayout>(R.id.oldUserAddressTextField)

        val oldUserPhoneNumberTextField =
            findViewById<TextInputLayout>(R.id.oldUserPhoneNumberTextField)

        val oldUserTypeTextField = findViewById<TextInputLayout>(R.id.oldUserTypeTextField)

        val oldUserSaveButton = findViewById<Button>(R.id.oldUserSaveButton)

        oldUserNameTextField.editText?.setText(ad)
        oldUserLastnameTextField.editText?.setText(soyad)
        oldUserMissionTextField.editText?.setText(gorev)
        oldUserAddressTextField.editText?.setText(adres)
        oldUserPhoneNumberTextField.editText?.setText(irtibatTelNo)
        oldUserTypeTextField.editText?.setText(tip)

        db.collection("Kullanicilar")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document["email"].toString() == email) {
                        docId = document.id
                    }
                }
            }
        oldUserSaveButton.setOnClickListener {

            ad = oldUserNameTextField.editText?.text.toString()
            soyad = oldUserLastnameTextField.editText?.text.toString()
            adres = oldUserAddressTextField.editText?.text.toString()
            gorev = oldUserMissionTextField.editText?.text.toString()
            irtibatTelNo = oldUserPhoneNumberTextField.editText?.text.toString()
            tip = oldUserTypeTextField.editText?.text.toString()

            if (ad!!.isEmpty()) {
                oldUserNameTextField.error = hata
            } else {
                oldUserNameTextField.error = null

                if (soyad!!.isEmpty()) {
                    oldUserLastnameTextField.error = hata
                } else {
                    oldUserLastnameTextField.error = null
                    if (gorev!!.isEmpty()) {
                        oldUserMissionTextField.error = hata
                    } else {
                        oldUserMissionTextField.error = null
                        if (adres!!.isEmpty()) {
                            oldUserAddressTextField.error = hata
                        } else {
                            oldUserAddressTextField.error = null
                            if (irtibatTelNo!!.isEmpty()) {
                                oldUserPhoneNumberTextField.error = hata
                            } else {
                                oldUserPhoneNumberTextField.error = null
                                if (tip!!.isEmpty()) {
                                    oldUserTypeTextField.error = hata
                                } else {
                                    oldUserTypeTextField.error = null

                                    val user = hashMapOf(
                                        "ad" to ad,
                                        "adres" to adres,
                                        "email" to email,
                                        "gorev" to gorev,
                                        "irtibatTelNo" to irtibatTelNo,
                                        "kayitTarihi" to kayitTarihi,
                                        "soyad" to soyad,
                                        "tip" to tip
                                    )





                                    db.collection("Kullanicilar").document(docId)
                                        .update(user as Map<String, Any>)
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                this,
                                                "Başarılı...",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            val intent = Intent(
                                                this,
                                                KullaniciIslemleriActivity::class.java
                                            )
                                            startActivity(intent)
                                            finish()
                                        }.addOnFailureListener {
                                            Toast.makeText(
                                                this,
                                                "Hata...",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                }
                            }


                        }
                    }
                }
            }
        }
    }
}