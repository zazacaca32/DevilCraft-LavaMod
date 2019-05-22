package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ModelFineryChestM extends ModelBipedAccesories
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape100;
    ModelRenderer Shape101;
    ModelRenderer Shape102;
    ModelRenderer Shape103;
    ModelRenderer Shape104;
    ModelRenderer Shape105;
    ModelRenderer Shape106;
    ModelRenderer Shape107;
    ModelRenderer Shape108;
    ModelRenderer Shape109;
    ModelRenderer Shape200;
    ModelRenderer Shape201;
    ModelRenderer Shape202;
    ModelRenderer Shape203;
    ModelRenderer Shape204;
    ModelRenderer Shape205;
    ModelRenderer Shape206;
    ModelRenderer Shape207;
    ModelRenderer Shape208;
    ModelRenderer Shape209;
    ModelRenderer Shape551;
    ModelRenderer Shape552;
    ModelRenderer Shape553;
    ModelRenderer Shape554;
    ModelRenderer Shape555;
    final String texture = "/mods/models/accesories/finerymen.png";

    public ModelFineryChestM()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 0, 16)).addBox(-3.0F, 0.0F, -2.2F, 6, 12, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(128, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(2.2F, -0.5F, -2.7F, 2, 7, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(128, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0698132F, 0.296706F);
        (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(-4.2F, -0.5F, -2.7F, 2, 7, 1);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(128, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, -0.0698132F, -0.296706F);
        (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(-1.0F, 6.7F, -2.7F, 2, 5, 1);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(128, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.1570796F);
        (this.Shape5 = new ModelRenderer(this, 0, 0)).addBox(-1.0F, 6.7F, -2.7F, 2, 5, 1);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(128, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, -0.1570796F);
        (this.Shape6 = new ModelRenderer(this, 0, 0)).addBox(-4.0F, 0.0F, -2.5F, 2, 12, 1);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(128, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape7 = new ModelRenderer(this, 0, 0)).addBox(2.0F, 0.0F, -2.5F, 2, 12, 1);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(128, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new ModelRenderer(this, 0, 0)).addBox(-0.5F, 0.5F, -3.0F, 1, 1, 1);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(128, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new ModelRenderer(this, 0, 0)).addBox(0.5F, 0.0F, -3.2F, 1, 2, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(128, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 0, 0)).addBox(-1.5F, 0.0F, -3.2F, 1, 2, 1);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(128, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new ModelRenderer(this, 0, 0)).addBox(-4.0F, 0.0F, 1.5F, 8, 10, 1);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(128, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new ModelRenderer(this, 0, 0)).addBox(-1.5F, 10.0F, 0.0F, 4, 3, 1);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(128, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.1396263F, 0.0F, -0.1570796F);
        (this.Shape13 = new ModelRenderer(this, 0, 0)).addBox(-2.5F, 10.0F, 0.0F, 4, 3, 1);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(128, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.1396263F, 0.0F, 0.1570796F);
        (this.Shape14 = new ModelRenderer(this, 0, 0)).addBox(-4.2F, 0.0F, -2.0F, 1, 12, 4);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(128, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        (this.Shape15 = new ModelRenderer(this, 0, 0)).addBox(3.2F, 0.0F, -2.0F, 1, 12, 4);
        this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape15.setTextureSize(128, 64);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        (this.Shape16 = new ModelRenderer(this, 0, 16)).addBox(-4.0F, -0.1F, -2.0F, 8, 1, 4);
        this.Shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape16.setTextureSize(128, 64);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
        (this.Shape17 = new ModelRenderer(this, 18, 14)).addBox(-0.5F, 6.0F, -3.0F, 1, 1, 1);
        this.Shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape17.setTextureSize(128, 64);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        (this.Shape100 = new ModelRenderer(this, 0, 16)).addBox(-3.0F, 7.0F, -2.1F, 4, 1, 1);
        this.Shape100.setTextureSize(128, 64);
        this.Shape100.mirror = true;
        this.setRotation(this.Shape100, 0.0F, 0.0F, 0.0F);
        (this.Shape101 = new ModelRenderer(this, 0, 16)).addBox(-3.1F, 7.0F, -2.0F, 1, 1, 4);
        this.Shape101.setTextureSize(128, 64);
        this.Shape101.mirror = true;
        this.setRotation(this.Shape101, 0.0F, 0.0F, 0.0F);
        (this.Shape102 = new ModelRenderer(this, 0, 16)).addBox(-3.0F, 7.0F, 1.1F, 4, 1, 1);
        this.Shape102.setTextureSize(128, 64);
        this.Shape102.mirror = true;
        this.setRotation(this.Shape102, 0.0F, 0.0F, 0.0F);
        (this.Shape103 = new ModelRenderer(this, 0, 16)).addBox(0.1F, 7.0F, -2.0F, 1, 1, 4);
        this.Shape103.setTextureSize(128, 64);
        this.Shape103.mirror = true;
        this.setRotation(this.Shape103, 0.0F, 0.0F, 0.0F);
        (this.Shape104 = new ModelRenderer(this, 0, 0)).addBox(0.3F, -2.0F, -2.0F, 1, 9, 4);
        this.Shape104.setTextureSize(128, 64);
        this.Shape104.mirror = true;
        this.setRotation(this.Shape104, 0.0F, 0.0F, 0.0F);
        (this.Shape105 = new ModelRenderer(this, 0, 0)).addBox(-3.0F, -2.0F, -2.5F, 4, 9, 1);
        this.Shape105.setTextureSize(128, 64);
        this.Shape105.mirror = true;
        this.setRotation(this.Shape105, 0.0F, 0.0F, 0.0F);
        (this.Shape106 = new ModelRenderer(this, 0, 0)).addBox(-3.5F, -2.0F, -2.0F, 1, 9, 4);
        this.Shape106.setTextureSize(128, 64);
        this.Shape106.mirror = true;
        this.setRotation(this.Shape106, 0.0F, 0.0F, 0.0F);
        (this.Shape107 = new ModelRenderer(this, 0, 0)).addBox(-3.0F, -2.0F, 1.5F, 4, 9, 1);
        this.Shape107.setTextureSize(128, 64);
        this.Shape107.mirror = true;
        this.setRotation(this.Shape107, 0.0F, 0.0F, 0.0F);
        (this.Shape108 = new ModelRenderer(this, 0, 0)).addBox(-3.0F, -2.1F, -2.0F, 4, 1, 4);
        this.Shape108.setTextureSize(128, 64);
        this.Shape108.mirror = true;
        this.setRotation(this.Shape108, 0.0F, 0.0F, 0.0F);
        (this.Shape109 = new ModelRenderer(this, 18, 14)).addBox(-3.8F, 5.0F, -0.5F, 1, 1, 1);
        this.Shape109.setTextureSize(128, 64);
        this.Shape109.mirror = true;
        this.setRotation(this.Shape109, 0.0F, 0.0F, 0.0F);
        (this.Shape200 = new ModelRenderer(this, 0, 16)).addBox(-1.0F, 7.0F, -2.1F, 4, 1, 1);
        this.Shape200.setTextureSize(128, 64);
        this.Shape200.mirror = true;
        this.setRotation(this.Shape200, 0.0F, 0.0F, 0.0F);
        (this.Shape201 = new ModelRenderer(this, 0, 16)).addBox(2.1F, 7.0F, -2.0F, 1, 1, 4);
        this.Shape201.setTextureSize(128, 64);
        this.Shape201.mirror = true;
        this.setRotation(this.Shape201, 0.0F, 0.0F, 0.0F);
        (this.Shape202 = new ModelRenderer(this, 0, 16)).addBox(-1.0F, 7.0F, 1.1F, 4, 1, 1);
        this.Shape202.setTextureSize(128, 64);
        this.Shape202.mirror = true;
        this.setRotation(this.Shape202, 0.0F, 0.0F, 0.0F);
        (this.Shape203 = new ModelRenderer(this, 0, 16)).addBox(-1.1F, 7.0F, -2.0F, 1, 1, 4);
        this.Shape203.setTextureSize(128, 64);
        this.Shape203.mirror = true;
        this.setRotation(this.Shape203, 0.0F, 0.0F, 0.0F);
        (this.Shape204 = new ModelRenderer(this, 0, 0)).addBox(-1.3F, -2.0F, -2.0F, 1, 9, 4);
        this.Shape204.setTextureSize(128, 64);
        this.Shape204.mirror = true;
        this.setRotation(this.Shape204, 0.0F, 0.0F, 0.0F);
        (this.Shape205 = new ModelRenderer(this, 0, 0)).addBox(-1.0F, -2.0F, -2.5F, 4, 9, 1);
        this.Shape205.setTextureSize(128, 64);
        this.Shape205.mirror = true;
        this.setRotation(this.Shape205, 0.0F, 0.0F, 0.0F);
        (this.Shape206 = new ModelRenderer(this, 0, 0)).addBox(2.5F, -2.0F, -2.0F, 1, 9, 4);
        this.Shape206.setTextureSize(128, 64);
        this.Shape206.mirror = true;
        this.setRotation(this.Shape206, 0.0F, 0.0F, 0.0F);
        (this.Shape207 = new ModelRenderer(this, 0, 0)).addBox(-1.0F, -2.0F, 1.5F, 4, 9, 1);
        this.Shape207.setTextureSize(128, 64);
        this.Shape207.mirror = true;
        this.setRotation(this.Shape207, 0.0F, 0.0F, 0.0F);
        (this.Shape208 = new ModelRenderer(this, 0, 0)).addBox(-1.0F, -2.1F, -2.0F, 4, 1, 4);
        this.Shape208.setTextureSize(128, 64);
        this.Shape208.mirror = true;
        this.setRotation(this.Shape208, 0.0F, 0.0F, 0.0F);
        (this.Shape209 = new ModelRenderer(this, 18, 14)).addBox(2.8F, 5.0F, -0.5F, 1, 1, 1);
        this.Shape209.setTextureSize(128, 64);
        this.Shape209.mirror = true;
        this.setRotation(this.Shape209, 0.0F, 0.0F, 0.0F);
        (this.Shape551 = new ModelRenderer(this, 0, 16)).addBox(2.6F, 2.4F, -3.0F, 1, 1, 1);
        this.Shape551.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape551.setTextureSize(128, 64);
        this.Shape551.mirror = true;
        this.setRotation(this.Shape551, 0.0F, 0.0F, 0.0F);
        (this.Shape552 = new ModelRenderer(this, 0, 16)).addBox(1.4F, 2.4F, -3.0F, 1, 1, 1);
        this.Shape552.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape552.setTextureSize(128, 64);
        this.Shape552.mirror = true;
        this.setRotation(this.Shape552, 0.0F, 0.0F, 0.0F);
        (this.Shape553 = new ModelRenderer(this, 14, 14)).addBox(2.0F, 3.0F, -3.2F, 1, 1, 1);
        this.Shape553.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape553.setTextureSize(128, 64);
        this.Shape553.mirror = true;
        this.setRotation(this.Shape553, 0.0F, 0.0F, 0.0F);
        (this.Shape554 = new ModelRenderer(this, 0, 16)).addBox(2.6F, 3.6F, -3.0F, 1, 1, 1);
        this.Shape554.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape554.setTextureSize(128, 64);
        this.Shape554.mirror = true;
        this.setRotation(this.Shape554, 0.0F, 0.0F, 0.0F);
        (this.Shape555 = new ModelRenderer(this, 0, 16)).addBox(1.4F, 3.6F, -3.0F, 1, 1, 1);
        this.Shape555.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape555.setTextureSize(128, 64);
        this.Shape555.mirror = true;
        this.setRotation(this.Shape555, 0.0F, 0.0F, 0.0F);
        super.bipedBody.addChild(this.Shape1);
        super.bipedBody.addChild(this.Shape2);
        super.bipedBody.addChild(this.Shape3);
        super.bipedBody.addChild(this.Shape4);
        super.bipedBody.addChild(this.Shape5);
        super.bipedBody.addChild(this.Shape6);
        super.bipedBody.addChild(this.Shape7);
        super.bipedBody.addChild(this.Shape8);
        super.bipedBody.addChild(this.Shape9);
        super.bipedBody.addChild(this.Shape10);
        super.bipedBody.addChild(this.Shape11);
        super.bipedBody.addChild(this.Shape12);
        super.bipedBody.addChild(this.Shape13);
        super.bipedBody.addChild(this.Shape14);
        super.bipedBody.addChild(this.Shape15);
        super.bipedBody.addChild(this.Shape16);
        super.bipedBody.addChild(this.Shape17);
        super.bipedRightArm.cubeList.clear();
        super.bipedRightArm.addChild(this.Shape100);
        super.bipedRightArm.addChild(this.Shape101);
        super.bipedRightArm.addChild(this.Shape102);
        super.bipedRightArm.addChild(this.Shape103);
        super.bipedRightArm.addChild(this.Shape104);
        super.bipedRightArm.addChild(this.Shape105);
        super.bipedRightArm.addChild(this.Shape106);
        super.bipedRightArm.addChild(this.Shape107);
        super.bipedRightArm.addChild(this.Shape108);
        super.bipedRightArm.addChild(this.Shape109);
        super.bipedLeftArm.cubeList.clear();
        super.bipedLeftArm.addChild(this.Shape200);
        super.bipedLeftArm.addChild(this.Shape201);
        super.bipedLeftArm.addChild(this.Shape202);
        super.bipedLeftArm.addChild(this.Shape203);
        super.bipedLeftArm.addChild(this.Shape204);
        super.bipedLeftArm.addChild(this.Shape205);
        super.bipedLeftArm.addChild(this.Shape206);
        super.bipedLeftArm.addChild(this.Shape207);
        super.bipedLeftArm.addChild(this.Shape208);
        super.bipedLeftArm.addChild(this.Shape209);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        ItemStack it = ((EntityPlayer)par1Entity).inventory.armorItemInSlot(3);

        if (super.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(0.75F, 0.75F, 0.75F);
            GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
            super.bipedHead.render(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            super.bipedBody.render(par7);

            if (it != null && it.itemID == 2806 && it.getItemDamage() == 5)
            {
                this.renderFlower(par7);
            }

            super.bipedRightArm.render(par7);
            super.bipedLeftArm.render(par7);
            super.bipedRightLeg.render(par7);
            super.bipedLeftLeg.render(par7);
            super.bipedHeadwear.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            super.bipedHead.render(par7);
            super.bipedBody.render(par7);

            if (it != null && it.itemID == 2806 && it.getItemDamage() == 5)
            {
                this.renderFlower(par7);
            }

            super.bipedRightArm.render(par7);
            super.bipedLeftArm.render(par7);
            super.bipedRightLeg.render(par7);
            super.bipedLeftLeg.render(par7);
            super.bipedHeadwear.render(par7);
        }
    }

    private void renderFlower(float par7)
    {
        this.Shape551.render(par7);
        this.Shape552.render(par7);
        this.Shape553.render(par7);
        this.Shape554.render(par7);
        this.Shape555.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public String getTexture()
    {
        return "/mods/models/accesories/finerymen.png";
    }
}
