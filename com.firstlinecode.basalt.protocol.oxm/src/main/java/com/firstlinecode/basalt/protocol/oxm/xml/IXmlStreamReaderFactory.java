package com.firstlinecode.basalt.protocol.oxm.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public interface IXmlStreamReaderFactory {
	XMLStreamReader createXmlStreamReader(String message) throws XMLStreamException;
}