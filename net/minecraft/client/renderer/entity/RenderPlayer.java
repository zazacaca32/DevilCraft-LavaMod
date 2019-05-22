package net.minecraft.client.renderer.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.src.ModelPlayer;
import net.minecraft.src.RenderPlayerAPI;
import net.minecraft.src.RenderPlayerBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderPlayer extends RenderLiving
{
    private ModelBiped modelBipedMain;
    private ModelBiped modelArmorChestplate;
    private ModelBiped modelArmor;
    public static String[] armorFilenamePrefix = new String[] {"cloth", "chain", "iron", "diamond", "gold"};
    @Deprecated
    public static float NAME_TAG_RANGE = 64.0F;
    @Deprecated
    public static float NAME_TAG_RANGE_SNEAK = 32.0F;
    private static final Class<?> forgeHooksClient = TryLoadType("net.minecraftforge.client.ForgeHooksClient");
    private static final Method getArmorTexture = TryLoadMethod(forgeHooksClient, "getArmorTexture", new Class[] {Entity.class, ItemStack.class, String.class, Integer.TYPE, Integer.TYPE});
    private static final Method getArmorModel = TryLoadMethod(forgeHooksClient, "getArmorModel", new Class[] {EntityLiving.class, ItemStack.class, Integer.TYPE, ModelBiped.class});
    private static final Class<?> itemRenderType = TryLoadType("net.minecraftforge.client.IItemRenderer$ItemRenderType");
    private static final Object equipped = TryLoadEnum(itemRenderType, "EQUIPPED");
    private static final Class<?> itemRendererHelper = TryLoadType("net.minecraftforge.client.IItemRenderer$ItemRendererHelper");
    private static final Object block3D = TryLoadEnum(itemRendererHelper, "BLOCK_3D");
    private static final Class<?> minecraftForgeClient = TryLoadType("net.minecraftforge.client.MinecraftForgeClient");
    private static final Method getItemRenderer = TryLoadMethod(minecraftForgeClient, "getItemRenderer", new Class[] {ItemStack.class, itemRenderType});
    private static final Class<?> iItemRenderer = TryLoadType("net.minecraftforge.client.IItemRenderer");
    private static final Method shouldUseRenderHelper = TryLoadMethod(iItemRenderer, "shouldUseRenderHelper", new Class[] {itemRenderType, ItemStack.class, itemRendererHelper});
    private static final Method getRenderPasses = TryLoadMethod(Item.class, "getRenderPasses", new Class[] {Integer.TYPE});
    private static final Constructor<?> renderPlayerEventSetArmorModelConstructor;
    private static final Field renderPlayerEventSetArmorModelResult;
    private static final Constructor<?> renderPlayerEventPreConstructor;
    private static final Constructor<?> renderPlayerEventPostConstructor;
    private static final Constructor<?> renderPlayerEventSpecialsPreConstructor;
    private static final Field renderPlayerEventSpecialsPreRenderHelmet;
    private static final Field renderPlayerEventSpecialsPreRenderCape;
    private static final Field renderPlayerEventSpecialsPreRenderItem;
    private static final Constructor<?> renderPlayerEventSpecialsPostConstructor;
    private static final Field eventBusField;
    private static final Method post;
    private static Field entityRenderMap;
    private static boolean initializedByForgeManager;
    private static Object currentRenderSpecialsPre;
    public final RenderPlayerAPI renderPlayerAPI = RenderPlayerAPI.create(this);

    public RenderPlayer()
    {
        super(new ModelPlayer(0.0F), 0.5F);
        RenderPlayerAPI.beforeLocalConstructing(this);
        this.modelBipedMain = (ModelBiped)super.mainModel;
        this.modelArmorChestplate = new ModelPlayer(1.0F);
        this.modelArmor = new ModelPlayer(0.5F);

        if (forgeHooksClient != null && Thread.currentThread().getStackTrace()[2].getClassName().equals(RenderManager.class.getName()))
        {
            initializedByForgeManager = true;
        }

        RenderPlayerAPI.afterLocalConstructing(this);
    }

    public final RenderPlayerBase getRenderPlayerBase(String var1)
    {
        return this.renderPlayerAPI != null ? this.renderPlayerAPI.getRenderPlayerBase(var1) : null;
    }

    public final Set<String> getRenderPlayerBaseIds(String var1)
    {
        return this.renderPlayerAPI != null ? this.renderPlayerAPI.getRenderPlayerBaseIds() : Collections.emptySet();
    }

    public Object dynamic(String var1, Object[] var2)
    {
        return this.renderPlayerAPI != null ? this.renderPlayerAPI.dynamic(var1, var2) : null;
    }

    public void doRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isDoRenderShadowAndFireModded)
        {
            RenderPlayerAPI.doRenderShadowAndFire(this, var1, var2, var4, var6, var8, var9);
        }
        else
        {
            super.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
        }
    }

    public final void superDoRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
    }

    public final void localDoRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
    }

    protected int getColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        int var4;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isGetColorMultiplierModded)
        {
            var4 = RenderPlayerAPI.getColorMultiplier(this, var1, var2, var3);
        }
        else
        {
            var4 = super.getColorMultiplier(var1, var2, var3);
        }

        return var4;
    }

    public final int realGetColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        return this.getColorMultiplier(var1, var2, var3);
    }

    public final int superGetColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        return super.getColorMultiplier(var1, var2, var3);
    }

    public final int localGetColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        return super.getColorMultiplier(var1, var2, var3);
    }

    protected float getDeathMaxRotation(EntityLiving var1)
    {
        float var2;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isGetDeathMaxRotationModded)
        {
            var2 = RenderPlayerAPI.getDeathMaxRotation(this, var1);
        }
        else
        {
            var2 = super.getDeathMaxRotation(var1);
        }

        return var2;
    }

    public final float realGetDeathMaxRotation(EntityLiving var1)
    {
        return this.getDeathMaxRotation(var1);
    }

    public final float superGetDeathMaxRotation(EntityLiving var1)
    {
        return super.getDeathMaxRotation(var1);
    }

    public final float localGetDeathMaxRotation(EntityLiving var1)
    {
        return super.getDeathMaxRotation(var1);
    }

    public FontRenderer getFontRendererFromRenderManager()
    {
        FontRenderer var1;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isGetFontRendererFromRenderManagerModded)
        {
            var1 = RenderPlayerAPI.getFontRendererFromRenderManager(this);
        }
        else
        {
            var1 = super.getFontRendererFromRenderManager();
        }

        return var1;
    }

    public final FontRenderer superGetFontRendererFromRenderManager()
    {
        return super.getFontRendererFromRenderManager();
    }

    public final FontRenderer localGetFontRendererFromRenderManager()
    {
        return super.getFontRendererFromRenderManager();
    }

    protected float handleRotationFloat(EntityLiving var1, float var2)
    {
        float var3;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isHandleRotationFloatModded)
        {
            var3 = RenderPlayerAPI.handleRotationFloat(this, var1, var2);
        }
        else
        {
            var3 = super.handleRotationFloat(var1, var2);
        }

        return var3;
    }

    public final float realHandleRotationFloat(EntityLiving var1, float var2)
    {
        return this.handleRotationFloat(var1, var2);
    }

    public final float superHandleRotationFloat(EntityLiving var1, float var2)
    {
        return super.handleRotationFloat(var1, var2);
    }

    public final float localHandleRotationFloat(EntityLiving var1, float var2)
    {
        return super.handleRotationFloat(var1, var2);
    }

    protected int inheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        int var4;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isInheritRenderPassModded)
        {
            var4 = RenderPlayerAPI.inheritRenderPass(this, var1, var2, var3);
        }
        else
        {
            var4 = super.inheritRenderPass(var1, var2, var3);
        }

        return var4;
    }

    public final int realInheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.inheritRenderPass(var1, var2, var3);
    }

    public final int superInheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        return super.inheritRenderPass(var1, var2, var3);
    }

    public final int localInheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        return super.inheritRenderPass(var1, var2, var3);
    }

    protected boolean loadDownloadableImageTexture(String var1, String var2)
    {
        boolean var3;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isLoadDownloadableImageTextureModded)
        {
            var3 = RenderPlayerAPI.loadDownloadableImageTexture(this, var1, var2);
        }
        else
        {
            var3 = super.loadDownloadableImageTexture(var1, var2);
        }

        return var3;
    }

    public final boolean realLoadDownloadableImageTexture(String var1, String var2)
    {
        return this.loadDownloadableImageTexture(var1, var2);
    }

    public final boolean superLoadDownloadableImageTexture(String var1, String var2)
    {
        return super.loadDownloadableImageTexture(var1, var2);
    }

    public final boolean localLoadDownloadableImageTexture(String var1, String var2)
    {
        return super.loadDownloadableImageTexture(var1, var2);
    }

    protected void func_98191_a(EntityPlayer var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isLoadPlayerTextureModded)
        {
            RenderPlayerAPI.loadPlayerTexture(this, var1);
        }
        else
        {
            this.localLoadPlayerTexture(var1);
        }
    }

    public final void realLoadPlayerTexture(EntityPlayer var1)
    {
        this.func_98191_a(var1);
    }

    public final void localLoadPlayerTexture(EntityPlayer var1)
    {
        this.loadDownloadableImageTexture(var1.skinUrl, var1.getTexture());
    }

    protected void loadTexture(String var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isLoadTextureModded)
        {
            RenderPlayerAPI.loadTexture(this, var1);
        }
        else
        {
            super.loadTexture(var1);
        }
    }

    public final void realLoadTexture(String var1)
    {
        this.loadTexture(var1);
    }

    public final void superLoadTexture(String var1)
    {
        super.loadTexture(var1);
    }

    public final void localLoadTexture(String var1)
    {
        super.loadTexture(var1);
    }

    protected void passSpecialRender(EntityLiving var1, double var2, double var4, double var6)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isPassSpecialRenderModded)
        {
            RenderPlayerAPI.passSpecialRender(this, var1, var2, var4, var6);
        }
        else
        {
            super.passSpecialRender(var1, var2, var4, var6);
        }
    }

    public final void realPassSpecialRender(EntityLiving var1, double var2, double var4, double var6)
    {
        this.passSpecialRender(var1, var2, var4, var6);
    }

    public final void superPassSpecialRender(EntityLiving var1, double var2, double var4, double var6)
    {
        super.passSpecialRender(var1, var2, var4, var6);
    }

    public final void localPassSpecialRender(EntityLiving var1, double var2, double var4, double var6)
    {
        super.passSpecialRender(var1, var2, var4, var6);
    }

    protected void renderArrowsStuckInEntity(EntityLiving var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderArrowsStuckInEntityModded)
        {
            RenderPlayerAPI.renderArrowsStuckInEntity(this, var1, var2);
        }
        else
        {
            super.renderArrowsStuckInEntity(var1, var2);
        }
    }

    public final void realRenderArrowsStuckInEntity(EntityLiving var1, float var2)
    {
        this.renderArrowsStuckInEntity(var1, var2);
    }

    public final void superRenderArrowsStuckInEntity(EntityLiving var1, float var2)
    {
        super.renderArrowsStuckInEntity(var1, var2);
    }

    public final void localRenderArrowsStuckInEntity(EntityLiving var1, float var2)
    {
        super.renderArrowsStuckInEntity(var1, var2);
    }

    public void renderFirstPersonArm(EntityPlayer var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderFirstPersonArmModded)
        {
            RenderPlayerAPI.renderFirstPersonArm(this, var1);
        }
        else
        {
            this.localRenderFirstPersonArm(var1);
        }
    }

    public final void localRenderFirstPersonArm(EntityPlayer var1)
    {
        float var2 = 1.0F;
        GL11.glColor3f(var2, var2, var2);
        this.modelBipedMain.onGround = 0.0F;
        this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var1);
        this.modelBipedMain.bipedRightArm.render(0.0625F);
    }

    protected void renderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderLivingLabelModded)
        {
            RenderPlayerAPI.renderLivingLabel(this, var1, var2, var3, var5, var7, var9);
        }
        else
        {
            super.renderLivingLabel(var1, var2, var3, var5, var7, var9);
        }
    }

    public final void realRenderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        this.renderLivingLabel(var1, var2, var3, var5, var7, var9);
    }

    public final void superRenderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        super.renderLivingLabel(var1, var2, var3, var5, var7, var9);
    }

    public final void localRenderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        super.renderLivingLabel(var1, var2, var3, var5, var7, var9);
    }

    protected void renderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderModelModded)
        {
            RenderPlayerAPI.renderModel(this, var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            super.renderModel(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public final void realRenderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        this.renderModel(var1, var2, var3, var4, var5, var6, var7);
    }

    public final void superRenderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        super.renderModel(var1, var2, var3, var4, var5, var6, var7);
    }

    public final void localRenderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        super.renderModel(var1, var2, var3, var4, var5, var6, var7);
    }

    public void renderPlayer(EntityPlayer var1, double var2, double var4, double var6, float var8, float var9)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderPlayerModded)
        {
            RenderPlayerAPI.renderPlayer(this, var1, var2, var4, var6, var8, var9);
        }
        else
        {
            this.localRenderPlayer(var1, var2, var4, var6, var8, var9);
        }
    }

    public final void localRenderPlayer(EntityPlayer var1, double var2, double var4, double var6, float var8, float var9)
    {
        if (!OnPreRenderPlayer(this, var1))
        {
            float var10 = 1.0F;
            GL11.glColor3f(var10, var10, var10);
            ItemStack var11 = var1.inventory.getCurrentItem();
            this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = var11 != null ? 1 : 0;

            if (var11 != null && var1.getItemInUseCount() > 0)
            {
                EnumAction var12 = var11.getItemUseAction();

                if (var12 == EnumAction.block)
                {
                    this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 3;
                }
                else if (var12 == EnumAction.bow)
                {
                    this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = true;
                }
            }

            this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = var1.isSneaking();
            double var14 = var4 - (double)var1.yOffset;

            if (var1.isSneaking() && !(var1 instanceof EntityPlayerSP))
            {
                var14 -= 0.125D;
            }

            super.doRenderLiving(var1, var2, var14, var6, var8, var9);
            this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = false;
            this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = false;
            this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 0;
            OnPostRenderPlayer(this, var1);
        }
    }

    protected void func_96450_a(EntityPlayer var1, double var2, double var4, double var6, String var8, float var9, double var10)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderPlayerNameAndScoreLabelModded)
        {
            RenderPlayerAPI.renderPlayerNameAndScoreLabel(this, var1, var2, var4, var6, var8, var9, var10);
        }
        else
        {
            this.localRenderPlayerNameAndScoreLabel(var1, var2, var4, var6, var8, var9, var10);
        }
    }

    public final void realRenderPlayerNameAndScoreLabel(EntityPlayer var1, double var2, double var4, double var6, String var8, float var9, double var10)
    {
        this.func_96450_a(var1, var2, var4, var6, var8, var9, var10);
    }

    public final void localRenderPlayerNameAndScoreLabel(EntityPlayer var1, double var2, double var4, double var6, String var8, float var9, double var10)
    {
        if (var10 < 100.0D)
        {
            Scoreboard var12 = var1.getWorldScoreboard();
            ScoreObjective var13 = var12.func_96539_a(2);

            if (var13 != null)
            {
                Score var14 = var12.func_96529_a(var1.getEntityName(), var13);

                if (var1.isPlayerSleeping())
                {
                    this.renderLivingLabel(var1, var14.func_96652_c() + " " + var13.getDisplayName(), var2, var4 - 1.5D, var6, 64);
                }
                else
                {
                    this.renderLivingLabel(var1, var14.func_96652_c() + " " + var13.getDisplayName(), var2, var4, var6, 64);
                }

                var4 += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * var9);
            }
        }

        super.func_96449_a(var1, var2, var4, var6, var8, var9, var10);
    }

    protected void renderPlayerScale(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderPlayerScaleModded)
        {
            RenderPlayerAPI.renderPlayerScale(this, var1, var2);
        }
        else
        {
            this.localRenderPlayerScale(var1, var2);
        }
    }

    public final void realRenderPlayerScale(EntityPlayer var1, float var2)
    {
        this.renderPlayerScale(var1, var2);
    }

    public final void localRenderPlayerScale(EntityPlayer var1, float var2)
    {
        float var3 = 0.9375F;
        GL11.glScalef(var3, var3, var3);
    }

    protected void renderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderPlayerSleepModded)
        {
            RenderPlayerAPI.renderPlayerSleep(this, var1, var2, var4, var6);
        }
        else
        {
            this.localRenderPlayerSleep(var1, var2, var4, var6);
        }
    }

    public final void realRenderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6)
    {
        this.renderPlayerSleep(var1, var2, var4, var6);
    }

    public final void localRenderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6)
    {
        if (var1.isEntityAlive() && var1.isPlayerSleeping())
        {
            super.renderLivingAt(var1, var2 + (double)var1.field_71079_bU, var4 + (double)var1.field_71082_cx, var6 + (double)var1.field_71089_bV);
        }
        else
        {
            super.renderLivingAt(var1, var2, var4, var6);
        }
    }

    protected void renderSpecials(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialsModded)
        {
            RenderPlayerAPI.renderSpecials(this, var1, var2);
        }
        else
        {
            this.localRenderSpecials(var1, var2);
        }
    }

    public final void realRenderSpecials(EntityPlayer var1, float var2)
    {
        this.renderSpecials(var1, var2);
    }

    public final void localRenderSpecials(EntityPlayer var1, float var2)
    {
        if (!OnPreRenderSpecials(this, var1, var2))
        {
            float var3 = 1.0F;
            GL11.glColor3f(var3, var3, var3);
            super.renderEquippedItems(var1, var2);
            this.renderArrowsStuckInEntity(var1, var2);
            this.renderSpecialHeadArmor(var1, var2);
            this.renderSpecialHeadEars(var1, var2);
            this.renderSpecialCloak(var1, var2);
            this.renderSpecialItemInHand(var1, var2);
            OnPostRenderSpecials(this, var1, var2);
        }
    }

    protected float renderSwingProgress(EntityLiving var1, float var2)
    {
        float var3;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSwingProgressModded)
        {
            var3 = RenderPlayerAPI.renderSwingProgress(this, var1, var2);
        }
        else
        {
            var3 = super.renderSwingProgress(var1, var2);
        }

        return var3;
    }

    public final float realRenderSwingProgress(EntityLiving var1, float var2)
    {
        return this.renderSwingProgress(var1, var2);
    }

    public final float superRenderSwingProgress(EntityLiving var1, float var2)
    {
        return super.renderSwingProgress(var1, var2);
    }

    public final float localRenderSwingProgress(EntityLiving var1, float var2)
    {
        return super.renderSwingProgress(var1, var2);
    }

    protected void rotatePlayer(EntityPlayer var1, float var2, float var3, float var4)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRotatePlayerModded)
        {
            RenderPlayerAPI.rotatePlayer(this, var1, var2, var3, var4);
        }
        else
        {
            this.localRotatePlayer(var1, var2, var3, var4);
        }
    }

    public final void realRotatePlayer(EntityPlayer var1, float var2, float var3, float var4)
    {
        this.rotatePlayer(var1, var2, var3, var4);
    }

    public final void localRotatePlayer(EntityPlayer var1, float var2, float var3, float var4)
    {
        if (var1.isEntityAlive() && var1.isPlayerSleeping())
        {
            GL11.glRotatef(var1.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.getDeathMaxRotation(var1), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        }
        else
        {
            super.rotateCorpse(var1, var2, var3, var4);
        }
    }

    protected int setArmorModel(EntityPlayer var1, int var2, float var3)
    {
        int var4;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetArmorModelModded)
        {
            var4 = RenderPlayerAPI.setArmorModel(this, var1, var2, var3);
        }
        else
        {
            var4 = this.localSetArmorModel(var1, var2, var3);
        }

        return var4;
    }

    public final int realSetArmorModel(EntityPlayer var1, int var2, float var3)
    {
        return this.setArmorModel(var1, var2, var3);
    }

    public final int localSetArmorModel(EntityPlayer var1, int var2, float var3)
    {
        ItemStack var4 = var1.inventory.armorItemInSlot(3 - var2);
        int var5 = OnPreSetArmorModel(this, var1, var2, var3, var4);

        if (var5 != -1)
        {
            return var5;
        }
        else
        {
            if (var4 != null)
            {
                Item var6 = var4.getItem();

                if (var6 instanceof ItemArmor)
                {
                    ItemArmor var7 = (ItemArmor)var6;
                    this.loadTexture(GetArmorTexture(var1, var4, "/armor/" + armorFilenamePrefix[var7.renderIndex] + "_" + (var2 == 2 ? 2 : 1) + ".png", var2, 1));
                    ModelBiped var8 = var2 == 2 ? this.modelArmor : this.modelArmorChestplate;
                    var8.bipedHead.showModel = var2 == 0;
                    var8.bipedHeadwear.showModel = var2 == 0;
                    var8.bipedBody.showModel = var2 == 1 || var2 == 2;
                    var8.bipedRightArm.showModel = var2 == 1;
                    var8.bipedLeftArm.showModel = var2 == 1;
                    var8.bipedRightLeg.showModel = var2 == 2 || var2 == 3;
                    var8.bipedLeftLeg.showModel = var2 == 2 || var2 == 3;
                    var8 = GetArmorModel(var1, var4, var2, var8);
                    this.setRenderPassModel(var8);

                    if (var8 != null)
                    {
                        var8.onGround = super.mainModel.onGround;
                    }

                    if (var8 != null)
                    {
                        var8.isRiding = super.mainModel.isRiding;
                    }

                    if (var8 != null)
                    {
                        var8.isChild = super.mainModel.isChild;
                    }

                    float var9 = 1.0F;
                    Integer var10 = GetArmorColor(var7, var4);

                    if (var10 != null)
                    {
                        int var11 = var10.intValue();
                        float var12 = (float)(var11 >> 16 & 255) / 255.0F;
                        float var13 = (float)(var11 >> 8 & 255) / 255.0F;
                        float var14 = (float)(var11 & 255) / 255.0F;
                        GL11.glColor3f(var9 * var12, var9 * var13, var9 * var14);

                        if (var4.isItemEnchanted())
                        {
                            return 31;
                        }

                        return 16;
                    }

                    GL11.glColor3f(var9, var9, var9);

                    if (var4.isItemEnchanted())
                    {
                        return 15;
                    }

                    return 1;
                }
            }

            return -1;
        }
    }

    public void updateIcons(IconRegister var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetIconRegisterModded)
        {
            RenderPlayerAPI.setIconRegister(this, var1);
        }
        else
        {
            super.updateIcons(var1);
        }
    }

    public final void superSetIconRegister(IconRegister var1)
    {
        super.updateIcons(var1);
    }

    public final void localSetIconRegister(IconRegister var1)
    {
        super.updateIcons(var1);
    }

    protected void func_82439_b(EntityPlayer var1, int var2, float var3)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetPassArmorModelModded)
        {
            RenderPlayerAPI.setPassArmorModel(this, var1, var2, var3);
        }
        else
        {
            this.localSetPassArmorModel(var1, var2, var3);
        }
    }

    public final void realSetPassArmorModel(EntityPlayer var1, int var2, float var3)
    {
        this.func_82439_b(var1, var2, var3);
    }

    public final void localSetPassArmorModel(EntityPlayer var1, int var2, float var3)
    {
        ItemStack var4 = var1.inventory.armorItemInSlot(3 - var2);

        if (var4 != null)
        {
            Item var5 = var4.getItem();

            if (var5 instanceof ItemArmor)
            {
                ItemArmor var6 = (ItemArmor)var5;
                this.loadTexture(GetArmorTexture(var1, var4, "/armor/" + armorFilenamePrefix[var6.renderIndex] + "_" + (var2 == 2 ? 2 : 1) + "_b.png", var2, 2));
                float var7 = 1.0F;
                GL11.glColor3f(var7, var7, var7);
            }
        }
    }

    public void setRenderManager(RenderManager var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetRenderManagerModded)
        {
            RenderPlayerAPI.setRenderManager(this, var1);
        }
        else
        {
            super.setRenderManager(var1);
        }
    }

    public final void superSetRenderManager(RenderManager var1)
    {
        super.setRenderManager(var1);
    }

    public final void localSetRenderManager(RenderManager var1)
    {
        super.setRenderManager(var1);
    }

    public void setRenderPassModel(ModelBase var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetRenderPassModelModded)
        {
            RenderPlayerAPI.setRenderPassModel(this, var1);
        }
        else
        {
            super.setRenderPassModel(var1);
        }
    }

    public final void superSetRenderPassModel(ModelBase var1)
    {
        super.setRenderPassModel(var1);
    }

    public final void localSetRenderPassModel(ModelBase var1)
    {
        super.setRenderPassModel(var1);
    }

    protected void renderSpecialHeadArmor(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialHeadArmorModded)
        {
            RenderPlayerAPI.renderSpecialHeadArmor(this, var1, var2);
        }
        else
        {
            this.localRenderSpecialHeadArmor(var1, var2);
        }
    }

    public final void realRenderSpecialHeadArmor(EntityPlayer var1, float var2)
    {
        this.renderSpecialHeadArmor(var1, var2);
    }

    public final void localRenderSpecialHeadArmor(EntityPlayer var1, float var2)
    {
        ItemStack var3 = var1.inventory.armorItemInSlot(3);

        if (var3 != null && RenderHelmet())
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);
            float var4;

            if (IsHeadItemBlock(var3))
            {
                if (IsForge3D(var3) || RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType()))
                {
                    var4 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(var4, -var4, -var4);
                }

                super.renderManager.itemRenderer.renderItem(var1, var3, 0);
            }
            else if (var3.getItem().itemID == Item.skull.itemID)
            {
                var4 = 1.0625F;
                GL11.glScalef(var4, -var4, -var4);
                String var5 = "";

                if (var3.hasTagCompound() && var3.getTagCompound().hasKey("SkullOwner"))
                {
                    var5 = var3.getTagCompound().getString("SkullOwner");
                }

                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var3.getItemDamage(), var5);
            }

            GL11.glPopMatrix();
        }
    }

    protected void renderSpecialHeadEars(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialHeadEarsModded)
        {
            RenderPlayerAPI.renderSpecialHeadEars(this, var1, var2);
        }
        else
        {
            this.localRenderSpecialHeadEars(var1, var2);
        }
    }

    public final void realRenderSpecialHeadEars(EntityPlayer var1, float var2)
    {
        this.renderSpecialHeadEars(var1, var2);
    }

    public final void localRenderSpecialHeadEars(EntityPlayer var1, float var2)
    {
        if (var1.username.equals("deadmau5") && this.loadDownloadableImageTexture(var1.skinUrl, (String)null))
        {
            for (int var3 = 0; var3 < 2; ++var3)
            {
                float var4 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2 - (var1.prevRenderYawOffset + (var1.renderYawOffset - var1.prevRenderYawOffset) * var2);
                float var5 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var2;
                GL11.glPushMatrix();
                GL11.glRotatef(var4, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(var5, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float)(var3 * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-var5, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-var4, 0.0F, 1.0F, 0.0F);
                float var6 = 1.333333F;
                GL11.glScalef(var6, var6, var6);
                this.modelBipedMain.renderEars(0.0625F);
                GL11.glPopMatrix();
            }
        }
    }

    protected void renderSpecialCloak(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialCloakModded)
        {
            RenderPlayerAPI.renderSpecialCloak(this, var1, var2);
        }
        else
        {
            this.localRenderSpecialCloak(var1, var2);
        }
    }

    public final void realRenderSpecialCloak(EntityPlayer var1, float var2)
    {
        this.renderSpecialCloak(var1, var2);
    }

    public final void localRenderSpecialCloak(EntityPlayer var1, float var2)
    {
        if (RenderCape() && this.loadDownloadableImageTexture(var1.cloakUrl, (String)null) && !var1.isInvisible() && !var1.getHideCape())
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double var4 = var1.field_71091_bM + (var1.field_71094_bP - var1.field_71091_bM) * (double)var2 - (var1.prevPosX + (var1.posX - var1.prevPosX) * (double)var2);
            double var6 = var1.field_71096_bN + (var1.field_71095_bQ - var1.field_71096_bN) * (double)var2 - (var1.prevPosY + (var1.posY - var1.prevPosY) * (double)var2);
            double var8 = var1.field_71097_bO + (var1.field_71085_bR - var1.field_71097_bO) * (double)var2 - (var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double)var2);
            float var3 = var1.prevRenderYawOffset + (var1.renderYawOffset - var1.prevRenderYawOffset) * var2;
            double var10 = (double)MathHelper.sin(var3 * (float)Math.PI / 180.0F);
            double var12 = (double)(-MathHelper.cos(var3 * (float)Math.PI / 180.0F));
            float var14 = (float)var6 * 10.0F;

            if (var14 < -6.0F)
            {
                var14 = -6.0F;
            }

            if (var14 > 32.0F)
            {
                var14 = 32.0F;
            }

            float var15 = (float)(var4 * var10 + var8 * var12) * 100.0F;
            float var16 = (float)(var4 * var12 - var8 * var10) * 100.0F;

            if (var15 < 0.0F)
            {
                var15 = 0.0F;
            }

            float var17 = var1.prevCameraYaw + (var1.cameraYaw - var1.prevCameraYaw) * var2;
            var14 += MathHelper.sin((var1.prevDistanceWalkedModified + (var1.distanceWalkedModified - var1.prevDistanceWalkedModified) * var2) * 6.0F) * 32.0F * var17;

            if (var1.isSneaking())
            {
                var14 += 25.0F;
            }

            GL11.glRotatef(6.0F + var15 / 2.0F + var14, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var16 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-var16 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            this.modelBipedMain.renderCloak(0.0625F);
            GL11.glPopMatrix();
        }
    }

    protected void renderSpecialItemInHand(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialItemInHandModded)
        {
            RenderPlayerAPI.renderSpecialItemInHand(this, var1, var2);
        }
        else
        {
            this.localRenderSpecialItemInHand(var1, var2);
        }
    }

    public final void realRenderSpecialItemInHand(EntityPlayer var1, float var2)
    {
        this.renderSpecialItemInHand(var1, var2);
    }

    public final void localRenderSpecialItemInHand(EntityPlayer var1, float var2)
    {
        ItemStack var3 = var1.inventory.getCurrentItem();

        if (var3 != null && RenderItem())
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            if (var1.fishEntity != null)
            {
                var3 = new ItemStack(Item.stick);
            }

            EnumAction var4 = null;

            if (var1.getItemInUseCount() > 0)
            {
                var4 = var3.getItemUseAction();
            }

            this.positionSpecialItemInHand(var1, var2, var4, var3);
            int var5;
            float var7;
            float var8;

            if (var3.getItem().requiresMultipleRenderPasses())
            {
                for (var5 = 0; var5 <= GetRenderPasses(var3); ++var5)
                {
                    int var6 = var3.getItem().getColorFromItemStack(var3, var5);
                    var7 = (float)(var6 >> 16 & 255) / 255.0F;
                    var8 = (float)(var6 >> 8 & 255) / 255.0F;
                    float var9 = (float)(var6 & 255) / 255.0F;
                    GL11.glColor4f(var7, var8, var9, 1.0F);
                    super.renderManager.itemRenderer.renderItem(var1, var3, var5);
                }
            }
            else
            {
                var5 = var3.getItem().getColorFromItemStack(var3, 0);
                float var10 = (float)(var5 >> 16 & 255) / 255.0F;
                var7 = (float)(var5 >> 8 & 255) / 255.0F;
                var8 = (float)(var5 & 255) / 255.0F;
                GL11.glColor4f(var10, var7, var8, 1.0F);
                super.renderManager.itemRenderer.renderItem(var1, var3, 0);
            }

            GL11.glPopMatrix();
        }
    }

    protected void positionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isPositionSpecialItemInHandModded)
        {
            RenderPlayerAPI.positionSpecialItemInHand(this, var1, var2, var3, var4);
        }
        else
        {
            this.localPositionSpecialItemInHand(var1, var2, var3, var4);
        }
    }

    public final void realPositionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        this.positionSpecialItemInHand(var1, var2, var3, var4);
    }

    public final void localPositionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        float var5;

        if (DrawItemBlockInHand(var1, var2, var3, var4))
        {
            var5 = 0.5F;
            GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
            var5 *= 0.75F;
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(-var5, -var5, var5);
        }
        else if (var4.itemID == Item.bow.itemID)
        {
            var5 = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var5, -var5, var5);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
        else if (Item.itemsList[var4.itemID].isFull3D())
        {
            var5 = 0.625F;

            if (Item.itemsList[var4.itemID].shouldRotateAroundWhenRendering())
            {
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            if (var1.getItemInUseCount() > 0 && var3 == EnumAction.block)
            {
                GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
            }

            GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
            GL11.glScalef(var5, -var5, var5);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
        else
        {
            var5 = 0.375F;
            GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
            GL11.glScalef(var5, var5, var5);
            GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
        }
    }

    private static boolean RenderCape()
    {
        if (renderPlayerEventSpecialsPreRenderCape != null && currentRenderSpecialsPre != null)
        {
            try
            {
                return ((Boolean)renderPlayerEventSpecialsPreRenderCape.get(currentRenderSpecialsPre)).booleanValue();
            }
            catch (Throwable var1)
            {
                return true;
            }
        }
        else
        {
            return true;
        }
    }

    public final boolean realRenderCape()
    {
        return RenderCape();
    }

    private static boolean RenderHelmet()
    {
        if (renderPlayerEventSpecialsPreRenderHelmet != null && currentRenderSpecialsPre != null)
        {
            try
            {
                return ((Boolean)renderPlayerEventSpecialsPreRenderHelmet.get(currentRenderSpecialsPre)).booleanValue();
            }
            catch (Throwable var1)
            {
                return true;
            }
        }
        else
        {
            return true;
        }
    }

    public final boolean realRenderHelmet()
    {
        return RenderHelmet();
    }

    private static boolean RenderItem()
    {
        if (renderPlayerEventSpecialsPreRenderItem != null && currentRenderSpecialsPre != null)
        {
            try
            {
                return ((Boolean)renderPlayerEventSpecialsPreRenderItem.get(currentRenderSpecialsPre)).booleanValue();
            }
            catch (Throwable var1)
            {
                return true;
            }
        }
        else
        {
            return true;
        }
    }

    public final boolean realRenderItem()
    {
        return RenderItem();
    }

    protected void func_82408_c(EntityLiving var1, int var2, float var3)
    {
        this.func_82439_b((EntityPlayer)var1, var2, var3);
    }

    public final void realSetPassModel(EntityLiving var1, int var2, float var3)
    {
        this.func_82408_c(var1, var2, var3);
    }

    public final void superSetPassModel(EntityLiving var1, int var2, float var3)
    {
        super.func_82408_c(var1, var2, var3);
    }

    protected void renderEquippedItems(EntityLiving var1, float var2)
    {
        this.renderSpecials((EntityPlayer)var1, var2);
    }

    public final void realRenderEquippedItems(EntityLiving var1, float var2)
    {
        this.renderEquippedItems(var1, var2);
    }

    public final void superRenderEquippedItems(EntityLiving var1, float var2)
    {
        super.renderEquippedItems(var1, var2);
    }

    public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.renderPlayer((EntityPlayer)var1, var2, var4, var6, var8, var9);
    }

    public final void superDoRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRenderLiving(var1, var2, var4, var6, var8, var9);
    }

    protected void renderLivingAt(EntityLiving var1, double var2, double var4, double var6)
    {
        this.renderPlayerSleep((EntityPlayer)var1, var2, var4, var6);
    }

    public final void realRenderLivingAt(EntityLiving var1, double var2, double var4, double var6)
    {
        this.renderLivingAt(var1, var2, var4, var6);
    }

    public final void superRenderLivingAt(EntityLiving var1, double var2, double var4, double var6)
    {
        super.renderLivingAt(var1, var2, var4, var6);
    }

    public void func_98190_a(EntityLiving var1)
    {
        this.func_98191_a((EntityPlayer)var1);
    }

    public final void superLoadLivingTexture(EntityLiving var1)
    {
        super.func_98190_a(var1);
    }

    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.renderPlayer((EntityPlayer)var1, var2, var4, var6, var8, var9);
    }

    protected void func_96449_a(EntityLiving var1, double var2, double var4, double var6, String var8, float var9, double var10)
    {
        this.func_96450_a((EntityPlayer)var1, var2, var4, var6, var8, var9, var10);
    }

    public final void realRenderLivingNonSneakingLabel(EntityLiving var1, double var2, double var4, double var6, String var8, float var9, double var10)
    {
        this.func_96449_a(var1, var2, var4, var6, var8, var9, var10);
    }

    public final void superRenderLivingNonSneakingLabel(EntityLiving var1, double var2, double var4, double var6, String var8, float var9, double var10)
    {
        super.func_96449_a(var1, var2, var4, var6, var8, var9, var10);
    }

    protected void preRenderCallback(EntityLiving var1, float var2)
    {
        this.renderPlayerScale((EntityPlayer)var1, var2);
    }

    public final void realPreRenderCallback(EntityLiving var1, float var2)
    {
        this.preRenderCallback(var1, var2);
    }

    public final void superPreRenderCallback(EntityLiving var1, float var2)
    {
        super.preRenderCallback(var1, var2);
    }

    protected int shouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.setArmorModel((EntityPlayer)var1, var2, var3);
    }

    public final int realShouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.shouldRenderPass(var1, var2, var3);
    }

    public final int superShouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return super.shouldRenderPass(var1, var2, var3);
    }

    protected void rotateCorpse(EntityLiving var1, float var2, float var3, float var4)
    {
        this.rotatePlayer((EntityPlayer)var1, var2, var3, var4);
    }

    public final void realRotateCorpse(EntityLiving var1, float var2, float var3, float var4)
    {
        this.rotateCorpse(var1, var2, var3, var4);
    }

    public final void superRotateCorpse(EntityLiving var1, float var2, float var3, float var4)
    {
        super.rotateCorpse(var1, var2, var3, var4);
    }

    private static Class<?> TryLoadType(String var0)
    {
        ClassLoader var1 = RenderPlayer.class.getClassLoader();

        try
        {
            return var1.loadClass(var0);
        }
        catch (ClassNotFoundException var3)
        {
            return null;
        }
    }

    private static Method TryLoadMethod(Class<?> var0, String var1, Class<?> ... var2)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getDeclaredMethod(var1, var2);
            }
            catch (NoSuchMethodException var4)
            {
                return null;
            }
        }
    }

    private static Object TryLoadEnum(Class<?> var0, String var1)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getField(var1).get((Object)null);
            }
            catch (IllegalAccessException var3)
            {
                return null;
            }
            catch (NoSuchFieldException var4)
            {
                return null;
            }
        }
    }

    private static Constructor TryLoadConstructor(Class<?> var0, Class<?> ... var1)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getConstructor(var1);
            }
            catch (NoSuchMethodException var3)
            {
                return null;
            }
        }
    }

    private static Field TryLoadField(Class<?> var0, String var1)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getDeclaredField(var1);
            }
            catch (NoSuchFieldException var3)
            {
                return null;
            }
        }
    }

    private static int OnPreSetArmorModel(RenderPlayer var0, EntityPlayer var1, int var2, float var3, ItemStack var4)
    {
        if (renderPlayerEventSetArmorModelConstructor != null && renderPlayerEventSetArmorModelResult != null && eventBusField != null && post != null)
        {
            try
            {
                Object var5 = renderPlayerEventSetArmorModelConstructor.newInstance(new Object[] {var1, var0, Integer.valueOf(3 - var2), Float.valueOf(var3), var4});
                post.invoke(eventBusField.get((Object)null), new Object[] {var5});
                return ((Integer)renderPlayerEventSetArmorModelResult.get(var5)).intValue();
            }
            catch (Throwable var6)
            {
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

    private static String GetArmorTexture(Entity var0, ItemStack var1, String var2, int var3, int var4)
    {
        if (getArmorTexture == null)
        {
            return var2;
        }
        else
        {
            try
            {
                return (String)getArmorTexture.invoke((Object)null, new Object[] {var0, var1, var2, Integer.valueOf(var3), Integer.valueOf(var4)});
            }
            catch (Exception var6)
            {
                throw new RuntimeException(getArmorTexture.getName(), var6);
            }
        }
    }

    private static ModelBiped GetArmorModel(EntityLiving var0, ItemStack var1, int var2, ModelBiped var3)
    {
        if (getArmorModel == null)
        {
            return var3;
        }
        else
        {
            try
            {
                return (ModelBiped)getArmorModel.invoke((Object)null, new Object[] {var0, var1, Integer.valueOf(var2), var3});
            }
            catch (Exception var5)
            {
                throw new RuntimeException(getArmorModel.getName(), var5);
            }
        }
    }

    private static Integer GetArmorColor(ItemArmor var0, ItemStack var1)
    {
        if (minecraftForgeClient != null)
        {
            int var2 = var0.getColor(var1);
            return var2 != -1 ? Integer.valueOf(var2) : null;
        }
        else
        {
            return var0.getArmorMaterial() == EnumArmorMaterial.CLOTH ? Integer.valueOf(var0.getColor(var1)) : null;
        }
    }

    private static boolean OnPreRenderPlayer(RenderPlayer var0, EntityPlayer var1)
    {
        if (renderPlayerEventPreConstructor != null && eventBusField != null && post != null)
        {
            try
            {
                Object var2 = renderPlayerEventPreConstructor.newInstance(new Object[] {var1, var0});
                return ((Boolean)post.invoke(eventBusField.get((Object)null), new Object[] {var2})).booleanValue();
            }
            catch (Throwable var3)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private static void OnPostRenderPlayer(RenderPlayer var0, EntityPlayer var1)
    {
        if (renderPlayerEventPostConstructor != null && eventBusField != null && post != null)
        {
            try
            {
                Object var2 = renderPlayerEventPostConstructor.newInstance(new Object[] {var0, var1});
                post.invoke(eventBusField.get((Object)null), new Object[] {var2});
            }
            catch (Throwable var3)
            {
                ;
            }
        }
    }

    private static boolean OnPreRenderSpecials(RenderPlayer var0, EntityPlayer var1, float var2)
    {
        if (renderPlayerEventSpecialsPreConstructor != null && eventBusField != null && post != null)
        {
            try
            {
                currentRenderSpecialsPre = renderPlayerEventSpecialsPreConstructor.newInstance(new Object[] {var1, var0, Float.valueOf(var2)});
                return ((Boolean)post.invoke(eventBusField.get((Object)null), new Object[] {currentRenderSpecialsPre})).booleanValue();
            }
            catch (Throwable var4)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private static void OnPostRenderSpecials(RenderPlayer var0, EntityPlayer var1, float var2)
    {
        currentRenderSpecialsPre = null;

        if (renderPlayerEventSpecialsPostConstructor != null && eventBusField != null && post != null)
        {
            try
            {
                Object var3 = renderPlayerEventSpecialsPostConstructor.newInstance(new Object[] {var0, var1, Float.valueOf(var2)});
                post.invoke(eventBusField.get((Object)null), new Object[] {var3});
            }
            catch (Throwable var4)
            {
                ;
            }
        }
    }

    private static boolean IsHeadItemBlock(ItemStack var0)
    {
        return getItemRenderer != null && shouldUseRenderHelper != null ? var0 != null && var0.getItem() instanceof ItemBlock : var0.getItem().itemID < 256;
    }

    private static boolean IsForge3D(ItemStack var0)
    {
        if (getItemRenderer != null && shouldUseRenderHelper != null)
        {
            try
            {
                Object var1 = getItemRenderer.invoke((Object)null, new Object[] {var0, equipped});
                return var1 != null && ((Boolean)shouldUseRenderHelper.invoke(var1, new Object[] {equipped, var0, block3D})).booleanValue();
            }
            catch (Throwable var2)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private static int GetRenderPasses(ItemStack var0)
    {
        if (getRenderPasses == null)
        {
            return 1;
        }
        else
        {
            try
            {
                return ((Integer)getRenderPasses.invoke(var0.getItem(), new Object[] {Integer.valueOf(var0.getItemDamage())})).intValue() - 1;
            }
            catch (Throwable var2)
            {
                return 1;
            }
        }
    }

    private static boolean DrawItemBlockInHand(EntityPlayer var0, float var1, EnumAction var2, ItemStack var3)
    {
        return minecraftForgeClient != null ? IsForge3D(var3) || var3.itemID < Block.blocksList.length && var3.getItemSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType()) : var3.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType());
    }

    public static int addNewArmourPrefix(String var0)
    {
        ArrayList var1 = new ArrayList(Arrays.asList(armorFilenamePrefix));
        var1.add(var0);
        armorFilenamePrefix = (String[])((String[])var1.toArray(new String[0]));
        return var1.indexOf(var0);
    }

    public static RenderPlayer[] getAllInstances()
    {
        if (forgeHooksClient != null && !initializedByForgeManager)
        {
            return new RenderPlayer[0];
        }
        else
        {
            RenderManager var0 = RenderManager.instance;

            if (var0 == null)
            {
                return new RenderPlayer[0];
            }
            else
            {
                if (entityRenderMap == null)
                {
                    entityRenderMap = TryLoadField(RenderManager.class, "q");

                    if (entityRenderMap == null)
                    {
                        entityRenderMap = TryLoadField(RenderManager.class, "field_78729_o");
                    }

                    if (entityRenderMap == null)
                    {
                        entityRenderMap = TryLoadField(RenderManager.class, "entityRenderMap");
                    }

                    if (entityRenderMap != null)
                    {
                        entityRenderMap.setAccessible(true);
                    }
                }

                if (entityRenderMap == null)
                {
                    throw new RuntimeException("Can not find field \"entityRenderMap\" (ofuscated \"q\") in class \"" + RenderManager.class.getName() + "\"");
                }
                else
                {
                    Map var1;

                    try
                    {
                        var1 = (Map)entityRenderMap.get(var0);
                    }
                    catch (Exception var7)
                    {
                        throw new RuntimeException("Unable to get value of field \"entityRenderMap\" (ofuscated \"q\") in class \"" + RenderManager.class.getName() + "\"", var7);
                    }

                    if (var1 == null)
                    {
                        return new RenderPlayer[0];
                    }
                    else
                    {
                        int var2 = 0;
                        Collection var3 = var1.values();
                        Iterator var4 = var3.iterator();

                        while (var4.hasNext())
                        {
                            Object var5 = var4.next();

                            if (var5 instanceof RenderPlayer)
                            {
                                ++var2;
                            }
                        }

                        RenderPlayer[] var8 = new RenderPlayer[var2];
                        var4 = var3.iterator();

                        while (var4.hasNext())
                        {
                            Object var6 = var4.next();

                            if (var6 instanceof RenderPlayer)
                            {
                                --var2;
                                var8[var2] = (RenderPlayer)var6;
                            }
                        }

                        return var8;
                    }
                }
            }
        }
    }

    public ModelPlayer[] getAllModels()
    {
        int var1 = (this.modelBipedMain instanceof ModelPlayer ? 1 : 0) + (this.modelArmorChestplate instanceof ModelPlayer ? 1 : 0) + (this.modelArmor instanceof ModelPlayer ? 1 : 0);
        ModelPlayer[] var2 = new ModelPlayer[var1];

        if (this.modelArmor instanceof ModelPlayer)
        {
            --var1;
            var2[var1] = (ModelPlayer)this.modelArmor;
        }

        if (this.modelArmorChestplate instanceof ModelPlayer)
        {
            --var1;
            var2[var1] = (ModelPlayer)this.modelArmorChestplate;
        }

        if (this.modelBipedMain instanceof ModelPlayer)
        {
            --var1;
            var2[var1] = (ModelPlayer)this.modelBipedMain;
        }

        return var2;
    }

    public final String[] getArmorFilenamePrefixField()
    {
        return armorFilenamePrefix;
    }

    public final void setArmorFilenamePrefixField(String[] var1)
    {
        armorFilenamePrefix = var1;
    }

    public final ModelBase getMainModelField()
    {
        return super.mainModel;
    }

    public final void setMainModelField(ModelBase var1)
    {
        super.mainModel = var1;
    }

    public final ModelBiped getModelArmorField()
    {
        return this.modelArmor;
    }

    public final void setModelArmorField(ModelBiped var1)
    {
        this.modelArmor = var1;
    }

    public final ModelBiped getModelArmorChestplateField()
    {
        return this.modelArmorChestplate;
    }

    public final void setModelArmorChestplateField(ModelBiped var1)
    {
        this.modelArmorChestplate = var1;
    }

    public final ModelBiped getModelBipedMainField()
    {
        return this.modelBipedMain;
    }

    public final void setModelBipedMainField(ModelBiped var1)
    {
        this.modelBipedMain = var1;
    }

    public final RenderBlocks getRenderBlocksField()
    {
        return super.renderBlocks;
    }

    public final void setRenderBlocksField(RenderBlocks var1)
    {
        super.renderBlocks = var1;
    }

    public final RenderManager getRenderManagerField()
    {
        return super.renderManager;
    }

    public final void setRenderManagerField(RenderManager var1)
    {
        super.renderManager = var1;
    }

    public final ModelBase getRenderPassModelField()
    {
        return super.renderPassModel;
    }

    public final void setRenderPassModelField(ModelBase var1)
    {
        super.renderPassModel = var1;
    }

    public final float getShadowOpaqueField()
    {
        return super.shadowOpaque;
    }

    public final void setShadowOpaqueField(float var1)
    {
        super.shadowOpaque = var1;
    }

    public final float getShadowSizeField()
    {
        return super.shadowSize;
    }

    public final void setShadowSizeField(float var1)
    {
        super.shadowSize = var1;
    }

    static
    {
        Class var0 = TryLoadType("net.minecraftforge.client.event.RenderPlayerEvent$SetArmorModel");
        renderPlayerEventSetArmorModelConstructor = TryLoadConstructor(var0, new Class[] {EntityPlayer.class, RenderPlayer.class, Integer.TYPE, Float.TYPE, ItemStack.class});
        renderPlayerEventSetArmorModelResult = TryLoadField(var0, "result");
        Class var1 = TryLoadType("net.minecraftforge.client.event.RenderPlayerEvent$Pre");
        renderPlayerEventPreConstructor = TryLoadConstructor(var1, new Class[] {EntityPlayer.class, RenderPlayer.class});
        Class var2 = TryLoadType("net.minecraftforge.client.event.RenderPlayerEvent$Post");
        renderPlayerEventPostConstructor = TryLoadConstructor(var2, new Class[] {EntityPlayer.class, RenderPlayer.class});
        Class var3 = TryLoadType("net.minecraftforge.client.event.RenderPlayerEvent$Specials$Pre");
        renderPlayerEventSpecialsPreConstructor = TryLoadConstructor(var3, new Class[] {EntityPlayer.class, RenderPlayer.class, Float.TYPE});
        renderPlayerEventSpecialsPreRenderHelmet = TryLoadField(var0, "renderHelmet");
        renderPlayerEventSpecialsPreRenderCape = TryLoadField(var0, "renderCape");
        renderPlayerEventSpecialsPreRenderItem = TryLoadField(var0, "renderItem");
        Class var4 = TryLoadType("net.minecraftforge.client.event.RenderPlayerEvent$Specials$Post");
        renderPlayerEventSpecialsPostConstructor = TryLoadConstructor(var4, new Class[] {EntityPlayer.class, RenderPlayer.class, Float.TYPE});
        Class var5 = TryLoadType("net.minecraftforge.common.MinecraftForge");
        eventBusField = TryLoadField(var5, "EVENT_BUS");
        Class var6 = TryLoadType("net.minecraftforge.event.Event");
        Class var7 = TryLoadType("net.minecraftforge.event.EventBus");
        post = TryLoadMethod(var7, "post", new Class[] {var6});
        boolean var8 = RenderPlayer.class.getSimpleName().length() < 4;
        Class var9 = TryLoadType(var8 ? "ModLoader" : "net.minecraft.src.ModLoader");

        if (var9 != null)
        {
            try
            {
                Field var10 = var9.getDeclaredField("hasInit");
                var10.setAccessible(true);

                if (!((Boolean)var10.get((Object)null)).booleanValue())
                {
                    ;
                }

                Method var11 = var9.getDeclaredMethod("init", new Class[0]);
                var11.setAccessible(true);
                var11.invoke((Object)null, new Object[0]);
            }
            catch (Throwable var12)
            {
                ;
            }
        }
    }
}
