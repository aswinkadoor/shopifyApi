package shopifyapi

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ApiServiceSpec extends Specification {

    ApiService apiService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Api(...).save(flush: true, failOnError: true)
        //new Api(...).save(flush: true, failOnError: true)
        //Api api = new Api(...).save(flush: true, failOnError: true)
        //new Api(...).save(flush: true, failOnError: true)
        //new Api(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //api.id
    }

    void "test get"() {
        setupData()

        expect:
        apiService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Api> apiList = apiService.list(max: 2, offset: 2)

        then:
        apiList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        apiService.count() == 5
    }

    void "test delete"() {
        Long apiId = setupData()

        expect:
        apiService.count() == 5

        when:
        apiService.delete(apiId)
        sessionFactory.currentSession.flush()

        then:
        apiService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Api api = new Api()
        apiService.save(api)

        then:
        api.id != null
    }
}
