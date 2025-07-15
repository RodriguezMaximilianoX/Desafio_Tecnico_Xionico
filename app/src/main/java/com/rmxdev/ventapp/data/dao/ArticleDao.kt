package com.rmxdev.ventapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rmxdev.ventapp.domain.entities.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Query("SELECT * FROM articles WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchArticles(query: String): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE id = :id LIMIT 1")
    suspend fun getArticleById(id: Int): Article?

    @Query("SELECT COUNT(*) FROM articles")
    suspend fun getArticlesCount(): Int

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

}