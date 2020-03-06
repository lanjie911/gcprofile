package cn.bj.brook.algorithm.dynamicplan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TestAreaBeRound {

    private AreaBeRound obj;

    public TestAreaBeRound() {
        this.obj = new AreaBeRound();
    }

    private char[][] matrix;

    @Before
    public void setUp(){
    }

    @After
    public void tearDown() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("--------------------------------------");
    }

    @Test
    public void test1() {
        matrix = new char[4][4];
        matrix[0] = new char[]{'X', 'X', 'X', 'X'};
        matrix[1] = new char[]{'X', 'O', 'O', 'X'};
        matrix[2] = new char[]{'X', 'X', 'O', 'X'};
        matrix[3] = new char[]{'X', 'O', 'X', 'X'};
        obj.solve(matrix);
    }

    @Test
    public void test2() {
        matrix = new char[4][4];
        matrix[0] = new char[]{'X', 'X', 'X', 'X'};
        matrix[1] = new char[]{'X', 'O', 'X', 'X'};
        matrix[2] = new char[]{'X', 'X', 'O', 'X'};
        matrix[3] = new char[]{'X', 'O', 'X', 'X'};
        obj.solve(matrix);
    }

    @Test
    public void test3() {
        matrix = new char[4][5];
        matrix[0] = new char[]{'X', 'X', 'X', 'X', 'O'};
        matrix[1] = new char[]{'X', 'O', 'X', 'O', 'X'};
        matrix[2] = new char[]{'X', 'X', 'X', 'X', 'O'};
        matrix[3] = new char[]{'X', 'O', 'X', 'X', 'O'};
        obj.solve(matrix);
    }

    @Test
    public void test4() {
        matrix = new char[2][2];
        matrix[0] = new char[]{'X', 'X'};
        matrix[1] = new char[]{'X', 'O'};
        obj.solve(matrix);
    }

    @Test
    public void test5() {
        matrix = new char[2][1];
        matrix[0] = new char[]{'O'};
        matrix[1] = new char[]{'X'};
        obj.solve(matrix);
    }

    @Test
    public void test6() {
        matrix = new char[1][2];
        matrix[0] = new char[]{'O', 'X'};
        obj.solve(matrix);
    }

    @Test
    public void test7() {
        matrix = new char[6][6];
        matrix[0] = new char[]{'O', 'O', 'O', 'O', 'X', 'X'};
        matrix[1] = new char[]{'O', 'O', 'O', 'O', 'O', 'O'};
        matrix[2] = new char[]{'O', 'X', 'O', 'X', 'O', 'O'};
        matrix[3] = new char[]{'O', 'X', 'O', 'O', 'X', 'O'};
        matrix[4] = new char[]{'O', 'X', 'O', 'X', 'O', 'O'};
        matrix[5] = new char[]{'O', 'X', 'O', 'O', 'O', 'O'};
        obj.solve(matrix);
    }

    @Test
    public void test8() {
        matrix = new char[10][10];
        matrix[0] = new char[]{'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'O'};
        matrix[1] = new char[]{'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};
        matrix[2] = new char[]{'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X'};
        matrix[3] = new char[]{'X', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O'};
        matrix[4] = new char[]{'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'X'};
        matrix[5] = new char[]{'X', 'X', 'O', 'X', 'X', 'O', 'O', 'X', 'X', 'X'};
        matrix[6] = new char[]{'O', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'};
        matrix[7] = new char[]{'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X'};
        matrix[8] = new char[]{'X', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O'};
        matrix[9] = new char[]{'X', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'};
        obj.solve(matrix);
    }

    @Test
    public void test9() {
        matrix = new char[3][3];
        matrix[0] = new char[]{'X', 'X', 'X'};
        matrix[1] = new char[]{'X', 'O', 'X'};
        matrix[2] = new char[]{'X', 'X', 'X'};
        obj.solve(matrix);
    }

}
