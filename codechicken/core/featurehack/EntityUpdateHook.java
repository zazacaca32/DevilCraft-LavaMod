package codechicken.core.featurehack;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityUpdateHook extends Entity {

   public final EntityUpdateHook.IUpdateCallback callback;


   public EntityUpdateHook(World world, int x, int y, int z, EntityUpdateHook.IUpdateCallback callback) {
      super(world);
      this.setPosition((double)x, (double)y, (double)z);
      this.callback = callback;
   }

   public void onUpdate() {
      if(!this.callback.isValid()) {
         this.setDead();
      } else {
         this.callback.onUpdate();
      }

   }

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound var1) {}

   protected void writeEntityToNBT(NBTTagCompound var1) {}

   public interface IUpdateCallback {

      void onUpdate();

      boolean isValid();
   }
}
