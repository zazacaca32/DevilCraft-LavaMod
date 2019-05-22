package codechicken.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Profiler {

   public HashMap times = new HashMap();
   public String currentSection;
   private long startTime;
   private long totalTime;


   public void start(String section) {
      if(this.currentSection != null) {
         this.end();
      }

      this.currentSection = section;
      this.startTime = System.nanoTime();
   }

   public void end() {
      long time = System.nanoTime() - this.startTime;
      this.totalTime += time;
      Long prev = (Long)this.times.get(this.currentSection);
      if(prev == null) {
         prev = Long.valueOf(0L);
      }

      this.times.put(this.currentSection, Long.valueOf(prev.longValue() + time));
      this.currentSection = null;
   }

   public List getResults() {
      ArrayList results = new ArrayList(this.times.size());
      Iterator var3 = this.times.entrySet().iterator();

      while(var3.hasNext()) {
         Entry e = (Entry)var3.next();
         results.add(new Profiler.ProfilerResult((String)e.getKey(), ((Long)e.getValue()).longValue(), this.totalTime));
      }

      return results;
   }

   public void clear() {
      if(this.currentSection != null) {
         this.end();
      }

      this.times.clear();
      this.totalTime = 0L;
   }

   public static class ProfilerResult {

      public final String name;
      public final long time;
      public final double fraction;


      public ProfilerResult(String name, long time, long totalTime) {
         this.name = name;
         this.time = time;
         this.fraction = (double)time / (double)totalTime;
      }
   }
}
