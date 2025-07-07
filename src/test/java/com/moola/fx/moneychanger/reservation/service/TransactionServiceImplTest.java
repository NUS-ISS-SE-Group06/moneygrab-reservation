package com.moola.fx.moneychanger.reservation.service;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.mapper.TransactionMapper;
import com.moola.fx.moneychanger.reservation.model.Transaction;
import com.moola.fx.moneychanger.reservation.repository.TransactionRepository;
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
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link TransactionServiceImpl}.
 * <p>
 * Uses Mockito for repository mocking and Mockito's static-mock
 * feature to intercept {@code TransactionMapper.toDto}.
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository repository;

    private TransactionServiceImpl service;

    private MockedStatic<TransactionMapper> mapperMock;  // static mock handle

    /* ---------- helper ---------- */

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

    /* ---------- test life-cycle ---------- */

    @BeforeEach
    void setUp() {
        service = new TransactionServiceImpl(repository);

        // Static mock for TransactionMapper.toDto(...)
        mapperMock = Mockito.mockStatic(TransactionMapper.class);
    }

    @AfterEach
    void tearDown() {
        mapperMock.close();
    }

    /* ---------- tests ---------- */

    @Test
    @DisplayName("listAll() maps every entity to DTO and returns the list")
    void listAllReturnsMappedDtos() {
        // given
        List<Transaction> entities = List.of(entity(1), entity(2));
        when(repository.findAll()).thenReturn(entities);

        // stub static mapper
        mapperMock.when(() -> TransactionMapper.toDto(entities.get(0)))
                  .thenReturn(dto(1));
        mapperMock.when(() -> TransactionMapper.toDto(entities.get(1)))
                  .thenReturn(dto(2));

        // when
        List<TransactionDto> result = service.listAll();

        // then
        assertThat(result).hasSize(2)
                          .extracting(TransactionDto::getId)
                          .containsExactly(1, 2);

        verify(repository).findAll();
        mapperMock.verify(() -> TransactionMapper.toDto(any(Transaction.class)), times(2));
    }

    @Test
    @DisplayName("listByMoneyChanger() delegates to repository and maps result")
    void listByMoneyChangerReturnsMappedDtos() {
        int mcId = 99;
        List<Transaction> entities = List.of(entity(10));
        when(repository.findByMoneyChangerId(mcId)).thenReturn(entities);

        mapperMock.when(() -> TransactionMapper.toDto(entities.get(0)))
                  .thenReturn(dto(10));

        List<TransactionDto> result = service.listByMoneyChanger(mcId);

        assertThat(result).singleElement()
                          .extracting(TransactionDto::getId)
                          .isEqualTo(10);

        verify(repository).findByMoneyChangerId(mcId);
        mapperMock.verify(() -> TransactionMapper.toDto(entities.get(0)));
    }
}
