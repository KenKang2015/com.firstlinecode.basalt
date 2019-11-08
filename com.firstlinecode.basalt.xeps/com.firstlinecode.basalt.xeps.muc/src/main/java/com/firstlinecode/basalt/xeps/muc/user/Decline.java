package com.firstlinecode.basalt.xeps.muc.user;

import com.firstlinecode.basalt.protocol.core.JabberId;
import com.firstlinecode.basalt.protocol.oxm.convention.annotations.TextOnly;
import com.firstlinecode.basalt.protocol.oxm.convention.conversion.annotations.String2JabberId;

public class Decline {
	@TextOnly
	private String reason;
	@String2JabberId
	private JabberId from;
	@String2JabberId
	private JabberId to;
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public JabberId getFrom() {
		return from;
	}
	
	public void setFrom(JabberId from) {
		this.from = from;
	}
	
	public JabberId getTo() {
		return to;
	}
	
	public void setTo(JabberId to) {
		this.to = to;
	}
	
}
