package pe.reto.basic.bcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.reto.basic.bcp.entity.Tipocambio;

@Repository
public interface TipoCambioRepository extends JpaRepository<Tipocambio, Integer>{

}

