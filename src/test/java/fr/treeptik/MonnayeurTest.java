package fr.treeptik;


import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static junit.framework.TestCase.assertEquals;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MonnayeurTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAvecUneSeulePieceARendre() {
        Monnayeur monnayeur = new Monnayeur(asList(25));
        assertEquals(
                singletonList(25),
                monnayeur.rendre(25));
    }

    @Ignore
    @Test
    public void testAvecUneSeulePieceARendreMaisPlusieursPiecesDisponibles() {
        Monnayeur monnayeur = new Monnayeur(asList(100, 25, 10, 5, 2, 1));
        assertEquals(
                singletonList(25),
                monnayeur.rendre(25));
    }

    @Ignore
    @Test
    public void testAvecUneSeulePieceARendreMaisPlusieursPiecesDisponiblesRangesAleatoirement() {
        Monnayeur monnayeur = new Monnayeur(asList(5, 10, 1, 25, 10, 25, 100));
        assertEquals(
                singletonList(25),
                monnayeur.rendre(25));
    }

    @Ignore
    @Test
    public void testAvecDeuxPiecesARendre() {
        Monnayeur monnayeur = new Monnayeur(asList(1, 5, 10, 25, 100));

        assertEquals(
                asList(10, 5),
                monnayeur.rendre(15));
    }

    @Ignore
    @Test
    public void testLeCLientQuiNaPasDeBillet() {
        // Attention les pièces ont changé :)
        Monnayeur monnayeur = new Monnayeur(asList(1, 5, 10, 21, 25));

        assertEquals(emptyList(),
                monnayeur.rendre(0));
    }

    @Ignore
    @Test
    public void testImpossibleDeRendreLaMonnaieCarMontantTropPetit() {
        Monnayeur monnayeur = new Monnayeur(asList(5, 10));

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Un montant de 3 est trop petit.");

        assertEquals(emptyList(),
                monnayeur.rendre(3));
    }

    @Ignore
    @Test
    public void testAvecTroisPiecesARendrePourUnChequede23() {
        Monnayeur monnayeur = new Monnayeur(asList(1, 4, 15, 20, 50));
        // attention on ne veut pas de [20, 1, 1, 1] car 4 pièces au lieu de 3
        assertEquals(
                asList(15, 4, 4),
                monnayeur.rendre(23));
    }

    @Ignore
    @Test
    public void testAvecTroisPiecesIdentiqueARendrePourUnChequede23() {
        // Attention les pièces ont changé :)
        Monnayeur monnayeur = new Monnayeur(asList(1, 5, 10, 21, 25));

        assertEquals(
                asList(21, 21, 21),
                monnayeur.rendre(63));
    }

    @Ignore
    @Test
    public void testAvecUnGrandNombredePieces() {
        // Attention les pièces ont changé :)
        Monnayeur monnayeur = new Monnayeur(asList(1, 2, 5, 10, 20, 50, 100));

        assertEquals(
                asList(100, 100, 100, 100, 100, 100, 100, 100, 100, 50, 20, 20, 5, 2, 2),
                monnayeur.rendre(999));
    }

    @Ignore
    @Test
    public void testImpossibleDeRendreLaMonnaieToutSimplement() {
        Monnayeur monnayeur = new Monnayeur(asList(5, 10));

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Le montant du chéque de 94 dollars" +
                " ne peut être rendu avec ces pièces.");

        assertEquals(emptyList(),
                monnayeur.rendre(94));
    }

    @Ignore
    @Test
    public void testMontantNegatif() {
        Monnayeur monnayeur = new Monnayeur(asList(1, 2, 5));

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("On ne rend pas la monnaie aux gens qui font crédit");

        assertEquals(emptyList(),
                monnayeur.rendre(-5));
    }

    // HARDCORE car pas de 1
    @Ignore
    @Test
    public void testLeCompteEstBonAvecDeuxPiecesSeulement() {
        Monnayeur monnayeur = new Monnayeur(asList(4, 5));

        assertEquals(
                asList(5, 5, 5, 4, 4, 4),
                monnayeur.rendre(27));
    }

    // HARDCORE car pas de 1
    @Ignore
    @Test
    public void testLeCompteEstBonSansPieceUnitaire() {
        Monnayeur monnayeur = new Monnayeur(asList(2, 5, 10, 20, 50));

        assertEquals(
                asList(10, 5, 2, 2, 2),
                monnayeur.rendre(21));
    }

}
