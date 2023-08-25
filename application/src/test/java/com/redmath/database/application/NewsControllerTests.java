package com.redmath.database.application;
import com.redmath.database.application.news.NewsRepository;
import com.redmath.database.application.news.NewsService;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTests {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private NewsService newsService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindNews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/News?title=%i%")
                        .with(SecurityMockMvcRequestPostProcessors.user("test")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }


    @Test
    public void testfindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/News")
                        .with(SecurityMockMvcRequestPostProcessors.user("test")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    /*   *//*private RequestPostProcessor testUser(String userName, String authoriy) {
          return SecurityMockMvcRequestPostProcessors.user(userName).authorities(new SimpleGrantedAuthority(authoriy));*//*
    }*/
    @Test
    public void testinsertNews() throws Exception {
        String jsonRequest = "{\"title\":\"title 9\",\"details\":\"details 9\",\"tags\":\"tags 9\",\"reportedTime\":\"2000-01-01T12:00:00\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/News")
                        .with(SecurityMockMvcRequestPostProcessors.user("test"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    public void testupdateNews() throws Exception {
        String jsonRequest1 = "{\"title\":\"title 9\",\"details\":\"details 9\",\"tags\":\"tags 9\",\"reportedTime\":\"2000-01-01T12:00:00\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/News/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("test"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                //.andExpect(ResultMatcher.matchAll())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title 9"));
    }

    @Test
    public void testDeleteNews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/News/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("test"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))

                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


        assertFalse(repository.existsById(1L));

    }
}

