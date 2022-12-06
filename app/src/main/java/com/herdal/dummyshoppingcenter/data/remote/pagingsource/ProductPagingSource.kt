package com.herdal.dummyshoppingcenter.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductDto
import com.herdal.dummyshoppingcenter.data.remote.service.ProductService
import com.herdal.dummyshoppingcenter.utils.Constants.NETWORK_PAGE_SIZE
import com.herdal.dummyshoppingcenter.utils.Constants.STARTING_PAGE_INDEX
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber

class ProductPagingSource(
    private val productService: ProductService
) : PagingSource<Int, ProductDto>() {
    override fun getRefreshKey(state: PagingState<Int, ProductDto>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductDto> {
        // Start refresh at position 1 if undefined.
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            // not showing first item (Iphone 9)
            val offset =
                if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else STARTING_PAGE_INDEX

            val response = productService.getAll(limit = params.loadSize, skip = offset).products
            Timber.d("$response")
            val nextKey = if (response.isEmpty()) null
            else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            val prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        }
    }
}