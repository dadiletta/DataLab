import noaa.Feed;

public class Welcome01 {
   public static void main(String[] args) {
      String id = "KSEA";
      Feed obs = Feed.load("https://forecast.weather.gov/xml/current_obs/" + id + ".xml");
      float temp = obs.getFloat("temp_f");
      String loc = obs.getString("location");
      System.out.println("The temperature at " + loc + " is " + temp + "F");
   }
}
