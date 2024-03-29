package com.firstlinecode.basalt.xeps.address;

import java.util.ArrayList;
import java.util.List;

import com.firstlinecode.basalt.protocol.core.Protocol;
import com.firstlinecode.basalt.protocol.oxm.convention.annotations.Array;
import com.firstlinecode.basalt.protocol.oxm.convention.annotations.ProtocolObject;

@ProtocolObject(namespace="http://jabber.org/protocol/address", localName="addresses")
public class Addresses {
	public static final Protocol PROTOCOL = new Protocol("http://jabber.org/protocol/address", "addresses");
	
	@Array(type=Address.class, elementName="address")
	private List<Address> addresses;

	public List<Address> getAddresses() {
		if (addresses == null) {
			addresses = new ArrayList<>();
		}
		
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
