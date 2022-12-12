package shop.DAO;

public interface IIventoryDAO {	
	void insertInventory(int productId,int storeId,int amount);
	void deleteInventory(int productId,int storeId);
	void updateInventory(int productId,int storeIdint,int amount);
	int findAmount(int productId);
}
