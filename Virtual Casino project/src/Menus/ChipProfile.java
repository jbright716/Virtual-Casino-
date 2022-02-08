package Menus;

public class ChipProfile {
	// Tells if profile is "active" (has been started in the new game menu).
	private boolean profileActive;
	// Total number of chips (-9999 is the identifier for infinite chips).
	private int chipsTotal;
	// Name for profile.
	private String profileName;
	// Profile integer (used for menu when determining profile location).
	private int profInt;
	
	// Creates non-active profile for use at profile creation
	ChipProfile(int pInt, String pName){
		profInt = pInt;
		profileName = pName;
		chipsTotal = 0;
		profileActive = false;
	}
	
	// Creates active profile with chip value and profile integer location.
	ChipProfile(String pName, int chipStart, int pInt){
		profileName = pName;
		chipsTotal = chipStart;
		profileActive = true;
		profInt = pInt;
	}
	
	// Method for removing chips.
	public boolean removeChips(int numRemove) {
		if (chipsTotal == -9999) {
			return false;
		}
		chipsTotal = chipsTotal - numRemove;
		if (chipsTotal <= 0) {
			return true;
		}
		return false;
	}
	
	// Method for adding chips.
	public void addChips(int numAdd) {
		if (chipsTotal == -9999) {
			return;
		}
		chipsTotal = chipsTotal + numAdd;
		return;
	}
	
	// Return total chips.
	public int getTotalChips() {
		if (chipsTotal == -9999) {
			return 9999;
		}
		return chipsTotal;
	}
	
	// Return whether the profile is active.
	public boolean getProfileActive() {
		return profileActive;
	}
	
	// Return profile name.
	public String getProfileName() {
		return profileName;
	}
	
	// Return profile int.
		public int getProfileInt() {
			return profInt;
	}

	public void setChips(int cashOut) {
		// TODO Auto-generated method stub
		chipsTotal = cashOut;
	}
		
}
