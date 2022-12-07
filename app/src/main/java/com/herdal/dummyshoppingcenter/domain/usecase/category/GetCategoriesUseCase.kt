package com.herdal.dummyshoppingcenter.domain.usecase.category

import com.herdal.dummyshoppingcenter.common.Resource
import com.herdal.dummyshoppingcenter.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading())
            val categories = categoryRepository.getCategories()
            Timber.d("$categories")
            emit(Resource.Success(data = categories))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        }
    }
}