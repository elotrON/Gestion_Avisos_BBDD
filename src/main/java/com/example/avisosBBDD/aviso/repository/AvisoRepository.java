package com.example.avisosBBDD.aviso.repository;

import com.example.avisosBBDD.aviso.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AvisoRepository extends JpaRepository<Aviso, Integer> {
    List<Aviso> findByActivoTrue();


}


