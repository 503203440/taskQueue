package com.yx.tq.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest implements Runnable {






    @Override
    public void run() {
        System.out.println("哔哔");
    }

    public static void main(String[] args) {

        //建立调度线程池
        ScheduledExecutorService service= Executors.newScheduledThreadPool(10);

        //初次延迟
        long initialDelay=0;

        //周期
        long period=5;

        //启动一个定时任务，每个period后执行，设置的单位为秒
//        service.scheduleAtFixedRate(new ScheduledExecutorTest(),initialDelay,period, TimeUnit.SECONDS);


        //启动一个定时任务，每次任务执行完毕后再间隔指定周期后执行下一次任务，与上一个方法不同的是，这个方法会等待上一次执行完毕再等待指定时间更新下一次
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest(),initialDelay,period,TimeUnit.SECONDS);

    }










}
