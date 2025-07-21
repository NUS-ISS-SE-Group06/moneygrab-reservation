package com.moola.fx.moneychanger.reservation.service;

import com.moola.fx.moneychanger.reservation.model.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> listAll();
    Reservation get(Integer id);
    Reservation save(Reservation entity);
    void delete(Integer id);
}
