package com.herdal.dummyshoppingcenter.ui.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.dummyshoppingcenter.common.Resource
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import com.herdal.dummyshoppingcenter.domain.usecase.product.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _productDetail =
        MutableStateFlow<Resource<ProductUiModel>>(Resource.Loading())
    val productDetail: StateFlow<Resource<ProductUiModel>> = _productDetail

    fun getProductById(id: Int) {
        getProductByIdUseCase.invoke(id)
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _productDetail.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        if (resource.data != null) {
                            _productDetail.value = Resource.Success(resource.data)
                        }
                    }
                    is Resource.Error -> {
                        _productDetail.value = Resource.Error(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun saveProductToDb(product: ProductUiModel) = viewModelScope.launch {
        productRepository.insertToDb(product)
    }
}