package ru.job4j.accidents.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.service.AccidentService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class IndexControllerTest {

    @Autowired
    private AccidentService accidentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void index() throws Exception {
        assertThat(mockMvc).isNotNull();

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/index"));
    }

    @Test
    @WithMockUser
    public void shouldReturnAccTableForIndex() throws Exception {
        assertThat(mockMvc).isNotNull();

        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("accidents"))
                .andExpect(model().attribute("accidents", accidentService.getAll()));
    }
}