package net.minecraft.client.addon.tchestplate.items.renders.models;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.items.renders.LModelRenderer;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModelPlayer;
import org.lwjgl.opengl.GL11;

public abstract class BaseItemModel extends ModelBase
{
    private boolean isNew = false;
    public int id;
    protected float rotateX = 0.0F;
    protected float rotateY = 0.0F;
    protected float rotateZ = 0.0F;
    private LModelRenderer[] models = null;
    private List dynamicModels;
    private final BaseItemModel.ModelType type;
    protected String tex = null;
    protected boolean bindToModel = true;
    protected int displayList;
    protected boolean compiled = false;
    protected int renderCalled = 0;
    protected boolean useDisplayLists = false;
    private static final float ANGLES_IN_RADIAN = (180F / (float)Math.PI);

    protected BaseItemModel(BaseItemModel.ModelType type)
    {
        this.type = type;
    }

    public void render(ModelPlayer modelPlayer, EntityPlayer player, float scale, float time, ExtendedPlayer pi)
    {
        GL11.glPushMatrix();
        this.preRender(modelPlayer, player, scale, time, pi);
        this.renderModels();
        GL11.glPopMatrix();
    }

    public void renderAsItem(float scale, float time)
    {
        switch (this.type.ordinal())
        {
            case 1:
                GL11.glTranslatef(0.0F, 0.35F, 0.0F);
                break;

            case 2:
                GL11.glTranslatef(0.0F, 0.35F, 0.0F);
                break;

            case 3:
                GL11.glTranslatef(0.0F, -0.3F, -0.4F);
                GL11.glScalef(0.7F, 0.7F, 0.7F);
        }

        this.renderModels();
    }

    protected void renderModels()
    {
        ++this.renderCalled;
        float scale = this.translateScale(0.0625F);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.tex);
        LModelRenderer[] arr$ = this.getModels();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            LModelRenderer mm2 = arr$[i$];
            mm2.render(scale);
        }
    }

    protected void preRender(ModelPlayer modelPlayer, EntityPlayer player, float scale, float time, ExtendedPlayer pi)
    {
        switch (this.type.ordinal())
        {
            default:
                this.setRotationAngles(this.rotateX, this.rotateY, this.rotateZ);
        }
    }

    public abstract String getName();

    public String getCreator()
    {
        return null;
    }

    public String getDescription()
    {
        return null;
    }

    protected int getDisplayList()
    {
        return this.displayList;
    }

    protected void setDisplayList(int displayList)
    {
        this.displayList = displayList;
    }

    protected boolean isCompiled()
    {
        return this.compiled;
    }

    protected void setCompiled(boolean compiled)
    {
        this.compiled = compiled;
    }

    public boolean isNew()
    {
        return this.isNew;
    }

    public BaseItemModel setNew(boolean flag)
    {
        this.isNew = flag;
        return this;
    }

    protected float translateScale(float scale)
    {
        return scale;
    }

    protected final LModelRenderer[] getModels()
    {
        if (this.models == null)
        {
            try
            {
                LinkedList var61 = new LinkedList();
                Field[] fields = this.getClass().getDeclaredFields();

                for (int i = 0; i < fields.length; ++i)
                {
                    Field f = fields[i];
                    f.setAccessible(true);

                    if (LModelRenderer.class.isAssignableFrom(f.getType()))
                    {
                        Object model = f.get(this);

                        if (model != null)
                        {
                            var61.add(model);
                        }
                    }
                }

                this.models = (LModelRenderer[])((LModelRenderer[])((LModelRenderer[])((LModelRenderer[])((LModelRenderer[])((LModelRenderer[])((LModelRenderer[])((LModelRenderer[])((LModelRenderer[])((LModelRenderer[])var61.toArray(new LModelRenderer[0]))))))))));
            }
            catch (Exception var6)
            {
                var6.printStackTrace();
                this.models = new LModelRenderer[0];
            }
        }

        return this.models;
    }

    protected void setRotation(LModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    protected void setTexture(String tex)
    {
        this.tex = tex;
    }

    protected void setModelRotation(ModelRenderer parent)
    {
        if (this.bindToModel)
        {
            this.rotateX = parent.rotateAngleX;
            this.rotateY = parent.rotateAngleY;
            this.rotateZ = parent.rotateAngleZ;
        }
    }

    protected void setRotationAngles(float x, float y, float z)
    {
        if (z != 0.0F)
        {
            GL11.glRotatef(z * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
        }

        if (y != 0.0F)
        {
            GL11.glRotatef(y * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        }

        if (x != 0.0F)
        {
            GL11.glRotatef(x * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
        }
    }

    public final BaseItemModel.ModelType getType()
    {
        return this.type;
    }

    public String getTexture()
    {
        return this.tex;
    }

    public int hashCode()
    {
        return this.id;
    }

    public boolean equals(Object obj)
    {
        return obj == this;
    }

    public String toString()
    {
        return this.id + "";
    }

    public void render(ModelPlayer modelPlayer, EntityPlayer player, float scale, float time, ExtendedPlayer pi, float var2, float var3, float var5, float var6, ItemStack item)
    {
    }

    public static enum ModelType
    {
        HEAD("HEAD", 0, "HEAD", 0, "HEAD", 0, "HEAD", 0, "HEAD", 0, "HEAD", 0, "Голова", "head"),
        MASK("MASK", 1, "MASK", 1, "MASK", 1, "MASK", 1, "MASK", 1, "MASK", 1, "Маска (Голова)", "head"),
        BODY("BODY", 2, "BODY", 2, "BODY", 2, "BODY", 2, "BODY", 2, "BODY", 2, "Тело", "body"),
        PET("PET", 3, "PET", 3, "PET", 3, "PET", 3, "PET", 3, "PET", 3, "Питомец", "pet"),
        SWORD("SWORD", 4, "SWORD", 4, "SWORD", 4, "SWORD", 4, "SWORD", 4, "SWORD", 4, "Меч", "sword"),
        SHIELD("SHIELD", 5, "SHIELD", 5, "SHIELD", 5, "SHIELD", 5, "SHIELD", 5, "SHIELD", 5, "Щит", "shield"),
        BRACELETS("BRACELETS", 6, "BRACELETS", 6, "BRACELETS", 6, "BRACELETS", 6, "BRACELETS", 6, "BRACELETS", 6, "Браслет", "braclet"),
    	BACKPACK("BACKPACK", 7, "BACKPACK", 6, "BACKPACK", 7, "BACKPACK", 7, "BACKPACK", 7, "BACKPACK", 7, "Рюкзак", "BACKPACK");

        private final String name;
        public final String slot;
        private static final BaseItemModel.ModelType[] $VALUES;
        private static final BaseItemModel.ModelType[] $VALUES$;
        private static final BaseItemModel.ModelType[] $VALUES$$;
        private static final BaseItemModel.ModelType[] $VALUES$$$;

        private ModelType(String var11, int var21, String asd, int czxdsd, String var111, int var211, String s, int n, String var1111, int var2111, String var136958, int var21136958, String name, String slot)
        {
            this.name = name;
            this.slot = slot;
        }

        public String getName()
        {
            return this.name;
        }

        static {
            $VALUES = new BaseItemModel.ModelType[]{HEAD, MASK, BODY, PET, SWORD, SHIELD, BRACELETS};
            $VALUES$ = new BaseItemModel.ModelType[]{HEAD, MASK, BODY, PET, SWORD, SHIELD, BRACELETS};
            $VALUES$$ = new BaseItemModel.ModelType[]{HEAD, MASK, BODY, PET, SWORD, SHIELD, BRACELETS};
            $VALUES$$$ = new BaseItemModel.ModelType[]{HEAD, MASK, BODY, PET, SWORD, SHIELD, BRACELETS};
        }
    }
}
