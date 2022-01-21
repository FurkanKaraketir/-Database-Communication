package com.furkankaraketir.necatdernegi

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class KullaniciListAdapter(private val userList: ArrayList<Kullanici>) :
    RecyclerView.Adapter<KullaniciListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = userList[position].ad + " " + userList[position].soyad
        holder.item.setOnClickListener {
            val intent = Intent(holder.item.context, KulaniciBilgiDegistirmeActivity::class.java)
            intent.putExtra("ad", userList[position].ad)
            intent.putExtra("adres", userList[position].adres)
            intent.putExtra("email", userList[position].email)
            intent.putExtra("gorev", userList[position].gorev)
            intent.putExtra("irtibatTelNo", userList[position].irtibatTelNo)
            intent.putExtra("kayitTarihi", userList[position].kayitTarihi)
            intent.putExtra("soyad", userList[position].soyad)
            intent.putExtra("tip", userList[position].tip)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.name)
        var item: MaterialCardView = itemView.findViewById(R.id.userListItem)
    }
}