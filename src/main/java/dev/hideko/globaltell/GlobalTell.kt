package dev.hideko.globaltell

import dev.hideko.globaltell.commands.Gtell
import net.md_5.bungee.api.plugin.Plugin

class GlobalTell : Plugin() {

    companion object {
        lateinit var instance: GlobalTell
    }

    override fun onEnable() {
        instance = this
        proxy.pluginManager.registerCommand(this, Gtell())
    }

    override fun onDisable() {
    }

}
