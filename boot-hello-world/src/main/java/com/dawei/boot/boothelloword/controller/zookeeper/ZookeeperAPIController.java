package com.dawei.boot.boothelloword.controller.zookeeper;

import java.nio.charset.StandardCharsets;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawei.boot.boothelloword.pojo.ResultDto;
import com.dawei.boot.boothelloword.utils.GsonUtil;

import static java.nio.charset.StandardCharsets.*;

/**
 * @author by Dawei on 2019/5/6. Zookeeper Api 尝试
 */
@RestController
@RequestMapping(value = "/zookeeper")
public class ZookeeperAPIController {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperAPIController.class);

    //@Resource
    private CuratorFramework curatorFramework;

    @RequestMapping(value = "/try")
    public ResultDto<String> tryCheckZookeeperApi() {
        ResultDto<String> resultDto = new ResultDto<>();

        //创建节点
        try {
            String forPath = curatorFramework.create()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/abc", new byte[10]);
        } catch (Exception e) {

            logger.error("try check api failed, e = ", e);
        }

        //设置节点数据
        try {
            String nodeDate = "Zookeeper Test Data";
            Stat stat = curatorFramework.setData().forPath("/abc", nodeDate.getBytes());
            logger.info("Set path={} data result={}", "/abc",  GsonUtil.toJson(stat));
        } catch (Exception e) {
            logger.error("Get node data failed, e=", e);
        }
        //获取节点数据
        try {
            byte[] dataBytes = curatorFramework.getData().forPath("/abc");
        } catch (Exception e) {
            logger.error("Get node data failed, e=", e);
        }

        //监听事件
        curatorFramework.getCuratorListenable()
            .addListener(
                (CuratorFramework curatorFramework, CuratorEvent curatorEvent) -> {
                    CuratorEventType eventType = curatorEvent.getType();
                    if (eventType == CuratorEventType.WATCHED) {
                        logger.info("Watching ");
                    }
                    WatchedEvent watchedEvent = curatorEvent.getWatchedEvent();
                    EventType type = watchedEvent.getType();
                    String path = watchedEvent.getPath();
                    KeeperState state = watchedEvent.getState();

                    Stat stat = curatorFramework.checkExists().watched().forPath(path);
                }
            );


        return resultDto;


    }


}
