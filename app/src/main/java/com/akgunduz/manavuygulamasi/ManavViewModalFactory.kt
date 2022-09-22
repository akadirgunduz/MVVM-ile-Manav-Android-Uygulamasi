package com.akgunduz.manavuygulamasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ManavViewModalFactory(private val repository: ManavDepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ManavViewModal(repository) as T
    }
}