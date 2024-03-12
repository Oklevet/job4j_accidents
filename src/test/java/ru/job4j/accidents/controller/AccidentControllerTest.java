package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.RulesService;
import ru.job4j.accidents.service.TypesService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private AccidentService accidentService;

    @Autowired
    private TypesService typesService;

    @Autowired
    private RulesService rulesService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnAccidentsTableForAccidents() throws Exception {
        assertThat(mockMvc).isNotNull();

        this.mockMvc.perform(get("/accidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents"))
                .andExpect(model().attributeExists("accidents"))
                .andExpect(model().attribute("accidents", accidentService.getAll()));
    }

    @Test
    @WithMockUser
    public void shouldReturnCrAccidentsTableForCrAccidents() throws Exception {
        assertThat(mockMvc).isNotNull();

        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("create/createAccident"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("rules"))
                .andExpect(model().attribute("types", typesService.findAllTypes()))
                .andExpect(model().attribute("rules", rulesService.findAllRules()));
    }

    @Test
    @WithMockUser
    public void shouldReturnEditAccidentByIdForEditAccidentId6() throws Exception {
        Accident accident = accidentService.getById(6).orElse(null);
        assertThat(mockMvc).isNotNull();

        this.mockMvc.perform(get("/editAccident?id=6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("create/editAccident"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("rules"))
                .andExpect(model().attributeExists("accident"))
                .andExpect(model().attribute("accident", accident))
                .andExpect(model().attribute("types", typesService.findAllTypes()))
                .andExpect(model().attribute("rules", rulesService.findAllRules()));
    }

    @Test
    @WithMockUser
    public void shouldReturnEditAccidentByIdForEditAccidentId0ThenError() throws Exception {
        assertThat(mockMvc).isNotNull();

        this.mockMvc.perform(get("/editAccident?id=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errors/404"));
    }
}