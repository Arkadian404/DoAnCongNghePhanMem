package shop.Services;

public interface IAccountServices {
	boolean authenticateAccount(String username, String password);
	String findRoleAccount(String username, String password);
	void registerAccount(String username, String password);
	void becomeSeller(int sellerId,String username);
	int findSellerId(String username);
	int findAccountId(String username);
}
