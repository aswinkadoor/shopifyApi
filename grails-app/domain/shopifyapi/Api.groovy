package shopifyapi

import grails.gorm.Entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.sql.Timestamp

class Api {


    String subjectType
    String message
    String author
    BigDecimal transactionId
    String verb
    Date createdAt


    static mapping = {
        version false
        id generator: 'sequence', params: [sequence:'API_SEQ']
    }

    static constraints = {
        subjectType nullable: true
        message nullable: true
        author nullable: true
        verb nullable: true
        createdAt nullable: true
        transactionId nullable: true

    }
}
