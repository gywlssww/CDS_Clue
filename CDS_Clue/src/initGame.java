import java.util.Random;
import java.util.Vector;
import java.lang.String;

import kr.ac.konkuk.ccslab.cm.*;
import kr.ac.konkuk.ccslab.cm.stub.*;
import kr.ac.konkuk.ccslab.cm.entity.*;

public class initGame {

	public static String card[] = {"JAMES", "JIN", "YUMI", "DEER", "WOONG", "BOBBY", "KNIFE", "PIPE", "ROPE", "GUN", "HAMMER", "WRENCH",
			"GARAGE", "BATHROOM", "KITCHEN", "YARD", "LIVINGROOM", "BALCONY", "DININGROOM", "BEDROOM", "LIBRARY"};
	
	public static int character; // card[0] ~ card[5] (cbr)
	public static int weapon; // card[6] ~ card[11] (cbr)
	public static int place; // card[12] ~ card[20] (cbr)
	public static int playerCard[] = new int[18]; // �÷��̾���� ī�尡 ����� �迭 (cbr)
	
	public static String playerTurn[]; // �÷��̾���� ������ ����� �迭 (cbv)
	
	// �÷��̾���� ������ ����
	private void playerTurn() {
		
		CMMember cmMember = new CMMember();
		Random random = new Random();
		
		int pNum = cmMember.getMemberNum(); /* CM method ��� */
		Vector<CMUser> pVector = cmMember.getAllMembers(); /* CM method ��� */
				
		// �÷��̾� �� ��ŭ�� ���� ���� ����
		int arr[] = new int[pNum];
		for(int i=0; i<pNum; i++) {
			arr[i] = random.nextInt(pNum);
			for(int j=0; j<i; j++) {
				if(arr[i]==arr[j]) {
					i--;
				}
			}
		}	

	}

	// ����ī�� ����
	private void answerCard() {
		
		Random random = new Random();
		character = random.nextInt(6);
		weapon = random.nextInt(6) + 6;
		place = random.nextInt(9) + 12;

	}
	
	// ���� ī�� ���� �й�
	private void distributeCard() {
		
		Random random = new Random();
		
		// 21���� ī�带 ����
		int arr[] = new int[21];
		for(int i=0; i<21; i++) {
			arr[i] = random.nextInt(21);
			for(int j=0; j<i; j++) {
				if(arr[i]==arr[j]) {
					i--;
				}
			}
		}

		// ���� ī�带 ������ 18���� ī�带 �й�
		int k = 0;
		
		for(int i=0; i<21; i++) {
			
			if(arr[i] != character && arr[i] != weapon && arr[i] != place) {
				playerCard[k] = arr[i];
				k++;
			}
		}
	}
	
	// ī�� �й� �� ������ ī��
	private void openCard() {
		
		CMMember cmMember = new CMMember();
		int pNum = cmMember.getMemberNum(); /* CM method ��� */
		int cNum = 18 - (pNum*3); // ������ ī���� ��
		int[] openCard = new int[cNum]; // ������ ī���� �迭 (cbr)
		
		for(int i =0; i<cNum; i++) {
			openCard[i] = playerCard[pNum*3 + i];
		}		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		initGame initGame = new initGame();
		initGame.answerCard();
		initGame.distributeCard();
	}

}
