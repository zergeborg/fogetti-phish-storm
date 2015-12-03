package fogetti.phish.storm.io;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class RDFReadingTest {

	@Ignore
	@Test
	public void readRdf() throws Exception {
		// Given
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open("/Users/gergely.nagy/Downloads/App/PhishStorm/content.rdf.u8.gz");

		// When
		model.read(in, "http://www.w3.org/TR/RDF/", "RDF/XML");

		// Then
		model.write(System.out);
	}
	
	@Ignore
    @Test
	public void readWithSAX() throws Exception {
		// Given
    	InputSource is = new InputSource(new FileInputStream("/Users/gergely.nagy/Downloads/App/PhishStorm/content.rdf.u8"));
        
		// When
    	XMLReader r = XMLReaderFactory.createXMLReader();

		// Then
        r.setContentHandler(new RDFHandler());
        r.parse(is);
	}
}