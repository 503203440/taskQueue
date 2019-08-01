package com.yx.tq.task.component;

import com.yx.tq.task.entity.Item;
import com.yx.tq.task.taskObject.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YX
 * @date 2019-08-01 15:01
 * 启动项目是会执行此类
 */
@Slf4j
@Component
public class TaskQueueComponent implements CommandLineRunner {

    //定义任务队列
    public static Queue<Item> tasks=new ConcurrentLinkedDeque<>();


    @Override
    public void run(String... args) throws Exception {

        //当系统启动以后，开启消费者线程，开始处理任务队列

        //定义线程池
        int cpuTotal = Runtime.getRuntime().availableProcessors();
        int threadNumber=cpuTotal*2;
        log.info("CPU核心数:{}",cpuTotal);

        //一核心两线程
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);

        //构造线程任务，向线程池中加入处理器最大的线程数量
        for (int i = 0; i < threadNumber; i++) {
            SendMail sendMail=new SendMail(tasks);
            executorService.execute(sendMail);
        }

        log.info("线程池启动完毕");
    }
}
