package com.market.main;

import java.util.Scanner;

import com.market.bookitem.Book;
import com.market.cart.Cart;
import com.market.member.Admin;
import com.market.member.User;
import com.market.exception.CartException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Welcome {
	static final int NUM_BOOK = 3; // 도서의 개수에 대한 상수 NUM_BOOK 선언
	static final int NUM_ITEM = 7; // 도서 정보의 개수에 대한 상수 NUM_ITEM 선언
	// static CartItem[] mCartItem = new CartItem[NUM_BOOK];
	// static int mCartCount = 0;
	static Cart mCart = new Cart(); // Cart 클래스의 객체를 생성하여 장바구니에 담긴 도서들을 저장
	static User mUser; // User 클래스의 객체를 생성하여 입력받은 고객의 정보 저장

	public static void main(String[] args) {
		// String[][] mBook = new String[NUM_BOOK][NUM_ITEM]; 도서 정보를 저장할 mBook을 2차원 배열로
		// 생성
		// Book[] mBookList = new Book[NUM_BOOK];
		Book[] mBookList;
		int mTotalBook = 0;
		Scanner input = new Scanner(System.in); // Scanner 클래스의 객체 생성

		System.out.print("당신의 이름을 입력하세요 : ");
		String userName = input.next(); // 이름을 문자열로 입력받음

		System.out.print("연락처를 입력하세요 : ");
		int userMobile = input.nextInt(); // 연락처를 정수로 입력받음
		// System.out.println("Welcome to Shopping Mall");
		// System.out.println("Welcome to Book Market!");
		/* [프로젝트1-1]에서 작성한 내용 */

		mUser = new User(userName, userMobile);

		String greeting = "\t  Welcome to Shopping Mall";
		String tagline = "\t  Welcome to Book Market!";
		/* 인사말을 문자열 변수에 저장 */

		boolean quit = false;

		while (!quit) {
			System.out.println("**************************************************************");
			System.out.println("\t" + greeting);
			System.out.println("\t" + tagline);
			/* 탭만큼 공백을 두고 greeting, tagline 변수의 문자열 출력 */

			/*
			 * System.out.println("************************************************");
			 * System.out.println(" 1. 고객 정보 확인하기 \t4. 바구니에 항목 추가하기");
			 * System.out.println(" 2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
			 * System.out.println(" 3. 장바구니 비우기 \t6.장바구니의 항목 삭제하기");
			 * System.out.println(" 7. 영수증 표시하기 \t8. 종료하기"); // 메뉴 항목 출력
			 * /* 메뉴 항목의 간격을 띄우기 위해 특수문자 '\t'를 적용
			 */
			/*
			 * System.out.println("************************************************");
			 */

			menuIntroduction(); // 메뉴 목록 출력 메서드 호출

			try {
				System.out.print("메뉴 번호를 선택해주세요 ");
				int n = input.nextInt(); // 메뉴 번호 입력
				// System.out.println(n + "번을 선택했습니다"); // 입력한 번호 출력

				if (n < 1 || n > 9) {
					System.out.println("1부터 9까지의 숫자를 입력하세요.");
				} // 메뉴 선택 번호가 1 ~ 8이 아니면 "1부터 8까지의 숫자를 입력하세요." 문자열 출력
				else {
					switch (n) {
						case 1:
							// System.out.println("현재 고객 정보 : ");
							// System.out.println("이름 " + userName + " 연락처 " + userMobile);
							// 선택한 메뉴 번호가 1일 때 키보드로 입력된 고객 이름과 연락처 출력
							menuGuestInfo(userName, userMobile); // 기존 내용 주석 처리, 고객 정보 출력 메소드 호
							break;
						case 2:
							// System.out.println("장바구니 상품 목록 보기 :");
							menuCartItemList();
							break;
						case 3:
							// System.out.println("장바구니 비우기");
							menuCartClear();
							break;
						case 4:
							mTotalBook = totalFileToBooklist();
							mBookList = new Book[mTotalBook];
							// System.out.println("장바구니에 항목 추가하기 : ");
							// menuCartAddItem(mBook);
							menuCartAddItem(mBookList);
							break;
						case 5:
							// System.out.println("5. 장바구니의 항목 수량 줄이기");
							menuRemoveItemCount();
							break;
						case 6:
							// System.out.println("6. 장바구니의 항목 삭제하기");
							menuCartRemoveItem();
							break;
						case 7:
							// System.out.println("7. 영수증 표시하기");
							menuCartBill();
							break;
						case 8:
							// System.out.println("8. 종료");
							menuExit();
							quit = true;
							break;
						case 9:
							menuAdminLogin();
							break;
					} // switch문 끝
				} // else 문 끝
			} catch (CartException e) {
				System.out.println(e.getMessage());
				quit = true;
			} // try 문 끝
			catch (Exception e) {
				System.out.println("올바르지 않은 메뉴 선택으로 종료합니다.");
				quit = true;
			}
		} // while 문 끝
	} // main 메서드 끝

	public static void menuIntroduction() {
		System.out.println("**************************************************************");
		System.out.println(" 1. 고객 정보 확인하기 \t\t4. 장바구니에 항목 추가하기");
		System.out.println(" 2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
		System.out.println(" 3. 장바구니 비우기 \t\t6. 장바구니의 항목 삭제하기");
		System.out.println(" 7. 영수증 표시하기 \t\t8. 종료하기");
		System.out.println(" 9. 관리자 로그인");// 메뉴 항목 출력
		/* 메뉴 항목의 간격을 띄우기 위해 특수문자 '\t'를 적용 */
		System.out.println("**************************************************************");
	} // menuIntroduction 메서드 끝

	public static void menuGuestInfo(String name, int mobile) {
		System.out.println("현재 고객 정보 : ");
		// Person person = new Person(name, mobile);
		// System.out.println("이름" + person.getName() + "연락처" + person.getPhone());
		System.out.println("이름 " + mUser.getName() + " 연락처 " + mUser.getPhone());
	} // menuGuestInfo 메서드 끝

	public static void menuCartItemList() {
		/*
		 * System.out.println("2. 장바구니 상품 목록 :");
		 * System.out.println("------------------------------------------------");
		 * System.out.println("      도서ID    \t|   수량\t|      합계");
		 * for (int i = 0; i < mCartCount; i++) {
		 * System.out.print("    " + mCartItem[i].getBookID() + "\t| ");
		 * System.out.print("    " + mCartItem[i].getQuantity() + "\t| ");
		 * System.out.print("    " + mCartItem[i].getTotalPrice());
		 * System.out.println("   ");
		 * }
		 * System.out.println("------------------------------------------------");
		 */

		if (mCart.mCartCount >= 0) { // 장바구니에 담긴 도서의 개수가 0보다 크거나 같으면
			mCart.printCart(); // 장바구니에 담긴 도서 목록 출력 메서드 호출
		}
	} // menuCartItemList 메서드 끝

	public static void menuCartClear() throws CartException {
		// System.out.println("3. 장바구니 비우기");
		if (mCart.mCartCount == 0) // 장바구니에 담긴 도서의 개수가 0이면 "장바구니에 항목이 없습니다." 문자열 출력
			throw new CartException("장바구니에 항목이 없습니다");
		// System.out.println(장바구니에 항목이 없습니");
		else {
			System.out.println("장바구니의 모든 항목을 삭제하겠습니까? Y | N");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();

			if (str.toUpperCase().equals("Y")) {
				System.out.println("장바구니의 모든 항목을 삭제했습니다.");
				// mCart.mCartItem = new CartItem[NUM_BOOK];
				mCart.deleteBook();
			}
		}
	} // menuCartClear 메서드 끝

	public static void menuCartAddItem(Book[] booklist) { // 장바구니에 도서 추가하는 메서드
		// System.out.println("4. 장바구니에 항목 추가하기");

		BookList(booklist); // 도서 정보를 저장하는 메서드 호출

		/*
		 * for (int i = 0; i < NUM_BOOK; i++) {
		 * for (int j = 0; j < NUM_ITEM; j++)
		 * System.out.print(book[i][j] + " | ");
		 * System.out.println("");
		 * } // 도서 정보 출력
		 */
		mCart.printBookList(booklist);

		boolean quit = false;

		while (!quit) { // 장바구니에 항목을 추가하지 않을 때까지 반복하는 while문 시작
			System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 : ");

			Scanner input = new Scanner(System.in);
			String str = input.nextLine(); // 도서의 ID를 입력받음

			boolean flag = false;
			int numId = -1;

			for (int i = 0; i < NUM_BOOK; i++) {
				if (str.equals(booklist[i].getBookId())) {
					numId = i;
					flag = true;
					break;
				} // 입력된 도서의 ID와 저장되어있는 도서 ID의 일치 여부 확인하여 도서 정보의 mumId(인덱스 번호)와 flag(일치 여부) 변수에 값을
					// 변경하여 저장하고 반복문 종료
			}

			if (flag) {
				System.out.println("장바구니에 추가하겠습니까? Y | N");
				str = input.nextLine(); // 장바구니에 도서 추가 여부를 위한 입력값(Y 또는 N)을 받

				if (str.toUpperCase().equals("Y")) { // 입력값(Y 또는 N)을 대문자로 변경하여 Y이면 '도서가 장바구니에 추가되었습니다.' 출력
					System.out.println(booklist[numId].getBookId() + " 도서가 장바구니에 추가되었습니다.");
					// 카트에 넣기
					if (!isCartInBook(booklist[numId].getBookId())) {
						// mCartItem[mCartCount++] = new CartItem(book[numId]);
						// mCartItem[mCartCount++] = new CartItemBook(booklist[numId]);
						// mCart.mCartCount = mCartCount++;
						// mCartCount++;
						mCart.insertBook(booklist[numId]);
					}
				}
				quit = true;
			} else
				System.out.println("다시 입력해 주세요");
			/*
			 * 변수 flag가 참이면(입력된 도서의 ID와 저장되어 있는 도서 정보의 ID가 일치하면) 반복문을 종료하고 거짓이면 '다시 입력해주세요'
			 * 출력
			 */
		} // while문
	} // menuCarAddItem 메서드 끝

	public static void menuRemoveItemCount() {
		System.out.println("5. 장바구니의 항목 수량 줄이기");
	} // menuRemoveItemCount 메서드 끝

	public static void menuCartRemoveItem() throws CartException { // 장바구니의 항목 삭제하는 메서드
		// System.out.println("6. 장바구니의 항목 삭제하기");
		if (mCart.mCartCount == 0)
			throw new CartException("장바구니에 항목이 없습니다.");
		// System.out.println("장바구니에 항목이 없습니다.");
		else {
			menuCartItemList(); // 장바구니에 담긴 도서 목록 출력 메서드 호출
			boolean quit = false; // while문의 조건식으로 사용할 변수 quit 선언 및 초기화
			while (!quit) { // 장바구니에 항목을 삭제하지 않을 때까지 반복하는 while문 시작
				System.out.println("장바구니에서 삭제할 도서의 ID를 입력하세요 : ");
				Scanner input = new Scanner(System.in);
				String str = input.nextLine();
				boolean flag = false;
				int numId = -1;

				for (int i = 0; i < mCart.mCartCount; i++) { // 장바구니에 담긴 도서의 개수만큼 반복
					if (str.equals(mCart.mCartItem[i].getBookID())) {
						numId = i;
						flag = true;
						break;
					}
				}

				if (flag) { // 입력된 도서의 ID와 저장되어 있는 도서 ID의 일치 여부 확인하여 도서 정보의 mumId(인덱스 번호)와 flag(일치 여부) 변수에
							// 값을
							// 변경하여 저장하고 반복문 종료
					System.out.println("장바구니에서 삭제하겠습니까? Y | N");
					str = input.nextLine();
					if (str.toUpperCase().equals("Y")) {
						System.out.println(mCart.mCartItem[numId].getBookID() + " 장바구니에서 도서가 삭제되었습니다.");
						// 배열 이동
						/*
						 * CartItemBook[] cartItem = new CartItemBook[NUM_BOOK];
						 * int num = 0;
						 * 
						 * for (int i = 0; i < mCartCount; i++)
						 * if (numId != i) cartItem[num++] = mCartItem[i];
						 * mCartCount = num;
						 * mCartItem = cartItem;
						 */
						mCart.removeCart(numId);
					}
					quit = true;
				} else
					System.out.println("다시 입력해 주세요");
			}
		}
	} // menuCartRemoveItem 메서드 끝

	public static void menuCartBill() throws CartException {
		// System.out.println("7. 영수증 표시하기");
		if (mCart.mCartCount == 0)
			throw new CartException("장바구니에 항목이 없습니다.");
		// System.out.println("장바구니에 항목이 없습니다.");

		else {
			System.out.println("배송받을 분은 고객 정보와 같습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();

			if (str.toUpperCase().equals("Y")) {
				System.out.print("배송지를 입력해주세요 ");
				String address = input.nextLine();

				// 배송을 위한 고객 정보(이름, 연락처, 주소)와 영수증 출력을 위한 printBill() 메서드 호출
				printBill(mUser.getName(), String.valueOf(mUser.getPhone()), address);
			} else {
				System.out.print("배송받을 고객명을 입력하세요 ");
				String name = input.nextLine();
				System.out.print("배송받을 고객의 연락처를 입력하세요 ");
				String phone = input.nextLine();
				System.out.print("배송받을 고객의 배송지를 입력해주세요 ");
				String address = input.nextLine();

				// printBill 메서드 호출, 변수 name, phone, address의 데이터를 받음
				printBill(name, phone, address);
			}
		}
	} // menuCartBill 메서드 끝

	public static void printBill(String name, String phone, String address) {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		System.out.println();
		System.out.println("---------------배송받을 고객 정보---------------");
		System.out.println("고객명 : " + name + " \t\t연락처 : " + phone);
		System.out.println(" 배송지 : " + address + "\t\t발송일 : " + strDate);

		mCart.printCart(); // 장바구니에 담긴 항목 출력

		int sum = 0;
		for (int i = 0; i < mCart.mCartCount; i++)
			sum += mCart.mCartItem[i].getTotalPrice();

		System.out.println("\t\t\t주문 총금액 : " + sum + "원\n");
		System.out.println("--------------------------------------------------------");
		System.out.println();
	} // printBill 메서드 끝

	public static void menuExit() {
		System.out.println("8. 종료");
	} // menuExit 메서드 끝

	public static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");

		Scanner input = new Scanner(System.in);
		System.out.print("아이디 : ");
		String adminId = input.next();

		System.out.print("비밀번호 : ");
		String adminPW = input.next();

		Admin admin = new Admin(mUser.getName(), mUser.getPhone());
		if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
			String[] writeBook = new String[7];
			System.out.println("도서 정보를 추가하겠습니까? Y | N");
			String str = input.next();

			if (str.toUpperCase().equals("Y")) {
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
				String strDate = formatter.format(date);
				writeBook[0] = "ISBN" + strDate;
				System.out.println("도서ID : " + writeBook[0]);
				String st1 = input.nextLine();
				System.out.print("도서명 : ");
				writeBook[1] = input.nextLine();
				System.out.println("가격 : ");
				writeBook[2] = input.nextLine();
				System.out.print("저자 : ");
				writeBook[3] = input.nextLine();
				System.out.println("설명 : ");
				writeBook[4] = input.nextLine();
				System.out.print("분야 : ");
				writeBook[5] = input.nextLine();
				System.out.println("출판일 : ");
				writeBook[6] = input.nextLine();
				try {
					FileWriter fw = new FileWriter("book.txt", true);
					for (int i = 0; i < 7; i++)
						fw.write(writeBook[i] + "\n");
					fw.close();
					System.out.println("새 도서 정보가 저장되었습니다.");
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				System.out.println("이름 " + admin.getName() + " 연락처 " + admin.getPhone());
				System.out.println("아이디 " + admin.getId() + " 비밀번호 " + admin.getPassword());
			}
		} else
			System.out.println("관리자 정보가 일치하지 않습니다.");
	}

	public static void BookList(Book[] booklist) { // 도서 정보를 저장하는 메서드
		setFileToBookList(booklist);
		/*
		 * booklist[0] = new Book("ISBN1234", "쉽게 배우는 JSP 웹 프로그래밍", 27000);
		 * booklist[0].setAuthor("송미영");
		 * booklist[0].setDescription("단계별로 쇼핑몰을 구현하며 배우는 JSP 웹 프로그래밍");
		 * booklist[0].setCategory("IT전문서");
		 * booklist[0].setReleaseDate("2018/10/08");
		 * 
		 * booklist[1] = new Book("ISBN1235", "안드로이드 프로그래밍", 33000);
		 * booklist[1].setAuthor("우재남");
		 * booklist[1].setDescription("실습 단계별 명쾌한 멘토링!");
		 * booklist[1].setCategory("IT전문서");
		 * booklist[1].setReleaseDate("2022/01/22");
		 * 
		 * booklist[2] = new Book("ISBN1236", "스크래치", 22000);
		 * booklist[2].setAuthor("고광일");
		 * booklist[2].setDescription("컴퓨팅 사고력을 키우는 블록 코딩");
		 * booklist[2].setCategory("컴퓨터입문");
		 * booklist[2].setReleaseDate("2019/06/10");
		 */
	} // Booklist 메서드 끝

	public static boolean isCartInBook(String bookId) { // 장바구니에 동일한 도서가 있는지 확인하는 메서드
		/*
		 * boolean flag = false;
		 * for (int i = 0; i < mCartCount; i++) {
		 * if (bookId == mCartItem[i].getBookID()) {
		 * mCartItem[i].setQuantity(mCartItem[i].getQuantity() + 1);
		 * flag = true;
		 * }
		 * }
		 * return flag;
		 */
		return mCart.isCartInBook(bookId);
	} // isCartInBook 메서드 끝 (장바구니에 항목 추가하는 메서드)

	public static int totalFileToBooklist() {
		try {
			FileReader fr = new FileReader("book.txt");
			BufferedReader reader = new BufferedReader(fr);

			String str;
			int num = 0;
			while ((str = reader.readLine()) != null) {
				if (str.contains("ISBN"))
					++num;
			}

			reader.close();
			fr.close();
			return num;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public static void setFileToBookList(Book[] booklist) {
		try {
			FileReader fr = new FileReader("book.txt");
			BufferedReader reader = new BufferedReader(fr);

			String str2;
			String[] readBook = new String[7];
			int count = 0;

			while ((str2 = reader.readLine()) != null) {
				if (str2.contains("ISBN")) {
					readBook[0] = str2;
					readBook[1] = reader.readLine();
					readBook[2] = reader.readLine();
					readBook[3] = reader.readLine();
					readBook[4] = reader.readLine();
					readBook[5] = reader.readLine();
					readBook[6] = reader.readLine();
				}

				booklist[count++] = new Book(readBook[0], readBook[1], Integer.parseInt(readBook[2]), readBook[3],
						readBook[4], readBook[5], readBook[6]);

			}

			reader.close();
			fr.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}