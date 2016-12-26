package series.serie3;

import org.junit.Test;

import static org.junit.Assert.*;

public class DNATests {

    DNACollection dna;
    @Test
    public void addSingleFragmentTest(){
        dna = new DNACollection(4);
        dna.add("A");
        assertEquals('A',dna.collection[0].getId());
    }
    @Test
    public void addCompleteFragmentTest(){
        dna = new DNACollection(4);
        dna.add("AC");
        assertEquals('A',dna.collection[0].getId());
        assertEquals('C',dna.collection[0].child[1].getId());
    }

    @Test
    public void countTest(){
        dna = new DNACollection(4);
        dna.add("AC");
        dna.add("ACG");
        dna.add("ACT");
        dna.add("ACA");
        dna.add("AAG");
        dna.add("AGA");
        dna.add("AT");
        assertEquals(6,dna.prefixCount("A"));
    }
    @Test
    public void countNoElementTest(){
        dna = new DNACollection(4);
        dna.add("AC");
        assertEquals(0,dna.prefixCount("T"));

    }

}
