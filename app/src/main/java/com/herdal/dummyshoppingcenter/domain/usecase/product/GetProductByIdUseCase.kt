package com.herdal.dummyshoppingcenter.domain.usecase.product

import com.herdal.dummyshoppingcenter.common.Resource
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: Int): Flow<Resource<ProductUiModel>> = flow {
        try {
            emit(Resource.Loading())
            val product = productRepository.getById(id)
            emit(Resource.Success(data = product))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}