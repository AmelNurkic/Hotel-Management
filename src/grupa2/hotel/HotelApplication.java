package grupa2.hotel;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import grupa2.hotel.data.Administrator;
import grupa2.hotel.data.Korisnik;
import grupa2.hotel.data.User;
import grupa2.hotel.data.Usluga;
import grupa2.hotel.data.IO.UserDao;
import grupa2.hotel.data.IO.UslugaDao;

public class HotelApplication implements Runnable {

	private Thread hotelApp;
	private boolean radi = false;
	private static Scanner unos = null;

	public synchronized void start() {
		radi = true;
		unos = new Scanner(System.in);
		hotelApp = new Thread(this, "mainT");
		hotelApp.run();
	}

	public synchronized void stop() {
		try {
			unos.close();
			radi = false;
			hotelApp.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		/**
		 * ovdje sve --program loop
		 */
		UserDao dao = new UserDao();
		while (radi) {
			try {
				System.out.println("__Hotel Management__");
				System.out.println("| unesite username ");
				String username = unos.next();
				System.out.println("| unesite password ");
				String password = unos.next();

				boolean administrator = false;
				boolean korisnik = false;

				try {
					korisnik = dao.getKorisnik(username, password) instanceof Korisnik;
					// ako nije korisnik provjeri dali je administrator
					if (!korisnik) {
						administrator = dao.getKorisnik(username, password) instanceof Administrator;
					}
				} catch (SQLException e) {
				}

				if (korisnik) {
					Korisnik kor = null;
					try {
						kor = (Korisnik) dao.getKorisnik(username, password);
					} catch (SQLException e) {
						System.err.println("Nesto je krivo : " + e.toString());
						System.exit(0);
					}
					while (korisnik) {
						System.out.println(kor.toString());
						System.out.println("| 1. Pogledaj cijene");
						System.out.println("| 2. Pogledaj koliko ste duzni");
						System.out.println("| 3. Naruèi novu uslugu");
						System.out.println("| 4. Odjavi se iz hotela");
						System.out.println("| 5. odjavi se");
						int input = unos.nextInt();

						if (input == 1) {

							System.out.println(Usluga.JEDNOKREVETNA_SOBA + " cijena : "
									+ Usluga.CIJENA_JEDNOKREVETNE_SOBE + " km");
							System.out.println(
									Usluga.DVOKREVETNA_SOBA + " cijena : " + Usluga.CIJENA_DVOKREVETNE_SOBE + " km");
							System.out.println(Usluga.APARTMAN + " cijena : " + Usluga.CIJENA_APARTMANA + " km");
							System.out.println(Usluga.TERETANA + " cijena : " + Usluga.CIJENA_TERETANA + " km");
							System.out.println(Usluga.KINO + " cijena : " + Usluga.CIJENA_KINO + " km");
							System.out.println(Usluga.RESTORAN + " cijena : " + Usluga.CIJENA_RESTORAN + " km");
							System.out.println(Usluga.BAZEN + " cijena : " + Usluga.CIJENA_BAZEN + " km");
							System.out.println(Usluga.SAUNA + " cijena : " + Usluga.CIJENA_SAUNA + " km");
						}
						if (input == 2) {

						}
						if (input == 3) {
							System.out.println("Izaberite uslugu koju zelite naruciti: ");
							System.out.println("| 1. Jednokrevetna soba");
							System.out.println("| 2. Dvokrevetna soba");
							System.out.println("| 3. Apartman");
							System.out.println("| 4. Teretana");
							System.out.println("| 5. Kino");
							System.out.println("| 6. Restoran");
							System.out.println("| 7. Bazen");
							System.out.println("| 8. Sauna");
							Scanner ulaz = new Scanner(System.in);
							int odabir = ulaz.nextInt();
							UslugaDao udao = new UslugaDao();
							Usluga usluga = new Usluga();
							switch (odabir) {
							case 1:
								usluga.setImeUsluge(Usluga.JEDNOKREVETNA_SOBA);
								usluga.setCijenaUsluge(Usluga.CIJENA_JEDNOKREVETNE_SOBE);
								break;
							case 2:
								usluga.setImeUsluge(Usluga.DVOKREVETNA_SOBA);
								usluga.setCijenaUsluge(Usluga.CIJENA_DVOKREVETNE_SOBE);
								break;
							case 3:
								usluga.setImeUsluge(Usluga.APARTMAN);
								usluga.setCijenaUsluge(Usluga.CIJENA_APARTMANA);
								break;
							case 4:
								usluga.setImeUsluge(Usluga.TERETANA);
								usluga.setCijenaUsluge(Usluga.CIJENA_TERETANA);
								break;
							case 5:
								usluga.setImeUsluge(Usluga.KINO);
								usluga.setCijenaUsluge(Usluga.CIJENA_KINO);
								break;
							case 6:
								usluga.setImeUsluge(Usluga.RESTORAN);
								usluga.setCijenaUsluge(Usluga.CIJENA_RESTORAN);
								break;
							case 7:
								usluga.setImeUsluge(Usluga.BAZEN);
								usluga.setCijenaUsluge(Usluga.CIJENA_BAZEN);
								break;
							case 8:
								usluga.setImeUsluge(Usluga.SAUNA);
								usluga.setCijenaUsluge(Usluga.CIJENA_SAUNA);
								break;
							}
							udao.createUsluga(usluga, kor);
							System.out.println("Hvala vam sto koristite nase usluge!");
							ulaz.close();

						}
						if (input == 4) {

						}
						if (input == 5) {
							dao.logOut(kor);

						}
					}
				}

				if (administrator) {
					Administrator admin = null;
					try {
						admin = (Administrator) dao.getKorisnik(username, password);
					} catch (SQLException e) {
						System.err.println("Nesto je krivo : " + e.toString());
						System.exit(0);
					}
					while (administrator) {
						System.out.println("| 1. Registruj korisnika");
						System.out.println("| 2. Promjeni informacije o korisniku");
						System.out.println("| 3. Odjavi korisnika iz hotela");
						System.out.println("| 4. Odjavi korinik a/e ");
						System.out.println("| 5. Pretrazi korisnik a/e");
						System.out.println("| 6. odjavi se");
						System.out.println("| 7. ugasi");
						int input = unos.nextInt();
						if (input == 1) {

						}
						if (input == 2) {
							Scanner ulaz = new Scanner(System.in);
							System.out.println(
									"Unesite korisnicko ime i sifru od korisnika cije informacije zelite promjeniti: ");
							String korime = ulaz.next();
							String sifra2 = ulaz.next();
							User a = dao.getKorisnik(korime, sifra2);
							System.out.println("Unesite koje informacije zelite promjeniti(0-5): ");
							System.out.println("1. Ime");
							System.out.println("2. Prezime");
							System.out.println("3. Godine");
							System.out.println("4. Korisnicko ime");
							System.out.println("5. Password");
							int odabir = ulaz.nextInt();
							switch (odabir) {
							case 1:
								System.out.println("Unesite novo ime korisnika:");
								String ime = ulaz.next();
								a.setIme(ime);
								break;
							case 2:
								System.out.println("Unesite novo prezime korisnika:");
								String prezime = ulaz.next();
								a.setPrezime(prezime);
								break;
							case 3:
								System.out.println("Unesite godine korisnika:");
								byte godine = ulaz.nextByte();
								a.setGodine(godine);
								break;
							case 4:
								System.out.println("Unesite novo korisnicko ime korisnika:");
								String korisnickoime = ulaz.next();
								a.setUsername(korisnickoime);
								break;
							case 5:
								System.out.println("Unesite novu sifru korisnika:");
								String sifra = ulaz.next();
								a.setPassword(sifra);
								break;
							}
							dao.updateKorisnik((Korisnik)a);
							System.out.println("Informacije promijenjene");
						}
						if (input == 3) {

						}
						if (input == 4) {

						}
						if (input == 5) {

						}
						if (input == 6) {

						}
						if (input == 7) {

						}

					}
				}
				if (!korisnik && !administrator) {
					System.out.println("--pogresni podatci--");
				}

			} catch (InputMismatchException e) {
				System.err.println("pogresan unos");
				radi = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}