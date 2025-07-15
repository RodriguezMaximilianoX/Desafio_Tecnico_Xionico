package com.rmxdev.ventapp.data.repositoryImpl

import com.rmxdev.ventapp.data.dao.ArticleDao
import com.rmxdev.ventapp.domain.entities.Article
import com.rmxdev.ventapp.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImpl(
    private val articleDao: ArticleDao
): ArticleRepository{
    override suspend fun insertAll(articles: List<Article>) {
        return articleDao.insertAll(articles)
    }

    override fun searchArticles(query: String): Flow<List<Article>> {
        return articleDao.searchArticles(query)
    }

    override suspend fun getArticleById(id: Int): Article? {
        return articleDao.getArticleById(id)
    }

    override suspend fun getArticlesCount(): Int {
        return articleDao.getArticlesCount()
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

}