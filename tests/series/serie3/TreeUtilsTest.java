package series.serie3;

import org.junit.Test;
import org.junit.Before;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static series.serie3.TreeUtils.*;
import static series.serie3.MakeTree.*;


public class TreeUtilsTest {

    Comparator<Integer>cmp;

    @Before
    public void init(){
        createTree();
        cmp = (cmp1, cmp2) -> cmp1 - cmp2;
    }
/*testing contains*/
    @Test
    public void test_contains_null() {
        assertFalse(contains(rootNull,2,5,cmp)); }

    @Test
    public void test_contains_exists(){assertTrue(contains(root,1,3,cmp)); }

    @Test
    public void test_contains_notexists(){
        assertFalse(contains(root,60,70,cmp));
    }

    /* testing createBstFromRange */

    @Test
    public void test_createBSTFromRange_null(){
        assertEquals(createBSTFromRange(1,0),null);
    }

    @Test
    public void test_createBSTFromRange_isBST_(){
        TreeNode root = createBSTFromRange(1,7);
        assertTrue(isBST(root,cmp));
    }


    /*testing isBst*/

    @Test
    public void test_isBst_null(){assertFalse(isBST(rootNull,cmp));}

    @Test
    public void test_isBst_true(){assertTrue(isBST(root,cmp));}

    @Test
    public void test_isBst_false(){assertFalse(isBST(rootTest,cmp));}
}
