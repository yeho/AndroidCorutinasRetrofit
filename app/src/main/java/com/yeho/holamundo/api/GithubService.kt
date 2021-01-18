package com.yeho.holamundo.api
import com.yeho.holamundo.data.RepoResult
import retrofit2.http.GET

interface GithubService {
    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=50")
    suspend fun searchRepositories(): RepoResult
}