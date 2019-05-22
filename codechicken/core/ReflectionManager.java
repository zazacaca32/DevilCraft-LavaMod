package codechicken.core;

import codechicken.core.asm.ObfuscationMappings;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ReflectionManager {

   public static HashMap primitiveWrappers = new HashMap();


   static {
      primitiveWrappers.put(Integer.TYPE, Integer.class);
      primitiveWrappers.put(Short.TYPE, Short.class);
      primitiveWrappers.put(Byte.TYPE, Byte.class);
      primitiveWrappers.put(Long.TYPE, Long.class);
      primitiveWrappers.put(Double.TYPE, Double.class);
      primitiveWrappers.put(Float.TYPE, Float.class);
      primitiveWrappers.put(Boolean.TYPE, Boolean.class);
      primitiveWrappers.put(Character.TYPE, Character.class);
   }

   public static boolean isInstance(Class class1, Object obj) {
      Class primitive = (Class)primitiveWrappers.get(class1);
      return primitive != null?(primitive == Long.class && Long.class.isInstance(obj)?true:((primitive == Long.class || primitive == Integer.class) && Integer.class.isInstance(obj)?true:((primitive == Long.class || primitive == Integer.class || primitive == Short.class) && Short.class.isInstance(obj)?true:((primitive == Long.class || primitive == Integer.class || primitive == Short.class || primitive == Byte.class) && Integer.class.isInstance(obj)?true:(primitive == Double.class && Double.class.isInstance(obj)?true:((primitive == Double.class || primitive == Float.class) && Float.class.isInstance(obj)?true:primitive.isInstance(obj))))))):class1.isInstance(obj);
   }

   public static Class findClass(String name) {
      return findClass(name, true);
   }

   public static boolean classExists(String name) {
      return findClass(name, false) != null;
   }

   public static Class findClass(String name, boolean init) {
      try {
         return Class.forName(name, init, ReflectionManager.class.getClassLoader());
      } catch (ClassNotFoundException var5) {
         try {
            return Class.forName("net.minecraft.src." + name, init, ReflectionManager.class.getClassLoader());
         } catch (ClassNotFoundException var4) {
            return null;
         }
      }
   }

   public static void setField(Class class1, Object instance, String name, Object value) throws IllegalAccessException, IllegalArgumentException {
      setField(class1, instance, new String[]{name}, value);
   }

   public static void setField(Class class1, Object instance, String[] names, Object value) throws IllegalAccessException, IllegalArgumentException {
      Field[] var7;
      int var6 = (var7 = class1.getDeclaredFields()).length;
      int var5 = 0;

      while(var5 < var6) {
         Field field = var7[var5];
         boolean match = false;
         String[] var12 = names;
         int var11 = names.length;
         int var10 = 0;

         while(true) {
            if(var10 < var11) {
               String name = var12[var10];
               if(!field.getName().equals(name)) {
                  ++var10;
                  continue;
               }

               match = true;
            }

            if(match) {
               field.setAccessible(true);
               field.set(instance, value);
               return;
            }

            ++var5;
            break;
         }
      }

   }

   public static void setField(Class class1, Object instance, int fieldindex, Object value) throws IllegalAccessException, IllegalArgumentException {
      Field field = class1.getDeclaredFields()[fieldindex];
      field.setAccessible(true);
      field.set(instance, value);
   }

   public static void callMethod(Class class1, String name, Object ... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      callMethod(class1, (Class)null, new String[]{name}, params);
   }

   public static void callMethod(Class class1, String[] names, Object ... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      callMethod(class1, (Class)null, names, params);
   }

   public static void callMethod(Class class1, Object instance, String name, Object ... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      callMethod(class1, (Class)null, instance, new String[]{name}, params);
   }

   public static void callMethod(Class class1, Object instance, String[] names, Object ... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      callMethod(class1, (Class)null, instance, names, params);
   }

   public static Object callMethod(Class class1, Class returntype, String name, Object ... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      return callMethod(class1, returntype, (Object)null, new String[]{name}, params);
   }

   public static Object callMethod(Class class1, Class returntype, String[] names, Object ... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      return callMethod(class1, returntype, (Object)null, names, params);
   }

   public static Object callMethod(Class class1, Class returntype, Object instance, String name, Object ... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      return callMethod(class1, returntype, instance, new String[]{name}, params);
   }

   public static Object callMethod(Class class1, Class returntype, Object instance, String[] names, Object ... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      Method[] var8;
      int var7 = (var8 = class1.getDeclaredMethods()).length;
      int var6 = 0;

      while(var6 < var7) {
         Method method = var8[var6];
         boolean match = false;
         String[] var13 = names;
         int var12 = names.length;
         int i = 0;

         while(true) {
            if(i < var12) {
               String paramtypes = var13[i];
               if(!method.getName().equals(paramtypes)) {
                  ++i;
                  continue;
               }

               match = true;
            }

            if(match) {
               Class[] var14 = method.getParameterTypes();
               if(var14.length == params.length) {
                  i = 0;

                  while(true) {
                     if(i >= params.length) {
                        method.setAccessible(true);
                        return method.invoke(instance, params);
                     }

                     if(!isInstance(var14[i], params[i])) {
                        break;
                     }

                     ++i;
                  }
               }
            }

            ++var6;
            break;
         }
      }

      return null;
   }

   public static Object getField(Class class1, Class fieldType, Object instance, int fieldIndex) throws IllegalAccessException, IllegalArgumentException {
      Field field = class1.getDeclaredFields()[fieldIndex];
      field.setAccessible(true);
      return field.get(instance);
   }

   public static Object getField(Class class1, Class fieldType, Object instance, String fieldName) {
      try {
         Field e = class1.getDeclaredField(fieldName);
         e.setAccessible(true);
         return e.get(instance);
      } catch (Exception var5) {
         throw new RuntimeException(var5);
      }
   }

   public static Object newInstance(Class class1, Object ... params) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
      Constructor[] var5;
      int var4 = (var5 = class1.getDeclaredConstructors()).length;

      label27:
      for(int var3 = 0; var3 < var4; ++var3) {
         Constructor constructor = var5[var3];
         Class[] paramtypes = constructor.getParameterTypes();
         if(paramtypes.length == params.length) {
            for(int i = 0; i < params.length; ++i) {
               if(!isInstance(paramtypes[i], params[i])) {
                  continue label27;
               }
            }

            constructor.setAccessible(true);
            return constructor.newInstance(params);
         }
      }

      return null;
   }

   public static boolean hasField(Class class1, String fieldName) {
      try {
         class1.getDeclaredField(fieldName);
         return true;
      } catch (NoSuchFieldException var3) {
         return false;
      }
   }

   public static Object get(Field field, Class class1) {
      return get(field, class1, (Object)null);
   }

   public static Object get(Field field, Class class1, Object instance) {
      try {
         return field.get(instance);
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public static void set(Field field, Object value) {
      set(field, (Object)null, value);
   }

   public static void set(Field field, Object instance, Object value) {
      try {
         field.set(instance, value);
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public static Field getField(ObfuscationMappings.DescriptorMapping mapping) {
      mapping.toRuntime();

      try {
         Class e = ReflectionManager.class.getClassLoader().loadClass(mapping.javaClass());
         Field field = e.getDeclaredField(mapping.s_name);
         field.setAccessible(true);
         return field;
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }
}
