package com.moola.fx.moneychanger.reservation.service;

import com.moola.fx.moneychanger.reservation.exception.ResourceNotFoundException;
import com.moola.fx.moneychanger.reservation.model.Reservation;
import com.moola.fx.moneychanger.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository repository;

    @InjectMocks
    private ReservationServiceImpl service;

    private Reservation makeReservation(Integer id, boolean deleted) {
        Reservation r = new Reservation();
        r.setId(id);
        r.setCustomerId(1);
        r.setMoneyChangerId(5);
        r.setCurrencyId(2);
        r.setExchangeRate(new BigDecimal("1.3500"));
        r.setForeignAmount(new BigDecimal("100"));
        r.setSgdAmount(new BigDecimal("135.00"));
        r.setStatus("PENDING");
        r.setExpiresAt(new Timestamp(System.currentTimeMillis() + 1000000));
        return r;
    }

    @Test
    void listAll_returnsAllReservations() {
        Reservation r1 = makeReservation(1, false);
        Reservation r2 = makeReservation(2, false);

        when(repository.findAll()).thenReturn(List.of(r1, r2));

        List<Reservation> result = service.listAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void get_found_returnsReservation() {
        Reservation r = makeReservation(1, false);
        when(repository.findById(1)).thenReturn(Optional.of(r));

        Reservation result = service.get(1);

        assertEquals(1, result.getId());
        verify(repository).findById(1);
    }

    @Test
    void get_notFound_throwsException() {
        when(repository.findById(999)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.get(999));
    }

    @Test
    void save_createsOrUpdatesReservation() {
        Reservation r = makeReservation(null, false);

        when(repository.save(r)).thenReturn(r);

        Reservation saved = service.save(r);

        assertNotNull(saved);
        verify(repository).save(r);
    }

    @Test
    void delete_successful() {
        Reservation r = makeReservation(1, false);

        when(repository.findById(1)).thenReturn(Optional.of(r));

        service.delete(1);

        verify(repository).delete(r);
    }

    @Test
    void delete_notFound_throwsException() {
        when(repository.findById(123)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.delete(123));
    }
}
