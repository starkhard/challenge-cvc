package br.com.cvc.http;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.service.impl.DefaultCVCRecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class HotelsControllerTest {

    private static final String URI_GET_ALL_HOTELS = "/hotel/v1/consult/findAllHotelsByCityId";
    private static final String URI_GET_SPECIFIC_HOTELS = "/hotel/v1/consult/findHotelById";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private DefaultCVCRecordService defaultCVCRecordService;


    @Test
    public void testReturnOfPayloadSearchingAllHotels() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(URI_GET_ALL_HOTELS).
                accept(MediaType.APPLICATION_JSON_VALUE).
                content(rightPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        ReservationBuilder[] responseBuilder = new ObjectMapper().
                readValue(mvcResult.getResponse().getContentAsString(), ReservationBuilder[].class);


        Assertions.assertArrayEquals(responseBuilder, Arrays.stream(responseBuilder).toArray());
        Assertions.assertTrue(responseBuilder[0].getTotalPrice().doubleValue() > 0);
    }


    @Test
    public void testReturnOfPayloadSearchingByHotelId() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(URI_GET_SPECIFIC_HOTELS).
                accept(MediaType.APPLICATION_JSON_VALUE).
                content(rightPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


        ReservationBuilder [] responseBuilder = new ObjectMapper().
                readValue(mvcResult.getResponse().getContentAsString(), ReservationBuilder[].class);

        Assertions.assertNotNull(responseBuilder);
        Assertions.assertTrue(responseBuilder[0].getTotalPrice().doubleValue() > 0);
        Assertions.assertTrue(responseBuilder[0].getPriceDetails() != null);
        Assertions.assertTrue(!responseBuilder[0].getRooms().isEmpty());
        Assertions.assertTrue(!responseBuilder[0].getCity().isEmpty());
    }

    @Test
    public void testexpectedErrorWithAinvalidaPayload() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(URI_GET_ALL_HOTELS).
                accept(MediaType.APPLICATION_JSON_VALUE).
                content("")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCallAllHotelsCVCApiWithFeignClient() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(URI_GET_ALL_HOTELS).
                accept(MediaType.APPLICATION_JSON_VALUE).
                content(rightPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindASpecificHotelById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(URI_GET_SPECIFIC_HOTELS).
                accept(MediaType.APPLICATION_JSON_VALUE).
                content(rightPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String rightPayload() {
        CVCReservationDTO cvcReservationDTO = new CVCReservationDTO();
        cvcReservationDTO.setCheckInDate(LocalDate.now());
        cvcReservationDTO.setCheckOutDate(LocalDate.of(2020, 07, 24));
        cvcReservationDTO.setNumberOfAdults(1);
        cvcReservationDTO.setNumberOfChildren(1);
        cvcReservationDTO.setId("1032");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(cvcReservationDTO);
        } catch (JsonProcessingException e) {
            log.error("error to parse dto " + e.getMessage());
        }
        return "";
    }
}
