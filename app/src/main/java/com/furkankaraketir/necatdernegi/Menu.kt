package com.furkankaraketir.necatdernegi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Menu : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var sistemYoneticileriList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        auth = Firebase.auth
        var currentUserEmail = ""
        if (auth.currentUser != null) {
            currentUserEmail = auth.currentUser!!.email.toString()
        }
        var adminEmail = ""
        var yoneticiBoolean = false

        val aileBilgileriButton = findViewById<Button>(R.id.aileBilgileriButton)
        val yardimlarVeDagitimButton = findViewById<Button>(R.id.yardimlarVeDagitimButton)
        val yardimKumbarasiButton = findViewById<Button>(R.id.yardimKumbarasiButton)
        val aileveYardimIstatistikleriButton =
            findViewById<Button>(R.id.aileveYardimIstatistikleriButton)
        val kullaniciIslemleriButton = findViewById<Button>(R.id.kullaniciIslemleriButton)

        val db = Firebase.firestore

        db.collection("Kullanicilar")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document["tip"].toString() == "Sistem Yöneticisi") {
                        adminEmail = document["email"].toString()
                        sistemYoneticileriList.add(adminEmail)
                    }
                }
            }

        aileBilgileriButton.setOnClickListener {
            TODO()
        }

        yardimlarVeDagitimButton.setOnClickListener {
            TODO()
        }

        yardimKumbarasiButton.setOnClickListener {
            TODO()
        }

        aileveYardimIstatistikleriButton.setOnClickListener {
            TODO()
        }

        kullaniciIslemleriButton.setOnClickListener {

            for (yonetici in sistemYoneticileriList) {
                if (currentUserEmail == yonetici) {
                    yoneticiBoolean = true
                    val intent = Intent(this, KullaniciIslemleriActivity::class.java)
                    startActivity(intent)
                }
            }

            if (!yoneticiBoolean) {
                Toast.makeText(this, "Yetkili Değilsiniz...", Toast.LENGTH_SHORT).show()
            }


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.signOut) {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)

    }
}