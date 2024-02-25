package com.example.beerfull.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.beerfull.data.remote.BeerApi
import com.example.beerfull.domain.model.Beer
import com.example.beerfull.utils.Constants.ITEMS_PER_PAGE

class SearchPagingSource(
    private val beerApi: BeerApi,
    private val searchQuery: String
) : PagingSource<Int, Beer>() {
    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val page = params.key ?: 1
            val beerResponse =
                beerApi.searchBeers(
                    searchQuery = searchQuery,
                    page = page,
                    pageCount = ITEMS_PER_PAGE
                )
            LoadResult.Page(
                data = beerResponse,
                prevKey = null,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}