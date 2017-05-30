package retrofit2.converter.xml;

import android.util.Log;

import com.blisek.restconsumer.cats.structs.CatsPictures;
import com.blisek.restconsumer.icndb.async.RetrofitCall;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public class CatsApiXmlResponseBodyConverter implements Converter<ResponseBody, CatsPictures> {
    private static final String TAG = "CatsApiXmlResponseBodyC";

    @Override
    public CatsPictures convert(ResponseBody value) throws IOException {
        CatsPictures catsPictures = new CatsPictures();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(value.byteStream());
            NodeList nList = doc.getElementsByTagName("url");
            int nListLength = nList.getLength();
            List<URL> picturesUrls = new ArrayList<>(nListLength);
            for(int i = 0; i < nListLength; ++i) {
                Node node = nList.item(i);
                if(node.getNodeType() != Node.ELEMENT_NODE) continue;
                Element element = (Element)node;
                picturesUrls.add(new URL(element.getTextContent()));
            }

            catsPictures.setPicturesUrls(picturesUrls);
        }
        catch (ParserConfigurationException | SAXException e) {
            Log.e(TAG, "Error occurred while parsing xml.", e);
        }

        return catsPictures;
    }
}
