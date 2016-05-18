package com.zbao;

/**
 * Created by zhangYB on 2016/5/12.
 */
public class MainDemo {

    /***
     * 动态代理演示代码
     *
     * @param args
     */
//    public static void main(String[] args) {
//        Double number = 100.01;
//        BigDecimal bd = new BigDecimal(number);
//        BigDecimal setScale = bd.setScale(2, bd.ROUND_CEILING);
//        System.out.println(setScale+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

       /* ProxyDemo proxyDemo = (ProxyDemo) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class<?>[]{ProxyDemo.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String methodName = method.getName();
                Integer a = (Integer) args[0];
                Integer b = (Integer) args[1];
                Annotation[] annotations = method.getAnnotations();
                for (int i = 0; i < annotations.length; i++) {
                    System.out.println("annotations is " + annotations[i]);
                }
                System.out.println("first paramster is " + a);
                System.out.println("second paramster is " + b);
                System.out.println("method is " + methodName);
                return null;
            }
        });*/
//    }
}
