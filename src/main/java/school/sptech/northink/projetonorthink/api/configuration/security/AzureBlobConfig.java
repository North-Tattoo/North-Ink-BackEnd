package school.sptech.northink.projetonorthink.api.configuration.security;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobConfig {

    @Value("${storagenorthink}")
    private String accountName;

    @Value("${amaBLvDVPkaVUeYMblFl5aggc5+6YtiD0FmfEgTy0UOZ8GIj/9cBH5PRWwLv/haKeU/2dMVQudR/+AStbBX59g==}")
    private String accountKey;

    @Value("${https://storagenorthink.blob.core.windows.net/imagens}")
    private String endpoint;

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new StorageSharedKeyCredential(accountName, accountKey))
                .buildClient();
    }

    @Bean
    public BlobContainerClient blobContainerClient(BlobServiceClient blobServiceClient) {

        return blobServiceClient.getBlobContainerClient("imagens");
    }
}
