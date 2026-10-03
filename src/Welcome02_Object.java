
import noaa.Feed;

public class Welcome02_Object {
   public static void main(String[] args) {
      String id1 = "KATL";
      Feed feed1 = Feed.load("https://forecast.weather.gov/xml/current_obs/" + id1 + ".xml");
      Observation ob1 = new Observation(feed1.getString("weather"), feed1.getFloat("temp_f"),
                                        feed1.getInt("wind_degrees"));
      System.out.println(id1 + ": " + ob1);

      String id2 = "KSAV";
      Feed feed2 = Feed.load("https://forecast.weather.gov/xml/current_obs/" + id2 + ".xml");
      Observation ob2 = new Observation(feed2.getString("weather"), feed2.getFloat("temp_f"),
                                        feed2.getInt("wind_degrees"));
      System.out.println(id2 + ": " + ob2);

      String id3 = "KSEA";
      Feed feed3 = Feed.load("https://forecast.weather.gov/xml/current_obs/" + id3 + ".xml");
      Observation ob3 = new Observation(feed3.getString("weather"), feed3.getFloat("temp_f"),
                                        feed3.getInt("wind_degrees"));
      System.out.println(id3 + ": " + ob3);

      if (ob1.colderThan(ob2) && ob1.colderThan(ob3)) {
         System.out.println("Colder at " + id1);
      } else {
         if(ob2.colderThan(ob3))
         {
         System.out.println("Colder at " + id2);
         }
         else
         {
            System.out.println("Colder at " + id3);
         }
      }
   }
}


/* Represents a weather observation */
class Observation {
   float temp;    // in fahrenheit
   int windDir;   // in degrees
   String description;
   
   Observation(String description, float temp, int windDir) {
      this.description = description;
      this.temp = temp;
      this.windDir = windDir;
   }
   
   /* determine if the temperature of this observation is colder than 'that's */
   public boolean colderThan(Observation that) {
      return this.temp < that.temp;
   }
   
   /* produce a string describing this observation */
   public String toString() {
      return (temp + " degrees; " + description + " (wind: " + windDir + " degrees)");
   }
}
