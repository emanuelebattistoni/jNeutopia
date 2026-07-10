package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
	//player stats
	int maxLife;
	int life;
	int coin;
	int bomb;
	//inventory
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmounts = new ArrayList <>();
	//Object on map
	String mapObjectNames[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectLootNames[][];
	boolean mapObjectOpened[][];
}
