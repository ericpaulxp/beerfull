package com.example.beerfull.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.beerfull.data.remote.BeerApi
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.utils.Constants.ITEMS_PER_PAGE

class BeerPagingSource(
    private val beerApi: BeerApi
) : PagingSource<Int, Beer>() {
    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        val page = params.key ?: 1
        return try {
            val beerList = beerApi.getAllBeers(page = page, pageCount = ITEMS_PER_PAGE)
            LoadResult.Page(
                data = beerList,
                nextKey = page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}