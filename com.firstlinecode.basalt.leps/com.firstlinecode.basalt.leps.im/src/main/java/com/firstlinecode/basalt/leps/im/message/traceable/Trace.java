package com.firstlinecode.basalt.leps.im.message.traceable;

import java.util.ArrayList;
import java.util.List;

import com.firstlinecode.basalt.protocol.core.Protocol;
import com.firstlinecode.basalt.protocol.oxm.convention.annotations.Array;
import com.firstlinecode.basalt.protocol.oxm.convention.annotations.ProtocolObject;

@ProtocolObject(namespace="urn:lep:traceable", localName="trace")
public class Trace {
	public static final Protocol PROTOCOL = new Protocol("urn:lep:traceable", "trace");
	
	@Array(type=MsgStatus.class, elementName="msg-status")
	private List<MsgStatus> msgStatuses;
	
	public Trace() {}
	
	public Trace(MsgStatus msgStatus) {
		getMsgStatuses().add(msgStatus);
	}
	
	public Trace(List<MsgStatus> msgStatuses) {
		this.msgStatuses = msgStatuses;
	}

	public List<MsgStatus> getMsgStatuses() {
		if (msgStatuses == null) {
			msgStatuses = new ArrayList<>();
		}
		
		return msgStatuses;
	}

	public void setMsgStatuses(List<MsgStatus> msgStatuses) {
		this.msgStatuses = msgStatuses;
	}
	
}
