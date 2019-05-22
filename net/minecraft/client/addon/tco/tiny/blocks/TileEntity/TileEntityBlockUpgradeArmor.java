package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.CFlag;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntityBlockUpgradeArmor extends TileEntityBlockBase {

   public int tickPowerTime = 0;
   private int tick;
   public int speedreamaningTime = 1000;
   public boolean isWorking = false;
   int startItemID;
   int startItemID2;
   public byte thisRenderModel = 0;
   private double[] randomic = new double[]{40.0D, 60.0D};
   public int chance = 100;


   public TileEntityBlockUpgradeArmor() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[2];
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      this.tickPowerTime = par1NBTTagCompound.getInteger("tickPowerTime");
      this.speedreamaningTime = par1NBTTagCompound.getInteger("srt");
      this.isWorking = par1NBTTagCompound.getBoolean("iw");
      this.startItemID = par1NBTTagCompound.getInteger("sii");
      this.thisRenderModel = par1NBTTagCompound.getByte("rm");
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setInteger("tickPowerTime", this.tickPowerTime);
      par1NBTTagCompound.setInteger("srt", this.speedreamaningTime);
      par1NBTTagCompound.setBoolean("iw", this.isWorking);
      par1NBTTagCompound.setInteger("sii", this.startItemID);
      par1NBTTagCompound.setByte("rm", this.thisRenderModel);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   @SideOnly(Side.CLIENT)
   public int getPowerScaled(int i) {
      if(this.tickPowerTime <= 0) {
         return 0;
      } else {
         int r = this.tickPowerTime * i / this.speedreamaningTime;
         if(r > i) {
            r = i;
         }

         return r;
      }
   }

   @SideOnly(Side.CLIENT)
   public boolean isReamaning() {
      return this.tickPowerTime > 0;
   }

   public int getrandom() {
      int sump = 0;

      for(int var6 = 0; var6 < this.randomic.length; ++var6) {
         sump = (int)((double)sump + this.randomic[var6]);
      }

      double var61 = Math.random() * (double)sump;
      int num = this.randomic.length;

      for(int i = 0; i < this.randomic.length; ++i) {
         if(var61 <= this.randomic[i]) {
            num = i;
            break;
         }

         var61 -= this.randomic[i];
      }

      return num;
   }

   public void setCD(ItemStack armor, int lvls) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         nbtData.setByte("cd", (byte)lvls);
      }

   }

   public Integer getCDCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("cd"));
      }
   }

   public void setUpgradeCharge(ItemStack armor, int upgrade) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(upgrade > -1) {
            int charge = nbtData.getInteger("amp");
            nbtData.setInteger("amp", charge + upgrade);
         } else {
            nbtData.setInteger("amp", 0);
         }
      }

   }
   
   public void setWither(ItemStack armor, int upgrade) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         if(upgrade > -1) {
	            int charge = nbtData.getInteger("wither");
	            nbtData.setInteger("wither", charge + upgrade);
	         } else {
	            nbtData.setInteger("wither", 0);
	         }
	      }

	   }
	   

   public void setShitCharge(ItemStack armor, int upgrade) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(upgrade > -1) {
            int charge = nbtData.getInteger("shi");
            nbtData.setInteger("shi", charge + upgrade);
         } else {
            nbtData.setInteger("shi", 0);
         }
      }

   }

   public void setYc(ItemStack armor, int upgrade) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(upgrade > -1) {
            int charge = nbtData.getInteger("yc");
            nbtData.setInteger("yc", charge + upgrade);
         } else {
            nbtData.setInteger("yc", 0);
         }
      }

   }

   public void setColor(ItemStack armor, int lvl) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("colorsa", lvl);
	      }

	   }

	   public Integer getColor(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("colorsa"));
	      }
	   }

	   public void setNC(ItemStack armor, int lvl) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("colorsa", lvl);
	      }

	   }

	   public Integer getNC(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("colorsa"));
	      }
	   }
   
		   
   public int getYc(ItemStack armor) {
      if(armor.stackSize <= 2) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return nbtData.getInteger("yc");
      } else {
         return 11;
      }
   }
   
   public int getYcRandom() {
	      int sump = 0;

	      for(int var6 = 0; var6 < this.randomic.length; ++var6) {
	         sump = (int)((double)sump + this.randomic[var6]);
	      }

	      double var61 = Math.random() * (double)sump;
	      int num = this.randomic.length;

	      for(int i = 0; i < this.randomic.length; ++i) {
	         if(var61 <= this.randomic[i]) {
	            num = i;
	            break;
	         }

	         var61 -= this.randomic[i];
	      }

	      return num;
	   }

   public Integer getExoCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("exo"));
      }
   }

   public void setExo(ItemStack armor, int lvls) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(lvls > 0) {
            nbtData.setByte("exo", (byte)1);
         }
      }

   }
   
	   

   public Integer getLPM(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getInteger("lpm"));
      }
   }

   public void setLPM(ItemStack armor, int lvls) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(lvls > 0) {
            nbtData.setInteger("lpm", lvls);
         }
      }

   }

   public Integer getLPTM(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getInteger("lptm"));
      }
   }

   public void setLPTM(ItemStack armor, int lvls) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(lvls > 0) {
            nbtData.setInteger("lptm", lvls);
         }
      }

   }

   public Byte getKDI(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Byte.valueOf(nbtData.getByte("kdi"));
      }
   }

   public void setKDI(ItemStack armor, int lvlsz) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         nbtData.setByte("kdi", (byte)1);
      }

   }

   public Byte getHebCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Byte.valueOf(nbtData.getByte("hell"));
      }
   }

   public void setHeb(ItemStack armor, int lvlsz) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(lvlsz > 0) {
            nbtData.setByte("hell", (byte)1);
         }
      }

   }

   public Integer getObl(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getInteger("Oblick"));
      }
   }

   public void setObl(ItemStack armor) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         nbtData.setInteger("Oblick", 1);
      }

   }

   public void setDarkShieldlvl(ItemStack armor, int lvl) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(lvl > 0) {
            nbtData.setByte("dss", (byte)lvl);
         }
      }

   }

   public Boolean getKorona(ItemStack armor)
   {
     if (armor.stackSize > 1) {
       return null;
     }
     NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
     return Boolean.valueOf(nbtData.getBoolean("itKorona"));
   }
   
   public void setKorona(ItemStack armor)
   {
     if (armor.stackSize <= 1)
     {
       NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
       nbtData.setBoolean("itKorona", true);
     }
   }
   
   public Integer getDarkShieldCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("dss"));
      }
   }

   public void addLetal(ItemStack armor, byte upgrade) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         if(upgrade != -1) {
            byte charge = nbtData.getByte("cd");
            nbtData.setByte("cd", (byte)(charge + upgrade));
         } else {
            nbtData.setByte("cd", (byte)0);
         }
      }

   }
   
   
   public Integer getLIT11(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$12"));
	      }
	   }

	   public void setLIT11(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$12", nbtData.getInteger("lavalitres$12") + 1);
	      }

	   }
   
   public Integer getLIT10(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$11"));
	      }
	   }

	   public void setLIT10(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$11", nbtData.getInteger("lavalitres$11") + 1);
	      }

	   }
   
   public Integer getLIT9(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$10"));
	      }
	   }

	   public void setLIT9(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$10", nbtData.getInteger("lavalitres$10") + 1);
	      }

	   }
   
   public Integer getLIT8(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$9"));
	      }
	   }

	   public void setLIT8(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$9", nbtData.getInteger("lavalitres$9") + 1);
	      }

	   }
   
   public Integer getLIT7(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$8"));
	      }
	   }

	   public void setLIT7(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$8", nbtData.getInteger("lavalitres$8") + 1);
	      }

	   }
   
   public Integer getLIT6(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$7"));
	      }
	   }

	   public void setLIT6(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$7", nbtData.getInteger("lavalitres$7") + 1);
	      }

	   }
   
   public Integer getLIT5(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$6"));
	      }
	   }

	   public void setLIT5(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$6", nbtData.getInteger("lavalitres$6") + 1);
	      }

	   }
   
   public Integer getLIT4(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$5"));
	      }
	   }

	   public void setLIT4(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$5", nbtData.getInteger("lavalitres$5") + 1);
	      }

	   }
   
   
   
   public Integer getLIT3(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$4"));
	      }
	   }

	   public void setLIT3(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$4", nbtData.getInteger("lavalitres$4") + 1);
	      }

	   }
	   
   public Integer getLIT2(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$3"));
	      }
	   }

	   public void setLIT2(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$3", nbtData.getInteger("lavalitres$3") + 1);
	      }

	   }
	   
   public Integer getLIT1(ItemStack armor) {
	      if(armor.stackSize > 1) {
	         return null;
	      } else {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         return Integer.valueOf(nbtData.getInteger("lavalitres$2"));
	      }
	   }

	   public void setLIT1(ItemStack armor) {
	      if(armor.stackSize <= 1) {
	         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
	         nbtData.setInteger("lavalitres$2", nbtData.getInteger("lavalitres$2") + 1);
	      }

	   }
	   
   public Integer getLIT(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getInteger("lavalitres$1"));
      }
   }

   public void setLIT(ItemStack armor) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         nbtData.setInteger("lavalitres$1", nbtData.getInteger("lavalitres$1") + 1);
      }

   }
   
   public void onUpdate(ItemStack i, World w, Entity e, int in, boolean b) {
   	NBTTagCompound a = new NBTTagCompound();
   	if (!i.hasTagCompound()) {
   		i.setTagCompound(a);
   	}   	
   }

   public void updateEntity() {
       if (CFlag.isSERVER) {
       super.updateEntity();
       boolean flag = false;
       if (++this.tick >= 20 && !super.worldObj.isRemote) {
           this.tick = 0;

           try {
               int var6;
               Integer var8;
               if (super.ItemStacks[0] != null && super.ItemStacks[1] != null) {
                   int var7 = super.ItemStacks[1].itemID;
                   var6 = ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getMetaArm(super.ItemStacks[1]);
                   if (!this.isWorking) {
                       var8 = (Integer) ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
                       if (this.getExoCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 201) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 31) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 21) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 22) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 23) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 24) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 25) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 26) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 27) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 28) {
                           this.isWorking = false;
                       } else if (this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 29) {
                           this.isWorking = false;
                       } else if ((this.getHebCharge(super.ItemStacks[1]).byteValue() <= 0 || var8.intValue() != 205) && (var6 > 400 && var6 < 450 || var8.intValue() != 205)) {
                           if ((this.getKDI(super.ItemStacks[1]).byteValue() <= 0 || var8.intValue() != 9999) && (var6 > 100 && var6 < 150 || var8.intValue() != 9999)) {
                               if ((this.getLIT(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2223) && (var6 == 209 || var8.intValue() != 2223)) {
                                   if ((this.getLIT1(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2222) && (var6 == 111 || var8.intValue() != 2222)) {
                                       if ((this.getLIT2(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2224) && (var6 == 309 || var8.intValue() != 2224)) {
                                           if ((this.getLIT3(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2225) && (var6 == 409 || var8.intValue() != 2225)) {
                                               if ((this.getLIT4(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2226) && (var6 == 105 || var8.intValue() != 2226)) {
                                                   if ((this.getLIT5(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2227) && (var6 == 205 || var8.intValue() != 2227)) {
                                                       if ((this.getLIT6(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2228) && (var6 == 305 || var8.intValue() != 2228)) {
                                                           if ((this.getLIT7(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2229) && (var6 == 405 || var8.intValue() != 2229)) {
                                                               if ((this.getLIT8(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2230) && (var6 == 101 || var8.intValue() != 2230)) {
                                                                   if ((this.getLIT9(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2231) && (var6 == 201 || var8.intValue() != 2231)) {
                                                                       if ((this.getLIT10(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2232) && (var6 == 301 || var8.intValue() != 2232)) {
                                                                           if ((this.getLIT11(super.ItemStacks[1]).intValue() <= 8 || var8.intValue() != 2233) && (var6 == 401 || var8.intValue() != 2233)) {
                                                                               if ((this.getObl(super.ItemStacks[1]).intValue() <= 0 || var8.intValue() != 600) && (var6 > 200 && var6 < 250 || var8.intValue() != 600)) {
                                                                                   if (this.getLPM(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 1000) {
                                                                                       this.isWorking = false;
                                                                                   } else if (this.getLPTM(super.ItemStacks[1]).intValue() > 0 && var8.intValue() == 1001) {
                                                                                       this.isWorking = false;
                                                                                   } else if (this.getYc(super.ItemStacks[1]) >= 10 && var8.intValue() == 250) {
                                                                                       this.isWorking = false;
                                                                                   } else if (((getKorona(this.ItemStacks[1]).booleanValue()) && (var8.intValue() == 7778)) || ((var6 > 150) && (var8.intValue() == 7778))) {
                                                                                       this.isWorking = false;
                                                                                   } else if (this.getCDCharge(super.ItemStacks[1]).intValue() >= 10 && var8.intValue() == 777) {
                                                                                       this.isWorking = false;
                                                                                   } else if (this.getColor(super.ItemStacks[1]).intValue() > 1 && var8.intValue() > -1 && var8.intValue() < 16) {
                                                                                       this.isWorking = false;
                                                                                   } else if (this.getColor(super.ItemStacks[1]) != null && this.getColor(super.ItemStacks[1]).intValue() == 15 && var8.intValue() == 1220) {
                                                                                       this.isWorking = false;
                                                                                   } else {
                                                                                       this.startItemID = super.ItemStacks[1].itemID;
                                                                                       this.startItemID2 = super.ItemStacks[0].itemID;
                                                                                       int time = ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getTimeBurnID(super.ItemStacks[1]);
                                                                                       if (time > -1) {
                                                                                           this.speedreamaningTime = time;
                                                                                       }

                                                                                       this.thisRenderModel = ((Integer) ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getSlotAsDataItemID(super.ItemStacks[1])[1]).byteValue();
                                                                                       this.isWorking = true;
                                                                                       this.tickPowerTime = 0;
                                                                                       flag = true;
                                                                                   }
                                                                               } else {
                                                                                   this.isWorking = false;
                                                                               }
                                                                           } else {
                                                                               this.isWorking = false;
                                                                           }
                                                                       } else {
                                                                           this.isWorking = false;
                                                                       }
                                                                   } else {
                                                                       this.isWorking = false;
                                                                   }
                                                               } else {
                                                                   this.isWorking = false;
                                                               }
                                                           } else {
                                                               this.isWorking = false;
                                                           }
                                                       } else {
                                                           this.isWorking = false;
                                                       }
                                                   } else {
                                                       this.isWorking = false;
                                                   }
                                               } else {
                                                   this.isWorking = false;
                                               }
                                           } else {
                                               this.isWorking = false;
                                           }
                                       } else {
                                           this.isWorking = false;
                                       }
                                   } else {
                                       this.isWorking = false;
                                   }
                               } else {
                                   this.isWorking = false;
                               }
                           } else {
                               this.isWorking = false;
                           }
                       } else {
                           this.isWorking = false;
                       }
                   }

                   if (this.startItemID == super.ItemStacks[1].itemID && this.startItemID2 == super.ItemStacks[0].itemID) {
                       if (this.isWorking) {
                           ++this.tickPowerTime;
                           var8 = (Integer) ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];
                           if (var8.intValue() != 0 && var8.intValue() != 250 && var8.intValue() != 777) {
                               if (var8.intValue() < 101 && var8.intValue() > 99 && var8.intValue() == 100) {
                                   this.chance = 20;
                               } else if (var8.intValue() > -1 && var8.intValue() < 16) {
                                   this.chance = 100;
                               } else if (var8.intValue() == 888) {
                                   this.chance = 60;
                               } else if (var8.intValue() >= 2223 && var8.intValue() <= 2300) {
                                   this.chance = 100;
                               } else if (var8.intValue() == 7778) {
                                   this.chance = 100;
                               } else if (var8.intValue() == 201) {
                                   this.chance = 100;
                               } else {
                                   this.chance = 60;
                               }
                           } else {
                               this.chance = 60;
                           }
                       }
                   } else {
                       this.isWorking = false;
                       this.tickPowerTime = 0;
                       flag = true;
                   }
               } else {
                   if (this.isWorking) {
                       this.isWorking = false;
                       flag = true;
                   }

                   if (this.tickPowerTime > 0) {
                       this.tickPowerTime = 0;
                   }
               }

               if (this.tickPowerTime >= this.speedreamaningTime && super.ItemStacks[0] != null && super.ItemStacks[1] != null) {
                   this.tickPowerTime = 0;
                   boolean var71 = true;
                   var8 = (Integer) ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItem(super.ItemStacks[0])[0];

                   if (var8.intValue() == 201 && this.getExoCharge(super.ItemStacks[1]) != null && this.getExoCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 31 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 21 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 22 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 23 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 24 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 25 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 26 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 27 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 28 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }
                   if (var8.intValue() == 29 && this.getDarkShieldCharge(super.ItemStacks[1]) != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }

                   if (var8.intValue() == 1220 && this.getColor(super.ItemStacks[1]) != null && this.getColor(super.ItemStacks[1]).intValue() == 15) {
                       var71 = false;
                   }
                   if (var8.intValue() > -1 && var8.intValue() < 16 && this.getColor(super.ItemStacks[1]) != null && this.getColor(super.ItemStacks[1]).intValue() > 1) {
                       var71 = false;
                   }

                   if (var8.intValue() == 20 && this.getNC(super.ItemStacks[1]).intValue() > 1) {
                       var71 = true;
                   }

                   if (var8.intValue() == 20 && this.getNC(super.ItemStacks[1]).intValue() < 1) {
                       var71 = false;
                   }


                   if (var8.intValue() == 2223 && this.getLIT(super.ItemStacks[1]) != null && this.getLIT(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2222 && this.getLIT1(super.ItemStacks[1]) != null && this.getLIT1(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2224 && this.getLIT2(super.ItemStacks[1]) != null && this.getLIT2(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2225 && this.getLIT3(super.ItemStacks[1]) != null && this.getLIT3(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }


                   if (var8.intValue() == 2226 && this.getLIT4(super.ItemStacks[1]) != null && this.getLIT4(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2227 && this.getLIT5(super.ItemStacks[1]) != null && this.getLIT5(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2228 && this.getLIT6(super.ItemStacks[1]) != null && this.getLIT6(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2229 && this.getLIT7(super.ItemStacks[1]) != null && this.getLIT7(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }

                   if (var8.intValue() == 2230 && this.getLIT8(super.ItemStacks[1]) != null && this.getLIT8(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2231 && this.getLIT9(super.ItemStacks[1]) != null && this.getLIT9(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2232 && this.getLIT10(super.ItemStacks[1]) != null && this.getLIT10(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }
                   if (var8.intValue() == 2233 && this.getLIT11(super.ItemStacks[1]) != null && this.getLIT11(super.ItemStacks[1]).intValue() >= 9) {
                       var71 = false;
                   }


                   if (var8.intValue() == 205 && this.getHebCharge(super.ItemStacks[1]) != null && this.getHebCharge(super.ItemStacks[1]).byteValue() > 0) {
                       var71 = false;
                   }

                   if (var8.intValue() == 777 && this.getCDCharge(super.ItemStacks[1]) != null && this.getCDCharge(super.ItemStacks[1]).intValue() < 0) {
                       this.isWorking = false;
                       var71 = false;
                   }

                   if ((var8.intValue() == 7778) && (getKorona(this.ItemStacks[1]) != null) && (getKorona(this.ItemStacks[1]).booleanValue())) {
                       var71 = false;
                   }

                   if (var8.intValue() == 9999 && this.getKDI(super.ItemStacks[1]).byteValue() > 0) {
                       var71 = false;
                   }

                   if (var8.intValue() == 600 && this.getObl(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }

                   if (var8.intValue() == 1000 && this.getLPM(super.ItemStacks[1]) != null && this.getLPM(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }

                   if (var8.intValue() == 1001 && this.getLPTM(super.ItemStacks[1]) != null && this.getLPTM(super.ItemStacks[1]).intValue() > 0) {
                       var71 = false;
                   }

                   if (var71) {
                       --super.ItemStacks[0].stackSize;
                       if (super.ItemStacks[0].stackSize <= 0) {
                           super.ItemStacks[0] = null;
                       }

                       var6 = ((Slot_R) Manager.INSTANCE.Get(super.RecipesIndex).get(1)).getMetaArmSub(super.ItemStacks[1]);
                       if (var8.intValue() == 52) {
                           if (this.getrandom() == 1) {
                               this.setUpgradeCharge(super.ItemStacks[1], 500);
                           } else {
                               this.setUpgradeCharge(super.ItemStacks[1], -1);
                           }

                       } else if (var8.intValue() == 777) {
                           if (this.getrandom() == 1) {
                               this.addLetal(super.ItemStacks[1], (byte) 1);
                           } else {
                               this.addLetal(super.ItemStacks[1], (byte) -1);
                           }
                       } else if (var8.intValue() == 888) {
                           if (this.getrandom() == 1) {
                               this.setWither(super.ItemStacks[1], 1);
                           } else {
                               this.setWither(super.ItemStacks[1], -1);
                           }
                       } else if (var8.intValue() == 201) {
                           this.setExo(super.ItemStacks[1], 1);
                       } else if (var8.intValue() == 31) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 1);
                       } else if (var8.intValue() == 21) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 2);
                       } else if (var8.intValue() == 22) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 3);
                       } else if (var8.intValue() == 23) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 4);
                       } else if (var8.intValue() == 24) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 5);
                       } else if (var8.intValue() == 25) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 6);
                       } else if (var8.intValue() == 26) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 7);
                       } else if (var8.intValue() == 27) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 8);
                       } else if (var8.intValue() == 28) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 9);
                       } else if (var8.intValue() == 29) {
                           this.setDarkShieldlvl(super.ItemStacks[1], 10);
                       } else if (var8.intValue() == 205) {
                           this.setHeb(super.ItemStacks[1], 1);
                       } else if (var8.intValue() == 9999) {
                           this.setKDI(super.ItemStacks[1], 11246);
                       } else if (var8.intValue() == 600) {
                           this.setObl(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2223) {
                           this.setLIT(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2222) {
                           this.setLIT1(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2224) {
                           this.setLIT2(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2225) {
                           this.setLIT3(super.ItemStacks[1]);

                       } else if (var8.intValue() == 2226) {
                           this.setLIT4(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2227) {
                           this.setLIT5(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2228) {
                           this.setLIT6(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2229) {
                           this.setLIT7(super.ItemStacks[1]);

                       } else if (var8.intValue() == 2230) {
                           this.setLIT8(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2231) {
                           this.setLIT9(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2232) {
                           this.setLIT10(super.ItemStacks[1]);
                       } else if (var8.intValue() == 2233) {
                           this.setLIT11(super.ItemStacks[1]);


                       } else if (var8.intValue() == 7778) {
                           setKorona(this.ItemStacks[1]);
                       } else if (var8.intValue() == 1000) {
                           this.setLPM(super.ItemStacks[1], 1000);
                       } else if (var8.intValue() == 1001) {
                           this.setLPTM(super.ItemStacks[1], 1000);
                       } else if (var8.intValue() > -1 && var8.intValue() < 16) {
                           this.setColor(super.ItemStacks[1], var8.intValue());
                       } else if (var8.intValue() == 250 && this.getYcRandom() == 1) {
                           this.setYc(super.ItemStacks[1], 1);
                       } else if (var8.intValue() == 777) {
                           if (this.getYcRandom() == 1) {
                               this.setCD(super.ItemStacks[1], this.getCDCharge(super.ItemStacks[1]).intValue() + 1);
                           } else {
                               this.setCD(super.ItemStacks[1], 0);
                           }
                       }

                       this.onInventoryChanged();
                   }
               }

               if (flag) {
                   PacketDispatcher.sendPacketToAllAround((double) super.xCoord, (double) super.yCoord, (double) super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
               }
           } catch (Exception var61) {
               FMLCommonHandler.instance().getFMLLogger().warning(this.getClass().getSimpleName() + " " + super.xCoord + " " + super.yCoord + " " + super.zCoord + " " + var61.getMessage());
           }
       }
   }
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      try {
         return i == 0 && super.ItemStacks[1] != null && this.getDarkShieldCharge(super.ItemStacks[1]).intValue() > 0?false:((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(i)).EqualSlotAsItemID(itemstack);
      } catch (Exception var4) {
         return false;
      }
   }

   String getInventoryName() {
      return "container.UpgradeArmor";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }
}
