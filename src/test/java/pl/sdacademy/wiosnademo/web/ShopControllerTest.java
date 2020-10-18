package pl.sdacademy.wiosnademo.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.wiosnademo.domain.Shop;

@SpringBootTest
class ShopControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private final ObjectMapper objectMapper = new ObjectMapper();
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void shouldLoadContext() throws Exception {
    final Shop shop = new Shop(null, "Bierda", "Biedranskiego 1",
        100, "111-111-111", List.of());
    final Shop expectedShop = new Shop(1L, "Bierda", "Biedranskiego 1",
        100, "111-111-111", List.of());

    final MvcResult result = mockMvc.perform(post("/api/shops")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(shop))) // obiekt java -> json string
        .andExpect(status().isCreated())
        .andReturn();
    final String body = result.getResponse().getContentAsString();
    // json jako string na obiekt java
    final Shop actualShop = objectMapper.readValue(body, Shop.class);
    assertThat(actualShop).isEqualTo(expectedShop);
  }
}