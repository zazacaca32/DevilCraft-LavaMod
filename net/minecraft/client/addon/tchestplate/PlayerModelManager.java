package net.minecraft.client.addon.tchestplate;

import net.minecraft.client.addon.tchestplate.items.IRenderItemChestPlate;
import net.minecraft.client.addon.tchestplate.items.IRenderItemChestPlateExt;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModelPlayerAPI;
import net.minecraft.src.ModelPlayerBase;
import org.lwjgl.opengl.GL11;

public class PlayerModelManager extends ModelPlayerBase
{
    int swing;

    public PlayerModelManager(ModelPlayerAPI var1)
    {
        super(var1);
    }

    public void afterSetRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        if (LavaChestPlate.instance.isGiroScuterClient(var7))
        {
            super.modelPlayer.bipedBody.rotateAngleX = 0.0F;
            super.modelPlayer.bipedRightLeg.rotationPointZ = 0.1F;
            super.modelPlayer.bipedLeftLeg.rotationPointZ = 0.1F;
            super.modelPlayer.bipedRightLeg.rotationPointY = 12.0F;
            super.modelPlayer.bipedLeftLeg.rotationPointY = 12.0F;
            super.modelPlayer.bipedHead.rotationPointY = 0.0F;
            super.modelPlayer.bipedHeadwear.rotationPointY = 0.0F;
            super.modelPlayer.bipedLeftArm.rotateAngleX = -1.2F;
            super.modelPlayer.bipedRightArm.rotateAngleX = -1.2F;
            super.modelPlayer.bipedLeftLeg.rotateAngleZ = -0.1F;
            super.modelPlayer.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
            super.modelPlayer.bipedLeftLeg.rotateAngleX = -0.0F;
            super.modelPlayer.bipedLeftLeg.rotateAngleY = 0.0F;
            super.modelPlayer.bipedRightLeg.rotateAngleZ = 0.1F;
            super.modelPlayer.bipedRightLeg.rotateAngleY = 0.0F;
            super.modelPlayer.bipedRightLeg.rotateAngleX = 0.0F;
            super.modelPlayer.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);

            if (!var7.isSneaking())
            {
                if (var7.isEntityAlive())
                {
                    super.modelPlayer.bipedBody.rotateAngleZ = -0.1F;
                }

                super.modelPlayer.bipedBody.setRotationPoint(1.9F, 12.0F, 0.0F);
                super.modelPlayer.bipedRightLeg.rotateAngleZ = 0.1F;
                super.modelPlayer.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
            }
        }
        else
        {
            super.modelPlayer.bipedBody.rotateAngleZ = 0.0F;
            super.modelPlayer.bipedRightLeg.rotateAngleZ = 0.0F;
        }
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        EntityPlayer var8 = (EntityPlayer)var1;
        ExtendedPlayer var9 = ExtendedPlayer.get(var8);

        if (var9.PredatorMode != 50)
        {
            super.render(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            super.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
        }

        if (!var1.isInvisible())
        {
            var1.worldObj.theProfiler.startSection("LavaChestPlate");

            if (var9 != null)
            {
                boolean var10 = GL11.glIsEnabled(GL11.GL_BLEND);
                int var11 = GL11.glGetInteger(GL11.GL_DEPTH_FUNC);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                if (!var10)
                {
                    GL11.glEnable(GL11.GL_BLEND);
                }

                GL11.glDepthFunc(GL11.GL_LESS);
                int var12 = 0;

                while (true)
                {
                    if (var12 >= var9.inventoryExt.getSizeInventory())
                    {
                        if (var10)
                        {
                            GL11.glEnable(GL11.GL_BLEND);
                        }

                        GL11.glDepthFunc(var11);
                        break;
                    }

                    if (var9.inventoryExt.inventory[var12] != null && var9.inventoryExt.inventory[var12].getItem() instanceof IRenderItemChestPlate)
                    {
                        if (var12 == 1)
                        {
                            var1.worldObj.theProfiler.startSection("render");

                            try
                            {
                                ((IRenderItemChestPlateExt)var9.inventoryExt.inventory[var12].getItem()).doRender(super.modelPlayer, var8, var7, var4, var9, var2, var3, var5, var6, var9.inventoryExt.inventory[var12]);
                            }
                            catch (Exception var20)
                            {
                                ;
                            }

                            var1.worldObj.theProfiler.endSection();
                        }

                        boolean var14 = false;

                        if (var12 == 2)
                        {
                            NBTTagCompound var211 = Utils.getOrCreateNbtData(var9.inventoryExt.inventory[var12]);
                            var14 = true;

                            if (var14)
                            {
                                BaseItemModel var16 = ((IRenderItemChestPlate)var9.inventoryExt.inventory[var12].getItem()).getItemModel(var9.inventoryExt.inventory[var12].getItemDamage());
                                var1.worldObj.theProfiler.startSection("render");

                                try
                                {
                                    if (var16.getType().ordinal() < 4)
                                    {
                                        var16.render(super.modelPlayer, var8, var7, var4, var9);
                                    }
                                }
                                catch (Exception var19)
                                {
                                    ;
                                }

                                var1.worldObj.theProfiler.endSection();
                            }
                        }

                        BaseItemModel var21;

                        if (var12 == 3)
                        {
                            var21 = ((IRenderItemChestPlate)var9.inventoryExt.inventory[var12].getItem()).getItemModel(var9.inventoryExt.inventory[var12].getItemDamage());
                            var1.worldObj.theProfiler.startSection("render");

                            try
                            {
                                var21.render(super.modelPlayer, var8, var7, var4, var9);
                            }
                            catch (Exception var18)
                            {
                                ;
                            }

                            var1.worldObj.theProfiler.endSection();
                        }

                        if (var12 == 5 || var12 == 4)
                        {
                            var21 = ((IRenderItemChestPlate)var9.inventoryExt.inventory[var12].getItem()).getItemModel(var9.inventoryExt.inventory[var12].getItemDamage());
                            var1.worldObj.theProfiler.startSection("render");

                            try
                            {
                                var21.render(super.modelPlayer, var8, var7, var4, var9, var2, var3, var5, var6, var9.inventoryExt.inventory[var12]);
                            }
                            catch (Exception var17)
                            {
                                ;
                            }

                            var1.worldObj.theProfiler.endSection();
                        }
                        if (var12 == 6)
                        {
                            var21 = ((IRenderItemChestPlate)var9.inventoryExt.inventory[var12].getItem()).getItemModel(var9.inventoryExt.inventory[var12].getItemDamage());
                            var1.worldObj.theProfiler.startSection("render");

                            try
                            {
                                var21.render(super.modelPlayer, var8, var7, var4, var9, var2, var3, var5, var6, var9.inventoryExt.inventory[var12]);
                            }
                            catch (Exception var17)
                            {
                                ;
                            }

                            var1.worldObj.theProfiler.endSection();
                        }
                    }

                    ++var12;
                }
            }

            var1.worldObj.theProfiler.endSection();
        }
    }
}
