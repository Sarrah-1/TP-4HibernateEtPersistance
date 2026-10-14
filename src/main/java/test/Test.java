package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        Salle salleA = new Salle();
        salleA.setCode("S1");
        salleA.setLibelle("Salle Informatique");
        salleService.create(salleA);

        Machine machine = new Machine();
        machine.setRef("M-101");
        machine.setMarque("Dell");
        machine.setDateAchat(new Date());
        machine.setSalle(salleA);
        machineService.create(machine);

        System.out.println("Données insérées avec succès ");

        System.out.println("Salles : " + salleService.getAll());
        System.out.println("Machines : " + machineService.getAll());
        System.out.println("Machines Dell (HQL) : " + machineService.findByMarque("Dell"));
    }
}
