package com.firstlinecode.basalt.protocol.oxm.xep.ibr;

import com.firstlinecode.basalt.protocol.core.Protocol;
import com.firstlinecode.basalt.protocol.oxm.xep.oob.TXOob;
import com.firstlinecode.basalt.protocol.oxm.xep.xdata.TXData;

public class TIqRegister {
	public static final Protocol PROTOCOL = new Protocol("jabber:iq:register", "query");
	
	private Object register;
	
	private TXData xData;
	private TXOob oob;
	
	public Object getRegister() {
		return register;
	}
	
	public void setRegister(Object register) {
		this.register = register;
	}
	
	public TXData getXData() {
		return xData;
	}
	
	public void setXData(TXData xData) {
		this.xData = xData;
	}
	
	public TXOob getOob() {
		return oob;
	}
	
	public void setOob(TXOob oob) {
		this.oob = oob;
	}
	
}
