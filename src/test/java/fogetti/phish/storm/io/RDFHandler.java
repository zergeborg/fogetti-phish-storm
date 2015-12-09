package fogetti.phish.storm.io;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class RDFHandler implements ContentHandler {

	private final IPAddressValidator validator = new IPAddressValidator();
	private final BufferedWriter writer;

	public RDFHandler(BufferedWriter writer) {
		this.writer = writer;
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		try {
			String resource = atts.getValue("http://www.w3.org/TR/RDF/", "resource");
			if (resource != null) {
				// System.out.println(String.format("[Resource=%s]", resource));
				String url = resource.split("//")[1];
				String[] split = StringUtils.split(url, "/");
				if (split.length > 1) {
					String mldps = split[0];
					if (!validator.matches(mldps)) {
						writer.write(resource+"\n");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

}
