package net.minecraft.client.addon.lavamobs.modelblock;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockWolf extends ModelBase
{
    ModelRenderer body;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer body7;
    ModelRenderer body8;
    ModelRenderer body9;
    ModelRenderer body10;
    ModelRenderer head;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer head6;
    ModelRenderer head7;
    ModelRenderer head8;
    ModelRenderer head9;
    ModelRenderer head10;
    ModelRenderer rightleg;
    ModelRenderer rightleg1;
    ModelRenderer rightleg2;
    ModelRenderer rightleg3;
    ModelRenderer leftleg;
    ModelRenderer leftleg1;
    ModelRenderer leftleg2;
    ModelRenderer leftleg3;
    ModelRenderer rightarm;
    ModelRenderer rightarm1;
    ModelRenderer rightarm2;
    ModelRenderer rightarm3;
    ModelRenderer rightarm4;
    ModelRenderer rightarm5;
    ModelRenderer rightarm6;
    ModelRenderer rightarm7;
    ModelRenderer rightarm8;
    ModelRenderer rightarm9;
    ModelRenderer leftarm;
    ModelRenderer leftarm1;
    ModelRenderer leftarm2;
    ModelRenderer leftarm3;
    ModelRenderer leftarm4;
    ModelRenderer tail;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;

    public ModelBlockWolf()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.body = new ModelRenderer(this, 0, 18);
        this.body.addBox(-4.0F, 0.0F, -1.5F, 8, 10, 3);
        this.body.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 16, 31);
        this.body2.addBox(0.0F, 1.0F, -2.0F, 3, 4, 5);
        this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body2.setTextureSize(64, 64);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0F, 0.0F, -0.2617994F);
        this.body.addChild(this.body2);
        this.body3 = new ModelRenderer(this, 16, 31);
        this.body3.addBox(-3.0F, 1.0F, -2.0F, 3, 4, 5);
        this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body3.setTextureSize(64, 64);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0F, 0.0F, 0.2617994F);
        this.body.addChild(this.body3);
        this.body4 = new ModelRenderer(this, 19, 56);
        this.body4.addBox(-2.0F, -1.0F, 0.0F, 4, 5, 3);
        this.body4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body4.setTextureSize(64, 64);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.4886922F, 0.0F, 0.0F);
        this.body.addChild(this.body4);
        this.body5 = new ModelRenderer(this, 17, 57);
        this.body5.addBox(-2.5F, 2.0F, 1.0F, 5, 4, 3);
        this.body5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body5.setTextureSize(64, 64);
        this.body5.mirror = true;
        this.setRotation(this.body5, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.body5);
        this.body6 = new ModelRenderer(this, 20, 56);
        this.body6.addBox(-1.5F, 5.0F, 2.0F, 3, 5, 3);
        this.body6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body6.setTextureSize(64, 64);
        this.body6.mirror = true;
        this.setRotation(this.body6, -0.1919862F, 0.0F, 0.0F);
        this.body.addChild(this.body6);
        this.body7 = new ModelRenderer(this, 17, 57);
        this.body7.addBox(-2.0F, -4.0F, -3.0F, 4, 4, 3);
        this.body7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body7.setTextureSize(64, 64);
        this.body7.mirror = true;
        this.setRotation(this.body7, 0.2094395F, 0.0F, 0.0F);
        this.body.addChild(this.body7);
        this.body8 = new ModelRenderer(this, 17, 57);
        this.body8.addBox(-2.1F, -3.0F, -2.0F, 4, 3, 4);
        this.body8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body8.setTextureSize(64, 64);
        this.body8.mirror = true;
        this.setRotation(this.body8, -0.3839724F, 0.0F, 0.0F);
        this.body.addChild(this.body8);
        this.body9 = new ModelRenderer(this, 21, 56);
        this.body9.addBox(-2.0F, 0.0F, -3.0F, 4, 7, 1);
        this.body9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body9.setTextureSize(64, 64);
        this.body9.mirror = true;
        this.setRotation(this.body9, 0.1745329F, 0.0F, 0.0F);
        this.body.addChild(this.body9);
        this.body10 = new ModelRenderer(this, 22, 23);
        this.body10.addBox(-2.0F, 10.0F, -2.0F, 4, 4, 4);
        this.body10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body10.setTextureSize(64, 64);
        this.body10.mirror = true;
        this.setRotation(this.body10, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.body10);
        this.head = new ModelRenderer(this, 16, 54);
        this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 4);
        this.head.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 52);
        this.head2.addBox(-2.0F, -2.0F, -8.0F, 4, 2, 4);
        this.head2.setRotationPoint(0.0F, 0.0F, -0.0F);
        this.head2.setTextureSize(64, 64);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.head2);
        this.head3 = new ModelRenderer(this, 8, 59);
        this.head3.addBox(-1.0F, -6.0F, -3.0F, 2, 4, 1);
        this.head3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head3.setTextureSize(64, 64);
        this.head3.mirror = true;
        this.setRotation(this.head3, -0.2617994F, 0.0F, 0.5759587F);
        this.head.addChild(this.head3);
        this.head4 = new ModelRenderer(this, 8, 59);
        this.head4.addBox(-1.0F, -6.0F, -3.0F, 2, 3, 1);
        this.head4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head4.setTextureSize(64, 64);
        this.head4.mirror = true;
        this.setRotation(this.head4, -0.2617994F, 0.0F, -0.5759587F);
        this.head.addChild(this.head4);
        this.head5 = new ModelRenderer(this, 0, 48);
        this.head5.addBox(-2.0F, 1.0F, -8.0F, 4, 1, 4);
        this.head5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head5.setTextureSize(64, 64);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.head5);
        this.head6 = new ModelRenderer(this, 0, 60);
        this.head6.addBox(-1.0F, -2.5F, -8.5F, 2, 2, 2);
        this.head6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head6.setTextureSize(64, 64);
        this.head6.mirror = true;
        this.setRotation(this.head6, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.head6);
        this.head7 = new ModelRenderer(this, 7, 0);
        this.head7.addBox(0.5F, 0.2F, -7.5F, 1, 1, 1);
        this.head7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head7.setTextureSize(64, 64);
        this.head7.mirror = true;
        this.setRotation(this.head7, 0.0F, 0.0F, 0.1396263F);
        this.head.addChild(this.head7);
        this.head8 = new ModelRenderer(this, 7, 0);
        this.head8.addBox(-1.5F, 0.2F, -7.5F, 1, 1, 1);
        this.head8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head8.setTextureSize(64, 64);
        this.head8.mirror = true;
        this.setRotation(this.head8, 0.0F, 0.0F, -0.1396263F);
        this.head.addChild(this.head8);
        this.head9 = new ModelRenderer(this, 1, 58);
        this.head9.addBox(0.5F, -3.0F, -4.1F, 2, 1, 1);
        this.head9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head9.setTextureSize(64, 64);
        this.head9.mirror = true;
        this.setRotation(this.head9, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.head9);
        this.head10 = new ModelRenderer(this, 0, 58);
        this.head10.addBox(-2.5F, -3.0F, -4.1F, 2, 1, 1);
        this.head10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head10.setTextureSize(64, 64);
        this.head10.mirror = true;
        this.setRotation(this.head10, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.head10);
        this.rightleg = new ModelRenderer(this, 0, 0);
        this.rightleg.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.rightleg.setRotationPoint(-2.0F, 16.0F, 1.0F);
        this.rightleg.setTextureSize(64, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        this.rightleg1 = new ModelRenderer(this, 19, 53);
        this.rightleg1.addBox(-3.0F, -1.5F, -2.0F, 3, 7, 4);
        this.rightleg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightleg1.setTextureSize(64, 64);
        this.rightleg1.mirror = true;
        this.setRotation(this.rightleg1, -0.837758F, 0.0F, 0.1919862F);
        this.rightleg.addChild(this.rightleg1);
        this.rightleg2 = new ModelRenderer(this, 21, 56);
        this.rightleg2.addBox(-3.0F, 1.0F, -5.0F, 2, 5, 3);
        this.rightleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightleg2.setTextureSize(64, 64);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.715585F, 0.0F, 0.0F);
        this.rightleg.addChild(this.rightleg2);
        this.rightleg3 = new ModelRenderer(this, 0, 40);
        this.rightleg3.addBox(-4.0F, 6.0F, -4.0F, 4, 2, 6);
        this.rightleg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightleg3.setTextureSize(64, 64);
        this.rightleg3.mirror = true;
        this.setRotation(this.rightleg3, 0.0F, 0.0F, 0.0F);
        this.rightleg.addChild(this.rightleg3);
        this.leftleg = new ModelRenderer(this, 0, 0);
        this.leftleg.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.leftleg.setRotationPoint(2.0F, 16.0F, 1.0F);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        this.leftleg1 = new ModelRenderer(this, 19, 53);
        this.leftleg1.addBox(0.0F, -1.5F, -2.0F, 3, 7, 4);
        this.leftleg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftleg1.setTextureSize(64, 64);
        this.leftleg1.mirror = true;
        this.setRotation(this.leftleg1, -0.837758F, 0.0F, -0.1919862F);
        this.leftleg.addChild(this.leftleg1);
        this.leftleg2 = new ModelRenderer(this, 21, 56);
        this.leftleg2.addBox(1.0F, 1.0F, -5.0F, 2, 5, 3);
        this.leftleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftleg2.setTextureSize(64, 64);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.715585F, 0.0F, 0.0F);
        this.leftleg.addChild(this.leftleg2);
        this.leftleg3 = new ModelRenderer(this, 1, 40);
        this.leftleg3.addBox(0.0F, 6.0F, -4.0F, 4, 2, 6);
        this.leftleg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftleg3.setTextureSize(64, 64);
        this.leftleg3.mirror = true;
        this.setRotation(this.leftleg3, 0.0F, 0.0F, 0.0F);
        this.leftleg.addChild(this.leftleg3);
        this.rightarm = new ModelRenderer(this, 0, 0);
        this.rightarm.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.rightarm.setRotationPoint(-3.6F, 6.0F, 0.0F);
        this.rightarm.setTextureSize(64, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
        this.rightarm1 = new ModelRenderer(this, 0, 31);
        this.rightarm1.addBox(-3.0F, -2.0F, -2.0F, 3, 4, 5);
        this.rightarm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm1.setTextureSize(64, 64);
        this.rightarm1.mirror = true;
        this.setRotation(this.rightarm1, 0.0F, 0.0F, 0.2617994F);
        this.rightarm.addChild(this.rightarm1);
        this.rightarm2 = new ModelRenderer(this, 19, 54);
        this.rightarm2.addBox(-2.5F, 1.5F, -1.5F, 2, 6, 4);
        this.rightarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm2.setTextureSize(64, 64);
        this.rightarm2.mirror = true;
        this.setRotation(this.rightarm2, 0.0F, 0.0F, 0.2443461F);
        this.rightarm.addChild(this.rightarm2);
        this.rightarm3 = new ModelRenderer(this, 19, 57);
        this.rightarm3.addBox(-4.0F, 6.0F, 1.0F, 2, 4, 3);
        this.rightarm3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm3.setTextureSize(64, 64);
        this.rightarm3.mirror = true;
        this.setRotation(this.rightarm3, -0.3665191F, 0.0F, 0.0F);
        this.rightarm.addChild(this.rightarm3);
        this.rightarm4 = new ModelRenderer(this, 6, 42);
        this.rightarm4.addBox(-4.0F, 10.0F, 0.5F, 2, 2, 4);
        this.rightarm4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm4.setTextureSize(64, 64);
        this.rightarm4.mirror = true;
        this.setRotation(this.rightarm4, -0.3665191F, 0.0F, 0.0F);
        this.rightarm.addChild(this.rightarm4);
        this.rightarm5 = new ModelRenderer(this, 0, 6);
        this.rightarm5.addBox(-3.5F, 10.0F, -10.5F, 1, 2, 10);
        this.rightarm5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm5.setTextureSize(64, 64);
        this.rightarm5.mirror = true;
        this.setRotation(this.rightarm5, -0.3665191F, 0.0F, 0.0F);
        this.rightarm.addChild(this.rightarm5);
        this.rightarm6 = new ModelRenderer(this, 8, 14);
        this.rightarm6.addBox(-3.5F, -1.0F, -16.2F, 1, 2, 2);
        this.rightarm6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm6.setTextureSize(64, 64);
        this.rightarm6.mirror = true;
        this.setRotation(this.rightarm6, 0.4537856F, 0.0F, 0.0F);
        this.rightarm.addChild(this.rightarm6);
        this.rightarm7 = new ModelRenderer(this, -10, 3);
        this.rightarm7.addBox(-3.0F, 9.5F, -10.5F, 0, 3, 10);
        this.rightarm7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm7.setTextureSize(64, 64);
        this.rightarm7.mirror = true;
        this.setRotation(this.rightarm7, -0.3665191F, 0.0F, 0.0F);
        this.rightarm.addChild(this.rightarm7);
        this.rightarm8 = new ModelRenderer(this, 0, 0);
        this.rightarm8.addBox(-4.0F, 9.0F, -0.5F, 2, 4, 1);
        this.rightarm8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm8.setTextureSize(64, 64);
        this.rightarm8.mirror = true;
        this.setRotation(this.rightarm8, -0.3665191F, 0.0F, 0.0F);
        this.rightarm.addChild(this.rightarm8);
        this.rightarm9 = new ModelRenderer(this, 0, 0);
        this.rightarm9.addBox(-3.5F, 10.5F, 4.5F, 1, 1, 1);
        this.rightarm9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm9.setTextureSize(64, 64);
        this.rightarm9.mirror = true;
        this.setRotation(this.rightarm9, -0.3665191F, 0.0F, 0.0F);
        this.rightarm.addChild(this.rightarm9);
        this.leftarm = new ModelRenderer(this, 0, 0);
        this.leftarm.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.leftarm.setRotationPoint(3.6F, 6.0F, 0.0F);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
        this.leftarm1 = new ModelRenderer(this, 0, 31);
        this.leftarm1.addBox(0.0F, -2.0F, -2.0F, 3, 4, 5);
        this.leftarm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm1.setTextureSize(64, 64);
        this.leftarm1.mirror = true;
        this.setRotation(this.leftarm1, 0.0F, 0.0F, -0.2617994F);
        this.leftarm.addChild(this.leftarm1);
        this.leftarm2 = new ModelRenderer(this, 20, 54);
        this.leftarm2.addBox(0.5F, 1.5F, -1.5F, 2, 6, 4);
        this.leftarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm2.setTextureSize(64, 64);
        this.leftarm2.mirror = true;
        this.setRotation(this.leftarm2, 0.0F, 0.0F, -0.2443461F);
        this.leftarm.addChild(this.leftarm2);
        this.leftarm3 = new ModelRenderer(this, 20, 57);
        this.leftarm3.addBox(2.0F, 6.0F, 1.0F, 2, 4, 3);
        this.leftarm3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm3.setTextureSize(64, 64);
        this.leftarm3.mirror = true;
        this.setRotation(this.leftarm3, -0.3665191F, 0.0F, 0.0F);
        this.leftarm.addChild(this.leftarm3);
        this.leftarm4 = new ModelRenderer(this, 1, 42);
        this.leftarm4.addBox(2.0F, 10.0F, 0.5F, 2, 2, 4);
        this.leftarm4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm4.setTextureSize(64, 64);
        this.leftarm4.mirror = true;
        this.setRotation(this.leftarm4, -0.3665191F, 0.0F, 0.0F);
        this.leftarm.addChild(this.leftarm4);
        this.tail = new ModelRenderer(this, 0, 0);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.tail.setRotationPoint(0.0F, 15.0F, 2.0F);
        this.tail.setTextureSize(64, 64);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.0F, 0.0F, 0.0F);
        this.tail1 = new ModelRenderer(this, 17, 60);
        this.tail1.addBox(-1.0F, -1.0F, 0.0F, 2, 1, 2);
        this.tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail1.setTextureSize(64, 64);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, -0.6632251F, 0.0F, 0.0F);
        this.tail.addChild(this.tail1);
        this.tail2 = new ModelRenderer(this, 17, 60);
        this.tail2.addBox(-1.0F, -0.5F, 2.0F, 2, 1, 2);
        this.tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail2.setTextureSize(64, 64);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, -0.418879F, 0.0F, 0.0F);
        this.tail.addChild(this.tail2);
        this.tail3 = new ModelRenderer(this, 17, 60);
        this.tail3.addBox(-1.0F, 1.8F, 2.5F, 2, 3, 1);
        this.tail3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail3.setTextureSize(64, 64);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 0.1919862F, 0.0F, 0.0F);
        this.tail.addChild(this.tail3);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    }

    public void render()
    {
        float par7 = 0.0625F;
        this.head.render(par7);
        this.body.render(par7);
        this.rightarm.render(par7);
        this.leftarm.render(par7);
        this.rightleg.render(par7);
        this.leftleg.render(par7);
        this.tail.render(par7);
    }
}
