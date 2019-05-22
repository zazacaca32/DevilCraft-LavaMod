package codechicken.core.inventory;

import codechicken.core.inventory.ContainerExtended;
import codechicken.core.inventory.IContainerSyncVar;
import codechicken.core.packet.PacketCustom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public abstract class ContainerSynchronised extends ContainerExtended {

   private ArrayList syncVars = new ArrayList();


   public abstract PacketCustom createSyncPacket();

   public final void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < this.syncVars.size(); ++i) {
         IContainerSyncVar var = (IContainerSyncVar)this.syncVars.get(i);
         if(var.changed()) {
            PacketCustom packet = this.createSyncPacket();
            packet.writeByte(i);
            var.writeChange(packet);
            this.sendContainerPacket(packet);
            var.reset();
         }
      }

   }

   public void sendContainerAndContentsToPlayer(Container container, List list, List playerCrafters) {
      super.sendContainerAndContentsToPlayer(container, list, playerCrafters);

      for(int i = 0; i < this.syncVars.size(); ++i) {
         IContainerSyncVar var = (IContainerSyncVar)this.syncVars.get(i);
         PacketCustom packet = this.createSyncPacket();
         packet.writeByte(i);
         var.writeChange(packet);
         Iterator var8 = playerCrafters.iterator();

         while(var8.hasNext()) {
            EntityPlayerMP player = (EntityPlayerMP)var8.next();
            packet.sendToPlayer(player);
         }
      }

   }

   public void addSyncVar(IContainerSyncVar var) {
      this.syncVars.add(var);
   }

   public final void handleOutputPacket(PacketCustom packet) {
      ((IContainerSyncVar)this.syncVars.get(packet.readUnsignedByte())).readChange(packet);
   }

   public List getSyncedVars() {
      return Collections.unmodifiableList(this.syncVars);
   }
}
