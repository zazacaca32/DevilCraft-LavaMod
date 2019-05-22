package codechicken.nei;

import java.util.HashMap;
import java.util.HashSet;

public class AllowedPropertyMap {

   public static HashMap idToNameMap = new HashMap();
   public static HashMap idToFeatureClassMap = new HashMap();
   public static HashMap nameToIDMap = new HashMap();
   public static HashSet nameSet = new HashSet();
   private static int counter;


   static {
      addAction("dawn", "time");
      addAction("noon", "time");
      addAction("dusk", "time");
      addAction("midnight", "time");
      addAction("rain", "rain");
   }

   public static void addAction(String actionName, String featureClass) {
      idToNameMap.put(Integer.valueOf(counter), actionName);
      idToFeatureClassMap.put(Integer.valueOf(counter), featureClass);
      nameToIDMap.put(actionName, Integer.valueOf(counter));
      nameSet.add(actionName);
      ++counter;
   }
}
