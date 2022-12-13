package com.herdal.dummyshoppingcenter.domain.usecase.product

import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import javax.inject.Inject

class DeleteProductFromDbUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: ProductUiModel) {
        return productRepository.delete(product)
    }
}