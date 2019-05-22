package codechicken.core;

import net.minecraft.entity.player.EntityPlayerMP;

public interface IGuiPacketSender {

   void sendPacket(EntityPlayerMP var1, int var2);
}
