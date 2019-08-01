package com.yx.tq.task.component;

import com.yx.tq.task.entity.Item;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author YX
 * @date 2019-08-01 16:19
 * 内存对象，装在任务队列
 */

public class DataContainer {

    //Item任务队列
    public static Queue<Item> items=new ConcurrentLinkedDeque<Item>();

    //其他任务队列...




}
