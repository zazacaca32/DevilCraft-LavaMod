package codechicken.core.featurehack;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityRenderHook extends Entity {

   public final EntityRenderHook.IRenderCallback callback;


   public EntityRenderHook(World world, double x, double y, double z, EntityRenderHook.IRenderCallback callback) {
      super(world);
      this.setPosition(x, y, z);
      super.ignoreFrustumCheck = true;
      this.callback = callback;
   }

   public void onUpdate() {
      if(!this.callback.isValid()) {
         this.setDead();
      }

   }

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound var1) {}

   protected void writeEntityToNBT(NBTTagCompound var1) {}

   public boolean shouldRenderInPass(int pass) {
      return this.callback.shouldRenderInPass(pass);
   }

   public interface IRenderCallback {

      void render(float var1, int var2);

      boolean shouldRenderInPass(int var1);

      boolean isValid();
   }
}
