package planetExpress;

import planetExpress.SQLAccess;

public class Main {
	public static void main(String[] args) throws Exception {
	    SQLAccess dao = new SQLAccess();
	    dao.readDataBase();
	}
}
