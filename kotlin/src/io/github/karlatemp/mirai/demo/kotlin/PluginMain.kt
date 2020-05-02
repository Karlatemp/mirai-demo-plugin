/*
 * Copyright (c) 2018-2020 Karlatemp. All rights reserved.
 * @author Karlatemp <karlatemp@vip.qq.com> <https://github.com/Karlatemp>
 * @create 2020/05/02 16:35:49
 *
 * mirai-demo-plugin/kotlin/PluginMain.kt
 */

package io.github.karlatemp.mirai.demo.kotlin

import net.mamoe.mirai.console.plugins.PluginBase
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.At

/**
 * 插件入口点, 需要继承PluginBase, 并且在plugin.yml指定
 */
object PluginMain : PluginBase() {
    /**
     * 插件入口点
     */
    override fun onEnable() {
        // 监听群聊信息
        // https://github.com/mamoe/mirai-demos/blob/master/mirai-demo-kotlin/src/main/java/demo/subscribe/SubscribeSamples.kt
        subscribeGroupMessages {
            // 当接收到消息 == "你好" 时就回复 "你好!"
            "你好" reply "你好!"
            has<At> {
                if (it.target == bot.id) {
                    reply("爬爬爬. 别At我")
                }
            }
            "sb" reply "别骂人好吗"
        }
    }
}