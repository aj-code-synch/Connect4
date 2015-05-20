package connect4;

import java.util.Arrays;

public class PassbyRefTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] value = new int[1];

		PassbyRefTest test = new PassbyRefTest();

		test.method1(value);
 
		System.out.println("mainMehtod  " + value[0]);
	}

	public void method1(int[] num){
		int[] localnum = new int[1];
		localnum[0] = num[0];
		localnum[0] = localnum[0] + 1;
		System.out.println("Method1  " + localnum[0]);
		method2(localnum);
	}


	public void method2(int[] num){
		int[] localnum = new int[1];
		localnum[0] = num[0];
		localnum[0] = localnum[0] + 1;
		System.out.println("Method2  " + localnum[0]);
		
		
		int[] localnum2 = new int[1];
		localnum2 = Arrays.copyOf(localnum, 1);
		
		localnum2[0] = localnum2[0] + 1;
		System.out.println(localnum2[0] + "||" + localnum[0]);
		
	}


}
