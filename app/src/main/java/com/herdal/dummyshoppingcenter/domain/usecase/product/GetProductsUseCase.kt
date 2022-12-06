package com.herdal.dummyshoppingcenter.domain.usecase.product

import androidx.paging.PagingData
import com.herdal.dummyshoppingcenter.common.ProductMapper
import com.herdal.dummyshoppingcenter.common.Resource
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val mapper: ProductMapper
) {
    operator fun invoke(): Flow<Resource<PagingData<ProductUiModel>>> = flow {
        try {
            emit(Resource.Loading())
            val domainProduct = productRepository.getProducts()
            domainProduct.map { pagingData ->
                emit(Resource.Success(data = pagingData))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}