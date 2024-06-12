package school.sptech.northink.projetonorthink.api.configuration;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Configuration
public class AzureConfiguration {

    @Value("${azure.defaultEndpointsProtocol}")
    private String defaultEndpointsProtocol;

    @Value("${azure.accountName}")
    private String accountName;

    @Value("${azure.accountKey}")
    private String accountKey;

    @Value("${azure.endpointSuffix}")
    private String endpointSuffix;

    @Bean
    @Scope(value = "prototype")
    public CloudBlobClient cloudBlobClient() throws URISyntaxException, InvalidKeyException {
        final String connectionString = String.format("DefaultEndpointsProtocol=%s;AccountName=%s;AccountKey=%s;EndpointSuffix=%s",
                defaultEndpointsProtocol, accountName, accountKey, endpointSuffix);
        return CloudStorageAccount.parse(connectionString).createCloudBlobClient();
    }
}
