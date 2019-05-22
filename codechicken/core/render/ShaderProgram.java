package codechicken.core.render;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.util.vector.Matrix4f;

public class ShaderProgram {

   int programID = ARBShaderObjects.glCreateProgramObjectARB();


   public ShaderProgram() {
      if(this.programID == 0) {
         throw new RuntimeException("Unable to allocate shader program object.");
      }
   }

   public void attach(int shaderType, String resource) {
      InputStream stream = ShaderProgram.class.getResourceAsStream(resource);
      if(stream == null) {
         throw new RuntimeException("Unable to locate resource: " + resource);
      } else {
         this.attach(shaderType, stream);
      }
   }

   public void use() {
      ARBShaderObjects.glUseProgramObjectARB(this.programID);
   }

   public static void restore() {
      ARBShaderObjects.glUseProgramObjectARB(0);
   }

   public void link() {
      ARBShaderObjects.glLinkProgramARB(this.programID);
      if(ARBShaderObjects.glGetObjectParameteriARB(this.programID, '\u8b82') == 0) {
         throw new RuntimeException("Error linking program: " + getInfoLog(this.programID));
      } else {
         ARBShaderObjects.glValidateProgramARB(this.programID);
         if(ARBShaderObjects.glGetObjectParameteriARB(this.programID, '\u8b83') == 0) {
            throw new RuntimeException("Error validating program: " + getInfoLog(this.programID));
         } else {
            this.use();
            this.onLink();
            restore();
         }
      }
   }

   public void attach(int shaderType, InputStream stream) {
      if(stream == null) {
         throw new RuntimeException("Invalid shader inputstream");
      } else {
         byte shaderID = 0;

         try {
            int shaderID1 = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
            if(shaderID1 == 0) {
               throw new RuntimeException("Unable to allocate shader object.");
            } else {
               try {
                  ARBShaderObjects.glShaderSourceARB(shaderID1, asString(stream));
               } catch (IOException var5) {
                  throw new RuntimeException("Error reading inputstream.", var5);
               }

               ARBShaderObjects.glCompileShaderARB(shaderID1);
               if(ARBShaderObjects.glGetObjectParameteriARB(shaderID1, '\u8b81') == 0) {
                  throw new RuntimeException("Error compiling shader: " + getInfoLog(shaderID1));
               } else {
                  ARBShaderObjects.glAttachObjectARB(this.programID, shaderID1);
               }
            }
         } catch (RuntimeException var6) {
            ARBShaderObjects.glDeleteObjectARB(shaderID);
            throw var6;
         }
      }
   }

   public static String asString(InputStream stream) throws IOException {
      StringBuilder sb = new StringBuilder();
      BufferedReader bin = new BufferedReader(new InputStreamReader(stream));

      String line;
      while((line = bin.readLine()) != null) {
         sb.append(line).append('\n');
      }

      stream.close();
      return sb.toString();
   }

   private static String getInfoLog(int shaderID) {
      return ARBShaderObjects.glGetInfoLogARB(shaderID, ARBShaderObjects.glGetObjectParameteriARB(shaderID, '\u8b84'));
   }

   public int getUniformLoc(String name) {
      return ARBShaderObjects.glGetUniformLocationARB(this.programID, name);
   }

   public int getAttribLoc(String name) {
      return ARBVertexShader.glGetAttribLocationARB(this.programID, name);
   }

   public void uniformTexture(String name, int textureIndex) {
      ARBShaderObjects.glUniform1iARB(this.getUniformLoc(name), textureIndex);
   }

   public void onLink() {}

   public void glVertexAttributeMat4(int loc, Matrix4f matrix) {
      ARBVertexShader.glVertexAttrib4fARB(loc, matrix.m00, matrix.m01, matrix.m02, matrix.m03);
      ARBVertexShader.glVertexAttrib4fARB(loc + 1, matrix.m10, matrix.m11, matrix.m12, matrix.m13);
      ARBVertexShader.glVertexAttrib4fARB(loc + 2, matrix.m20, matrix.m21, matrix.m22, matrix.m23);
      ARBVertexShader.glVertexAttrib4fARB(loc + 3, matrix.m30, matrix.m31, matrix.m32, matrix.m33);
   }
}
