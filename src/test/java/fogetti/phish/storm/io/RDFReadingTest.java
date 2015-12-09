package fogetti.phish.storm.io;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

@Ignore
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
	
    @Test
	public void readWithSAX() throws Exception {
		// Given
    	InputSource is = new InputSource(new FileInputStream("/Users/gergely.nagy/Downloads/App/PhishStorm/content.rdf.u8"));
        
		// When
    	XMLReader r = XMLReaderFactory.createXMLReader();

		// Then
    	Path path = Paths.get("/Users/gergely.nagy/Downloads/App/PhishStorm/result/valid-urls.csv");
    	try (BufferedWriter writer = Files.newBufferedWriter(path)) {
    	    r.setContentHandler(new RDFHandler(writer));
    	    r.parse(is);
    	}
	}
}