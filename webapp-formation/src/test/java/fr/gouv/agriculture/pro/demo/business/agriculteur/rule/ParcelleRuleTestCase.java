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
import fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle;
import java.util.ArrayList;
import java.util.List;
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
 * @version $Id: ParcelleRuleTestCase.java 38440 2015-04-01 08:28:27Z olivier.dupre $
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class ParcelleRuleTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParcelleRuleTestCase.class);

    @TestedObject
    private ParcelleRule parcelleRule;

    @Mock
    private Agriculteur agriculteur;
    @Mock
    private Parcelle parcelle1, parcelle2, parcelle3, parcelle4;

    private List<Parcelle> parcellesList;

    @Mock
    private I18nService i18nService;

    @Before
    public void setUp() {
        // Mock du i18nService pour pouvoir faire appel à Messages.getMessage("...")
        Messages.setI18nService(i18nService);
        expect(i18nService.getMessage(EasyMock.anyObject(String.class), EasyMock.anyObject(Locale.class))).andReturn("Toto").anyTimes();

        expect(parcelle1.getLibelle()).andReturn("Libellé 1").anyTimes();
        expect(parcelle2.getLibelle()).andReturn("Libellé 2").anyTimes();
        expect(parcelle3.getLibelle()).andReturn("Libellé 3").anyTimes();
        expect(parcelle4.getLibelle()).andReturn("Libellé 1").anyTimes();

        parcellesList = new ArrayList<Parcelle>();
    }

    /**
     * Test of validate method, of class ParcelleRule.
     */
    @Test
    public void testValidate() {
        LOGGER.debug("validate");

        parcellesList.add(parcelle1);
        parcellesList.add(parcelle2);
        parcellesList.add(parcelle3);

        expect(agriculteur.getParcelles()).andReturn(parcellesList);

        replay();

        RuleReport result = parcelleRule.validate(agriculteur);

        assertTrue(result.isEmpty());
    }

    /**
     * Test of validate method, of class ParcelleRule.
     */
    @Test
    public void testValidateKo() {
        LOGGER.debug("validate KO");

        parcellesList.add(parcelle1);
        parcellesList.add(parcelle2);
        parcellesList.add(parcelle4);

        expect(agriculteur.getParcelles()).andReturn(parcellesList);
        replay();

        RuleReport result = parcelleRule.validate(agriculteur);

        assertFalse(result.isEmpty());
        assertTrue(result.containsError());
    }
}
