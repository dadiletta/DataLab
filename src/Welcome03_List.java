import noaa.Feed;
import java.util.ArrayList;
import java.util.Scanner;

public class Welcome03_List {
   public static void main(String[] args) {
      Feed index = Feed.load("https://forecast.weather.gov/xml/current_obs/index.xml");
      ArrayList<WeatherStation> allstns = new ArrayList<WeatherStation>();
      for (Feed stn : index.getAll("station")) {
         allstns.add(new WeatherStation(stn.getString("station_name"), stn.getString("station_id"),
                                        stn.getString("state"), stn.getDouble("latitude"),
                                        stn.getDouble("longitude")));
      }
      System.out.println("Total stations: " + allstns.size());
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a state abbreviation: ");
      String state = sc.next();
      System.out.println("Stations in " + state);
      int count = 0;
      for (WeatherStation ws : allstns) {
         if (ws.isLocatedInState(state)) {
           count++;
            System.out.println("  " + ws.getId() + ": " + ws.getName());
         }
      }
      System.out.println("There are "+ count + " stations in "+ state + ".");
      WeatherStation southern = allstns.get(0);
     for (WeatherStation ws : allstns) {
         if (ws.getLat() < southern.getLat()) 
         {
           southern = ws;
         }
      }
     System.out.println(southern.getName()+ " Latitude: "+southern.getLat());
   }
}