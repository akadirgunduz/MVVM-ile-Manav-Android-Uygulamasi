package com.akgunduz.manavuygulamasi

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ManavRVAdapter.ManavItemClickInterface {
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<ManavItems>
    lateinit var manavRVAdapter: ManavRVAdapter
    lateinit var manavViewModal: ManavViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV = findViewById(R.id.idRVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<ManavItems>()
        manavRVAdapter = ManavRVAdapter(list,this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = manavRVAdapter
        val manavDepo = ManavDepo(ManavVeritabani(this))
        val factory = ManavViewModalFactory(manavDepo)
        manavViewModal = ViewModelProvider(this,factory).get(ManavViewModal::class.java)
        manavViewModal.getAllManavItems().observe(this, Observer {
            manavRVAdapter.list = it
            manavRVAdapter.notifyDataSetChanged()
        })
        addFAB.setOnClickListener {
            openDialog()
        }

    }
    fun openDialog(){
        val dialog = Dialog (this)
        dialog.setContentView(R.layout.manav_add_dialog)
        val cikisBtn = dialog.findViewById<Button>(R.id.idBtnCikis)
        val ekleBtn = dialog.findViewById<Button>(R.id.idBtnEkle)
        val itemAdEdt = dialog.findViewById<EditText>(R.id.idEdtItemAd)
        val itemFiyatEdt = dialog.findViewById<EditText>(R.id.idEdtItemFiyat)
        val itemMiktarEdt = dialog.findViewById<EditText>(R.id.idEdtItemMiktari)
        cikisBtn.setOnClickListener {
            dialog.dismiss()
        }
        ekleBtn.setOnClickListener {
            val itemAd : String = itemAdEdt.text.toString()
            val itemFiyat : String = itemFiyatEdt.text.toString()
            val itemMiktar : String = itemMiktarEdt.text.toString()
            val mktr : Int = itemMiktar.toInt()
            val fyt : Int = itemFiyat.toInt()
            if(itemAd.isNotEmpty() && itemFiyat.isNotEmpty() && itemMiktar.isNotEmpty()){
                val items = ManavItems(itemAd, mktr, fyt)
                manavViewModal.insert(items)
                Toast.makeText(applicationContext, "Ürün Girildi..", Toast.LENGTH_SHORT).show()
                manavRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext, "Bütün verileri giriniz..", Toast.LENGTH_SHORT).show()
            }

        }
        dialog.show()
    }

    override fun onItemClick(manavItems: ManavItems) {
        manavViewModal.delete(manavItems)
        manavRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext , "Ürün Silindi..", Toast.LENGTH_SHORT).show()
    }
}