package thermalproject;

public class CPU {

	double currentTemp;
//probably best to work in Kelvin, but other teams might find it easier to work in Celsius.
//current figures computed in Kelvin, but printed in Celsius
//if unit time is 10 seconds, need to work with double, as very small increments in temperature in
//this timeframe
	int intensity;
	//intensity should be between 5-100
//may not be “intensity", but some input to determine activity, and therefore heat production
	//may need to change to float if intensity very variable. PLACEHOLDER: data type
	boolean isAlive;
	//not yet used
	String rack;
	//rack should be an enum PLACEHOLDER: data type
	public int pos;
	public String name;
	double default_temp = 293.15;
	int default_intensity = 25;
	//actual default intensity should be about 5, to reflect activity just by being on PLACEHOLDER: specifics
	double coolingPlan1 = 293.15;
	double coolingPlan2 = 283.15;
	double coolingPlan3 = 278.15;

	public CPU() {
		pos = 0;
		rack = "z";
		currentTemp = default_temp;
		name = "z" + "0";
		isAlive = true;
		intensity = default_intensity;

	}

	public CPU(int p, String r) {
		pos = p;
		rack = r;
		currentTemp = default_temp;
		isAlive = true;
		intensity = default_intensity;
		name = r + Integer.toString(p);
	}

	public CPU(int p, String r, double t, int i, boolean b) {
		currentTemp = t;
		intensity = i;
		isAlive = b;
		pos = p;
		rack = r;
		name = r + Integer.toString(p);
	}

	public int getPos() {
		return pos;
	}

	public String getName() {
		return name;
	}

	public double getTemp() {
		return currentTemp;
	}

	public void setTemp(double i) {
		currentTemp = i;
	}

	public int getIntensity() {
		return intensity;
	}

	public boolean getIsAlive() {
		return isAlive;
	}

	public void setIntensity(int i) {
		intensity = i;
	}

	public double heatDiff(CPU cpu) {
		double ans, a, b;
		a = this.getTemp();
		b = cpu.getTemp();
		ans = a - b;
		return ans;
	}

	public void generateHeat() {
		double generated = (double) intensity / 1000;
		currentTemp += generated;
		//generate heat should not be based on currentTemp but only intensity. PLACEHOLDER: maths
	}

	public void radiate(CPU other) //may need no input method for top CPUs in racks. not sure. PLACEHOLDER: stub
	{
		double diff = this.heatDiff(other);
		double thisTemp = this.getTemp();
		double otherTemp = other.getTemp();

		if (diff < 0) {
			//transfer some small amount of heat "down" to lower (this) CPU
			//PLACEHOLDER: maths
			double trans = diff / 50;
			this.setTemp(thisTemp - trans);
			other.setTemp(otherTemp + trans);
		} else if (diff > 0) {
			//transfer proportionally more heat "up" from this CPU to other CPU
			//PLACEHOLDER: maths
			double trans = diff / 20;
			this.setTemp(thisTemp - trans);
			//this CPU loses more heat to surronding area than transferred up, meaning some
			//heat is lost to surronding area, otherCPU recieves less than full amount of heat. PLACEHOLDER: maths
			other.setTemp(otherTemp + (trans / 1.2));
		} else {
			//do nothing
		}
	}

	public void cool(int i) {
		double diff = 0;
		if (i == 0) {
			diff = 0;
		} else if (i == 1) {
			diff = currentTemp - coolingPlan1;
		} else if (i == 2) {
			diff = currentTemp - coolingPlan2;
		} else if (i == 3) {
			diff = currentTemp - coolingPlan3;
		}
		if (diff == 0) {
		} else if (diff < 0) {
			//do nothing for now. may want to slightly heat CPUs, but this call is quite unlikely
		} else if (diff > 0) {
			currentTemp -= (diff / 200);
			//PLACEHOLDER: maths
		}
	}

	public void run(int i, CPU other) {
		this.generateHeat();
		this.radiate(other);
		this.cool(i);
	}

	public void run(int i) {
		this.generateHeat();
		this.cool(i);
	}
}