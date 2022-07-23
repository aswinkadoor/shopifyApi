package shopifyapi

import grails.gorm.services.Service

@Service(Api)
interface ApiService {

    Api get(Serializable id)

    List<Api> list(Map args)

    Long count()

    void delete(Serializable id)

    Api save(Api api)

}