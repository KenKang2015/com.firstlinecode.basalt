package com.firstlinecode.basalt.protocol.oxm.convention.conversion.factories;

import java.lang.annotation.Annotation;

import com.firstlinecode.basalt.protocol.oxm.convention.conversion.IConverterFactory;
import com.firstlinecode.basalt.protocol.oxm.convention.conversion.annotations.String2Enum;
import com.firstlinecode.basalt.protocol.oxm.conversion.IConverter;
import com.firstlinecode.basalt.protocol.oxm.conversion.converters.String2EnumConverter;

public class String2EnumConverterFactory implements IConverterFactory {
	
	@Override
	public IConverter<String, Object> create(Annotation annotation) {
		String2Enum string2Enum = (String2Enum)annotation;
		return new String2EnumConverter(string2Enum.type(), string2Enum.upperCaseString(),
				string2Enum.dashToUnderline());
	}
	
}
