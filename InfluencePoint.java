package Williamsgayass;
import java.util.*;
public class InfluencePoint 
{
	public static void main(String[] args)
	{
		Random util = new Random();
		comparison(1000,util);
	}
	public static double PercentagePoints(double[][] dataspace)
	{
		double totalutility = 0;
		for (int i = 0; i < dataspace[0].length - 1; i ++)
		{
			double test = 0;
			double unadjusted = 0;
			for (int j = 0; j < dataspace.length; j++)
			{
				test = test + (dataspace[j][i] / dataspace[j][dataspace[0].length - 1]);
				unadjusted += dataspace[j][i];
			}
			if (test > 0)
			{
				totalutility += unadjusted;
			//	System.out.println(i);
			}
		}
	//	System.out.println(totalutility);
		//System.out.println("");
		return totalutility;
	}
	public static double Democracy(double[][] dataspace)
	{
		double totalutility = 0;
		for (int i = 0; i < dataspace[0].length - 1; i ++)
		{
			int yes = 0;
			int no = 0;
			double unadjusted = 0;
			for (int j = 0; j < dataspace.length; j++)
			{
				if (dataspace[j][i] > 0)
				{
					yes++;
				}
				else
				{
					no++;
				}
				unadjusted += dataspace[j][i];
			}
			if (yes > no)
			{
				totalutility += unadjusted;
				//System.out.println(i);
			}
		}
		//System.out.println(totalutility);
		//System.out.println("");
		return totalutility;
	}
	public static double[][] fill(int Participants, int Events, Random Determiner)
	{
		double[][] filled = new double[Participants][Events + 1];
		for (int i = 0; i < Participants; i++)
		{
			double store = 0;
			for (int j = 0; j < Events / 2; j++)
			{
				double temp = Determiner.nextGaussian();
				filled[i][j] = temp;
				store = store + Math.abs(temp);
				//System.out.print(temp + " ");
			}
			for (int j = Events / 2; j < Events; j++)
			{
				double temp = Determiner.nextGaussian() *1000.0;
				filled[i][j] = temp;
				store = store + Math.abs(temp);
				//System.out.print(temp + " ");
			}
			filled[i][Events] = store;
			//System.out.println(store);
		}
		return filled;
	}
	public static void comparison(int counter, Random util)
	{
		double DemocracyW = 0;
		double InfluenceW = 0;
		for (int i = 0; i < counter; i++)
		{
			double[][] filled = fill(1000,5,util);
			double Democracyhold = Democracy(filled);
			double Influencehold = PercentagePoints(filled);
			if (Democracyhold > Influencehold)
			{
				DemocracyW++;
			}
			if (Influencehold > Democracyhold)
			{
				InfluenceW++;
			}
		}
		System.out.println("Democracy Standard: " + DemocracyW + ", Influence: " + InfluenceW + ", Total Simulations: " + counter);
		System.out.println("Influence Outperformance Rate: " + InfluenceW / DemocracyW + ", Equal: " + (counter-InfluenceW-DemocracyW));
	}
}
