package com.johnny.controller

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.ui.Model
import org.springframework.validation.support.BindingAwareModelMap
import spock.lang.Specification

import javax.print.attribute.standard.Media

import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@WebMvcTest(controllers = [HelloController])
class HelloControllerSpec extends Specification{

    @Autowired
    MockMvc mvc

    def 'with a name'() {
        when:
        ResultActions results = mvc.perform(get("/hello").param("name","Dolly").accept(MediaType.TEXT_PLAIN))

        then:
        results.andExpect(status().isOk())
               .andExpect(view().name("hello"))
               .andExpect(model().attribute("user", is("Dolly")))
    }

    def 'without a name'() {
        when:
        ResultActions results = mvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN))

        then:
        results.andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attribute("user", is("world")))
    }

}
