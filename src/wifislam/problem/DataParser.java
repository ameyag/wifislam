package wifislam.problem;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataParser {



	public static ArrayList<LocationCoordinates> parse()
	{
		String path = "input\\guesses.txt";
		String data = Utils.readFiletoString(path);
		
		ArrayList<LocationCoordinates> guessed_locations = googleParser(data);

		return guessed_locations;

	}

	public static ArrayList<LocationCoordinates> googleParser(String data)
	{
		ArrayList<LocationCoordinates> coordinates = new ArrayList<LocationCoordinates>();

		ArrayList<Double> xcoordinates = new ArrayList<Double>();
		ArrayList<Double> ycoordinates = new ArrayList<Double>();

		JSONParser parser=new JSONParser();

		//			System.out.println("=======decode=======");

		String s="[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
		Object obj;
		try {
			obj = parser.parse(data);

			JSONArray array=(JSONArray)obj;
			//				System.out.println("======the 2nd element of array======");
			//				System.out.println(array.get(1));
			//				System.out.println();

			JSONObject obj2=(JSONObject)array.get(1);
			//				System.out.println("======field \"1\"==========");
			//				System.out.println(obj2.get("1"));    

			for(int i=0;i<array.size();i++)
			{
				String patternToDiscard="[{]|[\"]|[:]|[,]";
				String[] locationString = array.get(i).toString().split(patternToDiscard);
				xcoordinates.add(Double.parseDouble(locationString[5]));
				ycoordinates.add(Double.parseDouble(locationString[11]));

				LocationCoordinates tempLocation = new LocationCoordinates();
				tempLocation.xcoordinate = Double.parseDouble(locationString[5]);
				tempLocation.ycoordinate = Double.parseDouble(locationString[11]);
				coordinates.add(tempLocation);
			}

			return coordinates;
			//
			//				s="{}";
			//				obj=parser.parse(s);
			//				System.out.println(obj);
			//
			//				s="[5,]";
			//				obj=parser.parse(s);
			//				System.out.println(obj);
			//
			//				s="[5,,2]";
			//				obj=parser.parse(s);
			//				System.out.println(obj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return coordinates;
	}
}


