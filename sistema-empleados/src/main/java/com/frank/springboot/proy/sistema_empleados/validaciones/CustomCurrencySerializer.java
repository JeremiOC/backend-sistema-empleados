package com.frank.springboot.proy.sistema_empleados.validaciones;

import java.io.IOException;

import java.text.NumberFormat;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomCurrencySerializer extends JsonSerializer<Double>{

  

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es","PE"));
        String formattedValue = currencyFormat.format(value);

        gen.writeString(formattedValue);   
     }

}
