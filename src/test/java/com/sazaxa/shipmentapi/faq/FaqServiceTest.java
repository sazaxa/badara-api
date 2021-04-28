package com.sazaxa.shipmentapi.faq;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.sazaxa.shipmentapi.config.AwsConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FaqServiceTest {

    private FaqService faqService;
    private FaqRepository faqRepository;
    private ClassPathResource IMAGE;
    
    @Autowired
    private AwsConfig awsConfig;

    private final Regions region = Regions.AP_NORTHEAST_2;
    private final String bucketName = "badara-image";
    private final String keyName = "faq/";


    @BeforeEach
    void setUp() {
        faqRepository = mock(FaqRepository.class);
        faqService = new FaqService(faqRepository);
        IMAGE = new ClassPathResource("green.PNG");
    }

    @Test
    void testMockito(){
       Faq mockedFaq = mock(Faq.class);

       when(mockedFaq.getTitle()).thenReturn("dummy-test-title-1");
       assertThat(mockedFaq.getTitle()).isEqualTo("dummy-test-title-1");

       verify(mockedFaq).getTitle();
    }

    @Test
    void getAllFaq() {
        Faq faq = Faq.builder()
                .title("dummy-test-title-1")
                .build();

        given(faqRepository.findAll()).willReturn(Arrays.asList(faq));
        List<Faq> list = faqService.getAllFaq();

        assertThat(list.get(0).getTitle()).isEqualTo("dummy-test-title-1");
        verify(faqRepository).findAll();
    }

    @Test
    void getFaqById() {
        Faq faq = Faq.builder()
                .id(1L)
                .title("dummy-test-title-1")
                .build();

        given(faqRepository.findById(1L)).willReturn(Optional.of(faq));
        Faq mockFaq = faqService.getFaqById(1L);

        assertThat(mockFaq.getTitle()).isEqualTo("dummy-test-title-1");
        verify(faqRepository).findById(1L);
    }

    @Test
    void testFileName(){
        assertThat(IMAGE.getFilename()).isEqualTo("green.PNG");
    }

    @Test
    void testGetKey(){
        System.out.println(awsConfig.getAccessKeyId());
    }


    @Test
    void testUploadImg(){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsConfig.getAccessKeyId(), awsConfig.getSecretAccessKey());
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(region)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .build();

            TransferManager tm = TransferManagerBuilder.standard()
                    .withS3Client(s3Client)
                    .build();

            String imageUrl = RandomStringUtils.randomAlphanumeric(12);

            // TransferManager processes all transfers asynchronously,
            // so this call returns immediately.
            File image = IMAGE.getFile();
            Upload upload = tm.upload(bucketName,keyName + imageUrl ,image);
            System.out.println("Object upload started");

            // Optionally, wait for the upload to finish before continuing.
            upload.waitForCompletion();
            System.out.println("Object upload complete");

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
