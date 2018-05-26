package series.serie3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static series.serie3.TreeUtils.getByDecreasingOrder;
import static series.serie3.TreeUtils.sumIf;

public class TreeUtilsTest {
    private static BST<Integer> bst = new BST<>();

    @Test
    public void sumIf1(){
        treeInsert(bst, 60);
        treeInsert(bst, 50);
        treeInsert(bst, 70);
        treeInsert(bst, 20);
        treeInsert(bst, 55);
        treeInsert(bst, 65);
        treeInsert(bst, 80);

        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer>=70;
            }
        };
        Assertions.assertEquals((Integer) 150, sumIf(bst.root,predicate));
    }
    @Test
    public void  getByDecreasingOrder1(){
        treeInsert(bst, 60);
        treeInsert(bst, 50);
        treeInsert(bst, 70);
        treeInsert(bst, 20);
        treeInsert(bst, 55);
        treeInsert(bst, 65);
        treeInsert(bst, 80);

        ArrayList<Integer> res = new ArrayList<Integer>(Arrays.asList(80,70,65,60,55,50,20));

        Assertions.assertIterableEquals(res, getByDecreasingOrder(bst.root));
    }

    private void treeInsert(BST<Integer> bst, int z) {
        Node<Integer> curr = new Node<>(z);
        Node<Integer> y = null;
        Node<Integer> x = bst.root;
        while (x!=null){
            y=x;
            if(curr.value<x.value)
                x=x.left;
            else
                x=x.right;
        }
        curr.parent=y;
        if(y==null)
            bst.root=curr;
        else if(curr.value<y.value)
                y.left=curr;
        else
            y.right = curr;
    }
}
