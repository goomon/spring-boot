package com.github.goomon.boot.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ObjectMapperTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void objectMapperTest() throws JsonProcessingException {
        // given
        String jsonInput = "{\"name\": \"jackson\"}";

        // when
        Person person = objectMapper.readValue(jsonInput, Person.class);

        // then
        assertThat(person.getName()).isEqualTo("jackson");
    }

    @Description("변환하는 클래스 필드에 매칭되지 않는 JSON 필드가 있을 경우 무시된다.")
    @Test
    void objectMapperWithNotExactlyMatchedJsonInput() throws JsonProcessingException {
        // given
        String jsonInput = "{\"name\": \"jackson\", \"age\": 20}";

        // when
        Person person = objectMapper.readValue(jsonInput, Person.class);

        // then
        assertThat(person.getName()).isEqualTo("jackson");
    }

    @Description("필드값이 null로 들어오게 되면 기본값이 정의되어 있어도 null로 역직렬화 된다.")
    @Test
    void objectMapperWithJsonInputThatHasNull() throws JsonProcessingException {
        String jsonInput = "{\"name\": null}";

        // when
        Person person = objectMapper.readValue(jsonInput, Person.class);

        // then
        assertThat(person.getName()).isEqualTo(null);
    }

    @Description("JSON 입력으로 필드가 입력되지 않아도 클래스 기본값이 세팅되어 있다면 역직렬화에 성공한다.")
    @Test
    void objectMapperWithDefaultValue() throws JsonProcessingException {
        String jsonInput = "{\"age\": 20}";

        // when
        Person person = objectMapper.readValue(jsonInput, Person.class);

        // then
        assertThat(person.getName()).isEqualTo("default");
    }

    static class Person {
        private String name = "default";

        public Person() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
