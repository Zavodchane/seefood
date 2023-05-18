package com.example.seefood.screens.classification_result

import androidx.lifecycle.ViewModel
import com.example.seefood.database.repos.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface ClassificationResultViewModelAbstract {

}

@HiltViewModel
class ClassificationResultViewModel
@Inject constructor(
   private val dishRepository: DishRepository
) : ViewModel(), ClassificationResultViewModelAbstract {

}