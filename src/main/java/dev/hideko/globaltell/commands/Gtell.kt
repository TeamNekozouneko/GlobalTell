package dev.hideko.globaltell.commands

import dev.hideko.globaltell.GlobalTell
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command
import net.md_5.bungee.api.plugin.TabExecutor

class Gtell: Command("gtell"), TabExecutor {

    override fun execute(sender: CommandSender?, args: Array<out String>?) {
        if(args !== null) {
            if(sender is ProxiedPlayer) {
                GlobalTell.instance.proxy.players.forEach {
                    if(it.name == args[0]) {
                        val chat = ChatColor.translateAlternateColorCodes('&', args[1])
                        val clickAction = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/gtell ${sender.name} ")
                        val textComponent = ComponentBuilder(("${sender.displayName} §7[GlobalTell]§8: §r$chat"))
                            .event(clickAction)
                            .create()
                        it.sendMessage(*textComponent)
                        sender.sendMessage(*textComponent)
                    } else {
                        sender.sendMessage("§c指定したプレイヤー「${args[0]}」は存在しませんでした。")
                    }
                }
            }
        } else {
            sender?.sendMessage("§cプレイヤー名を入力してください。")
        }
    }

    override fun onTabComplete(sender: CommandSender?, args: Array<out String>?): MutableIterable<String> {
        val matches = HashSet<String>()
        if(args?.size == 1) {
            GlobalTell.instance.proxy.players.forEach {
                matches.add(it.name)
            }
        }
        return matches
    }

}