package com.firstlinecode.basalt.protocol.core.stream;

import com.firstlinecode.basalt.protocol.core.Protocol;

public class Session implements Feature {
	public static final Protocol PROTOCOL = new Protocol("urn:ietf:params:xml:ns:xmpp-session", "session");
}
