package com.firstlinecode.basalt.protocol.oxm.parsing;

import com.firstlinecode.basalt.protocol.core.stanza.Stanza;

public class ParsingException extends RuntimeException {
	private static final long serialVersionUID = -6198730769256774136L;
	
	private Exception cause;
	private Stanza stanza;
	
	public ParsingException(Exception cause, Stanza stanza) {
		this.cause = cause;
		this.stanza = stanza;
	}
	
	public Exception getCause() {
		return cause;
	}
	
	public Stanza getStanza() {
		return stanza;
	}
}
