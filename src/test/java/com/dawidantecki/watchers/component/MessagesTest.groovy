package com.dawidantecki.watchers.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MessagesTest extends Specification {

    @Autowired
    Messages messages

    def "should return a message from the properties"() {
        when:
        def message = messages.get(code)

        then:
        !isEmpty(message)

        where:
        code << ["welcome.message", "description.message"]
    }

    // Used to checks if passed string is valid
    private static boolean isEmpty(String validate) {
        boolean isEmpty = false

        if (validate == null || validate.trim().length() == 0 || validate == "")
            isEmpty = true

        return isEmpty
    }
}
