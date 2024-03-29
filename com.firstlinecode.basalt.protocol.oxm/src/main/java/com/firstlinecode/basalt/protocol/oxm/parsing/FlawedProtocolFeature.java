package com.firstlinecode.basalt.protocol.oxm.parsing;

import java.util.List;

import com.firstlinecode.basalt.protocol.core.ProtocolChain;
import com.firstlinecode.basalt.protocol.core.stream.Feature;

public class FlawedProtocolFeature extends FlawedProtocolObject implements Feature {
	public FlawedProtocolFeature() {
		super();
	}
	
	public FlawedProtocolFeature(List<ProtocolChain> flaws) {
		super(flaws);
	}
}
