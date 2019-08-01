package com.yx.tq.task.taskObject;

import com.yx.tq.task.entity.Mail;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Queue;

/**
 * @author YX
 * @date 2019-08-01 15:06
 * 发送邮件的线程类，传入的参数为队列，包含Mail对象，请使用ConcurrentLinkedDeque的实现，以保证线程安全
 */
@Slf4j
public class SendMail implements Runnable {

    private Queue<Mail> mail;
    private String threadName;

    /**
     * @param mail
     */
    public SendMail(Queue<Mail> mail) {
        this.mail = mail;
    }

    @Override
    public void run() {

        while (true){

            try {
                //设置线程休眠，否则会疯狂占用cpu资源
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //从队列获取一个Mail后从中移除
            Mail mail = this.mail.poll();

            if(mail !=null){
                System.out.println("["+Thread.currentThread().getName() + "]本次消费了[" + mail.getName() + "]\t生产日期时间：" + new SimpleDateFormat("HH:mm:ss").format(mail.getSendTime()));
            }


        }

    }
}
