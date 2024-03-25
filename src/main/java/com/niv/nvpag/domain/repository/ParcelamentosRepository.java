package com.niv.nvpag.domain.repository;

import com.niv.nvpag.domain.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelamentosRepository extends JpaRepository<Parcelamento, Long> {
}
