package com.moola.fx.moneychanger.reservation.service; 

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.mapper.TransactionMapper;
import com.moola.fx.moneychanger.reservation.model.Transaction;
import com.moola.fx.moneychanger.reservation.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link TransactionServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository repository;

    private TransactionServiceImpl service;

    private MockedStatic<TransactionMapper> mapperMock;


    private static Transaction entity(int id) {
        Transaction t = new Transaction();
        t.setId(id);
        t.setMoneyChangerId(77);
        t.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        return t;
    }

    private static TransactionDto dto(int id) {
        TransactionDto d = new TransactionDto();
        d.setId(id);
        d.setMoneyChangerId(77);
        d.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        return d;
    }


    @BeforeEach
    void setUp() {
        service = new TransactionServiceImpl(repository);
        mapperMock = Mockito.mockStatic(TransactionMapper.class);
    }

    @AfterEach
    void tearDown() {
        mapperMock.close();
    }

  

    @Test
    @DisplayName("listAll() should return list of mapped DTOs")
    void listAllReturnsMappedDtos() {
        List<Transaction> entities = List.of(entity(1), entity(2));
        when(repository.findAll()).thenReturn(entities);

        mapperMock.when(() -> TransactionMapper.toDto(entities.get(0))).thenReturn(dto(1));
        mapperMock.when(() -> TransactionMapper.toDto(entities.get(1))).thenReturn(dto(2));

        List<TransactionDto> result = service.listAll();

        assertThat(result)
                .hasSize(2)
                .extracting(TransactionDto::getId)
                .containsExactly(1, 2);

        verify(repository).findAll();
        mapperMock.verify(() -> TransactionMapper.toDto(any(Transaction.class)), times(2));
    }

    @Test
    @DisplayName("listByMoneyChanger() should return mapped DTOs by moneyChangerId")
    void listByMoneyChangerReturnsMappedDtos() {
        int mcId = 99;
        List<Transaction> entities = List.of(entity(10));
        when(repository.findByMoneyChangerId(mcId)).thenReturn(entities);

        mapperMock.when(() -> TransactionMapper.toDto(entities.get(0))).thenReturn(dto(10));

        List<TransactionDto> result = service.listByMoneyChanger(mcId);

        assertThat(result)
                .singleElement()
                .extracting(TransactionDto::getId)
                .isEqualTo(10);

        verify(repository).findByMoneyChangerId(mcId);
        mapperMock.verify(() -> TransactionMapper.toDto(entities.get(0)));
    }

    @Test
    @DisplayName("updateTransactionStatus() should return mapped DTO when update succeeds")
    void updateStatusSuccess() {
        int id = 1;
        String status = "COMPLETED";
        String comments = "Customer did not show up.";
        int userId = 9;

        Transaction entity = entity(id);
        entity.setCurrentStatus(status);

        when(repository.updateStatus(id, status, comments, userId)).thenReturn(1);
        when(repository.getReferenceById(id)).thenReturn(entity);

        TransactionDto expectedDto = dto(id);
        expectedDto.setCurrentStatus(status);

        mapperMock.when(() -> TransactionMapper.toDto(entity)).thenReturn(expectedDto);

        TransactionDto result = service.updateTransactionStatus(id, status, comments, userId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getCurrentStatus()).isEqualTo(status);

        verify(repository).updateStatus(id, status, comments, userId);
        verify(repository).getReferenceById(id);
        mapperMock.verify(() -> TransactionMapper.toDto(entity));
    }

    @Test
    @DisplayName("updateTransactionStatus() should throw if no rows updated")
    void updateStatusNotFound() {
        when(repository.updateStatus(anyInt(), anyString(), anyString(), anyInt())).thenReturn(0);

        assertThatThrownBy(() -> service.updateTransactionStatus(42, "CANCELLED", "No response", 9))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("42");

        verify(repository).updateStatus(42, "CANCELLED", "No response", 9);
        verify(repository, never()).getReferenceById(anyInt());
        mapperMock.verifyNoInteractions();
    }
}
