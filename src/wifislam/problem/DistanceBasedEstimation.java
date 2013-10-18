package wifislam.problem;

import java.util.ArrayList;

public class DistanceBasedEstimation {
	public static LocationCoordinates getBestLocationEstimate(ArrayList<LocationCoordinates> coordinates)
	{
		
		for (LocationCoordinates currentCoordinates : coordinates)
		{
			Double currPointX = currentCoordinates.xcoordinate;
			Double currPointY = currentCoordinates.ycoordinate;
			Double distanceSum = 0.0;
			
			for(LocationCoordinates otherCoordinates: coordinates)
			{
				distanceSum+=getDistance(currPointX,currPointY,
							otherCoordinates.xcoordinate,otherCoordinates.ycoordinate);
			}
			
			currentCoordinates.distanceFromOtherPointsSum = distanceSum;
			
		}
		
		Double minimumDistance = 10000.0;
		LocationCoordinates bestEstimateLocation = new LocationCoordinates();
		for(LocationCoordinates currentCoordinates : coordinates)
		{
			if(currentCoordinates.distanceFromOtherPointsSum!=0.0)
			{
				if(currentCoordinates.distanceFromOtherPointsSum<minimumDistance)
				{
					minimumDistance = currentCoordinates.distanceFromOtherPointsSum;
					bestEstimateLocation.xcoordinate=currentCoordinates.xcoordinate;
					bestEstimateLocation.ycoordinate = currentCoordinates.ycoordinate;
				}
			}
		}
		
		return bestEstimateLocation;
	}
	
	public static Double getDistance(Double x1, Double y1, Double x2, Double y2)
	{
		return Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)));
	}
}
