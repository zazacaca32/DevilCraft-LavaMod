package net.minecraft.src;

import java.lang.reflect.Method;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public abstract class RenderPlayerBase
{
    protected final RenderPlayer renderPlayer;
    private final RenderPlayerAPI renderPlayerAPI;
    private Method[] methods;

    public RenderPlayerBase(RenderPlayerAPI renderPlayerAPI)
    {
        this.renderPlayerAPI = renderPlayerAPI;
        this.renderPlayer = renderPlayerAPI.renderPlayer;
    }

    public void beforeBaseAttach(boolean bl) {}

    public void afterBaseAttach(boolean bl) {}

    public void beforeLocalConstructing() {}

    public void afterLocalConstructing() {}

    public void beforeBaseDetach(boolean bl) {}

    public void afterBaseDetach(boolean bl) {}

    public Object dynamic(String string, Object[] arrobject)
    {
        return this.renderPlayerAPI.dynamicOverwritten(string, arrobject, this);
    }

    public final int hashCode()
    {
        return super.hashCode();
    }

    public void beforeDoRenderShadowAndFire(Entity entity, double d, double d2, double d3, float f, float f2) {}

    public void doRenderShadowAndFire(Entity entity, double d, double d2, double d3, float f, float f2)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenDoRenderShadowAndFire(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localDoRenderShadowAndFire(entity, d, d2, d3, f, f2);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.doRenderShadowAndFire(entity, d, d2, d3, f, f2);
        }
    }

    public void afterDoRenderShadowAndFire(Entity entity, double d, double d2, double d3, float f, float f2) {}

    public void beforeGetColorMultiplier(EntityLiving entityLiving, float f, float f2) {}

    public int getColorMultiplier(EntityLiving entityLiving, float f, float f2)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenGetColorMultiplier(this);
        int n = renderPlayerBase == null ? this.renderPlayer.localGetColorMultiplier(entityLiving, f, f2) : (renderPlayerBase != this ? renderPlayerBase.getColorMultiplier(entityLiving, f, f2) : 0);
        return n;
    }

    public void afterGetColorMultiplier(EntityLiving entityLiving, float f, float f2) {}

    public void beforeGetDeathMaxRotation(EntityLiving entityLiving) {}

    public float getDeathMaxRotation(EntityLiving entityLiving)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenGetDeathMaxRotation(this);
        float f = renderPlayerBase == null ? this.renderPlayer.localGetDeathMaxRotation(entityLiving) : (renderPlayerBase != this ? renderPlayerBase.getDeathMaxRotation(entityLiving) : 0.0F);
        return f;
    }

    public void afterGetDeathMaxRotation(EntityLiving entityLiving) {}

    public void beforeGetFontRendererFromRenderManager() {}

    public FontRenderer getFontRendererFromRenderManager()
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenGetFontRendererFromRenderManager(this);
        FontRenderer fontRenderer = renderPlayerBase == null ? this.renderPlayer.localGetFontRendererFromRenderManager() : (renderPlayerBase != this ? renderPlayerBase.getFontRendererFromRenderManager() : null);
        return fontRenderer;
    }

    public void afterGetFontRendererFromRenderManager() {}

    public void beforeHandleRotationFloat(EntityLiving entityLiving, float f) {}

    public float handleRotationFloat(EntityLiving entityLiving, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenHandleRotationFloat(this);
        float f2 = renderPlayerBase == null ? this.renderPlayer.localHandleRotationFloat(entityLiving, f) : (renderPlayerBase != this ? renderPlayerBase.handleRotationFloat(entityLiving, f) : 0.0F);
        return f2;
    }

    public void afterHandleRotationFloat(EntityLiving entityLiving, float f) {}

    public void beforeInheritRenderPass(EntityLiving entityLiving, int n, float f) {}

    public int inheritRenderPass(EntityLiving entityLiving, int n, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenInheritRenderPass(this);
        int n2 = renderPlayerBase == null ? this.renderPlayer.localInheritRenderPass(entityLiving, n, f) : (renderPlayerBase != this ? renderPlayerBase.inheritRenderPass(entityLiving, n, f) : 0);
        return n2;
    }

    public void afterInheritRenderPass(EntityLiving entityLiving, int n, float f) {}

    public void beforeLoadDownloadableImageTexture(String string, String string2) {}

    public boolean loadDownloadableImageTexture(String string, String string2)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenLoadDownloadableImageTexture(this);
        boolean bl = renderPlayerBase == null ? this.renderPlayer.localLoadDownloadableImageTexture(string, string2) : (renderPlayerBase != this ? renderPlayerBase.loadDownloadableImageTexture(string, string2) : false);
        return bl;
    }

    public void afterLoadDownloadableImageTexture(String string, String string2) {}

    public void beforeLoadPlayerTexture(EntityPlayer entityPlayer) {}

    public void loadPlayerTexture(EntityPlayer entityPlayer)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenLoadPlayerTexture(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localLoadPlayerTexture(entityPlayer);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.loadPlayerTexture(entityPlayer);
        }
    }

    public void afterLoadPlayerTexture(EntityPlayer entityPlayer) {}

    public void beforeLoadTexture(String string) {}

    public void loadTexture(String string)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenLoadTexture(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localLoadTexture(string);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.loadTexture(string);
        }
    }

    public void afterLoadTexture(String string) {}

    public void beforePassSpecialRender(EntityLiving entityLiving, double d, double d2, double d3) {}

    public void passSpecialRender(EntityLiving entityLiving, double d, double d2, double d3)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenPassSpecialRender(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localPassSpecialRender(entityLiving, d, d2, d3);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.passSpecialRender(entityLiving, d, d2, d3);
        }
    }

    public void afterPassSpecialRender(EntityLiving entityLiving, double d, double d2, double d3) {}

    public void beforeRenderArrowsStuckInEntity(EntityLiving entityLiving, float f) {}

    public void renderArrowsStuckInEntity(EntityLiving entityLiving, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderArrowsStuckInEntity(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderArrowsStuckInEntity(entityLiving, f);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderArrowsStuckInEntity(entityLiving, f);
        }
    }

    public void afterRenderArrowsStuckInEntity(EntityLiving entityLiving, float f) {}

    public void beforeRenderFirstPersonArm(EntityPlayer entityPlayer) {}

    public void renderFirstPersonArm(EntityPlayer entityPlayer)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderFirstPersonArm(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderFirstPersonArm(entityPlayer);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderFirstPersonArm(entityPlayer);
        }
    }

    public void afterRenderFirstPersonArm(EntityPlayer entityPlayer) {}

    public void beforeRenderLivingLabel(EntityLiving entityLiving, String string, double d, double d2, double d3, int n) {}

    public void renderLivingLabel(EntityLiving entityLiving, String string, double d, double d2, double d3, int n)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderLivingLabel(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderLivingLabel(entityLiving, string, d, d2, d3, n);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderLivingLabel(entityLiving, string, d, d2, d3, n);
        }
    }

    public void afterRenderLivingLabel(EntityLiving entityLiving, String string, double d, double d2, double d3, int n) {}

    public void beforeRenderModel(EntityLiving entityLiving, float f, float f2, float f3, float f4, float f5, float f6) {}

    public void renderModel(EntityLiving entityLiving, float f, float f2, float f3, float f4, float f5, float f6)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderModel(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderModel(entityLiving, f, f2, f3, f4, f5, f6);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderModel(entityLiving, f, f2, f3, f4, f5, f6);
        }
    }

    public void afterRenderModel(EntityLiving entityLiving, float f, float f2, float f3, float f4, float f5, float f6) {}

    public void beforeRenderPlayer(EntityPlayer entityPlayer, double d, double d2, double d3, float f, float f2) {}

    public void renderPlayer(EntityPlayer entityPlayer, double d, double d2, double d3, float f, float f2)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderPlayer(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderPlayer(entityPlayer, d, d2, d3, f, f2);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderPlayer(entityPlayer, d, d2, d3, f, f2);
        }
    }

    public void afterRenderPlayer(EntityPlayer entityPlayer, double d, double d2, double d3, float f, float f2) {}

    public void beforeRenderPlayerNameAndScoreLabel(EntityPlayer entityPlayer, double d, double d2, double d3, String string, float f, double d4) {}

    public void renderPlayerNameAndScoreLabel(EntityPlayer entityPlayer, double d, double d2, double d3, String string, float f, double d4)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderPlayerNameAndScoreLabel(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderPlayerNameAndScoreLabel(entityPlayer, d, d2, d3, string, f, d4);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderPlayerNameAndScoreLabel(entityPlayer, d, d2, d3, string, f, d4);
        }
    }

    public void afterRenderPlayerNameAndScoreLabel(EntityPlayer entityPlayer, double d, double d2, double d3, String string, float f, double d4) {}

    public void beforeRenderPlayerScale(EntityPlayer entityPlayer, float f) {}

    public void renderPlayerScale(EntityPlayer entityPlayer, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderPlayerScale(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderPlayerScale(entityPlayer, f);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderPlayerScale(entityPlayer, f);
        }
    }

    public void afterRenderPlayerScale(EntityPlayer entityPlayer, float f) {}

    public void beforeRenderPlayerSleep(EntityPlayer entityPlayer, double d, double d2, double d3) {}

    public void renderPlayerSleep(EntityPlayer entityPlayer, double d, double d2, double d3)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderPlayerSleep(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderPlayerSleep(entityPlayer, d, d2, d3);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderPlayerSleep(entityPlayer, d, d2, d3);
        }
    }

    public void afterRenderPlayerSleep(EntityPlayer entityPlayer, double d, double d2, double d3) {}

    public void beforeRenderSpecials(EntityPlayer entityPlayer, float f) {}

    public void renderSpecials(EntityPlayer entityPlayer, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderSpecials(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderSpecials(entityPlayer, f);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderSpecials(entityPlayer, f);
        }
    }

    public void afterRenderSpecials(EntityPlayer entityPlayer, float f) {}

    public void beforeRenderSwingProgress(EntityLiving entityLiving, float f) {}

    public float renderSwingProgress(EntityLiving entityLiving, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderSwingProgress(this);
        float f2 = renderPlayerBase == null ? this.renderPlayer.localRenderSwingProgress(entityLiving, f) : (renderPlayerBase != this ? renderPlayerBase.renderSwingProgress(entityLiving, f) : 0.0F);
        return f2;
    }

    public void afterRenderSwingProgress(EntityLiving entityLiving, float f) {}

    public void beforeRotatePlayer(EntityPlayer entityPlayer, float f, float f2, float f3) {}

    public void rotatePlayer(EntityPlayer entityPlayer, float f, float f2, float f3)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRotatePlayer(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRotatePlayer(entityPlayer, f, f2, f3);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.rotatePlayer(entityPlayer, f, f2, f3);
        }
    }

    public void afterRotatePlayer(EntityPlayer entityPlayer, float f, float f2, float f3) {}

    public void beforeSetArmorModel(EntityPlayer entityPlayer, int n, float f) {}

    public int setArmorModel(EntityPlayer entityPlayer, int n, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenSetArmorModel(this);
        int n2 = renderPlayerBase == null ? this.renderPlayer.localSetArmorModel(entityPlayer, n, f) : (renderPlayerBase != this ? renderPlayerBase.setArmorModel(entityPlayer, n, f) : 0);
        return n2;
    }

    public void afterSetArmorModel(EntityPlayer entityPlayer, int n, float f) {}

    public void beforeSetIconRegister(IconRegister iconRegister) {}

    public void setIconRegister(IconRegister iconRegister)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenSetIconRegister(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localSetIconRegister(iconRegister);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.setIconRegister(iconRegister);
        }
    }

    public void afterSetIconRegister(IconRegister iconRegister) {}

    public void beforeSetPassArmorModel(EntityPlayer entityPlayer, int n, float f) {}

    public void setPassArmorModel(EntityPlayer entityPlayer, int n, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenSetPassArmorModel(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localSetPassArmorModel(entityPlayer, n, f);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.setPassArmorModel(entityPlayer, n, f);
        }
    }

    public void afterSetPassArmorModel(EntityPlayer entityPlayer, int n, float f) {}

    public void beforeSetRenderManager(RenderManager renderManager) {}

    public void setRenderManager(RenderManager renderManager)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenSetRenderManager(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localSetRenderManager(renderManager);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.setRenderManager(renderManager);
        }
    }

    public void afterSetRenderManager(RenderManager renderManager) {}

    public void beforeSetRenderPassModel(ModelBase modelBase) {}

    public void setRenderPassModel(ModelBase modelBase)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenSetRenderPassModel(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localSetRenderPassModel(modelBase);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.setRenderPassModel(modelBase);
        }
    }

    public void afterSetRenderPassModel(ModelBase modelBase) {}

    public void beforeRenderSpecialHeadArmor(EntityPlayer entityPlayer, float f) {}

    public void renderSpecialHeadArmor(EntityPlayer entityPlayer, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderSpecialHeadArmor(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderSpecialHeadArmor(entityPlayer, f);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderSpecialHeadArmor(entityPlayer, f);
        }
    }

    public void afterRenderSpecialHeadArmor(EntityPlayer entityPlayer, float f) {}

    public void beforeRenderSpecialHeadEars(EntityPlayer entityPlayer, float f) {}

    public void renderSpecialHeadEars(EntityPlayer entityPlayer, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderSpecialHeadEars(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderSpecialHeadEars(entityPlayer, f);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderSpecialHeadEars(entityPlayer, f);
        }
    }

    public void afterRenderSpecialHeadEars(EntityPlayer entityPlayer, float f) {}

    public void beforeRenderSpecialCloak(EntityPlayer entityPlayer, float f) {}

    public void renderSpecialCloak(EntityPlayer entityPlayer, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderSpecialCloak(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderSpecialCloak(entityPlayer, f);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderSpecialCloak(entityPlayer, f);
        }
    }

    public void afterRenderSpecialCloak(EntityPlayer entityPlayer, float f) {}

    public void beforeRenderSpecialItemInHand(EntityPlayer entityPlayer, float f) {}

    public void renderSpecialItemInHand(EntityPlayer entityPlayer, float f)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenRenderSpecialItemInHand(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localRenderSpecialItemInHand(entityPlayer, f);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.renderSpecialItemInHand(entityPlayer, f);
        }
    }

    public void afterRenderSpecialItemInHand(EntityPlayer entityPlayer, float f) {}

    public void beforePositionSpecialItemInHand(EntityPlayer entityPlayer, float f, EnumAction enumAction, ItemStack itemStack) {}

    public void positionSpecialItemInHand(EntityPlayer entityPlayer, float f, EnumAction enumAction, ItemStack itemStack)
    {
        RenderPlayerBase renderPlayerBase = this.renderPlayerAPI.GetOverwrittenPositionSpecialItemInHand(this);

        if (renderPlayerBase == null)
        {
            this.renderPlayer.localPositionSpecialItemInHand(entityPlayer, f, enumAction, itemStack);
        }
        else if (renderPlayerBase != this)
        {
            renderPlayerBase.positionSpecialItemInHand(entityPlayer, f, enumAction, itemStack);
        }
    }

    public void afterPositionSpecialItemInHand(EntityPlayer entityPlayer, float f, EnumAction enumAction, ItemStack itemStack) {}
}
