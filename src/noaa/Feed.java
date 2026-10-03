package noaa;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Reads an XML feed from the internet, such as the National Weather Service's
 * current conditions, and hands you its values by name.
 *
 * You run this; you are not asked to write anything like it. None of the
 * networking here is on the AP exam. What is on the exam is what the Welcome
 * files do with the values once they have them.
 *
 *   Feed seattle = Feed.load("https://forecast.weather.gov/xml/current_obs/KSEA.xml");
 *   double temp = seattle.getDouble("temp_f");
 *
 * Only standard Java is used, so it runs on Java 8 and anything newer.
 */
public class Feed {

   private final Element element;

   private Feed(Element element) {
      this.element = element;
   }

   /** Downloads the XML at this address and reads it. */
   public static Feed load(String address) {
      try {
         HttpURLConnection conn = (HttpURLConnection) URI.create(address).toURL().openConnection();
         conn.setConnectTimeout(15000);
         conn.setReadTimeout(30000);
         // The Weather Service asks every program that reads its feeds to say what it is.
         conn.setRequestProperty("User-Agent", "DataLab (AP Computer Science A starter)");
         int status = conn.getResponseCode();
         if (status != 200) {
            throw new IllegalStateException("The feed at " + address + " answered " + status + ", not 200.");
         }
         try (InputStream in = conn.getInputStream()) {
            return new Feed(parser().parse(in).getDocumentElement());
         }
      } catch (IOException e) {
         throw new IllegalStateException("Could not read " + address + ". Are you online? (" + e.getMessage() + ")", e);
      } catch (IllegalArgumentException e) {
         throw new IllegalStateException("\"" + address + "\" is not a web address. Is there a space in it?", e);
      } catch (org.xml.sax.SAXException | javax.xml.parsers.ParserConfigurationException e) {
         throw new IllegalStateException("The page at " + address + " is not XML this can read.", e);
      }
   }

   /** The text of the first element with this name, or "" when there is none. */
   public String getString(String name) {
      NodeList found = element.getElementsByTagName(name);
      return found.getLength() == 0 ? "" : found.item(0).getTextContent().trim();
   }

   /** The first element with this name as a number, or NaN when it is missing or not a number. */
   public double getDouble(String name) {
      try {
         return Double.parseDouble(getString(name));
      } catch (NumberFormatException e) {
         return Double.NaN;
      }
   }

   /** The same as getDouble, as a float. */
   public float getFloat(String name) {
      return (float) getDouble(name);
   }

   /** The first element with this name as a whole number, or 0 when it is missing or not a number. */
   public int getInt(String name) {
      double value = getDouble(name);
      return Double.isNaN(value) ? 0 : (int) Math.round(value);
   }

   /** Every element with this name, each one a Feed of its own to read values from. */
   public List<Feed> getAll(String name) {
      NodeList found = element.getElementsByTagName(name);
      List<Feed> all = new ArrayList<Feed>();
      for (int i = 0; i < found.getLength(); i++) {
         Node node = found.item(i);
         if (node instanceof Element) {
            all.add(new Feed((Element) node));
         }
      }
      return all;
   }

   // A feed is somebody else's file, so it is not allowed to make this program
   // fetch anything else (no external entities, no outside DTDs).
   private static DocumentBuilder parser() throws javax.xml.parsers.ParserConfigurationException {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      factory.setExpandEntityReferences(false);
      DocumentBuilder builder = factory.newDocumentBuilder();
      // Report a broken page through load()'s message, not as a stray line of its own.
      builder.setErrorHandler(new org.xml.sax.helpers.DefaultHandler());
      return builder;
   }
}
