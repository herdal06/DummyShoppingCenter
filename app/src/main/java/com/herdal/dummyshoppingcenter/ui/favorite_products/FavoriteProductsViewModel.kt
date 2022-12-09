package com.herdal.dummyshoppingcenter.ui.favorite_products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import com.herdal.dummyshoppingcenter.domain.usecase.product.AddProductToFavoriteUseCase
import com.herdal.dummyshoppingcenter.domain.usecase.product.DeleteProductFromDbUseCase
import com.herdal.dummyshoppingcenter.domain.usecase.product.GetProductsFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteProductsViewModel @Inject constructor(
    getProductsFromDbUseCase: GetProductsFromDbUseCase,
    private val deleteProductFromDbUseCase: DeleteProductFromDbUseCase,
    private val addProductToFavoriteUseCase: AddProductToFavoriteUseCase
) : ViewModel() {

    val allFavProducts: LiveData<List<ProductUiModel>> =
        getProductsFromDbUseCase.invoke().asLiveData()

    private val productsEventChannel = Channel<ProductsEvent>()
    val productsEvent = productsEventChannel.receiveAsFlow()

    sealed class ProductsEvent {
        data class ShowUndoDeleteItemMessage(val product: ProductUiModel) : ProductsEvent()
    }

    fun onItemSwiped(product: ProductUiModel) = viewModelScope.launch {
        deleteProductFromDbUseCase.invoke(product)
        productsEventChannel.send(ProductsEvent.ShowUndoDeleteItemMessage(product))
    }

    fun onUndoDeleteClick(product: ProductUiModel) = viewModelScope.launch {
        addProductToFavoriteUseCase.invoke(product)
    }
}