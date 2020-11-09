package com.example.daggerhiltapp.api

import com.example.daggerhiltapp.models.Blog
import com.example.daggerhiltapp.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject constructor() : EntityMapper<BlogNetworkEntity, Blog> {

    override fun mapFromEntity(entity: BlogNetworkEntity) =
        Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )

    override fun mapToEntity(domainModel: Blog) =
        BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )

    fun mapFromEntityList(entities: List<BlogNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}