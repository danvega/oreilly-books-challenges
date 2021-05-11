package com.oreilly.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    BookController controller;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void updateMethodShouldHavePutMappingAnnotation() throws NoSuchMethodException {
        PutMapping annotation = controller.getClass().getMethod("update", Book.class, int.class).getAnnotation(PutMapping.class);
        assertNotNull(annotation);

        String[] paths = annotation.value();
        assertEquals(1,paths.length);
        assertEquals("/{id}",paths[0]);
        System.out.println("success");
    }

    @Test
    void updateMethodIdArgumentShouldBeAnnotatedWithPathVariable() throws NoSuchMethodException {
        Annotation[][] annotations = controller.getClass().getMethod("update", Book.class, int.class).getParameterAnnotations();
        assertNotNull(annotations);
        assertEquals(2, annotations.length);
        assertEquals("PathVariable", annotations[1][0].annotationType().getSimpleName());
        System.out.println("success");
    }

    @Test
    void deleteMethodShouldHaveDeleteMappingAnnotation() throws NoSuchMethodException {
        DeleteMapping annotation = controller.getClass().getMethod("delete", int.class).getAnnotation(DeleteMapping.class);
        assertNotNull(annotation);
        System.out.println("success");
    }

    @Test
    void deleteMethodShouldReturnResponseStatusNoContent() throws NoSuchMethodException {
        ResponseStatus annotation = controller.getClass().getMethod("delete", int.class).getAnnotation(ResponseStatus.class);
        assertNotNull(annotation);
        HttpStatus httpStatus = annotation.value();
        assertEquals(HttpStatus.NO_CONTENT,httpStatus);
        System.out.println("success");
    }

}
