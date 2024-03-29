package com.firstlinecode.basalt.protocol.oxm;

import com.firstlinecode.basalt.protocol.core.ProtocolChain;
import com.firstlinecode.basalt.protocol.im.stanza.Message;
import com.firstlinecode.basalt.protocol.im.stanza.Presence;
import com.firstlinecode.basalt.protocol.oxm.parsers.im.MessageParserFactory;
import com.firstlinecode.basalt.protocol.oxm.parsers.im.PresenceParserFactory;
import com.firstlinecode.basalt.protocol.oxm.parsing.IParsingFactory;
import com.firstlinecode.basalt.protocol.oxm.translating.ITranslatingFactory;
import com.firstlinecode.basalt.protocol.oxm.translators.im.MessageTranslatorFactory;
import com.firstlinecode.basalt.protocol.oxm.translators.im.PresenceTranslatorFactory;

public class StandardOxmFactory extends MinimumOxmFactory {
	public StandardOxmFactory(IParsingFactory parsingFactory, ITranslatingFactory translatingFactory) {
		super(parsingFactory, translatingFactory);
		registerPresenceAndMessageProtocols();
	}

	private void registerPresenceAndMessageProtocols() {
		// register parsers
		register(ProtocolChain.first(Presence.PROTOCOL), new PresenceParserFactory());
		register(ProtocolChain.first(Message.PROTOCOL), new MessageParserFactory());
		
		// register translators
		register(Presence.class, new PresenceTranslatorFactory());
		register(Message.class, new MessageTranslatorFactory());
	}
}
