package com.herdal.dummyshoppingcenter.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _products =
        MutableLiveData<PagingData<ProductUiModel>>()
    val products: LiveData<PagingData<ProductUiModel>> = _products

    suspend fun getProducts(): LiveData<PagingData<ProductUiModel>> {
        val response = productRepository.getProducts().cachedIn(viewModelScope).asLiveData()
        _products.value = response.value
        Timber.d("$response")
        return response
    }
}