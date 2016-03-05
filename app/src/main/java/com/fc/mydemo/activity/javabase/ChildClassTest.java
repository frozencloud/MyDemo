package com.fc.mydemo.activity.javabase;

/**
 * 1.接口只能被实现，不能被继承；
 * 2.接口只能继承接口，不能继承一般类、抽象类；
 * 3.抽象类与一般类类似，可以继承抽象类，实现接口；
 * 4.抽象类可以继承一般类；
 */

/**
 * 抽象类示例
 */
abstract class AbstractTest {
    abstract void fun();
}

/**
 * 抽象类可以继承抽象类；可以实现接口
 */
abstract class AbstractTest1 extends AbstractTest implements InterfaceTest {
    abstract void fun();
}

abstract class AbstractTest2 extends ChildClassTest {
}

/**
 * 接口示例
 */
interface InterfaceTest {
    void open();

    void close();
}

/**
 * 接口可以继承接口，接口不可以继承、实现抽象类，不可以实现接口
 */
interface InterfaceTest1 extends InterfaceTest {
}

/**
 * 抽象类、接口类的之类，示意。
 * 抽象类用于继承，而接口用于实现。
 */
public class ChildClassTest extends AbstractTest implements InterfaceTest {
    @Override
    void fun() {
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }
}
