package com.evalueytor.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalueytor.empresa.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long >{

}
