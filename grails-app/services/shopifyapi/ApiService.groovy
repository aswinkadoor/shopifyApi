package shopifyapi

import groovy.time.TimeCategory

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ApiService {

    def updateDb(jsonData){
        def model
        try{
            jsonData.events.each {

                def transactionList = Api.createCriteria().list {
                    projections {
                        property('transactionId')
                    }
                }

                if (transactionList.size() == 0) {
                    def apiInstance = new Api()
                    apiInstance.author = it.author
                    apiInstance.subjectType = it.subject_type
                    apiInstance.transactionId = it.id.toBigDecimal()
                    apiInstance.verb = it.verb
                    apiInstance.message = it.message
                    apiInstance.createdAt = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss-hh:mm").parse(it.created_at)
                    apiInstance.save(flush: true, failOnError: true, deepValidate: false)
                } else if (transactionList.size() > 0) {
                    if (it.id.toBigDecimal() in transactionList) {

                    } else {
                        def apiInstance = new Api()
                        apiInstance.author = it.author
                        apiInstance.subjectType = it.subject_type
                        apiInstance.transactionId = it.id.toBigDecimal()
                        apiInstance.verb = it.verb
                        apiInstance.message = it.message
                        apiInstance.createdAt = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss-hh:mm").parse(it.created_at)
                        apiInstance.save(flush: true, failOnError: true, deepValidate: false)
                    }
                }

            }
            model =[message:'Data Sync completed', success:true]
        }
        catch(Exception ex){
            model = [success: false, message: 'Something went wrong....']
        }
        return  model

    }

    def loadData(params){
        def result = Api.createCriteria().list {
            if(params.fromDate){
                gte("createdAt",  new SimpleDateFormat("yyyy-MM-dd").parse(params.fromDate))
            }
            if(params.toDate){
                lte("createdAt",  (new SimpleDateFormat("yyyy-MM-dd").parse(params.toDate)))
            }
        }
        return result
    }
}
