package codechicken.core.liquid;

import codechicken.core.liquid.LiquidUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public class ExtendedLiquidTank implements ILiquidTank {

   private LiquidStack liquid;
   private boolean changeType;
   private int capacity;


   public ExtendedLiquidTank(LiquidStack type, int capacity) {
      if(type == null) {
         this.liquid = new LiquidStack(0, 0);
         this.changeType = true;
      } else {
         this.liquid = new LiquidStack(type.itemID, 0, type.itemMeta);
      }

      this.capacity = capacity;
   }

   public ExtendedLiquidTank(int capacity) {
      this((LiquidStack)null, capacity);
   }

   public LiquidStack getLiquid() {
      return this.liquid.copy();
   }

   public int getCapacity() {
      return this.capacity;
   }

   public boolean canAccept(LiquidStack type) {
      return type == null || type.itemID <= 0 || this.liquid.amount == 0 && this.changeType || this.liquid.isLiquidEqual(type);
   }

   public int fill(LiquidStack resource, boolean doFill) {
      if(resource != null && resource.itemID > 0) {
         if(!this.canAccept(resource)) {
            return 0;
         } else {
            int tofill = Math.min(this.getCapacity() - this.liquid.amount, resource.amount);
            if(doFill && tofill > 0) {
               if(!this.liquid.isLiquidEqual(resource)) {
                  this.liquid = LiquidUtils.copy(resource, this.liquid.amount + tofill);
               } else {
                  this.liquid.amount += tofill;
               }

               this.onLiquidChanged();
            }

            return tofill;
         }
      } else {
         return 0;
      }
   }

   public LiquidStack drain(int maxDrain, boolean doDrain) {
      if(this.liquid.amount != 0 && maxDrain > 0) {
         int todrain = Math.min(maxDrain, this.liquid.amount);
         if(doDrain && todrain > 0) {
            this.liquid.amount -= todrain;
            this.onLiquidChanged();
         }

         return new LiquidStack(this.liquid.itemID, todrain, this.liquid.itemMeta);
      } else {
         return null;
      }
   }

   public int getTankPressure() {
      return 0;
   }

   public void onLiquidChanged() {}

   public void fromTag(NBTTagCompound tag) {
      LiquidStack nbtLiquid = LiquidStack.loadLiquidStackFromNBT(tag);
      if(nbtLiquid != null) {
         this.liquid = nbtLiquid;
      }

   }

   public NBTTagCompound toTag() {
      return this.liquid.writeToNBT(new NBTTagCompound());
   }
}
