package com.firstlinecode.basalt.protocol.oxm.conversion.converters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.firstlinecode.basalt.protocol.oxm.convention.conversion.Converter;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Converter(TString2XDataTypeConverter.class)
public @interface TString2XDataType {
}
