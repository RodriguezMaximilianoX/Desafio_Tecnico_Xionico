package com.rmxdev.ventapp.domain.repository

import com.rmxdev.ventapp.domain.entities.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun insertAll(articles: List<Article>)
    fun searchArticles(query: String): Flow<List<Article>>
    suspend fun getArticleById(id: Int): Article?
    suspend fun getArticlesCount(): Int
    fun getAllArticles(): Flow<List<Article>>
}