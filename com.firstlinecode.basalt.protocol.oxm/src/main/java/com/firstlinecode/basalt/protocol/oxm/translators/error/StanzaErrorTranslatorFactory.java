package com.firstlinecode.basalt.protocol.oxm.translators.error;

import com.firstlinecode.basalt.protocol.core.LangText;
import com.firstlinecode.basalt.protocol.core.Protocol;
import com.firstlinecode.basalt.protocol.core.stanza.Iq;
import com.firstlinecode.basalt.protocol.core.stanza.error.StanzaError;
import com.firstlinecode.basalt.protocol.im.stanza.Message;
import com.firstlinecode.basalt.protocol.im.stanza.Presence;
import com.firstlinecode.basalt.protocol.oxm.Attributes;
import com.firstlinecode.basalt.protocol.oxm.translating.IProtocolWriter;
import com.firstlinecode.basalt.protocol.oxm.translating.ITranslatingFactory;
import com.firstlinecode.basalt.protocol.oxm.translating.ITranslator;
import com.firstlinecode.basalt.protocol.oxm.translating.ITranslatorFactory;
import com.firstlinecode.basalt.protocol.oxm.xml.translating.error.XmlSenderMessageStripper;

public class StanzaErrorTranslatorFactory implements ITranslatorFactory<StanzaError> {
	private ITranslator<StanzaError> translator;
	
	public StanzaErrorTranslatorFactory() {
		translator = new StanzaErrorTranslator();
	}

	@Override
	public Class<StanzaError> getType() {
		return StanzaError.class;
	}

	@Override
	public ITranslator<StanzaError> create() {
		return translator;
	}
	
	private class StanzaErrorTranslator implements ITranslator<StanzaError> {

		@Override
		public Protocol getProtocol() {
			return StanzaError.PROTOCOL;
		}

		@Override
		public String translate(StanzaError error, IProtocolWriter writer, ITranslatingFactory translatingFactory) {
			StanzaError.Kind kind = error.getKind();
			if (kind == StanzaError.Kind.PRESENCE) {
				writer.writeProtocolBegin(Presence.PROTOCOL); // 1{
			} else if (kind == StanzaError.Kind.MESSAGE) {
				writer.writeProtocolBegin(Message.PROTOCOL); // 1{
			} else {
				writer.writeProtocolBegin(Iq.PROTOCOL); // 1{
			}
			
			writer.writeAttributes(new Attributes().
					add("from", error.getFrom()).
					add("to", error.getTo()).
					add("id", error.getId()).
					add(LangText.PREFIX_LANG_TEXT, LangText.LOCAL_NAME_LANG_TEXT, error.getLang()).
					add("type", "error").
					get()
				);
			
			String senderMessage = getSenderMessage(error);
			if (senderMessage != null) {
				writer.writeString(senderMessage);
			}
			
			writer.writeProtocolBegin(new Protocol("error")); // 2{
			
			writer.writeAttributes(new Attributes().
					add("type", error.getType().toString().toLowerCase()).
					get()
				);
			
			if (error.getDefinedCondition() != null) {
				writer.writeProtocolBegin(new Protocol(StanzaError.NAMESPACE_STANZA_ERROR_CONTEXT,
						error.getDefinedCondition())); // 3{
				writer.writeProtocolEnd(); // }3
			}
			
			if (error.getText() != null) {
				writer.writeProtocolBegin(new Protocol(StanzaError.NAMESPACE_STANZA_ERROR_CONTEXT, "text"));
				writer.writeAttributes(new Attributes().
						add(LangText.PREFIX_LANG_TEXT, LangText.LOCAL_NAME_LANG_TEXT, error.getText().getLang()).
						get());
				writer.writeText(error.getText().getText());
				writer.writeProtocolEnd();
			}
			
			if (error.getApplicationSpecificCondition() != null) {
				String applicationSpecificCondition = translatingFactory.translate(
						error.getApplicationSpecificCondition());
				
				if (applicationSpecificCondition != null) {
					writer.writeString(applicationSpecificCondition);
				}
			}
			
			writer.writeProtocolEnd(); // }2
			writer.writeProtocolEnd(); // }1
			
			return writer.getDocument();
		}

		private String getSenderMessage(StanzaError error) {
			if (error.getSenderMessage() != null)
				return error.getSenderMessage();
			
			if (error.getOriginalMessage() != null) {
				return stripSenderMessage(error.getOriginalMessage());
			}
			
			return null;
		}

		private String stripSenderMessage(String originalMessage) {
			try {
				return stripXmlSenderMessage(originalMessage);
			} catch (Exception e) {
				// ignore exception
				return null;
			}
		}

		private String stripXmlSenderMessage(String originalMessage) {
			return new XmlSenderMessageStripper().strip(originalMessage);
		}
		
	}

}
