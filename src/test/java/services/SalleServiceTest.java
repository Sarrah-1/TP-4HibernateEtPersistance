package services;

import entities.Salle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SalleServiceTest {

    @Test
    public void testCreateSalle() {
        SalleService service = new SalleService();
        Salle salle = new Salle();
        salle.setCode("S2");
        salle.setLibelle("Salle Réseau");

        boolean result = service.create(salle);
        Assertions.assertTrue(result, "La création de la salle doit réussir");
    }

    @Test
    public void testGetByIdAndUpdateAndDelete() {
        SalleService service = new SalleService();
        Salle salle = new Salle("S3", "Salle Systèmes");
        Assertions.assertTrue(service.create(salle));

        Salle lue = service.getById(salle.getId());
        Assertions.assertNotNull(lue);
        Assertions.assertEquals("S3", lue.getCode());

        lue.setLibelle("Salle Systèmes (modifiée)");
        Assertions.assertTrue(service.update(lue));
        Assertions.assertEquals("Salle Systèmes (modifiée)", service.getById(lue.getId()).getLibelle());

        Assertions.assertTrue(service.delete(lue));
        Assertions.assertNull(service.getById(lue.getId()));
    }
}
