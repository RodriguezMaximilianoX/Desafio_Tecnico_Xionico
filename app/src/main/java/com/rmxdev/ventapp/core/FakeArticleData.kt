package com.rmxdev.ventapp.core

import com.rmxdev.ventapp.data.dao.ArticleDao
import com.rmxdev.ventapp.domain.entities.Article

object FakeArticleData {
    suspend fun insertRandomArticles(articleDao: ArticleDao) {
        val articles = List(1000) { index ->
            Article(
                name = "Artículo #${index + 1}",
                price = (1..1000).random().toDouble() + (0..99).random() / 100.0
            )
        }
        articleDao.insertAll(articles)
    }
}