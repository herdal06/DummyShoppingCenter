package com.herdal.dummyshoppingcenter.domain.usecase.product

import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsFromDbUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<List<ProductUiModel>> {
        return productRepository.getAllFromDb()
    }
}