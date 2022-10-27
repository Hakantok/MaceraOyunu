import java.util.Scanner;

public class Game {
	
	private Scanner input = new Scanner(System.in);
	
	public void start() {
		System.out.println("Macera Oyununa Ho�geldiniz !");
		System.out.print("L�tfen bir isim giriniz : ");
		String playerName = input.nextLine();
		Player player = new Player(playerName);
		System.out.println("Say�n " + player.getName() + " bu karanl�k ve sisli adaya ho�geldiniz. Burada ya�ananlar�n hepsi ger�ek !!");
		System.out.println("--------------------------------------------------------------------");	
		player.selectChar();
		
		Location location = null;
		
		while(true) {
			player.printInfo();
			System.out.println();
			System.out.println("####### B�lgeler #######");
			System.out.println();
			System.out.println("1 - G�venli Ev --> Buras� sizin i�in g�venli bir ev, d��man yoktur.");
			System.out.println("2 - E�ya D�kkan� --> Silah veya Z�rh sat�n alabilirsiniz.");
			System.out.println("3 - Ma�ara --> �d�l <Yemek> , dikkatli ol kar��na zombi ��kabilir!");
			System.out.println("4 - Orman --> �d�l <Odun> , dikkatli ol kar��na vampir ��kabilir!");
			System.out.println("5 - Nehir --> �d�l <Su> , dikkatli ol kar��na ay� ��kabilir!");
			System.out.println("6 - Maden --> �d�l <Yok> , dikkatli ol kar��na y�lan ��kabilir!");
			System.out.println("0 - ��k�� Yap  --> Oyunu sonland�r.");
			System.out.print("L�tfen gitmek istedi�iniz b�lgeyi se�iniz : ");
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
				System.out.println("L�tfen ge�erli bir b�lge giriniz!");
			}
			
			if(location == null) {
				System.out.println("Bu karanl�k ve sisli adadan �abuk vazge�tin ");
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
