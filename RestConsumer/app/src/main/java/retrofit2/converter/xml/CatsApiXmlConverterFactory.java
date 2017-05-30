package retrofit2.converter.xml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public final class CatsApiXmlConverterFactory extends Converter.Factory {

    private CatsApiXmlConverterFactory() {
    }

    public static CatsApiXmlConverterFactory create() { return new CatsApiXmlConverterFactory(); }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new CatsApiXmlResponseBodyConverter();
    }
}
