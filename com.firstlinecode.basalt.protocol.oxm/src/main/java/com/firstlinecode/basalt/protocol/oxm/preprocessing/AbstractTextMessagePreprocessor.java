package com.firstlinecode.basalt.protocol.oxm.preprocessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.firstlinecode.basalt.protocol.core.ProtocolException;
import com.firstlinecode.basalt.protocol.core.stream.error.XmlNotWellFormed;

public abstract class AbstractTextMessagePreprocessor implements ITextMessagePreprocessor {
	private static final int DEFAULT_MAX_BUFFER_SIZE = 1024 * 1024;
	
	protected int maxBufferSize;
	protected int lastFoundMessageEnd = 0;
	
	protected char[] buffer = new char[0];
	protected List<String> messages = new ArrayList<>();
	
	protected int index = 0;
	protected boolean found = false;
	
	public AbstractTextMessagePreprocessor() {
		maxBufferSize = getDefaultMaxBufferSize();
	}
	
	@Override
	public String[] process(char[] chars, int length) throws OutOfMaxBufferSizeException, ProtocolException {
		resetBuffer();
		
		if (buffer.length > maxBufferSize) {
			throw new OutOfMaxBufferSizeException("Message is so big. Stop to process it.");
		}
		
		buffer = appendCharsToBuffer(chars, length);
		
		if (!findNextNonWhitespaceChar(false))
			return new String[0];
		
		if (!parseMessage()) {
			return new String[0];
		}
		
		while (findNextNonWhitespaceChar()) {
			if (!parseMessage()) {
				break;
			}
		}
		
		return getMessages();
	}

	protected char[] appendCharsToBuffer(char[] chars, int length) {
		char[] newBuffer = new char[buffer.length + length];
		
		System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
		System.arraycopy(chars, 0, newBuffer, buffer.length, length);
		
		return newBuffer;
	}
	
	protected boolean findNextNonWhitespaceChar(boolean ignoreCurrent) {
		if (!inRange())
			return false;
		
		if (ignoreCurrent)
			index++;
		
		while (inRange() && Character.isWhitespace(buffer[index])) {
			index++;
		}
		
		return buffer[index] != ' ';
	}
	
	protected boolean findNextNonWhitespaceChar() {
		return findNextNonWhitespaceChar(true);
	}
	
	protected boolean inRange() {
		if (buffer.length == 0)
			return false;
		
		return index < buffer.length - 1;
	}
	
	@Override
	public void resetBuffer() {
		if (buffer.length > 0 && lastFoundMessageEnd != 0) {
			if (found && lastFoundMessageEnd == index) {
				buffer = new char[0];
			} else {
				buffer = Arrays.copyOfRange(buffer, lastFoundMessageEnd + 1, buffer.length);
			}
		}
		
		index = lastFoundMessageEnd = 0;
		found = false;
		doResetBuffer();
	}
	
	@Override
	public String[] getMessages() {
		String[] messagesArray = new String[messages.size()];
		messagesArray = messages.toArray(messagesArray);
		
		messages.clear();
		resetBuffer();
		
		return messagesArray;
	}

	@Override
	public void setMaxBufferSize(int maxBufferSize) {
		this.maxBufferSize = maxBufferSize;
	}

	@Override
	public int getMaxBufferSize() {
		return maxBufferSize;
	}

	@Override
	public char[] getBuffer() {
		return buffer;
	}
	
	protected int getDefaultMaxBufferSize() {
		return DEFAULT_MAX_BUFFER_SIZE;
	}
	
	protected ProtocolException newXmlNotWellFormedException() {
		return new ProtocolException(new XmlNotWellFormed(String.format("Not a valid xmpp message. Error position: %d.", index)));
	}
	
	protected boolean checkChars(char[] chars, int offset) {
		for (int i = offset; i < chars.length; i++) {
			if (!inRange())
				return false;
			
			index++;
			if (buffer[index] != chars[i]) {
				throw newXmlNotWellFormedException();
			}
		}
		
		return true;
	}
	
	protected void messageFound() {
		String message;
		
		if (lastFoundMessageEnd == 0) {
			message = new String(buffer, 0, index - lastFoundMessageEnd + 1);
		} else {
			message = new String(buffer, lastFoundMessageEnd + 1, index - lastFoundMessageEnd);
		}
		
		messages.add(message);
		lastFoundMessageEnd = index;
		found = true;
	}
	
	@Override
	public void clear() {
		lastFoundMessageEnd = 0;
		buffer = new char[0];
		messages = new ArrayList<>();
		index = 0;
		found = false;
	}
	
	protected abstract boolean parseMessage();
	protected abstract void doResetBuffer();
}
