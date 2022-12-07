package com.herdal.dummyshoppingcenter.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.herdal.dummyshoppingcenter.common.Resource
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.usecase.category.GetCategoriesUseCase
import com.herdal.dummyshoppingcenter.domain.usecase.product.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _products =
        MutableLiveData<PagingData<ProductItemUiState>>()
    val products: LiveData<PagingData<ProductItemUiState>> = _products

    private val _productList =
        MutableStateFlow<Resource<PagingData<ProductItemUiState>>>(Resource.Loading())
    val productList: StateFlow<Resource<PagingData<ProductItemUiState>>> = _productList

    private val _categories =
        MutableStateFlow<Resource<List<CategoryItemUiState>>>(Resource.Loading())
    val categories: StateFlow<Resource<List<CategoryItemUiState>>> = _categories

    fun getAllCategories() {
        getCategoriesUseCase.invoke()
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _categories.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        if (resource.data != null) {
                            _categories.value = Resource.Success(resource.data.map { string ->
                                CategoryItemUiState(string)
                            })
                        }
                    }
                    is Resource.Error -> {
                        _categories.value = Resource.Error(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getAllProducts() {
        getProductsUseCase.invoke()
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _productList.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        if (resource.data != null) {
                            _productList.value =
                                Resource.Success(resource.data.map { productUiModel ->
                                    ProductItemUiState(productUiModel)
                                })
                        }
                    }
                    is Resource.Error -> {
                        _productList.value = Resource.Error(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }

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