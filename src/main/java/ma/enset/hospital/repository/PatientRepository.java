package ma.enset.hospital.repository;

import ma.enset.hospital.entities.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import javax.swing.*;
import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByNomContains(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.nom = :nom, p.dateNaissanec = :dateNaissanec, p.malade = :malade, p.score = :score WHERE p.id = :id")
    void updatePatient(@Param("id") Long id, @Param("nom") String nom, @Param("dateNaissanec") Date dateNaissanec, @Param("malade") Boolean malade, @Param("score") int score);


    @Transactional
    @Modifying
    @Query("DELETE FROM Patient p WHERE p.id = :id")
    void deletePatient(@Param("id") Long id);

}
