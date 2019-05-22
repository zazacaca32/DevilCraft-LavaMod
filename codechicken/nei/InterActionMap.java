package codechicken.nei;


public enum InterActionMap {

   TIME("TIME", 0, false),
   CREATIVE("CREATIVE", 1, false),
   RAIN("RAIN", 2, false),
   ITEM("ITEM", 3, false),
   HEAL("HEAL", 4, false),
   DELETE("DELETE", 5, true),
   MAGNET("MAGNET", 6, true),
   ENCHANT("ENCHANT", 7, true),
   POTION("POTION", 8, true);
   public boolean requiresSMPCounterpart;
   public static final int protocol = 5;
   // $FF: synthetic field
   private static final InterActionMap[] ENUM$VALUES = new InterActionMap[]{TIME, CREATIVE, RAIN, ITEM, HEAL, DELETE, MAGNET, ENCHANT, POTION};


   private InterActionMap(String var1, int var2, boolean requiresSMPCounterpart) {
      this.requiresSMPCounterpart = requiresSMPCounterpart;
   }

   public static InterActionMap getAction(String name) {
      return valueOf(name.toUpperCase());
   }

   public String getName() {
      return this.name().toLowerCase();
   }
}
