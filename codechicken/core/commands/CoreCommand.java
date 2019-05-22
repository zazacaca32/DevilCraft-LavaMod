package codechicken.core.commands;

import codechicken.core.ServerUtils;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public abstract class CoreCommand implements ICommand {

   public abstract boolean OPOnly();

   public String getCommandUsage(ICommandSender var1) {
      return "/" + this.getCommandName() + " help";
   }

   public void processCommand(ICommandSender listener, String[] args) {
      if(args.length >= this.minimumParameters() && (args.length != 1 || !args[0].equals("help"))) {
         String command = this.getCommandName();
         String[] var7 = args;
         int var6 = args.length;

         for(int var5 = 0; var5 < var6; ++var5) {
            String arg = var7[var5];
            command = command + " " + arg;
         }

         this.handleCommand(command, listener.getCommandSenderName(), args, listener);
      } else {
         this.printHelp(listener);
      }
   }

   public abstract void handleCommand(String var1, String var2, String[] var3, ICommandSender var4);

   public abstract void printHelp(ICommandSender var1);

   public final EntityPlayerMP getPlayer(String name) {
      return ServerUtils.getPlayer(name);
   }

   public WorldServer getWorld(int dimension) {
      return DimensionManager.getWorld(dimension);
   }

   public WorldServer getWorld(EntityPlayer player) {
      return (WorldServer)player.worldObj;
   }

   public Integer parseInteger(String parse) {
      try {
         return Integer.valueOf(Integer.parseInt(parse));
      } catch (NumberFormatException var3) {
         return null;
      }
   }

   public int compareTo(Object arg0) {
      return this.getCommandName().compareTo(((ICommand)arg0).getCommandName());
   }

   public List getCommandAliases() {
      return null;
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return null;
   }

   public boolean isUsernameIndex(String[] astring, int i) {
      return false;
   }

   public boolean canCommandSenderUseCommand(ICommandSender var1) {
      return this.OPOnly()?(var1 instanceof EntityPlayer?ServerUtils.isPlayerOP(var1.getCommandSenderName()):var1 instanceof MinecraftServer):true;
   }

   public abstract int minimumParameters();
}
