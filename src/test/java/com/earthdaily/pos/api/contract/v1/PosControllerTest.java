package com.earthdaily.pos.api.contract.v1;

import com.earthdaily.pos.data.PosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PosControllerTest {

    @InjectMocks
    PosController controller;

    @Mock
    PosRepository repository;


    @Test
    public void testAddPos_Created() {
        Pos pos = new Pos("123", 456, 789, "street name");
        ResponseEntity<Object> responseEntity = controller.create(pos);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/123");
    }

    @Test
    public void testAddPosFailure_BadRequest() {
        when(repository.save(any(Pos.class))).thenThrow(new RuntimeException("something went wrong"));

        Pos pos = new Pos("123", 456, 789, "street name");
        ResponseEntity<Object> responseEntity = controller.create(pos);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertNull(responseEntity.getHeaders().getLocation());
    }

    @Test
    public void testGetPos_OK() {
        Pos pos = new Pos("123", 456, 789, "street name");
        when(repository.findById("123")).thenReturn(Optional.of(pos));

        ResponseEntity<Pos> responseEntity = controller.get("123");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        assertThat(responseEntity.getBody().getId()).isEqualTo("123");
        assertThat(responseEntity.getBody().getLat()).isEqualTo(456);
        assertThat(responseEntity.getBody().getLng()).isEqualTo(789);
        assertThat(responseEntity.getBody().getName()).isEqualTo("street name");
    }

    @Test
    public void testGetPosFailure_NotFound() {
        when(repository.findById("321")).thenReturn(Optional.empty());

        ResponseEntity<Pos> responseEntity = controller.get("321");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testUpdatePos_OK() {
        Pos oldPos = new Pos("123", 456, 789, "street name");
        when(repository.findById("123")).thenReturn(Optional.of(oldPos));

        Pos newPos = new Pos("123", 654, 987, "street name v2");
        ResponseEntity<Pos> responseEntity = controller.update("123", newPos);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testUpdatePosSave_BadRequest() {
        Pos oldPos = new Pos("123", 456, 789, "street name");
        when(repository.findById("123")).thenReturn(Optional.of(oldPos));

        Pos newPos = new Pos("123", 654, 987, "street name v2");
        when(repository.save(newPos)).thenThrow(new RuntimeException("something went wrong"));
        ResponseEntity<Pos> responseEntity = controller.update("123", newPos);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testUpdatePos_NotFound() {
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        Pos newPos = new Pos("123", 654, 987, "street name v2");
        ResponseEntity<Pos> responseEntity = controller.update("123", newPos);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertNull(responseEntity.getBody());
    }
}


