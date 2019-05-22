package codechicken.core.inventory;

import codechicken.core.packet.PacketCustom;

public interface IContainerSyncVar {

   boolean changed();

   void reset();

   void writeChange(PacketCustom var1);

   void readChange(PacketCustom var1);
}
