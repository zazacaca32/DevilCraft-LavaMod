package net.minecraft.src;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
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

public final class RenderPlayerAPI
{
    private static final Class[] Class = new Class[] {RenderPlayerAPI.class};
    private static final Class[] Classes = new Class[] {RenderPlayerAPI.class, String.class};
    private static boolean isCreated;
    private static final Logger logger = Logger.getLogger("RenderPlayerAPI");
    private static final Map EmptySortMap = Collections.unmodifiableMap(new HashMap());
    private static final Object[] initializer = new Object[] {null};
    private static final Object[] initializers = new Object[] {null, null};
    private static final List beforeDoRenderShadowAndFireHookTypes = new LinkedList();
    private static final List overrideDoRenderShadowAndFireHookTypes = new LinkedList();
    private static final List afterDoRenderShadowAndFireHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeDoRenderShadowAndFireHooks;
    private RenderPlayerBase[] overrideDoRenderShadowAndFireHooks;
    private RenderPlayerBase[] afterDoRenderShadowAndFireHooks;
    public boolean isDoRenderShadowAndFireModded;
    private static final Map allBaseBeforeDoRenderShadowAndFireSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDoRenderShadowAndFireInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDoRenderShadowAndFireSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDoRenderShadowAndFireInferiors = new Hashtable(0);
    private static final Map allBaseAfterDoRenderShadowAndFireSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDoRenderShadowAndFireInferiors = new Hashtable(0);
    private static final List beforeGetColorMultiplierHookTypes = new LinkedList();
    private static final List overrideGetColorMultiplierHookTypes = new LinkedList();
    private static final List afterGetColorMultiplierHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeGetColorMultiplierHooks;
    private RenderPlayerBase[] overrideGetColorMultiplierHooks;
    private RenderPlayerBase[] afterGetColorMultiplierHooks;
    public boolean isGetColorMultiplierModded;
    private static final Map allBaseBeforeGetColorMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetColorMultiplierInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetColorMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetColorMultiplierInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetColorMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetColorMultiplierInferiors = new Hashtable(0);
    private static final List beforeGetDeathMaxRotationHookTypes = new LinkedList();
    private static final List overrideGetDeathMaxRotationHookTypes = new LinkedList();
    private static final List afterGetDeathMaxRotationHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeGetDeathMaxRotationHooks;
    private RenderPlayerBase[] overrideGetDeathMaxRotationHooks;
    private RenderPlayerBase[] afterGetDeathMaxRotationHooks;
    public boolean isGetDeathMaxRotationModded;
    private static final Map allBaseBeforeGetDeathMaxRotationSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetDeathMaxRotationInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetDeathMaxRotationSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetDeathMaxRotationInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetDeathMaxRotationSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetDeathMaxRotationInferiors = new Hashtable(0);
    private static final List beforeGetFontRendererFromRenderManagerHookTypes = new LinkedList();
    private static final List overrideGetFontRendererFromRenderManagerHookTypes = new LinkedList();
    private static final List afterGetFontRendererFromRenderManagerHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeGetFontRendererFromRenderManagerHooks;
    private RenderPlayerBase[] overrideGetFontRendererFromRenderManagerHooks;
    private RenderPlayerBase[] afterGetFontRendererFromRenderManagerHooks;
    public boolean isGetFontRendererFromRenderManagerModded;
    private static final Map allBaseBeforeGetFontRendererFromRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetFontRendererFromRenderManagerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetFontRendererFromRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetFontRendererFromRenderManagerInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetFontRendererFromRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetFontRendererFromRenderManagerInferiors = new Hashtable(0);
    private static final List beforeHandleRotationFloatHookTypes = new LinkedList();
    private static final List overrideHandleRotationFloatHookTypes = new LinkedList();
    private static final List afterHandleRotationFloatHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeHandleRotationFloatHooks;
    private RenderPlayerBase[] overrideHandleRotationFloatHooks;
    private RenderPlayerBase[] afterHandleRotationFloatHooks;
    public boolean isHandleRotationFloatModded;
    private static final Map allBaseBeforeHandleRotationFloatSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeHandleRotationFloatInferiors = new Hashtable(0);
    private static final Map allBaseOverrideHandleRotationFloatSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideHandleRotationFloatInferiors = new Hashtable(0);
    private static final Map allBaseAfterHandleRotationFloatSuperiors = new Hashtable(0);
    private static final Map allBaseAfterHandleRotationFloatInferiors = new Hashtable(0);
    private static final List beforeInheritRenderPassHookTypes = new LinkedList();
    private static final List overrideInheritRenderPassHookTypes = new LinkedList();
    private static final List afterInheritRenderPassHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeInheritRenderPassHooks;
    private RenderPlayerBase[] overrideInheritRenderPassHooks;
    private RenderPlayerBase[] afterInheritRenderPassHooks;
    public boolean isInheritRenderPassModded;
    private static final Map allBaseBeforeInheritRenderPassSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeInheritRenderPassInferiors = new Hashtable(0);
    private static final Map allBaseOverrideInheritRenderPassSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideInheritRenderPassInferiors = new Hashtable(0);
    private static final Map allBaseAfterInheritRenderPassSuperiors = new Hashtable(0);
    private static final Map allBaseAfterInheritRenderPassInferiors = new Hashtable(0);
    private static final List beforeLoadDownloadableImageTextureHookTypes = new LinkedList();
    private static final List overrideLoadDownloadableImageTextureHookTypes = new LinkedList();
    private static final List afterLoadDownloadableImageTextureHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeLoadDownloadableImageTextureHooks;
    private RenderPlayerBase[] overrideLoadDownloadableImageTextureHooks;
    private RenderPlayerBase[] afterLoadDownloadableImageTextureHooks;
    public boolean isLoadDownloadableImageTextureModded;
    private static final Map allBaseBeforeLoadDownloadableImageTextureSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeLoadDownloadableImageTextureInferiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadDownloadableImageTextureSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadDownloadableImageTextureInferiors = new Hashtable(0);
    private static final Map allBaseAfterLoadDownloadableImageTextureSuperiors = new Hashtable(0);
    private static final Map allBaseAfterLoadDownloadableImageTextureInferiors = new Hashtable(0);
    private static final List beforeLoadPlayerTextureHookTypes = new LinkedList();
    private static final List overrideLoadPlayerTextureHookTypes = new LinkedList();
    private static final List afterLoadPlayerTextureHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeLoadPlayerTextureHooks;
    private RenderPlayerBase[] overrideLoadPlayerTextureHooks;
    private RenderPlayerBase[] afterLoadPlayerTextureHooks;
    public boolean isLoadPlayerTextureModded;
    private static final Map allBaseBeforeLoadPlayerTextureSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeLoadPlayerTextureInferiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadPlayerTextureSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadPlayerTextureInferiors = new Hashtable(0);
    private static final Map allBaseAfterLoadPlayerTextureSuperiors = new Hashtable(0);
    private static final Map allBaseAfterLoadPlayerTextureInferiors = new Hashtable(0);
    private static final List beforeLoadTextureHookTypes = new LinkedList();
    private static final List overrideLoadTextureHookTypes = new LinkedList();
    private static final List afterLoadTextureHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeLoadTextureHooks;
    private RenderPlayerBase[] overrideLoadTextureHooks;
    private RenderPlayerBase[] afterLoadTextureHooks;
    public boolean isLoadTextureModded;
    private static final Map allBaseBeforeLoadTextureSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeLoadTextureInferiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadTextureSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadTextureInferiors = new Hashtable(0);
    private static final Map allBaseAfterLoadTextureSuperiors = new Hashtable(0);
    private static final Map allBaseAfterLoadTextureInferiors = new Hashtable(0);
    private static final List beforePassSpecialRenderHookTypes = new LinkedList();
    private static final List overridePassSpecialRenderHookTypes = new LinkedList();
    private static final List afterPassSpecialRenderHookTypes = new LinkedList();
    private RenderPlayerBase[] beforePassSpecialRenderHooks;
    private RenderPlayerBase[] overridePassSpecialRenderHooks;
    private RenderPlayerBase[] afterPassSpecialRenderHooks;
    public boolean isPassSpecialRenderModded;
    private static final Map allBaseBeforePassSpecialRenderSuperiors = new Hashtable(0);
    private static final Map allBaseBeforePassSpecialRenderInferiors = new Hashtable(0);
    private static final Map allBaseOverridePassSpecialRenderSuperiors = new Hashtable(0);
    private static final Map allBaseOverridePassSpecialRenderInferiors = new Hashtable(0);
    private static final Map allBaseAfterPassSpecialRenderSuperiors = new Hashtable(0);
    private static final Map allBaseAfterPassSpecialRenderInferiors = new Hashtable(0);
    private static final List beforeRenderArrowsStuckInEntityHookTypes = new LinkedList();
    private static final List overrideRenderArrowsStuckInEntityHookTypes = new LinkedList();
    private static final List afterRenderArrowsStuckInEntityHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderArrowsStuckInEntityHooks;
    private RenderPlayerBase[] overrideRenderArrowsStuckInEntityHooks;
    private RenderPlayerBase[] afterRenderArrowsStuckInEntityHooks;
    public boolean isRenderArrowsStuckInEntityModded;
    private static final Map allBaseBeforeRenderArrowsStuckInEntitySuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderArrowsStuckInEntityInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderArrowsStuckInEntitySuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderArrowsStuckInEntityInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderArrowsStuckInEntitySuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderArrowsStuckInEntityInferiors = new Hashtable(0);
    private static final List beforeRenderFirstPersonArmHookTypes = new LinkedList();
    private static final List overrideRenderFirstPersonArmHookTypes = new LinkedList();
    private static final List afterRenderFirstPersonArmHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderFirstPersonArmHooks;
    private RenderPlayerBase[] overrideRenderFirstPersonArmHooks;
    private RenderPlayerBase[] afterRenderFirstPersonArmHooks;
    public boolean isRenderFirstPersonArmModded;
    private static final Map allBaseBeforeRenderFirstPersonArmSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderFirstPersonArmInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderFirstPersonArmSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderFirstPersonArmInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderFirstPersonArmSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderFirstPersonArmInferiors = new Hashtable(0);
    private static final List beforeRenderLivingLabelHookTypes = new LinkedList();
    private static final List overrideRenderLivingLabelHookTypes = new LinkedList();
    private static final List afterRenderLivingLabelHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderLivingLabelHooks;
    private RenderPlayerBase[] overrideRenderLivingLabelHooks;
    private RenderPlayerBase[] afterRenderLivingLabelHooks;
    public boolean isRenderLivingLabelModded;
    private static final Map allBaseBeforeRenderLivingLabelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderLivingLabelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderLivingLabelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderLivingLabelInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderLivingLabelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderLivingLabelInferiors = new Hashtable(0);
    private static final List beforeRenderModelHookTypes = new LinkedList();
    private static final List overrideRenderModelHookTypes = new LinkedList();
    private static final List afterRenderModelHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderModelHooks;
    private RenderPlayerBase[] overrideRenderModelHooks;
    private RenderPlayerBase[] afterRenderModelHooks;
    public boolean isRenderModelModded;
    private static final Map allBaseBeforeRenderModelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderModelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderModelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderModelInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderModelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderModelInferiors = new Hashtable(0);
    private static final List beforeRenderPlayerHookTypes = new LinkedList();
    private static final List overrideRenderPlayerHookTypes = new LinkedList();
    private static final List afterRenderPlayerHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderPlayerHooks;
    private RenderPlayerBase[] overrideRenderPlayerHooks;
    private RenderPlayerBase[] afterRenderPlayerHooks;
    public boolean isRenderPlayerModded;
    private static final Map allBaseBeforeRenderPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderPlayerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerInferiors = new Hashtable(0);
    private static final List beforeRenderPlayerNameAndScoreLabelHookTypes = new LinkedList();
    private static final List overrideRenderPlayerNameAndScoreLabelHookTypes = new LinkedList();
    private static final List afterRenderPlayerNameAndScoreLabelHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderPlayerNameAndScoreLabelHooks;
    private RenderPlayerBase[] overrideRenderPlayerNameAndScoreLabelHooks;
    private RenderPlayerBase[] afterRenderPlayerNameAndScoreLabelHooks;
    public boolean isRenderPlayerNameAndScoreLabelModded;
    private static final Map allBaseBeforeRenderPlayerNameAndScoreLabelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderPlayerNameAndScoreLabelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerNameAndScoreLabelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerNameAndScoreLabelInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerNameAndScoreLabelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerNameAndScoreLabelInferiors = new Hashtable(0);
    private static final List beforeRenderPlayerScaleHookTypes = new LinkedList();
    private static final List overrideRenderPlayerScaleHookTypes = new LinkedList();
    private static final List afterRenderPlayerScaleHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderPlayerScaleHooks;
    private RenderPlayerBase[] overrideRenderPlayerScaleHooks;
    private RenderPlayerBase[] afterRenderPlayerScaleHooks;
    public boolean isRenderPlayerScaleModded;
    private static final Map allBaseBeforeRenderPlayerScaleSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderPlayerScaleInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerScaleSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerScaleInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerScaleSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerScaleInferiors = new Hashtable(0);
    private static final List beforeRenderPlayerSleepHookTypes = new LinkedList();
    private static final List overrideRenderPlayerSleepHookTypes = new LinkedList();
    private static final List afterRenderPlayerSleepHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderPlayerSleepHooks;
    private RenderPlayerBase[] overrideRenderPlayerSleepHooks;
    private RenderPlayerBase[] afterRenderPlayerSleepHooks;
    public boolean isRenderPlayerSleepModded;
    private static final Map allBaseBeforeRenderPlayerSleepSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderPlayerSleepInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerSleepSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerSleepInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerSleepSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerSleepInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialsHookTypes = new LinkedList();
    private static final List overrideRenderSpecialsHookTypes = new LinkedList();
    private static final List afterRenderSpecialsHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderSpecialsHooks;
    private RenderPlayerBase[] overrideRenderSpecialsHooks;
    private RenderPlayerBase[] afterRenderSpecialsHooks;
    public boolean isRenderSpecialsModded;
    private static final Map allBaseBeforeRenderSpecialsSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialsInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialsSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialsInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialsSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialsInferiors = new Hashtable(0);
    private static final List beforeRenderSwingProgressHookTypes = new LinkedList();
    private static final List overrideRenderSwingProgressHookTypes = new LinkedList();
    private static final List afterRenderSwingProgressHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderSwingProgressHooks;
    private RenderPlayerBase[] overrideRenderSwingProgressHooks;
    private RenderPlayerBase[] afterRenderSwingProgressHooks;
    public boolean isRenderSwingProgressModded;
    private static final Map allBaseBeforeRenderSwingProgressSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSwingProgressInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSwingProgressSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSwingProgressInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSwingProgressSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSwingProgressInferiors = new Hashtable(0);
    private static final List beforeRotatePlayerHookTypes = new LinkedList();
    private static final List overrideRotatePlayerHookTypes = new LinkedList();
    private static final List afterRotatePlayerHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRotatePlayerHooks;
    private RenderPlayerBase[] overrideRotatePlayerHooks;
    private RenderPlayerBase[] afterRotatePlayerHooks;
    public boolean isRotatePlayerModded;
    private static final Map allBaseBeforeRotatePlayerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRotatePlayerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRotatePlayerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRotatePlayerInferiors = new Hashtable(0);
    private static final Map allBaseAfterRotatePlayerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRotatePlayerInferiors = new Hashtable(0);
    private static final List beforeSetArmorModelHookTypes = new LinkedList();
    private static final List overrideSetArmorModelHookTypes = new LinkedList();
    private static final List afterSetArmorModelHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeSetArmorModelHooks;
    private RenderPlayerBase[] overrideSetArmorModelHooks;
    private RenderPlayerBase[] afterSetArmorModelHooks;
    public boolean isSetArmorModelModded;
    private static final Map allBaseBeforeSetArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetArmorModelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetArmorModelInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetArmorModelInferiors = new Hashtable(0);
    private static final List beforeSetIconRegisterHookTypes = new LinkedList();
    private static final List overrideSetIconRegisterHookTypes = new LinkedList();
    private static final List afterSetIconRegisterHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeSetIconRegisterHooks;
    private RenderPlayerBase[] overrideSetIconRegisterHooks;
    private RenderPlayerBase[] afterSetIconRegisterHooks;
    public boolean isSetIconRegisterModded;
    private static final Map allBaseBeforeSetIconRegisterSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetIconRegisterInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetIconRegisterSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetIconRegisterInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetIconRegisterSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetIconRegisterInferiors = new Hashtable(0);
    private static final List beforeSetPassArmorModelHookTypes = new LinkedList();
    private static final List overrideSetPassArmorModelHookTypes = new LinkedList();
    private static final List afterSetPassArmorModelHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeSetPassArmorModelHooks;
    private RenderPlayerBase[] overrideSetPassArmorModelHooks;
    private RenderPlayerBase[] afterSetPassArmorModelHooks;
    public boolean isSetPassArmorModelModded;
    private static final Map allBaseBeforeSetPassArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetPassArmorModelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetPassArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetPassArmorModelInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetPassArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetPassArmorModelInferiors = new Hashtable(0);
    private static final List beforeSetRenderManagerHookTypes = new LinkedList();
    private static final List overrideSetRenderManagerHookTypes = new LinkedList();
    private static final List afterSetRenderManagerHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeSetRenderManagerHooks;
    private RenderPlayerBase[] overrideSetRenderManagerHooks;
    private RenderPlayerBase[] afterSetRenderManagerHooks;
    public boolean isSetRenderManagerModded;
    private static final Map allBaseBeforeSetRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetRenderManagerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRenderManagerInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetRenderManagerInferiors = new Hashtable(0);
    private static final List beforeSetRenderPassModelHookTypes = new LinkedList();
    private static final List overrideSetRenderPassModelHookTypes = new LinkedList();
    private static final List afterSetRenderPassModelHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeSetRenderPassModelHooks;
    private RenderPlayerBase[] overrideSetRenderPassModelHooks;
    private RenderPlayerBase[] afterSetRenderPassModelHooks;
    public boolean isSetRenderPassModelModded;
    private static final Map allBaseBeforeSetRenderPassModelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetRenderPassModelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRenderPassModelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRenderPassModelInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetRenderPassModelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetRenderPassModelInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialHeadArmorHookTypes = new LinkedList();
    private static final List overrideRenderSpecialHeadArmorHookTypes = new LinkedList();
    private static final List afterRenderSpecialHeadArmorHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderSpecialHeadArmorHooks;
    private RenderPlayerBase[] overrideRenderSpecialHeadArmorHooks;
    private RenderPlayerBase[] afterRenderSpecialHeadArmorHooks;
    public boolean isRenderSpecialHeadArmorModded;
    private static final Map allBaseBeforeRenderSpecialHeadArmorSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialHeadArmorInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialHeadArmorSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialHeadArmorInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialHeadArmorSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialHeadArmorInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialHeadEarsHookTypes = new LinkedList();
    private static final List overrideRenderSpecialHeadEarsHookTypes = new LinkedList();
    private static final List afterRenderSpecialHeadEarsHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderSpecialHeadEarsHooks;
    private RenderPlayerBase[] overrideRenderSpecialHeadEarsHooks;
    private RenderPlayerBase[] afterRenderSpecialHeadEarsHooks;
    public boolean isRenderSpecialHeadEarsModded;
    private static final Map allBaseBeforeRenderSpecialHeadEarsSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialHeadEarsInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialHeadEarsSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialHeadEarsInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialHeadEarsSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialHeadEarsInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialCloakHookTypes = new LinkedList();
    private static final List overrideRenderSpecialCloakHookTypes = new LinkedList();
    private static final List afterRenderSpecialCloakHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderSpecialCloakHooks;
    private RenderPlayerBase[] overrideRenderSpecialCloakHooks;
    private RenderPlayerBase[] afterRenderSpecialCloakHooks;
    public boolean isRenderSpecialCloakModded;
    private static final Map allBaseBeforeRenderSpecialCloakSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialCloakInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialCloakSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialCloakInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialCloakSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialCloakInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialItemInHandHookTypes = new LinkedList();
    private static final List overrideRenderSpecialItemInHandHookTypes = new LinkedList();
    private static final List afterRenderSpecialItemInHandHookTypes = new LinkedList();
    private RenderPlayerBase[] beforeRenderSpecialItemInHandHooks;
    private RenderPlayerBase[] overrideRenderSpecialItemInHandHooks;
    private RenderPlayerBase[] afterRenderSpecialItemInHandHooks;
    public boolean isRenderSpecialItemInHandModded;
    private static final Map allBaseBeforeRenderSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialItemInHandInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialItemInHandInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialItemInHandInferiors = new Hashtable(0);
    private static final List beforePositionSpecialItemInHandHookTypes = new LinkedList();
    private static final List overridePositionSpecialItemInHandHookTypes = new LinkedList();
    private static final List afterPositionSpecialItemInHandHookTypes = new LinkedList();
    private RenderPlayerBase[] beforePositionSpecialItemInHandHooks;
    private RenderPlayerBase[] overridePositionSpecialItemInHandHooks;
    private RenderPlayerBase[] afterPositionSpecialItemInHandHooks;
    public boolean isPositionSpecialItemInHandModded;
    private static final Map allBaseBeforePositionSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseBeforePositionSpecialItemInHandInferiors = new Hashtable(0);
    private static final Map allBaseOverridePositionSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseOverridePositionSpecialItemInHandInferiors = new Hashtable(0);
    private static final Map allBaseAfterPositionSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseAfterPositionSpecialItemInHandInferiors = new Hashtable(0);
    protected final RenderPlayer renderPlayer;
    private static final Set keys = new HashSet();
    private static final Map keysToVirtualIds = new HashMap();
    private static final Set dynamicTypes = new HashSet();
    private static final Map virtualDynamicHookMethods = new HashMap();
    private static final Map beforeDynamicHookMethods = new HashMap();
    private static final Map overrideDynamicHookMethods = new HashMap();
    private static final Map afterDynamicHookMethods = new HashMap();
    private static final List beforeLocalConstructingHookTypes = new LinkedList();
    private static final List afterLocalConstructingHookTypes = new LinkedList();
    private static final Map beforeDynamicHookTypes = new Hashtable(0);
    private static final Map overrideDynamicHookTypes = new Hashtable(0);
    private static final Map afterDynamicHookTypes = new Hashtable(0);
    private RenderPlayerBase[] beforeLocalConstructingHooks;
    private RenderPlayerBase[] afterLocalConstructingHooks;
    private final Map baseObjectsToId = new Hashtable();
    private final Map allBaseObjects = new Hashtable();
    private final Set unmodifiableAllBaseIds;
    private static final Map allBaseConstructors = new Hashtable();
    private static final Set unmodifiableAllIds = Collections.unmodifiableSet(allBaseConstructors.keySet());
    private static final Map allBaseBeforeLocalConstructingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeLocalConstructingInferiors = new Hashtable(0);
    private static final Map allBaseAfterLocalConstructingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterLocalConstructingInferiors = new Hashtable(0);
    private static final Map allBaseBeforeDynamicSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDynamicInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDynamicSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDynamicInferiors = new Hashtable(0);
    private static final Map allBaseAfterDynamicSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDynamicInferiors = new Hashtable(0);
    private static boolean initialized = false;

    private static void log(String string)
    {
        System.out.println(string);
        logger.fine(string);
    }

    private static String error(String string)
    {
        logger.severe(string);
        return string;
    }

    public static void register(String string, Class class_)
    {
        register(string, class_, (RenderPlayerBaseSorting)null);
    }

    public static void register(String string, Class class_, RenderPlayerBaseSorting renderPlayerBaseSorting)
    {
        try
        {
            register(class_, string, renderPlayerBaseSorting);
        }
        catch (RuntimeException var4)
        {
            if (string != null)
            {
                log("Render Player API: failed to register id \'" + string + "\'");
            }
            else
            {
                log("Render Player API: failed to register RenderPlayerBase");
            }

            throw var4;
        }
    }

    private static void register(Class class_, String string, RenderPlayerBaseSorting renderPlayerBaseSorting)
    {
        if (!isCreated)
        {
            try
            {
                Method throwable = RenderPlayer.class.getMethod("getRenderPlayerBase", new Class[] {String.class});

                if (throwable.getReturnType() != RenderPlayerBase.class)
                {
                    throw new NoSuchMethodException(RenderPlayerBase.class.getName() + " " + RenderPlayer.class.getName() + ".getRenderPlayerBase(" + String.class.getName() + ")");
                }
            }
            catch (NoSuchMethodException var14)
            {
                String[] throwable2 = new String[] {"========================================", "Render Player API 1.3 can not be created!", "----------------------------------------", "Mandatory member method \"" + RenderPlayerBase.class.getName() + " getRenderPlayerBase(" + String.class.getName() + ")\" not found in class \"" + RenderPlayer.class.getName() + "\".", "There are two scenarios this can happen:", "* The file \"" + RenderPlayer.class.getName().replace(".", File.separator) + ".class\" of Render Player API has been replaced by a file of the same name but a different source.", "  Install Render Player API again to fix this specific problem.", "* Render Player API has been installed in the \"mods\" folder.", "  Deinstall Render Player API and install it again following the installation instructions in the readme file.", "========================================"};
                String[] object = throwable2;
                int renderPlayer = throwable2.length;
                int var9;
                String string2;

                for (var9 = 0; var9 < renderPlayer; ++var9)
                {
                    string2 = object[var9];
                    logger.severe(string2);
                }

                object = throwable2;
                renderPlayer = throwable2.length;

                for (var9 = 0; var9 < renderPlayer; ++var9)
                {
                    string2 = object[var9];
                    System.err.println(string2);
                }

                String var17 = "\n\n";
                String[] var19 = throwable2;
                var9 = throwable2.length;

                for (int var21 = 0; var21 < var9; ++var21)
                {
                    String string3 = var19[var21];
                    var17 = (String)var17 + "\t" + string3 + "\n";
                }

                throw new RuntimeException((String)var17, var14);
            }

            log("Render Player API 1.3 Created");
            isCreated = true;
        }

        if (string == null)
        {
            throw new NullPointerException("Argument \'id\' can not be null");
        }
        else if (class_ == null)
        {
            throw new NullPointerException("Argument \'baseClass\' can not be null");
        }
        else
        {
            Constructor constructor2 = (Constructor)allBaseConstructors.get(string);

            if (constructor2 != null)
            {
                throw new IllegalArgumentException("The class \'" + class_.getName() + "\' can not be registered with the id \'" + string + "\' because the class \'" + constructor2.getDeclaringClass().getName() + "\' has allready been registered with the same id");
            }
            else
            {
                Constructor constructor;

                try
                {
                    constructor = class_.getDeclaredConstructor(Classes);
                }
                catch (Throwable var13)
                {
                    try
                    {
                        constructor = class_.getDeclaredConstructor(Class);
                    }
                    catch (Throwable var12)
                    {
                        throw new IllegalArgumentException("Can not find necessary constructor with one argument of type \'" + RenderPlayerAPI.class.getName() + "\' and eventually a second argument of type \'String\' in the class \'" + class_.getName() + "\'", var13);
                    }
                }

                allBaseConstructors.put(string, constructor);

                if (renderPlayerBaseSorting != null)
                {
                    addSorting(string, allBaseBeforeLocalConstructingSuperiors, renderPlayerBaseSorting.getBeforeLocalConstructingSuperiors());
                    addSorting(string, allBaseBeforeLocalConstructingInferiors, renderPlayerBaseSorting.getBeforeLocalConstructingInferiors());
                    addSorting(string, allBaseAfterLocalConstructingSuperiors, renderPlayerBaseSorting.getAfterLocalConstructingSuperiors());
                    addSorting(string, allBaseAfterLocalConstructingInferiors, renderPlayerBaseSorting.getAfterLocalConstructingInferiors());
                    addDynamicSorting(string, allBaseBeforeDynamicSuperiors, renderPlayerBaseSorting.getDynamicBeforeSuperiors());
                    addDynamicSorting(string, allBaseBeforeDynamicInferiors, renderPlayerBaseSorting.getDynamicBeforeInferiors());
                    addDynamicSorting(string, allBaseOverrideDynamicSuperiors, renderPlayerBaseSorting.getDynamicOverrideSuperiors());
                    addDynamicSorting(string, allBaseOverrideDynamicInferiors, renderPlayerBaseSorting.getDynamicOverrideInferiors());
                    addDynamicSorting(string, allBaseAfterDynamicSuperiors, renderPlayerBaseSorting.getDynamicAfterSuperiors());
                    addDynamicSorting(string, allBaseAfterDynamicInferiors, renderPlayerBaseSorting.getDynamicAfterInferiors());
                    addSorting(string, allBaseBeforeDoRenderShadowAndFireSuperiors, renderPlayerBaseSorting.getBeforeDoRenderShadowAndFireSuperiors());
                    addSorting(string, allBaseBeforeDoRenderShadowAndFireInferiors, renderPlayerBaseSorting.getBeforeDoRenderShadowAndFireInferiors());
                    addSorting(string, allBaseOverrideDoRenderShadowAndFireSuperiors, renderPlayerBaseSorting.getOverrideDoRenderShadowAndFireSuperiors());
                    addSorting(string, allBaseOverrideDoRenderShadowAndFireInferiors, renderPlayerBaseSorting.getOverrideDoRenderShadowAndFireInferiors());
                    addSorting(string, allBaseAfterDoRenderShadowAndFireSuperiors, renderPlayerBaseSorting.getAfterDoRenderShadowAndFireSuperiors());
                    addSorting(string, allBaseAfterDoRenderShadowAndFireInferiors, renderPlayerBaseSorting.getAfterDoRenderShadowAndFireInferiors());
                    addSorting(string, allBaseBeforeGetColorMultiplierSuperiors, renderPlayerBaseSorting.getBeforeGetColorMultiplierSuperiors());
                    addSorting(string, allBaseBeforeGetColorMultiplierInferiors, renderPlayerBaseSorting.getBeforeGetColorMultiplierInferiors());
                    addSorting(string, allBaseOverrideGetColorMultiplierSuperiors, renderPlayerBaseSorting.getOverrideGetColorMultiplierSuperiors());
                    addSorting(string, allBaseOverrideGetColorMultiplierInferiors, renderPlayerBaseSorting.getOverrideGetColorMultiplierInferiors());
                    addSorting(string, allBaseAfterGetColorMultiplierSuperiors, renderPlayerBaseSorting.getAfterGetColorMultiplierSuperiors());
                    addSorting(string, allBaseAfterGetColorMultiplierInferiors, renderPlayerBaseSorting.getAfterGetColorMultiplierInferiors());
                    addSorting(string, allBaseBeforeGetDeathMaxRotationSuperiors, renderPlayerBaseSorting.getBeforeGetDeathMaxRotationSuperiors());
                    addSorting(string, allBaseBeforeGetDeathMaxRotationInferiors, renderPlayerBaseSorting.getBeforeGetDeathMaxRotationInferiors());
                    addSorting(string, allBaseOverrideGetDeathMaxRotationSuperiors, renderPlayerBaseSorting.getOverrideGetDeathMaxRotationSuperiors());
                    addSorting(string, allBaseOverrideGetDeathMaxRotationInferiors, renderPlayerBaseSorting.getOverrideGetDeathMaxRotationInferiors());
                    addSorting(string, allBaseAfterGetDeathMaxRotationSuperiors, renderPlayerBaseSorting.getAfterGetDeathMaxRotationSuperiors());
                    addSorting(string, allBaseAfterGetDeathMaxRotationInferiors, renderPlayerBaseSorting.getAfterGetDeathMaxRotationInferiors());
                    addSorting(string, allBaseBeforeGetFontRendererFromRenderManagerSuperiors, renderPlayerBaseSorting.getBeforeGetFontRendererFromRenderManagerSuperiors());
                    addSorting(string, allBaseBeforeGetFontRendererFromRenderManagerInferiors, renderPlayerBaseSorting.getBeforeGetFontRendererFromRenderManagerInferiors());
                    addSorting(string, allBaseOverrideGetFontRendererFromRenderManagerSuperiors, renderPlayerBaseSorting.getOverrideGetFontRendererFromRenderManagerSuperiors());
                    addSorting(string, allBaseOverrideGetFontRendererFromRenderManagerInferiors, renderPlayerBaseSorting.getOverrideGetFontRendererFromRenderManagerInferiors());
                    addSorting(string, allBaseAfterGetFontRendererFromRenderManagerSuperiors, renderPlayerBaseSorting.getAfterGetFontRendererFromRenderManagerSuperiors());
                    addSorting(string, allBaseAfterGetFontRendererFromRenderManagerInferiors, renderPlayerBaseSorting.getAfterGetFontRendererFromRenderManagerInferiors());
                    addSorting(string, allBaseBeforeHandleRotationFloatSuperiors, renderPlayerBaseSorting.getBeforeHandleRotationFloatSuperiors());
                    addSorting(string, allBaseBeforeHandleRotationFloatInferiors, renderPlayerBaseSorting.getBeforeHandleRotationFloatInferiors());
                    addSorting(string, allBaseOverrideHandleRotationFloatSuperiors, renderPlayerBaseSorting.getOverrideHandleRotationFloatSuperiors());
                    addSorting(string, allBaseOverrideHandleRotationFloatInferiors, renderPlayerBaseSorting.getOverrideHandleRotationFloatInferiors());
                    addSorting(string, allBaseAfterHandleRotationFloatSuperiors, renderPlayerBaseSorting.getAfterHandleRotationFloatSuperiors());
                    addSorting(string, allBaseAfterHandleRotationFloatInferiors, renderPlayerBaseSorting.getAfterHandleRotationFloatInferiors());
                    addSorting(string, allBaseBeforeInheritRenderPassSuperiors, renderPlayerBaseSorting.getBeforeInheritRenderPassSuperiors());
                    addSorting(string, allBaseBeforeInheritRenderPassInferiors, renderPlayerBaseSorting.getBeforeInheritRenderPassInferiors());
                    addSorting(string, allBaseOverrideInheritRenderPassSuperiors, renderPlayerBaseSorting.getOverrideInheritRenderPassSuperiors());
                    addSorting(string, allBaseOverrideInheritRenderPassInferiors, renderPlayerBaseSorting.getOverrideInheritRenderPassInferiors());
                    addSorting(string, allBaseAfterInheritRenderPassSuperiors, renderPlayerBaseSorting.getAfterInheritRenderPassSuperiors());
                    addSorting(string, allBaseAfterInheritRenderPassInferiors, renderPlayerBaseSorting.getAfterInheritRenderPassInferiors());
                    addSorting(string, allBaseBeforeLoadDownloadableImageTextureSuperiors, renderPlayerBaseSorting.getBeforeLoadDownloadableImageTextureSuperiors());
                    addSorting(string, allBaseBeforeLoadDownloadableImageTextureInferiors, renderPlayerBaseSorting.getBeforeLoadDownloadableImageTextureInferiors());
                    addSorting(string, allBaseOverrideLoadDownloadableImageTextureSuperiors, renderPlayerBaseSorting.getOverrideLoadDownloadableImageTextureSuperiors());
                    addSorting(string, allBaseOverrideLoadDownloadableImageTextureInferiors, renderPlayerBaseSorting.getOverrideLoadDownloadableImageTextureInferiors());
                    addSorting(string, allBaseAfterLoadDownloadableImageTextureSuperiors, renderPlayerBaseSorting.getAfterLoadDownloadableImageTextureSuperiors());
                    addSorting(string, allBaseAfterLoadDownloadableImageTextureInferiors, renderPlayerBaseSorting.getAfterLoadDownloadableImageTextureInferiors());
                    addSorting(string, allBaseBeforeLoadPlayerTextureSuperiors, renderPlayerBaseSorting.getBeforeLoadPlayerTextureSuperiors());
                    addSorting(string, allBaseBeforeLoadPlayerTextureInferiors, renderPlayerBaseSorting.getBeforeLoadPlayerTextureInferiors());
                    addSorting(string, allBaseOverrideLoadPlayerTextureSuperiors, renderPlayerBaseSorting.getOverrideLoadPlayerTextureSuperiors());
                    addSorting(string, allBaseOverrideLoadPlayerTextureInferiors, renderPlayerBaseSorting.getOverrideLoadPlayerTextureInferiors());
                    addSorting(string, allBaseAfterLoadPlayerTextureSuperiors, renderPlayerBaseSorting.getAfterLoadPlayerTextureSuperiors());
                    addSorting(string, allBaseAfterLoadPlayerTextureInferiors, renderPlayerBaseSorting.getAfterLoadPlayerTextureInferiors());
                    addSorting(string, allBaseBeforeLoadTextureSuperiors, renderPlayerBaseSorting.getBeforeLoadTextureSuperiors());
                    addSorting(string, allBaseBeforeLoadTextureInferiors, renderPlayerBaseSorting.getBeforeLoadTextureInferiors());
                    addSorting(string, allBaseOverrideLoadTextureSuperiors, renderPlayerBaseSorting.getOverrideLoadTextureSuperiors());
                    addSorting(string, allBaseOverrideLoadTextureInferiors, renderPlayerBaseSorting.getOverrideLoadTextureInferiors());
                    addSorting(string, allBaseAfterLoadTextureSuperiors, renderPlayerBaseSorting.getAfterLoadTextureSuperiors());
                    addSorting(string, allBaseAfterLoadTextureInferiors, renderPlayerBaseSorting.getAfterLoadTextureInferiors());
                    addSorting(string, allBaseBeforePassSpecialRenderSuperiors, renderPlayerBaseSorting.getBeforePassSpecialRenderSuperiors());
                    addSorting(string, allBaseBeforePassSpecialRenderInferiors, renderPlayerBaseSorting.getBeforePassSpecialRenderInferiors());
                    addSorting(string, allBaseOverridePassSpecialRenderSuperiors, renderPlayerBaseSorting.getOverridePassSpecialRenderSuperiors());
                    addSorting(string, allBaseOverridePassSpecialRenderInferiors, renderPlayerBaseSorting.getOverridePassSpecialRenderInferiors());
                    addSorting(string, allBaseAfterPassSpecialRenderSuperiors, renderPlayerBaseSorting.getAfterPassSpecialRenderSuperiors());
                    addSorting(string, allBaseAfterPassSpecialRenderInferiors, renderPlayerBaseSorting.getAfterPassSpecialRenderInferiors());
                    addSorting(string, allBaseBeforeRenderArrowsStuckInEntitySuperiors, renderPlayerBaseSorting.getBeforeRenderArrowsStuckInEntitySuperiors());
                    addSorting(string, allBaseBeforeRenderArrowsStuckInEntityInferiors, renderPlayerBaseSorting.getBeforeRenderArrowsStuckInEntityInferiors());
                    addSorting(string, allBaseOverrideRenderArrowsStuckInEntitySuperiors, renderPlayerBaseSorting.getOverrideRenderArrowsStuckInEntitySuperiors());
                    addSorting(string, allBaseOverrideRenderArrowsStuckInEntityInferiors, renderPlayerBaseSorting.getOverrideRenderArrowsStuckInEntityInferiors());
                    addSorting(string, allBaseAfterRenderArrowsStuckInEntitySuperiors, renderPlayerBaseSorting.getAfterRenderArrowsStuckInEntitySuperiors());
                    addSorting(string, allBaseAfterRenderArrowsStuckInEntityInferiors, renderPlayerBaseSorting.getAfterRenderArrowsStuckInEntityInferiors());
                    addSorting(string, allBaseBeforeRenderFirstPersonArmSuperiors, renderPlayerBaseSorting.getBeforeRenderFirstPersonArmSuperiors());
                    addSorting(string, allBaseBeforeRenderFirstPersonArmInferiors, renderPlayerBaseSorting.getBeforeRenderFirstPersonArmInferiors());
                    addSorting(string, allBaseOverrideRenderFirstPersonArmSuperiors, renderPlayerBaseSorting.getOverrideRenderFirstPersonArmSuperiors());
                    addSorting(string, allBaseOverrideRenderFirstPersonArmInferiors, renderPlayerBaseSorting.getOverrideRenderFirstPersonArmInferiors());
                    addSorting(string, allBaseAfterRenderFirstPersonArmSuperiors, renderPlayerBaseSorting.getAfterRenderFirstPersonArmSuperiors());
                    addSorting(string, allBaseAfterRenderFirstPersonArmInferiors, renderPlayerBaseSorting.getAfterRenderFirstPersonArmInferiors());
                    addSorting(string, allBaseBeforeRenderLivingLabelSuperiors, renderPlayerBaseSorting.getBeforeRenderLivingLabelSuperiors());
                    addSorting(string, allBaseBeforeRenderLivingLabelInferiors, renderPlayerBaseSorting.getBeforeRenderLivingLabelInferiors());
                    addSorting(string, allBaseOverrideRenderLivingLabelSuperiors, renderPlayerBaseSorting.getOverrideRenderLivingLabelSuperiors());
                    addSorting(string, allBaseOverrideRenderLivingLabelInferiors, renderPlayerBaseSorting.getOverrideRenderLivingLabelInferiors());
                    addSorting(string, allBaseAfterRenderLivingLabelSuperiors, renderPlayerBaseSorting.getAfterRenderLivingLabelSuperiors());
                    addSorting(string, allBaseAfterRenderLivingLabelInferiors, renderPlayerBaseSorting.getAfterRenderLivingLabelInferiors());
                    addSorting(string, allBaseBeforeRenderModelSuperiors, renderPlayerBaseSorting.getBeforeRenderModelSuperiors());
                    addSorting(string, allBaseBeforeRenderModelInferiors, renderPlayerBaseSorting.getBeforeRenderModelInferiors());
                    addSorting(string, allBaseOverrideRenderModelSuperiors, renderPlayerBaseSorting.getOverrideRenderModelSuperiors());
                    addSorting(string, allBaseOverrideRenderModelInferiors, renderPlayerBaseSorting.getOverrideRenderModelInferiors());
                    addSorting(string, allBaseAfterRenderModelSuperiors, renderPlayerBaseSorting.getAfterRenderModelSuperiors());
                    addSorting(string, allBaseAfterRenderModelInferiors, renderPlayerBaseSorting.getAfterRenderModelInferiors());
                    addSorting(string, allBaseBeforeRenderPlayerSuperiors, renderPlayerBaseSorting.getBeforeRenderPlayerSuperiors());
                    addSorting(string, allBaseBeforeRenderPlayerInferiors, renderPlayerBaseSorting.getBeforeRenderPlayerInferiors());
                    addSorting(string, allBaseOverrideRenderPlayerSuperiors, renderPlayerBaseSorting.getOverrideRenderPlayerSuperiors());
                    addSorting(string, allBaseOverrideRenderPlayerInferiors, renderPlayerBaseSorting.getOverrideRenderPlayerInferiors());
                    addSorting(string, allBaseAfterRenderPlayerSuperiors, renderPlayerBaseSorting.getAfterRenderPlayerSuperiors());
                    addSorting(string, allBaseAfterRenderPlayerInferiors, renderPlayerBaseSorting.getAfterRenderPlayerInferiors());
                    addSorting(string, allBaseBeforeRenderPlayerNameAndScoreLabelSuperiors, renderPlayerBaseSorting.getBeforeRenderPlayerNameAndScoreLabelSuperiors());
                    addSorting(string, allBaseBeforeRenderPlayerNameAndScoreLabelInferiors, renderPlayerBaseSorting.getBeforeRenderPlayerNameAndScoreLabelInferiors());
                    addSorting(string, allBaseOverrideRenderPlayerNameAndScoreLabelSuperiors, renderPlayerBaseSorting.getOverrideRenderPlayerNameAndScoreLabelSuperiors());
                    addSorting(string, allBaseOverrideRenderPlayerNameAndScoreLabelInferiors, renderPlayerBaseSorting.getOverrideRenderPlayerNameAndScoreLabelInferiors());
                    addSorting(string, allBaseAfterRenderPlayerNameAndScoreLabelSuperiors, renderPlayerBaseSorting.getAfterRenderPlayerNameAndScoreLabelSuperiors());
                    addSorting(string, allBaseAfterRenderPlayerNameAndScoreLabelInferiors, renderPlayerBaseSorting.getAfterRenderPlayerNameAndScoreLabelInferiors());
                    addSorting(string, allBaseBeforeRenderPlayerScaleSuperiors, renderPlayerBaseSorting.getBeforeRenderPlayerScaleSuperiors());
                    addSorting(string, allBaseBeforeRenderPlayerScaleInferiors, renderPlayerBaseSorting.getBeforeRenderPlayerScaleInferiors());
                    addSorting(string, allBaseOverrideRenderPlayerScaleSuperiors, renderPlayerBaseSorting.getOverrideRenderPlayerScaleSuperiors());
                    addSorting(string, allBaseOverrideRenderPlayerScaleInferiors, renderPlayerBaseSorting.getOverrideRenderPlayerScaleInferiors());
                    addSorting(string, allBaseAfterRenderPlayerScaleSuperiors, renderPlayerBaseSorting.getAfterRenderPlayerScaleSuperiors());
                    addSorting(string, allBaseAfterRenderPlayerScaleInferiors, renderPlayerBaseSorting.getAfterRenderPlayerScaleInferiors());
                    addSorting(string, allBaseBeforeRenderPlayerSleepSuperiors, renderPlayerBaseSorting.getBeforeRenderPlayerSleepSuperiors());
                    addSorting(string, allBaseBeforeRenderPlayerSleepInferiors, renderPlayerBaseSorting.getBeforeRenderPlayerSleepInferiors());
                    addSorting(string, allBaseOverrideRenderPlayerSleepSuperiors, renderPlayerBaseSorting.getOverrideRenderPlayerSleepSuperiors());
                    addSorting(string, allBaseOverrideRenderPlayerSleepInferiors, renderPlayerBaseSorting.getOverrideRenderPlayerSleepInferiors());
                    addSorting(string, allBaseAfterRenderPlayerSleepSuperiors, renderPlayerBaseSorting.getAfterRenderPlayerSleepSuperiors());
                    addSorting(string, allBaseAfterRenderPlayerSleepInferiors, renderPlayerBaseSorting.getAfterRenderPlayerSleepInferiors());
                    addSorting(string, allBaseBeforeRenderSpecialsSuperiors, renderPlayerBaseSorting.getBeforeRenderSpecialsSuperiors());
                    addSorting(string, allBaseBeforeRenderSpecialsInferiors, renderPlayerBaseSorting.getBeforeRenderSpecialsInferiors());
                    addSorting(string, allBaseOverrideRenderSpecialsSuperiors, renderPlayerBaseSorting.getOverrideRenderSpecialsSuperiors());
                    addSorting(string, allBaseOverrideRenderSpecialsInferiors, renderPlayerBaseSorting.getOverrideRenderSpecialsInferiors());
                    addSorting(string, allBaseAfterRenderSpecialsSuperiors, renderPlayerBaseSorting.getAfterRenderSpecialsSuperiors());
                    addSorting(string, allBaseAfterRenderSpecialsInferiors, renderPlayerBaseSorting.getAfterRenderSpecialsInferiors());
                    addSorting(string, allBaseBeforeRenderSwingProgressSuperiors, renderPlayerBaseSorting.getBeforeRenderSwingProgressSuperiors());
                    addSorting(string, allBaseBeforeRenderSwingProgressInferiors, renderPlayerBaseSorting.getBeforeRenderSwingProgressInferiors());
                    addSorting(string, allBaseOverrideRenderSwingProgressSuperiors, renderPlayerBaseSorting.getOverrideRenderSwingProgressSuperiors());
                    addSorting(string, allBaseOverrideRenderSwingProgressInferiors, renderPlayerBaseSorting.getOverrideRenderSwingProgressInferiors());
                    addSorting(string, allBaseAfterRenderSwingProgressSuperiors, renderPlayerBaseSorting.getAfterRenderSwingProgressSuperiors());
                    addSorting(string, allBaseAfterRenderSwingProgressInferiors, renderPlayerBaseSorting.getAfterRenderSwingProgressInferiors());
                    addSorting(string, allBaseBeforeRotatePlayerSuperiors, renderPlayerBaseSorting.getBeforeRotatePlayerSuperiors());
                    addSorting(string, allBaseBeforeRotatePlayerInferiors, renderPlayerBaseSorting.getBeforeRotatePlayerInferiors());
                    addSorting(string, allBaseOverrideRotatePlayerSuperiors, renderPlayerBaseSorting.getOverrideRotatePlayerSuperiors());
                    addSorting(string, allBaseOverrideRotatePlayerInferiors, renderPlayerBaseSorting.getOverrideRotatePlayerInferiors());
                    addSorting(string, allBaseAfterRotatePlayerSuperiors, renderPlayerBaseSorting.getAfterRotatePlayerSuperiors());
                    addSorting(string, allBaseAfterRotatePlayerInferiors, renderPlayerBaseSorting.getAfterRotatePlayerInferiors());
                    addSorting(string, allBaseBeforeSetArmorModelSuperiors, renderPlayerBaseSorting.getBeforeSetArmorModelSuperiors());
                    addSorting(string, allBaseBeforeSetArmorModelInferiors, renderPlayerBaseSorting.getBeforeSetArmorModelInferiors());
                    addSorting(string, allBaseOverrideSetArmorModelSuperiors, renderPlayerBaseSorting.getOverrideSetArmorModelSuperiors());
                    addSorting(string, allBaseOverrideSetArmorModelInferiors, renderPlayerBaseSorting.getOverrideSetArmorModelInferiors());
                    addSorting(string, allBaseAfterSetArmorModelSuperiors, renderPlayerBaseSorting.getAfterSetArmorModelSuperiors());
                    addSorting(string, allBaseAfterSetArmorModelInferiors, renderPlayerBaseSorting.getAfterSetArmorModelInferiors());
                    addSorting(string, allBaseBeforeSetIconRegisterSuperiors, renderPlayerBaseSorting.getBeforeSetIconRegisterSuperiors());
                    addSorting(string, allBaseBeforeSetIconRegisterInferiors, renderPlayerBaseSorting.getBeforeSetIconRegisterInferiors());
                    addSorting(string, allBaseOverrideSetIconRegisterSuperiors, renderPlayerBaseSorting.getOverrideSetIconRegisterSuperiors());
                    addSorting(string, allBaseOverrideSetIconRegisterInferiors, renderPlayerBaseSorting.getOverrideSetIconRegisterInferiors());
                    addSorting(string, allBaseAfterSetIconRegisterSuperiors, renderPlayerBaseSorting.getAfterSetIconRegisterSuperiors());
                    addSorting(string, allBaseAfterSetIconRegisterInferiors, renderPlayerBaseSorting.getAfterSetIconRegisterInferiors());
                    addSorting(string, allBaseBeforeSetPassArmorModelSuperiors, renderPlayerBaseSorting.getBeforeSetPassArmorModelSuperiors());
                    addSorting(string, allBaseBeforeSetPassArmorModelInferiors, renderPlayerBaseSorting.getBeforeSetPassArmorModelInferiors());
                    addSorting(string, allBaseOverrideSetPassArmorModelSuperiors, renderPlayerBaseSorting.getOverrideSetPassArmorModelSuperiors());
                    addSorting(string, allBaseOverrideSetPassArmorModelInferiors, renderPlayerBaseSorting.getOverrideSetPassArmorModelInferiors());
                    addSorting(string, allBaseAfterSetPassArmorModelSuperiors, renderPlayerBaseSorting.getAfterSetPassArmorModelSuperiors());
                    addSorting(string, allBaseAfterSetPassArmorModelInferiors, renderPlayerBaseSorting.getAfterSetPassArmorModelInferiors());
                    addSorting(string, allBaseBeforeSetRenderManagerSuperiors, renderPlayerBaseSorting.getBeforeSetRenderManagerSuperiors());
                    addSorting(string, allBaseBeforeSetRenderManagerInferiors, renderPlayerBaseSorting.getBeforeSetRenderManagerInferiors());
                    addSorting(string, allBaseOverrideSetRenderManagerSuperiors, renderPlayerBaseSorting.getOverrideSetRenderManagerSuperiors());
                    addSorting(string, allBaseOverrideSetRenderManagerInferiors, renderPlayerBaseSorting.getOverrideSetRenderManagerInferiors());
                    addSorting(string, allBaseAfterSetRenderManagerSuperiors, renderPlayerBaseSorting.getAfterSetRenderManagerSuperiors());
                    addSorting(string, allBaseAfterSetRenderManagerInferiors, renderPlayerBaseSorting.getAfterSetRenderManagerInferiors());
                    addSorting(string, allBaseBeforeSetRenderPassModelSuperiors, renderPlayerBaseSorting.getBeforeSetRenderPassModelSuperiors());
                    addSorting(string, allBaseBeforeSetRenderPassModelInferiors, renderPlayerBaseSorting.getBeforeSetRenderPassModelInferiors());
                    addSorting(string, allBaseOverrideSetRenderPassModelSuperiors, renderPlayerBaseSorting.getOverrideSetRenderPassModelSuperiors());
                    addSorting(string, allBaseOverrideSetRenderPassModelInferiors, renderPlayerBaseSorting.getOverrideSetRenderPassModelInferiors());
                    addSorting(string, allBaseAfterSetRenderPassModelSuperiors, renderPlayerBaseSorting.getAfterSetRenderPassModelSuperiors());
                    addSorting(string, allBaseAfterSetRenderPassModelInferiors, renderPlayerBaseSorting.getAfterSetRenderPassModelInferiors());
                    addSorting(string, allBaseBeforeRenderSpecialHeadArmorSuperiors, renderPlayerBaseSorting.getBeforeRenderSpecialHeadArmorSuperiors());
                    addSorting(string, allBaseBeforeRenderSpecialHeadArmorInferiors, renderPlayerBaseSorting.getBeforeRenderSpecialHeadArmorInferiors());
                    addSorting(string, allBaseOverrideRenderSpecialHeadArmorSuperiors, renderPlayerBaseSorting.getOverrideRenderSpecialHeadArmorSuperiors());
                    addSorting(string, allBaseOverrideRenderSpecialHeadArmorInferiors, renderPlayerBaseSorting.getOverrideRenderSpecialHeadArmorInferiors());
                    addSorting(string, allBaseAfterRenderSpecialHeadArmorSuperiors, renderPlayerBaseSorting.getAfterRenderSpecialHeadArmorSuperiors());
                    addSorting(string, allBaseAfterRenderSpecialHeadArmorInferiors, renderPlayerBaseSorting.getAfterRenderSpecialHeadArmorInferiors());
                    addSorting(string, allBaseBeforeRenderSpecialHeadEarsSuperiors, renderPlayerBaseSorting.getBeforeRenderSpecialHeadEarsSuperiors());
                    addSorting(string, allBaseBeforeRenderSpecialHeadEarsInferiors, renderPlayerBaseSorting.getBeforeRenderSpecialHeadEarsInferiors());
                    addSorting(string, allBaseOverrideRenderSpecialHeadEarsSuperiors, renderPlayerBaseSorting.getOverrideRenderSpecialHeadEarsSuperiors());
                    addSorting(string, allBaseOverrideRenderSpecialHeadEarsInferiors, renderPlayerBaseSorting.getOverrideRenderSpecialHeadEarsInferiors());
                    addSorting(string, allBaseAfterRenderSpecialHeadEarsSuperiors, renderPlayerBaseSorting.getAfterRenderSpecialHeadEarsSuperiors());
                    addSorting(string, allBaseAfterRenderSpecialHeadEarsInferiors, renderPlayerBaseSorting.getAfterRenderSpecialHeadEarsInferiors());
                    addSorting(string, allBaseBeforeRenderSpecialCloakSuperiors, renderPlayerBaseSorting.getBeforeRenderSpecialCloakSuperiors());
                    addSorting(string, allBaseBeforeRenderSpecialCloakInferiors, renderPlayerBaseSorting.getBeforeRenderSpecialCloakInferiors());
                    addSorting(string, allBaseOverrideRenderSpecialCloakSuperiors, renderPlayerBaseSorting.getOverrideRenderSpecialCloakSuperiors());
                    addSorting(string, allBaseOverrideRenderSpecialCloakInferiors, renderPlayerBaseSorting.getOverrideRenderSpecialCloakInferiors());
                    addSorting(string, allBaseAfterRenderSpecialCloakSuperiors, renderPlayerBaseSorting.getAfterRenderSpecialCloakSuperiors());
                    addSorting(string, allBaseAfterRenderSpecialCloakInferiors, renderPlayerBaseSorting.getAfterRenderSpecialCloakInferiors());
                    addSorting(string, allBaseBeforeRenderSpecialItemInHandSuperiors, renderPlayerBaseSorting.getBeforeRenderSpecialItemInHandSuperiors());
                    addSorting(string, allBaseBeforeRenderSpecialItemInHandInferiors, renderPlayerBaseSorting.getBeforeRenderSpecialItemInHandInferiors());
                    addSorting(string, allBaseOverrideRenderSpecialItemInHandSuperiors, renderPlayerBaseSorting.getOverrideRenderSpecialItemInHandSuperiors());
                    addSorting(string, allBaseOverrideRenderSpecialItemInHandInferiors, renderPlayerBaseSorting.getOverrideRenderSpecialItemInHandInferiors());
                    addSorting(string, allBaseAfterRenderSpecialItemInHandSuperiors, renderPlayerBaseSorting.getAfterRenderSpecialItemInHandSuperiors());
                    addSorting(string, allBaseAfterRenderSpecialItemInHandInferiors, renderPlayerBaseSorting.getAfterRenderSpecialItemInHandInferiors());
                    addSorting(string, allBaseBeforePositionSpecialItemInHandSuperiors, renderPlayerBaseSorting.getBeforePositionSpecialItemInHandSuperiors());
                    addSorting(string, allBaseBeforePositionSpecialItemInHandInferiors, renderPlayerBaseSorting.getBeforePositionSpecialItemInHandInferiors());
                    addSorting(string, allBaseOverridePositionSpecialItemInHandSuperiors, renderPlayerBaseSorting.getOverridePositionSpecialItemInHandSuperiors());
                    addSorting(string, allBaseOverridePositionSpecialItemInHandInferiors, renderPlayerBaseSorting.getOverridePositionSpecialItemInHandInferiors());
                    addSorting(string, allBaseAfterPositionSpecialItemInHandSuperiors, renderPlayerBaseSorting.getAfterPositionSpecialItemInHandSuperiors());
                    addSorting(string, allBaseAfterPositionSpecialItemInHandInferiors, renderPlayerBaseSorting.getAfterPositionSpecialItemInHandInferiors());
                }

                addMethod(string, class_, beforeLocalConstructingHookTypes, "beforeLocalConstructing", new Class[0]);
                addMethod(string, class_, afterLocalConstructingHookTypes, "afterLocalConstructing", new Class[0]);
                addMethod(string, class_, beforeDoRenderShadowAndFireHookTypes, "beforeDoRenderShadowAndFire", new Class[] {Entity.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, overrideDoRenderShadowAndFireHookTypes, "doRenderShadowAndFire", new Class[] {Entity.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, afterDoRenderShadowAndFireHookTypes, "afterDoRenderShadowAndFire", new Class[] {Entity.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, beforeGetColorMultiplierHookTypes, "beforeGetColorMultiplier", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE});
                addMethod(string, class_, overrideGetColorMultiplierHookTypes, "getColorMultiplier", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE});
                addMethod(string, class_, afterGetColorMultiplierHookTypes, "afterGetColorMultiplier", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE});
                addMethod(string, class_, beforeGetDeathMaxRotationHookTypes, "beforeGetDeathMaxRotation", new Class[] {EntityLiving.class});
                addMethod(string, class_, overrideGetDeathMaxRotationHookTypes, "getDeathMaxRotation", new Class[] {EntityLiving.class});
                addMethod(string, class_, afterGetDeathMaxRotationHookTypes, "afterGetDeathMaxRotation", new Class[] {EntityLiving.class});
                addMethod(string, class_, beforeGetFontRendererFromRenderManagerHookTypes, "beforeGetFontRendererFromRenderManager", new Class[0]);
                addMethod(string, class_, overrideGetFontRendererFromRenderManagerHookTypes, "getFontRendererFromRenderManager", new Class[0]);
                addMethod(string, class_, afterGetFontRendererFromRenderManagerHookTypes, "afterGetFontRendererFromRenderManager", new Class[0]);
                addMethod(string, class_, beforeHandleRotationFloatHookTypes, "beforeHandleRotationFloat", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, overrideHandleRotationFloatHookTypes, "handleRotationFloat", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, afterHandleRotationFloatHookTypes, "afterHandleRotationFloat", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, beforeInheritRenderPassHookTypes, "beforeInheritRenderPass", new Class[] {EntityLiving.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, overrideInheritRenderPassHookTypes, "inheritRenderPass", new Class[] {EntityLiving.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, afterInheritRenderPassHookTypes, "afterInheritRenderPass", new Class[] {EntityLiving.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, beforeLoadDownloadableImageTextureHookTypes, "beforeLoadDownloadableImageTexture", new Class[] {String.class, String.class});
                addMethod(string, class_, overrideLoadDownloadableImageTextureHookTypes, "loadDownloadableImageTexture", new Class[] {String.class, String.class});
                addMethod(string, class_, afterLoadDownloadableImageTextureHookTypes, "afterLoadDownloadableImageTexture", new Class[] {String.class, String.class});
                addMethod(string, class_, beforeLoadPlayerTextureHookTypes, "beforeLoadPlayerTexture", new Class[] {EntityPlayer.class});
                addMethod(string, class_, overrideLoadPlayerTextureHookTypes, "loadPlayerTexture", new Class[] {EntityPlayer.class});
                addMethod(string, class_, afterLoadPlayerTextureHookTypes, "afterLoadPlayerTexture", new Class[] {EntityPlayer.class});
                addMethod(string, class_, beforeLoadTextureHookTypes, "beforeLoadTexture", new Class[] {String.class});
                addMethod(string, class_, overrideLoadTextureHookTypes, "loadTexture", new Class[] {String.class});
                addMethod(string, class_, afterLoadTextureHookTypes, "afterLoadTexture", new Class[] {String.class});
                addMethod(string, class_, beforePassSpecialRenderHookTypes, "beforePassSpecialRender", new Class[] {EntityLiving.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(string, class_, overridePassSpecialRenderHookTypes, "passSpecialRender", new Class[] {EntityLiving.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(string, class_, afterPassSpecialRenderHookTypes, "afterPassSpecialRender", new Class[] {EntityLiving.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(string, class_, beforeRenderArrowsStuckInEntityHookTypes, "beforeRenderArrowsStuckInEntity", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, overrideRenderArrowsStuckInEntityHookTypes, "renderArrowsStuckInEntity", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, afterRenderArrowsStuckInEntityHookTypes, "afterRenderArrowsStuckInEntity", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, beforeRenderFirstPersonArmHookTypes, "beforeRenderFirstPersonArm", new Class[] {EntityPlayer.class});
                addMethod(string, class_, overrideRenderFirstPersonArmHookTypes, "renderFirstPersonArm", new Class[] {EntityPlayer.class});
                addMethod(string, class_, afterRenderFirstPersonArmHookTypes, "afterRenderFirstPersonArm", new Class[] {EntityPlayer.class});
                addMethod(string, class_, beforeRenderLivingLabelHookTypes, "beforeRenderLivingLabel", new Class[] {EntityLiving.class, String.class, Double.TYPE, Double.TYPE, Double.TYPE, Integer.TYPE});
                addMethod(string, class_, overrideRenderLivingLabelHookTypes, "renderLivingLabel", new Class[] {EntityLiving.class, String.class, Double.TYPE, Double.TYPE, Double.TYPE, Integer.TYPE});
                addMethod(string, class_, afterRenderLivingLabelHookTypes, "afterRenderLivingLabel", new Class[] {EntityLiving.class, String.class, Double.TYPE, Double.TYPE, Double.TYPE, Integer.TYPE});
                addMethod(string, class_, beforeRenderModelHookTypes, "beforeRenderModel", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, overrideRenderModelHookTypes, "renderModel", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, afterRenderModelHookTypes, "afterRenderModel", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, beforeRenderPlayerHookTypes, "beforeRenderPlayer", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, overrideRenderPlayerHookTypes, "renderPlayer", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, afterRenderPlayerHookTypes, "afterRenderPlayer", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, beforeRenderPlayerNameAndScoreLabelHookTypes, "beforeRenderPlayerNameAndScoreLabel", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, String.class, Float.TYPE, Double.TYPE});
                addMethod(string, class_, overrideRenderPlayerNameAndScoreLabelHookTypes, "renderPlayerNameAndScoreLabel", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, String.class, Float.TYPE, Double.TYPE});
                addMethod(string, class_, afterRenderPlayerNameAndScoreLabelHookTypes, "afterRenderPlayerNameAndScoreLabel", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, String.class, Float.TYPE, Double.TYPE});
                addMethod(string, class_, beforeRenderPlayerScaleHookTypes, "beforeRenderPlayerScale", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, overrideRenderPlayerScaleHookTypes, "renderPlayerScale", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, afterRenderPlayerScaleHookTypes, "afterRenderPlayerScale", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, beforeRenderPlayerSleepHookTypes, "beforeRenderPlayerSleep", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(string, class_, overrideRenderPlayerSleepHookTypes, "renderPlayerSleep", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(string, class_, afterRenderPlayerSleepHookTypes, "afterRenderPlayerSleep", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(string, class_, beforeRenderSpecialsHookTypes, "beforeRenderSpecials", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, overrideRenderSpecialsHookTypes, "renderSpecials", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, afterRenderSpecialsHookTypes, "afterRenderSpecials", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, beforeRenderSwingProgressHookTypes, "beforeRenderSwingProgress", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, overrideRenderSwingProgressHookTypes, "renderSwingProgress", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, afterRenderSwingProgressHookTypes, "afterRenderSwingProgress", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(string, class_, beforeRotatePlayerHookTypes, "beforeRotatePlayer", new Class[] {EntityPlayer.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, overrideRotatePlayerHookTypes, "rotatePlayer", new Class[] {EntityPlayer.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, afterRotatePlayerHookTypes, "afterRotatePlayer", new Class[] {EntityPlayer.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(string, class_, beforeSetArmorModelHookTypes, "beforeSetArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, overrideSetArmorModelHookTypes, "setArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, afterSetArmorModelHookTypes, "afterSetArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, beforeSetIconRegisterHookTypes, "beforeSetIconRegister", new Class[] {IconRegister.class});
                addMethod(string, class_, overrideSetIconRegisterHookTypes, "setIconRegister", new Class[] {IconRegister.class});
                addMethod(string, class_, afterSetIconRegisterHookTypes, "afterSetIconRegister", new Class[] {IconRegister.class});
                addMethod(string, class_, beforeSetPassArmorModelHookTypes, "beforeSetPassArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, overrideSetPassArmorModelHookTypes, "setPassArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, afterSetPassArmorModelHookTypes, "afterSetPassArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(string, class_, beforeSetRenderManagerHookTypes, "beforeSetRenderManager", new Class[] {RenderManager.class});
                addMethod(string, class_, overrideSetRenderManagerHookTypes, "setRenderManager", new Class[] {RenderManager.class});
                addMethod(string, class_, afterSetRenderManagerHookTypes, "afterSetRenderManager", new Class[] {RenderManager.class});
                addMethod(string, class_, beforeSetRenderPassModelHookTypes, "beforeSetRenderPassModel", new Class[] {ModelBase.class});
                addMethod(string, class_, overrideSetRenderPassModelHookTypes, "setRenderPassModel", new Class[] {ModelBase.class});
                addMethod(string, class_, afterSetRenderPassModelHookTypes, "afterSetRenderPassModel", new Class[] {ModelBase.class});
                addMethod(string, class_, beforeRenderSpecialHeadArmorHookTypes, "beforeRenderSpecialHeadArmor", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, overrideRenderSpecialHeadArmorHookTypes, "renderSpecialHeadArmor", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, afterRenderSpecialHeadArmorHookTypes, "afterRenderSpecialHeadArmor", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, beforeRenderSpecialHeadEarsHookTypes, "beforeRenderSpecialHeadEars", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, overrideRenderSpecialHeadEarsHookTypes, "renderSpecialHeadEars", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, afterRenderSpecialHeadEarsHookTypes, "afterRenderSpecialHeadEars", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, beforeRenderSpecialCloakHookTypes, "beforeRenderSpecialCloak", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, overrideRenderSpecialCloakHookTypes, "renderSpecialCloak", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, afterRenderSpecialCloakHookTypes, "afterRenderSpecialCloak", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, beforeRenderSpecialItemInHandHookTypes, "beforeRenderSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, overrideRenderSpecialItemInHandHookTypes, "renderSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, afterRenderSpecialItemInHandHookTypes, "afterRenderSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(string, class_, beforePositionSpecialItemInHandHookTypes, "beforePositionSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE, EnumAction.class, ItemStack.class});
                addMethod(string, class_, overridePositionSpecialItemInHandHookTypes, "positionSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE, EnumAction.class, ItemStack.class});
                addMethod(string, class_, afterPositionSpecialItemInHandHookTypes, "afterPositionSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE, EnumAction.class, ItemStack.class});
                addDynamicMethods(string, class_);
                addDynamicKeys(string, class_, beforeDynamicHookMethods, beforeDynamicHookTypes);
                addDynamicKeys(string, class_, overrideDynamicHookMethods, overrideDynamicHookTypes);
                addDynamicKeys(string, class_, afterDynamicHookMethods, afterDynamicHookTypes);
                initialize();
                RenderPlayer[] var15 = RenderPlayer.getAllInstances();
                int var16 = var15.length;

                for (int var18 = 0; var18 < var16; ++var18)
                {
                    RenderPlayer var20 = var15[var18];
                    var20.renderPlayerAPI.attachRenderPlayerBase(string);
                }

                System.out.println("Render Player API: registered " + string);
                logger.fine("Render Player API: registered class \'" + class_.getName() + "\' with id \'" + string + "\'");
                initialized = false;
            }
        }
    }

    public static boolean unregister(String string)
    {
        if (string == null)
        {
            return false;
        }
        else
        {
            Constructor constructor = (Constructor)allBaseConstructors.remove(string);

            if (constructor == null)
            {
                return false;
            }
            else
            {
                RenderPlayer[] n = RenderPlayer.getAllInstances();
                int class_ = n.length;

                for (int var4 = 0; var4 < class_; ++var4)
                {
                    RenderPlayer string2 = n[var4];
                    string2.renderPlayerAPI.detachRenderPlayerBase(string);
                }

                beforeLocalConstructingHookTypes.remove(string);
                afterLocalConstructingHookTypes.remove(string);
                allBaseBeforeDoRenderShadowAndFireSuperiors.remove(string);
                allBaseBeforeDoRenderShadowAndFireInferiors.remove(string);
                allBaseOverrideDoRenderShadowAndFireSuperiors.remove(string);
                allBaseOverrideDoRenderShadowAndFireInferiors.remove(string);
                allBaseAfterDoRenderShadowAndFireSuperiors.remove(string);
                allBaseAfterDoRenderShadowAndFireInferiors.remove(string);
                beforeDoRenderShadowAndFireHookTypes.remove(string);
                overrideDoRenderShadowAndFireHookTypes.remove(string);
                afterDoRenderShadowAndFireHookTypes.remove(string);
                allBaseBeforeGetColorMultiplierSuperiors.remove(string);
                allBaseBeforeGetColorMultiplierInferiors.remove(string);
                allBaseOverrideGetColorMultiplierSuperiors.remove(string);
                allBaseOverrideGetColorMultiplierInferiors.remove(string);
                allBaseAfterGetColorMultiplierSuperiors.remove(string);
                allBaseAfterGetColorMultiplierInferiors.remove(string);
                beforeGetColorMultiplierHookTypes.remove(string);
                overrideGetColorMultiplierHookTypes.remove(string);
                afterGetColorMultiplierHookTypes.remove(string);
                allBaseBeforeGetDeathMaxRotationSuperiors.remove(string);
                allBaseBeforeGetDeathMaxRotationInferiors.remove(string);
                allBaseOverrideGetDeathMaxRotationSuperiors.remove(string);
                allBaseOverrideGetDeathMaxRotationInferiors.remove(string);
                allBaseAfterGetDeathMaxRotationSuperiors.remove(string);
                allBaseAfterGetDeathMaxRotationInferiors.remove(string);
                beforeGetDeathMaxRotationHookTypes.remove(string);
                overrideGetDeathMaxRotationHookTypes.remove(string);
                afterGetDeathMaxRotationHookTypes.remove(string);
                allBaseBeforeGetFontRendererFromRenderManagerSuperiors.remove(string);
                allBaseBeforeGetFontRendererFromRenderManagerInferiors.remove(string);
                allBaseOverrideGetFontRendererFromRenderManagerSuperiors.remove(string);
                allBaseOverrideGetFontRendererFromRenderManagerInferiors.remove(string);
                allBaseAfterGetFontRendererFromRenderManagerSuperiors.remove(string);
                allBaseAfterGetFontRendererFromRenderManagerInferiors.remove(string);
                beforeGetFontRendererFromRenderManagerHookTypes.remove(string);
                overrideGetFontRendererFromRenderManagerHookTypes.remove(string);
                afterGetFontRendererFromRenderManagerHookTypes.remove(string);
                allBaseBeforeHandleRotationFloatSuperiors.remove(string);
                allBaseBeforeHandleRotationFloatInferiors.remove(string);
                allBaseOverrideHandleRotationFloatSuperiors.remove(string);
                allBaseOverrideHandleRotationFloatInferiors.remove(string);
                allBaseAfterHandleRotationFloatSuperiors.remove(string);
                allBaseAfterHandleRotationFloatInferiors.remove(string);
                beforeHandleRotationFloatHookTypes.remove(string);
                overrideHandleRotationFloatHookTypes.remove(string);
                afterHandleRotationFloatHookTypes.remove(string);
                allBaseBeforeInheritRenderPassSuperiors.remove(string);
                allBaseBeforeInheritRenderPassInferiors.remove(string);
                allBaseOverrideInheritRenderPassSuperiors.remove(string);
                allBaseOverrideInheritRenderPassInferiors.remove(string);
                allBaseAfterInheritRenderPassSuperiors.remove(string);
                allBaseAfterInheritRenderPassInferiors.remove(string);
                beforeInheritRenderPassHookTypes.remove(string);
                overrideInheritRenderPassHookTypes.remove(string);
                afterInheritRenderPassHookTypes.remove(string);
                allBaseBeforeLoadDownloadableImageTextureSuperiors.remove(string);
                allBaseBeforeLoadDownloadableImageTextureInferiors.remove(string);
                allBaseOverrideLoadDownloadableImageTextureSuperiors.remove(string);
                allBaseOverrideLoadDownloadableImageTextureInferiors.remove(string);
                allBaseAfterLoadDownloadableImageTextureSuperiors.remove(string);
                allBaseAfterLoadDownloadableImageTextureInferiors.remove(string);
                beforeLoadDownloadableImageTextureHookTypes.remove(string);
                overrideLoadDownloadableImageTextureHookTypes.remove(string);
                afterLoadDownloadableImageTextureHookTypes.remove(string);
                allBaseBeforeLoadPlayerTextureSuperiors.remove(string);
                allBaseBeforeLoadPlayerTextureInferiors.remove(string);
                allBaseOverrideLoadPlayerTextureSuperiors.remove(string);
                allBaseOverrideLoadPlayerTextureInferiors.remove(string);
                allBaseAfterLoadPlayerTextureSuperiors.remove(string);
                allBaseAfterLoadPlayerTextureInferiors.remove(string);
                beforeLoadPlayerTextureHookTypes.remove(string);
                overrideLoadPlayerTextureHookTypes.remove(string);
                afterLoadPlayerTextureHookTypes.remove(string);
                allBaseBeforeLoadTextureSuperiors.remove(string);
                allBaseBeforeLoadTextureInferiors.remove(string);
                allBaseOverrideLoadTextureSuperiors.remove(string);
                allBaseOverrideLoadTextureInferiors.remove(string);
                allBaseAfterLoadTextureSuperiors.remove(string);
                allBaseAfterLoadTextureInferiors.remove(string);
                beforeLoadTextureHookTypes.remove(string);
                overrideLoadTextureHookTypes.remove(string);
                afterLoadTextureHookTypes.remove(string);
                allBaseBeforePassSpecialRenderSuperiors.remove(string);
                allBaseBeforePassSpecialRenderInferiors.remove(string);
                allBaseOverridePassSpecialRenderSuperiors.remove(string);
                allBaseOverridePassSpecialRenderInferiors.remove(string);
                allBaseAfterPassSpecialRenderSuperiors.remove(string);
                allBaseAfterPassSpecialRenderInferiors.remove(string);
                beforePassSpecialRenderHookTypes.remove(string);
                overridePassSpecialRenderHookTypes.remove(string);
                afterPassSpecialRenderHookTypes.remove(string);
                allBaseBeforeRenderArrowsStuckInEntitySuperiors.remove(string);
                allBaseBeforeRenderArrowsStuckInEntityInferiors.remove(string);
                allBaseOverrideRenderArrowsStuckInEntitySuperiors.remove(string);
                allBaseOverrideRenderArrowsStuckInEntityInferiors.remove(string);
                allBaseAfterRenderArrowsStuckInEntitySuperiors.remove(string);
                allBaseAfterRenderArrowsStuckInEntityInferiors.remove(string);
                beforeRenderArrowsStuckInEntityHookTypes.remove(string);
                overrideRenderArrowsStuckInEntityHookTypes.remove(string);
                afterRenderArrowsStuckInEntityHookTypes.remove(string);
                allBaseBeforeRenderFirstPersonArmSuperiors.remove(string);
                allBaseBeforeRenderFirstPersonArmInferiors.remove(string);
                allBaseOverrideRenderFirstPersonArmSuperiors.remove(string);
                allBaseOverrideRenderFirstPersonArmInferiors.remove(string);
                allBaseAfterRenderFirstPersonArmSuperiors.remove(string);
                allBaseAfterRenderFirstPersonArmInferiors.remove(string);
                beforeRenderFirstPersonArmHookTypes.remove(string);
                overrideRenderFirstPersonArmHookTypes.remove(string);
                afterRenderFirstPersonArmHookTypes.remove(string);
                allBaseBeforeRenderLivingLabelSuperiors.remove(string);
                allBaseBeforeRenderLivingLabelInferiors.remove(string);
                allBaseOverrideRenderLivingLabelSuperiors.remove(string);
                allBaseOverrideRenderLivingLabelInferiors.remove(string);
                allBaseAfterRenderLivingLabelSuperiors.remove(string);
                allBaseAfterRenderLivingLabelInferiors.remove(string);
                beforeRenderLivingLabelHookTypes.remove(string);
                overrideRenderLivingLabelHookTypes.remove(string);
                afterRenderLivingLabelHookTypes.remove(string);
                allBaseBeforeRenderModelSuperiors.remove(string);
                allBaseBeforeRenderModelInferiors.remove(string);
                allBaseOverrideRenderModelSuperiors.remove(string);
                allBaseOverrideRenderModelInferiors.remove(string);
                allBaseAfterRenderModelSuperiors.remove(string);
                allBaseAfterRenderModelInferiors.remove(string);
                beforeRenderModelHookTypes.remove(string);
                overrideRenderModelHookTypes.remove(string);
                afterRenderModelHookTypes.remove(string);
                allBaseBeforeRenderPlayerSuperiors.remove(string);
                allBaseBeforeRenderPlayerInferiors.remove(string);
                allBaseOverrideRenderPlayerSuperiors.remove(string);
                allBaseOverrideRenderPlayerInferiors.remove(string);
                allBaseAfterRenderPlayerSuperiors.remove(string);
                allBaseAfterRenderPlayerInferiors.remove(string);
                beforeRenderPlayerHookTypes.remove(string);
                overrideRenderPlayerHookTypes.remove(string);
                afterRenderPlayerHookTypes.remove(string);
                allBaseBeforeRenderPlayerNameAndScoreLabelSuperiors.remove(string);
                allBaseBeforeRenderPlayerNameAndScoreLabelInferiors.remove(string);
                allBaseOverrideRenderPlayerNameAndScoreLabelSuperiors.remove(string);
                allBaseOverrideRenderPlayerNameAndScoreLabelInferiors.remove(string);
                allBaseAfterRenderPlayerNameAndScoreLabelSuperiors.remove(string);
                allBaseAfterRenderPlayerNameAndScoreLabelInferiors.remove(string);
                beforeRenderPlayerNameAndScoreLabelHookTypes.remove(string);
                overrideRenderPlayerNameAndScoreLabelHookTypes.remove(string);
                afterRenderPlayerNameAndScoreLabelHookTypes.remove(string);
                allBaseBeforeRenderPlayerScaleSuperiors.remove(string);
                allBaseBeforeRenderPlayerScaleInferiors.remove(string);
                allBaseOverrideRenderPlayerScaleSuperiors.remove(string);
                allBaseOverrideRenderPlayerScaleInferiors.remove(string);
                allBaseAfterRenderPlayerScaleSuperiors.remove(string);
                allBaseAfterRenderPlayerScaleInferiors.remove(string);
                beforeRenderPlayerScaleHookTypes.remove(string);
                overrideRenderPlayerScaleHookTypes.remove(string);
                afterRenderPlayerScaleHookTypes.remove(string);
                allBaseBeforeRenderPlayerSleepSuperiors.remove(string);
                allBaseBeforeRenderPlayerSleepInferiors.remove(string);
                allBaseOverrideRenderPlayerSleepSuperiors.remove(string);
                allBaseOverrideRenderPlayerSleepInferiors.remove(string);
                allBaseAfterRenderPlayerSleepSuperiors.remove(string);
                allBaseAfterRenderPlayerSleepInferiors.remove(string);
                beforeRenderPlayerSleepHookTypes.remove(string);
                overrideRenderPlayerSleepHookTypes.remove(string);
                afterRenderPlayerSleepHookTypes.remove(string);
                allBaseBeforeRenderSpecialsSuperiors.remove(string);
                allBaseBeforeRenderSpecialsInferiors.remove(string);
                allBaseOverrideRenderSpecialsSuperiors.remove(string);
                allBaseOverrideRenderSpecialsInferiors.remove(string);
                allBaseAfterRenderSpecialsSuperiors.remove(string);
                allBaseAfterRenderSpecialsInferiors.remove(string);
                beforeRenderSpecialsHookTypes.remove(string);
                overrideRenderSpecialsHookTypes.remove(string);
                afterRenderSpecialsHookTypes.remove(string);
                allBaseBeforeRenderSwingProgressSuperiors.remove(string);
                allBaseBeforeRenderSwingProgressInferiors.remove(string);
                allBaseOverrideRenderSwingProgressSuperiors.remove(string);
                allBaseOverrideRenderSwingProgressInferiors.remove(string);
                allBaseAfterRenderSwingProgressSuperiors.remove(string);
                allBaseAfterRenderSwingProgressInferiors.remove(string);
                beforeRenderSwingProgressHookTypes.remove(string);
                overrideRenderSwingProgressHookTypes.remove(string);
                afterRenderSwingProgressHookTypes.remove(string);
                allBaseBeforeRotatePlayerSuperiors.remove(string);
                allBaseBeforeRotatePlayerInferiors.remove(string);
                allBaseOverrideRotatePlayerSuperiors.remove(string);
                allBaseOverrideRotatePlayerInferiors.remove(string);
                allBaseAfterRotatePlayerSuperiors.remove(string);
                allBaseAfterRotatePlayerInferiors.remove(string);
                beforeRotatePlayerHookTypes.remove(string);
                overrideRotatePlayerHookTypes.remove(string);
                afterRotatePlayerHookTypes.remove(string);
                allBaseBeforeSetArmorModelSuperiors.remove(string);
                allBaseBeforeSetArmorModelInferiors.remove(string);
                allBaseOverrideSetArmorModelSuperiors.remove(string);
                allBaseOverrideSetArmorModelInferiors.remove(string);
                allBaseAfterSetArmorModelSuperiors.remove(string);
                allBaseAfterSetArmorModelInferiors.remove(string);
                beforeSetArmorModelHookTypes.remove(string);
                overrideSetArmorModelHookTypes.remove(string);
                afterSetArmorModelHookTypes.remove(string);
                allBaseBeforeSetIconRegisterSuperiors.remove(string);
                allBaseBeforeSetIconRegisterInferiors.remove(string);
                allBaseOverrideSetIconRegisterSuperiors.remove(string);
                allBaseOverrideSetIconRegisterInferiors.remove(string);
                allBaseAfterSetIconRegisterSuperiors.remove(string);
                allBaseAfterSetIconRegisterInferiors.remove(string);
                beforeSetIconRegisterHookTypes.remove(string);
                overrideSetIconRegisterHookTypes.remove(string);
                afterSetIconRegisterHookTypes.remove(string);
                allBaseBeforeSetPassArmorModelSuperiors.remove(string);
                allBaseBeforeSetPassArmorModelInferiors.remove(string);
                allBaseOverrideSetPassArmorModelSuperiors.remove(string);
                allBaseOverrideSetPassArmorModelInferiors.remove(string);
                allBaseAfterSetPassArmorModelSuperiors.remove(string);
                allBaseAfterSetPassArmorModelInferiors.remove(string);
                beforeSetPassArmorModelHookTypes.remove(string);
                overrideSetPassArmorModelHookTypes.remove(string);
                afterSetPassArmorModelHookTypes.remove(string);
                allBaseBeforeSetRenderManagerSuperiors.remove(string);
                allBaseBeforeSetRenderManagerInferiors.remove(string);
                allBaseOverrideSetRenderManagerSuperiors.remove(string);
                allBaseOverrideSetRenderManagerInferiors.remove(string);
                allBaseAfterSetRenderManagerSuperiors.remove(string);
                allBaseAfterSetRenderManagerInferiors.remove(string);
                beforeSetRenderManagerHookTypes.remove(string);
                overrideSetRenderManagerHookTypes.remove(string);
                afterSetRenderManagerHookTypes.remove(string);
                allBaseBeforeSetRenderPassModelSuperiors.remove(string);
                allBaseBeforeSetRenderPassModelInferiors.remove(string);
                allBaseOverrideSetRenderPassModelSuperiors.remove(string);
                allBaseOverrideSetRenderPassModelInferiors.remove(string);
                allBaseAfterSetRenderPassModelSuperiors.remove(string);
                allBaseAfterSetRenderPassModelInferiors.remove(string);
                beforeSetRenderPassModelHookTypes.remove(string);
                overrideSetRenderPassModelHookTypes.remove(string);
                afterSetRenderPassModelHookTypes.remove(string);
                allBaseBeforeRenderSpecialHeadArmorSuperiors.remove(string);
                allBaseBeforeRenderSpecialHeadArmorInferiors.remove(string);
                allBaseOverrideRenderSpecialHeadArmorSuperiors.remove(string);
                allBaseOverrideRenderSpecialHeadArmorInferiors.remove(string);
                allBaseAfterRenderSpecialHeadArmorSuperiors.remove(string);
                allBaseAfterRenderSpecialHeadArmorInferiors.remove(string);
                beforeRenderSpecialHeadArmorHookTypes.remove(string);
                overrideRenderSpecialHeadArmorHookTypes.remove(string);
                afterRenderSpecialHeadArmorHookTypes.remove(string);
                allBaseBeforeRenderSpecialHeadEarsSuperiors.remove(string);
                allBaseBeforeRenderSpecialHeadEarsInferiors.remove(string);
                allBaseOverrideRenderSpecialHeadEarsSuperiors.remove(string);
                allBaseOverrideRenderSpecialHeadEarsInferiors.remove(string);
                allBaseAfterRenderSpecialHeadEarsSuperiors.remove(string);
                allBaseAfterRenderSpecialHeadEarsInferiors.remove(string);
                beforeRenderSpecialHeadEarsHookTypes.remove(string);
                overrideRenderSpecialHeadEarsHookTypes.remove(string);
                afterRenderSpecialHeadEarsHookTypes.remove(string);
                allBaseBeforeRenderSpecialCloakSuperiors.remove(string);
                allBaseBeforeRenderSpecialCloakInferiors.remove(string);
                allBaseOverrideRenderSpecialCloakSuperiors.remove(string);
                allBaseOverrideRenderSpecialCloakInferiors.remove(string);
                allBaseAfterRenderSpecialCloakSuperiors.remove(string);
                allBaseAfterRenderSpecialCloakInferiors.remove(string);
                beforeRenderSpecialCloakHookTypes.remove(string);
                overrideRenderSpecialCloakHookTypes.remove(string);
                afterRenderSpecialCloakHookTypes.remove(string);
                allBaseBeforeRenderSpecialItemInHandSuperiors.remove(string);
                allBaseBeforeRenderSpecialItemInHandInferiors.remove(string);
                allBaseOverrideRenderSpecialItemInHandSuperiors.remove(string);
                allBaseOverrideRenderSpecialItemInHandInferiors.remove(string);
                allBaseAfterRenderSpecialItemInHandSuperiors.remove(string);
                allBaseAfterRenderSpecialItemInHandInferiors.remove(string);
                beforeRenderSpecialItemInHandHookTypes.remove(string);
                overrideRenderSpecialItemInHandHookTypes.remove(string);
                afterRenderSpecialItemInHandHookTypes.remove(string);
                allBaseBeforePositionSpecialItemInHandSuperiors.remove(string);
                allBaseBeforePositionSpecialItemInHandInferiors.remove(string);
                allBaseOverridePositionSpecialItemInHandSuperiors.remove(string);
                allBaseOverridePositionSpecialItemInHandInferiors.remove(string);
                allBaseAfterPositionSpecialItemInHandSuperiors.remove(string);
                allBaseAfterPositionSpecialItemInHandInferiors.remove(string);
                beforePositionSpecialItemInHandHookTypes.remove(string);
                overridePositionSpecialItemInHandHookTypes.remove(string);
                afterPositionSpecialItemInHandHookTypes.remove(string);
                Iterator var7 = keysToVirtualIds.keySet().iterator();

                while (var7.hasNext())
                {
                    String var9 = (String)var7.next();

                    if (((String)keysToVirtualIds.get(var9)).equals(string))
                    {
                        keysToVirtualIds.remove(var9);
                    }
                }

                boolean var8 = false;
                Class var10 = constructor.getDeclaringClass();
                Iterator var11 = allBaseConstructors.keySet().iterator();

                while (var11.hasNext())
                {
                    String var12 = (String)var11.next();
                    Class class_2 = ((Constructor)allBaseConstructors.get(var12)).getDeclaringClass();

                    if (!var12.equals(string) && class_2.equals(var10))
                    {
                        var8 = true;
                        break;
                    }
                }

                if (!var8)
                {
                    dynamicTypes.remove(var10);
                    virtualDynamicHookMethods.remove(var10);
                    beforeDynamicHookMethods.remove(var10);
                    overrideDynamicHookMethods.remove(var10);
                    afterDynamicHookMethods.remove(var10);
                }

                removeDynamicHookTypes(string, beforeDynamicHookTypes);
                removeDynamicHookTypes(string, overrideDynamicHookTypes);
                removeDynamicHookTypes(string, afterDynamicHookTypes);
                allBaseBeforeDynamicSuperiors.remove(string);
                allBaseBeforeDynamicInferiors.remove(string);
                allBaseOverrideDynamicSuperiors.remove(string);
                allBaseOverrideDynamicInferiors.remove(string);
                allBaseAfterDynamicSuperiors.remove(string);
                allBaseAfterDynamicInferiors.remove(string);
                log("RenderPlayerAPI: unregistered id \'" + string + "\'");
                return true;
            }
        }
    }

    public static void removeDynamicHookTypes(String string, Map map)
    {
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext())
        {
            ((List)map.get(iterator.next())).remove(string);
        }
    }

    public static Set getRegisteredIds()
    {
        return unmodifiableAllIds;
    }

    private static void addSorting(String string, Map map, String[] arrstring)
    {
        if (arrstring != null && arrstring.length > 0)
        {
            map.put(string, arrstring);
        }
    }

    private static void addDynamicSorting(String string, Map map, Map map2)
    {
        if (map2 != null && map2.size() > 0)
        {
            map.put(string, map2);
        }
    }

    private static boolean addMethod(String string, Class class_, List list, String string2, Class ... arrclass)
    {
        try
        {
            Method method = class_.getMethod(string2, arrclass);
            boolean exception = method.getDeclaringClass() != RenderPlayerBase.class;

            if (exception)
            {
                list.add(string);
            }

            return exception;
        }
        catch (Exception var8)
        {
            throw new RuntimeException("Can not reflect method \'" + string2 + "\' of class \'" + class_.getName() + "\'", var8);
        }
    }

    private static void addDynamicMethods(String string, Class class_)
    {
        if (dynamicTypes.add(class_))
        {
            Map map = null;
            Map map2 = null;
            Map map3 = null;
            Map map4 = null;
            Method[] arrmethod = class_.getDeclaredMethods();

            for (int i = 0; i < arrmethod.length; ++i)
            {
                Method method = arrmethod[i];
                String string2;
                int n;

                if (method.getDeclaringClass() == class_ && !Modifier.isAbstract(n = method.getModifiers()) && !Modifier.isStatic(n) && (string2 = method.getName()).length() >= 7 && string2.substring(0, 7).equalsIgnoreCase("dynamic"))
                {
                    for (string2 = string2.substring(7); string2.charAt(0) == 95; string2 = string2.substring(1))
                    {
                        ;
                    }

                    boolean bl = false;
                    boolean bl2 = false;
                    boolean bl3 = false;
                    boolean bl4 = false;

                    if (string2.substring(0, 7).equalsIgnoreCase("virtual"))
                    {
                        bl2 = true;
                        string2 = string2.substring(7);
                    }
                    else if (string2.length() >= 8 && string2.substring(0, 8).equalsIgnoreCase("override"))
                    {
                        string2 = string2.substring(8);
                        bl3 = true;
                    }
                    else if (string2.length() >= 6 && string2.substring(0, 6).equalsIgnoreCase("before"))
                    {
                        bl = true;
                        string2 = string2.substring(6);
                    }
                    else if (string2.length() >= 5 && string2.substring(0, 5).equalsIgnoreCase("after"))
                    {
                        bl4 = true;
                        string2 = string2.substring(5);
                    }

                    if (string2.length() >= 1 && (bl || bl2 || bl3 || bl4))
                    {
                        string2 = string2.substring(0, 1).toLowerCase() + string2.substring(1);
                    }

                    while (string2.charAt(0) == 95)
                    {
                        string2 = string2.substring(1);
                    }

                    if (string2.length() == 0)
                    {
                        throw new RuntimeException("Can not process dynamic hook method with no key");
                    }

                    keys.add(string2);

                    if (bl2)
                    {
                        if (keysToVirtualIds.containsKey(string2))
                        {
                            throw new RuntimeException("Can not process more than one dynamic virtual method");
                        }

                        keysToVirtualIds.put(string2, string);
                        map = addDynamicMethod(string2, method, map);
                    }
                    else if (bl)
                    {
                        map2 = addDynamicMethod(string2, method, map2);
                    }
                    else if (bl4)
                    {
                        map4 = addDynamicMethod(string2, method, map4);
                    }
                    else
                    {
                        map3 = addDynamicMethod(string2, method, map3);
                    }
                }
            }

            if (map != null)
            {
                virtualDynamicHookMethods.put(class_, map);
            }

            if (map2 != null)
            {
                beforeDynamicHookMethods.put(class_, map2);
            }

            if (map3 != null)
            {
                overrideDynamicHookMethods.put(class_, map3);
            }

            if (map4 != null)
            {
                afterDynamicHookMethods.put(class_, map4);
            }
        }
    }

    private static void addDynamicKeys(String string, Class class_, Map map, Map map2)
    {
        Map map3 = (Map)map.get(class_);

        if (map3 != null && map3.size() != 0)
        {
            String string2;

            for (Iterator var5 = map3.keySet().iterator(); var5.hasNext(); ((List)map2.get(string2)).add(string))
            {
                string2 = (String)var5.next();

                if (!map2.containsKey(string2))
                {
                    map2.put(string2, new ArrayList(1));
                }
            }
        }
    }

    private static Map addDynamicMethod(String string, Method method, Map map)
    {
        if (map == null)
        {
            map = new HashMap();
        }

        if (((Map)map).containsKey(string))
        {
            throw new RuntimeException("method with key \'" + string + "\' allready exists");
        }
        else
        {
            ((Map)map).put(string, method);
            return (Map)map;
        }
    }

    public static RenderPlayerAPI create(RenderPlayer renderPlayer)
    {
        if (allBaseConstructors.size() > 0 && !initialized)
        {
            initialize();
        }

        return new RenderPlayerAPI(renderPlayer);
    }

    private static void initialize()
    {
        sortBases(beforeLocalConstructingHookTypes, allBaseBeforeLocalConstructingSuperiors, allBaseBeforeLocalConstructingInferiors, "beforeLocalConstructing");
        sortBases(afterLocalConstructingHookTypes, allBaseAfterLocalConstructingSuperiors, allBaseAfterLocalConstructingInferiors, "afterLocalConstructing");
        Iterator var0 = keys.iterator();

        while (var0.hasNext())
        {
            String string = (String)var0.next();
            sortDynamicBases(beforeDynamicHookTypes, allBaseBeforeDynamicSuperiors, allBaseBeforeDynamicInferiors, string);
            sortDynamicBases(overrideDynamicHookTypes, allBaseOverrideDynamicSuperiors, allBaseOverrideDynamicInferiors, string);
            sortDynamicBases(afterDynamicHookTypes, allBaseAfterDynamicSuperiors, allBaseAfterDynamicInferiors, string);
        }

        sortBases(beforeDoRenderShadowAndFireHookTypes, allBaseBeforeDoRenderShadowAndFireSuperiors, allBaseBeforeDoRenderShadowAndFireInferiors, "beforeDoRenderShadowAndFire");
        sortBases(overrideDoRenderShadowAndFireHookTypes, allBaseOverrideDoRenderShadowAndFireSuperiors, allBaseOverrideDoRenderShadowAndFireInferiors, "overrideDoRenderShadowAndFire");
        sortBases(afterDoRenderShadowAndFireHookTypes, allBaseAfterDoRenderShadowAndFireSuperiors, allBaseAfterDoRenderShadowAndFireInferiors, "afterDoRenderShadowAndFire");
        sortBases(beforeGetColorMultiplierHookTypes, allBaseBeforeGetColorMultiplierSuperiors, allBaseBeforeGetColorMultiplierInferiors, "beforeGetColorMultiplier");
        sortBases(overrideGetColorMultiplierHookTypes, allBaseOverrideGetColorMultiplierSuperiors, allBaseOverrideGetColorMultiplierInferiors, "overrideGetColorMultiplier");
        sortBases(afterGetColorMultiplierHookTypes, allBaseAfterGetColorMultiplierSuperiors, allBaseAfterGetColorMultiplierInferiors, "afterGetColorMultiplier");
        sortBases(beforeGetDeathMaxRotationHookTypes, allBaseBeforeGetDeathMaxRotationSuperiors, allBaseBeforeGetDeathMaxRotationInferiors, "beforeGetDeathMaxRotation");
        sortBases(overrideGetDeathMaxRotationHookTypes, allBaseOverrideGetDeathMaxRotationSuperiors, allBaseOverrideGetDeathMaxRotationInferiors, "overrideGetDeathMaxRotation");
        sortBases(afterGetDeathMaxRotationHookTypes, allBaseAfterGetDeathMaxRotationSuperiors, allBaseAfterGetDeathMaxRotationInferiors, "afterGetDeathMaxRotation");
        sortBases(beforeGetFontRendererFromRenderManagerHookTypes, allBaseBeforeGetFontRendererFromRenderManagerSuperiors, allBaseBeforeGetFontRendererFromRenderManagerInferiors, "beforeGetFontRendererFromRenderManager");
        sortBases(overrideGetFontRendererFromRenderManagerHookTypes, allBaseOverrideGetFontRendererFromRenderManagerSuperiors, allBaseOverrideGetFontRendererFromRenderManagerInferiors, "overrideGetFontRendererFromRenderManager");
        sortBases(afterGetFontRendererFromRenderManagerHookTypes, allBaseAfterGetFontRendererFromRenderManagerSuperiors, allBaseAfterGetFontRendererFromRenderManagerInferiors, "afterGetFontRendererFromRenderManager");
        sortBases(beforeHandleRotationFloatHookTypes, allBaseBeforeHandleRotationFloatSuperiors, allBaseBeforeHandleRotationFloatInferiors, "beforeHandleRotationFloat");
        sortBases(overrideHandleRotationFloatHookTypes, allBaseOverrideHandleRotationFloatSuperiors, allBaseOverrideHandleRotationFloatInferiors, "overrideHandleRotationFloat");
        sortBases(afterHandleRotationFloatHookTypes, allBaseAfterHandleRotationFloatSuperiors, allBaseAfterHandleRotationFloatInferiors, "afterHandleRotationFloat");
        sortBases(beforeInheritRenderPassHookTypes, allBaseBeforeInheritRenderPassSuperiors, allBaseBeforeInheritRenderPassInferiors, "beforeInheritRenderPass");
        sortBases(overrideInheritRenderPassHookTypes, allBaseOverrideInheritRenderPassSuperiors, allBaseOverrideInheritRenderPassInferiors, "overrideInheritRenderPass");
        sortBases(afterInheritRenderPassHookTypes, allBaseAfterInheritRenderPassSuperiors, allBaseAfterInheritRenderPassInferiors, "afterInheritRenderPass");
        sortBases(beforeLoadDownloadableImageTextureHookTypes, allBaseBeforeLoadDownloadableImageTextureSuperiors, allBaseBeforeLoadDownloadableImageTextureInferiors, "beforeLoadDownloadableImageTexture");
        sortBases(overrideLoadDownloadableImageTextureHookTypes, allBaseOverrideLoadDownloadableImageTextureSuperiors, allBaseOverrideLoadDownloadableImageTextureInferiors, "overrideLoadDownloadableImageTexture");
        sortBases(afterLoadDownloadableImageTextureHookTypes, allBaseAfterLoadDownloadableImageTextureSuperiors, allBaseAfterLoadDownloadableImageTextureInferiors, "afterLoadDownloadableImageTexture");
        sortBases(beforeLoadPlayerTextureHookTypes, allBaseBeforeLoadPlayerTextureSuperiors, allBaseBeforeLoadPlayerTextureInferiors, "beforeLoadPlayerTexture");
        sortBases(overrideLoadPlayerTextureHookTypes, allBaseOverrideLoadPlayerTextureSuperiors, allBaseOverrideLoadPlayerTextureInferiors, "overrideLoadPlayerTexture");
        sortBases(afterLoadPlayerTextureHookTypes, allBaseAfterLoadPlayerTextureSuperiors, allBaseAfterLoadPlayerTextureInferiors, "afterLoadPlayerTexture");
        sortBases(beforeLoadTextureHookTypes, allBaseBeforeLoadTextureSuperiors, allBaseBeforeLoadTextureInferiors, "beforeLoadTexture");
        sortBases(overrideLoadTextureHookTypes, allBaseOverrideLoadTextureSuperiors, allBaseOverrideLoadTextureInferiors, "overrideLoadTexture");
        sortBases(afterLoadTextureHookTypes, allBaseAfterLoadTextureSuperiors, allBaseAfterLoadTextureInferiors, "afterLoadTexture");
        sortBases(beforePassSpecialRenderHookTypes, allBaseBeforePassSpecialRenderSuperiors, allBaseBeforePassSpecialRenderInferiors, "beforePassSpecialRender");
        sortBases(overridePassSpecialRenderHookTypes, allBaseOverridePassSpecialRenderSuperiors, allBaseOverridePassSpecialRenderInferiors, "overridePassSpecialRender");
        sortBases(afterPassSpecialRenderHookTypes, allBaseAfterPassSpecialRenderSuperiors, allBaseAfterPassSpecialRenderInferiors, "afterPassSpecialRender");
        sortBases(beforeRenderArrowsStuckInEntityHookTypes, allBaseBeforeRenderArrowsStuckInEntitySuperiors, allBaseBeforeRenderArrowsStuckInEntityInferiors, "beforeRenderArrowsStuckInEntity");
        sortBases(overrideRenderArrowsStuckInEntityHookTypes, allBaseOverrideRenderArrowsStuckInEntitySuperiors, allBaseOverrideRenderArrowsStuckInEntityInferiors, "overrideRenderArrowsStuckInEntity");
        sortBases(afterRenderArrowsStuckInEntityHookTypes, allBaseAfterRenderArrowsStuckInEntitySuperiors, allBaseAfterRenderArrowsStuckInEntityInferiors, "afterRenderArrowsStuckInEntity");
        sortBases(beforeRenderFirstPersonArmHookTypes, allBaseBeforeRenderFirstPersonArmSuperiors, allBaseBeforeRenderFirstPersonArmInferiors, "beforeRenderFirstPersonArm");
        sortBases(overrideRenderFirstPersonArmHookTypes, allBaseOverrideRenderFirstPersonArmSuperiors, allBaseOverrideRenderFirstPersonArmInferiors, "overrideRenderFirstPersonArm");
        sortBases(afterRenderFirstPersonArmHookTypes, allBaseAfterRenderFirstPersonArmSuperiors, allBaseAfterRenderFirstPersonArmInferiors, "afterRenderFirstPersonArm");
        sortBases(beforeRenderLivingLabelHookTypes, allBaseBeforeRenderLivingLabelSuperiors, allBaseBeforeRenderLivingLabelInferiors, "beforeRenderLivingLabel");
        sortBases(overrideRenderLivingLabelHookTypes, allBaseOverrideRenderLivingLabelSuperiors, allBaseOverrideRenderLivingLabelInferiors, "overrideRenderLivingLabel");
        sortBases(afterRenderLivingLabelHookTypes, allBaseAfterRenderLivingLabelSuperiors, allBaseAfterRenderLivingLabelInferiors, "afterRenderLivingLabel");
        sortBases(beforeRenderModelHookTypes, allBaseBeforeRenderModelSuperiors, allBaseBeforeRenderModelInferiors, "beforeRenderModel");
        sortBases(overrideRenderModelHookTypes, allBaseOverrideRenderModelSuperiors, allBaseOverrideRenderModelInferiors, "overrideRenderModel");
        sortBases(afterRenderModelHookTypes, allBaseAfterRenderModelSuperiors, allBaseAfterRenderModelInferiors, "afterRenderModel");
        sortBases(beforeRenderPlayerHookTypes, allBaseBeforeRenderPlayerSuperiors, allBaseBeforeRenderPlayerInferiors, "beforeRenderPlayer");
        sortBases(overrideRenderPlayerHookTypes, allBaseOverrideRenderPlayerSuperiors, allBaseOverrideRenderPlayerInferiors, "overrideRenderPlayer");
        sortBases(afterRenderPlayerHookTypes, allBaseAfterRenderPlayerSuperiors, allBaseAfterRenderPlayerInferiors, "afterRenderPlayer");
        sortBases(beforeRenderPlayerNameAndScoreLabelHookTypes, allBaseBeforeRenderPlayerNameAndScoreLabelSuperiors, allBaseBeforeRenderPlayerNameAndScoreLabelInferiors, "beforeRenderPlayerNameAndScoreLabel");
        sortBases(overrideRenderPlayerNameAndScoreLabelHookTypes, allBaseOverrideRenderPlayerNameAndScoreLabelSuperiors, allBaseOverrideRenderPlayerNameAndScoreLabelInferiors, "overrideRenderPlayerNameAndScoreLabel");
        sortBases(afterRenderPlayerNameAndScoreLabelHookTypes, allBaseAfterRenderPlayerNameAndScoreLabelSuperiors, allBaseAfterRenderPlayerNameAndScoreLabelInferiors, "afterRenderPlayerNameAndScoreLabel");
        sortBases(beforeRenderPlayerScaleHookTypes, allBaseBeforeRenderPlayerScaleSuperiors, allBaseBeforeRenderPlayerScaleInferiors, "beforeRenderPlayerScale");
        sortBases(overrideRenderPlayerScaleHookTypes, allBaseOverrideRenderPlayerScaleSuperiors, allBaseOverrideRenderPlayerScaleInferiors, "overrideRenderPlayerScale");
        sortBases(afterRenderPlayerScaleHookTypes, allBaseAfterRenderPlayerScaleSuperiors, allBaseAfterRenderPlayerScaleInferiors, "afterRenderPlayerScale");
        sortBases(beforeRenderPlayerSleepHookTypes, allBaseBeforeRenderPlayerSleepSuperiors, allBaseBeforeRenderPlayerSleepInferiors, "beforeRenderPlayerSleep");
        sortBases(overrideRenderPlayerSleepHookTypes, allBaseOverrideRenderPlayerSleepSuperiors, allBaseOverrideRenderPlayerSleepInferiors, "overrideRenderPlayerSleep");
        sortBases(afterRenderPlayerSleepHookTypes, allBaseAfterRenderPlayerSleepSuperiors, allBaseAfterRenderPlayerSleepInferiors, "afterRenderPlayerSleep");
        sortBases(beforeRenderSpecialsHookTypes, allBaseBeforeRenderSpecialsSuperiors, allBaseBeforeRenderSpecialsInferiors, "beforeRenderSpecials");
        sortBases(overrideRenderSpecialsHookTypes, allBaseOverrideRenderSpecialsSuperiors, allBaseOverrideRenderSpecialsInferiors, "overrideRenderSpecials");
        sortBases(afterRenderSpecialsHookTypes, allBaseAfterRenderSpecialsSuperiors, allBaseAfterRenderSpecialsInferiors, "afterRenderSpecials");
        sortBases(beforeRenderSwingProgressHookTypes, allBaseBeforeRenderSwingProgressSuperiors, allBaseBeforeRenderSwingProgressInferiors, "beforeRenderSwingProgress");
        sortBases(overrideRenderSwingProgressHookTypes, allBaseOverrideRenderSwingProgressSuperiors, allBaseOverrideRenderSwingProgressInferiors, "overrideRenderSwingProgress");
        sortBases(afterRenderSwingProgressHookTypes, allBaseAfterRenderSwingProgressSuperiors, allBaseAfterRenderSwingProgressInferiors, "afterRenderSwingProgress");
        sortBases(beforeRotatePlayerHookTypes, allBaseBeforeRotatePlayerSuperiors, allBaseBeforeRotatePlayerInferiors, "beforeRotatePlayer");
        sortBases(overrideRotatePlayerHookTypes, allBaseOverrideRotatePlayerSuperiors, allBaseOverrideRotatePlayerInferiors, "overrideRotatePlayer");
        sortBases(afterRotatePlayerHookTypes, allBaseAfterRotatePlayerSuperiors, allBaseAfterRotatePlayerInferiors, "afterRotatePlayer");
        sortBases(beforeSetArmorModelHookTypes, allBaseBeforeSetArmorModelSuperiors, allBaseBeforeSetArmorModelInferiors, "beforeSetArmorModel");
        sortBases(overrideSetArmorModelHookTypes, allBaseOverrideSetArmorModelSuperiors, allBaseOverrideSetArmorModelInferiors, "overrideSetArmorModel");
        sortBases(afterSetArmorModelHookTypes, allBaseAfterSetArmorModelSuperiors, allBaseAfterSetArmorModelInferiors, "afterSetArmorModel");
        sortBases(beforeSetIconRegisterHookTypes, allBaseBeforeSetIconRegisterSuperiors, allBaseBeforeSetIconRegisterInferiors, "beforeSetIconRegister");
        sortBases(overrideSetIconRegisterHookTypes, allBaseOverrideSetIconRegisterSuperiors, allBaseOverrideSetIconRegisterInferiors, "overrideSetIconRegister");
        sortBases(afterSetIconRegisterHookTypes, allBaseAfterSetIconRegisterSuperiors, allBaseAfterSetIconRegisterInferiors, "afterSetIconRegister");
        sortBases(beforeSetPassArmorModelHookTypes, allBaseBeforeSetPassArmorModelSuperiors, allBaseBeforeSetPassArmorModelInferiors, "beforeSetPassArmorModel");
        sortBases(overrideSetPassArmorModelHookTypes, allBaseOverrideSetPassArmorModelSuperiors, allBaseOverrideSetPassArmorModelInferiors, "overrideSetPassArmorModel");
        sortBases(afterSetPassArmorModelHookTypes, allBaseAfterSetPassArmorModelSuperiors, allBaseAfterSetPassArmorModelInferiors, "afterSetPassArmorModel");
        sortBases(beforeSetRenderManagerHookTypes, allBaseBeforeSetRenderManagerSuperiors, allBaseBeforeSetRenderManagerInferiors, "beforeSetRenderManager");
        sortBases(overrideSetRenderManagerHookTypes, allBaseOverrideSetRenderManagerSuperiors, allBaseOverrideSetRenderManagerInferiors, "overrideSetRenderManager");
        sortBases(afterSetRenderManagerHookTypes, allBaseAfterSetRenderManagerSuperiors, allBaseAfterSetRenderManagerInferiors, "afterSetRenderManager");
        sortBases(beforeSetRenderPassModelHookTypes, allBaseBeforeSetRenderPassModelSuperiors, allBaseBeforeSetRenderPassModelInferiors, "beforeSetRenderPassModel");
        sortBases(overrideSetRenderPassModelHookTypes, allBaseOverrideSetRenderPassModelSuperiors, allBaseOverrideSetRenderPassModelInferiors, "overrideSetRenderPassModel");
        sortBases(afterSetRenderPassModelHookTypes, allBaseAfterSetRenderPassModelSuperiors, allBaseAfterSetRenderPassModelInferiors, "afterSetRenderPassModel");
        sortBases(beforeRenderSpecialHeadArmorHookTypes, allBaseBeforeRenderSpecialHeadArmorSuperiors, allBaseBeforeRenderSpecialHeadArmorInferiors, "beforeRenderSpecialHeadArmor");
        sortBases(overrideRenderSpecialHeadArmorHookTypes, allBaseOverrideRenderSpecialHeadArmorSuperiors, allBaseOverrideRenderSpecialHeadArmorInferiors, "overrideRenderSpecialHeadArmor");
        sortBases(afterRenderSpecialHeadArmorHookTypes, allBaseAfterRenderSpecialHeadArmorSuperiors, allBaseAfterRenderSpecialHeadArmorInferiors, "afterRenderSpecialHeadArmor");
        sortBases(beforeRenderSpecialHeadEarsHookTypes, allBaseBeforeRenderSpecialHeadEarsSuperiors, allBaseBeforeRenderSpecialHeadEarsInferiors, "beforeRenderSpecialHeadEars");
        sortBases(overrideRenderSpecialHeadEarsHookTypes, allBaseOverrideRenderSpecialHeadEarsSuperiors, allBaseOverrideRenderSpecialHeadEarsInferiors, "overrideRenderSpecialHeadEars");
        sortBases(afterRenderSpecialHeadEarsHookTypes, allBaseAfterRenderSpecialHeadEarsSuperiors, allBaseAfterRenderSpecialHeadEarsInferiors, "afterRenderSpecialHeadEars");
        sortBases(beforeRenderSpecialCloakHookTypes, allBaseBeforeRenderSpecialCloakSuperiors, allBaseBeforeRenderSpecialCloakInferiors, "beforeRenderSpecialCloak");
        sortBases(overrideRenderSpecialCloakHookTypes, allBaseOverrideRenderSpecialCloakSuperiors, allBaseOverrideRenderSpecialCloakInferiors, "overrideRenderSpecialCloak");
        sortBases(afterRenderSpecialCloakHookTypes, allBaseAfterRenderSpecialCloakSuperiors, allBaseAfterRenderSpecialCloakInferiors, "afterRenderSpecialCloak");
        sortBases(beforeRenderSpecialItemInHandHookTypes, allBaseBeforeRenderSpecialItemInHandSuperiors, allBaseBeforeRenderSpecialItemInHandInferiors, "beforeRenderSpecialItemInHand");
        sortBases(overrideRenderSpecialItemInHandHookTypes, allBaseOverrideRenderSpecialItemInHandSuperiors, allBaseOverrideRenderSpecialItemInHandInferiors, "overrideRenderSpecialItemInHand");
        sortBases(afterRenderSpecialItemInHandHookTypes, allBaseAfterRenderSpecialItemInHandSuperiors, allBaseAfterRenderSpecialItemInHandInferiors, "afterRenderSpecialItemInHand");
        sortBases(beforePositionSpecialItemInHandHookTypes, allBaseBeforePositionSpecialItemInHandSuperiors, allBaseBeforePositionSpecialItemInHandInferiors, "beforePositionSpecialItemInHand");
        sortBases(overridePositionSpecialItemInHandHookTypes, allBaseOverridePositionSpecialItemInHandSuperiors, allBaseOverridePositionSpecialItemInHandInferiors, "overridePositionSpecialItemInHand");
        sortBases(afterPositionSpecialItemInHandHookTypes, allBaseAfterPositionSpecialItemInHandSuperiors, allBaseAfterPositionSpecialItemInHandInferiors, "afterPositionSpecialItemInHand");
        initialized = true;
    }

    public static void beforeLocalConstructing(RenderPlayer renderPlayer)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.beforeLocalConstructing();
        }
    }

    public static void afterLocalConstructing(RenderPlayer renderPlayer)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.afterLocalConstructing();
        }
    }

    private static void sortBases(List list, Map map, Map map2, String string)
    {
        (new RenderPlayerBaseSorter(list, map, map2, string)).Sort();
    }

    private static void sortDynamicBases(Map map, Map map2, Map map3, String string)
    {
        List list = (List)map.get(string);

        if (list != null && list.size() > 1)
        {
            sortBases(list, getDynamicSorters(string, list, map2), getDynamicSorters(string, list, map3), string);
        }
    }

    private static Map getDynamicSorters(String string, List list, Map map)
    {
        HashMap map2 = null;
        Iterator var4 = list.iterator();

        while (var4.hasNext())
        {
            String string2 = (String)var4.next();
            Map map3 = (Map)map.get(string2);
            String[] arrstring;

            if (map3 != null && (arrstring = (String[])map3.get(string)) != null && arrstring.length > 0)
            {
                if (map2 == null)
                {
                    map2 = new HashMap(1);
                }

                map2.put(string2, arrstring);
            }
        }

        return (Map)(map2 != null ? map2 : EmptySortMap);
    }

    private RenderPlayerAPI(RenderPlayer renderPlayer)
    {
        this.unmodifiableAllBaseIds = Collections.unmodifiableSet(this.allBaseObjects.keySet());
        this.renderPlayer = renderPlayer;
        initializer[0] = this;
        initializers[0] = this;
        Iterator iterator = allBaseConstructors.keySet().iterator();

        while (iterator.hasNext())
        {
            String string = (String)iterator.next();
            RenderPlayerBase renderPlayerBase = this.createRenderPlayerBase(string);
            renderPlayerBase.beforeBaseAttach(false);
            this.allBaseObjects.put(string, renderPlayerBase);
            this.baseObjectsToId.put(renderPlayerBase, string);
        }

        this.beforeLocalConstructingHooks = this.create(beforeLocalConstructingHookTypes);
        this.afterLocalConstructingHooks = this.create(afterLocalConstructingHookTypes);
        this.updateRenderPlayerBases();
        iterator = this.allBaseObjects.keySet().iterator();

        while (iterator.hasNext())
        {
            ((RenderPlayerBase)this.allBaseObjects.get(iterator.next())).afterBaseAttach(false);
        }
    }

    private RenderPlayerBase createRenderPlayerBase(String string)
    {
        Constructor constructor = (Constructor)allBaseConstructors.get(string);
        initializers[1] = string;

        try
        {
            RenderPlayerBase renderPlayerBase = constructor.getParameterTypes().length == 1 ? (RenderPlayerBase)constructor.newInstance(initializer) : (RenderPlayerBase)constructor.newInstance(initializers);
            return renderPlayerBase;
        }
        catch (Exception var5)
        {
            throw new RuntimeException("Exception while creating a RenderPlayerBase of type \'" + constructor.getDeclaringClass() + "\'", var5);
        }
    }

    private void updateRenderPlayerBases()
    {
        this.beforeDoRenderShadowAndFireHooks = this.create(beforeDoRenderShadowAndFireHookTypes);
        this.overrideDoRenderShadowAndFireHooks = this.create(overrideDoRenderShadowAndFireHookTypes);
        this.afterDoRenderShadowAndFireHooks = this.create(afterDoRenderShadowAndFireHookTypes);
        this.isDoRenderShadowAndFireModded = this.beforeDoRenderShadowAndFireHooks != null || this.overrideDoRenderShadowAndFireHooks != null || this.afterDoRenderShadowAndFireHooks != null;
        this.beforeGetColorMultiplierHooks = this.create(beforeGetColorMultiplierHookTypes);
        this.overrideGetColorMultiplierHooks = this.create(overrideGetColorMultiplierHookTypes);
        this.afterGetColorMultiplierHooks = this.create(afterGetColorMultiplierHookTypes);
        this.isGetColorMultiplierModded = this.beforeGetColorMultiplierHooks != null || this.overrideGetColorMultiplierHooks != null || this.afterGetColorMultiplierHooks != null;
        this.beforeGetDeathMaxRotationHooks = this.create(beforeGetDeathMaxRotationHookTypes);
        this.overrideGetDeathMaxRotationHooks = this.create(overrideGetDeathMaxRotationHookTypes);
        this.afterGetDeathMaxRotationHooks = this.create(afterGetDeathMaxRotationHookTypes);
        this.isGetDeathMaxRotationModded = this.beforeGetDeathMaxRotationHooks != null || this.overrideGetDeathMaxRotationHooks != null || this.afterGetDeathMaxRotationHooks != null;
        this.beforeGetFontRendererFromRenderManagerHooks = this.create(beforeGetFontRendererFromRenderManagerHookTypes);
        this.overrideGetFontRendererFromRenderManagerHooks = this.create(overrideGetFontRendererFromRenderManagerHookTypes);
        this.afterGetFontRendererFromRenderManagerHooks = this.create(afterGetFontRendererFromRenderManagerHookTypes);
        this.isGetFontRendererFromRenderManagerModded = this.beforeGetFontRendererFromRenderManagerHooks != null || this.overrideGetFontRendererFromRenderManagerHooks != null || this.afterGetFontRendererFromRenderManagerHooks != null;
        this.beforeHandleRotationFloatHooks = this.create(beforeHandleRotationFloatHookTypes);
        this.overrideHandleRotationFloatHooks = this.create(overrideHandleRotationFloatHookTypes);
        this.afterHandleRotationFloatHooks = this.create(afterHandleRotationFloatHookTypes);
        this.isHandleRotationFloatModded = this.beforeHandleRotationFloatHooks != null || this.overrideHandleRotationFloatHooks != null || this.afterHandleRotationFloatHooks != null;
        this.beforeInheritRenderPassHooks = this.create(beforeInheritRenderPassHookTypes);
        this.overrideInheritRenderPassHooks = this.create(overrideInheritRenderPassHookTypes);
        this.afterInheritRenderPassHooks = this.create(afterInheritRenderPassHookTypes);
        this.isInheritRenderPassModded = this.beforeInheritRenderPassHooks != null || this.overrideInheritRenderPassHooks != null || this.afterInheritRenderPassHooks != null;
        this.beforeLoadDownloadableImageTextureHooks = this.create(beforeLoadDownloadableImageTextureHookTypes);
        this.overrideLoadDownloadableImageTextureHooks = this.create(overrideLoadDownloadableImageTextureHookTypes);
        this.afterLoadDownloadableImageTextureHooks = this.create(afterLoadDownloadableImageTextureHookTypes);
        this.isLoadDownloadableImageTextureModded = this.beforeLoadDownloadableImageTextureHooks != null || this.overrideLoadDownloadableImageTextureHooks != null || this.afterLoadDownloadableImageTextureHooks != null;
        this.beforeLoadPlayerTextureHooks = this.create(beforeLoadPlayerTextureHookTypes);
        this.overrideLoadPlayerTextureHooks = this.create(overrideLoadPlayerTextureHookTypes);
        this.afterLoadPlayerTextureHooks = this.create(afterLoadPlayerTextureHookTypes);
        this.isLoadPlayerTextureModded = this.beforeLoadPlayerTextureHooks != null || this.overrideLoadPlayerTextureHooks != null || this.afterLoadPlayerTextureHooks != null;
        this.beforeLoadTextureHooks = this.create(beforeLoadTextureHookTypes);
        this.overrideLoadTextureHooks = this.create(overrideLoadTextureHookTypes);
        this.afterLoadTextureHooks = this.create(afterLoadTextureHookTypes);
        this.isLoadTextureModded = this.beforeLoadTextureHooks != null || this.overrideLoadTextureHooks != null || this.afterLoadTextureHooks != null;
        this.beforePassSpecialRenderHooks = this.create(beforePassSpecialRenderHookTypes);
        this.overridePassSpecialRenderHooks = this.create(overridePassSpecialRenderHookTypes);
        this.afterPassSpecialRenderHooks = this.create(afterPassSpecialRenderHookTypes);
        this.isPassSpecialRenderModded = this.beforePassSpecialRenderHooks != null || this.overridePassSpecialRenderHooks != null || this.afterPassSpecialRenderHooks != null;
        this.beforeRenderArrowsStuckInEntityHooks = this.create(beforeRenderArrowsStuckInEntityHookTypes);
        this.overrideRenderArrowsStuckInEntityHooks = this.create(overrideRenderArrowsStuckInEntityHookTypes);
        this.afterRenderArrowsStuckInEntityHooks = this.create(afterRenderArrowsStuckInEntityHookTypes);
        this.isRenderArrowsStuckInEntityModded = this.beforeRenderArrowsStuckInEntityHooks != null || this.overrideRenderArrowsStuckInEntityHooks != null || this.afterRenderArrowsStuckInEntityHooks != null;
        this.beforeRenderFirstPersonArmHooks = this.create(beforeRenderFirstPersonArmHookTypes);
        this.overrideRenderFirstPersonArmHooks = this.create(overrideRenderFirstPersonArmHookTypes);
        this.afterRenderFirstPersonArmHooks = this.create(afterRenderFirstPersonArmHookTypes);
        this.isRenderFirstPersonArmModded = this.beforeRenderFirstPersonArmHooks != null || this.overrideRenderFirstPersonArmHooks != null || this.afterRenderFirstPersonArmHooks != null;
        this.beforeRenderLivingLabelHooks = this.create(beforeRenderLivingLabelHookTypes);
        this.overrideRenderLivingLabelHooks = this.create(overrideRenderLivingLabelHookTypes);
        this.afterRenderLivingLabelHooks = this.create(afterRenderLivingLabelHookTypes);
        this.isRenderLivingLabelModded = this.beforeRenderLivingLabelHooks != null || this.overrideRenderLivingLabelHooks != null || this.afterRenderLivingLabelHooks != null;
        this.beforeRenderModelHooks = this.create(beforeRenderModelHookTypes);
        this.overrideRenderModelHooks = this.create(overrideRenderModelHookTypes);
        this.afterRenderModelHooks = this.create(afterRenderModelHookTypes);
        this.isRenderModelModded = this.beforeRenderModelHooks != null || this.overrideRenderModelHooks != null || this.afterRenderModelHooks != null;
        this.beforeRenderPlayerHooks = this.create(beforeRenderPlayerHookTypes);
        this.overrideRenderPlayerHooks = this.create(overrideRenderPlayerHookTypes);
        this.afterRenderPlayerHooks = this.create(afterRenderPlayerHookTypes);
        this.isRenderPlayerModded = this.beforeRenderPlayerHooks != null || this.overrideRenderPlayerHooks != null || this.afterRenderPlayerHooks != null;
        this.beforeRenderPlayerNameAndScoreLabelHooks = this.create(beforeRenderPlayerNameAndScoreLabelHookTypes);
        this.overrideRenderPlayerNameAndScoreLabelHooks = this.create(overrideRenderPlayerNameAndScoreLabelHookTypes);
        this.afterRenderPlayerNameAndScoreLabelHooks = this.create(afterRenderPlayerNameAndScoreLabelHookTypes);
        this.isRenderPlayerNameAndScoreLabelModded = this.beforeRenderPlayerNameAndScoreLabelHooks != null || this.overrideRenderPlayerNameAndScoreLabelHooks != null || this.afterRenderPlayerNameAndScoreLabelHooks != null;
        this.beforeRenderPlayerScaleHooks = this.create(beforeRenderPlayerScaleHookTypes);
        this.overrideRenderPlayerScaleHooks = this.create(overrideRenderPlayerScaleHookTypes);
        this.afterRenderPlayerScaleHooks = this.create(afterRenderPlayerScaleHookTypes);
        this.isRenderPlayerScaleModded = this.beforeRenderPlayerScaleHooks != null || this.overrideRenderPlayerScaleHooks != null || this.afterRenderPlayerScaleHooks != null;
        this.beforeRenderPlayerSleepHooks = this.create(beforeRenderPlayerSleepHookTypes);
        this.overrideRenderPlayerSleepHooks = this.create(overrideRenderPlayerSleepHookTypes);
        this.afterRenderPlayerSleepHooks = this.create(afterRenderPlayerSleepHookTypes);
        this.isRenderPlayerSleepModded = this.beforeRenderPlayerSleepHooks != null || this.overrideRenderPlayerSleepHooks != null || this.afterRenderPlayerSleepHooks != null;
        this.beforeRenderSpecialsHooks = this.create(beforeRenderSpecialsHookTypes);
        this.overrideRenderSpecialsHooks = this.create(overrideRenderSpecialsHookTypes);
        this.afterRenderSpecialsHooks = this.create(afterRenderSpecialsHookTypes);
        this.isRenderSpecialsModded = this.beforeRenderSpecialsHooks != null || this.overrideRenderSpecialsHooks != null || this.afterRenderSpecialsHooks != null;
        this.beforeRenderSwingProgressHooks = this.create(beforeRenderSwingProgressHookTypes);
        this.overrideRenderSwingProgressHooks = this.create(overrideRenderSwingProgressHookTypes);
        this.afterRenderSwingProgressHooks = this.create(afterRenderSwingProgressHookTypes);
        this.isRenderSwingProgressModded = this.beforeRenderSwingProgressHooks != null || this.overrideRenderSwingProgressHooks != null || this.afterRenderSwingProgressHooks != null;
        this.beforeRotatePlayerHooks = this.create(beforeRotatePlayerHookTypes);
        this.overrideRotatePlayerHooks = this.create(overrideRotatePlayerHookTypes);
        this.afterRotatePlayerHooks = this.create(afterRotatePlayerHookTypes);
        this.isRotatePlayerModded = this.beforeRotatePlayerHooks != null || this.overrideRotatePlayerHooks != null || this.afterRotatePlayerHooks != null;
        this.beforeSetArmorModelHooks = this.create(beforeSetArmorModelHookTypes);
        this.overrideSetArmorModelHooks = this.create(overrideSetArmorModelHookTypes);
        this.afterSetArmorModelHooks = this.create(afterSetArmorModelHookTypes);
        this.isSetArmorModelModded = this.beforeSetArmorModelHooks != null || this.overrideSetArmorModelHooks != null || this.afterSetArmorModelHooks != null;
        this.beforeSetIconRegisterHooks = this.create(beforeSetIconRegisterHookTypes);
        this.overrideSetIconRegisterHooks = this.create(overrideSetIconRegisterHookTypes);
        this.afterSetIconRegisterHooks = this.create(afterSetIconRegisterHookTypes);
        this.isSetIconRegisterModded = this.beforeSetIconRegisterHooks != null || this.overrideSetIconRegisterHooks != null || this.afterSetIconRegisterHooks != null;
        this.beforeSetPassArmorModelHooks = this.create(beforeSetPassArmorModelHookTypes);
        this.overrideSetPassArmorModelHooks = this.create(overrideSetPassArmorModelHookTypes);
        this.afterSetPassArmorModelHooks = this.create(afterSetPassArmorModelHookTypes);
        this.isSetPassArmorModelModded = this.beforeSetPassArmorModelHooks != null || this.overrideSetPassArmorModelHooks != null || this.afterSetPassArmorModelHooks != null;
        this.beforeSetRenderManagerHooks = this.create(beforeSetRenderManagerHookTypes);
        this.overrideSetRenderManagerHooks = this.create(overrideSetRenderManagerHookTypes);
        this.afterSetRenderManagerHooks = this.create(afterSetRenderManagerHookTypes);
        this.isSetRenderManagerModded = this.beforeSetRenderManagerHooks != null || this.overrideSetRenderManagerHooks != null || this.afterSetRenderManagerHooks != null;
        this.beforeSetRenderPassModelHooks = this.create(beforeSetRenderPassModelHookTypes);
        this.overrideSetRenderPassModelHooks = this.create(overrideSetRenderPassModelHookTypes);
        this.afterSetRenderPassModelHooks = this.create(afterSetRenderPassModelHookTypes);
        this.isSetRenderPassModelModded = this.beforeSetRenderPassModelHooks != null || this.overrideSetRenderPassModelHooks != null || this.afterSetRenderPassModelHooks != null;
        this.beforeRenderSpecialHeadArmorHooks = this.create(beforeRenderSpecialHeadArmorHookTypes);
        this.overrideRenderSpecialHeadArmorHooks = this.create(overrideRenderSpecialHeadArmorHookTypes);
        this.afterRenderSpecialHeadArmorHooks = this.create(afterRenderSpecialHeadArmorHookTypes);
        this.isRenderSpecialHeadArmorModded = this.beforeRenderSpecialHeadArmorHooks != null || this.overrideRenderSpecialHeadArmorHooks != null || this.afterRenderSpecialHeadArmorHooks != null;
        this.beforeRenderSpecialHeadEarsHooks = this.create(beforeRenderSpecialHeadEarsHookTypes);
        this.overrideRenderSpecialHeadEarsHooks = this.create(overrideRenderSpecialHeadEarsHookTypes);
        this.afterRenderSpecialHeadEarsHooks = this.create(afterRenderSpecialHeadEarsHookTypes);
        this.isRenderSpecialHeadEarsModded = this.beforeRenderSpecialHeadEarsHooks != null || this.overrideRenderSpecialHeadEarsHooks != null || this.afterRenderSpecialHeadEarsHooks != null;
        this.beforeRenderSpecialCloakHooks = this.create(beforeRenderSpecialCloakHookTypes);
        this.overrideRenderSpecialCloakHooks = this.create(overrideRenderSpecialCloakHookTypes);
        this.afterRenderSpecialCloakHooks = this.create(afterRenderSpecialCloakHookTypes);
        this.isRenderSpecialCloakModded = this.beforeRenderSpecialCloakHooks != null || this.overrideRenderSpecialCloakHooks != null || this.afterRenderSpecialCloakHooks != null;
        this.beforeRenderSpecialItemInHandHooks = this.create(beforeRenderSpecialItemInHandHookTypes);
        this.overrideRenderSpecialItemInHandHooks = this.create(overrideRenderSpecialItemInHandHookTypes);
        this.afterRenderSpecialItemInHandHooks = this.create(afterRenderSpecialItemInHandHookTypes);
        this.isRenderSpecialItemInHandModded = this.beforeRenderSpecialItemInHandHooks != null || this.overrideRenderSpecialItemInHandHooks != null || this.afterRenderSpecialItemInHandHooks != null;
        this.beforePositionSpecialItemInHandHooks = this.create(beforePositionSpecialItemInHandHookTypes);
        this.overridePositionSpecialItemInHandHooks = this.create(overridePositionSpecialItemInHandHookTypes);
        this.afterPositionSpecialItemInHandHooks = this.create(afterPositionSpecialItemInHandHookTypes);
        this.isPositionSpecialItemInHandModded = this.beforePositionSpecialItemInHandHooks != null || this.overridePositionSpecialItemInHandHooks != null || this.afterPositionSpecialItemInHandHooks != null;
    }

    private void attachRenderPlayerBase(String string)
    {
        initializer[0] = this;
        initializers[0] = this;
        RenderPlayerBase renderPlayerBase = this.createRenderPlayerBase(string);
        renderPlayerBase.beforeBaseAttach(true);
        this.allBaseObjects.put(string, renderPlayerBase);
        this.updateRenderPlayerBases();
        renderPlayerBase.afterBaseAttach(true);
    }

    private void detachRenderPlayerBase(String string)
    {
        RenderPlayerBase renderPlayerBase = (RenderPlayerBase)this.allBaseObjects.get(string);
        renderPlayerBase.beforeBaseDetach(true);
        this.allBaseObjects.remove(string);
        this.updateRenderPlayerBases();
        renderPlayerBase.afterBaseDetach(true);
    }

    private RenderPlayerBase[] create(List list)
    {
        if (list.isEmpty())
        {
            return null;
        }
        else
        {
            RenderPlayerBase[] arrrenderPlayerBase = new RenderPlayerBase[list.size()];

            for (int i = 0; i < arrrenderPlayerBase.length; ++i)
            {
                arrrenderPlayerBase[i] = this.getRenderPlayerBase((String)list.get(i));
            }

            return arrrenderPlayerBase;
        }
    }

    private void beforeLocalConstructing()
    {
        if (this.beforeLocalConstructingHooks != null)
        {
            for (int i = this.beforeLocalConstructingHooks.length - 1; i >= 0; --i)
            {
                this.beforeLocalConstructingHooks[i].beforeLocalConstructing();
            }
        }

        this.beforeLocalConstructingHooks = null;
    }

    private void afterLocalConstructing()
    {
        if (this.afterLocalConstructingHooks != null)
        {
            for (int i = 0; i < this.afterLocalConstructingHooks.length; ++i)
            {
                this.afterLocalConstructingHooks[i].afterLocalConstructing();
            }
        }

        this.afterLocalConstructingHooks = null;
    }

    public RenderPlayerBase getRenderPlayerBase(String string)
    {
        return (RenderPlayerBase)this.allBaseObjects.get(string);
    }

    public Set getRenderPlayerBaseIds()
    {
        return this.unmodifiableAllBaseIds;
    }

    public Object dynamic(String string, Object[] arrobject)
    {
        string = string.replace('.', '_').replace(' ', '_');
        this.executeAll(string, arrobject, beforeDynamicHookTypes, beforeDynamicHookMethods, true);
        Object object = this.dynamicOverwritten(string, arrobject, (RenderPlayerBase)null);
        this.executeAll(string, arrobject, afterDynamicHookTypes, afterDynamicHookMethods, false);
        return object;
    }

    public Object dynamicOverwritten(String string, Object[] arrobject, RenderPlayerBase renderPlayerBase)
    {
        List list = (List)overrideDynamicHookTypes.get(string);
        String string2 = null;

        if (list != null)
        {
            if (renderPlayerBase != null)
            {
                string2 = (String)this.baseObjectsToId.get(renderPlayerBase);
                int map2 = list.indexOf(string2);
                string2 = map2 > 0 ? (String)list.get(map2 - 1) : null;
            }
            else if (list.size() > 0)
            {
                string2 = (String)list.get(list.size() - 1);
            }
        }

        Map map;

        if (string2 == null)
        {
            string2 = (String)keysToVirtualIds.get(string);

            if (string2 == null)
            {
                return null;
            }

            map = virtualDynamicHookMethods;
        }
        else
        {
            map = overrideDynamicHookMethods;
        }

        Map map21 = (Map)map.get(((Constructor)allBaseConstructors.get(string2)).getDeclaringClass());

        if (map21 == null)
        {
            return null;
        }
        else
        {
            Method method = (Method)map21.get(string);
            return this.execute(this.getRenderPlayerBase(string2), method, arrobject);
        }
    }

    private void executeAll(String string, Object[] arrobject, Map map, Map map2, boolean bl)
    {
        List list = (List)map.get(string);

        if (list != null)
        {
            int n = bl ? list.size() - 1 : 0;

            while (true)
            {
                if (bl)
                {
                    if (n < 0)
                    {
                        break;
                    }
                }
                else if (n >= list.size())
                {
                    break;
                }

                String string2 = (String)list.get(n);
                RenderPlayerBase renderPlayerBase = this.getRenderPlayerBase(string2);
                Class class_ = renderPlayerBase.getClass();
                Map map3 = (Map)map2.get(class_);
                Method method;

                if (map3 != null && (method = (Method)map3.get(string)) != null)
                {
                    this.execute(renderPlayerBase, method, arrobject);
                }

                n += bl ? -1 : 1;
            }
        }
    }

    private Object execute(RenderPlayerBase renderPlayerBase, Method method, Object[] arrobject)
    {
        try
        {
            return method.invoke(renderPlayerBase, arrobject);
        }
        catch (Exception var5)
        {
            throw new RuntimeException("Exception while invoking dynamic method", var5);
        }
    }

    public static void doRenderShadowAndFire(RenderPlayer renderPlayer, Entity entity, double d, double d2, double d3, float f, float f2)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.doRenderShadowAndFire(entity, d, d2, d3, f, f2);
        }
        else
        {
            renderPlayer.localDoRenderShadowAndFire(entity, d, d2, d3, f, f2);
        }
    }

    private void doRenderShadowAndFire(Entity entity, double d, double d2, double d3, float f, float f2)
    {
        int n;

        if (this.beforeDoRenderShadowAndFireHooks != null)
        {
            for (n = this.beforeDoRenderShadowAndFireHooks.length - 1; n >= 0; --n)
            {
                this.beforeDoRenderShadowAndFireHooks[n].beforeDoRenderShadowAndFire(entity, d, d2, d3, f, f2);
            }
        }

        if (this.overrideDoRenderShadowAndFireHooks != null)
        {
            this.overrideDoRenderShadowAndFireHooks[this.overrideDoRenderShadowAndFireHooks.length - 1].doRenderShadowAndFire(entity, d, d2, d3, f, f2);
        }
        else
        {
            this.renderPlayer.localDoRenderShadowAndFire(entity, d, d2, d3, f, f2);
        }

        if (this.afterDoRenderShadowAndFireHooks != null)
        {
            for (n = 0; n < this.afterDoRenderShadowAndFireHooks.length; ++n)
            {
                this.afterDoRenderShadowAndFireHooks[n].afterDoRenderShadowAndFire(entity, d, d2, d3, f, f2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenDoRenderShadowAndFire(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideDoRenderShadowAndFireHooks.length; ++i)
        {
            if (this.overrideDoRenderShadowAndFireHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideDoRenderShadowAndFireHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static int getColorMultiplier(RenderPlayer renderPlayer, EntityLiving entityLiving, float f, float f2)
    {
        int n = renderPlayer.renderPlayerAPI != null ? renderPlayer.renderPlayerAPI.getColorMultiplier(entityLiving, f, f2) : renderPlayer.localGetColorMultiplier(entityLiving, f, f2);
        return n;
    }

    private int getColorMultiplier(EntityLiving entityLiving, float f, float f2)
    {
        int n;

        if (this.beforeGetColorMultiplierHooks != null)
        {
            for (n = this.beforeGetColorMultiplierHooks.length - 1; n >= 0; --n)
            {
                this.beforeGetColorMultiplierHooks[n].beforeGetColorMultiplier(entityLiving, f, f2);
            }
        }

        n = this.overrideGetColorMultiplierHooks != null ? this.overrideGetColorMultiplierHooks[this.overrideGetColorMultiplierHooks.length - 1].getColorMultiplier(entityLiving, f, f2) : this.renderPlayer.localGetColorMultiplier(entityLiving, f, f2);

        if (this.afterGetColorMultiplierHooks != null)
        {
            for (int i = 0; i < this.afterGetColorMultiplierHooks.length; ++i)
            {
                this.afterGetColorMultiplierHooks[i].afterGetColorMultiplier(entityLiving, f, f2);
            }
        }

        return n;
    }

    protected RenderPlayerBase GetOverwrittenGetColorMultiplier(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideGetColorMultiplierHooks.length; ++i)
        {
            if (this.overrideGetColorMultiplierHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideGetColorMultiplierHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static float getDeathMaxRotation(RenderPlayer renderPlayer, EntityLiving entityLiving)
    {
        float f = renderPlayer.renderPlayerAPI != null ? renderPlayer.renderPlayerAPI.getDeathMaxRotation(entityLiving) : renderPlayer.localGetDeathMaxRotation(entityLiving);
        return f;
    }

    private float getDeathMaxRotation(EntityLiving entityLiving)
    {
        if (this.beforeGetDeathMaxRotationHooks != null)
        {
            for (int f = this.beforeGetDeathMaxRotationHooks.length - 1; f >= 0; --f)
            {
                this.beforeGetDeathMaxRotationHooks[f].beforeGetDeathMaxRotation(entityLiving);
            }
        }

        float var4 = this.overrideGetDeathMaxRotationHooks != null ? this.overrideGetDeathMaxRotationHooks[this.overrideGetDeathMaxRotationHooks.length - 1].getDeathMaxRotation(entityLiving) : this.renderPlayer.localGetDeathMaxRotation(entityLiving);

        if (this.afterGetDeathMaxRotationHooks != null)
        {
            for (int i = 0; i < this.afterGetDeathMaxRotationHooks.length; ++i)
            {
                this.afterGetDeathMaxRotationHooks[i].afterGetDeathMaxRotation(entityLiving);
            }
        }

        return var4;
    }

    protected RenderPlayerBase GetOverwrittenGetDeathMaxRotation(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideGetDeathMaxRotationHooks.length; ++i)
        {
            if (this.overrideGetDeathMaxRotationHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideGetDeathMaxRotationHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static FontRenderer getFontRendererFromRenderManager(RenderPlayer renderPlayer)
    {
        FontRenderer fontRenderer = renderPlayer.renderPlayerAPI != null ? renderPlayer.renderPlayerAPI.getFontRendererFromRenderManager() : renderPlayer.localGetFontRendererFromRenderManager();
        return fontRenderer;
    }

    private FontRenderer getFontRendererFromRenderManager()
    {
        if (this.beforeGetFontRendererFromRenderManagerHooks != null)
        {
            for (int fontRenderer = this.beforeGetFontRendererFromRenderManagerHooks.length - 1; fontRenderer >= 0; --fontRenderer)
            {
                this.beforeGetFontRendererFromRenderManagerHooks[fontRenderer].beforeGetFontRendererFromRenderManager();
            }
        }

        FontRenderer var3 = this.overrideGetFontRendererFromRenderManagerHooks != null ? this.overrideGetFontRendererFromRenderManagerHooks[this.overrideGetFontRendererFromRenderManagerHooks.length - 1].getFontRendererFromRenderManager() : this.renderPlayer.localGetFontRendererFromRenderManager();

        if (this.afterGetFontRendererFromRenderManagerHooks != null)
        {
            for (int i = 0; i < this.afterGetFontRendererFromRenderManagerHooks.length; ++i)
            {
                this.afterGetFontRendererFromRenderManagerHooks[i].afterGetFontRendererFromRenderManager();
            }
        }

        return var3;
    }

    protected RenderPlayerBase GetOverwrittenGetFontRendererFromRenderManager(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideGetFontRendererFromRenderManagerHooks.length; ++i)
        {
            if (this.overrideGetFontRendererFromRenderManagerHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideGetFontRendererFromRenderManagerHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static float handleRotationFloat(RenderPlayer renderPlayer, EntityLiving entityLiving, float f)
    {
        float f2 = renderPlayer.renderPlayerAPI != null ? renderPlayer.renderPlayerAPI.handleRotationFloat(entityLiving, f) : renderPlayer.localHandleRotationFloat(entityLiving, f);
        return f2;
    }

    private float handleRotationFloat(EntityLiving entityLiving, float f)
    {
        if (this.beforeHandleRotationFloatHooks != null)
        {
            for (int f2 = this.beforeHandleRotationFloatHooks.length - 1; f2 >= 0; --f2)
            {
                this.beforeHandleRotationFloatHooks[f2].beforeHandleRotationFloat(entityLiving, f);
            }
        }

        float var5 = this.overrideHandleRotationFloatHooks != null ? this.overrideHandleRotationFloatHooks[this.overrideHandleRotationFloatHooks.length - 1].handleRotationFloat(entityLiving, f) : this.renderPlayer.localHandleRotationFloat(entityLiving, f);

        if (this.afterHandleRotationFloatHooks != null)
        {
            for (int i = 0; i < this.afterHandleRotationFloatHooks.length; ++i)
            {
                this.afterHandleRotationFloatHooks[i].afterHandleRotationFloat(entityLiving, f);
            }
        }

        return var5;
    }

    protected RenderPlayerBase GetOverwrittenHandleRotationFloat(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideHandleRotationFloatHooks.length; ++i)
        {
            if (this.overrideHandleRotationFloatHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideHandleRotationFloatHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static int inheritRenderPass(RenderPlayer renderPlayer, EntityLiving entityLiving, int n, float f)
    {
        int n2 = renderPlayer.renderPlayerAPI != null ? renderPlayer.renderPlayerAPI.inheritRenderPass(entityLiving, n, f) : renderPlayer.localInheritRenderPass(entityLiving, n, f);
        return n2;
    }

    private int inheritRenderPass(EntityLiving entityLiving, int n, float f)
    {
        int n2;

        if (this.beforeInheritRenderPassHooks != null)
        {
            for (n2 = this.beforeInheritRenderPassHooks.length - 1; n2 >= 0; --n2)
            {
                this.beforeInheritRenderPassHooks[n2].beforeInheritRenderPass(entityLiving, n, f);
            }
        }

        n2 = this.overrideInheritRenderPassHooks != null ? this.overrideInheritRenderPassHooks[this.overrideInheritRenderPassHooks.length - 1].inheritRenderPass(entityLiving, n, f) : this.renderPlayer.localInheritRenderPass(entityLiving, n, f);

        if (this.afterInheritRenderPassHooks != null)
        {
            for (int i = 0; i < this.afterInheritRenderPassHooks.length; ++i)
            {
                this.afterInheritRenderPassHooks[i].afterInheritRenderPass(entityLiving, n, f);
            }
        }

        return n2;
    }

    protected RenderPlayerBase GetOverwrittenInheritRenderPass(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideInheritRenderPassHooks.length; ++i)
        {
            if (this.overrideInheritRenderPassHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideInheritRenderPassHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static boolean loadDownloadableImageTexture(RenderPlayer renderPlayer, String string, String string2)
    {
        boolean bl = renderPlayer.renderPlayerAPI != null ? renderPlayer.renderPlayerAPI.loadDownloadableImageTexture(string, string2) : renderPlayer.localLoadDownloadableImageTexture(string, string2);
        return bl;
    }

    private boolean loadDownloadableImageTexture(String string, String string2)
    {
        if (this.beforeLoadDownloadableImageTextureHooks != null)
        {
            Integer n = Integer.valueOf(this.beforeLoadDownloadableImageTextureHooks.length - 1);

            while (((Integer)n).intValue() >= 0)
            {
                this.beforeLoadDownloadableImageTextureHooks[((Integer)n).intValue()].beforeLoadDownloadableImageTexture(string, string2);
            }
        }

        Object var5 = this.overrideLoadDownloadableImageTextureHooks != null ? Boolean.valueOf(this.overrideLoadDownloadableImageTextureHooks[this.overrideLoadDownloadableImageTextureHooks.length - 1].loadDownloadableImageTexture(string, string2)) : Integer.valueOf(this.renderPlayer.localLoadDownloadableImageTexture(string, string2) ? 1 : 0);

        if (this.afterLoadDownloadableImageTextureHooks != null)
        {
            for (int i = 0; i < this.afterLoadDownloadableImageTextureHooks.length; ++i)
            {
                this.afterLoadDownloadableImageTextureHooks[i].afterLoadDownloadableImageTexture(string, string2);
            }
        }

        return ((Boolean)var5).booleanValue();
    }

    protected RenderPlayerBase GetOverwrittenLoadDownloadableImageTexture(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideLoadDownloadableImageTextureHooks.length; ++i)
        {
            if (this.overrideLoadDownloadableImageTextureHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideLoadDownloadableImageTextureHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void loadPlayerTexture(RenderPlayer renderPlayer, EntityPlayer entityPlayer)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.loadPlayerTexture(entityPlayer);
        }
        else
        {
            renderPlayer.localLoadPlayerTexture(entityPlayer);
        }
    }

    private void loadPlayerTexture(EntityPlayer entityPlayer)
    {
        int n;

        if (this.beforeLoadPlayerTextureHooks != null)
        {
            for (n = this.beforeLoadPlayerTextureHooks.length - 1; n >= 0; --n)
            {
                this.beforeLoadPlayerTextureHooks[n].beforeLoadPlayerTexture(entityPlayer);
            }
        }

        if (this.overrideLoadPlayerTextureHooks != null)
        {
            this.overrideLoadPlayerTextureHooks[this.overrideLoadPlayerTextureHooks.length - 1].loadPlayerTexture(entityPlayer);
        }
        else
        {
            this.renderPlayer.localLoadPlayerTexture(entityPlayer);
        }

        if (this.afterLoadPlayerTextureHooks != null)
        {
            for (n = 0; n < this.afterLoadPlayerTextureHooks.length; ++n)
            {
                this.afterLoadPlayerTextureHooks[n].afterLoadPlayerTexture(entityPlayer);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenLoadPlayerTexture(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideLoadPlayerTextureHooks.length; ++i)
        {
            if (this.overrideLoadPlayerTextureHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideLoadPlayerTextureHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void loadTexture(RenderPlayer renderPlayer, String string)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.loadTexture(string);
        }
        else
        {
            renderPlayer.localLoadTexture(string);
        }
    }

    private void loadTexture(String string)
    {
        int n;

        if (this.beforeLoadTextureHooks != null)
        {
            for (n = this.beforeLoadTextureHooks.length - 1; n >= 0; --n)
            {
                this.beforeLoadTextureHooks[n].beforeLoadTexture(string);
            }
        }

        if (this.overrideLoadTextureHooks != null)
        {
            this.overrideLoadTextureHooks[this.overrideLoadTextureHooks.length - 1].loadTexture(string);
        }
        else
        {
            this.renderPlayer.localLoadTexture(string);
        }

        if (this.afterLoadTextureHooks != null)
        {
            for (n = 0; n < this.afterLoadTextureHooks.length; ++n)
            {
                this.afterLoadTextureHooks[n].afterLoadTexture(string);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenLoadTexture(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideLoadTextureHooks.length; ++i)
        {
            if (this.overrideLoadTextureHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideLoadTextureHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void passSpecialRender(RenderPlayer renderPlayer, EntityLiving entityLiving, double d, double d2, double d3)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.passSpecialRender(entityLiving, d, d2, d3);
        }
        else
        {
            renderPlayer.localPassSpecialRender(entityLiving, d, d2, d3);
        }
    }

    private void passSpecialRender(EntityLiving entityLiving, double d, double d2, double d3)
    {
        int n;

        if (this.beforePassSpecialRenderHooks != null)
        {
            for (n = this.beforePassSpecialRenderHooks.length - 1; n >= 0; --n)
            {
                this.beforePassSpecialRenderHooks[n].beforePassSpecialRender(entityLiving, d, d2, d3);
            }
        }

        if (this.overridePassSpecialRenderHooks != null)
        {
            this.overridePassSpecialRenderHooks[this.overridePassSpecialRenderHooks.length - 1].passSpecialRender(entityLiving, d, d2, d3);
        }
        else
        {
            this.renderPlayer.localPassSpecialRender(entityLiving, d, d2, d3);
        }

        if (this.afterPassSpecialRenderHooks != null)
        {
            for (n = 0; n < this.afterPassSpecialRenderHooks.length; ++n)
            {
                this.afterPassSpecialRenderHooks[n].afterPassSpecialRender(entityLiving, d, d2, d3);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenPassSpecialRender(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overridePassSpecialRenderHooks.length; ++i)
        {
            if (this.overridePassSpecialRenderHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overridePassSpecialRenderHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderArrowsStuckInEntity(RenderPlayer renderPlayer, EntityLiving entityLiving, float f)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderArrowsStuckInEntity(entityLiving, f);
        }
        else
        {
            renderPlayer.localRenderArrowsStuckInEntity(entityLiving, f);
        }
    }

    private void renderArrowsStuckInEntity(EntityLiving entityLiving, float f)
    {
        int n;

        if (this.beforeRenderArrowsStuckInEntityHooks != null)
        {
            for (n = this.beforeRenderArrowsStuckInEntityHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderArrowsStuckInEntityHooks[n].beforeRenderArrowsStuckInEntity(entityLiving, f);
            }
        }

        if (this.overrideRenderArrowsStuckInEntityHooks != null)
        {
            this.overrideRenderArrowsStuckInEntityHooks[this.overrideRenderArrowsStuckInEntityHooks.length - 1].renderArrowsStuckInEntity(entityLiving, f);
        }
        else
        {
            this.renderPlayer.localRenderArrowsStuckInEntity(entityLiving, f);
        }

        if (this.afterRenderArrowsStuckInEntityHooks != null)
        {
            for (n = 0; n < this.afterRenderArrowsStuckInEntityHooks.length; ++n)
            {
                this.afterRenderArrowsStuckInEntityHooks[n].afterRenderArrowsStuckInEntity(entityLiving, f);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderArrowsStuckInEntity(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderArrowsStuckInEntityHooks.length; ++i)
        {
            if (this.overrideRenderArrowsStuckInEntityHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderArrowsStuckInEntityHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderFirstPersonArm(RenderPlayer renderPlayer, EntityPlayer entityPlayer)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderFirstPersonArm(entityPlayer);
        }
        else
        {
            renderPlayer.localRenderFirstPersonArm(entityPlayer);
        }
    }

    private void renderFirstPersonArm(EntityPlayer entityPlayer)
    {
        int n;

        if (this.beforeRenderFirstPersonArmHooks != null)
        {
            for (n = this.beforeRenderFirstPersonArmHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderFirstPersonArmHooks[n].beforeRenderFirstPersonArm(entityPlayer);
            }
        }

        if (this.overrideRenderFirstPersonArmHooks != null)
        {
            this.overrideRenderFirstPersonArmHooks[this.overrideRenderFirstPersonArmHooks.length - 1].renderFirstPersonArm(entityPlayer);
        }
        else
        {
            this.renderPlayer.localRenderFirstPersonArm(entityPlayer);
        }

        if (this.afterRenderFirstPersonArmHooks != null)
        {
            for (n = 0; n < this.afterRenderFirstPersonArmHooks.length; ++n)
            {
                this.afterRenderFirstPersonArmHooks[n].afterRenderFirstPersonArm(entityPlayer);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderFirstPersonArm(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderFirstPersonArmHooks.length; ++i)
        {
            if (this.overrideRenderFirstPersonArmHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderFirstPersonArmHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderLivingLabel(RenderPlayer renderPlayer, EntityLiving entityLiving, String string, double d, double d2, double d3, int n)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderLivingLabel(entityLiving, string, d, d2, d3, n);
        }
        else
        {
            renderPlayer.localRenderLivingLabel(entityLiving, string, d, d2, d3, n);
        }
    }

    private void renderLivingLabel(EntityLiving entityLiving, String string, double d, double d2, double d3, int n)
    {
        int n2;

        if (this.beforeRenderLivingLabelHooks != null)
        {
            for (n2 = this.beforeRenderLivingLabelHooks.length - 1; n2 >= 0; --n2)
            {
                this.beforeRenderLivingLabelHooks[n2].beforeRenderLivingLabel(entityLiving, string, d, d2, d3, n);
            }
        }

        if (this.overrideRenderLivingLabelHooks != null)
        {
            this.overrideRenderLivingLabelHooks[this.overrideRenderLivingLabelHooks.length - 1].renderLivingLabel(entityLiving, string, d, d2, d3, n);
        }
        else
        {
            this.renderPlayer.localRenderLivingLabel(entityLiving, string, d, d2, d3, n);
        }

        if (this.afterRenderLivingLabelHooks != null)
        {
            for (n2 = 0; n2 < this.afterRenderLivingLabelHooks.length; ++n2)
            {
                this.afterRenderLivingLabelHooks[n2].afterRenderLivingLabel(entityLiving, string, d, d2, d3, n);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderLivingLabel(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderLivingLabelHooks.length; ++i)
        {
            if (this.overrideRenderLivingLabelHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderLivingLabelHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderModel(RenderPlayer renderPlayer, EntityLiving entityLiving, float f, float f2, float f3, float f4, float f5, float f6)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderModel(entityLiving, f, f2, f3, f4, f5, f6);
        }
        else
        {
            renderPlayer.localRenderModel(entityLiving, f, f2, f3, f4, f5, f6);
        }
    }

    private void renderModel(EntityLiving entityLiving, float f, float f2, float f3, float f4, float f5, float f6)
    {
        int n;

        if (this.beforeRenderModelHooks != null)
        {
            for (n = this.beforeRenderModelHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderModelHooks[n].beforeRenderModel(entityLiving, f, f2, f3, f4, f5, f6);
            }
        }

        if (this.overrideRenderModelHooks != null)
        {
            this.overrideRenderModelHooks[this.overrideRenderModelHooks.length - 1].renderModel(entityLiving, f, f2, f3, f4, f5, f6);
        }
        else
        {
            this.renderPlayer.localRenderModel(entityLiving, f, f2, f3, f4, f5, f6);
        }

        if (this.afterRenderModelHooks != null)
        {
            for (n = 0; n < this.afterRenderModelHooks.length; ++n)
            {
                this.afterRenderModelHooks[n].afterRenderModel(entityLiving, f, f2, f3, f4, f5, f6);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderModel(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderModelHooks.length; ++i)
        {
            if (this.overrideRenderModelHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderModelHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderPlayer(RenderPlayer renderPlayer, EntityPlayer entityPlayer, double d, double d2, double d3, float f, float f2)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderPlayer(entityPlayer, d, d2, d3, f, f2);
        }
        else
        {
            renderPlayer.localRenderPlayer(entityPlayer, d, d2, d3, f, f2);
        }
    }

    private void renderPlayer(EntityPlayer entityPlayer, double d, double d2, double d3, float f, float f2)
    {
        int n;

        if (this.beforeRenderPlayerHooks != null)
        {
            for (n = this.beforeRenderPlayerHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderPlayerHooks[n].beforeRenderPlayer(entityPlayer, d, d2, d3, f, f2);
            }
        }

        if (this.overrideRenderPlayerHooks != null)
        {
            this.overrideRenderPlayerHooks[this.overrideRenderPlayerHooks.length - 1].renderPlayer(entityPlayer, d, d2, d3, f, f2);
        }
        else
        {
            this.renderPlayer.localRenderPlayer(entityPlayer, d, d2, d3, f, f2);
        }

        if (this.afterRenderPlayerHooks != null)
        {
            for (n = 0; n < this.afterRenderPlayerHooks.length; ++n)
            {
                this.afterRenderPlayerHooks[n].afterRenderPlayer(entityPlayer, d, d2, d3, f, f2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderPlayer(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderPlayerHooks.length; ++i)
        {
            if (this.overrideRenderPlayerHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderPlayerHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderPlayerNameAndScoreLabel(RenderPlayer renderPlayer, EntityPlayer entityPlayer, double d, double d2, double d3, String string, float f, double d4)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderPlayerNameAndScoreLabel(entityPlayer, d, d2, d3, string, f, d4);
        }
        else
        {
            renderPlayer.localRenderPlayerNameAndScoreLabel(entityPlayer, d, d2, d3, string, f, d4);
        }
    }

    private void renderPlayerNameAndScoreLabel(EntityPlayer entityPlayer, double d, double d2, double d3, String string, float f, double d4)
    {
        int n;

        if (this.beforeRenderPlayerNameAndScoreLabelHooks != null)
        {
            for (n = this.beforeRenderPlayerNameAndScoreLabelHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderPlayerNameAndScoreLabelHooks[n].beforeRenderPlayerNameAndScoreLabel(entityPlayer, d, d2, d3, string, f, d4);
            }
        }

        if (this.overrideRenderPlayerNameAndScoreLabelHooks != null)
        {
            this.overrideRenderPlayerNameAndScoreLabelHooks[this.overrideRenderPlayerNameAndScoreLabelHooks.length - 1].renderPlayerNameAndScoreLabel(entityPlayer, d, d2, d3, string, f, d4);
        }
        else
        {
            this.renderPlayer.localRenderPlayerNameAndScoreLabel(entityPlayer, d, d2, d3, string, f, d4);
        }

        if (this.afterRenderPlayerNameAndScoreLabelHooks != null)
        {
            for (n = 0; n < this.afterRenderPlayerNameAndScoreLabelHooks.length; ++n)
            {
                this.afterRenderPlayerNameAndScoreLabelHooks[n].afterRenderPlayerNameAndScoreLabel(entityPlayer, d, d2, d3, string, f, d4);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderPlayerNameAndScoreLabel(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderPlayerNameAndScoreLabelHooks.length; ++i)
        {
            if (this.overrideRenderPlayerNameAndScoreLabelHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderPlayerNameAndScoreLabelHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderPlayerScale(RenderPlayer renderPlayer, EntityPlayer entityPlayer, float f)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderPlayerScale(entityPlayer, f);
        }
        else
        {
            renderPlayer.localRenderPlayerScale(entityPlayer, f);
        }
    }

    private void renderPlayerScale(EntityPlayer entityPlayer, float f)
    {
        int n;

        if (this.beforeRenderPlayerScaleHooks != null)
        {
            for (n = this.beforeRenderPlayerScaleHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderPlayerScaleHooks[n].beforeRenderPlayerScale(entityPlayer, f);
            }
        }

        if (this.overrideRenderPlayerScaleHooks != null)
        {
            this.overrideRenderPlayerScaleHooks[this.overrideRenderPlayerScaleHooks.length - 1].renderPlayerScale(entityPlayer, f);
        }
        else
        {
            this.renderPlayer.localRenderPlayerScale(entityPlayer, f);
        }

        if (this.afterRenderPlayerScaleHooks != null)
        {
            for (n = 0; n < this.afterRenderPlayerScaleHooks.length; ++n)
            {
                this.afterRenderPlayerScaleHooks[n].afterRenderPlayerScale(entityPlayer, f);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderPlayerScale(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderPlayerScaleHooks.length; ++i)
        {
            if (this.overrideRenderPlayerScaleHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderPlayerScaleHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderPlayerSleep(RenderPlayer renderPlayer, EntityPlayer entityPlayer, double d, double d2, double d3)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderPlayerSleep(entityPlayer, d, d2, d3);
        }
        else
        {
            renderPlayer.localRenderPlayerSleep(entityPlayer, d, d2, d3);
        }
    }

    private void renderPlayerSleep(EntityPlayer entityPlayer, double d, double d2, double d3)
    {
        int n;

        if (this.beforeRenderPlayerSleepHooks != null)
        {
            for (n = this.beforeRenderPlayerSleepHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderPlayerSleepHooks[n].beforeRenderPlayerSleep(entityPlayer, d, d2, d3);
            }
        }

        if (this.overrideRenderPlayerSleepHooks != null)
        {
            this.overrideRenderPlayerSleepHooks[this.overrideRenderPlayerSleepHooks.length - 1].renderPlayerSleep(entityPlayer, d, d2, d3);
        }
        else
        {
            this.renderPlayer.localRenderPlayerSleep(entityPlayer, d, d2, d3);
        }

        if (this.afterRenderPlayerSleepHooks != null)
        {
            for (n = 0; n < this.afterRenderPlayerSleepHooks.length; ++n)
            {
                this.afterRenderPlayerSleepHooks[n].afterRenderPlayerSleep(entityPlayer, d, d2, d3);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderPlayerSleep(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderPlayerSleepHooks.length; ++i)
        {
            if (this.overrideRenderPlayerSleepHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderPlayerSleepHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderSpecials(RenderPlayer renderPlayer, EntityPlayer entityPlayer, float f)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderSpecials(entityPlayer, f);
        }
        else
        {
            renderPlayer.localRenderSpecials(entityPlayer, f);
        }
    }

    private void renderSpecials(EntityPlayer entityPlayer, float f)
    {
        int n;

        if (this.beforeRenderSpecialsHooks != null)
        {
            for (n = this.beforeRenderSpecialsHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderSpecialsHooks[n].beforeRenderSpecials(entityPlayer, f);
            }
        }

        if (this.overrideRenderSpecialsHooks != null)
        {
            this.overrideRenderSpecialsHooks[this.overrideRenderSpecialsHooks.length - 1].renderSpecials(entityPlayer, f);
        }
        else
        {
            this.renderPlayer.localRenderSpecials(entityPlayer, f);
        }

        if (this.afterRenderSpecialsHooks != null)
        {
            for (n = 0; n < this.afterRenderSpecialsHooks.length; ++n)
            {
                this.afterRenderSpecialsHooks[n].afterRenderSpecials(entityPlayer, f);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecials(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderSpecialsHooks.length; ++i)
        {
            if (this.overrideRenderSpecialsHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialsHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static float renderSwingProgress(RenderPlayer renderPlayer, EntityLiving entityLiving, float f)
    {
        float f2 = renderPlayer.renderPlayerAPI != null ? renderPlayer.renderPlayerAPI.renderSwingProgress(entityLiving, f) : renderPlayer.localRenderSwingProgress(entityLiving, f);
        return f2;
    }

    private float renderSwingProgress(EntityLiving entityLiving, float f)
    {
        if (this.beforeRenderSwingProgressHooks != null)
        {
            for (int f2 = this.beforeRenderSwingProgressHooks.length - 1; f2 >= 0; --f2)
            {
                this.beforeRenderSwingProgressHooks[f2].beforeRenderSwingProgress(entityLiving, f);
            }
        }

        float var5 = this.overrideRenderSwingProgressHooks != null ? this.overrideRenderSwingProgressHooks[this.overrideRenderSwingProgressHooks.length - 1].renderSwingProgress(entityLiving, f) : this.renderPlayer.localRenderSwingProgress(entityLiving, f);

        if (this.afterRenderSwingProgressHooks != null)
        {
            for (int i = 0; i < this.afterRenderSwingProgressHooks.length; ++i)
            {
                this.afterRenderSwingProgressHooks[i].afterRenderSwingProgress(entityLiving, f);
            }
        }

        return var5;
    }

    protected RenderPlayerBase GetOverwrittenRenderSwingProgress(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderSwingProgressHooks.length; ++i)
        {
            if (this.overrideRenderSwingProgressHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderSwingProgressHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void rotatePlayer(RenderPlayer renderPlayer, EntityPlayer entityPlayer, float f, float f2, float f3)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.rotatePlayer(entityPlayer, f, f2, f3);
        }
        else
        {
            renderPlayer.localRotatePlayer(entityPlayer, f, f2, f3);
        }
    }

    private void rotatePlayer(EntityPlayer entityPlayer, float f, float f2, float f3)
    {
        int n;

        if (this.beforeRotatePlayerHooks != null)
        {
            for (n = this.beforeRotatePlayerHooks.length - 1; n >= 0; --n)
            {
                this.beforeRotatePlayerHooks[n].beforeRotatePlayer(entityPlayer, f, f2, f3);
            }
        }

        if (this.overrideRotatePlayerHooks != null)
        {
            this.overrideRotatePlayerHooks[this.overrideRotatePlayerHooks.length - 1].rotatePlayer(entityPlayer, f, f2, f3);
        }
        else
        {
            this.renderPlayer.localRotatePlayer(entityPlayer, f, f2, f3);
        }

        if (this.afterRotatePlayerHooks != null)
        {
            for (n = 0; n < this.afterRotatePlayerHooks.length; ++n)
            {
                this.afterRotatePlayerHooks[n].afterRotatePlayer(entityPlayer, f, f2, f3);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRotatePlayer(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRotatePlayerHooks.length; ++i)
        {
            if (this.overrideRotatePlayerHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRotatePlayerHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static int setArmorModel(RenderPlayer renderPlayer, EntityPlayer entityPlayer, int n, float f)
    {
        int n2 = renderPlayer.renderPlayerAPI != null ? renderPlayer.renderPlayerAPI.setArmorModel(entityPlayer, n, f) : renderPlayer.localSetArmorModel(entityPlayer, n, f);
        return n2;
    }

    private int setArmorModel(EntityPlayer entityPlayer, int n, float f)
    {
        int n2;

        if (this.beforeSetArmorModelHooks != null)
        {
            for (n2 = this.beforeSetArmorModelHooks.length - 1; n2 >= 0; --n2)
            {
                this.beforeSetArmorModelHooks[n2].beforeSetArmorModel(entityPlayer, n, f);
            }
        }

        n2 = this.overrideSetArmorModelHooks != null ? this.overrideSetArmorModelHooks[this.overrideSetArmorModelHooks.length - 1].setArmorModel(entityPlayer, n, f) : this.renderPlayer.localSetArmorModel(entityPlayer, n, f);

        if (this.afterSetArmorModelHooks != null)
        {
            for (int i = 0; i < this.afterSetArmorModelHooks.length; ++i)
            {
                this.afterSetArmorModelHooks[i].afterSetArmorModel(entityPlayer, n, f);
            }
        }

        return n2;
    }

    protected RenderPlayerBase GetOverwrittenSetArmorModel(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideSetArmorModelHooks.length; ++i)
        {
            if (this.overrideSetArmorModelHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideSetArmorModelHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void setIconRegister(RenderPlayer renderPlayer, IconRegister iconRegister)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.setIconRegister(iconRegister);
        }
        else
        {
            renderPlayer.localSetIconRegister(iconRegister);
        }
    }

    private void setIconRegister(IconRegister iconRegister)
    {
        int n;

        if (this.beforeSetIconRegisterHooks != null)
        {
            for (n = this.beforeSetIconRegisterHooks.length - 1; n >= 0; --n)
            {
                this.beforeSetIconRegisterHooks[n].beforeSetIconRegister(iconRegister);
            }
        }

        if (this.overrideSetIconRegisterHooks != null)
        {
            this.overrideSetIconRegisterHooks[this.overrideSetIconRegisterHooks.length - 1].setIconRegister(iconRegister);
        }
        else
        {
            this.renderPlayer.localSetIconRegister(iconRegister);
        }

        if (this.afterSetIconRegisterHooks != null)
        {
            for (n = 0; n < this.afterSetIconRegisterHooks.length; ++n)
            {
                this.afterSetIconRegisterHooks[n].afterSetIconRegister(iconRegister);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenSetIconRegister(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideSetIconRegisterHooks.length; ++i)
        {
            if (this.overrideSetIconRegisterHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideSetIconRegisterHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void setPassArmorModel(RenderPlayer renderPlayer, EntityPlayer entityPlayer, int n, float f)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.setPassArmorModel(entityPlayer, n, f);
        }
        else
        {
            renderPlayer.localSetPassArmorModel(entityPlayer, n, f);
        }
    }

    private void setPassArmorModel(EntityPlayer entityPlayer, int n, float f)
    {
        int n2;

        if (this.beforeSetPassArmorModelHooks != null)
        {
            for (n2 = this.beforeSetPassArmorModelHooks.length - 1; n2 >= 0; --n2)
            {
                this.beforeSetPassArmorModelHooks[n2].beforeSetPassArmorModel(entityPlayer, n, f);
            }
        }

        if (this.overrideSetPassArmorModelHooks != null)
        {
            this.overrideSetPassArmorModelHooks[this.overrideSetPassArmorModelHooks.length - 1].setPassArmorModel(entityPlayer, n, f);
        }
        else
        {
            this.renderPlayer.localSetPassArmorModel(entityPlayer, n, f);
        }

        if (this.afterSetPassArmorModelHooks != null)
        {
            for (n2 = 0; n2 < this.afterSetPassArmorModelHooks.length; ++n2)
            {
                this.afterSetPassArmorModelHooks[n2].afterSetPassArmorModel(entityPlayer, n, f);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenSetPassArmorModel(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideSetPassArmorModelHooks.length; ++i)
        {
            if (this.overrideSetPassArmorModelHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideSetPassArmorModelHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void setRenderManager(RenderPlayer renderPlayer, RenderManager renderManager)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.setRenderManager(renderManager);
        }
        else
        {
            renderPlayer.localSetRenderManager(renderManager);
        }
    }

    private void setRenderManager(RenderManager renderManager)
    {
        int n;

        if (this.beforeSetRenderManagerHooks != null)
        {
            for (n = this.beforeSetRenderManagerHooks.length - 1; n >= 0; --n)
            {
                this.beforeSetRenderManagerHooks[n].beforeSetRenderManager(renderManager);
            }
        }

        if (this.overrideSetRenderManagerHooks != null)
        {
            this.overrideSetRenderManagerHooks[this.overrideSetRenderManagerHooks.length - 1].setRenderManager(renderManager);
        }
        else
        {
            this.renderPlayer.localSetRenderManager(renderManager);
        }

        if (this.afterSetRenderManagerHooks != null)
        {
            for (n = 0; n < this.afterSetRenderManagerHooks.length; ++n)
            {
                this.afterSetRenderManagerHooks[n].afterSetRenderManager(renderManager);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenSetRenderManager(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideSetRenderManagerHooks.length; ++i)
        {
            if (this.overrideSetRenderManagerHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideSetRenderManagerHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void setRenderPassModel(RenderPlayer renderPlayer, ModelBase modelBase)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.setRenderPassModel(modelBase);
        }
        else
        {
            renderPlayer.localSetRenderPassModel(modelBase);
        }
    }

    private void setRenderPassModel(ModelBase modelBase)
    {
        int n;

        if (this.beforeSetRenderPassModelHooks != null)
        {
            for (n = this.beforeSetRenderPassModelHooks.length - 1; n >= 0; --n)
            {
                this.beforeSetRenderPassModelHooks[n].beforeSetRenderPassModel(modelBase);
            }
        }

        if (this.overrideSetRenderPassModelHooks != null)
        {
            this.overrideSetRenderPassModelHooks[this.overrideSetRenderPassModelHooks.length - 1].setRenderPassModel(modelBase);
        }
        else
        {
            this.renderPlayer.localSetRenderPassModel(modelBase);
        }

        if (this.afterSetRenderPassModelHooks != null)
        {
            for (n = 0; n < this.afterSetRenderPassModelHooks.length; ++n)
            {
                this.afterSetRenderPassModelHooks[n].afterSetRenderPassModel(modelBase);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenSetRenderPassModel(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideSetRenderPassModelHooks.length; ++i)
        {
            if (this.overrideSetRenderPassModelHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideSetRenderPassModelHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderSpecialHeadArmor(RenderPlayer renderPlayer, EntityPlayer entityPlayer, float f)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderSpecialHeadArmor(entityPlayer, f);
        }
        else
        {
            renderPlayer.localRenderSpecialHeadArmor(entityPlayer, f);
        }
    }

    private void renderSpecialHeadArmor(EntityPlayer entityPlayer, float f)
    {
        int n;

        if (this.beforeRenderSpecialHeadArmorHooks != null)
        {
            for (n = this.beforeRenderSpecialHeadArmorHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderSpecialHeadArmorHooks[n].beforeRenderSpecialHeadArmor(entityPlayer, f);
            }
        }

        if (this.overrideRenderSpecialHeadArmorHooks != null)
        {
            this.overrideRenderSpecialHeadArmorHooks[this.overrideRenderSpecialHeadArmorHooks.length - 1].renderSpecialHeadArmor(entityPlayer, f);
        }
        else
        {
            this.renderPlayer.localRenderSpecialHeadArmor(entityPlayer, f);
        }

        if (this.afterRenderSpecialHeadArmorHooks != null)
        {
            for (n = 0; n < this.afterRenderSpecialHeadArmorHooks.length; ++n)
            {
                this.afterRenderSpecialHeadArmorHooks[n].afterRenderSpecialHeadArmor(entityPlayer, f);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecialHeadArmor(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderSpecialHeadArmorHooks.length; ++i)
        {
            if (this.overrideRenderSpecialHeadArmorHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialHeadArmorHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderSpecialHeadEars(RenderPlayer renderPlayer, EntityPlayer entityPlayer, float f)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderSpecialHeadEars(entityPlayer, f);
        }
        else
        {
            renderPlayer.localRenderSpecialHeadEars(entityPlayer, f);
        }
    }

    private void renderSpecialHeadEars(EntityPlayer entityPlayer, float f)
    {
        int n;

        if (this.beforeRenderSpecialHeadEarsHooks != null)
        {
            for (n = this.beforeRenderSpecialHeadEarsHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderSpecialHeadEarsHooks[n].beforeRenderSpecialHeadEars(entityPlayer, f);
            }
        }

        if (this.overrideRenderSpecialHeadEarsHooks != null)
        {
            this.overrideRenderSpecialHeadEarsHooks[this.overrideRenderSpecialHeadEarsHooks.length - 1].renderSpecialHeadEars(entityPlayer, f);
        }
        else
        {
            this.renderPlayer.localRenderSpecialHeadEars(entityPlayer, f);
        }

        if (this.afterRenderSpecialHeadEarsHooks != null)
        {
            for (n = 0; n < this.afterRenderSpecialHeadEarsHooks.length; ++n)
            {
                this.afterRenderSpecialHeadEarsHooks[n].afterRenderSpecialHeadEars(entityPlayer, f);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecialHeadEars(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderSpecialHeadEarsHooks.length; ++i)
        {
            if (this.overrideRenderSpecialHeadEarsHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialHeadEarsHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderSpecialCloak(RenderPlayer renderPlayer, EntityPlayer entityPlayer, float f)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderSpecialCloak(entityPlayer, f);
        }
        else
        {
            renderPlayer.localRenderSpecialCloak(entityPlayer, f);
        }
    }

    private void renderSpecialCloak(EntityPlayer entityPlayer, float f)
    {
        int n;

        if (this.beforeRenderSpecialCloakHooks != null)
        {
            for (n = this.beforeRenderSpecialCloakHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderSpecialCloakHooks[n].beforeRenderSpecialCloak(entityPlayer, f);
            }
        }

        if (this.overrideRenderSpecialCloakHooks != null)
        {
            this.overrideRenderSpecialCloakHooks[this.overrideRenderSpecialCloakHooks.length - 1].renderSpecialCloak(entityPlayer, f);
        }
        else
        {
            this.renderPlayer.localRenderSpecialCloak(entityPlayer, f);
        }

        if (this.afterRenderSpecialCloakHooks != null)
        {
            for (n = 0; n < this.afterRenderSpecialCloakHooks.length; ++n)
            {
                this.afterRenderSpecialCloakHooks[n].afterRenderSpecialCloak(entityPlayer, f);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecialCloak(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderSpecialCloakHooks.length; ++i)
        {
            if (this.overrideRenderSpecialCloakHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialCloakHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void renderSpecialItemInHand(RenderPlayer renderPlayer, EntityPlayer entityPlayer, float f)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.renderSpecialItemInHand(entityPlayer, f);
        }
        else
        {
            renderPlayer.localRenderSpecialItemInHand(entityPlayer, f);
        }
    }

    private void renderSpecialItemInHand(EntityPlayer entityPlayer, float f)
    {
        int n;

        if (this.beforeRenderSpecialItemInHandHooks != null)
        {
            for (n = this.beforeRenderSpecialItemInHandHooks.length - 1; n >= 0; --n)
            {
                this.beforeRenderSpecialItemInHandHooks[n].beforeRenderSpecialItemInHand(entityPlayer, f);
            }
        }

        if (this.overrideRenderSpecialItemInHandHooks != null)
        {
            this.overrideRenderSpecialItemInHandHooks[this.overrideRenderSpecialItemInHandHooks.length - 1].renderSpecialItemInHand(entityPlayer, f);
        }
        else
        {
            this.renderPlayer.localRenderSpecialItemInHand(entityPlayer, f);
        }

        if (this.afterRenderSpecialItemInHandHooks != null)
        {
            for (n = 0; n < this.afterRenderSpecialItemInHandHooks.length; ++n)
            {
                this.afterRenderSpecialItemInHandHooks[n].afterRenderSpecialItemInHand(entityPlayer, f);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecialItemInHand(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overrideRenderSpecialItemInHandHooks.length; ++i)
        {
            if (this.overrideRenderSpecialItemInHandHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialItemInHandHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }

    public static void positionSpecialItemInHand(RenderPlayer renderPlayer, EntityPlayer entityPlayer, float f, EnumAction enumAction, ItemStack itemStack)
    {
        if (renderPlayer.renderPlayerAPI != null)
        {
            renderPlayer.renderPlayerAPI.positionSpecialItemInHand(entityPlayer, f, enumAction, itemStack);
        }
        else
        {
            renderPlayer.localPositionSpecialItemInHand(entityPlayer, f, enumAction, itemStack);
        }
    }

    private void positionSpecialItemInHand(EntityPlayer entityPlayer, float f, EnumAction enumAction, ItemStack itemStack)
    {
        int n;

        if (this.beforePositionSpecialItemInHandHooks != null)
        {
            for (n = this.beforePositionSpecialItemInHandHooks.length - 1; n >= 0; --n)
            {
                this.beforePositionSpecialItemInHandHooks[n].beforePositionSpecialItemInHand(entityPlayer, f, enumAction, itemStack);
            }
        }

        if (this.overridePositionSpecialItemInHandHooks != null)
        {
            this.overridePositionSpecialItemInHandHooks[this.overridePositionSpecialItemInHandHooks.length - 1].positionSpecialItemInHand(entityPlayer, f, enumAction, itemStack);
        }
        else
        {
            this.renderPlayer.localPositionSpecialItemInHand(entityPlayer, f, enumAction, itemStack);
        }

        if (this.afterPositionSpecialItemInHandHooks != null)
        {
            for (n = 0; n < this.afterPositionSpecialItemInHandHooks.length; ++n)
            {
                this.afterPositionSpecialItemInHandHooks[n].afterPositionSpecialItemInHand(entityPlayer, f, enumAction, itemStack);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenPositionSpecialItemInHand(RenderPlayerBase renderPlayerBase)
    {
        for (int i = 0; i < this.overridePositionSpecialItemInHandHooks.length; ++i)
        {
            if (this.overridePositionSpecialItemInHandHooks[i] == renderPlayerBase)
            {
                if (i == 0)
                {
                    return null;
                }

                return this.overridePositionSpecialItemInHandHooks[i - 1];
            }
        }

        return renderPlayerBase;
    }
}
