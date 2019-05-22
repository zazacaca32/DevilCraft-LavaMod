package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.entity.gui.GuiMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.IMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipeList;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAUpdateMerchant extends PacketMA {

   private MerchantRecipeList merchantrecipelist;
   private int wid;


   public PacketMAUpdateMerchant(int windowid, MerchantRecipeList merchantrecipelist) {
      this.merchantrecipelist = merchantrecipelist;
      this.wid = windowid;
   }

   public PacketMAUpdateMerchant() {}

   protected void write(ByteArrayDataOutput out) {
      try {
         out.writeInt(this.wid);
         this.merchantrecipelist.writeRecipiesToStream(out);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      try {
         this.wid = in.readInt();
         this.merchantrecipelist = MerchantRecipeList.readRecipiesFromStream(in);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      FMLLog.getLogger().info(this.getClass().getSimpleName() + " 9");
      if(side.isServer()) {
         throw new PacketMA.ProtocolException("Cannot send this packet to the server!");
      } else {
         try {
            GuiScreen guiscreen;
            if(this.merchantrecipelist != null && (guiscreen = Minecraft.getMinecraft().currentScreen) != null && guiscreen instanceof GuiMerchant && this.wid == Minecraft.getMinecraft().thePlayer.openContainer.windowId) {
               IMerchant imerchant = ((GuiMerchant)guiscreen).getIMerchant();
               MerchantRecipeList merchantrecipelist = this.merchantrecipelist;
               imerchant.setRecipes(merchantrecipelist);
            }
         } catch (Exception var6) {
            ;
         }

      }
   }

   protected void length(int length) {}
}
