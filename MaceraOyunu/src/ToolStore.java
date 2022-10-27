
public class ToolStore extends NormalLoc {

	public ToolStore(Player player) {
		super(player, "Ma�aza");
	}
	
	@Override
	public boolean onLocation() {
		System.out.println("---------- Ma�azaya Ho�geldiniz ----------");
		boolean showMenu = true;
		while(showMenu) {
			System.out.println("1 - Silahlar");
			System.out.println("2 - Z�rhlar");
			System.out.println("3 - ��k�� Yap");
			System.out.print("Se�iminiz : ");
			int selectCase = input.nextInt();
			while(selectCase < 1 || selectCase > 3) {
				System.out.println("Ge�ersiz de�er, tekrar giriniz : ");
				selectCase = input.nextInt();
			}
			
			switch(selectCase) {
			case 1:
				printWeapon();
				buyWeapon();
				break;
			case 2:
				printArmor();
				buyArmor();
				break;
			case 3:
				System.out.println("Bir daha bekleriz :)");
				showMenu = false;
				break;
			}
		}
		return true;
	}
	
	public void printWeapon() {
		System.out.println("-------- Silahlar --------");
		for(Weapon w : Weapon.weapons()) {
			System.out.println(
			w.getId() + " - " +
			w.getName() + " < Para : " +
			w.getPrice() + " , Hasar : " +
			w.getDamage() + " >");
		}
		System.out.println("0 - ��k�� Yap");
	}
	
	public void buyWeapon() {
		System.out.print("Bir silah se�iniz : ");
		int selectWeaponID = input.nextInt();
		while(selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
			System.out.println("Ge�ersiz de�er, tekrar giriniz : ");
			selectWeaponID = input.nextInt();
		}
		
		if(selectWeaponID != 0) {
			Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
			
			if(selectedWeapon != null) {
				if(selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
					System.out.println("Yeterli paran�z bulunmamaktad�r !");
				}else {
					System.out.println(selectedWeapon.getName() + " silah�n� sat�n ald�n�z.");
					int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
					this.getPlayer().setMoney(balance);
					System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());
					this.getPlayer().getInventory().setWeapon(selectedWeapon);

				}
			}
		}
	}
	
	public void printArmor() {
		System.out.println("-------- Z�rhlar --------");
		for (Armor a : Armor.armors()) {
			System.out.println(a.getId() + " - " + a.getName() +
					" < Para : " + a.getPrice() + " , Z�rh : " + a.getBlock() + " >"
					);
		}
		System.out.println("0 - ��k�� Yap");
	}
	
	public void buyArmor() {
		System.out.print("Bir z�rh se�iniz : ");
		int selectArmorID = input.nextInt();
		while(selectArmorID < 1 || selectArmorID > Armor.armors().length) {
			System.out.println("Ge�ersiz de�er, tekrar giriniz : ");
			selectArmorID = input.nextInt();
		}
		
		if(selectArmorID != 0) {
			Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
			
			if(selectedArmor != null) {
				if(selectedArmor.getPrice() > this.getPlayer().getMoney()) {
					System.out.println("Yeterli paran�z bulunmamaktad�r !");
				}else {
					System.out.println(selectedArmor.getName() + " z�rh�n� sat�n ald�n�z.");
					this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
					this.getPlayer().getInventory().setArmor(selectedArmor);
					System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());
				}
			}
		}
	}

}
