package net.minecraft.client.addon.lavamobs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.entity.inventory.IMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipe;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipeList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.INpc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockDemonTrader extends EntityCreature implements INpc, IMerchant {

   public String skin = "oldman.png";
   private boolean check_man = true;
   private EntityPlayer buyingPlayer;
   private MerchantRecipeList buyingList;
   private boolean needsInitilization;
   private String lastBuyingPlayer;
   private int timeUntilReset;


   public BlockDemonTrader(World par1World) {
      super(par1World);
      super.moveSpeed = 0.0F;
   }

   public boolean interact(EntityPlayer par1EntityPlayer) {
      ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
      boolean flag = itemstack != null && itemstack.itemID == Item.monsterPlacer.itemID;
      if(!flag && this.isEntityAlive() && !this.isTrading() && !this.isChild()) {
         if(!super.worldObj.isRemote) {
            this.setCustomer(par1EntityPlayer);
            par1EntityPlayer.openGui(LavaModMobs.instance, 10028, par1EntityPlayer.worldObj, super.entityId, 0, 0);
         }

         return true;
      } else {
         return super.interact(par1EntityPlayer);
      }
   }

   protected Entity findPlayerToAttack() {
      return null;
   }

   protected boolean canDespawn() {
      return false;
   }

   public int getAttackStrength(Entity par1Entity) {
      return 0;
   }

   protected boolean isAIEnabled() {
      return false;
   }

   public int getMaxHealth() {
      return 20;
   }

   public int getTotalArmorValue() {
      return 2;
   }

   public void onLivingUpdate() {
      if(this.check_man) {
         this.check_man = false;
         int d011111111 = MathHelper.floor_double(super.posX);
         int y = MathHelper.floor_double(super.posY);
         int d211111111 = MathHelper.floor_double(super.posZ);
         String name = "Демон";
         this.func_94058_c("Демон");
      }

      if(super.newPosRotationIncrements > 0) {
         double d0111111111 = super.posX + (super.newPosX - super.posX) / (double)super.newPosRotationIncrements;
         double d2111111111 = super.posY + (super.newPosY - super.posY) / (double)super.newPosRotationIncrements;
         double d3 = super.posZ + (super.newPosZ - super.posZ) / (double)super.newPosRotationIncrements;
         double d4 = MathHelper.wrapAngleTo180_double(super.newRotationYaw - (double)super.rotationYaw);
         super.rotationYaw += (float)(d4 / (double)super.newPosRotationIncrements);
         super.rotationPitch += (float)((super.newRotationPitch - (double)super.rotationPitch) / (double)super.newPosRotationIncrements);
         --super.newPosRotationIncrements;
         this.setPosition(d0111111111, d2111111111, d3);
         this.setRotation(super.rotationYaw, super.rotationPitch);
      }

      super.worldObj.theProfiler.startSection("ai");
      if(this.isClientWorld()) {
         super.worldObj.theProfiler.startSection("oldAi");
         this.updateEntityActionState();
         super.worldObj.theProfiler.endSection();
         super.rotationYawHead = super.rotationYaw;
      }

      super.worldObj.theProfiler.endSection();
   }

   protected void dropRareDrop(int par1) {}

   protected int getDropItemId() {
      return 0;
   }

   protected boolean isMovementCeased() {
      return false;
   }

   public boolean isEntityInvulnerable() {
      return true;
   }

   protected boolean isMovementBlocked() {
      return false;
   }

   public void addPotionEffect(PotionEffect par1PotionEffect) {}

   public String getTexture() {
      return "/mods/" + this.skin;
   }

   public void setCustomer(EntityPlayer par1EntityPlayer) {
      this.buyingPlayer = par1EntityPlayer;
   }

   public EntityPlayer getCustomer() {
      return this.buyingPlayer;
   }

   public boolean isTrading() {
      return this.buyingPlayer != null;
   }

   public void useRecipe(MerchantRecipe par1MerchantRecipe) {
      par1MerchantRecipe.incrementToolUses();
      if(par1MerchantRecipe.hasSameIDsAs((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1))) {
         this.timeUntilReset = 40;
         this.needsInitilization = true;
         this.lastBuyingPlayer = this.buyingPlayer != null?this.buyingPlayer.getCommandSenderName():null;
      }

   }

   public MerchantRecipeList getRecipes(EntityPlayer par1EntityPlayer) {
      if(this.buyingList == null) {
         this.addDefaultEquipmentAndRecipies(1);
      }

      return this.buyingList;
   }

   @SideOnly(Side.CLIENT)
   public void setRecipes(MerchantRecipeList par1MerchantRecipeList) {}

   private void addDefaultEquipmentAndRecipies(int par1) {
      MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 10, 3), (ItemStack)null, new ItemStack(2594, 1, 4)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 64, 4), (ItemStack)null, new ItemStack(3330, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 8, 4), new ItemStack(2594, 10, 3), new ItemStack(2813, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 10, 4), new ItemStack(2594, 12, 3), new ItemStack(2814, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 7, 4), new ItemStack(2594, 10, 3), new ItemStack(2815, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 6, 4), new ItemStack(2594, 8, 3), new ItemStack(2816, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 12, 4), new ItemStack(2594, 15, 3), new ItemStack(2817, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 6, 4), new ItemStack(2594, 10, 3), new ItemStack(2820, 1, 1)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 3, 4), new ItemStack(2594, 0, 3), new ItemStack(2581, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 3, 4), (ItemStack)null, new ItemStack(2582, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 3, 4), (ItemStack)null, new ItemStack(2578, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 3, 4), (ItemStack)null, new ItemStack(2554, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 4, 4), (ItemStack)null, new ItemStack(2596, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 4, 4), (ItemStack)null, new ItemStack(2596, 1, 1)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 4, 4), (ItemStack)null, new ItemStack(2596, 1, 2)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 3, 4), (ItemStack)null, new ItemStack(2596, 1, 3)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 18, 4), new ItemStack(2594, 12, 3), new ItemStack(2822, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 24, 4), new ItemStack(2594, 16, 3), new ItemStack(2823, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 16, 4), new ItemStack(2594, 10, 3), new ItemStack(2824, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 14, 4), new ItemStack(2594, 8, 3), new ItemStack(2825, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 28, 4), new ItemStack(2594, 16, 3), new ItemStack(2826, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 32, 4), new ItemStack(2594, 16, 3), new ItemStack(6557, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 32, 4), new ItemStack(2594, 16, 3), new ItemStack(6357, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 40, 4), new ItemStack(2594, 8, 3), new ItemStack(6358, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 30, 4), new ItemStack(2594, 10, 3), new ItemStack(6359, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 28, 4), new ItemStack(2594, 12, 3), new ItemStack(6360, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 50, 4), new ItemStack(2594, 12, 3), new ItemStack(6558, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 28, 4), new ItemStack(2594, 16, 3), new ItemStack(6580, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 48, 4), new ItemStack(2594, 20, 3), new ItemStack(6579, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 32, 4), new ItemStack(2594, 12, 3), new ItemStack(2804, 1, 7)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 28, 4), new ItemStack(2594, 16, 3), new ItemStack(2805, 1, 7)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 48, 4), new ItemStack(2594, 10, 3), new ItemStack(2499, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(3330, 1, 0), (ItemStack)null, new ItemStack(2516, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 32, 4), new ItemStack(2594, 12, 3), new ItemStack(6365, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 40, 4), new ItemStack(2594, 10, 3), new ItemStack(6366, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 30, 4), new ItemStack(2594, 8, 3), new ItemStack(6367, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 28, 4), new ItemStack(2594, 10, 3), new ItemStack(6368, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 32, 4), (ItemStack)null, new ItemStack(3869, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 48, 4), new ItemStack(2594, 10, 3), new ItemStack(2804, 1, 9)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 48, 4), new ItemStack(2594, 10, 3), new ItemStack(2805, 1, 8)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 10, 4), (ItemStack)null, new ItemStack(7168, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 8, 4), (ItemStack)null, new ItemStack(7169, 1, 0)));
      merchantrecipelist.add(new MerchantRecipe(new ItemStack(2594, 12, 4), (ItemStack)null, new ItemStack(7170, 1, 10)));
      if(this.buyingList == null) {
         this.buyingList = new MerchantRecipeList();
      }

      for(int j1 = 0; j1 < merchantrecipelist.size(); ++j1) {
         this.buyingList.addToListWithCheck((MerchantRecipe)merchantrecipelist.get(j1));
      }

   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      if(this.buyingList != null) {
         par1NBTTagCompound.setCompoundTag("Offers", this.buyingList.getRecipiesAsTags());
      }

   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      if(par1NBTTagCompound.hasKey("Offers")) {
         NBTTagCompound nbttagcompound1 = par1NBTTagCompound.getCompoundTag("Offers");
         this.buyingList = new MerchantRecipeList(nbttagcompound1);
      }

   }
}
