package br.com.emendes.jornadamilhasapi.unit.controller;

import br.com.emendes.jornadamilhasapi.controller.DestinationController;
import br.com.emendes.jornadamilhasapi.exception.ResourceNotFoundException;
import br.com.emendes.jornadamilhasapi.service.DestinationService;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;
import br.com.emendes.jornadamilhasapi.util.PageableResponse;
import br.com.emendes.jornadamilhasapi.util.faker.DestinationFaker;
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
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

  @Nested
  @DisplayName("Tests for fetch endpoint")
  class FetchEndpoint {

    private final String URL_TEMPLATE = "/api/destinations";
    private final Pageable PAGEABLE_DEFAULT = PageRequest.of(0, 10);

    @Test
    @DisplayName("fetch must return status 200 and Page<DestinationResponse> when fetch successfully")
    void fetch_MustReturnStatus200AndPageDestinationResponse_WhenFetchSuccessfully() throws Exception {
      BDDMockito.when(destinationServiceMock.fetch(PAGEABLE_DEFAULT))
          .thenReturn(new PageImpl<>(List.of(DestinationFaker.destinationResponse()), PAGEABLE_DEFAULT, 1));

      String actualContent = mockMvc.perform(get(URL_TEMPLATE))
          .andExpect(status().isOk())
          .andReturn().getResponse().getContentAsString();

      Page<DestinationResponse> actualResponseBody = mapper
          .readValue(actualContent, new TypeReference<PageableResponse<DestinationResponse>>() {
          });

      Assertions.assertThat(actualResponseBody).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    @DisplayName("fetch must return status 200 and Page<DestinationResponse> when fetch by name successfully")
    void fetch_MustReturnStatus200AndPageDestinationResponse_WhenFetchByNameSuccessfully() throws Exception {
      BDDMockito.when(destinationServiceMock.findByName(PAGEABLE_DEFAULT, "Veneza"))
          .thenReturn(new PageImpl<>(List.of(DestinationFaker.destinationResponse()), PAGEABLE_DEFAULT, 1));

      String actualContent = mockMvc.perform(get(URL_TEMPLATE).param("name", "Veneza"))
          .andExpect(status().isOk())
          .andReturn().getResponse().getContentAsString();

      Page<DestinationResponse> actualResponseBody = mapper
          .readValue(actualContent, new TypeReference<PageableResponse<DestinationResponse>>() {
          });

      Assertions.assertThat(actualResponseBody).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    @DisplayName("fetch must return status 404 and ProblemDetail when not found destination with name containing Veneza")
    void fetch_MustReturnStatus404AndProblemDetail_WhenNotFoundDestinationWithNameContainingVeneza() throws Exception {
      BDDMockito.given(destinationServiceMock.findByName(PAGEABLE_DEFAULT, "Veneza"))
          .willThrow(new ResourceNotFoundException("Destinations with name containing {Veneza} not found"));

      String actualContent = mockMvc.perform(get(URL_TEMPLATE).param("name", "Veneza"))
          .andExpect(status().isNotFound())
          .andReturn().getResponse().getContentAsString();

      ProblemDetail actualResponseBody = mapper.readValue(actualContent, ProblemDetail.class);

      Assertions.assertThat(actualResponseBody).isNotNull();
      Assertions.assertThat(actualResponseBody.getTitle()).isNotNull().isEqualTo("Not Found");
      Assertions.assertThat(actualResponseBody.getDetail()).isNotNull()
          .isEqualTo("Destinations with name containing {Veneza} not found");
      Assertions.assertThat(actualResponseBody.getStatus()).isEqualTo(404);
    }
  }

}