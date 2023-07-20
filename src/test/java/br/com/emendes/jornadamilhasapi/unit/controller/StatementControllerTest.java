package br.com.emendes.jornadamilhasapi.unit.controller;

import br.com.emendes.jornadamilhasapi.controller.StatementController;
import br.com.emendes.jornadamilhasapi.exception.ResourceNotFoundException;
import br.com.emendes.jornadamilhasapi.service.StatementService;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import br.com.emendes.jornadamilhasapi.util.PageableResponse;
import br.com.emendes.jornadamilhasapi.util.faker.StatementFaker;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testes para a StatementController.
 */
@WebMvcTest(controllers = {StatementController.class})
@DisplayName("Unit tests for StatementController")
class StatementControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @MockBean
  private StatementService statementServiceMock;

  private final String CONTENT_TYPE = "application/json;charset=UTF-8";

  @Nested
  @DisplayName("Tests for save endpoint")
  class SaveEndpoint {

    private final String URL_TEMPLATE = "/api/statements";

    @Test
    @DisplayName("save must return status 201 and StatementResponse when save successfully")
    void save_MustReturnStatus201AndStatementResponse_WhenSaveSuccessfully() throws Exception {
      BDDMockito.when(statementServiceMock.save(any()))
          .thenReturn(StatementFaker.statementResponse());

      String requestBody = """
          {
            "username": "Lorem Ipsum",
            "text": "lorem ipsum dolor sit amet",
            "urlImage": "https://www.imagexpto.com/123"
          }
          """;

      String actualContent = mockMvc.perform(post(URL_TEMPLATE).contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isCreated())
          .andReturn().getResponse().getContentAsString();

      StatementResponse actualResponseBody = mapper.readValue(actualContent, StatementResponse.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.id()).isNotNull();
      Assertions.assertThat(actualResponseBody.username()).isNotNull().isEqualTo("Lorem Ipsum");
      Assertions.assertThat(actualResponseBody.text())
          .isNotNull().isEqualTo("lorem ipsum dolor sit amet");
      Assertions.assertThat(actualResponseBody.urlImage())
          .isNotNull().isEqualTo("https://www.imagexpto.com/123");
      Assertions.assertThat(actualResponseBody.createdAt()).isNotNull();
    }

    @Test
    @DisplayName("save must return status 400 and ProblemDetail when request body has invalid fields")
    void save_MustReturnStatus400AndProblemDetail_WhenRequestBodyHasInvalidFields() throws Exception {
      String requestBody = """
          {
            "username": "",
            "text": "lorem ipsum dolor sit amet",
            "urlImage": "https://www.imagexpto.com/123"
          }
          """;

      String actualContent = mockMvc.perform(post(URL_TEMPLATE).contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Bad Request");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .isEqualTo("Invalid field(s)");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(400);

      Assertions.assertThat(actualResponseBody.getProperties()).isNotNull();

      String actualFields = (String) actualResponseBody.getProperties().get("fields");
      String actualMessages = (String) actualResponseBody.getProperties().get("errorMessages");

      Assertions.assertThat(actualFields).isNotNull().contains("username");
      Assertions.assertThat(actualMessages).isNotNull()
          .contains("username must not be blank", "username must contain between 2 and 100 characters long");
    }

  }

  @Nested
  @DisplayName("Tests for fetch endpoint")
  class FetchEndpoint {

    private final Pageable PAGEABLE_DEFAULT = PageRequest.of(0, 10);

    @Test
    @DisplayName("fetch must return status 200 and Page<StatementResponse> when fetch successfully")
    void fetch_MustReturnStatus200AndPageStatementResponse_WhenFetchSuccessfully() throws Exception {
      BDDMockito.when(statementServiceMock.fetch(PAGEABLE_DEFAULT))
          .thenReturn(new PageImpl<>(List.of(StatementFaker.statementResponse()), PAGEABLE_DEFAULT, 1));

      String URL_TEMPLATE = "/api/statements";
      String actualContent = mockMvc.perform(get(URL_TEMPLATE))
          .andExpect(status().isOk())
          .andReturn().getResponse().getContentAsString();

      Page<StatementResponse> actualResponseBody = mapper
          .readValue(actualContent, new TypeReference<PageableResponse<StatementResponse>>() {
          });

      Assertions.assertThat(actualResponseBody).isNotNull().isNotEmpty().hasSize(1);
    }

  }

  @Nested
  @DisplayName("Tests for findById endpoint")
  class FindByIdEndpoint {

    private final String URL_TEMPLATE = "/api/statements/{id}";

    @Test
    @DisplayName("findById must return status 200 and StatementResponse when found successfully")
    void findById_MustReturnStatus200AndStatementResponse_WhenFoundSuccessfully() throws Exception {
      BDDMockito.when(statementServiceMock.findById(any()))
          .thenReturn(StatementFaker.statementResponse());

      String actualContent = mockMvc
          .perform(get(URL_TEMPLATE, "1234567890abcdef12345678"))
          .andExpect(status().isOk())
          .andReturn().getResponse().getContentAsString();

      StatementResponse actualResponseBody = mapper.readValue(actualContent, StatementResponse.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.id()).isNotNull().isEqualTo("1234567890abcdef12345678");
      Assertions.assertThat(actualResponseBody.username()).isNotNull().isEqualTo("Lorem Ipsum");
      Assertions.assertThat(actualResponseBody.text()).isNotNull().isEqualTo("lorem ipsum dolor sit amet");
      Assertions.assertThat(actualResponseBody.urlImage()).isNotNull();
      Assertions.assertThat(actualResponseBody.createdAt()).isNotNull();
    }

    @Test
    @DisplayName("findById must return status 400 and ProblemDetail when id is invalid")
    void findById_MustReturnStatus400AndProblemDetail_WhenIdIsInvalid() throws Exception {
      String actualContent = mockMvc
          .perform(get(URL_TEMPLATE, "1234567890ZX"))
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Bad Request");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .contains("id must be hexadecimal value", "id must be 24 characters long");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(400);
    }

    @Test
    @DisplayName("findById must return status 404 and ProblemDetail when not find statement")
    void findById_MustReturnStatus404AndProblemDetail_WhenNotFindStatement() throws Exception {
      BDDMockito.given(statementServiceMock.findById(any()))
          .willThrow(new ResourceNotFoundException("Statement not found"));

      String actualContent = mockMvc
          .perform(get(URL_TEMPLATE, "1234567890abcdef12345678"))
          .andExpect(status().isNotFound())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Not Found");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .isEqualTo("Statement not found");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(404);
    }

  }

  @Nested
  @DisplayName("Tests for fetchLast endpoint")
  class FetchLastEndpoint {

    @Test
    @DisplayName("fetchLast must return status 200 and List<StatementResponse> when found successfully")
    void fetchLast_MustReturnStatus200AndListStatementResponse_WhenFoundSuccessfully() throws Exception {
      StatementResponse statementResponse = StatementFaker.statementResponse();
      BDDMockito.when(statementServiceMock.fetchLast(3))
          .thenReturn(List.of(statementResponse, statementResponse, statementResponse));

      String actualContent = mockMvc.perform(get("/api/statements/home"))
          .andExpect(status().isOk())
          .andReturn().getResponse().getContentAsString();

      List<StatementResponse> actualResponseBody = mapper.readValue(actualContent, new TypeReference<>() {
      });

      Assertions.assertThat(actualResponseBody).isNotNull().hasSize(3);
    }

  }

  @Nested
  @DisplayName("Tests for update endpoint")
  class UpdateEndpoint {

    private static final String URL_TEMPLATE = "/api/statements/{id}";

    @Test
    @DisplayName("update must return status 204 when update successfully")
    void update_MustReturnStatus204_WhenUpdateSuccessfully() throws Exception {
      String requestBody = """
          {
            "username": "Lorem Ipsum",
            "text": "lorem ipsum dolor sit amet updated",
            "urlImage": "https://www.imagexpto.com/123456"
          }
          """;

      mockMvc.perform(put(URL_TEMPLATE, "1234567890abcdef12345678")
              .contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("update must return status 400 and ProblemDetail when request body has invalid fields")
    void update_MustReturnStatus400AndProblemDetail_WhenRequestBodyHasInvalidFields() throws Exception {
      String requestBody = """
          {
            "username": "",
            "text": "lorem ipsum dolor sit amet updated",
            "urlImage": "https://www.imagexpto.com/123456"
          }
          """;

      String actualContent = mockMvc.perform(put(URL_TEMPLATE, "1234567890abcdef12345678")
              .contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Bad Request");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .isEqualTo("Invalid field(s)");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(400);

      Assertions.assertThat(actualResponseBody.getProperties()).isNotNull();

      String actualFields = (String) actualResponseBody.getProperties().get("fields");
      String actualMessages = (String) actualResponseBody.getProperties().get("errorMessages");

      Assertions.assertThat(actualFields).isNotNull().contains("username");
      Assertions.assertThat(actualMessages).isNotNull()
          .contains("username must not be blank", "username must contain between 2 and 100 characters long");
    }

    @Test
    @DisplayName("update must return status 400 and ProblemDetail when id is invalid")
    void update_MustReturnStatus400AndProblemDetail_WhenIdIsInvalid() throws Exception {
      String requestBody = """
          {
            "username": "Lorem Ipsum",
            "text": "lorem ipsum dolor sit amet updated",
            "urlImage": "https://www.imagexpto.com/123456"
          }
          """;

      String actualContent = mockMvc.perform(put(URL_TEMPLATE, "1234567890X")
              .contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Bad Request");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .contains("id must be hexadecimal value", "id must be 24 characters long");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(400);
    }

    @Test
    @DisplayName("update must return status 404 and ProblemDetail when not find statement")
    void update_MustReturnStatus404AndProblemDetail_WhenNotFindStatement() throws Exception {
      BDDMockito.willThrow(new ResourceNotFoundException("Statement not found"))
          .given(statementServiceMock).update(any(), any());

      String requestBody = """
          {
            "username": "Lorem Ipsum",
            "text": "lorem ipsum dolor sit amet updated",
            "urlImage": "https://www.imagexpto.com/123456"
          }
          """;

      String actualContent = mockMvc.perform(put(URL_TEMPLATE, "1234567890abcdef12345678")
              .contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isNotFound())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Not Found");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .isEqualTo("Statement not found");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(404);
    }

  }

}