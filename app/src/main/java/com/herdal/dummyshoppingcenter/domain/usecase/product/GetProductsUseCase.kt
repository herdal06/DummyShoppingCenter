package com.herdal.dummyshoppingcenter.domain.usecase.product

import androidx.paging.PagingData
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): Flow<PagingData<ProductUiModel>> {
        return productRepository.getProducts()
    }
}