package com.herdal.dummyshoppingcenter.ui.favorite_products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    val allFavProducts: LiveData<List<ProductUiModel>> = productRepository.getAllFromDb().asLiveData()
}