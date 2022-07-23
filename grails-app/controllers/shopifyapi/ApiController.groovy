package shopifyapi

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*

class ApiController {

    def apiService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    @Transactional
    def index(Integer max) {


        if(params.actionType == "sync"){
            def urlString = 'https://full-stack-dev-test.myshopify.com/admin/api/2022-04/events.json?filter=Product'
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
                jsonData = new JsonSlurper().parseText(output)
            }
            connection.disconnect();
            def result = apiService.updateDb(jsonData)
            render result as JSON
        }
        else if(params.actionType == "loadData"){
            def result = apiService.loadData(params)
            render template: 'dataTemplate', model: [result:result]
        }
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
