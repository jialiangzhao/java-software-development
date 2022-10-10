package model;

import java.util.HashMap;

public class Menu {
	
	private HashMap<String, MenuItem> items;
	
	public Menu() {
		items = new HashMap<>();
		items.put("AlexsAnchovy", new AlexsAnchovy());
		items.put("AustensAustere", new AustensAustere());
		items.put("AkshithsBBQChicken", new AkshithsBBQChicken());
		items.put("MatthewsMargarita", new MatthewsMargarita());
		items.put("ThomassToasted", new ThomassToasted());
		items.put("MinhsMelt", new MinhsMelt());
		items.put("MichaelsMushroom", new MichaelsMushroom());
	}
	
	public double getPrice(String in) {
		return items.get(in).getPrice();
	}
	
	public String getQuip(String in) {
		return items.get(in).toString();
	}
	
	public String toString() {
		String ret = "Menu:\n";

		for(String item: items.keySet()) {
			ret += "  " + item + "\n";
		}
		return ret;
	}
}
