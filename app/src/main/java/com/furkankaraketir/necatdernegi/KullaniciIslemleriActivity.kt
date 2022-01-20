package com.furkankaraketir.necatdernegi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class KullaniciIslemleriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici_islemleri)

        val kullaniciTanimlamaButton = findViewById<Button>(R.id.kullaniciTanimlamaButton)
        val kullaniciBilgiGuncellemeSilme = findViewById<Button>(R.id.kullaniciBilgiGuncellemeSilme)

        kullaniciTanimlamaButton.setOnClickListener {
            val intent = Intent(this, KullaniciTanimlamaActivity::class.java)
            startActivity(intent)
        }

        kullaniciBilgiGuncellemeSilme.setOnClickListener {
            TODO()
        }

    }
}