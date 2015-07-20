package org.springtest.first.spryng;

import java.util.List;

public interface AddressDAO {
	public int addEntry(Address address);

	public List<Address> getAddress(String city);

	public List<Address> getAllAddresses();

	public void updateAddress(Address address);

	public int deleteAddress(Address address);

	public int deleteAllAddresses();
}
