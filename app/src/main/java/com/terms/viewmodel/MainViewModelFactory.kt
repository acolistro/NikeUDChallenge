package com.terms.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terms.data.repository.DefinitionRepository

class MainViewModelFactory constructor(private val definitionRepository: DefinitionRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(definitionRepository) as T
    }

}