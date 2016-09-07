package br.thejungle.environment.things;

import java.util.Random;
import java.util.logging.Logger;

import br.thejungle.environment.things.info.PlantInfo;
import br.thejungle.environment.things.info.ThingInfo;

/**
 * This implementation represents a plant food
 */
public class Plant extends Food {

	private PlantInfo plantInfo;
	private short maxRichness, maxStepGrowth;
	private int maxGrowTimes;
	private int growTimes;
	private static Random random = new Random();

	private static Logger logger = Logger.getLogger("br.thejungle.environment.things.Plant");

	public static final float RICHNESS_SIZE_RATE = 0.1F;

	public Plant(short maxRichness, short maxStepGrowth, int maxGrowTimes) {
		super((short) 5, (short) 0);
		this.maxRichness = maxRichness;
		this.maxStepGrowth = maxStepGrowth;
		this.plantInfo = new PlantInfo(this);
		this.maxGrowTimes = maxGrowTimes;
		this.growTimes = 0;
	}

	public PlantInfo getPlantInfo() {
		return plantInfo;
	}

	public void timeElapsed() {
		if (getRichness() < maxRichness && growTimes <= maxGrowTimes) {
			short g = (short) (random.nextFloat() * maxStepGrowth);
			setRichness((short) (getRichness() + g));
			setSize((short) (getRichness() * RICHNESS_SIZE_RATE));
			growTimes++;
			logger.fine("Growing plant by " + g + " (maxStepGrowth=" + maxStepGrowth + "; growTimes="+ growTimes +"; maxGrowTimes="+ maxGrowTimes +")");
		}
	}

	public boolean isValid() {
		return (getRichness() > 0);
	}

	public ThingInfo getThingInfo() {
		return new PlantInfo(this);
	}

	public String toString() {
		return super.toString() + "; maxRichness=" + maxRichness
				+ "; maxStepGrowth=" + maxStepGrowth + "; maxGrowTimes="
				+ maxGrowTimes + "; growTimes=" + growTimes;
	}

}
