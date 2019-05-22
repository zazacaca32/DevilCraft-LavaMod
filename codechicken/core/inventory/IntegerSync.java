package codechicken.core.inventory;

import codechicken.core.inventory.IContainerSyncVar;
import codechicken.core.packet.PacketCustom;

public abstract class IntegerSync implements IContainerSyncVar {

   public int c_value;


   public boolean changed() {
      return this.getValue() != this.c_value;
   }

   public void reset() {
      this.c_value = this.getValue();
   }

   public void writeChange(PacketCustom packet) {
      packet.writeInt(this.getValue());
   }

   public void readChange(PacketCustom packet) {
      this.c_value = packet.readInt();
   }

   public abstract int getValue();
}
