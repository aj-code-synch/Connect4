package connect4;

public class SO_30265772 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Boolean ret = SO_30265772.myMethod();
		System.out.println(ret);
	}


	public static Boolean  myMethod() {


		Boolean r = true;
		try {
			throw new Exception("");
		} catch (Exception e) {
			return r;
		} finally {
			r = false;
		}



	}
}
