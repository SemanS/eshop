package com.webinson.zuzka.utils;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Slavo on 14.09.2016.
 */
public class Converter {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    //Converts Object to XML file
    public void doMarshaling(String fileName, Object graph) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            marshaller.marshal(graph, new StreamResult(fos));
        } finally {
            fos.close();
        }
    }

    //Converts XML to Java Object
    public Object doUnMarshaling(InputStream input) throws IOException {
        try {
            return unmarshaller.unmarshal(new StreamSource(input));
        } finally {
            input.close();
        }
    }
}
