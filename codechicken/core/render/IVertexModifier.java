package codechicken.core.render;

import codechicken.core.render.CCModel;
import codechicken.core.render.UV;
import codechicken.core.vec.Vector3;
import net.minecraft.client.renderer.Tessellator;

public interface IVertexModifier {

   void applyModifiers(CCModel var1, Tessellator var2, Vector3 var3, UV var4, Vector3 var5, int var6);

   boolean needsNormals();
}
