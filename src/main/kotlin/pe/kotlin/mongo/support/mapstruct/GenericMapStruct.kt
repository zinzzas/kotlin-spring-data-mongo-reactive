package pe.kotlin.mongo.support.mapstruct

interface GenericMapStruct<E, D> {
    fun toDTO(entity: E): D
}