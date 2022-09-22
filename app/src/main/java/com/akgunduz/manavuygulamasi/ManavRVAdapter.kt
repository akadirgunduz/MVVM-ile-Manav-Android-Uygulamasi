package com.akgunduz.manavuygulamasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class ManavRVAdapter (
    var list: List<ManavItems>,
    val manavItemClickInterface: ManavItemClickInterface
    ) : RecyclerView.Adapter<ManavRVAdapter.ManavViewHolder>() {

    inner class ManavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val adTV = itemView.findViewById<TextView>(R.id.idTVUrunAd)
        val miktarTV = itemView.findViewById<TextView>(R.id.idTVMiktar)
        val fiyatTV = itemView.findViewById<TextView>(R.id.idTVFiyat)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVToatalAmt)
        val silIV = itemView.findViewById<ImageView>(R.id.idIVSil)

    }




    interface ManavItemClickInterface{
        fun onItemClick(manavItems: ManavItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManavViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.manav_rv_item , parent, false )
        return ManavViewHolder(view)
    }

    override fun onBindViewHolder(holder: ManavViewHolder, position: Int) {
        holder.adTV.text = list.get(position).itemAd
        holder.miktarTV.text = list.get(position).itemAdet.toString()
        holder.fiyatTV.text = "Rs. "+list.get(position).itemFiyat.toString()
        val itemTotal: Int = list.get(position).itemFiyat*list.get(position).itemAdet
        holder.amountTV.text = "Rs. "+itemTotal.toString()
        holder.silIV.setOnClickListener {
            manavItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}