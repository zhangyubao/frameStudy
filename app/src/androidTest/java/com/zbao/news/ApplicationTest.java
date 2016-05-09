package com.zbao.news;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * 只能在设备上执行的测试代码 （只能将项目发布到手机或者模拟器上才能运行的代码）
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

}