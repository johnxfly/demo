package com.johnny.controller

import com.johnny.domain.Greeting
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloRestControllerSpec extends Specification {

    @Autowired
    TestRestTemplate template

    def 'greet without name'(){
        when:
        ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class)

        then:
        entity.statusCode == HttpStatus.OK
        entity.headers.get("Content-Type") == [MediaType.APPLICATION_JSON_UTF8_VALUE]
        entity.body.message == "Hello, World!"
    }

    def 'greet with name'(){
        when:
        ResponseEntity<Greeting> entity = template.getForEntity("/rest?name=Bob", Greeting.class)

        then:
        entity.body.message == "Hello, Bob!"
    }
}