package com.moola.fx.moneychanger.reservation.repository;

import com.moola.fx.moneychanger.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}

