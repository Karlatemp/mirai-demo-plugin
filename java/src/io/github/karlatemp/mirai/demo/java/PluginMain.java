/*
 * Copyright (c) 2018-2020 Karlatemp. All rights reserved.
 * @author Karlatemp <karlatemp@vip.qq.com> <https://github.com/Karlatemp>
 * @create 2020/05/02 16:23:34
 *
 * mirai-demo-plugin/java/PluginMain.java
 */

package io.github.karlatemp.mirai.demo.java;

import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.MessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.SingleMessage;

/**
 * 插件入口点, 需要继承PluginBase, 并且在plugin.yml指定
 */
@SuppressWarnings("unused")
public class PluginMain extends PluginBase {
    /**
     * 这是在插件启动的时候调用的挂钩
     */
    @Override
    public void onEnable() {
        // 我们使用GroupMessage监听来着群聊的信息
        getEventListener().subscribeAlways(GroupMessageEvent.class, event -> {
            // 具体的处理可以看
            // https://github.com/mamoe/mirai-demos/blob/master/mirai-demo-java/src/main/java/demo/BlockingTest.java
            {
                String message = event.getMessage().contentToString();
                if (message.equals("你好")) {
                    event.getSubject().sendMessage("你好");
                }
                if (message.startsWith("撤回")) {
                    if (event.getGroup().getBotAsMember().getPermission() != MemberPermission.MEMBER) { // 如果机器人是管理/群主
                        event.getBot().recall(event.getMessage()); // 撤回这条消息
                    }
                }
            }
            {
                long botQQ = event.getBot().getId();
                for (SingleMessage comp : event.getMessage()) {
                    if (comp instanceof At) {
                        if (((At) comp).getTarget() == botQQ) {
                            event.getSubject().sendMessage("爬, 别@我");
                            break; // 退出检测
                        }
                    } else if (comp instanceof Image) {
                        event.getSubject().sendMessage("发图片好玩吗!");
                        break; // 退出检测
                    }
                }
            }
        });

        // 使用ContactMessage监听全部信息
        getEventListener().subscribeAlways(MessageEvent.class, event -> {
        });
    }

    /**
     * 这里是插件关闭的时候调用的挂钩, 你可以在这里写存储插件数据的方法
     */
    @Override
    public void onDisable() {

    }
}



