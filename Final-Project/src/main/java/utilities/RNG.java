package utilities;

import java.util.Random;

public class RNG {
	public static int generateInt(int min, int max)
	{
		Random rng = new Random();
		int num = rng.nextInt(max + 1 - min) + min;
		return num;
	}
	public static void validateMinandMax(double min, double max)
	{
		if(min>max)
		{
		throw new IllegalArgumentException("min cannot be greater than max");
		}
	}
}
