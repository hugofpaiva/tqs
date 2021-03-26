package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

public class AddressResolverIT {

    private ISimpleHttpClient httpClient;
    private AddressResolver resolver;


    @BeforeEach
    public void init(){
        httpClient = new TqsBasicHttpClient();
        resolver = new AddressResolver(httpClient);
    }

    @Test
    public void whenGoodCoordinates_returnAddress() throws IOException, URISyntaxException, ParseException {
        Address result = resolver.findAddressForLocation(40.640661, -8.656688);
        assertEquals( result, new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null) );
    }

    @Test
    public void whenBadCoordinates_throwBadArrayIndex() throws IOException, URISyntaxException, ParseException {
        assertThrows(IndexOutOfBoundsException.class, () -> resolver.findAddressForLocation(220.000000,-220.000000));
    }

    @Test
    public void whenURLNull_throwNullPointerException() throws IOException, URISyntaxException, ParseException {
        assertThrows(NullPointerException.class, () -> httpClient.get(null));
    }

}
