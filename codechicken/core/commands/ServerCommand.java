package codechicken.core.commands;

import codechicken.core.commands.CoreCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public abstract class ServerCommand extends CoreCommand {

   public void processCommand(ICommandSender var1, String[] var2) {
      this.handleCommand(var2, (MinecraftServer)var1);
   }

   public boolean canCommandSenderUseCommand(ICommandSender var1) {
      return !super.canCommandSenderUseCommand(var1)?false:var1 instanceof MinecraftServer;
   }

   public abstract void handleCommand(String[] var1, MinecraftServer var2);

   public final boolean OPOnly() {
      return false;
   }
}
