package codechicken.nei;

import codechicken.core.ServerUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PlayerSave {

   public ItemStack[] creativeInv;
   private File saveFile;
   private NBTTagCompound saveCompound;
   public String username;
   private boolean isDirty;
   private boolean creativeInvDirty;
   public boolean wasOp;


   public PlayerSave(String playername, File saveLocation) {
      this.username = playername;
      this.wasOp = ServerUtils.isPlayerOP(playername);
      this.saveFile = new File(saveLocation, this.username + ".dat");
      if(!this.saveFile.getParentFile().exists()) {
         this.saveFile.getParentFile().mkdirs();
      }

      this.load();
   }

   private void load() {
      this.saveCompound = new NBTTagCompound();

      try {
         if(!this.saveFile.exists()) {
            this.saveFile.createNewFile();
         }

         if(this.saveFile.length() > 0L) {
            DataInputStream e = new DataInputStream(new FileInputStream(this.saveFile));
            this.saveCompound = (NBTTagCompound)NBTBase.readNamedTag(e);
            e.close();
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.loadCreativeInv();
   }

   private void loadCreativeInv() {
      this.creativeInv = new ItemStack[54];
      NBTTagList itemList = this.saveCompound.getTagList("creativeitems");
      if(itemList != null) {
         for(int tagPos = 0; tagPos < itemList.tagCount(); ++tagPos) {
            NBTTagCompound stacksave = (NBTTagCompound)itemList.tagAt(tagPos);
            this.creativeInv[stacksave.getByte("Slot") & 255] = ItemStack.loadItemStackFromNBT(stacksave);
         }
      }

   }

   public boolean getMagnetMode() {
      return this.saveCompound.getBoolean("magnetmode");
   }

   public boolean getCreativeInv() {
      return this.saveCompound.getBoolean("creativeinv");
   }

   public void setMagnetMode(boolean b) {
      this.saveCompound.setBoolean("magnetmode", b);
      this.setDirty();
   }

   public void setCreativeInv(boolean b) {
      this.saveCompound.setBoolean("creativeinv", b);
      this.setDirty();
   }

   public void save() {
      if(this.isDirty) {
         if(this.creativeInvDirty) {
            this.saveCreativeInv();
         }

         try {
            DataOutputStream e = new DataOutputStream(new FileOutputStream(this.saveFile));
            NBTBase.writeNamedTag(this.saveCompound, e);
            e.close();
            this.isDirty = false;
         } catch (Exception var2) {
            var2.printStackTrace();
         }

      }
   }

   private void saveCreativeInv() {
      NBTTagList invsave = new NBTTagList();

      for(int i = 0; i < this.creativeInv.length; ++i) {
         if(this.creativeInv[i] != null) {
            NBTTagCompound stacksave = new NBTTagCompound();
            stacksave.setByte("Slot", (byte)i);
            this.creativeInv[i].writeToNBT(stacksave);
            invsave.appendTag(stacksave);
         }
      }

      this.saveCompound.setTag("creativeitems", invsave);
      this.creativeInvDirty = false;
   }

   public void setCreativeDirty() {
      this.creativeInvDirty = this.isDirty = true;
   }

   public void setDirty() {
      this.isDirty = true;
   }
}
