package series.serie3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static series.serie3.TreeUtils.sumIf;

public class TreeUtilsTest {

    @Test
    public void sumIf1(){
        Node root = new Node(60);
        //left tree
        root.left = new Node(50);
        root.left.left = new Node(20);
        root.left.right = new Node(40);
        //right tree
        root.right = new Node(70);
        root.right.left = new Node(65);
        root.right.right = new Node(80);

        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer>=70;
            }
        };
        Assertions.assertEquals((Integer) 150, sumIf(root,predicate));
    }
}
