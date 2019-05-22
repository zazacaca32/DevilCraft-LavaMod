package net.minecraft.client.addon.tchestplate.util;

import cpw.mods.fml.client.FMLClientHandler;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.texturepacks.TexturePackList;
import org.lwjgl.opengl.GL11;

public class UtilsFX
{
    private static Map textureSizeCache = new HashMap();

    public static int getTextureSize(String var0, int var1)
    {
        if (textureSizeCache.get(Arrays.asList(new Serializable[] {var0, Integer.valueOf(var1)})) != null)
        {
            return ((Integer)textureSizeCache.get(Arrays.asList(new Serializable[] {var0, Integer.valueOf(var1)}))).intValue();
        }
        else
        {
            try
            {
                TexturePackList var61 = FMLClientHandler.instance().getClient().texturePackList;
                InputStream var3 = var61.getSelectedTexturePack().getResourceAsStream(var0);

                if (var3 == null)
                {
                    throw new Exception("Image not found: " + var0);
                }
                else
                {
                    BufferedImage var4 = ImageIO.read(var3);
                    int var5 = var4.getWidth() / var1;
                    textureSizeCache.put(Arrays.asList(new Serializable[] {var0, Integer.valueOf(var1)}), Integer.valueOf(var5));
                    return var5;
                }
            }
            catch (Exception var6)
            {
                var6.printStackTrace();
                return 16;
            }
        }
    }

    public static void bindTexture(String var0)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(var0);
    }

    public static void drawTag(Minecraft var0, int var1, int var2, int var3, GuiScreen var4, boolean var5, boolean var6)
    {
        drawTag(var0, var1, var2, var3, 0, var4, var5, var6, 1.0F);
    }

    public static void drawTag(Minecraft var0, int var1, int var2, int var3, int var4, GuiScreen var5, boolean var6, boolean var7, float var8)
    {
        if (var7)
        {
            bindTexture("/mods/thaumcraft/textures/misc/ss_tags_2.png");
        }
        else
        {
            bindTexture("/mods/thaumcraft/textures/misc/ss_tags_1.png");
        }

        byte var11 = 2;
        Color var12 = new Color(11345345);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, 500.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_LIGHTING);

        if (var0.gameSettings.guiScale != 1)
        {
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        }

        byte var13 = 64;
        byte var14 = 0;

        if (var6)
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
            GuiScreen.drawRect(var1 * var11 - 2, var2 * var11 - 2, var1 * var11 + 34, var2 * var11 + 34, -1442840576);
        }

        GL11.glColor4f((float)var12.getRed() / 255.0F, (float)var12.getGreen() / 255.0F, (float)var12.getBlue() / 255.0F, var7 ? Math.min(var8, 0.8F) : var8);
        var5.drawTexturedModalRect(var1 * var11, var2 * var11, var13, var14, 32, 32);
        int var9;
        int var10;

        if (var3 > 1)
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            String var19 = "" + var3;
            var9 = var0.fontRenderer.getStringWidth(var19);

            for (int var20 = -1; var20 <= 1; ++var20)
            {
                for (var10 = -1; var10 <= 1; ++var10)
                {
                    if ((var20 == 0 || var10 == 0) && (var20 != 0 || var10 != 0))
                    {
                        var0.fontRenderer.drawString(var19, var20 + 33 - var9 + var1 * var11, var10 + 33 - var0.fontRenderer.FONT_HEIGHT + var2 * var11, 0);
                    }
                }
            }

            var0.fontRenderer.drawString(var19, 33 - var9 + var1 * var11, 33 - var0.fontRenderer.FONT_HEIGHT + var2 * var11, 16777215);
        }

        if (var4 > 0)
        {
            bindTexture("/mods/thaumcraft/textures/misc/particles.png");
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            int var191 = 32 * (var0.thePlayer.ticksExisted % 16);
            var9 = 32 * (var0.thePlayer.ticksExisted % 32 / 16);
            var5.drawTexturedModalRect(var1 * var11 - 8, var2 * var11 - 8, var191, 96 + var9, 32, 32);
            String var201 = "" + var4;
            var10 = var0.fontRenderer.getStringWidth(var201) / 2;

            for (int var17 = -1; var17 <= 1; ++var17)
            {
                for (int var18 = -1; var18 <= 1; ++var18)
                {
                    if ((var17 == 0 || var18 == 0) && (var17 != 0 || var18 != 0))
                    {
                        var0.fontRenderer.drawString(var201, 8 - var10 + var17 + var1 * var11, 15 + var18 - var0.fontRenderer.FONT_HEIGHT + var2 * var11, 0);
                    }
                }
            }

            var0.fontRenderer.drawString(var201, 8 - var10 + var1 * var11, 15 - var0.fontRenderer.FONT_HEIGHT + var2 * var11, 16777215);
        }

        if (var0.gameSettings.guiScale != 1)
        {
            GL11.glScalef(1.0F, 1.0F, 1.0F);
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
    }
}
