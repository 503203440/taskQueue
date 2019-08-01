package com.yx.tq.task;

import com.yx.tq.task.component.DataContainer;
import com.yx.tq.task.component.TaskQueueComponent;
import com.yx.tq.task.entity.Item;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author YX
 * @date 2019-08-01 15:33
 */
@RestController
@RequestMapping("/queue")
public class AddTaskQueueController {


    /**
     * 访问此接口将会向组件类中的任务队列写入9999个待处理的任务，处理任务的线程池会处理此任务
     * @return
     */
    @RequestMapping("/addTask")
    public Object addTask(){

        for (int i = 0; i < 9999; i++) {

            Item item=new Item();
            item.setName("NO."+(i+1));
            item.setStartTime(new Date());

            //如果不包含则加入到items
            if(!DataContainer.items.contains(item)){
                DataContainer.items.add(item);
            }

        }
        return "000";
    }




}
