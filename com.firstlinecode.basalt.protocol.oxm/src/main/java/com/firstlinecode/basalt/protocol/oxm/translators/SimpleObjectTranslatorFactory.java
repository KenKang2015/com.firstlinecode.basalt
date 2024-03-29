package com.firstlinecode.basalt.protocol.oxm.translators;

import java.lang.reflect.Method;

import com.firstlinecode.basalt.protocol.core.Protocol;
import com.firstlinecode.basalt.protocol.oxm.TextObject;
import com.firstlinecode.basalt.protocol.oxm.translating.IProtocolWriter;
import com.firstlinecode.basalt.protocol.oxm.translating.ITranslatingFactory;
import com.firstlinecode.basalt.protocol.oxm.translating.ITranslator;
import com.firstlinecode.basalt.protocol.oxm.translating.ITranslatorFactory;

public class SimpleObjectTranslatorFactory<T> implements ITranslatorFactory<T> {
	private ITranslator<T> translator;
	
	private Class<T> type;
	private Protocol protocol;
	
	private Method getTextMethod;
	
	public SimpleObjectTranslatorFactory(Class<T> type, Protocol protocol) {
		this(type, protocol, "getText");
	}
	
	public SimpleObjectTranslatorFactory(Class<T> type, Protocol protocol, String getTextMethodName) {
		this.type = type;
		this.protocol = protocol;
		
		if (!TextObject.class.isAssignableFrom(type)) {
			try {
				getTextMethod = type.getMethod(getTextMethodName, new Class<?>[] {});
			} catch (Exception e) {
				// ignore
			}
		}
		
		translator = new SimpleObjectTranslator<>();
	}

	@Override
	public Class<T> getType() {
		return type;
	}

	@Override
	public ITranslator<T> create() {
		return translator;
	}
	
	private class SimpleObjectTranslator<K> implements ITranslator<K> {

		@Override
		public Protocol getProtocol() {
			return protocol;
		}

		@Override
		public String translate(K object, IProtocolWriter writer, ITranslatingFactory translatingFactory) {
			writer.writeProtocolBegin(protocol);
			
			if (object instanceof TextObject) {
				String text = ((TextObject)object).getText();
				if (text != null) {
					writer.writeText(text);
				}
			} else {
				if (getTextMethod != null) {
					try {
						Object oText = getTextMethod.invoke(object, new Object[0]);
						
						if (oText != null)
							writer.writeText(oText.toString());
					} catch (Exception e) {
						throw new RuntimeException("Can't invoke getText method.", e);
					}
				}
			}
			
			writer.writeProtocolEnd();
			
			return writer.toString();
		}
	}

}
