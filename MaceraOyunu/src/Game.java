import java.util.Scanner;

public class Game {
	
	private Scanner input = new Scanner(System.in);
	
	public void start() {
		System.out.println("Macera Oyununa Hoþgeldiniz !");
		System.out.print("Lütfen bir isim giriniz : ");
		String playerName = input.nextLine();
		Player player = new Player(playerName);
		System.out.println("Sayýn " + player.getName() + " bu karanlýk ve sisli adaya hoþgeldiniz. Burada yaþananlarýn hepsi gerçek !!");
		System.out.println("--------------------------------------------------------------------");	
		player.selectChar();
		
		Location location = null;
		
		while(true) {
			player.printInfo();
			System.out.println();
			System.out.println("####### Bölgeler #######");
			System.out.println();
			System.out.println("1 - Güvenli Ev --> Burasý sizin için güvenli bir ev, düþman yoktur.");
			System.out.println("2 - Eþya Dükkaný --> Silah veya Zýrh satýn alabilirsiniz.");
			System.out.println("3 - Maðara --> Ödül <Yemek> , dikkatli ol karþýna zombi çýkabilir!");
			System.out.println("4 - Orman --> Ödül <Odun> , dikkatli ol karþýna vampir çýkabilir!");
			System.out.println("5 - Nehir --> Ödül <Su> , dikkatli ol karþýna ayý çýkabilir!");
			System.out.println("6 - Maden --> Ödül <Yok> , dikkatli ol karþýna yýlan çýkabilir!");
			System.out.println("0 - Çýkýþ Yap  --> Oyunu sonlandýr.");
			System.out.print("Lütfen gitmek istediðiniz bölgeyi seçiniz : ");
			int selectLoc = input.nextInt();
			switch(selectLoc) {
			case 0:
				location = null;
				break;
			case 1:
				location = new SafeHouse(player);
				break;
			case 2:
				location = new ToolStore(player);
				break;
			case 3:
				location = new Cave(player);
				break;
			case 4:
				location = new Forest(player);
				break;
			case 5:
				location = new River(player);
				break;
			case 6:
				location = new Coal(player);				
				break;
			default:
				System.out.println("Lütfen geçerli bir bölge giriniz!");
			}
			
			if(location == null) {
				System.out.println("Bu karanlýk ve sisli adadan çabuk vazgeçtin ");
				break;
			}
			
			if(!location.onLocation())
			{
				System.out.println("Game Over");
				break;
			}
		}	
	}
}
