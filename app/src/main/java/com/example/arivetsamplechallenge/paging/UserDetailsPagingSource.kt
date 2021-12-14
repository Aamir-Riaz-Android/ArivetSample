package com.example.arivetsamplechallenge.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.arivetsamplechallenge.api_response.UsersInfo
import com.example.arivetsamplechallenge.data.local.dao.UserDao
import javax.inject.Inject

class UserDetailsPagingSource @Inject constructor (private val userPostDao: UserDao) : PagingSource<Int, UsersInfo>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UsersInfo> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        val userInfo = userPostDao.getUsersDataList(params.loadSize)
        return LoadResult.Page(
            data = userInfo,
            prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
            nextKey = if (userInfo.isNullOrEmpty()) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, UsersInfo>): Int? = null
}
