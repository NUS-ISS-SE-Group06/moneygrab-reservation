package com.moola.fx.moneychanger.reservation.service;

import com.moola.fx.moneychanger.reservation.exception.ResourceNotFoundException;
import com.moola.fx.moneychanger.reservation.model.Reservation;
import com.moola.fx.moneychanger.reservation.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repo;

    @Autowired
    public ReservationServiceImpl(ReservationRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Reservation> listAll() {
        return repo.findAll();
    }

    @Override
    public Reservation get(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    @Override
    @Transactional
    public Reservation save(Reservation entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Integer id) {
        Reservation existing = get(id);
        repo.delete(existing);
    }
}