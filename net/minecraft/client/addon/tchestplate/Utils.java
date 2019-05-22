package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.util.Coords;
import net.minecraft.client.addon.tchestplate.weapon.items.ActiveMode;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public final class Utils
{
    public static short getEnchantHammer(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("hup") ? par1ItemStack.stackTagCompound.getShort("hup") : 0;
    }

    public static boolean isModLoaded(String modid)
    {
        try
        {
            boolean var4 = ModLoader.isModLoaded(modid);
            return var4;
        }
        catch (Throwable var41)
        {
            Iterator i$ = Loader.instance().getActiveModList().iterator();
            ModContainer f;

            do
            {
                if (!i$.hasNext())
                {
                    return false;
                }

                f = (ModContainer)i$.next();
            }
            while (!f.getModId().equals(modid));

            return true;
        }
    }

    public static boolean incEnchantHammer(int inc, ItemStack par1ItemStack)
    {
        getOrCreateNbtData(par1ItemStack);

        if (par1ItemStack.stackTagCompound == null)
        {
            return false;
        }
        else if (par1ItemStack.stackTagCompound.hasKey("hup"))
        {
            short up = par1ItemStack.stackTagCompound.getShort("hup");
            up += (short)inc;
            par1ItemStack.stackTagCompound.setShort("hup", up);
            return true;
        }
        else
        {
            par1ItemStack.stackTagCompound.setShort("hup", (short)inc);
            return true;
        }
    }

    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static NBTTagCompound getOrCreateNbtData(ItemStack itemStack)
    {
        NBTTagCompound ret = itemStack.getTagCompound();

        if (ret == null)
        {
            ret = new NBTTagCompound("tag");
            itemStack.setTagCompound(ret);
        }

        return ret;
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemStack(FontRenderer fr, ItemStack it, int x, int y)
    {
        FontRenderer font = null;

        if (it != null)
        {
            font = it.getItem().getFontRenderer(it);
        }

        if (font == null)
        {
            font = fr;
        }

        ClientProxy.itemRenderer.renderItemAndEffectIntoGUI(font, Minecraft.getMinecraft().renderEngine, it, x, y);
    }

    @SideOnly(Side.CLIENT)
    public static void addInformationNoobItem(ItemStack par1ItemStack, List par2List)
    {
        par2List.add("§6 Итем новечка дается на 3 дня.");
        NBTTagCompound nbt = getOrCreateNbtData(par1ItemStack);
        String owner = nbt.getString("nubowner");

        if (owner != null && !owner.isEmpty())
        {
            long time_ = nbt.getLong("nubtime");

            if (System.currentTimeMillis() < time_)
            {
                par2List.add("§7 Итем принадлежит " + owner);
                par2List.add("§7 Итем будет не активен через " + (time_ - System.currentTimeMillis()) / 1000L + "сек");
            }
            else
            {
                par2List.add("§7 У итема истёк срок службы он исчезнет.");
            }
        }
    }

    public static NBTTagList getLavaTagList(ItemStack itemStack)
    {
        return (NBTTagList) getOrCreateNbtData(itemStack).getTag("LaEnch");
    }

    public static void setLavaEnch(Map par0Map, ItemStack par1ItemStack)
    {
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = par0Map.keySet().iterator();

        while (iterator.hasNext())
        {
            ActiveMode i = (ActiveMode) iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setShort("id", (short) i.ordinal());
            nbttagcompound.setShort("lvl", (short)((Integer) par0Map.get(i)).intValue());
            nbttaglist.appendTag(nbttagcompound);
        }

        if (nbttaglist.tagCount() > 0)
        {
            par1ItemStack.setTagInfo("LaEnch", nbttaglist);
        }
        else if (par1ItemStack.hasTagCompound())
        {
            par1ItemStack.getTagCompound().removeTag("LaEnch");
        }
    }

    public static Map getLavaEnch(ItemStack par0ItemStack)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        NBTTagList nbttaglist = getLavaTagList(par0ItemStack);

        if (nbttaglist != null)
        {
            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                short short1 = ((NBTTagCompound) nbttaglist.tagAt(i)).getShort("id");
                short short2 = ((NBTTagCompound) nbttaglist.tagAt(i)).getShort("lvl");
                linkedhashmap.put(ActiveMode.values()[short1], Integer.valueOf(short2));
            }
        }

        return linkedhashmap;
    }

    public static final byte[] intToByteArray(int value)
    {
        return new byte[] {(byte)(value >>> 24), (byte)(value >>> 16)};
    }

    public static int byteArrayToInt(byte[] value)
    {
        return (value[1] & 255) << 16 | (value[0] & 255) << 24;
    }

    public static Entity getPointedEntity(World world, EntityPlayer entityplayer, double range, float padding)
    {
        return getPointedEntity(world, entityplayer, range, padding, false);
    }

    public static Entity getPointedEntity(World world, EntityPlayer entityplayer, double range, float padding, boolean nonCollide)
    {
        Entity pointedEntity = null;
        Vec3 vec3d = Vec3.fakePool.getVecFromPool(entityplayer.posX, entityplayer.posY + (double)entityplayer.getEyeHeight(), entityplayer.posZ);
        Vec3 vec3d2 = entityplayer.getLookVec();
        Vec3 vec3d3 = vec3d.addVector(vec3d2.xCoord * range, vec3d2.yCoord * range, vec3d2.zCoord * range);
        List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.boundingBox.addCoord(vec3d2.xCoord * range, vec3d2.yCoord * range, vec3d2.zCoord * range).expand((double)padding, (double)padding, (double)padding));
        double d2 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity = (Entity)list.get(i);

            if ((entity.canBeCollidedWith() || nonCollide) && world.rayTraceBlocks_do_do(world.getWorldVec3Pool().getVecFromPool(entityplayer.posX, entityplayer.posY + (double)entityplayer.getEyeHeight(), entityplayer.posZ), world.getWorldVec3Pool().getVecFromPool(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ), false, true) == null)
            {
                float f2 = Math.max(0.8F, entity.getCollisionBorderSize());
                AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d3);

                if (axisalignedbb.isVecInside(vec3d))
                {
                    if (0.0D < d2 || d2 == 0.0D)
                    {
                        pointedEntity = entity;
                        d2 = 0.0D;
                    }
                }
                else if (movingobjectposition != null)
                {
                    double d3 = vec3d.distanceTo(movingobjectposition.hitVec);

                    if (d3 < d2 || d2 == 0.0D)
                    {
                        pointedEntity = entity;
                        d2 = d3;
                    }
                }
            }
        }

        return pointedEntity;
    }

    public static Entity getPointedEntity(World world, EntityPlayer entityplayer, double range, Class clazz)
    {
        Entity pointedEntity = null;
        Vec3 vec3d = Vec3.fakePool.getVecFromPool(entityplayer.posX, entityplayer.posY + (double)entityplayer.getEyeHeight(), entityplayer.posZ);
        Vec3 vec3d2 = entityplayer.getLookVec();
        Vec3 vec3d3 = vec3d.addVector(vec3d2.xCoord * range, vec3d2.yCoord * range, vec3d2.zCoord * range);
        float f1 = 1.1F;
        List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.boundingBox.addCoord(vec3d2.xCoord * range, vec3d2.yCoord * range, vec3d2.zCoord * range).expand(1.100000023841858D, 1.100000023841858D, 1.100000023841858D));
        double d2 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity = (Entity)list.get(i);

            if (entity.canBeCollidedWith() && world.rayTraceBlocks_do_do(world.getWorldVec3Pool().getVecFromPool(entityplayer.posX, entityplayer.posY + (double)entityplayer.getEyeHeight(), entityplayer.posZ), world.getWorldVec3Pool().getVecFromPool(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ), false, true) == null && !clazz.isInstance(entity))
            {
                float f2 = Math.max(0.8F, entity.getCollisionBorderSize());
                AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d3);

                if (axisalignedbb.isVecInside(vec3d))
                {
                    if (0.0D < d2 || d2 == 0.0D)
                    {
                        pointedEntity = entity;
                        d2 = 0.0D;
                    }
                }
                else if (movingobjectposition != null)
                {
                    double d3 = vec3d.distanceTo(movingobjectposition.hitVec);

                    if (d3 < d2 || d2 == 0.0D)
                    {
                        pointedEntity = entity;
                        d2 = d3;
                    }
                }
            }
        }

        return pointedEntity;
    }

    public static MovingObjectPosition getTargetBlock(World world, EntityPlayer player, boolean par3)
    {
        float var4 = 1.0F;
        float var5 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * 1.0F;
        float var6 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * 1.0F;
        double var7 = player.prevPosX + (player.posX - player.prevPosX) * 1.0D;
        double var8 = player.prevPosY + (player.posY - player.prevPosY) * 1.0D + 1.62D - (double)player.yOffset;
        double var9 = player.prevPosZ + (player.posZ - player.prevPosZ) * 1.0D;
        Vec3 var10 = world.getWorldVec3Pool().getVecFromPool(var7, var8, var9);
        float var11 = MathHelper.cos(-var6 * 0.017453292F - (float)Math.PI);
        float var12 = MathHelper.sin(-var6 * 0.017453292F - (float)Math.PI);
        float var13 = -MathHelper.cos(-var5 * 0.017453292F);
        float var14 = MathHelper.sin(-var5 * 0.017453292F);
        float var15 = var12 * var13;
        float var16 = var11 * var13;
        double var17 = 10.0D;
        Vec3 var18 = var10.addVector((double)var15 * 10.0D, (double)var14 * 10.0D, (double)var16 * 10.0D);
        return world.rayTraceBlocks_do_do(var10, var18, par3, !par3);
    }

    public static MovingObjectPosition getTargetBlock(World world, double x, double y, double z, float yaw, float pitch, boolean par3, double range)
    {
        Vec3 var13 = world.getWorldVec3Pool().getVecFromPool(x, y, z);
        float var14 = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
        float var15 = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);
        float var16 = -MathHelper.cos(-pitch * 0.017453292F);
        float var17 = MathHelper.sin(-pitch * 0.017453292F);
        float var18 = var15 * var16;
        float var19 = var14 * var16;
        Vec3 var20 = var13.addVector((double)var18 * range, (double)var17 * range, (double)var19 * range);
        return world.rayTraceBlocks_do_do(var13, var20, par3, !par3);
    }

    public static void render3DItem(ItemStack par2ItemStack, int par3, float scale, int brightness)
    {
        Minecraft mc = Minecraft.getMinecraft();
        GL11.glPushMatrix();
        Block block = null;

        if (par2ItemStack.getItem() instanceof ItemBlock && par2ItemStack.itemID < Block.blocksList.length)
        {
            block = Block.blocksList[par2ItemStack.itemID];
        }

        if (block != null && par2ItemStack.getItemSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.blocksList[par2ItemStack.itemID].getRenderType()))
        {
            mc.renderEngine.bindTexture("/terrain.png");
            ClientProxy.renderBlocksInstance.renderBlockAsItem(Block.blocksList[par2ItemStack.itemID], par2ItemStack.getItemDamage(), 1.0F);
        }
        else
        {
            Icon icon = Minecraft.getMinecraft().renderViewEntity.getItemIcon(par2ItemStack, par3);

            if (icon == null)
            {
                GL11.glPopMatrix();
                return;
            }

            if (par2ItemStack.getItemSpriteNumber() == 0)
            {
                mc.renderEngine.bindTexture("/terrain.png");
            }
            else
            {
                mc.renderEngine.bindTexture("/gui/items.png");
            }

            Tessellator tessellator = Tessellator.instance;
            float f = icon.getMinU();
            float f2 = icon.getMaxU();
            float f3 = icon.getMinV();
            float f4 = icon.getMaxV();
            float f5 = 0.0F;
            float f6 = 0.3F;
            GL11.glEnable('耺');
            GL11.glScalef(scale, scale, scale);
            renderItemIn2D(tessellator, f2, f3, f, f4, icon.getSheetWidth(), icon.getSheetHeight(), 0.0625F, brightness);

            if (par2ItemStack != null && par2ItemStack.hasEffect() && par3 == 0)
            {
                GL11.glDepthFunc(GL11.GL_EQUAL);
                GL11.glDisable(GL11.GL_LIGHTING);
                mc.renderEngine.bindTexture("%blur%/misc/glint.png");
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                float f7 = 0.76F;
                GL11.glColor4f(0.38F, 0.19F, 0.608F, 1.0F);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glPushMatrix();
                float f8 = 0.125F;
                GL11.glScalef(0.125F, 0.125F, 0.125F);
                float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                GL11.glTranslatef(f9, 0.0F, 0.0F);
                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F, brightness);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(0.125F, 0.125F, 0.125F);
                f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-f9, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F, brightness);
                GL11.glPopMatrix();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDepthFunc(GL11.GL_LEQUAL);
            }

            GL11.glDisable('耺');
        }

        GL11.glPopMatrix();
    }

    public static void renderItemIn2D(Tessellator par0Tessellator, float par1, float par2, float par3, float par4, int par5, int par6, float par7, int brightness)
    {
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setBrightness(brightness);
        par0Tessellator.setNormal(0.0F, 0.0F, 1.0F);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)par1, (double)par4);
        par0Tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, (double)par3, (double)par4);
        par0Tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, (double)par3, (double)par2);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, (double)par1, (double)par2);
        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setBrightness(brightness);
        par0Tessellator.setNormal(0.0F, 0.0F, -1.0F);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, (double)(0.0F - par7), (double)par1, (double)par2);
        par0Tessellator.addVertexWithUV(1.0D, 1.0D, (double)(0.0F - par7), (double)par3, (double)par2);
        par0Tessellator.addVertexWithUV(1.0D, 0.0D, (double)(0.0F - par7), (double)par3, (double)par4);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, (double)(0.0F - par7), (double)par1, (double)par4);
        par0Tessellator.draw();
        float f5 = (float)par5 * (par1 - par3);
        float f6 = (float)par6 * (par4 - par2);
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setBrightness(brightness);
        par0Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        int k;
        float f7;
        float f8;

        for (k = 0; (float)k < f5; ++k)
        {
            f7 = (float)k / f5;
            f8 = par1 + (par3 - par1) * f7 - 0.5F / (float)par5;
            par0Tessellator.addVertexWithUV((double)f7, 0.0D, (double)(0.0F - par7), (double)f8, (double)par4);
            par0Tessellator.addVertexWithUV((double)f7, 0.0D, 0.0D, (double)f8, (double)par4);
            par0Tessellator.addVertexWithUV((double)f7, 1.0D, 0.0D, (double)f8, (double)par2);
            par0Tessellator.addVertexWithUV((double)f7, 1.0D, (double)(0.0F - par7), (double)f8, (double)par2);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setBrightness(brightness);
        par0Tessellator.setNormal(1.0F, 0.0F, 0.0F);
        float f9;

        for (k = 0; (float)k < f5; ++k)
        {
            f7 = (float)k / f5;
            f8 = par1 + (par3 - par1) * f7 - 0.5F / (float)par5;
            f9 = f7 + 1.0F / f5;
            par0Tessellator.addVertexWithUV((double)f9, 1.0D, (double)(0.0F - par7), (double)f8, (double)par2);
            par0Tessellator.addVertexWithUV((double)f9, 1.0D, 0.0D, (double)f8, (double)par2);
            par0Tessellator.addVertexWithUV((double)f9, 0.0D, 0.0D, (double)f8, (double)par4);
            par0Tessellator.addVertexWithUV((double)f9, 0.0D, (double)(0.0F - par7), (double)f8, (double)par4);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setBrightness(brightness);
        par0Tessellator.setNormal(0.0F, 1.0F, 0.0F);

        for (k = 0; (float)k < f6; ++k)
        {
            f7 = (float)k / f6;
            f8 = par4 + (par2 - par4) * f7 - 0.5F / (float)par6;
            f9 = f7 + 1.0F / f6;
            par0Tessellator.addVertexWithUV(0.0D, (double)f9, 0.0D, (double)par1, (double)f8);
            par0Tessellator.addVertexWithUV(1.0D, (double)f9, 0.0D, (double)par3, (double)f8);
            par0Tessellator.addVertexWithUV(1.0D, (double)f9, (double)(0.0F - par7), (double)par3, (double)f8);
            par0Tessellator.addVertexWithUV(0.0D, (double)f9, (double)(0.0F - par7), (double)par1, (double)f8);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setBrightness(brightness);
        par0Tessellator.setNormal(0.0F, -1.0F, 0.0F);

        for (k = 0; (float)k < f6; ++k)
        {
            f7 = (float)k / f6;
            f8 = par4 + (par2 - par4) * f7 - 0.5F / (float)par6;
            par0Tessellator.addVertexWithUV(1.0D, (double)f7, 0.0D, (double)par3, (double)f8);
            par0Tessellator.addVertexWithUV(0.0D, (double)f7, 0.0D, (double)par1, (double)f8);
            par0Tessellator.addVertexWithUV(0.0D, (double)f7, (double)(0.0F - par7), (double)par1, (double)f8);
            par0Tessellator.addVertexWithUV(1.0D, (double)f7, (double)(0.0F - par7), (double)par3, (double)f8);
        }

        par0Tessellator.draw();
    }

    public static Coords getBlockBelowLoc(Coords loc, World w)
    {
        Coords locBelow = loc.subtract(0.0F, 1.0F, 0.0F);

        if (locBelow.y < 20.0F)
        {
            return locBelow;
        }
        else
        {
            if (w.isAirBlock((int)locBelow.x, (int)locBelow.y, (int)locBelow.z))
            {
                locBelow = getBlockBelowLoc(locBelow, w);
            }

            return locBelow;
        }
    }

    public static boolean getRandomProc(double procent)
    {
        double[] randomic = new double[] {procent, 100.0D - procent};
        double r = Math.random() * 100.0D;
        int num = randomic.length;

        for (int i = 0; i < randomic.length; ++i)
        {
            if (r <= randomic[i])
            {
                num = i;
                break;
            }

            r -= randomic[i];
        }

        return num <= 0;
    }
}
