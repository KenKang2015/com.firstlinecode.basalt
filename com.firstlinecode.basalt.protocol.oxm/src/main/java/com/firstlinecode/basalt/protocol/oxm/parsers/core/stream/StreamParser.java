package com.firstlinecode.basalt.protocol.oxm.parsers.core.stream;

import java.util.List;

import com.firstlinecode.basalt.protocol.core.JabberId;
import com.firstlinecode.basalt.protocol.core.ProtocolException;
import com.firstlinecode.basalt.protocol.core.stream.Stream;
import com.firstlinecode.basalt.protocol.core.stream.error.InvalidXml;
import com.firstlinecode.basalt.protocol.oxm.Attribute;
import com.firstlinecode.basalt.protocol.oxm.Value;
import com.firstlinecode.basalt.protocol.oxm.annotations.Parser;
import com.firstlinecode.basalt.protocol.oxm.annotations.ProcessAttributes;
import com.firstlinecode.basalt.protocol.oxm.annotations.ProcessText;
import com.firstlinecode.basalt.protocol.oxm.parsing.BadMessageException;
import com.firstlinecode.basalt.protocol.oxm.parsing.IParsingContext;

@Parser(namespace="http://etherx.jabber.org/streams", localName="stream", objectType=Stream.class)
public class StreamParser {
	@ProcessText("/close")
	public void processCloseText(IParsingContext<Stream> context, Value<?> close) {
		if (close != null) {
			throw new BadMessageException("/stream/close should be a boolean only element.");
		}
			
		context.getObject().setClose(true);
	}
	
	@ProcessAttributes("/")
	public void processAttributes(IParsingContext<Stream> context, List<Attribute> attributes) {
		Stream stream = context.getObject();
		
		for (Attribute attribute : attributes) {
			if ("from".equals(attribute.getName())) {
				stream.setFrom(JabberId.parse(attribute.getValue().getString()));
			} else if ("to".equals(attribute.getName())) {
				stream.setTo(JabberId.parse(attribute.getValue().getString()));
			} else if ("id".equals(attribute.getName())) {
				stream.setId(attribute.getValue().getString());
			} else if ("version".equals(attribute.getName())) {
				stream.setVersion(attribute.getValue().getString());
			} else if ("xml:lang".equals(attribute.getName())) {
				stream.setLang(attribute.getValue().getString());
			} else {
				throw new ProtocolException(new InvalidXml(String.format("Unknown stream attribute: %s.",
						attribute.getName())));
			}
		}
	}
}
