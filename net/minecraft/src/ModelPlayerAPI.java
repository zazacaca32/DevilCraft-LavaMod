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
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public final class ModelPlayerAPI
{
    private static final Class[] Class = new Class[] {ModelPlayerAPI.class};
    private static final Class[] Classes = new Class[] {ModelPlayerAPI.class, String.class};
    private static boolean isCreated;
    private static final Logger logger = Logger.getLogger("ModelPlayerAPI");
    private static ModelPlayer[] allInstances = new ModelPlayer[0];
    private static final Map EmptySortMap = Collections.unmodifiableMap(new HashMap());
    private static final Object[] initializer = new Object[] {null};
    private static final Object[] initializers = new Object[] {null, null};
    private static final List beforeGetRandomModelBoxHookTypes = new LinkedList();
    private static final List overrideGetRandomModelBoxHookTypes = new LinkedList();
    private static final List afterGetRandomModelBoxHookTypes = new LinkedList();
    private ModelPlayerBase[] beforeGetRandomModelBoxHooks;
    private ModelPlayerBase[] overrideGetRandomModelBoxHooks;
    private ModelPlayerBase[] afterGetRandomModelBoxHooks;
    public boolean isGetRandomModelBoxModded;
    private static final Map allBaseBeforeGetRandomModelBoxSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetRandomModelBoxInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetRandomModelBoxSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetRandomModelBoxInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetRandomModelBoxSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetRandomModelBoxInferiors = new Hashtable(0);
    private static final List beforeGetTextureOffsetHookTypes = new LinkedList();
    private static final List overrideGetTextureOffsetHookTypes = new LinkedList();
    private static final List afterGetTextureOffsetHookTypes = new LinkedList();
    private ModelPlayerBase[] beforeGetTextureOffsetHooks;
    private ModelPlayerBase[] overrideGetTextureOffsetHooks;
    private ModelPlayerBase[] afterGetTextureOffsetHooks;
    public boolean isGetTextureOffsetModded;
    private static final Map allBaseBeforeGetTextureOffsetSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetTextureOffsetInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetTextureOffsetSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetTextureOffsetInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetTextureOffsetSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetTextureOffsetInferiors = new Hashtable(0);
    private static final List beforeRenderHookTypes = new LinkedList();
    private static final List overrideRenderHookTypes = new LinkedList();
    private static final List afterRenderHookTypes = new LinkedList();
    private ModelPlayerBase[] beforeRenderHooks;
    private ModelPlayerBase[] overrideRenderHooks;
    private ModelPlayerBase[] afterRenderHooks;
    public boolean isRenderModded;
    private static final Map allBaseBeforeRenderSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderInferiors = new Hashtable(0);
    private static final List beforeRenderCloakHookTypes = new LinkedList();
    private static final List overrideRenderCloakHookTypes = new LinkedList();
    private static final List afterRenderCloakHookTypes = new LinkedList();
    private ModelPlayerBase[] beforeRenderCloakHooks;
    private ModelPlayerBase[] overrideRenderCloakHooks;
    private ModelPlayerBase[] afterRenderCloakHooks;
    public boolean isRenderCloakModded;
    private static final Map allBaseBeforeRenderCloakSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderCloakInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderCloakSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderCloakInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderCloakSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderCloakInferiors = new Hashtable(0);
    private static final List beforeRenderEarsHookTypes = new LinkedList();
    private static final List overrideRenderEarsHookTypes = new LinkedList();
    private static final List afterRenderEarsHookTypes = new LinkedList();
    private ModelPlayerBase[] beforeRenderEarsHooks;
    private ModelPlayerBase[] overrideRenderEarsHooks;
    private ModelPlayerBase[] afterRenderEarsHooks;
    public boolean isRenderEarsModded;
    private static final Map allBaseBeforeRenderEarsSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderEarsInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderEarsSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderEarsInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderEarsSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderEarsInferiors = new Hashtable(0);
    private static final List beforeSetLivingAnimationsHookTypes = new LinkedList();
    private static final List overrideSetLivingAnimationsHookTypes = new LinkedList();
    private static final List afterSetLivingAnimationsHookTypes = new LinkedList();
    private ModelPlayerBase[] beforeSetLivingAnimationsHooks;
    private ModelPlayerBase[] overrideSetLivingAnimationsHooks;
    private ModelPlayerBase[] afterSetLivingAnimationsHooks;
    public boolean isSetLivingAnimationsModded;
    private static final Map allBaseBeforeSetLivingAnimationsSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetLivingAnimationsInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetLivingAnimationsSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetLivingAnimationsInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetLivingAnimationsSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetLivingAnimationsInferiors = new Hashtable(0);
    private static final List beforeSetRotationAnglesHookTypes = new LinkedList();
    private static final List overrideSetRotationAnglesHookTypes = new LinkedList();
    private static final List afterSetRotationAnglesHookTypes = new LinkedList();
    private ModelPlayerBase[] beforeSetRotationAnglesHooks;
    private ModelPlayerBase[] overrideSetRotationAnglesHooks;
    private ModelPlayerBase[] afterSetRotationAnglesHooks;
    public boolean isSetRotationAnglesModded;
    private static final Map allBaseBeforeSetRotationAnglesSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetRotationAnglesInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRotationAnglesSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRotationAnglesInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetRotationAnglesSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetRotationAnglesInferiors = new Hashtable(0);
    private static final List beforeSetTextureOffsetHookTypes = new LinkedList();
    private static final List overrideSetTextureOffsetHookTypes = new LinkedList();
    private static final List afterSetTextureOffsetHookTypes = new LinkedList();
    private ModelPlayerBase[] beforeSetTextureOffsetHooks;
    private ModelPlayerBase[] overrideSetTextureOffsetHooks;
    private ModelPlayerBase[] afterSetTextureOffsetHooks;
    public boolean isSetTextureOffsetModded;
    private static final Map allBaseBeforeSetTextureOffsetSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetTextureOffsetInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetTextureOffsetSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetTextureOffsetInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetTextureOffsetSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetTextureOffsetInferiors = new Hashtable(0);
    protected final ModelPlayer modelPlayer;
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
    private ModelPlayerBase[] beforeLocalConstructingHooks;
    private ModelPlayerBase[] afterLocalConstructingHooks;
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

    private static void log(String var0)
    {
        System.out.println(var0);
        logger.fine(var0);
    }

    private static String error(String var0)
    {
        logger.severe(var0);
        return var0;
    }

    public static void register(String var0, Class var1)
    {
        register(var0, var1, (ModelPlayerBaseSorting)null);
    }

    public static void register(String var0, Class var1, ModelPlayerBaseSorting var2)
    {
        try
        {
            register(var1, var0, var2);
        }
        catch (RuntimeException var4)
        {
            if (var0 != null)
            {
                log("Model Player API: failed to register id \'" + var0 + "\'");
            }
            else
            {
                log("Model Player API: failed to register ModelPlayerBase");
            }

            throw var4;
        }
    }

    private static void register(Class var0, String var1, ModelPlayerBaseSorting var2)
    {
        int var6;
        int var7;

        if (!isCreated)
        {
            try
            {
                Method var13 = ModelPlayer.class.getMethod("getModelPlayerBase", new Class[] {String.class});

                if (var13.getReturnType() != ModelPlayerBase.class)
                {
                    throw new NoSuchMethodException(ModelPlayerBase.class.getName() + " " + ModelPlayer.class.getName() + ".getModelPlayerBase(" + String.class.getName() + ")");
                }
            }
            catch (NoSuchMethodException var151)
            {
                String[] var14 = new String[] {"========================================", "Model Player API 1.3 can not be created!", "----------------------------------------", "Mandatory member method \"" + ModelPlayerBase.class.getName() + " getModelPlayerBase(" + String.class.getName() + ")\" not found in class \"" + ModelPlayer.class.getName() + "\".", "There are two scenarios this can happen:", "* The file \"" + ModelPlayer.class.getName().replace(".", File.separator) + ".class\" of Render Player API has been replaced by a file of the same name but a different source.", "  Install Render Player API again to fix this specific problem.", "* Render Player API has been installed in the \"mods\" folder.", "  Deinstall Render Player API and install it again following the installation instructions in the readme file.", "========================================"};
                String[] var16 = var14;
                var6 = var14.length;
                String var19;

                for (var7 = 0; var7 < var6; ++var7)
                {
                    var19 = var16[var7];
                    logger.severe(var19);
                }

                var16 = var14;
                var6 = var14.length;

                for (var7 = 0; var7 < var6; ++var7)
                {
                    var19 = var16[var7];
                    System.err.println(var19);
                }

                String var15 = "\n\n";
                String[] var17 = var14;
                var7 = var14.length;

                for (int var18 = 0; var18 < var7; ++var18)
                {
                    String var9 = var17[var18];
                    var15 = var15 + "\t" + var9 + "\n";
                }

                throw new RuntimeException(var15, var151);
            }

            log("Model Player API 1.3 Created");
            isCreated = true;
        }

        if (var1 == null)
        {
            throw new NullPointerException("Argument \'id\' can not be null");
        }
        else if (var0 == null)
        {
            throw new NullPointerException("Argument \'baseClass\' can not be null");
        }
        else
        {
            Constructor var161 = (Constructor)allBaseConstructors.get(var1);

            if (var161 != null)
            {
                throw new IllegalArgumentException("The class \'" + var0.getName() + "\' can not be registered with the id \'" + var1 + "\' because the class \'" + var161.getDeclaringClass().getName() + "\' has allready been registered with the same id");
            }
            else
            {
                Constructor var171;

                try
                {
                    var171 = var0.getDeclaredConstructor(Classes);
                }
                catch (Throwable var141)
                {
                    try
                    {
                        var171 = var0.getDeclaredConstructor(Class);
                    }
                    catch (Throwable var131)
                    {
                        throw new IllegalArgumentException("Can not find necessary constructor with one argument of type \'" + ModelPlayerAPI.class.getName() + "\' and eventually a second argument of type \'String\' in the class \'" + var0.getName() + "\'", var141);
                    }
                }

                allBaseConstructors.put(var1, var171);

                if (var2 != null)
                {
                    addSorting(var1, allBaseBeforeLocalConstructingSuperiors, var2.getBeforeLocalConstructingSuperiors());
                    addSorting(var1, allBaseBeforeLocalConstructingInferiors, var2.getBeforeLocalConstructingInferiors());
                    addSorting(var1, allBaseAfterLocalConstructingSuperiors, var2.getAfterLocalConstructingSuperiors());
                    addSorting(var1, allBaseAfterLocalConstructingInferiors, var2.getAfterLocalConstructingInferiors());
                    addDynamicSorting(var1, allBaseBeforeDynamicSuperiors, var2.getDynamicBeforeSuperiors());
                    addDynamicSorting(var1, allBaseBeforeDynamicInferiors, var2.getDynamicBeforeInferiors());
                    addDynamicSorting(var1, allBaseOverrideDynamicSuperiors, var2.getDynamicOverrideSuperiors());
                    addDynamicSorting(var1, allBaseOverrideDynamicInferiors, var2.getDynamicOverrideInferiors());
                    addDynamicSorting(var1, allBaseAfterDynamicSuperiors, var2.getDynamicAfterSuperiors());
                    addDynamicSorting(var1, allBaseAfterDynamicInferiors, var2.getDynamicAfterInferiors());
                    addSorting(var1, allBaseBeforeGetRandomModelBoxSuperiors, var2.getBeforeGetRandomModelBoxSuperiors());
                    addSorting(var1, allBaseBeforeGetRandomModelBoxInferiors, var2.getBeforeGetRandomModelBoxInferiors());
                    addSorting(var1, allBaseOverrideGetRandomModelBoxSuperiors, var2.getOverrideGetRandomModelBoxSuperiors());
                    addSorting(var1, allBaseOverrideGetRandomModelBoxInferiors, var2.getOverrideGetRandomModelBoxInferiors());
                    addSorting(var1, allBaseAfterGetRandomModelBoxSuperiors, var2.getAfterGetRandomModelBoxSuperiors());
                    addSorting(var1, allBaseAfterGetRandomModelBoxInferiors, var2.getAfterGetRandomModelBoxInferiors());
                    addSorting(var1, allBaseBeforeGetTextureOffsetSuperiors, var2.getBeforeGetTextureOffsetSuperiors());
                    addSorting(var1, allBaseBeforeGetTextureOffsetInferiors, var2.getBeforeGetTextureOffsetInferiors());
                    addSorting(var1, allBaseOverrideGetTextureOffsetSuperiors, var2.getOverrideGetTextureOffsetSuperiors());
                    addSorting(var1, allBaseOverrideGetTextureOffsetInferiors, var2.getOverrideGetTextureOffsetInferiors());
                    addSorting(var1, allBaseAfterGetTextureOffsetSuperiors, var2.getAfterGetTextureOffsetSuperiors());
                    addSorting(var1, allBaseAfterGetTextureOffsetInferiors, var2.getAfterGetTextureOffsetInferiors());
                    addSorting(var1, allBaseBeforeRenderSuperiors, var2.getBeforeRenderSuperiors());
                    addSorting(var1, allBaseBeforeRenderInferiors, var2.getBeforeRenderInferiors());
                    addSorting(var1, allBaseOverrideRenderSuperiors, var2.getOverrideRenderSuperiors());
                    addSorting(var1, allBaseOverrideRenderInferiors, var2.getOverrideRenderInferiors());
                    addSorting(var1, allBaseAfterRenderSuperiors, var2.getAfterRenderSuperiors());
                    addSorting(var1, allBaseAfterRenderInferiors, var2.getAfterRenderInferiors());
                    addSorting(var1, allBaseBeforeRenderCloakSuperiors, var2.getBeforeRenderCloakSuperiors());
                    addSorting(var1, allBaseBeforeRenderCloakInferiors, var2.getBeforeRenderCloakInferiors());
                    addSorting(var1, allBaseOverrideRenderCloakSuperiors, var2.getOverrideRenderCloakSuperiors());
                    addSorting(var1, allBaseOverrideRenderCloakInferiors, var2.getOverrideRenderCloakInferiors());
                    addSorting(var1, allBaseAfterRenderCloakSuperiors, var2.getAfterRenderCloakSuperiors());
                    addSorting(var1, allBaseAfterRenderCloakInferiors, var2.getAfterRenderCloakInferiors());
                    addSorting(var1, allBaseBeforeRenderEarsSuperiors, var2.getBeforeRenderEarsSuperiors());
                    addSorting(var1, allBaseBeforeRenderEarsInferiors, var2.getBeforeRenderEarsInferiors());
                    addSorting(var1, allBaseOverrideRenderEarsSuperiors, var2.getOverrideRenderEarsSuperiors());
                    addSorting(var1, allBaseOverrideRenderEarsInferiors, var2.getOverrideRenderEarsInferiors());
                    addSorting(var1, allBaseAfterRenderEarsSuperiors, var2.getAfterRenderEarsSuperiors());
                    addSorting(var1, allBaseAfterRenderEarsInferiors, var2.getAfterRenderEarsInferiors());
                    addSorting(var1, allBaseBeforeSetLivingAnimationsSuperiors, var2.getBeforeSetLivingAnimationsSuperiors());
                    addSorting(var1, allBaseBeforeSetLivingAnimationsInferiors, var2.getBeforeSetLivingAnimationsInferiors());
                    addSorting(var1, allBaseOverrideSetLivingAnimationsSuperiors, var2.getOverrideSetLivingAnimationsSuperiors());
                    addSorting(var1, allBaseOverrideSetLivingAnimationsInferiors, var2.getOverrideSetLivingAnimationsInferiors());
                    addSorting(var1, allBaseAfterSetLivingAnimationsSuperiors, var2.getAfterSetLivingAnimationsSuperiors());
                    addSorting(var1, allBaseAfterSetLivingAnimationsInferiors, var2.getAfterSetLivingAnimationsInferiors());
                    addSorting(var1, allBaseBeforeSetRotationAnglesSuperiors, var2.getBeforeSetRotationAnglesSuperiors());
                    addSorting(var1, allBaseBeforeSetRotationAnglesInferiors, var2.getBeforeSetRotationAnglesInferiors());
                    addSorting(var1, allBaseOverrideSetRotationAnglesSuperiors, var2.getOverrideSetRotationAnglesSuperiors());
                    addSorting(var1, allBaseOverrideSetRotationAnglesInferiors, var2.getOverrideSetRotationAnglesInferiors());
                    addSorting(var1, allBaseAfterSetRotationAnglesSuperiors, var2.getAfterSetRotationAnglesSuperiors());
                    addSorting(var1, allBaseAfterSetRotationAnglesInferiors, var2.getAfterSetRotationAnglesInferiors());
                    addSorting(var1, allBaseBeforeSetTextureOffsetSuperiors, var2.getBeforeSetTextureOffsetSuperiors());
                    addSorting(var1, allBaseBeforeSetTextureOffsetInferiors, var2.getBeforeSetTextureOffsetInferiors());
                    addSorting(var1, allBaseOverrideSetTextureOffsetSuperiors, var2.getOverrideSetTextureOffsetSuperiors());
                    addSorting(var1, allBaseOverrideSetTextureOffsetInferiors, var2.getOverrideSetTextureOffsetInferiors());
                    addSorting(var1, allBaseAfterSetTextureOffsetSuperiors, var2.getAfterSetTextureOffsetSuperiors());
                    addSorting(var1, allBaseAfterSetTextureOffsetInferiors, var2.getAfterSetTextureOffsetInferiors());
                }

                addMethod(var1, var0, beforeLocalConstructingHookTypes, "beforeLocalConstructing", new Class[] {Float.TYPE});
                addMethod(var1, var0, afterLocalConstructingHookTypes, "afterLocalConstructing", new Class[] {Float.TYPE});
                addMethod(var1, var0, beforeGetRandomModelBoxHookTypes, "beforeGetRandomModelBox", new Class[] {Random.class});
                addMethod(var1, var0, overrideGetRandomModelBoxHookTypes, "getRandomModelBox", new Class[] {Random.class});
                addMethod(var1, var0, afterGetRandomModelBoxHookTypes, "afterGetRandomModelBox", new Class[] {Random.class});
                addMethod(var1, var0, beforeGetTextureOffsetHookTypes, "beforeGetTextureOffset", new Class[] {String.class});
                addMethod(var1, var0, overrideGetTextureOffsetHookTypes, "getTextureOffset", new Class[] {String.class});
                addMethod(var1, var0, afterGetTextureOffsetHookTypes, "afterGetTextureOffset", new Class[] {String.class});
                addMethod(var1, var0, beforeRenderHookTypes, "beforeRender", new Class[] {Entity.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideRenderHookTypes, "render", new Class[] {Entity.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterRenderHookTypes, "afterRender", new Class[] {Entity.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeRenderCloakHookTypes, "beforeRenderCloak", new Class[] {Float.TYPE});
                addMethod(var1, var0, overrideRenderCloakHookTypes, "renderCloak", new Class[] {Float.TYPE});
                addMethod(var1, var0, afterRenderCloakHookTypes, "afterRenderCloak", new Class[] {Float.TYPE});
                addMethod(var1, var0, beforeRenderEarsHookTypes, "beforeRenderEars", new Class[] {Float.TYPE});
                addMethod(var1, var0, overrideRenderEarsHookTypes, "renderEars", new Class[] {Float.TYPE});
                addMethod(var1, var0, afterRenderEarsHookTypes, "afterRenderEars", new Class[] {Float.TYPE});
                addMethod(var1, var0, beforeSetLivingAnimationsHookTypes, "beforeSetLivingAnimations", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideSetLivingAnimationsHookTypes, "setLivingAnimations", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterSetLivingAnimationsHookTypes, "afterSetLivingAnimations", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeSetRotationAnglesHookTypes, "beforeSetRotationAngles", new Class[] {Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Entity.class});
                addMethod(var1, var0, overrideSetRotationAnglesHookTypes, "setRotationAngles", new Class[] {Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Entity.class});
                addMethod(var1, var0, afterSetRotationAnglesHookTypes, "afterSetRotationAngles", new Class[] {Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Entity.class});
                addMethod(var1, var0, beforeSetTextureOffsetHookTypes, "beforeSetTextureOffset", new Class[] {String.class, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, overrideSetTextureOffsetHookTypes, "setTextureOffset", new Class[] {String.class, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, afterSetTextureOffsetHookTypes, "afterSetTextureOffset", new Class[] {String.class, Integer.TYPE, Integer.TYPE});
                addDynamicMethods(var1, var0);
                addDynamicKeys(var1, var0, beforeDynamicHookMethods, beforeDynamicHookTypes);
                addDynamicKeys(var1, var0, overrideDynamicHookMethods, overrideDynamicHookTypes);
                addDynamicKeys(var1, var0, afterDynamicHookMethods, afterDynamicHookTypes);
                initialize();
                ModelPlayer[] var181 = getAllInstances();
                var6 = var181.length;

                for (var7 = 0; var7 < var6; ++var7)
                {
                    ModelPlayer var191 = var181[var7];
                    var191.modelPlayerAPI.attachModelPlayerBase(var1);
                }

                System.out.println("Model Player API: registered " + var1);
                logger.fine("Model Player API: registered class \'" + var0.getName() + "\' with id \'" + var1 + "\'");
                initialized = false;
            }
        }
    }

    public static boolean unregister(String var0)
    {
        if (var0 == null)
        {
            return false;
        }
        else
        {
            Constructor var1 = (Constructor)allBaseConstructors.remove(var0);

            if (var1 == null)
            {
                return false;
            }
            else
            {
                ModelPlayer[] var2 = getAllInstances();
                int var3 = var2.length;

                for (int var7 = 0; var7 < var3; ++var7)
                {
                    ModelPlayer var9 = var2[var7];
                    var9.modelPlayerAPI.detachModelPlayerBase(var0);
                }

                beforeLocalConstructingHookTypes.remove(var0);
                afterLocalConstructingHookTypes.remove(var0);
                allBaseBeforeGetRandomModelBoxSuperiors.remove(var0);
                allBaseBeforeGetRandomModelBoxInferiors.remove(var0);
                allBaseOverrideGetRandomModelBoxSuperiors.remove(var0);
                allBaseOverrideGetRandomModelBoxInferiors.remove(var0);
                allBaseAfterGetRandomModelBoxSuperiors.remove(var0);
                allBaseAfterGetRandomModelBoxInferiors.remove(var0);
                beforeGetRandomModelBoxHookTypes.remove(var0);
                overrideGetRandomModelBoxHookTypes.remove(var0);
                afterGetRandomModelBoxHookTypes.remove(var0);
                allBaseBeforeGetTextureOffsetSuperiors.remove(var0);
                allBaseBeforeGetTextureOffsetInferiors.remove(var0);
                allBaseOverrideGetTextureOffsetSuperiors.remove(var0);
                allBaseOverrideGetTextureOffsetInferiors.remove(var0);
                allBaseAfterGetTextureOffsetSuperiors.remove(var0);
                allBaseAfterGetTextureOffsetInferiors.remove(var0);
                beforeGetTextureOffsetHookTypes.remove(var0);
                overrideGetTextureOffsetHookTypes.remove(var0);
                afterGetTextureOffsetHookTypes.remove(var0);
                allBaseBeforeRenderSuperiors.remove(var0);
                allBaseBeforeRenderInferiors.remove(var0);
                allBaseOverrideRenderSuperiors.remove(var0);
                allBaseOverrideRenderInferiors.remove(var0);
                allBaseAfterRenderSuperiors.remove(var0);
                allBaseAfterRenderInferiors.remove(var0);
                beforeRenderHookTypes.remove(var0);
                overrideRenderHookTypes.remove(var0);
                afterRenderHookTypes.remove(var0);
                allBaseBeforeRenderCloakSuperiors.remove(var0);
                allBaseBeforeRenderCloakInferiors.remove(var0);
                allBaseOverrideRenderCloakSuperiors.remove(var0);
                allBaseOverrideRenderCloakInferiors.remove(var0);
                allBaseAfterRenderCloakSuperiors.remove(var0);
                allBaseAfterRenderCloakInferiors.remove(var0);
                beforeRenderCloakHookTypes.remove(var0);
                overrideRenderCloakHookTypes.remove(var0);
                afterRenderCloakHookTypes.remove(var0);
                allBaseBeforeRenderEarsSuperiors.remove(var0);
                allBaseBeforeRenderEarsInferiors.remove(var0);
                allBaseOverrideRenderEarsSuperiors.remove(var0);
                allBaseOverrideRenderEarsInferiors.remove(var0);
                allBaseAfterRenderEarsSuperiors.remove(var0);
                allBaseAfterRenderEarsInferiors.remove(var0);
                beforeRenderEarsHookTypes.remove(var0);
                overrideRenderEarsHookTypes.remove(var0);
                afterRenderEarsHookTypes.remove(var0);
                allBaseBeforeSetLivingAnimationsSuperiors.remove(var0);
                allBaseBeforeSetLivingAnimationsInferiors.remove(var0);
                allBaseOverrideSetLivingAnimationsSuperiors.remove(var0);
                allBaseOverrideSetLivingAnimationsInferiors.remove(var0);
                allBaseAfterSetLivingAnimationsSuperiors.remove(var0);
                allBaseAfterSetLivingAnimationsInferiors.remove(var0);
                beforeSetLivingAnimationsHookTypes.remove(var0);
                overrideSetLivingAnimationsHookTypes.remove(var0);
                afterSetLivingAnimationsHookTypes.remove(var0);
                allBaseBeforeSetRotationAnglesSuperiors.remove(var0);
                allBaseBeforeSetRotationAnglesInferiors.remove(var0);
                allBaseOverrideSetRotationAnglesSuperiors.remove(var0);
                allBaseOverrideSetRotationAnglesInferiors.remove(var0);
                allBaseAfterSetRotationAnglesSuperiors.remove(var0);
                allBaseAfterSetRotationAnglesInferiors.remove(var0);
                beforeSetRotationAnglesHookTypes.remove(var0);
                overrideSetRotationAnglesHookTypes.remove(var0);
                afterSetRotationAnglesHookTypes.remove(var0);
                allBaseBeforeSetTextureOffsetSuperiors.remove(var0);
                allBaseBeforeSetTextureOffsetInferiors.remove(var0);
                allBaseOverrideSetTextureOffsetSuperiors.remove(var0);
                allBaseOverrideSetTextureOffsetInferiors.remove(var0);
                allBaseAfterSetTextureOffsetSuperiors.remove(var0);
                allBaseAfterSetTextureOffsetInferiors.remove(var0);
                beforeSetTextureOffsetHookTypes.remove(var0);
                overrideSetTextureOffsetHookTypes.remove(var0);
                afterSetTextureOffsetHookTypes.remove(var0);
                Iterator var91 = keysToVirtualIds.keySet().iterator();

                while (var91.hasNext())
                {
                    String var101 = (String)var91.next();

                    if (((String)keysToVirtualIds.get(var101)).equals(var0))
                    {
                        keysToVirtualIds.remove(var101);
                    }
                }

                boolean var111 = false;
                Class var10 = var1.getDeclaringClass();
                var91 = allBaseConstructors.keySet().iterator();

                while (var91.hasNext())
                {
                    String var11 = (String)var91.next();
                    Class var6 = ((Constructor)allBaseConstructors.get(var11)).getDeclaringClass();

                    if (!var11.equals(var0) && var6.equals(var10))
                    {
                        var111 = true;
                        break;
                    }
                }

                if (!var111)
                {
                    dynamicTypes.remove(var10);
                    virtualDynamicHookMethods.remove(var10);
                    beforeDynamicHookMethods.remove(var10);
                    overrideDynamicHookMethods.remove(var10);
                    afterDynamicHookMethods.remove(var10);
                }

                removeDynamicHookTypes(var0, beforeDynamicHookTypes);
                removeDynamicHookTypes(var0, overrideDynamicHookTypes);
                removeDynamicHookTypes(var0, afterDynamicHookTypes);
                allBaseBeforeDynamicSuperiors.remove(var0);
                allBaseBeforeDynamicInferiors.remove(var0);
                allBaseOverrideDynamicSuperiors.remove(var0);
                allBaseOverrideDynamicInferiors.remove(var0);
                allBaseAfterDynamicSuperiors.remove(var0);
                allBaseAfterDynamicInferiors.remove(var0);
                log("ModelPlayerAPI: unregistered id \'" + var0 + "\'");
                return true;
            }
        }
    }

    public static void removeDynamicHookTypes(String var0, Map var1)
    {
        Iterator var2 = var1.keySet().iterator();

        while (var2.hasNext())
        {
            ((List)var1.get(var2.next())).remove(var0);
        }
    }

    public static Set getRegisteredIds()
    {
        return unmodifiableAllIds;
    }

    private static void addSorting(String var0, Map var1, String[] var2)
    {
        if (var2 != null && var2.length > 0)
        {
            var1.put(var0, var2);
        }
    }

    private static void addDynamicSorting(String var0, Map var1, Map var2)
    {
        if (var2 != null && var2.size() > 0)
        {
            var1.put(var0, var2);
        }
    }

    private static boolean addMethod(String var0, Class var1, List var2, String var3, Class ... var4)
    {
        try
        {
            Method var7 = var1.getMethod(var3, var4);
            boolean var6 = var7.getDeclaringClass() != ModelPlayerBase.class;

            if (var6)
            {
                var2.add(var0);
            }

            return var6;
        }
        catch (Exception var71)
        {
            throw new RuntimeException("Can not reflect method \'" + var3 + "\' of class \'" + var1.getName() + "\'", var71);
        }
    }

    private static void addDynamicMethods(String var0, Class var1)
    {
        if (dynamicTypes.add(var1))
        {
            Map var2 = null;
            Map var3 = null;
            Map var4 = null;
            Map var5 = null;
            Method[] var6 = var1.getDeclaredMethods();

            for (int var7 = 0; var7 < var6.length; ++var7)
            {
                Method var8 = var6[var7];

                if (var8.getDeclaringClass() == var1)
                {
                    int var9 = var8.getModifiers();

                    if (!Modifier.isAbstract(var9) && !Modifier.isStatic(var9))
                    {
                        String var10 = var8.getName();

                        if (var10.length() >= 7 && var10.substring(0, 7).equalsIgnoreCase("dynamic"))
                        {
                            for (var10 = var10.substring(7); var10.charAt(0) == 95; var10 = var10.substring(1))
                            {
                                ;
                            }

                            boolean var11 = false;
                            boolean var12 = false;
                            boolean var13 = false;
                            boolean var14 = false;

                            if (var10.substring(0, 7).equalsIgnoreCase("virtual"))
                            {
                                var12 = true;
                                var10 = var10.substring(7);
                            }
                            else if (var10.length() >= 8 && var10.substring(0, 8).equalsIgnoreCase("override"))
                            {
                                var10 = var10.substring(8);
                                var13 = true;
                            }
                            else if (var10.length() >= 6 && var10.substring(0, 6).equalsIgnoreCase("before"))
                            {
                                var11 = true;
                                var10 = var10.substring(6);
                            }
                            else if (var10.length() >= 5 && var10.substring(0, 5).equalsIgnoreCase("after"))
                            {
                                var14 = true;
                                var10 = var10.substring(5);
                            }

                            if (var10.length() >= 1 && (var11 || var12 || var13 || var14))
                            {
                                var10 = var10.substring(0, 1).toLowerCase() + var10.substring(1);
                            }

                            while (var10.charAt(0) == 95)
                            {
                                var10 = var10.substring(1);
                            }

                            if (var10.length() == 0)
                            {
                                throw new RuntimeException("Can not process dynamic hook method with no key");
                            }

                            keys.add(var10);

                            if (var12)
                            {
                                if (keysToVirtualIds.containsKey(var10))
                                {
                                    throw new RuntimeException("Can not process more than one dynamic virtual method");
                                }

                                keysToVirtualIds.put(var10, var0);
                                var2 = addDynamicMethod(var10, var8, var2);
                            }
                            else if (var11)
                            {
                                var3 = addDynamicMethod(var10, var8, var3);
                            }
                            else if (var14)
                            {
                                var5 = addDynamicMethod(var10, var8, var5);
                            }
                            else
                            {
                                var4 = addDynamicMethod(var10, var8, var4);
                            }
                        }
                    }
                }
            }

            if (var2 != null)
            {
                virtualDynamicHookMethods.put(var1, var2);
            }

            if (var3 != null)
            {
                beforeDynamicHookMethods.put(var1, var3);
            }

            if (var4 != null)
            {
                overrideDynamicHookMethods.put(var1, var4);
            }

            if (var5 != null)
            {
                afterDynamicHookMethods.put(var1, var5);
            }
        }
    }

    private static void addDynamicKeys(String var0, Class var1, Map var2, Map var3)
    {
        Map var4 = (Map)var2.get(var1);
        String var6;

        if (var4 != null && var4.size() != 0)
        {
            for (Iterator var5 = var4.keySet().iterator(); var5.hasNext(); ((List)var3.get(var6)).add(var0))
            {
                var6 = (String)var5.next();

                if (!var3.containsKey(var6))
                {
                    var3.put(var6, new ArrayList(1));
                }
            }
        }
    }

    private static Map addDynamicMethod(String var0, Method var1, Map var2)
    {
        if (var2 == null)
        {
            var2 = new HashMap();
        }

        if (((Map)var2).containsKey(var0))
        {
            throw new RuntimeException("method with key \'" + var0 + "\' allready exists");
        }
        else
        {
            ((Map)var2).put(var0, var1);
            return (Map)var2;
        }
    }

    public static ModelPlayerAPI create(ModelPlayer var0)
    {
        if (allBaseConstructors.size() > 0 && !initialized)
        {
            initialize();
        }

        return new ModelPlayerAPI(var0);
    }

    private static void initialize()
    {
        sortBases(beforeLocalConstructingHookTypes, allBaseBeforeLocalConstructingSuperiors, allBaseBeforeLocalConstructingInferiors, "beforeLocalConstructing");
        sortBases(afterLocalConstructingHookTypes, allBaseAfterLocalConstructingSuperiors, allBaseAfterLocalConstructingInferiors, "afterLocalConstructing");
        Iterator var0 = keys.iterator();

        while (var0.hasNext())
        {
            String var1 = (String)var0.next();
            sortDynamicBases(beforeDynamicHookTypes, allBaseBeforeDynamicSuperiors, allBaseBeforeDynamicInferiors, var1);
            sortDynamicBases(overrideDynamicHookTypes, allBaseOverrideDynamicSuperiors, allBaseOverrideDynamicInferiors, var1);
            sortDynamicBases(afterDynamicHookTypes, allBaseAfterDynamicSuperiors, allBaseAfterDynamicInferiors, var1);
        }

        sortBases(beforeGetRandomModelBoxHookTypes, allBaseBeforeGetRandomModelBoxSuperiors, allBaseBeforeGetRandomModelBoxInferiors, "beforeGetRandomModelBox");
        sortBases(overrideGetRandomModelBoxHookTypes, allBaseOverrideGetRandomModelBoxSuperiors, allBaseOverrideGetRandomModelBoxInferiors, "overrideGetRandomModelBox");
        sortBases(afterGetRandomModelBoxHookTypes, allBaseAfterGetRandomModelBoxSuperiors, allBaseAfterGetRandomModelBoxInferiors, "afterGetRandomModelBox");
        sortBases(beforeGetTextureOffsetHookTypes, allBaseBeforeGetTextureOffsetSuperiors, allBaseBeforeGetTextureOffsetInferiors, "beforeGetTextureOffset");
        sortBases(overrideGetTextureOffsetHookTypes, allBaseOverrideGetTextureOffsetSuperiors, allBaseOverrideGetTextureOffsetInferiors, "overrideGetTextureOffset");
        sortBases(afterGetTextureOffsetHookTypes, allBaseAfterGetTextureOffsetSuperiors, allBaseAfterGetTextureOffsetInferiors, "afterGetTextureOffset");
        sortBases(beforeRenderHookTypes, allBaseBeforeRenderSuperiors, allBaseBeforeRenderInferiors, "beforeRender");
        sortBases(overrideRenderHookTypes, allBaseOverrideRenderSuperiors, allBaseOverrideRenderInferiors, "overrideRender");
        sortBases(afterRenderHookTypes, allBaseAfterRenderSuperiors, allBaseAfterRenderInferiors, "afterRender");
        sortBases(beforeRenderCloakHookTypes, allBaseBeforeRenderCloakSuperiors, allBaseBeforeRenderCloakInferiors, "beforeRenderCloak");
        sortBases(overrideRenderCloakHookTypes, allBaseOverrideRenderCloakSuperiors, allBaseOverrideRenderCloakInferiors, "overrideRenderCloak");
        sortBases(afterRenderCloakHookTypes, allBaseAfterRenderCloakSuperiors, allBaseAfterRenderCloakInferiors, "afterRenderCloak");
        sortBases(beforeRenderEarsHookTypes, allBaseBeforeRenderEarsSuperiors, allBaseBeforeRenderEarsInferiors, "beforeRenderEars");
        sortBases(overrideRenderEarsHookTypes, allBaseOverrideRenderEarsSuperiors, allBaseOverrideRenderEarsInferiors, "overrideRenderEars");
        sortBases(afterRenderEarsHookTypes, allBaseAfterRenderEarsSuperiors, allBaseAfterRenderEarsInferiors, "afterRenderEars");
        sortBases(beforeSetLivingAnimationsHookTypes, allBaseBeforeSetLivingAnimationsSuperiors, allBaseBeforeSetLivingAnimationsInferiors, "beforeSetLivingAnimations");
        sortBases(overrideSetLivingAnimationsHookTypes, allBaseOverrideSetLivingAnimationsSuperiors, allBaseOverrideSetLivingAnimationsInferiors, "overrideSetLivingAnimations");
        sortBases(afterSetLivingAnimationsHookTypes, allBaseAfterSetLivingAnimationsSuperiors, allBaseAfterSetLivingAnimationsInferiors, "afterSetLivingAnimations");
        sortBases(beforeSetRotationAnglesHookTypes, allBaseBeforeSetRotationAnglesSuperiors, allBaseBeforeSetRotationAnglesInferiors, "beforeSetRotationAngles");
        sortBases(overrideSetRotationAnglesHookTypes, allBaseOverrideSetRotationAnglesSuperiors, allBaseOverrideSetRotationAnglesInferiors, "overrideSetRotationAngles");
        sortBases(afterSetRotationAnglesHookTypes, allBaseAfterSetRotationAnglesSuperiors, allBaseAfterSetRotationAnglesInferiors, "afterSetRotationAngles");
        sortBases(beforeSetTextureOffsetHookTypes, allBaseBeforeSetTextureOffsetSuperiors, allBaseBeforeSetTextureOffsetInferiors, "beforeSetTextureOffset");
        sortBases(overrideSetTextureOffsetHookTypes, allBaseOverrideSetTextureOffsetSuperiors, allBaseOverrideSetTextureOffsetInferiors, "overrideSetTextureOffset");
        sortBases(afterSetTextureOffsetHookTypes, allBaseAfterSetTextureOffsetSuperiors, allBaseAfterSetTextureOffsetInferiors, "afterSetTextureOffset");
        initialized = true;
    }

    public static ModelPlayer[] getAllInstances()
    {
        return allInstances;
    }

    public static void beforeLocalConstructing(ModelPlayer var0, float var1)
    {
        ModelPlayer[] var2 = new ModelPlayer[allInstances.length + 1];

        for (int var3 = 0; var3 < allInstances.length; ++var3)
        {
            var2[var3] = allInstances[var3];
        }

        var2[allInstances.length] = var0;
        allInstances = var2;

        if (var0.modelPlayerAPI != null)
        {
            var0.modelPlayerAPI.beforeLocalConstructing(var1);
        }
    }

    public static void afterLocalConstructing(ModelPlayer var0, float var1)
    {
        if (var0.modelPlayerAPI != null)
        {
            var0.modelPlayerAPI.afterLocalConstructing(var1);
        }
    }

    private static void sortBases(List var0, Map var1, Map var2, String var3)
    {
        (new ModelPlayerBaseSorter(var0, var1, var2, var3)).Sort();
    }

    private static void sortDynamicBases(Map var0, Map var1, Map var2, String var3)
    {
        List var4 = (List)var0.get(var3);

        if (var4 != null && var4.size() > 1)
        {
            sortBases(var4, getDynamicSorters(var3, var4, var1), getDynamicSorters(var3, var4, var2), var3);
        }
    }

    private static Map getDynamicSorters(String var0, List var1, Map var2)
    {
        HashMap var3 = null;
        Iterator var4 = var1.iterator();

        while (var4.hasNext())
        {
            String var5 = (String)var4.next();
            Map var6 = (Map)var2.get(var5);

            if (var6 != null)
            {
                String[] var7 = (String[])((String[])var6.get(var0));

                if (var7 != null && var7.length > 0)
                {
                    if (var3 == null)
                    {
                        var3 = new HashMap(1);
                    }

                    var3.put(var5, var7);
                }
            }
        }

        return (Map)(var3 != null ? var3 : EmptySortMap);
    }

    private ModelPlayerAPI(ModelPlayer var1)
    {
        this.unmodifiableAllBaseIds = Collections.unmodifiableSet(this.allBaseObjects.keySet());
        this.modelPlayer = var1;
        initializer[0] = this;
        initializers[0] = this;
        Iterator var2 = allBaseConstructors.keySet().iterator();

        while (var2.hasNext())
        {
            String var3 = (String)var2.next();
            ModelPlayerBase var4 = this.createModelPlayerBase(var3);
            var4.beforeBaseAttach(false);
            this.allBaseObjects.put(var3, var4);
            this.baseObjectsToId.put(var4, var3);
        }

        this.beforeLocalConstructingHooks = this.create(beforeLocalConstructingHookTypes);
        this.afterLocalConstructingHooks = this.create(afterLocalConstructingHookTypes);
        this.updateModelPlayerBases();
        var2 = this.allBaseObjects.keySet().iterator();

        while (var2.hasNext())
        {
            ((ModelPlayerBase)this.allBaseObjects.get(var2.next())).afterBaseAttach(false);
        }
    }

    private ModelPlayerBase createModelPlayerBase(String var1)
    {
        Constructor var2 = (Constructor)allBaseConstructors.get(var1);
        initializers[1] = var1;

        try
        {
            ModelPlayerBase var5;

            if (var2.getParameterTypes().length == 1)
            {
                var5 = (ModelPlayerBase)var2.newInstance(initializer);
            }
            else
            {
                var5 = (ModelPlayerBase)var2.newInstance(initializers);
            }

            return var5;
        }
        catch (Exception var4)
        {
            throw new RuntimeException("Exception while creating a ModelPlayerBase of type \'" + var2.getDeclaringClass() + "\'", var4);
        }
    }

    private void updateModelPlayerBases()
    {
        this.beforeGetRandomModelBoxHooks = this.create(beforeGetRandomModelBoxHookTypes);
        this.overrideGetRandomModelBoxHooks = this.create(overrideGetRandomModelBoxHookTypes);
        this.afterGetRandomModelBoxHooks = this.create(afterGetRandomModelBoxHookTypes);
        this.isGetRandomModelBoxModded = this.beforeGetRandomModelBoxHooks != null || this.overrideGetRandomModelBoxHooks != null || this.afterGetRandomModelBoxHooks != null;
        this.beforeGetTextureOffsetHooks = this.create(beforeGetTextureOffsetHookTypes);
        this.overrideGetTextureOffsetHooks = this.create(overrideGetTextureOffsetHookTypes);
        this.afterGetTextureOffsetHooks = this.create(afterGetTextureOffsetHookTypes);
        this.isGetTextureOffsetModded = this.beforeGetTextureOffsetHooks != null || this.overrideGetTextureOffsetHooks != null || this.afterGetTextureOffsetHooks != null;
        this.beforeRenderHooks = this.create(beforeRenderHookTypes);
        this.overrideRenderHooks = this.create(overrideRenderHookTypes);
        this.afterRenderHooks = this.create(afterRenderHookTypes);
        this.isRenderModded = this.beforeRenderHooks != null || this.overrideRenderHooks != null || this.afterRenderHooks != null;
        this.beforeRenderCloakHooks = this.create(beforeRenderCloakHookTypes);
        this.overrideRenderCloakHooks = this.create(overrideRenderCloakHookTypes);
        this.afterRenderCloakHooks = this.create(afterRenderCloakHookTypes);
        this.isRenderCloakModded = this.beforeRenderCloakHooks != null || this.overrideRenderCloakHooks != null || this.afterRenderCloakHooks != null;
        this.beforeRenderEarsHooks = this.create(beforeRenderEarsHookTypes);
        this.overrideRenderEarsHooks = this.create(overrideRenderEarsHookTypes);
        this.afterRenderEarsHooks = this.create(afterRenderEarsHookTypes);
        this.isRenderEarsModded = this.beforeRenderEarsHooks != null || this.overrideRenderEarsHooks != null || this.afterRenderEarsHooks != null;
        this.beforeSetLivingAnimationsHooks = this.create(beforeSetLivingAnimationsHookTypes);
        this.overrideSetLivingAnimationsHooks = this.create(overrideSetLivingAnimationsHookTypes);
        this.afterSetLivingAnimationsHooks = this.create(afterSetLivingAnimationsHookTypes);
        this.isSetLivingAnimationsModded = this.beforeSetLivingAnimationsHooks != null || this.overrideSetLivingAnimationsHooks != null || this.afterSetLivingAnimationsHooks != null;
        this.beforeSetRotationAnglesHooks = this.create(beforeSetRotationAnglesHookTypes);
        this.overrideSetRotationAnglesHooks = this.create(overrideSetRotationAnglesHookTypes);
        this.afterSetRotationAnglesHooks = this.create(afterSetRotationAnglesHookTypes);
        this.isSetRotationAnglesModded = this.beforeSetRotationAnglesHooks != null || this.overrideSetRotationAnglesHooks != null || this.afterSetRotationAnglesHooks != null;
        this.beforeSetTextureOffsetHooks = this.create(beforeSetTextureOffsetHookTypes);
        this.overrideSetTextureOffsetHooks = this.create(overrideSetTextureOffsetHookTypes);
        this.afterSetTextureOffsetHooks = this.create(afterSetTextureOffsetHookTypes);
        this.isSetTextureOffsetModded = this.beforeSetTextureOffsetHooks != null || this.overrideSetTextureOffsetHooks != null || this.afterSetTextureOffsetHooks != null;
    }

    private void attachModelPlayerBase(String var1)
    {
        initializer[0] = this;
        initializers[0] = this;
        ModelPlayerBase var2 = this.createModelPlayerBase(var1);
        var2.beforeBaseAttach(true);
        this.allBaseObjects.put(var1, var2);
        this.updateModelPlayerBases();
        var2.afterBaseAttach(true);
    }

    private void detachModelPlayerBase(String var1)
    {
        ModelPlayerBase var2 = (ModelPlayerBase)this.allBaseObjects.get(var1);
        var2.beforeBaseDetach(true);
        this.allBaseObjects.remove(var1);
        this.updateModelPlayerBases();
        var2.afterBaseDetach(true);
    }

    private ModelPlayerBase[] create(List var1)
    {
        if (var1.isEmpty())
        {
            return null;
        }
        else
        {
            ModelPlayerBase[] var2 = new ModelPlayerBase[var1.size()];

            for (int var3 = 0; var3 < var2.length; ++var3)
            {
                var2[var3] = this.getModelPlayerBase((String)var1.get(var3));
            }

            return var2;
        }
    }

    private void beforeLocalConstructing(float var1)
    {
        if (this.beforeLocalConstructingHooks != null)
        {
            for (int var2 = this.beforeLocalConstructingHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeLocalConstructingHooks[var2].beforeLocalConstructing(var1);
            }
        }

        this.beforeLocalConstructingHooks = null;
    }

    private void afterLocalConstructing(float var1)
    {
        if (this.afterLocalConstructingHooks != null)
        {
            for (int var2 = 0; var2 < this.afterLocalConstructingHooks.length; ++var2)
            {
                this.afterLocalConstructingHooks[var2].afterLocalConstructing(var1);
            }
        }

        this.afterLocalConstructingHooks = null;
    }

    public ModelPlayerBase getModelPlayerBase(String var1)
    {
        return (ModelPlayerBase)this.allBaseObjects.get(var1);
    }

    public Set getModelPlayerBaseIds()
    {
        return this.unmodifiableAllBaseIds;
    }

    public Object dynamic(String var1, Object[] var2)
    {
        var1 = var1.replace('.', '_').replace(' ', '_');
        this.executeAll(var1, var2, beforeDynamicHookTypes, beforeDynamicHookMethods, true);
        Object var3 = this.dynamicOverwritten(var1, var2, (ModelPlayerBase)null);
        this.executeAll(var1, var2, afterDynamicHookTypes, afterDynamicHookMethods, false);
        return var3;
    }

    public Object dynamicOverwritten(String var1, Object[] var2, ModelPlayerBase var3)
    {
        List var4 = (List)overrideDynamicHookTypes.get(var1);
        String var5 = null;

        if (var4 != null)
        {
            if (var3 != null)
            {
                var5 = (String)this.baseObjectsToId.get(var3);
                int var9 = var4.indexOf(var5);

                if (var9 > 0)
                {
                    var5 = (String)var4.get(var9 - 1);
                }
                else
                {
                    var5 = null;
                }
            }
            else if (var4.size() > 0)
            {
                var5 = (String)var4.get(var4.size() - 1);
            }
        }

        Map var91;

        if (var5 == null)
        {
            var5 = (String)keysToVirtualIds.get(var1);

            if (var5 == null)
            {
                return null;
            }

            var91 = virtualDynamicHookMethods;
        }
        else
        {
            var91 = overrideDynamicHookMethods;
        }

        Map var7 = (Map)var91.get(((Constructor)allBaseConstructors.get(var5)).getDeclaringClass());

        if (var7 == null)
        {
            return null;
        }
        else
        {
            Method var8 = (Method)var7.get(var1);
            return var7 == null ? null : this.execute(this.getModelPlayerBase(var5), var8, var2);
        }
    }

    private void executeAll(String var1, Object[] var2, Map var3, Map var4, boolean var5)
    {
        List var6 = (List)var3.get(var1);

        if (var6 != null)
        {
            int var7 = var5 ? var6.size() - 1 : 0;

            while (true)
            {
                if (var5)
                {
                    if (var7 < 0)
                    {
                        break;
                    }
                }
                else if (var7 >= var6.size())
                {
                    break;
                }

                String var8 = (String)var6.get(var7);
                ModelPlayerBase var9 = this.getModelPlayerBase(var8);
                Class var10 = var9.getClass();
                Map var11 = (Map)var4.get(var10);

                if (var11 != null)
                {
                    Method var12 = (Method)var11.get(var1);

                    if (var12 != null)
                    {
                        this.execute(var9, var12, var2);
                    }
                }

                var7 += var5 ? -1 : 1;
            }
        }
    }

    private Object execute(ModelPlayerBase var1, Method var2, Object[] var3)
    {
        try
        {
            return var2.invoke(var1, var3);
        }
        catch (Exception var5)
        {
            throw new RuntimeException("Exception while invoking dynamic method", var5);
        }
    }

    public static ModelRenderer getRandomModelBox(ModelPlayer var0, Random var1)
    {
        ModelRenderer var2;

        if (var0.modelPlayerAPI != null)
        {
            var2 = var0.modelPlayerAPI.getRandomModelBox(var1);
        }
        else
        {
            var2 = var0.localGetRandomModelBox(var1);
        }

        return var2;
    }

    private ModelRenderer getRandomModelBox(Random var1)
    {
        if (this.beforeGetRandomModelBoxHooks != null)
        {
            for (int var4 = this.beforeGetRandomModelBoxHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeGetRandomModelBoxHooks[var4].beforeGetRandomModelBox(var1);
            }
        }

        ModelRenderer var41;

        if (this.overrideGetRandomModelBoxHooks != null)
        {
            var41 = this.overrideGetRandomModelBoxHooks[this.overrideGetRandomModelBoxHooks.length - 1].getRandomModelBox(var1);
        }
        else
        {
            var41 = this.modelPlayer.localGetRandomModelBox(var1);
        }

        if (this.afterGetRandomModelBoxHooks != null)
        {
            for (int var3 = 0; var3 < this.afterGetRandomModelBoxHooks.length; ++var3)
            {
                this.afterGetRandomModelBoxHooks[var3].afterGetRandomModelBox(var1);
            }
        }

        return var41;
    }

    protected ModelPlayerBase GetOverwrittenGetRandomModelBox(ModelPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetRandomModelBoxHooks.length; ++var2)
        {
            if (this.overrideGetRandomModelBoxHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetRandomModelBoxHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static TextureOffset getTextureOffset(ModelPlayer var0, String var1)
    {
        TextureOffset var2;

        if (var0.modelPlayerAPI != null)
        {
            var2 = var0.modelPlayerAPI.getTextureOffset(var1);
        }
        else
        {
            var2 = var0.localGetTextureOffset(var1);
        }

        return var2;
    }

    private TextureOffset getTextureOffset(String var1)
    {
        if (this.beforeGetTextureOffsetHooks != null)
        {
            for (int var4 = this.beforeGetTextureOffsetHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeGetTextureOffsetHooks[var4].beforeGetTextureOffset(var1);
            }
        }

        TextureOffset var41;

        if (this.overrideGetTextureOffsetHooks != null)
        {
            var41 = this.overrideGetTextureOffsetHooks[this.overrideGetTextureOffsetHooks.length - 1].getTextureOffset(var1);
        }
        else
        {
            var41 = this.modelPlayer.localGetTextureOffset(var1);
        }

        if (this.afterGetTextureOffsetHooks != null)
        {
            for (int var3 = 0; var3 < this.afterGetTextureOffsetHooks.length; ++var3)
            {
                this.afterGetTextureOffsetHooks[var3].afterGetTextureOffset(var1);
            }
        }

        return var41;
    }

    protected ModelPlayerBase GetOverwrittenGetTextureOffset(ModelPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetTextureOffsetHooks.length; ++var2)
        {
            if (this.overrideGetTextureOffsetHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetTextureOffsetHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void render(ModelPlayer var0, Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        if (var0.modelPlayerAPI != null)
        {
            var0.modelPlayerAPI.render(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            var0.localRender(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    private void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        int var8;

        if (this.beforeRenderHooks != null)
        {
            for (var8 = this.beforeRenderHooks.length - 1; var8 >= 0; --var8)
            {
                this.beforeRenderHooks[var8].beforeRender(var1, var2, var3, var4, var5, var6, var7);
            }
        }

        if (this.overrideRenderHooks != null)
        {
            this.overrideRenderHooks[this.overrideRenderHooks.length - 1].render(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            this.modelPlayer.localRender(var1, var2, var3, var4, var5, var6, var7);
        }

        if (this.afterRenderHooks != null)
        {
            for (var8 = 0; var8 < this.afterRenderHooks.length; ++var8)
            {
                this.afterRenderHooks[var8].afterRender(var1, var2, var3, var4, var5, var6, var7);
            }
        }
    }

    protected ModelPlayerBase GetOverwrittenRender(ModelPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderHooks.length; ++var2)
        {
            if (this.overrideRenderHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderCloak(ModelPlayer var0, float var1)
    {
        if (var0.modelPlayerAPI != null)
        {
            var0.modelPlayerAPI.renderCloak(var1);
        }
        else
        {
            var0.localRenderCloak(var1);
        }
    }

    private void renderCloak(float var1)
    {
        int var2;

        if (this.beforeRenderCloakHooks != null)
        {
            for (var2 = this.beforeRenderCloakHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeRenderCloakHooks[var2].beforeRenderCloak(var1);
            }
        }

        if (this.overrideRenderCloakHooks != null)
        {
            this.overrideRenderCloakHooks[this.overrideRenderCloakHooks.length - 1].renderCloak(var1);
        }
        else
        {
            this.modelPlayer.localRenderCloak(var1);
        }

        if (this.afterRenderCloakHooks != null)
        {
            for (var2 = 0; var2 < this.afterRenderCloakHooks.length; ++var2)
            {
                this.afterRenderCloakHooks[var2].afterRenderCloak(var1);
            }
        }
    }

    protected ModelPlayerBase GetOverwrittenRenderCloak(ModelPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderCloakHooks.length; ++var2)
        {
            if (this.overrideRenderCloakHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderCloakHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderEars(ModelPlayer var0, float var1)
    {
        if (var0.modelPlayerAPI != null)
        {
            var0.modelPlayerAPI.renderEars(var1);
        }
        else
        {
            var0.localRenderEars(var1);
        }
    }

    private void renderEars(float var1)
    {
        int var2;

        if (this.beforeRenderEarsHooks != null)
        {
            for (var2 = this.beforeRenderEarsHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeRenderEarsHooks[var2].beforeRenderEars(var1);
            }
        }

        if (this.overrideRenderEarsHooks != null)
        {
            this.overrideRenderEarsHooks[this.overrideRenderEarsHooks.length - 1].renderEars(var1);
        }
        else
        {
            this.modelPlayer.localRenderEars(var1);
        }

        if (this.afterRenderEarsHooks != null)
        {
            for (var2 = 0; var2 < this.afterRenderEarsHooks.length; ++var2)
            {
                this.afterRenderEarsHooks[var2].afterRenderEars(var1);
            }
        }
    }

    protected ModelPlayerBase GetOverwrittenRenderEars(ModelPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderEarsHooks.length; ++var2)
        {
            if (this.overrideRenderEarsHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderEarsHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void setLivingAnimations(ModelPlayer var0, EntityLiving var1, float var2, float var3, float var4)
    {
        if (var0.modelPlayerAPI != null)
        {
            var0.modelPlayerAPI.setLivingAnimations(var1, var2, var3, var4);
        }
        else
        {
            var0.localSetLivingAnimations(var1, var2, var3, var4);
        }
    }

    private void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4)
    {
        int var5;

        if (this.beforeSetLivingAnimationsHooks != null)
        {
            for (var5 = this.beforeSetLivingAnimationsHooks.length - 1; var5 >= 0; --var5)
            {
                this.beforeSetLivingAnimationsHooks[var5].beforeSetLivingAnimations(var1, var2, var3, var4);
            }
        }

        if (this.overrideSetLivingAnimationsHooks != null)
        {
            this.overrideSetLivingAnimationsHooks[this.overrideSetLivingAnimationsHooks.length - 1].setLivingAnimations(var1, var2, var3, var4);
        }
        else
        {
            this.modelPlayer.localSetLivingAnimations(var1, var2, var3, var4);
        }

        if (this.afterSetLivingAnimationsHooks != null)
        {
            for (var5 = 0; var5 < this.afterSetLivingAnimationsHooks.length; ++var5)
            {
                this.afterSetLivingAnimationsHooks[var5].afterSetLivingAnimations(var1, var2, var3, var4);
            }
        }
    }

    protected ModelPlayerBase GetOverwrittenSetLivingAnimations(ModelPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetLivingAnimationsHooks.length; ++var2)
        {
            if (this.overrideSetLivingAnimationsHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetLivingAnimationsHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void setRotationAngles(ModelPlayer var0, float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        if (var0.modelPlayerAPI != null)
        {
            var0.modelPlayerAPI.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            var0.localSetRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    private void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        int var8;

        if (this.beforeSetRotationAnglesHooks != null)
        {
            for (var8 = this.beforeSetRotationAnglesHooks.length - 1; var8 >= 0; --var8)
            {
                this.beforeSetRotationAnglesHooks[var8].beforeSetRotationAngles(var1, var2, var3, var4, var5, var6, var7);
            }
        }

        if (this.overrideSetRotationAnglesHooks != null)
        {
            this.overrideSetRotationAnglesHooks[this.overrideSetRotationAnglesHooks.length - 1].setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            this.modelPlayer.localSetRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        }

        if (this.afterSetRotationAnglesHooks != null)
        {
            for (var8 = 0; var8 < this.afterSetRotationAnglesHooks.length; ++var8)
            {
                this.afterSetRotationAnglesHooks[var8].afterSetRotationAngles(var1, var2, var3, var4, var5, var6, var7);
            }
        }
    }

    protected ModelPlayerBase GetOverwrittenSetRotationAngles(ModelPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetRotationAnglesHooks.length; ++var2)
        {
            if (this.overrideSetRotationAnglesHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetRotationAnglesHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void setTextureOffset(ModelPlayer var0, String var1, int var2, int var3)
    {
        if (var0.modelPlayerAPI != null)
        {
            var0.modelPlayerAPI.setTextureOffset(var1, var2, var3);
        }
        else
        {
            var0.localSetTextureOffset(var1, var2, var3);
        }
    }

    private void setTextureOffset(String var1, int var2, int var3)
    {
        int var4;

        if (this.beforeSetTextureOffsetHooks != null)
        {
            for (var4 = this.beforeSetTextureOffsetHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeSetTextureOffsetHooks[var4].beforeSetTextureOffset(var1, var2, var3);
            }
        }

        if (this.overrideSetTextureOffsetHooks != null)
        {
            this.overrideSetTextureOffsetHooks[this.overrideSetTextureOffsetHooks.length - 1].setTextureOffset(var1, var2, var3);
        }
        else
        {
            this.modelPlayer.localSetTextureOffset(var1, var2, var3);
        }

        if (this.afterSetTextureOffsetHooks != null)
        {
            for (var4 = 0; var4 < this.afterSetTextureOffsetHooks.length; ++var4)
            {
                this.afterSetTextureOffsetHooks[var4].afterSetTextureOffset(var1, var2, var3);
            }
        }
    }

    protected ModelPlayerBase GetOverwrittenSetTextureOffset(ModelPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetTextureOffsetHooks.length; ++var2)
        {
            if (this.overrideSetTextureOffsetHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetTextureOffsetHooks[var2 - 1];
            }
        }

        return var1;
    }
}
