package codechicken.core;


public class ArrayUtils {

   public static int[] byteArrayToIntArray(byte[] ab) {
      if(ab.length % 4 != 0) {
         byte[] ai = new byte[(ab.length / 4 + 1) * 4];
         System.arraycopy(ab, 0, ai, 0, ab.length);
         ab = ai;
      }

      int[] var3 = new int[ab.length / 4];

      for(int i = 0; i < var3.length; ++i) {
         var3[i] = ((ab[i * 4] & 255) << 24) + ((ab[i * 4 + 1] & 255) << 16) + ((ab[i * 4 + 2] & 255) << 8) + (ab[i * 4 + 3] & 255);
      }

      return var3;
   }

   public static byte[] intArrayToByteArray(int[] ai) {
      byte[] ab = new byte[ai.length * 4];

      for(int i = 0; i < ai.length; ++i) {
         ab[i * 4] = (byte)(ai[i] >> 24);
         ab[i * 4 + 1] = (byte)(ai[i] >> 16);
         ab[i * 4 + 2] = (byte)(ai[i] >> 8);
         ab[i * 4 + 3] = (byte)ai[i];
      }

      return ab;
   }

   public static int hashCode(int[] ai) {
      int hashcode = 0;
      int[] var5 = ai;
      int var4 = ai.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         int i = var5[var3];
         hashcode = hashcode * 31 + i;
      }

      return hashcode;
   }

   public static int hashCode(byte[] ab) {
      int hashcode = 0;
      byte[] var5 = ab;
      int var4 = ab.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         byte b = var5[var3];
         hashcode = hashcode * 31 + b;
      }

      return hashcode;
   }

   public static String toString(byte[] ab) {
      StringBuilder stringbuild = (new StringBuilder()).append("[");
      boolean first = true;
      byte[] var6 = ab;
      int var5 = ab.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         byte b = var6[var4];
         if(first) {
            first = false;
         } else {
            stringbuild.append(",");
         }

         stringbuild.append(b);
      }

      return stringbuild.append("]").toString();
   }

   public static String toString(int[] ai) {
      StringBuilder stringbuild = (new StringBuilder()).append("[");
      boolean first = true;
      int[] var6 = ai;
      int var5 = ai.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         int i = var6[var4];
         if(first) {
            first = false;
         } else {
            stringbuild.append(",");
         }

         stringbuild.append(i);
      }

      return stringbuild.append("]").toString();
   }
}
