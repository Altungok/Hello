/*
 Ad 			  : Ömer Faruk
 Soyad            : Altungök 
 Öğrenci numarası : 250706050
 e-posta Adresi   : frkaltungok@gmail.com
 Yapılış amacı    : Güvenli şifre belirleme , şifre gücünü puanlama
 
*/
//Scanerı tanımladık 
import java.util.Scanner;
public class ödev {
	
	public static final String  BITIS_KOMUTU="Bitir";
	public static void main(String[] args) {
		Scanner keyboard = new Scanner (System.in);
		int toplamSifre = 0, gecerliSifre = 0 , gecersizSifre = 0 ;
		int maxPuan = -1;
		String maxPuanliSifre = "";
		int toplamGucPuani = 0;
		int cokGucluSayisi = 0 ;
		int maxRakamSayisi = -1 ;
		String maxRakamliSifre = "" ;
		int hataUzunluk = 0 ;
		int hataBosluk = 0;
		int hataTur = 0 ;
		int hataArdisik = 0 ;
		int hataYasakli = 0;
		
		System.out.println("---------------------------- \n--- Şifre Analiz Sistemi --- \n----------------------------");
		while (true) {
			System.out.print("Şifre giriniz (Çıkmak için Bitir yazınız ) : ");
			
			String sifre=keyboard.nextLine();
			if (sifre.equalsIgnoreCase(BITIS_KOMUTU)) {
				break;
			}
			toplamSifre = toplamSifre+1;
			int uzunluk = sifre.length();
			boolean gecerli =true;
			String hataMesaji="";
			int buyukHarf = 0 , kucukHarf = 0, rakam = 0, ozelKarakter = 0;
//Temel geçerlilik kontrolleri :
// A. Uzunluk kontrolü 
			if (uzunluk <8 || uzunluk >20) {
				gecerli = false;
				hataMesaji = "Uzunluk Hatalı . (Dikkat edip yeni bir şifre giriniz) ";
				hataUzunluk =hataUzunluk +1;
			}		
// B. Boşluk kontrolü
		    if (gecerli ) {
		    	for (int i=0 ;i<uzunluk;i++) {
		    		if (sifre.charAt(i) == ' ') {
		    			gecerli= false ;
		    			hataMesaji = "Boşluk içeriyor . (Dikkat edip yeni bir şifre giriniz )";
		    			hataBosluk = hataBosluk +1 ;
		    			break;
		    		}
		    		
		    	}	
		    	
		    }
 // C. Karakter türü kontrolü
		    
		    if (gecerli) {
		    	for (int i = 0 ; i<uzunluk; i++) {
		    		char c= sifre.charAt(i);
		    		if (c>= 'A' && c <= 'Z')
		    			buyukHarf = buyukHarf + 1;
		    		else if (c>='a' && c<='z')
		    			kucukHarf = kucukHarf +1;
		    		else if (c>='0' && c<='9')
		    			rakam = rakam +1;
		    		else
		    			ozelKarakter =ozelKarakter +1;
		    		
		    		}
		    	if (buyukHarf ==0 || kucukHarf ==0 || rakam ==0 || ozelKarakter==0) {
		    		gecerli = false ;
		    		hataMesaji = "Karakter türü Eksik . (Dikkat edip yeni bir şifre giriniz )"; 
		    		hataTur = hataTur +1;
		    		}
		    }
// D. Ardışık tekrar kontrolü (aynı karakter 3 kez peş peşe gelemez)
		    if (gecerli ) {
		    	for (int i = 0 ;i<uzunluk-2;i++) {
		    		if (sifre.charAt(i)==sifre.charAt(i+1) && sifre.charAt(i)==sifre.charAt(i+2)) {
		    			gecerli = false ;
		    			hataMesaji = "Aynı karakter 3 kez art arda kullanılmış . (Dikkat edip yeni bir şifre giriniz )";
		    			hataArdisik = hataArdisik + 1 ;
		    			break ;
		    			
		    			
		    	}
		    }

		}
// E. Yasaklı ifade kontrolü 
		if (gecerli ) {
			String kucukSifre = sifre.toLowerCase();
			boolean yasakliBulundu = false ;
			//admin
			if (uzunluk >= 5) {
				for (int i = 0 ; i<=uzunluk -5 ; i++) {
					if(kucukSifre.substring(i,i+5).equals("admin"))
						yasakliBulundu = true ;
				}
				
			}
			//1234
			if (!yasakliBulundu && uzunluk>=4) {
				for(int i=0 ; i<=uzunluk-4 ;i++) {
					if(kucukSifre.substring(i, i+4).equals("1234"))
						yasakliBulundu=true ;
					
					
				}
			}
			//qwerty
			if(!yasakliBulundu && uzunluk>= 6) {
				for (int i=0 ; i<=uzunluk-6;i++) {
					if (kucukSifre.substring(i , i+6).equals("qwerty"))
						yasakliBulundu = true ;
					
				}
			}
			//password
			if (!yasakliBulundu && uzunluk >= 8) {
				for (int i = 0 ; i<= uzunluk - 8 ; i++) {
					if (kucukSifre.substring(i,i+8).equals("password"))
						yasakliBulundu = true;
					
					}
				}
				
				if (yasakliBulundu) {
					gecerli = false ;
					hataMesaji = "Yasakli ifade içeriyor. (Dikkat edip yeni bir şifre giriniz )";
					hataYasakli = hataYasakli + 1;
					
					
				}
			}
//Çıktı yazdırma ve puan hesaplama:
		if (!gecerli) {
			gecersizSifre = gecersizSifre + 1 ;
			System.out.println("Geçersiz sifre : "+ hataMesaji +"\n");
		}
		else {
			gecerliSifre = gecerliSifre + 1 ;
//Puan hesaplama
			int uzunlukPuani = 0 ;
			if (uzunluk >= 8 && uzunluk <= 10 )
				uzunlukPuani = 10;
			else if (uzunluk >=11 && uzunluk <= 14)
				uzunlukPuani = 20;
			else if (uzunluk >= 15 && uzunluk <=20)
				uzunlukPuani = 30;
//yukarıda teyit etmiştim ondan şifrede 4 tür bulunduğu için :10+10+10+15 = 45 puan alır 
			int turPuani = 45 ;
//Farkli karakter sayısı hesabı 
			int farkliKarakter = 0 ;
				for(int i = 0 ; i < uzunluk ; i++ ) {
				boolean ilkKullanim = true ;
				for (int j = 0 ; j < i ; j++) {
					if (sifre.charAt(i) == sifre.charAt(j)) {
						ilkKullanim = false ;
						break ;
						
					}
				}
				if (ilkKullanim) 
					farkliKarakter = farkliKarakter + 1 ;
				
			}
			int cesitlilikPuani = 0 ;
			if (farkliKarakter >=11 ) 
				cesitlilikPuani =20;
			else if (farkliKarakter >=8)
				cesitlilikPuani = 10;
			else if (farkliKarakter >=5)
				cesitlilikPuani = 5 ;
			
//Tekrar cezası hesabı (fazladan kullanılan her karakter için -1 puan düşücez ) 
			int ekstraKullanim = uzunluk - farkliKarakter;
			int tekrarCezasi = ekstraKullanim * -1;
			int gucPuani = uzunlukPuani + turPuani + cesitlilikPuani + tekrarCezasi ;
			 toplamGucPuani = toplamGucPuani+ gucPuani;
			
//Güç sınıfını belirleme
			String gucSinifi = "";
			if (gucPuani >=80) { 
				gucSinifi = "Çok güçlü "; 
				cokGucluSayisi++; }		
			else if  (gucPuani >=60)
				gucSinifi="Güçlü";
			else if (gucPuani >=35)
				gucSinifi ="Orta";
			else 
				gucSinifi="Zayıf";
			
//İstatikleri güncelleme
			if (gucPuani>maxPuan) {
				maxPuan = gucPuani;
				maxPuanliSifre = sifre;
			}
			if (rakam>maxRakamSayisi) {
				maxRakamSayisi=rakam;
				maxRakamliSifre=sifre;
			}
//Maskeleme
			String maskelenmis = "";
			for (int i = 0; i < uzunluk ; i++) {
				char c = sifre.charAt(i);	
				/* 1 = Büyük harf
				   2 = Küçük harf 
				   3 = Rakam
				   4 = Özel karakter 
				*/
				int charTip; 
				if (c>='A' && c<='Z')
					charTip=1;
				else if (c>= 'a' && c<='z')
					charTip=2;
				else if (c>= '0' && c<= '9')
					charTip=3;
				else 
					charTip = 4;
				switch(charTip) {
				case 1 :
					maskelenmis += c; 
					break;
				case 2 :
					maskelenmis +="*";
					break;
				case 3 :
					maskelenmis +="#";
					break;
				case 4 :
					maskelenmis += c ;
					break;
				}	
			}
//Geçerli şifre Sayısı 
			System.out.println("GEÇERLİ ŞİFRE ");
			System.out.println("Şifre : "+sifre);
			System.out.println("Uzunluk : "+uzunluk);
			System.out.println("Farklı karakter sayısı : "+farkliKarakter);
			System.out.println("Güç puanı : "+gucPuani);
			System.out.println("Güç sınıfı : "+gucSinifi);
			System.out.println("Maskelenmiş şifre : "+maskelenmis+"\n");
			
					
		}	
		
		}
//Özet rapor : 
		System.out.println("===== ÖZET RAPOR ===== ");
		System.out.println("Toplam  girilen şifre sayısı : " +toplamSifre );
		System.out.println("Geçerli şifre sayısı : " +gecerliSifre);
		System.out.println("Geçersiz şifre sayısı : "+gecersizSifre);
		
		if (gecerliSifre > 0 ) {
			System.out.println("En yüksek puanlı şifre : " +maxPuanliSifre);
			System.out.println("Ortalama güç puanı : " +((double) toplamGucPuani / gecerliSifre));
			System.out.println("Çok güçlü sınıfındaki şifre sayisi : " + cokGucluSayisi);
			System.out.println("İçinde en fazla rakam bulunan geçerli şifre : " + maxRakamliSifre);
			
			
		}
		if (gecersizSifre> 0 ) {
//En sık hata 
			String enSikHata = "Uzunluk Hatalı .";
			int maxHata = hataUzunluk;
			if (hataBosluk  > maxHata) {
				maxHata = hataBosluk ;
				enSikHata = "Boşluk içeriyor .";
			}
			if (hataTur > maxHata) {
				maxHata = hataTur ; 
				enSikHata = "Karakter Türü Eksik .";
			
				}
			if (hataArdisik>maxHata) {
				maxHata = hataArdisik;
				enSikHata = "Aynı karakterden 3 kez art arda kullanılmış . ";
						
			}
			if (hataYasakli>maxHata) {
				maxHata=hataYasakli ; 
				enSikHata = "Yasaklı ifade içeriyor ";
				
			}
			System.out.println("En sık görülen geçersizlik nedeni : "+enSikHata);
		}
	  keyboard.close();
	}

	
	
	
}





























