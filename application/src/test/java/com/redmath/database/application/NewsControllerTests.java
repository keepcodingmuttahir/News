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
        public void testFindNews() throws Exception{
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
         public void testinsertNews() throws Exception{
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
     public void testupdateNews() throws Exception{
        String jsonRequest = "{\"title\":\"title 9\",\"details\":\"details 9\",\"tags\":\"tags 9\",\"reportedTime\":\"2000-01-01T12:00:00\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/News/1")
                .with(SecurityMockMvcRequestPostProcessors.user("test"))
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


    }
    @Test
    public void testDeleteNews() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/News/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("test"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))

                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


                assertFalse(repository.existsById(1L));

    }
      /*  @Test
        void testFindAllNews() {
            // Arrange
            List<News> newsList = new ArrayList<>();
            newsList.add(new News());
            when(newsRepository.findAll()).thenReturn(newsList);

            // Act
            List<News> result = newsService.findAll();

            // Assert
            assertFalse(result.isEmpty());
            assertEquals(newsList.size(), result.size());
        }*//*

        @Test
        void testFindNewsByIdExisting() {
            // Arrange
            News news = new News();
            when(newsRepository.findById(anyLong())).thenReturn(Optional.of(news));

            // Act
            News result = newsService.findById(1L);

            // Assert
            assertNotNull(result);
        }

        @Test
        void testFindNewsByIdNonExisting() {
            // Arrange
            when(newsRepository.findById(anyLong())).thenReturn(Optional.empty());

            // Act
            News result = newsService.findById(1L);

            // Assert
            assertNull(result);
        }

        @Test
        void testCreateNews() {
            // Arrange
            News news = new News();
            news.setTitle("Sample News Title");
            when(newsRepository.existsById(anyLong())).thenReturn(false);
            when(newsRepository.save(any(News.class))).thenReturn(news);

            // Act
            News createdNews = newsService.create(news);

            // Assert
            assertNotNull(createdNews);
            assertEquals("Sample News Title", createdNews.getTitle());
        }

        @Test
        void testUpdateNews() {
            // Arrange
            News existingNews = new News();
            existingNews.setId(1L);
            existingNews.setTitle("Existing News Title");
            News updatedNews = new News();
            updatedNews.setTitle("Updated News Title");

            when(newsRepository.findById(anyLong())).thenReturn(Optional.of(existingNews));
            when(newsRepository.save(any(News.class))).thenReturn(updatedNews);

            // Act
            News result = newsService.update(updatedNews, 1L);

            // Assert
            assertNotNull(result);
            assertEquals("Updated News Title", result.getTitle());
        }

        @Test
        void testDeleteExistingNews() {
            // Arrange
            News existingNews = new News();
            existingNews.setId(1L);
            when(newsRepository.findById(anyLong())).thenReturn(Optional.of(existingNews));

            // Act
            assertDoesNotThrow(() -> newsService.delete(1L));

            // Assert
            verify(newsRepository, times(1)).deleteById(anyLong());
        }

        @Test
        void testDeleteNonExistingNews() {
            // Arrange
            when(newsRepository.findById(anyLong())).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(ResponseStatusException.class, () -> newsService.delete(1L));
        }*/
}

