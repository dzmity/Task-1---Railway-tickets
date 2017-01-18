package by.epam.rafalovich.railway_tickets.entity;

public enum UserRole {
	
	USER(1), ADMIN(2), GUEST(3);
	
	private int roleId;
	
	

	public int getRoleId() {
		return roleId;
	}
	

	private UserRole(int roleId) {
		this.roleId = roleId;
	}
	
	/*public UserRole getById(int id) {
		
		for(UserRole x: UserRole.values()) {
			if (id == x.getRoleId()) {
				return x;
			}
		}
		 return GUEST;
	}*/
	
	
}
