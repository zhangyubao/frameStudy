package com.zbao.news;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 可以直接在机器上执行的代码(即可以直接在开发工具或者开发环境下运行的代码)
 * <p/>
 * <p/>
 * Mockito使用 进行无返回值的单元测试
 * http://chriszou.com/2016/04/29/android-unit-testing-mockito.html
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private TestDemo mTestDemo;

    @Before
    public void gerentor() {  //在执行所有的测试方法之前会执行此注解的方法
        mTestDemo = new TestDemo();
    }


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    /***
     * testAdd()  testMutiply() 两个方法用来演示@Before注解的使用
     */
    @Test
    public void testAdd() {
        int result = mTestDemo.add(1, 1);
        //期望值  实际结果值
        assertEquals(2, result);
    }

    @Test
    public void testMutiply() {
        int result = mTestDemo.mutiply(1, 2);
        assertEquals(3, result);
    }

    /**
     * 用来演示期望抛出异常的测试
     */
    @Test(expected = ArithmeticException.class)
    public void testDvider() {
        int result = mTestDemo.divider(5, 0);
        assertEquals(2, result);
    }

    @Test
    @Ignore("method is not implemented yet")
    public void testFunction() {

    }
}