package model;

public class PunchCard {
	private int numVisites;
	public PunchCard() {
		numVisites=0;
	}
	public void addVisits() {
		numVisites++;
	}
	public boolean isNthVisit(int n) {
		if(numVisites%n==0) {
			return true;
		}
		return false;
	}
}
