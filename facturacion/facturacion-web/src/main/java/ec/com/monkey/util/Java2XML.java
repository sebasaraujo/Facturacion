package ec.com.monkey.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ec.com.monkey.modelo.comprobantes.Factura;

public class Java2XML {

	public static String marshalFactura(Factura comprobante, String pathArchivoSalida)
	  {
	    String respuesta = null;
	    try
	    {
	      JAXBContext context = JAXBContext.newInstance(new Class[] { Factura.class });
	      Marshaller marshaller = context.createMarshaller();
	      marshaller.setProperty("jaxb.encoding", "UTF-8");
	      marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
	      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	      OutputStreamWriter out = new OutputStreamWriter(byteArrayOutputStream, "UTF-8");
	      marshaller.marshal(comprobante, out);
	      OutputStream outputStream = new FileOutputStream(pathArchivoSalida);
	      byteArrayOutputStream.writeTo(outputStream);
	      byteArrayOutputStream.close();
	      outputStream.close();
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	      return ex.getMessage();
	    }
	    return respuesta;
	  }
	
}
