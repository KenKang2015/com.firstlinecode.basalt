package com.firstlinecode.basalt.protocol.oxm.parsing;

import com.firstlinecode.basalt.protocol.core.ProtocolChain;

public interface IParsingFactory {
	public static final Object KEY_FLAWS = new Object();
	
	Object parse(String message);
	Object parse(String message, boolean stream);
	
	void register(ProtocolChain chain, IParserFactory<?> parserFactory);
	void unregister(ProtocolChain chain);
}
