package com.yx.tq.task.component;

import com.yx.tq.task.entity.Mail;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author YX
 * @date 2019-08-01 16:19
 * 内存对象，装在任务队列
 */

public class DataContainer {

    //Mail任务队列
    public static Queue<Mail> mail =new ConcurrentLinkedDeque<>();

    //其他任务队列...




}
