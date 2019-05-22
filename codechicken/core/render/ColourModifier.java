package codechicken.core.render;

import codechicken.core.render.CCModel;
import codechicken.core.render.CCRenderState;
import codechicken.core.render.IVertexModifier;
import codechicken.core.render.UV;
import codechicken.core.vec.Vector3;
import net.minecraft.client.renderer.Tessellator;

public class ColourModifier implements IVertexModifier {

   public static final ColourModifier instance = new ColourModifier();


   public void applyModifiers(CCModel m, Tessellator tess, Vector3 vec, UV uv, Vector3 normal, int i) {
      if(CCRenderState.useModelColours() && m != null && m.colours != null) {
         CCRenderState.vertexColour(m.colours[i]);
      }

   }

   public boolean needsNormals() {
      return false;
   }
}
