package services;

import entities.Machine;
import entities.Salle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class MachineServiceTest {

    @Test
    public void testCreateMachineAndFindByMarque() {
        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        Salle salle = new Salle("S4", "Salle Multimédia");
        Assertions.assertTrue(salleService.create(salle));

        Machine machine = new Machine("M-HP-1", "HP", new Date(), salle);
        Assertions.assertTrue(machineService.create(machine), "La création de la machine doit réussir");

        List<Machine> hp = machineService.findByMarque("HP");
        Assertions.assertFalse(hp.isEmpty(), "La requête HQL doit retourner au moins une machine HP");
    }
}
