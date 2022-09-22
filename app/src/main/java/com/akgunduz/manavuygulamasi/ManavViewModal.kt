package com.akgunduz.manavuygulamasi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ManavViewModal (private val repository: ManavDepo) : ViewModel(){
    fun insert(items: ManavItems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: ManavItems) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllManavItems() = repository.getAllItems()
}