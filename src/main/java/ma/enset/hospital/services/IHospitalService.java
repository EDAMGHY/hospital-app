package ma.enset.hospital.services;

import ma.enset.hospital.entities.Consultation;
import ma.enset.hospital.entities.Medecin;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.entities.RendezVous;

public interface IHospitalService  {
    Patient savePatient(Patient patient);

    Medecin saveMedecin(Medecin medecin);

    RendezVous saveRDV(RendezVous rdv);

    Consultation saveConsultation(Consultation consultation);

}
