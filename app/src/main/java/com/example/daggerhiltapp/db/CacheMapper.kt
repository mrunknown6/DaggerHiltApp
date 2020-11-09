package com.example.daggerhiltapp.db

import com.example.daggerhiltapp.models.Blog
import com.example.daggerhiltapp.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject constructor() : EntityMapper<BlogCacheEntity, Blog>{

    override fun mapFromEntity(entity: BlogCacheEntity) =
        Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )

    override fun mapToEntity(domainModel: Blog) =
        BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )

    fun mapFromEntityList(entities: List<BlogCacheEntity>) =
        entities.map { mapFromEntity(it) }
}