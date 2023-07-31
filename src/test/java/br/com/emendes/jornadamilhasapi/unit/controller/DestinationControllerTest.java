package br.com.emendes.jornadamilhasapi.unit.controller;

import br.com.emendes.jornadamilhasapi.controller.DestinationController;
import br.com.emendes.jornadamilhasapi.service.DestinationService;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;
import br.com.emendes.jornadamilhasapi.util.faker.DestinationFaker;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testes para a DestinationController.
 */
@WebMvcTest(controllers = {DestinationController.class})
@DisplayName("Unit tests for DestinationController")
class DestinationControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @MockBean
  private DestinationService destinationServiceMock;

  @Nested
  @DisplayName("Tests for save endpoint")
  class SaveEndpoint {

    private static final String MEDIA_TYPE_ACCEPT = "application/json;charset=UTF-8";
    private final String URL_TEMPLATE = "/api/destinations";

    @Test
    @DisplayName("save must return status 201 and DestinationResponse when save successfully")
    void save_MustReturnStatus201AndDestinationResponse_WhenSaveSuccessfully() throws Exception {
      BDDMockito.when(destinationServiceMock.save(any(), any()))
          .thenReturn(DestinationFaker.destinationResponse());

      String destinationJson = """
          {
            "name": "Veneza - Itália",
            "price": 500.00
          }
          """;

      MockMultipartFile destinationInfo = new MockMultipartFile(
          "destination_info", null, MediaType.APPLICATION_JSON_VALUE, destinationJson.getBytes());

      InputStream image = new FileInputStream("src/test/resources/image/veneza01.jpg");

      MockMultipartFile destinationImage = new MockMultipartFile(
          "destination_image", "veneza01.jpg", MediaType.IMAGE_JPEG_VALUE, image);

      String actualContent = mockMvc.perform(multipart(URL_TEMPLATE)
              .file(destinationImage)
              .file(destinationInfo)
              .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
              .accept(MEDIA_TYPE_ACCEPT)
          )
          .andExpect(status().isCreated())
          .andReturn().getResponse().getContentAsString();

      DestinationResponse actualResponseBody = mapper.readValue(actualContent, DestinationResponse.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.id()).isNotNull();
      Assertions.assertThat(actualResponseBody.name()).isNotNull().isEqualTo("Veneza - Itália");
      Assertions.assertThat(actualResponseBody.price()).isNotNull().isEqualTo("500.00");
      Assertions.assertThat(actualResponseBody.urlImage())
          .isNotNull().isEqualByComparingTo(URI.create("http://urlimage.com/api/images/aaaabbbbccccddddeeeeffff"));
      Assertions.assertThat(actualResponseBody.createdAt()).isNotNull();
    }

    @Test
    @DisplayName("save must return status 400 and ProblemDetail when destination_info has invalid fields")
    void save_MustReturnStatus400AndProblemDetail_WhenDestinationInfoHasInvalidFields() throws Exception {
      String destinationJson = """
          {
            "name": "",
            "price": 500.00
          }
          """;

      MockMultipartFile destinationInfo = new MockMultipartFile(
          "destination_info", null, MediaType.APPLICATION_JSON_VALUE, destinationJson.getBytes());

      InputStream image = new FileInputStream("src/test/resources/image/veneza01.jpg");

      MockMultipartFile destinationImage = new MockMultipartFile(
          "destination_image", "veneza01.jpg", MediaType.IMAGE_JPEG_VALUE, image);

      String actualContent = mockMvc.perform(multipart(URL_TEMPLATE)
              .file(destinationImage)
              .file(destinationInfo)
              .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
              .accept(MEDIA_TYPE_ACCEPT)
          )
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

      Assertions.assertThat(actualFields).isNotNull().contains("name");
      Assertions.assertThat(actualMessages).isNotNull()
          .contains("name must not be blank", "name must contain between 2 and 150 characters long");
    }

    @Test
    @DisplayName("save must return status 400 and ProblemDetail when destination_image is invalid format")
    void save_MustReturnStatus400AndProblemDetail_WhenDestinationImageIsInvalidFormat() throws Exception {
      String destinationJson = """
          {
            "name": "Veneza - Itália",
            "price": 500.00
          }
          """;

      MockMultipartFile destinationInfo = new MockMultipartFile(
          "destination_info", null, MediaType.APPLICATION_JSON_VALUE, destinationJson.getBytes());

      InputStream image = new FileInputStream("src/test/resources/image/veneza02.gif");

      MockMultipartFile destinationImage = new MockMultipartFile(
          "destination_image", "veneza02.gif", MediaType.IMAGE_GIF_VALUE, image);

      String actualContent = mockMvc.perform(multipart(URL_TEMPLATE)
              .file(destinationImage)
              .file(destinationInfo)
              .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
              .accept(MEDIA_TYPE_ACCEPT)
          )
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Bad Request");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .isEqualTo("file format must be [jpeg, png]");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(400);
    }

  }

}