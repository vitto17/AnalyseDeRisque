package fr.gouv.agriculture.pro.demo.business.agriculteur.rule;

import fr.gouv.agriculture.orion.message.RuleReport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import fr.gouv.agriculture.orion.i18n.I18nService;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import java.util.Locale;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.unitils.easymock.EasyMockUnitils.*;

import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.TestedObject;

/**
 * @author MINAGRI
 * @version $Id: AddressRuleTestCase.java 38447 2015-04-01 14:57:19Z olivier.dupre $
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class AddressRuleTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressRuleTestCase.class);

    @TestedObject
    private AddressRule addressRule;

    @Mock
    private Agriculteur agriculteur;

    @Mock
    private I18nService i18nService;

    @Before
    public void setUp() {
        // Mock du i18nService pour pouvoir faire appel à Messages.getMessage("...")
        Messages.setI18nService(i18nService);
        expect(i18nService.getMessage(EasyMock.anyObject(String.class), EasyMock.anyObject(Locale.class))).andReturn("Toto").anyTimes();
    }

    /**
     * Test of validate method, of class AddressRule.
     */
    @Test
    public void testValidate() {
        LOGGER.debug("validate");

        expect(agriculteur.getAdresse1()).andReturn("Adresse 1").anyTimes();
        expect(agriculteur.getAdresse2()).andReturn("Adresse 2").anyTimes();
        expect(agriculteur.getCodePostal()).andReturn("31000").anyTimes();

        replay();

        RuleReport result = addressRule.validate(agriculteur);

        assertTrue(result.isEmpty());
    }

    /**
     * Test of validate method, of class AddressRule.
     */
    @Test
    public void testValidateKo1() {
        LOGGER.debug("validate KO - Adresse 1");

        expect(agriculteur.getAdresse1()).andReturn("    ").anyTimes();
        expect(agriculteur.getAdresse2()).andReturn("Adresse 2").anyTimes();
        expect(agriculteur.getCodePostal()).andReturn("31000").anyTimes();

        replay();

        RuleReport result = addressRule.validate(agriculteur);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getMessages().size());

        assertTrue(result.containsWarning());
        assertFalse(result.containsError());
    }

    /**
     * Test of validate method, of class AddressRule.
     */
    @Test
    public void testValidateKo2() {
        LOGGER.debug("validate KO - Adresse 2");

        expect(agriculteur.getAdresse1()).andReturn("Adresse 1").anyTimes();
        expect(agriculteur.getAdresse2()).andReturn(null).anyTimes();
        expect(agriculteur.getCodePostal()).andReturn("31000").anyTimes();

        replay();

        RuleReport result = addressRule.validate(agriculteur);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getMessages().size());

        assertFalse(result.containsWarning());
        assertFalse(result.containsError());
    }

    /**
     * Test of validate method, of class AddressRule.
     */
    @Test
    public void testValidateKoZip() {
        LOGGER.debug("validate KO - Zip code");

        expect(agriculteur.getAdresse1()).andReturn("Adresse 1").anyTimes();
        expect(agriculteur.getAdresse2()).andReturn("Adresse 2").anyTimes();
        expect(agriculteur.getCodePostal()).andReturn(null).anyTimes();

        replay();

        RuleReport result = addressRule.validate(agriculteur);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getMessages().size());

        assertFalse(result.containsWarning());
        assertTrue(result.containsError());
    }

    /**
     * Test of validate method, of class AddressRule.
     */
    @Test
    public void testValidateKoAll() {
        LOGGER.debug("validate KO - All");

        expect(agriculteur.getAdresse1()).andReturn(null).anyTimes();
        expect(agriculteur.getAdresse2()).andReturn("    ").anyTimes();
        expect(agriculteur.getCodePostal()).andReturn("").anyTimes();

        replay();

        RuleReport result = addressRule.validate(agriculteur);

        assertFalse(result.isEmpty());
        assertEquals(3, result.getMessages().size());

        assertTrue(result.containsWarning());
        assertTrue(result.containsError());
    }
}
