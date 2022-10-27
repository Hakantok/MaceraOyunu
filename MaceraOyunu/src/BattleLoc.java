import java.util.Random;

public class BattleLoc extends Location {

	private Obstacle obstacle;
	private String award;
	private int maxObstacle;

	public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
		super(player, name);
		this.obstacle = obstacle;
		this.award = award;
		this.maxObstacle = maxObstacle;
	}

	@Override
	public boolean onLocation() {
		int obsNumber = this.randomObstacleNumber();
		System.out.println("�uan buradas�n�z : " + this.getName());
		System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " ya��yor.");
		System.out.print("<S>ava� veya <K>a� : ");
		String selectCase = input.nextLine().toUpperCase();

		if (selectCase.equals("S") && combat(obsNumber)) {
			System.out.println(this.getName() + " t�m d��manlar� yendiniz!");
			return true;
		}

		if (this.getPlayer().getHealth() <= 0) {
			System.out.println("�ld�n�z !");
			return false;
		}
		return true;
	}

	public boolean combat(int obsNumber) {
		for (int i = 1; i <= obsNumber; i++) {
			this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
			playerStats();
			obstacleStats(i);
			Random rnd = new Random();
			int firstHit = rnd.nextInt(2);

			while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
				System.out.print("<V>ur veya <K>a� : ");
				String selectCombat = input.nextLine().toUpperCase();
				if (selectCombat.equals("V")) {
					if (firstHit == 0) {
						this.getObstacle()
								.setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
						System.out.println("Siz vurdunuz");
						System.out.println("Vurdugunuz hasar: " + this.getPlayer().getTotalDamage());
						System.out.println("Kalan Caniniz: " + this.getPlayer().getHealth());
						System.out.println(
								this.getObstacle().getName() + " kalan cani: " + this.getObstacle().getHealth());
						System.out.println();
						if (this.getObstacle().getHealth() > 0) {
							int obsDamage = this.getObstacle().getDamage()
									- this.getPlayer().getInventory().getArmor().getBlock();
							if (obsDamage < 0) {
								obsDamage = 0;
							}
							this.getPlayer().setHealth(this.getPlayer().getHealth() - obsDamage);
							System.out.println("Canavar vurdu");
							System.out.println("Canavarin vurdugu hasar: " + this.getObstacle().getDamage());
							System.out.println("Kalan Caniniz: " + this.getPlayer().getHealth());
							System.out.println(
									this.getObstacle().getName() + " kalan cani: " + this.getObstacle().getHealth());
							System.out.println();
						}
					}
					if (firstHit == 1) {
						if (this.getPlayer().getHealth() > 0) {
							int obsDamage = this.getObstacle().getDamage()
									- this.getPlayer().getInventory().getArmor().getBlock();
							if (obsDamage < 0) {
								obsDamage = 0;
							}
							this.getPlayer().setHealth(this.getPlayer().getHealth() - obsDamage);
							System.out.println("Canavar vurdu");
							System.out.println("Canavarin vurdugu hasar: " + this.getObstacle().getDamage());
							System.out.println("Kalan Caniniz: " + this.getPlayer().getHealth());
							System.out.println(
									this.getObstacle().getName() + " kalan cani: " + this.getObstacle().getHealth());
							System.out.println();
						}
						this.getObstacle()
								.setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
						System.out.println("Siz vurdunuz");
						System.out.println("Vurdugunuz hasar: " + this.getPlayer().getTotalDamage());
						System.out.println("Kalan Caniniz: " + this.getPlayer().getHealth());
						System.out.println(
								this.getObstacle().getName() + " kalan cani: " + this.getObstacle().getHealth());
						System.out.println();
					}
				} else {
					System.out.println("Canavardan ka�t�n�z.");
					break;
				}
				if (this.getPlayer().getHealth() <= 0) {
					return false;
				}
			}

			if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
				if (this.getObstacle().getName() == "Y�lan") {
					snakeAward();
				} else {
					System.out.println("D��man� Yendiniz!");
					System.out.println(this.getObstacle().getAward() + " para kazand�n�z");
					this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
					System.out.println("G�ncel Paran�z : " + this.getPlayer().getMoney());
				}

			} else {
				return false;
			}

		}
		return true;

	}

	public void afterHit() {
		System.out.println("Can�n�z : " + this.getPlayer().getHealth());
		System.out.println(this.getObstacle().getName() + " Can� : " + this.getObstacle().getHealth());
		System.out.println("------------");
	}

	public void playerStats() {
		System.out.println("Oyuncu De�erleri");
		System.out.println("-----------------------------");
		System.out.println("Sa�l�k : " + this.getPlayer().getHealth());
		System.out.println("Silah : " + this.getPlayer().getWeapon().getName());
		System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
		System.out.println("Z�rh : " + this.getPlayer().getInventory().getArmor().getName());
		System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
		System.out.println("Para : " + this.getPlayer().getMoney());
		System.out.println();
	}

	public void obstacleStats(int i) {
		System.out.println(i + "." + this.getObstacle().getName() + " De�erleri");
		System.out.println("-----------------------------");
		System.out.println("Sa�l�k : " + this.getObstacle().getHealth());
		System.out.println("Hasar : " + this.getObstacle().getDamage());
		System.out.println("�d�l : " + this.getObstacle().getAward());
		System.out.println();
	}

	public void snakeAward() {
		Random rnd = new Random();
		int randomChance = rnd.nextInt(3);
		if (randomChance == 0) {
			int weaponChance = (int) (Math.random() * 100);
			int weapon = (int) (Math.random() * 100);

			if (weaponChance < 15) {
				if (weapon < 50) {
					if (wonWeapon("Tabanca")) {
						this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
						System.out.println("D��man� �ld�rd�n�z �d�l olarak bir adet Tabanca kazand�n�z.");
					}
				}
				if (weapon > 50 && weapon < 80) {
					if (wonWeapon("K�l�c")) {
						this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
						System.out.println("D��man� �ld�rd�n�z �d�l olarak bir adet K�l�c kazand�n�z.");
					}
				}
				if (weapon >= 80) {
					if (wonWeapon("T�fek")) {
						this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
						System.out.println("D��man� �ld�rd�n�z �d�l olarak bir adet T�fek kazand�n�z.");
					}
				}
			} else {
				System.out.println("Herhangi bir �d�l kazanamad�n�z.");
			}
		}
		if (randomChance == 1) {
			int armorChance = (int) (Math.random() * 100);
			int armor = (int) (Math.random() * 100);

			if (armorChance < 15) {
				if (armor < 50) {
					if (wonArmor("Hafif")) {
						this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
						System.out.println("D��man� �ld�rd�n�z �d�l olarak Hafif z�rh kazand�n�z.");
					}
				}
				if (armor > 50 && armor < 80) {
					if (wonArmor("Orta")) {
						this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
						System.out.println("D��man� �ld�rd�n�z �d�l olarak Orta z�rh kazand�n�z.");
					}
				}
				if (armor >= 80) {
					if (wonArmor("A��r")) {
						this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
						System.out.println("D��man� �ld�rd�n�z �d�l olarak A��r z�rh kazand�n�z.");
					}
				}
			} else {
				System.out.println("Herhangi bir �d�l kazanamad�n�z.");
			}
		}
		if (randomChance == 2) {
			int moneyChance = (int) (Math.random() * 100);
			int money = (int) (Math.random() * 100);

			if (moneyChance < 25) {
				if (money < 50) {
					int coin = 10;
					this.getPlayer().setMoney(coin + this.getPlayer().getMoney());
					System.out.println("D��man� �ld�rd�n�z �d�l olarak " + coin + " kazand�n�z.");
				}
				if (money > 50 && money < 80) {
					int coin = 5;
					this.getPlayer().setMoney(coin + this.getPlayer().getMoney());
					System.out.println("D��man� �ld�rd�n�z �d�l olarak " + coin + " kazand�n�z.");
				}
				if (money >= 80) {
					int coin = 1;
					this.getPlayer().setMoney(coin + this.getPlayer().getMoney());
					System.out.println("D��man� �ld�rd�n�z �d�l olarak " + coin + " kazand�n�z.");
				}

			} else {
				System.out.println("Herhangi bir �d�l kazanamad�n�z.");
			}
		}
	}

	public boolean wonWeapon(String name) {
		if (this.getPlayer().getInventory().getWeapon().getName() == "Yumruk") {
			return true;
		}
		return false;
	}

	public boolean wonArmor(String name) {
		if (this.getPlayer().getInventory().getArmor().getName() == "Pa�avra") {
			return true;
		}
		return false;
	}

	public int randomObstacleNumber() {
		Random r = new Random();
		return r.nextInt(this.getMaxObstacle() + 1);
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public int getMaxObstacle() {
		return maxObstacle;
	}

	public void setMaxObstacle(int maxObstacle) {
		this.maxObstacle = maxObstacle;
	}

}
