package com.herdal.dummyshoppingcenter.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _products =
        MutableLiveData<PagingData<ProductItemUiState>>()
    val products: LiveData<PagingData<ProductItemUiState>> = _products

    suspend fun getProducts(): LiveData<PagingData<ProductItemUiState>> {
        val response = productRepository.getProducts().map { pagingData ->
            pagingData.map { productModel ->
                ProductItemUiState(productModel)
            }
        }.cachedIn(viewModelScope).asLiveData()
        _products.value = response.value
        Timber.d("$response")
        return response
    }
}