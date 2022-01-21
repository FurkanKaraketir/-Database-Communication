package com.furkankaraketir.necatdernegi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class KullaniciGuncellemeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var userList = ArrayList<Kullanici>()
    private lateinit var recyclerViewAdapter: KullaniciListAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici_guncelleme)
        auth = Firebase.auth
        val db = Firebase.firestore


        val userListRecyclerView = findViewById<RecyclerView>(R.id.UserListRecyclerView)


        val layoutManager = LinearLayoutManager(applicationContext)
        userListRecyclerView.layoutManager = layoutManager
        recyclerViewAdapter = KullaniciListAdapter(userList)
        userListRecyclerView.adapter = recyclerViewAdapter

        db.collection("Kullanicilar")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val user = Kullanici(
                        document["ad"].toString(),
                        document["adres"].toString(),
                        document["email"].toString(),
                        document["gorev"].toString(),
                        document["irtibatTelNo"].toString(),
                        document["kayitTarihi"].toString(),
                        document["soyad"].toString(),
                        document["tip"].toString()
                    )

                    userList.add(user)
                }

                recyclerViewAdapter.notifyDataSetChanged()

            }


    }
}