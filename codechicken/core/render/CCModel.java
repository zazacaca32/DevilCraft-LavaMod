package codechicken.core.render;

import codechicken.core.lighting.CCRBModel;
import codechicken.core.lighting.LightModel;
import codechicken.core.render.CCRenderState;
import codechicken.core.render.ColourModifier;
import codechicken.core.render.IUVTransformation;
import codechicken.core.render.IVertexModifier;
import codechicken.core.render.UV;
import codechicken.core.render.UVTranslation;
import codechicken.core.render.Vertex5;
import codechicken.core.vec.CoordinateSystem;
import codechicken.core.vec.Cuboid6;
import codechicken.core.vec.RightHanded;
import codechicken.core.vec.Rotation;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.TransformationList;
import codechicken.core.vec.Translation;
import codechicken.core.vec.Vector3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.Tessellator;

public class CCModel {

   public final int vertexMode;
   public final int vp;
   public Vertex5[] verts;
   public Vector3[] normals;
   public int[] colours;
   private static final Pattern vertPattern = Pattern.compile("v(?: ([\\d\\.+-]+))+");
   private static final Pattern uvwPattern = Pattern.compile("vt(?: ([\\d\\.+-]+))+");
   private static final Pattern normalPattern = Pattern.compile("vn(?: ([\\d\\.+-]+))+");
   private static final Pattern polyPattern = Pattern.compile("f(?: ((?:\\d*)(?:/\\d*)?(?:/\\d*)?))+");
   private static final Matcher vertMatcher = vertPattern.matcher("");
   private static final Matcher uvwMatcher = uvwPattern.matcher("");
   private static final Matcher normalMatcher = normalPattern.matcher("");
   private static final Matcher polyMatcher = polyPattern.matcher("");


   protected CCModel(int vertexMode) {
      if(vertexMode != 7 && vertexMode != 4) {
         throw new IllegalArgumentException("Models must be GL_QUADS or GL_TRIANGLES");
      } else {
         this.vertexMode = vertexMode;
         this.vp = vertexMode == 7?4:3;
      }
   }

   public CCModel generateBox(int i, double x1, double y1, double z1, double w, double h, double d, double tx, double ty, double tw, double th, double f) {
      double x2 = x1 + w;
      double y2 = y1 + h;
      double z2 = z1 + d;
      x1 /= f;
      x2 /= f;
      y1 /= f;
      y2 /= f;
      z1 /= f;
      z2 /= f;
      double u1 = (tx + d + w) / tw;
      double v1 = (ty + d) / th;
      double u2 = (tx + d * 2.0D + w) / tw;
      double v2 = ty / th;
      this.verts[i++] = new Vertex5(x1, y1, z2, u1, v2);
      this.verts[i++] = new Vertex5(x1, y1, z1, u1, v1);
      this.verts[i++] = new Vertex5(x2, y1, z1, u2, v1);
      this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
      u1 = (tx + d) / tw;
      v1 = (ty + d) / th;
      u2 = (tx + d + w) / tw;
      v2 = ty / th;
      this.verts[i++] = new Vertex5(x2, y2, z2, u2, v2);
      this.verts[i++] = new Vertex5(x2, y2, z1, u2, v1);
      this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
      this.verts[i++] = new Vertex5(x1, y2, z2, u1, v2);
      u1 = (tx + d + w) / tw;
      v1 = (ty + d) / th;
      u2 = (tx + d) / tw;
      v2 = (ty + d + h) / th;
      this.verts[i++] = new Vertex5(x1, y2, z1, u2, v1);
      this.verts[i++] = new Vertex5(x2, y2, z1, u1, v1);
      this.verts[i++] = new Vertex5(x2, y1, z1, u1, v2);
      this.verts[i++] = new Vertex5(x1, y1, z1, u2, v2);
      u1 = (tx + d * 2.0D + w * 2.0D) / tw;
      v1 = (ty + d) / th;
      u2 = (tx + d * 2.0D + w) / tw;
      v2 = (ty + d + h) / th;
      this.verts[i++] = new Vertex5(x1, y2, z2, u1, v1);
      this.verts[i++] = new Vertex5(x1, y1, z2, u1, v2);
      this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
      this.verts[i++] = new Vertex5(x2, y2, z2, u2, v1);
      u1 = (tx + d) / tw;
      v1 = (ty + d) / th;
      u2 = tx / tw;
      v2 = (ty + d + h) / th;
      this.verts[i++] = new Vertex5(x1, y2, z2, u2, v1);
      this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
      this.verts[i++] = new Vertex5(x1, y1, z1, u1, v2);
      this.verts[i++] = new Vertex5(x1, y1, z2, u2, v2);
      u1 = (tx + d * 2.0D + w) / tw;
      v1 = (ty + d) / th;
      u2 = (tx + d + w) / tw;
      v2 = (ty + d + h) / th;
      this.verts[i++] = new Vertex5(x2, y1, z2, u1, v2);
      this.verts[i++] = new Vertex5(x2, y1, z1, u2, v2);
      this.verts[i++] = new Vertex5(x2, y2, z1, u2, v1);
      this.verts[i++] = new Vertex5(x2, y2, z2, u1, v1);
      return this;
   }

   public CCModel generateBlock(int i, Cuboid6 bounds) {
      return this.generateBlock(i, bounds.min.x, bounds.min.y, bounds.min.z, bounds.max.x, bounds.max.y, bounds.max.z);
   }

   public CCModel generateBlock(int i, double x1, double y1, double z1, double x2, double y2, double z2) {
      this.verts[i++] = new Vertex5(x1, y1, z2, x1, z2);
      this.verts[i++] = new Vertex5(x1, y1, z1, x1, z1);
      this.verts[i++] = new Vertex5(x2, y1, z1, x2, z1);
      this.verts[i++] = new Vertex5(x2, y1, z2, x2, z2);
      double u1 = x1 + 2.0D;
      double u2 = x2 + 2.0D;
      this.verts[i++] = new Vertex5(x2, y2, z2, u2, z2);
      this.verts[i++] = new Vertex5(x2, y2, z1, u2, z1);
      this.verts[i++] = new Vertex5(x1, y2, z1, u1, z1);
      this.verts[i++] = new Vertex5(x1, y2, z2, u1, z2);
      u1 = x1 + 4.0D;
      double v1 = 1.0D - y2;
      u2 = x2 + 4.0D;
      double v2 = 1.0D - y1;
      this.verts[i++] = new Vertex5(x1, y1, z1, u1, v2);
      this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
      this.verts[i++] = new Vertex5(x2, y2, z1, u2, v1);
      this.verts[i++] = new Vertex5(x2, y1, z1, u2, v2);
      u1 = x1 + 6.0D;
      v1 = 1.0D - y2;
      u2 = x2 + 6.0D;
      v2 = 1.0D - y1;
      this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
      this.verts[i++] = new Vertex5(x2, y2, z2, u2, v1);
      this.verts[i++] = new Vertex5(x1, y2, z2, u1, v1);
      this.verts[i++] = new Vertex5(x1, y1, z2, u1, v2);
      u1 = z1 + 8.0D;
      v1 = 1.0D - y2;
      u2 = z2 + 8.0D;
      v2 = 1.0D - y1;
      this.verts[i++] = new Vertex5(x1, y1, z2, u2, v2);
      this.verts[i++] = new Vertex5(x1, y2, z2, u2, v1);
      this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
      this.verts[i++] = new Vertex5(x1, y1, z1, u1, v2);
      u1 = z1 + 10.0D;
      v1 = 1.0D - y2;
      u2 = z2 + 10.0D;
      v2 = 1.0D - y1;
      this.verts[i++] = new Vertex5(x2, y1, z1, u1, v2);
      this.verts[i++] = new Vertex5(x2, y2, z1, u1, v1);
      this.verts[i++] = new Vertex5(x2, y2, z2, u2, v1);
      this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
      return this;
   }

   public CCModel computeNormals() {
      return this.computeNormals(0, this.verts.length);
   }

   public CCModel computeNormals(int start, int length) {
      if(length % this.vp == 0 && start % this.vp == 0) {
         if(this.normals == null) {
            this.normals = new Vector3[this.verts.length];
         }

         for(int k = 0; k < length; k += this.vp) {
            int i = k + start;
            Vector3 diff1 = this.verts[i + 1].vec.copy().subtract(this.verts[i].vec);
            Vector3 diff2 = this.verts[i + this.vp - 1].vec.copy().subtract(this.verts[i].vec);
            this.normals[i] = diff1.crossProduct(diff2).normalize();

            for(int d = 1; d < this.vp; ++d) {
               this.normals[i + d] = this.normals[i].copy();
            }
         }

         return this;
      } else {
         throw new IllegalArgumentException("Cannot generate normals across polygons");
      }
   }

   public CCModel computeLighting(LightModel light) {
      if(this.colours == null) {
         this.setColour(-1);
      }

      for(int k = 0; k < this.verts.length; ++k) {
         this.colours[k] = light.apply(this.colours[k], this.normals[k]);
      }

      return this;
   }

   public CCModel setColour(int c) {
      if(this.colours == null) {
         this.colours = new int[this.verts.length];
      }

      for(int k = 0; k < this.verts.length; ++k) {
         this.colours[k] = c;
      }

      return this;
   }

   public CCRBModel withMCLighting() {
      return new CCRBModel(this);
   }

   public CCModel smoothNormals() {
      ArrayList map = new ArrayList();
      int e = 0;

      while(e < this.verts.length) {
         Vector3 vec = this.verts[e].vec;
         Iterator n = map.iterator();

         while(true) {
            if(!n.hasNext()) {
               map.add((new CCModel.PositionNormalEntry(vec)).addNormal(this.normals[e]));
            } else {
               CCModel.PositionNormalEntry new_n = (CCModel.PositionNormalEntry)n.next();
               if(!new_n.positionEqual(vec)) {
                  continue;
               }

               new_n.addNormal(this.normals[e]);
            }

            ++e;
            break;
         }
      }

      Iterator var8 = map.iterator();

      while(var8.hasNext()) {
         CCModel.PositionNormalEntry var7 = (CCModel.PositionNormalEntry)var8.next();
         if(var7.normals.size() > 1) {
            Vector3 var9 = new Vector3();
            Iterator var6 = var7.normals.iterator();

            Vector3 var10;
            while(var6.hasNext()) {
               var10 = (Vector3)var6.next();
               var9.add(var10);
            }

            var9.normalize();
            var6 = var7.normals.iterator();

            while(var6.hasNext()) {
               var10 = (Vector3)var6.next();
               var10.set(var9);
            }
         }
      }

      return this;
   }

   public CCModel apply(Transformation t) {
      int k;
      for(k = 0; k < this.verts.length; ++k) {
         t.apply(this.verts[k].vec);
      }

      if(this.normals != null) {
         for(k = 0; k < this.normals.length; ++k) {
            t.applyN(this.normals[k]);
         }
      }

      return this;
   }

   public void render() {
      this.render(0, this.verts.length, (Transformation)null, (IUVTransformation)null, (IVertexModifier)null);
   }

   public void render(double x, double y, double z, double u, double v) {
      this.render(new Translation(new Vector3(x, y, z)), new UVTranslation(u, v));
   }

   public void render(double x, double y, double z, IUVTransformation u) {
      this.render(new Translation(new Vector3(x, y, z)), u);
   }

   public void render(Transformation t, double u, double v) {
      this.render(t, new UVTranslation(u, v));
   }

   public void render(Transformation t, IUVTransformation u) {
      this.render(t, u, ColourModifier.instance);
   }

   public void render(Transformation t, IUVTransformation u, IVertexModifier m) {
      this.render(0, this.verts.length, t, u, m);
   }

   public void render(int start, int length, Transformation t, IUVTransformation u, IVertexModifier m) {
      boolean drawNormal = CCRenderState.useNormals() && this.normals != null;
      boolean computeNormal = drawNormal || m != null && m.needsNormals();
      Vector3 normal = new Vector3();
      Vector3 vec = new Vector3();
      UV uv = new UV();
      Tessellator tess = Tessellator.instance;

      for(int k = 0; k < length; ++k) {
         int i = start + k;
         if(computeNormal) {
            if(t != null) {
               t.applyN(normal.set(this.normals[i]));
            } else {
               normal = this.normals[i];
            }

            if(drawNormal) {
               tess.setNormal((float)normal.x, (float)normal.y, (float)normal.z);
            }
         }

         Vertex5 vert = this.verts[i];
         if(t != null) {
            t.apply(vec.set(vert.vec));
         } else {
            vec = vert.vec;
         }

         if(u != null) {
            u.transform(uv.set(vert.uv));
         } else {
            uv = vert.uv;
         }

         if(m != null) {
            m.applyModifiers(this, tess, vec, uv, normal, i);
         }

         tess.addVertexWithUV(vec.x, vec.y, vec.z, uv.u, uv.v);
      }

   }

   public static CCModel quadModel(int numVerts) {
      return newModel(7, numVerts);
   }

   public static CCModel triModel(int numVerts) {
      return newModel(4, numVerts);
   }

   public static CCModel newModel(int vertexMode, int numVerts) {
      CCModel model = newModel(vertexMode);
      model.verts = new Vertex5[numVerts];
      return model;
   }

   public static CCModel newModel(int vertexMode) {
      return new CCModel(vertexMode);
   }

   private static double[] parseDoubles(String s, String token) {
      String[] as = s.split(token);
      double[] values = new double[as.length];

      for(int i = 0; i < as.length; ++i) {
         values[i] = Double.parseDouble(as[i]);
      }

      return values;
   }

   private static void illegalAssert(boolean b, String err) {
      if(!b) {
         throw new IllegalArgumentException(err);
      }
   }

   private static void assertMatch(Matcher m, String s) {
      m.reset(s);
      illegalAssert(m.matches(), "Malformed line: " + s);
   }

   public static Map parseObjModels(InputStream input, int vertexMode, CoordinateSystem importSystem) throws IOException {
      int vp = vertexMode == 7?4:3;
      HashMap modelMap = new HashMap();
      ArrayList verts = new ArrayList();
      ArrayList uvs = new ArrayList();
      ArrayList normals = new ArrayList();
      ArrayList polys = new ArrayList();
      String modelName = "unnamed";
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      String line;
      while((line = reader.readLine()) != null) {
         line = line.replaceAll("\\s+", " ").trim();
         if(!line.startsWith("#") && line.length() != 0) {
            double[] var17;
            Vector3 var18;
            if(line.startsWith("v ")) {
               assertMatch(vertMatcher, line);
               var17 = parseDoubles(line.substring(2), " ");
               illegalAssert(var17.length >= 3, "Vertices must have x, y and z components");
               var18 = new Vector3(var17[0], var17[1], var17[2]);
               importSystem.convert(var18);
               verts.add(var18);
            } else if(line.startsWith("vt ")) {
               assertMatch(uvwMatcher, line);
               var17 = parseDoubles(line.substring(3), " ");
               illegalAssert(var17.length >= 2, "Tex Coords must have u, and v components");
               uvs.add(new Vector3(var17[0], 1.0D - var17[1], 0.0D));
            } else if(line.startsWith("vn ")) {
               assertMatch(normalMatcher, line);
               var17 = parseDoubles(line.substring(3), " ");
               illegalAssert(var17.length >= 3, "Normals must have x, y and z components");
               var18 = (new Vector3(var17[0], var17[1], var17[2])).normalize();
               importSystem.convert(var18);
               normals.add(var18);
            } else {
               if(line.startsWith("f ")) {
                  assertMatch(polyMatcher, line);
                  String[] av = line.substring(2).split(" ");
                  illegalAssert(av.length >= 3, "Polygons must have at least 3 vertices");
                  int[][] polyVerts = new int[av.length][3];

                  for(int i = 0; i < av.length; ++i) {
                     String[] as = av[i].split("/");

                     for(int p = 0; p < as.length; ++p) {
                        if(as[p].length() > 0) {
                           polyVerts[i][p] = Integer.parseInt(as[p]);
                        }
                     }
                  }

                  if(vp == 3) {
                     triangulate(polys, polyVerts);
                  } else {
                     quadulate(polys, polyVerts);
                  }
               }

               if(line.startsWith("g ")) {
                  if(!polys.isEmpty()) {
                     modelMap.put(modelName, createModel(verts, uvs, normals, vertexMode, polys));
                     polys.clear();
                  }

                  modelName = line.substring(2);
               }
            }
         }
      }

      if(!polys.isEmpty()) {
         modelMap.put(modelName, createModel(verts, uvs, normals, vertexMode, polys));
      }

      return modelMap;
   }

   public static void triangulate(List polys, int[][] polyVerts) {
      for(int i = 2; i < polyVerts.length; ++i) {
         polys.add(polyVerts[0]);
         polys.add(polyVerts[i]);
         polys.add(polyVerts[i - 1]);
      }

   }

   public static void quadulate(List polys, int[][] polyVerts) {
      if(polyVerts.length == 4) {
         polys.add(polyVerts[0]);
         polys.add(polyVerts[3]);
         polys.add(polyVerts[2]);
         polys.add(polyVerts[1]);
      } else {
         for(int i = 2; i < polyVerts.length; ++i) {
            polys.add(polyVerts[0]);
            polys.add(polyVerts[i]);
            polys.add(polyVerts[i - 1]);
            polys.add(polyVerts[i - 1]);
         }
      }

   }

   public static Map parseObjModels(String s) {
      return parseObjModels(s, 4, new RightHanded());
   }

   public static Map parseObjModels(String s, CoordinateSystem importSystem) {
      return parseObjModels(s, 4, importSystem);
   }

   public static Map parseObjModels(String s, int vertexMode, CoordinateSystem importSystem) {
      try {
         return parseObjModels(CCModel.class.getResourceAsStream(s), vertexMode, importSystem);
      } catch (Exception var4) {
         throw new RuntimeException("failed to load model: " + s, var4);
      }
   }

   public static CCModel createModel(List verts, List uvs, List normals, int vertexMode, List polys) {
      int vp = vertexMode == 7?4:3;
      if(polys.size() >= vp && polys.size() % vp == 0) {
         boolean hasNormals = ((int[])polys.get(0))[2] > 0;
         CCModel model = newModel(vertexMode, polys.size());
         if(hasNormals) {
            model.normals = new Vector3[polys.size()];
         }

         for(int i = 0; i < polys.size(); ++i) {
            int[] ai = (int[])polys.get(i);
            Vector3 vert = ((Vector3)verts.get(ai[0] - 1)).copy();
            Vector3 uv = ai[1] <= 0?new Vector3():((Vector3)uvs.get(ai[1] - 1)).copy();
            if(ai[2] > 0 != hasNormals) {
               throw new IllegalArgumentException("Normals are an all or nothing deal here.");
            }

            model.verts[i] = new Vertex5(vert, uv.x, uv.y);
            if(hasNormals) {
               model.normals[i] = ((Vector3)normals.get(ai[2] - 1)).copy();
            }
         }

         return model;
      } else {
         throw new IllegalArgumentException("Invalid number of vertices for model: " + polys.size());
      }
   }

   public CCModel shrinkUVs(double d) {
      for(int k = 0; k < this.verts.length; k += this.vp) {
         UV uv = new UV();

         int i;
         for(i = 0; i < this.vp; ++i) {
            uv.add(this.verts[k + i].uv);
         }

         uv.mul(1.0D / (double)this.vp);

         for(i = 0; i < this.vp; ++i) {
            Vertex5 vert = this.verts[k + i];
            vert.uv.u += vert.uv.u < uv.u?d:-d;
            vert.uv.v += vert.uv.v < uv.v?d:-d;
         }
      }

      return this;
   }

   public CCModel sidedCopy(int side1, int side2, Vector3 point) {
      CCModel model = newModel(this.vertexMode, this.verts.length);
      copy(this, 0, model, 0, model.verts.length);
      return model.apply((new TransformationList(new Transformation[]{Rotation.sideRotations[side1].inverse(), Rotation.sideRotations[side2]})).at(point));
   }

   public static void copy(CCModel src, int srcpos, CCModel dest, int destpos, int length) {
      int k;
      for(k = 0; k < length; ++k) {
         dest.verts[destpos + k] = src.verts[srcpos + k].copy();
      }

      if(src.normals != null) {
         if(dest.normals == null) {
            dest.normals = new Vector3[dest.verts.length];
         }

         for(k = 0; k < length; ++k) {
            dest.normals[destpos + k] = src.normals[srcpos + k].copy();
         }
      }

      if(src.colours != null) {
         if(dest.colours == null) {
            dest.colours = new int[dest.verts.length];
         }

         System.arraycopy(src.colours, srcpos, dest.colours, destpos, length);
      }

   }

   public static void generateSidedModels(CCModel[] models, int side, Vector3 point) {
      for(int s = 0; s < 6; ++s) {
         if(s != side) {
            models[s] = models[side].sidedCopy(side, s, point);
         }
      }

   }

   public static void generateSidedModelsH(CCModel[] models, int side, Vector3 point) {
      for(int s = 2; s < 6; ++s) {
         if(s != side) {
            models[s] = models[side].sidedCopy(side, s, point);
         }
      }

   }

   public CCModel generateBackface(int srcpos, int destpos, int length) {
      if(srcpos + length > destpos) {
         throw new IllegalArgumentException("Overlapping src and dest arrays");
      } else if(srcpos % this.vp == 0 && destpos % this.vp == 0 && length % this.vp == 0) {
         int[][] o = new int[][]{new int[2], {1, this.vp - 1}, {2, this.vp - 2}, {3, this.vp - 3}};

         for(int i = 0; i < length; ++i) {
            int b = i / this.vp * this.vp;
            int d = i % this.vp;
            int di = destpos + b + o[d][1];
            int si = srcpos + b + o[d][0];
            this.verts[di] = new Vertex5(this.verts[si]);
            if(this.normals != null && this.normals[si] != null) {
               this.normals[di] = this.normals[si].copy().negate();
            }
         }

         return this;
      } else {
         throw new IllegalArgumentException("Vertices do not align with polygons");
      }
   }

   public CCModel generateSidedParts(int side, Vector3 point) {
      if(this.verts.length % (6 * this.vp) != 0) {
         throw new IllegalArgumentException("Invalid number of vertices for sided part generation");
      } else {
         int length = this.verts.length / 6;

         for(int s = 0; s < 6; ++s) {
            if(s != side) {
               this.generateSidedPart(side, s, point, length * side, length * s, length);
            }
         }

         return this;
      }
   }

   public CCModel generateSidedPartsH(int side, Vector3 point) {
      if(this.verts.length % (4 * this.vp) != 0) {
         throw new IllegalArgumentException("Invalid number of vertices for sided part generation");
      } else {
         int length = this.verts.length / 4;

         for(int s = 2; s < 6; ++s) {
            if(s != side) {
               this.generateSidedPart(side, s, point, length * (side - 2), length * (s - 2), length);
            }
         }

         return this;
      }
   }

   public CCModel generateSidedPart(int side1, int side2, Vector3 point, int srcpos, int destpos, int length) {
      return this.generateRotatedPart(new TransformationList(new Transformation[]{Rotation.sideRotations[side1].inverse(), Rotation.sideRotations[side2]}), point, srcpos, destpos, length);
   }

   public CCModel generateRotatedPart(Transformation t, Vector3 point, int srcpos, int destpos, int length) {
      int k;
      for(k = 0; k < length; ++k) {
         this.verts[destpos + k] = this.verts[srcpos + k].copy();
         this.verts[destpos + k].vec.subtract(point).apply(t).add(point);
      }

      if(this.normals != null) {
         for(k = 0; k < length; ++k) {
            this.normals[destpos + k] = this.normals[srcpos + k].copy().apply(t);
         }
      }

      return this;
   }

   public static CCModel combine(Collection models) {
      if(models.isEmpty()) {
         return null;
      } else {
         int numVerts = 0;
         int vertexMode = -1;

         CCModel c_model;
         for(Iterator i = models.iterator(); i.hasNext(); numVerts += c_model.verts.length) {
            c_model = (CCModel)i.next();
            if(vertexMode == -1) {
               vertexMode = c_model.vertexMode;
            }

            if(vertexMode != c_model.vertexMode) {
               throw new IllegalArgumentException("Cannot combine models with different vertex modes");
            }
         }

         c_model = newModel(vertexMode, numVerts);
         int i1 = 0;

         CCModel model;
         for(Iterator var6 = models.iterator(); var6.hasNext(); i1 += model.verts.length) {
            model = (CCModel)var6.next();
            copy(model, 0, c_model, i1, model.verts.length);
         }

         return c_model;
      }
   }

   public CCModel twoFacedCopy() {
      CCModel model = newModel(this.vertexMode, this.verts.length * 2);
      copy(this, 0, model, 0, this.verts.length);
      model.generateBackface(0, this.verts.length, this.verts.length);
      return model;
   }

   public CCModel copy() {
      CCModel model = newModel(this.vertexMode, this.verts.length);
      copy(this, 0, model, 0, this.verts.length);
      return model;
   }

   public Vector3 collapse() {
      Vector3 v = new Vector3();
      Vertex5[] var5 = this.verts;
      int var4 = this.verts.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Vertex5 vert = var5[var3];
         v.add(vert.vec);
      }

      v.multiply(1.0D / (double)this.verts.length);
      return v;
   }

   public CCModel zOffset(Cuboid6 offsets) {
      for(int k = 0; k < this.verts.length; ++k) {
         Vertex5 vert = this.verts[k];
         Vector3 normal = this.normals[k];
         switch(findSide(normal)) {
         case 0:
            vert.vec.y += offsets.min.y;
            break;
         case 1:
            vert.vec.y += offsets.max.y;
            break;
         case 2:
            vert.vec.z += offsets.min.z;
            break;
         case 3:
            vert.vec.z += offsets.max.z;
            break;
         case 4:
            vert.vec.x += offsets.min.x;
            break;
         case 5:
            vert.vec.x += offsets.max.x;
         }
      }

      return this;
   }

   public static int findSide(Vector3 normal) {
      return normal.y <= -0.99D?0:(normal.y >= 0.99D?1:(normal.z <= -0.99D?2:(normal.z >= 0.99D?3:(normal.x <= -0.99D?4:(normal.x >= 0.99D?5:-1)))));
   }

   public Cuboid6 bounds() {
      Vector3 vec1 = this.verts[0].vec;
      Cuboid6 c = new Cuboid6(vec1.copy(), vec1.copy());

      for(int i = 1; i < this.verts.length; ++i) {
         c.enclose(this.verts[i].vec);
      }

      return c;
   }

   private static class PositionNormalEntry {

      public Vector3 pos;
      public LinkedList normals = new LinkedList();


      public PositionNormalEntry(Vector3 position) {
         this.pos = position;
      }

      public boolean positionEqual(Vector3 v) {
         return this.pos.x == v.x && this.pos.y == v.y && this.pos.z == v.z;
      }

      public CCModel.PositionNormalEntry addNormal(Vector3 normal) {
         this.normals.add(normal);
         return this;
      }
   }
}
