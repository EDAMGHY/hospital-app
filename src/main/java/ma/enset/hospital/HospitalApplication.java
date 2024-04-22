package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repository.*;
import ma.enset.hospital.services.IHospitalService;
import ma.enset.hospital.services.UserService;
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
            RendezVousRepository rendezVousRepository,
            UserService userService
    ) {
        return args -> {
            User u = new User();
            u.setUsername("user1");
            u.setPassword("123456");
            userService.addUser(u);


            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword("123456");
            userService.addUser(u2);


            Stream.of("STUDENT", "USER", "ADMIN").forEach(name -> {
                Role role = new Role();
                role.setRoleName(name);
                userService.addRole(role);
            });


            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("admin", "USER");

            try {
                User user = userService.authenticate("user1", "123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("Roles");
                user.getRoles().forEach(System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }

//            Stream.of("John Doe", "Mary Doe", "Sam Smith").forEach(name -> {
//                Patient patient = new Patient();
//                patient.setNom(name);
//                patient.setMalade(false);
//                patient.setScore((int) Math.floor(Math.random() * 100 + 1));
//                patient.setDateNaissanec(new Date());
//                hospitalService.savePatient(patient);
//            });
//
//            Stream.of("Edward Burnwood", "Eren Yeager").forEach(name -> {
//                Medecin medecin = new Medecin();
//                medecin.setNom(name);
//                medecin.setSpeciality(Math.random() > 0.5 ? "DENTAL" : "CARDIO");
//                medecin.setEmail(slugify(name) + "@gmail.com");
//                hospitalService.saveMedecin(medecin);
//            });
//
//
//            //  Get Patient
//            Patient patient = patientRepository.findById(2L).orElse(null);
//            Medecin medecin = medecinRepository.findById(1L).orElse(null);
//
//
//            RendezVous rendezVous = new RendezVous();
//            rendezVous.setDate(new Date());
//            rendezVous.setStatus(StatusRDV.PENDING);
//            rendezVous.setPatient(patient);
//            rendezVous.setMedecin(medecin);
//            hospitalService.saveRDV(rendezVous);
//            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
//            Consultation consultation = new Consultation();
//            consultation.setDateConsultation(rendezVous1.getDate());
//            consultation.setRendezVous(rendezVous1);
//            consultation.setRapport("Rapport de la consultation");
//            hospitalService.saveConsultation(consultation);
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
