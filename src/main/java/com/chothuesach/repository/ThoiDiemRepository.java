package com.chothuesach.repository;

import com.chothuesach.model.ThoiDiem;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface ThoiDiemRepository extends CrudRepository<ThoiDiem, Date> {
}
