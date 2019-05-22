package net.minecraft.client.addon.spearaddon.bow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BagModel extends ModelBase
{
    ModelRenderer chest;
    ModelRenderer chest1;
    ModelRenderer chest2;
    ModelRenderer chest3;
    ModelRenderer chest4;
    ModelRenderer lock;
    ModelRenderer lock1;
    ModelRenderer lock2;
    ModelRenderer lock3;
    ModelRenderer mount;
    ModelRenderer mount1;
    ModelRenderer mount2;
    ModelRenderer mount3;

    public BagModel()
    {
        super.textureWidth = 64;
        super.textureHeight = 128;
        (this.chest = new ModelRenderer(this, 0, 41)).addBox(-7.0F, -4.0F, -14.0F, 14, 1, 5);
        this.chest.setRotationPoint(0.0F, 13.0F, 11.5F);
        this.chest.setTextureSize(64, 128);
        this.chest.mirror = true;
        this.setRotation(this.chest, 0.0F, 0.0F, 0.0F);
        (this.chest1 = new ModelRenderer(this, 0, 58)).addBox(-7.0F, -4.0F, -14.0F, 14, 1, 12);
        this.chest1.setRotationPoint(0.0F, 15.0F, 8.0F);
        this.chest1.setTextureSize(64, 128);
        this.chest1.mirror = true;
        this.setRotation(this.chest1, 0.0F, 0.0F, 0.0F);
        (this.chest2 = new ModelRenderer(this, 0, 17)).addBox(-7.0F, 0.0F, -14.0F, 14, 10, 14);
        this.chest2.setRotationPoint(0.0F, 14.0F, 7.0F);
        this.chest2.setTextureSize(64, 128);
        this.chest2.mirror = true;
        this.setRotation(this.chest2, 0.0F, 0.0F, 0.0F);
        (this.chest3 = new ModelRenderer(this, 0, 0)).addBox(-7.0F, -4.0F, -14.0F, 14, 2, 14);
        this.chest3.setRotationPoint(0.0F, 16.0F, 7.0F);
        this.chest3.setTextureSize(64, 128);
        this.chest3.mirror = true;
        this.setRotation(this.chest3, 0.0F, 0.0F, 0.0F);
        (this.chest4 = new ModelRenderer(this, 0, 47)).addBox(-7.0F, -4.0F, -14.0F, 14, 1, 10);
        this.chest4.setRotationPoint(0.0F, 14.0F, 9.0F);
        this.chest4.setTextureSize(64, 128);
        this.chest4.mirror = true;
        this.setRotation(this.chest4, 0.0F, 0.0F, 0.0F);
        (this.lock = new ModelRenderer(this, 0, 8)).addBox(-2.0F, 3.0F, -1.0F, 4, 2, 2);
        this.lock.setRotationPoint(0.0F, 13.5F, -7.8F);
        this.lock.setTextureSize(64, 128);
        this.lock.mirror = true;
        this.setRotation(this.lock, -0.1396263F, -0.0349066F, -0.0349066F);
        (this.lock1 = new ModelRenderer(this, 0, 4)).addBox(-1.5F, 0.5F, -0.5F, 1, 2, 1);
        this.lock1.setRotationPoint(0.0F, 14.5F, -7.8F);
        this.lock1.setTextureSize(64, 128);
        this.lock1.mirror = true;
        this.setRotation(this.lock1, -0.1396263F, -0.0349066F, -0.0349066F);
        (this.lock2 = new ModelRenderer(this, 0, 4)).addBox(0.5F, 0.5F, -0.5F, 1, 3, 1);
        this.lock2.setRotationPoint(0.0F, 14.5F, -7.8F);
        this.lock2.setTextureSize(64, 128);
        this.lock2.mirror = true;
        this.setRotation(this.lock2, -0.1396263F, -0.0349066F, -0.0349066F);
        (this.lock3 = new ModelRenderer(this, 4, 6)).addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
        this.lock3.setRotationPoint(0.0F, 14.5F, -7.8F);
        this.lock3.setTextureSize(64, 128);
        this.lock3.mirror = true;
        this.setRotation(this.lock3, -0.1396263F, -0.0349066F, -0.0349066F);
        (this.mount = new ModelRenderer(this, 0, 20)).addBox(-1.0F, -1.5F, -14.0F, 2, 4, 1);
        this.mount.setRotationPoint(0.0F, 14.0F, 6.9F);
        this.mount.setTextureSize(64, 128);
        this.mount.mirror = true;
        this.setRotation(this.mount, 0.0F, 0.0F, 0.0F);
        (this.mount1 = new ModelRenderer(this, 1, 1)).addBox(-0.5F, 0.0F, 0.0F, 1, 1, 2);
        this.mount1.setRotationPoint(0.0F, 13.0F, -8.8F);
        this.mount1.setTextureSize(64, 128);
        this.mount1.mirror = true;
        this.setRotation(this.mount1, 0.0F, 0.0F, 0.0F);
        (this.mount2 = new ModelRenderer(this, 8, 0)).addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1);
        this.mount2.setRotationPoint(0.0F, 14.0F, -8.8F);
        this.mount2.setTextureSize(64, 128);
        this.mount2.mirror = true;
        this.setRotation(this.mount2, 0.0F, 0.0F, 0.0F);
        (this.mount3 = new ModelRenderer(this, 1, 1)).addBox(-0.5F, 0.0F, 0.0F, 1, 1, 2);
        this.mount3.setRotationPoint(0.0F, 15.0F, -7.8F);
        this.mount3.setTextureSize(64, 128);
        this.mount3.mirror = true;
        this.setRotation(this.mount3, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.chest.render(f5);
        this.chest1.render(f5);
        this.chest2.render(f5);
        this.chest3.render(f5);
        this.chest4.render(f5);
        this.lock.render(f5);
        this.lock1.render(f5);
        this.lock2.render(f5);
        this.lock3.render(f5);
        this.mount.render(f5);
        this.mount1.render(f5);
        this.mount2.render(f5);
        this.mount3.render(f5);
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
}
