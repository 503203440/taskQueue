package com.yx.tq.task.component;

import com.yx.tq.task.taskObject.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YX
 * @date 2019-08-01 15:01
 * 启动项目时执行
 */
@Slf4j
@Component
public class TaskQueueComponent implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        sendMail();


    }


    //发送消息的方法
    public void sendMail(){
        //当系统启动以后，开启消费者线程，开始处理任务队列

        //定义线程池
        int cpuTotal = Runtime.getRuntime().availableProcessors();
        log.info("CPU核心数:{}",cpuTotal);

        //一核心两线程
//        int threadNumber=cpuTotal*2;
//        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);

        //这里就不创建这么多线程了，避免过度使用cpu资源
        int threadNumber=cpuTotal*2;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);

        //构造线程任务，向线程池中加入处理器最大的线程数量
        for (int i = 0; i < threadNumber; i++) {
            SendMail sendMail=new SendMail(DataContainer.mail);//将数据容器的mails交给sendMail任务类处理
            executorService.execute(sendMail);
        }

        log.info("发送消息线程池启动完毕");

    }

    //修改状态的方法
    public void changStatus(){




    }







}
