package com.yx.tq.task;

import com.yx.tq.task.entity.Item;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * 队列
 */
@Slf4j
public class QueueTest {

    //使用并发连接双端队列
    static volatile Queue<Item> items = new ConcurrentLinkedDeque<>();


    //启动线程
    public static void main(String[] args) {

//        threadQueue1();
        threadQueue2();

    }


    /**
     * 独立线程，非线程池
     */
    public static void threadQueue1(){
        //生产线程
        Thread product = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 999; i++) {
                    System.out.println("正在生产" + (i + 1) + "号");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        log.error("线程休眠失败");
//                    }
                    Item item = new Item();
                    item.setName("NO." + (i + 1));
                    item.setStartTime(new Date());
                    items.add(item);//增加
                }

            }
        });

        //消费线程(当消费者速度不够是可以定义多个消费者，或者使用线程池)
        Thread consumer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"消费中...");

                while (true) {

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("error", e);
                    }


//                  items.peek();//获取但不移除队列的头
                    Item item = items.poll();//获取并移除队列的头
                    if (item != null) {
                        System.out.println(Thread.currentThread().getName()+"本次消费了[" + item.getName() + "]\t生产日期时间：" + new SimpleDateFormat("HH:mm:ss").format(item.getStartTime()));
                    }
                }


            }
        },"consumer1");
        Thread consumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"消费中...");

                while (true) {

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("error", e);
                    }


//                  items.peek();//获取但不移除队列的头
                    Item item = items.poll();//获取并移除队列的头
                    if (item != null) {
                        System.out.println(Thread.currentThread().getName()+"本次消费了[" + item.getName() + "]\t生产日期时间：" + new SimpleDateFormat("HH:mm:ss").format(item.getStartTime()));
                    }
                }


            }
        },"consumer2");

        //启动生产者
        product.start();
        //启动消费者们
        consumer1.start();
        consumer2.start();

        while (true) {

            Thread.State state = product.getState();
//            System.out.println("线程状态：" + product.getState().toString());

            //如果生产者已经执行完了
            if (state == Thread.State.TERMINATED) {

                System.out.println("是否需要再生产一波Item？");
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();

                //用户输入Y
                if ("y".equalsIgnoreCase(next)) {

                    //再次构造线程类
                    product = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            for (int i = 0; i < 9999; i++) {
                                System.out.println("正在生产" + (i + 1) + "号");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        log.error("线程休眠失败");
//                    }
                                Item item = new Item();
                                item.setName("NO." + (i + 1));
                                item.setStartTime(new Date());
                                items.add(item);//增加
                            }

                        }
                    });
                    //启动线程
                    product.start();
                }
            }
        }


    }

    /**
     * 使用线程池
     */
    public static void threadQueue2(){

        //因为单个生产者，此处不使用线程池
        Thread product=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 999; i++) {
                    System.out.println("正在生产" + (i + 1) + "号");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        log.error("线程休眠失败");
//                    }
                    Item item = new Item();
                    item.setName("NO." + (i + 1));
                    item.setStartTime(new Date());
                    items.add(item);//增加
                }
            }
        });
        //启动生产者进行生产
        product.start();

        //构造消费者线程池
        int threadNum = Runtime.getRuntime().availableProcessors() * 2;//获得系统的CPU核心数，1核心有两个线程
        ExecutorService comsumers= Executors.newFixedThreadPool(threadNum);

        //增加消费者线程任务
        Future<?> error1 = comsumers.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "消费中...");

                while (true) {

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("error", e);
                    }


//                  items.peek();//获取但不移除队列的头
                    Item item = items.poll();//获取并移除队列的头
                    if (item != null) {
                        System.out.println(Thread.currentThread().getName() + "本次消费了[" + item.getName() + "]\t生产日期时间：" + new SimpleDateFormat("HH:mm:ss").format(item.getStartTime()));
                    }
                }
            }
        });
        Future<?> error2 = comsumers.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "消费中...");

                while (true) {

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("error", e);
                    }


//                  items.peek();//获取但不移除队列的头
                    Item item = items.poll();//获取并移除队列的头
                    if (item != null) {
                        System.out.println(Thread.currentThread().getName() + "本次消费了[" + item.getName() + "]\t生产日期时间：" + new SimpleDateFormat("HH:mm:ss").format(item.getStartTime()));
                    }
                }
            }
        });

        //捕获线程执行后的异常，注意这里执行error.get会导致主线程阻塞，所以如果需要捕获线程执行中的异常，建议写到主线程的最后
//        try {
//            error1.get();
//            error2.get();
//        } catch (InterruptedException | ExecutionException e) {
//            log.error("error，线程池线程任务执行异常",e);
//        }

        while (true) {

            Thread.State state = product.getState();
            System.out.println("线程状态：" + product.getState().toString());

            //如果生产者已经执行完了
            if (state == Thread.State.TERMINATED) {

                System.out.println("是否需要再生产一波Item？");
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();

                //用户输入Y
                if ("y".equalsIgnoreCase(next)) {

                    //再次构造线程类
                    product = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            for (int i = 0; i < 9999; i++) {
                                System.out.println("正在生产" + (i + 1) + "号");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        log.error("线程休眠失败");
//                    }
                                Item item = new Item();
                                item.setName("NO." + (i + 1));
                                item.setStartTime(new Date());
                                items.add(item);//增加
                            }

                        }
                    });
                    //启动线程
                    product.start();
                }
            }
        }


    }

}
