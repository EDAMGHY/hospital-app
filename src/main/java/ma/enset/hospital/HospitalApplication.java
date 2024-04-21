package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repository.ConsultationRepository;
import ma.enset.hospital.repository.MedecinRepository;
import ma.enset.hospital.repository.PatientRepository;
import ma.enset.hospital.repository.RendezVousRepository;
import ma.enset.hospital.services.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static ma.enset.hospital.utlis.Utils.slugify;

@SpringBootApplication
public class HospitalApplication {
    //public class HospitalApplication implements CommandLineRunner {
//    @Autowired
//    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }


    @Bean
    CommandLineRunner start(
            IHospitalService hospitalService,
            PatientRepository patientRepository,
            MedecinRepository medecinRepository,
            RendezVousRepository rendezVousRepository
    ) {
        return args -> {
            Stream.of("John Doe", "Mary Doe", "Sam Smith").forEach(name -> {
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setMalade(false);
                patient.setScore((int) Math.floor(Math.random() * 100 + 1));
                patient.setDateNaissanec(new Date());
                hospitalService.savePatient(patient);
            });

            Stream.of("Edward Burnwood", "Eren Yeager").forEach(name -> {
                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setSpeciality(Math.random() > 0.5 ? "DENTAL" : "CARDIO");
                medecin.setEmail(slugify(name) + "@gmail.com");
                hospitalService.saveMedecin(medecin);
            });


            //  Get Patient
            Patient patient = patientRepository.findById(2L).orElse(null);
            Medecin medecin = medecinRepository.findById(1L).orElse(null);


            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            hospitalService.saveRDV(rendezVous);
            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(rendezVous1.getDate());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation");
            hospitalService.saveConsultation(consultation);
        };
    }

//     @Override
//     public void run(String... args) throws Exception {
//        //  Add Patients
//        System.out.println("**************");
//        patientRepository.save(new Patient(null, "John Doe", new Date("05/01/2000"), true, 100));
//        patientRepository.save(new Patient(null, "Maria Doe", new Date("10/25/1970"), false, 200));
//        patientRepository.save(new Patient(null, "Bob Smith", new Date("07/12/1997"), true, 70));
//        //  List Patients
//        System.out.println("**************");
//        List<Patient> list = patientRepository.findAll();
//        list.forEach(System.out::println);
//        //  Get Patient
//        System.out.println("**************");
//        Patient patient = patientRepository.findById(2L).orElse(null);
//        System.out.println("patient" + patient);
//
//        //  Search for a Patient
//        System.out.println("**************");
//        List<Patient> searchedPatients = patientRepository.findByNomContains("Doe");
//        searchedPatients.forEach(System.out::println);
//
//        //  Update a Patient
//        System.out.println("**************");
//        patientRepository.updatePatient(3L, "Edward J Smith", new Date("05/17/1980"), false, 120);
//        List<Patient> updatedList = patientRepository.findAll();
//        updatedList.forEach(System.out::println);
//
//        //  Update a Patient
//        System.out.println("**************");
//        patientRepository.deletePatient(2L);
//        List<Patient> deletedList = patientRepository.findAll();
//        deletedList.forEach(System.out::println);
//    }
}
