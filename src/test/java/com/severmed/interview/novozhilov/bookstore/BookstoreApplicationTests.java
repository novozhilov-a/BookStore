package com.severmed.interview.novozhilov.bookstore;

import com.severmed.interview.novozhilov.bookstore.model.Book;
import com.severmed.interview.novozhilov.bookstore.model.BooksRepo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookstoreApplicationTests {
	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private BooksRepo repository;

    @Test
    @Order(1)
    public void setUp() {
        Book book = new Book(null, "Робинзон Крузо", "Дефо", 2, 3);
        repository.save(book);

        book = new Book(null, "Ореховый Будда", "Борис Акунин", 2, 1);
        repository.save(book);

        book = new Book(null, "Тайные виды на гору Фудзи", "Виктор Пелевин", 1, 3);
        repository.save(book);

        repository.flush();

        System.out.println("!!!!");
    }

    @Test
    @Order(2)
    public void getBookById() throws Exception {
        this.mockMvc.perform(get("/books/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Робинзон Крузо")))
                .andExpect(jsonPath("$.author", is("Дефо")))
                .andExpect(jsonPath("$.rackNum", equalTo(2)))
                .andExpect(jsonPath("$.levelNum", equalTo(3)));
    }

    @Test
    @Order(3)
    public void updateBookById() throws Exception {
        String json =
                "{\n" +
                "    \"rackNum\":4\n" +
                "}\n";
        this.mockMvc.perform(put("/books/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rackNum", equalTo(4))
                );
    }

    @Test
    @Order(4)
    public void deleteBookById() throws Exception {
        this.mockMvc.perform(delete("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    public void addNewBook() throws Exception {
        String json =
                "{\n" +
                "    \"name\":\"Alice in wonderland\",\n" +
                "    \"author\":\"Lewis Carrol\",\n" +
                "    \"rackNum\":1,\n" +
                "    \"levelNum\":1\n" +
                "}";
        this.mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                ;
    }

    @Test
    @Order(6)
    public void findByBookName() throws Exception {
        String json =
                "{\n" +
                "    \"name\":\"wonder\"\n" +
                "}";
        this.mockMvc.perform(post("/books/findByBookName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name", is("Alice in wonderland")))
                ;
    }

    @Test
    @Order(7)
    public void getBookByRackNum() throws Exception {
        this.mockMvc.perform(get("/books/rack/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    @Order(8)
    public void getBookByLevelNum() throws Exception {
        this.mockMvc.perform(get("/books/level/3"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1
                        /*робинзона крузо удалили*/)));
    }

    @Test
    @Order(9)
    public void getBookByRackNumLevelNum() throws Exception {
        this.mockMvc.perform(get("/books/rack/1/level/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }

}
