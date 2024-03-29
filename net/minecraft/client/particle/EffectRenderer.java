package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EffectRenderer
{
    /** Reference to the World object. */
    protected World worldObj;
    private List[] fxLayers = new List[4];
    private RenderEngine renderer;

    /** RNG. */
    private Random rand = new Random();

    public EffectRenderer(World par1World, RenderEngine par2RenderEngine)
    {
        if (par1World != null)
        {
            this.worldObj = par1World;
        }

        this.renderer = par2RenderEngine;

        for (int i = 0; i < 4; ++i)
        {
            this.fxLayers[i] = new ArrayList();
        }
    }

    public void addEffect(EntityFX par1EntityFX)
    {
        int i = par1EntityFX.getFXLayer();

        if (this.fxLayers[i].size() >= 4000)
        {
            this.fxLayers[i].remove(0);
        }

        this.fxLayers[i].add(par1EntityFX);
    }

    public void updateEffects()
    {
        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < this.fxLayers[i].size(); ++j)
            {
                EntityFX entityfx = (EntityFX)this.fxLayers[i].get(j);

                if (entityfx != null)
                {
                    entityfx.onUpdate();
                }

                if (entityfx == null || entityfx.isDead)
                {
                    this.fxLayers[i].remove(j--);
                }
            }
        }
    }

    /**
     * Renders all current particles. Args player, partialTickTime
     */
    public void renderParticles(Entity par1Entity, float par2)
    {
        float f1 = ActiveRenderInfo.rotationX;
        float f2 = ActiveRenderInfo.rotationZ;
        float f3 = ActiveRenderInfo.rotationYZ;
        float f4 = ActiveRenderInfo.rotationXY;
        float f5 = ActiveRenderInfo.rotationXZ;
        EntityFX.interpPosX = par1Entity.lastTickPosX + (par1Entity.posX - par1Entity.lastTickPosX) * (double)par2;
        EntityFX.interpPosY = par1Entity.lastTickPosY + (par1Entity.posY - par1Entity.lastTickPosY) * (double)par2;
        EntityFX.interpPosZ = par1Entity.lastTickPosZ + (par1Entity.posZ - par1Entity.lastTickPosZ) * (double)par2;

        for (int i = 0; i < 3; ++i)
        {
            if (!this.fxLayers[i].isEmpty())
            {
                switch (i)
                {
                    case 0:
                    default:
                        this.renderer.bindTexture("/particles.png");
                        break;

                    case 1:
                        this.renderer.bindTexture("/terrain.png");
                        break;

                    case 2:
                        this.renderer.bindTexture("/gui/items.png");
                }

                Tessellator tessellator = Tessellator.instance;
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDepthMask(false);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
                tessellator.startDrawingQuads();

                for (int j = 0; j < this.fxLayers[i].size(); ++j)
                {
                    EntityFX entityfx = (EntityFX)this.fxLayers[i].get(j);

                    if (entityfx == null)
                    {
                        continue;
                    }

                    tessellator.setBrightness(entityfx.getBrightnessForRender(par2));
                    entityfx.renderParticle(tessellator, par2, f1, f5, f2, f3, f4);
                }

                tessellator.draw();
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glDepthMask(true);
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            }
        }
    }

    public void renderLitParticles(Entity par1Entity, float par2)
    {
        float f1 = MathHelper.cos(par1Entity.rotationYaw * 0.017453292F);
        float f2 = MathHelper.sin(par1Entity.rotationYaw * 0.017453292F);
        float f3 = -f2 * MathHelper.sin(par1Entity.rotationPitch * 0.017453292F);
        float f4 = f1 * MathHelper.sin(par1Entity.rotationPitch * 0.017453292F);
        float f5 = MathHelper.cos(par1Entity.rotationPitch * 0.017453292F);
        byte b0 = 3;

        if (!this.fxLayers[b0].isEmpty())
        {
            Tessellator tessellator = Tessellator.instance;

            for (int i = 0; i < this.fxLayers[b0].size(); ++i)
            {
                EntityFX entityfx = (EntityFX)this.fxLayers[b0].get(i);

                if (entityfx == null)
                {
                    continue;
                }

                tessellator.setBrightness(entityfx.getBrightnessForRender(par2));
                entityfx.renderParticle(tessellator, par2, f1, f5, f2, f3, f4);
            }
        }
    }

    public void clearEffects(World par1World)
    {
        this.worldObj = par1World;

        for (int i = 0; i < 4; ++i)
        {
            this.fxLayers[i].clear();
        }
    }

    public void addBlockDestroyEffects(int par1, int par2, int par3, int par4, int par5)
    {
        Block block = Block.blocksList[par4];

        if (block != null && !block.addBlockDestroyEffects(worldObj, par1, par2, par3, par5, this))
        {
            byte b0 = 4;

            for (int j1 = 0; j1 < b0; ++j1)
            {
                for (int k1 = 0; k1 < b0; ++k1)
                {
                    for (int l1 = 0; l1 < b0; ++l1)
                    {
                        double d0 = (double)par1 + ((double)j1 + 0.5D) / (double)b0;
                        double d1 = (double)par2 + ((double)k1 + 0.5D) / (double)b0;
                        double d2 = (double)par3 + ((double)l1 + 0.5D) / (double)b0;
                        int i2 = this.rand.nextInt(6);
                        this.addEffect((new EntityDiggingFX(this.worldObj, d0, d1, d2, d0 - (double)par1 - 0.5D, d1 - (double)par2 - 0.5D, d2 - (double)par3 - 0.5D, block, i2, par5, this.renderer)).func_70596_a(par1, par2, par3));
                    }
                }
            }
        }
    }

    /**
     * Adds block hit particles for the specified block. Args: x, y, z, sideHit
     */
    public void addBlockHitEffects(int par1, int par2, int par3, int par4)
    {
        int i1 = this.worldObj.getBlockId(par1, par2, par3);

        if (i1 != 0)
        {
            Block block = Block.blocksList[i1];
            float f = 0.1F;
            double d0 = (double)par1 + this.rand.nextDouble() * (block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() - (double)(f * 2.0F)) + (double)f + block.getBlockBoundsMinX();
            double d1 = (double)par2 + this.rand.nextDouble() * (block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() - (double)(f * 2.0F)) + (double)f + block.getBlockBoundsMinY();
            double d2 = (double)par3 + this.rand.nextDouble() * (block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() - (double)(f * 2.0F)) + (double)f + block.getBlockBoundsMinZ();

            if (par4 == 0)
            {
                d1 = (double)par2 + block.getBlockBoundsMinY() - (double)f;
            }

            if (par4 == 1)
            {
                d1 = (double)par2 + block.getBlockBoundsMaxY() + (double)f;
            }

            if (par4 == 2)
            {
                d2 = (double)par3 + block.getBlockBoundsMinZ() - (double)f;
            }

            if (par4 == 3)
            {
                d2 = (double)par3 + block.getBlockBoundsMaxZ() + (double)f;
            }

            if (par4 == 4)
            {
                d0 = (double)par1 + block.getBlockBoundsMinX() - (double)f;
            }

            if (par4 == 5)
            {
                d0 = (double)par1 + block.getBlockBoundsMaxX() + (double)f;
            }

            this.addEffect((new EntityDiggingFX(this.worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, block, par4, this.worldObj.getBlockMetadata(par1, par2, par3), this.renderer)).func_70596_a(par1, par2, par3).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
        }
    }

    public String getStatistics()
    {
        return "" + (this.fxLayers[0].size() + this.fxLayers[1].size() + this.fxLayers[2].size());
    }

    public void addBlockHitEffects(int x, int y, int z, MovingObjectPosition target)
    {
        Block block = Block.blocksList[worldObj.getBlockId(x, y, z)];

        if (block != null && !block.addBlockHitEffects(worldObj, target, this))
        {
            addBlockHitEffects(x, y, z, target.sideHit);
        }
    }
}
