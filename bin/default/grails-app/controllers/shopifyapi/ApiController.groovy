package shopifyapi

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import groovy.json.JsonSlurper

import static org.springframework.http.HttpStatus.*

class ApiController {

    ApiService apiService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {

        def urlString = 'https://full-stack-dev-test.myshopify.com/admin/api/2022-04/events.json?filter=Product&verb=destroy'
        URL url = new URL(urlString)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection()
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("X-Shopify-Access-Token", "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")
        connection.setRequestMethod('GET')

        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(inputStreamReader);
        String output;
        def jsonData
        while ((output = br.readLine()) != null) {
            println(output.getClass());
            jsonData = new JsonSlurper().parseText(output)
            println jsonData.getClass()
        }
        connection.disconnect();

        jsonData.events.each{

            println "~~~~~~~~~~~~~~~~~~~~~~~~~~"
            println Api.createCriteria().list {}[0].id
            println Api.createCriteria().list {}[0].transactionId
            println "~~~~~~~~~~~~~~~~~~~~~~~~~~"

            println "##############################"
            println it.id

            def apiInstance = new Api()

            apiInstance.withTransaction {
//                apiInstance.author          = it.author
//                apiInstance.subjectType     = it.subject_type
                apiInstance.transactionId             =  it.id.toBigDecimal()
//                apiInstance.id              = it.id.toBigDecimal()
//                apiInstance.verb            = it.verb
//                apiInstance.message         = it.message
                apiInstance.save(flush:true, failOnError:true, deepValidate:false)

            }





            def test = Api.findAll()

            println "~~~~~~~~~~~~~~~~~~~~~~~~~~`````"
            println test
            println "~~~~~~~~~~~~~~~~~~~~~~~~~~`````"



        }

        render view: 'index'/*, model:[result:api]*/
    }


    def show(Long id) {
        respond apiService.get(id)
    }

    def create() {
        respond new Api(params)
    }

    def save(Api api) {
        if (api == null) {
            notFound()
            return
        }

        try {
            apiService.save(api)
        } catch (ValidationException e) {
            respond api.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'api.label', default: 'Api'), api.id])
                redirect api
            }
            '*' { respond api, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond apiService.get(id)
    }

    def update(Api api) {
        if (api == null) {
            notFound()
            return
        }

        try {
            apiService.save(api)
        } catch (ValidationException e) {
            respond api.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'api.label', default: 'Api'), api.id])
                redirect api
            }
            '*'{ respond api, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        apiService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'api.label', default: 'Api'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'api.label', default: 'Api'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
