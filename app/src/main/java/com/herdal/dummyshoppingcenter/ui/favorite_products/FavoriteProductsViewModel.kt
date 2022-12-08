package com.herdal.dummyshoppingcenter.ui.favorite_products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    val allFavProducts: LiveData<List<ProductUiModel>> =
        productRepository.getAllFromDb().asLiveData()

    private val productsEventChannel = Channel<ProductsEvent>()
    val productsEvent = productsEventChannel.receiveAsFlow()

    sealed class ProductsEvent {
        data class ShowUndoDeleteItemMessage(val product: ProductUiModel) : ProductsEvent()
    }

    fun onItemSwiped(product: ProductUiModel) = viewModelScope.launch {
        productRepository.delete(product)
        productsEventChannel.send(ProductsEvent.ShowUndoDeleteItemMessage(product))
    }

    fun onUndoDeleteClick(product: ProductUiModel) = viewModelScope.launch {
        productRepository.insertToDb(product)
    }
}