package br.com.emendes.jornadamilhasapi.unit.controller;

import br.com.emendes.jornadamilhasapi.controller.ImageController;
import br.com.emendes.jornadamilhasapi.exception.ResourceNotFoundException;
import br.com.emendes.jornadamilhasapi.service.ImageService;
import br.com.emendes.jornadamilhasapi.util.faker.ImageFaker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testes para a ImageController.
 */
@WebMvcTest(controllers = {ImageController.class})
@DisplayName("Unit tests for ImageController")
class ImageControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @MockBean
  private ImageService imageServiceMock;
  private static final String[] MEDIA_TYPE_ACCEPT = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE};

  @Nested
  @DisplayName("Tests for findById endpoint")
  class FindByIdEndpoint {

    private final String URL_TEMPLATE = "/api/images/{id}";

    @Test
    @DisplayName("findById must return status 200 when found successfully")
    void findById_MustReturnStatus200AndImageResponse_WhenFoundSuccessfully() throws Exception {
      BDDMockito.when(imageServiceMock.findById(any()))
          .thenReturn(ImageFaker.resource());

      mockMvc.perform(get(URL_TEMPLATE, "abcdef1234567890abcdef12").accept(MEDIA_TYPE_ACCEPT))
          .andExpect(status().isOk());
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
          .contains("id must be hexadecimal token", "id must be 24 characters long");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(400);
    }

    @Test
    @DisplayName("findById must return status 404 and ProblemDetail when not find image")
    void findById_MustReturnStatus404AndProblemDetail_WhenNotFindImage() throws Exception {
      BDDMockito.given(imageServiceMock.findById(any()))
          .willThrow(new ResourceNotFoundException("Image not found"));

      String actualContent = mockMvc
          .perform(get(URL_TEMPLATE, "abcdef1234567890abcdef12"))
          .andExpect(status().isNotFound())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Not Found");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .isEqualTo("Image not found");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(404);
    }

  }

}