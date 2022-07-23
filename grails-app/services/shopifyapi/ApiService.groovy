package shopifyapi

import grails.converters.JSON

import java.text.SimpleDateFormat

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
}
